# Database Backend
# London Air Pollution Project
## by Savvas Panagiotis Nikopoulos, Jess Fedor, Isabelle Papa, Antoine Assaf, Ahnaf Inkiad, Jenny Do
### at Northeastern University - London
2022.08.03

## Overview:
* Quality air to breathe is an important factor in one's overall quality of life. Clean air quality has been linked to
longer life expectancies while poor, polluted air has been linked to prolonging a number of medical issues such as asthma.
Keeping track of air pollutants is paramount to solving the issue. Computer systems provide an easy, automated way to
take, store, and visualize pollutant readings.
* Our project begins with installing inexpensive sensors to take pollutant readings scheduled on a specified interval
and broadcast that information to a web server that interfaces directly with a database backend. Data is then pulled out
of the database onto cloud storage where geospatial and historical analysis may be performed on the readings.
  


## Set up a root AWS account
* Detailed instructions on how to perform this task are linked [here](https://aws.amazon.com/premiumsupport/knowledge-center/create-and-activate-aws-account/).
After root user is created, set up IAM (Identity and Access Management) accounts on the dashboard using the tutorial provided [here](https://docs.aws.amazon.com/IAM/latest/UserGuide/id_users_create.html).
Note the security group set up process to assign different access levels to each user.


## Set up instance in AWS EC2
* Detailed instructions on how to perform this task are linked [here](https://docs.aws.amazon.com/AWSEC2/latest/UserGuide/EC2_GetStarted.html).
This project was done in Amazon Linux 2, but any platform may be used as long as terminal access is present.

## Set up MongoDB on Amazon Linux 2.
* MongoDB was the database backend used for this project, but many other NoSQL or SQL platforms are able to be used as well.
MongoDB provides clear instruction on set up [here](https://www.mongodb.com/docs/manual/tutorial/install-mongodb-on-amazon/).
Once the database is set up, create a structure for internal databases and collections that need to be supported. This project 
uses two databases: Users and Sensors. Sensors has two collections: sensorInfo and sensorReadings.

