#!/bin/sh

PROJECT_NAME=springboot-crud-codedeploy
WORK_PATH=/home/ec2-user/$PROJECT_NAME
DEPLOY_PATH=$WORK_PATH/
APPLICATION_JAR_NAME=$PROJECT_NAME.jar
APPLICATION_JAR=$DEPLOY_PATH$APPLICATION_JAR_NAME

user=`/usr/bin/id -u -n`

if [ "$user" = "ec2-user" ]
then
        echo "O.K!"
else
        echo "ec2-user account use only"
        exit 1
fi

nohup java -jar $APPLICATION_JAR --spring.profiles.active=sandbox --spring.config.location=classpath:/application.yml > $WORK_PATH/logs/nohup.out 2>&1 &
