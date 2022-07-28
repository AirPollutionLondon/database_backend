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

# Tar file of backup directory
# TAR=$DEST/../$TIME.tar.bz2

CSV=$DEST/$TIME-sensorReadings.csv

# Create backup dir (-p to avoid warning if already exists)
# /bin/mkdir -p $DEST

# Log
echo "Backing up $HOST/$DBNAME to s3://$BUCKET/ on $TIME";

# Dump from mongodb host into backup directory
# mongodump -h $HOST -d $DBNAME -o $DEST
echo "mongoexport --authenticationDatabase $DBAUTH -u $DBUSER -p $DBPASS -h $HOST:$PORT -d $DBNAME -o $CSV --collection=sensorReadings --type=csv --fields=_id,serialNumber,timeStamp,VOC,CO2,SPM1,SPM25,SPM10,AEC1,AEC25,AEC10"
mongoexport --authenticationDatabase $DBAUTH -u $DBUSER -p $DBPASS -h $HOST:$PORT -d $DBNAME -o $CSV --collection=sensorReadings --type=csv --fields=_id,serialNumber,timeStamp,VOC,CO2,SPM1,SPM25,SPM10,AEC1,AEC25,AEC10

# echo "mongo $DBNAME --eval 'db.collection_name.deleteMany( { \"date\": { $lt: \"$QDATE\" } } );'"
# mongosh  --authenticationDatabase $DBAUTH -u $DBUSER -p $DBPASS $HOST:$PORT/$DBNAME --eval 'db.sensorReadings.deleteMany( { "date": { $lt: "'$QDATE'" } } );'

# Create tar of backup directory
# /bin/tar -jcvf $TAR -C $DEST .

# Upload tar to s3
#/usr/bin/aws s3 cp $TAR s3://$BUCKET/
/usr/bin/aws s3 cp $CSV s3://$BUCKET/

# Remove tar file locally
# /bin/rm -f $TAR
rm -f $CSV

# Remove backup directory
/bin/rm -rf $DEST

# All done
echo "Backup available at https://s3.amazonaws.com/$BUCKET/$CSV"
