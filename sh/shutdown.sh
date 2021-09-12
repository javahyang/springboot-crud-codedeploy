#!/bin/sh

PROJECT_NAME=springboot-crud-codedeploy
WORK_PATH=/home/ec2-user/$PROJECT_NAME
DEPLOY_PATH=$WORK_PATH/
APPLICATION_JAR_NAME=$PROJECT_NAME.jar
APPLICATION_JAR=$DEPLOY_PATH$APPLICATION_JAR_NAME

echo "> 현재 실행중인 애플리케이션 pid 확인"
CURRENT_PID=$(pgrep -f $APPLICATION_JAR_NAME)

if [ -z $CURRENT_PID ]
then
  echo "> 현재 구동중인 애플리케이션이 없으므로 종료하지 않습니다."
else
  echo "> kill -15 $CURRENT_PID"
  kill -15 $CURRENT_PID
  sleep 5
fi
