#!/bin/sh

# Make sure to:
# 1) Name this file `backup.sh` and place it in /home/ubuntu
# 2) Run sudo apt-get install awscli to install the AWSCLI
# 3) Run aws configure (enter s3-authorized IAM user and specify region)
# 4) Fill in DB host + name
# 5) Create S3 bucket for the backups and fill it in below (set a lifecycle rule to expire files older than X days in the bucket)
# 6) Run sudo mkdir /data/tmp
# 7) Run sudo chmod 777 /data/tmp/
# 8) Run chmod +x backup.sh
# 9) Test it out via ./backup.sh
# 10) Set up a daily backup at midnight via `crontab -e`:
#    0 0 * * * /home/ubuntu/backup.sh > /home/ubuntu/backup.log 2>&1
# 

# DB host (secondary preferred as to avoid impacting primary performance)
HOST=18.132.45.54
PORT=27017

# DB name
DBNAME=sensors
DBAUTH=admin
DBUSER=breatheuk
DBPASS=markmartin

# S3 bucket name
BUCKET=test-data452e1421

# Linux user account
USER=ec2-user

# Current time
TIME=`/bin/date +%d-%m-%Y-%T`

# Backup directory
DEST=/data/tmp/bck

# Assign locations for the 3 csv files
CSV=$DEST/$TIME-sensorReadings.csv
# CSV_INFO=$DEST/$TIME-sensorInfo.csv
# CSV_MERGED=$DEST/$TIME-sensorMerged.csv

# Create backup dir (-p to avoid warning if already exists)
/bin/mkdir -p $DEST

# Log
echo "Backing up $HOST/$DBNAME to s3://$BUCKET/ on $TIME";

# Get sensor reading information from the database and put it into a csv
echo "mongoexport --authenticationDatabase $DBAUTH -u $DBUSER -p $DBPASS -h $HOST:$PORT -d $DBNAME -o $CSV --collection=sensorReadings --type=csv --fields=serialNumber,timeStamp,VOC,CO2,SPM1,SPM25,SPM10,AEC1,AEC25,AEC10"
mongoexport --authenticationDatabase $DBAUTH -u $DBUSER -p $DBPASS -h $HOST:$PORT -d $DBNAME -o $CSV --collection=sensorReadings --type=csv --fields=serialNumber,timeStamp,VOC,CO2,SPM1,SPM25,SPM10,AEC1,AEC25,AEC10

# # Get sensor info information (meta-info) from the databse and put it into a csv
# echo "mongoexport --authenticationDatabase $DBAUTH -u $DBUSER -p $DBPASS -h $HOST:$PORT -d $DBNAME -o $CSV_INFO --collection=sensorInfo --type=csv --fields=lat,lon"
# mongoexport --authenticationDatabase $DBAUTH -u $DBUSER -p $DBPASS -h $HOST:$PORT -d $DBNAME -o $CSV_INFO --collection=sensorInfo --type=csv --fields=lat,lon

# # Merge csvs together
# paste -d , $CSV $CSV_INFO > $CSV_MERGED


# Upload csv to S3
/usr/bin/aws s3 cp $CSV s3://$BUCKET/

# Remove local archives of the csvs
rm -f $CSV
# rm -f $CSV_INFO
# rm -f $CSV_MERGED

# Remove backup directory
/bin/rm -rf $DEST

# All done
echo "Backup available at https://s3.amazonaws.com/$BUCKET/$CSV"
