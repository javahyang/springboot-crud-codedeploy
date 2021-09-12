#!/bin/bash

PROJECT_NAME=springboot-crud-codedeploy
WORK_PATH=/home/ec2-user/$PROJECT_NAME
BUILD_PATH=$(ls $WORK_PATH/build/*.jar)
JAR_NAME=$(basename $BUILD_PATH)

echo "> build 파일명: $JAR_NAME"

echo "> build 파일 복사"
DEPLOY_PATH=$WORK_PATH/
cp -R $BUILD_PATH $DEPLOY_PATH

echo "> $PROJECT_NAME jar 교체"
CP_JAR_PATH=$DEPLOY_PATH$JAR_NAME
APPLICATION_JAR_NAME=$PROJECT_NAME.jar
APPLICATION_JAR=$DEPLOY_PATH$APPLICATION_JAR_NAME

echo "> CP_JAR_PATH $CP_JAR_PATH"
echo "> APPLICATION_JAR $APPLICATION_JAR"

echo "> T: no-target-directory, f: force, s : symbolic"
ln -Tfs $CP_JAR_PATH $APPLICATION_JAR

echo "> 현재 실행중인 애플리케이션 pid 확인"
CURRENT_PID=$(pgrep -f $APPLICATION_JAR_NAME)

if [ -z $CURRENT_PID ]
then
  echo "> 현재 구동중인 애플리케이션이 없으므로 종료하지 않습니다.."
else
  echo "> kill -15 $CURRENT_PID"
  kill -15 $CURRENT_PID
  sleep 5
fi

echo "> $APPLICATION_JAR $WORK_PATH/sh/*.sh에 및 실행권한 추가" # 수동배포하는 sh에 실행권한 추가되어야 함
chmod +x $WORK_PATH/sh/*.sh

echo "> logs 폴더 없으면 생성"
mkdir -p $WORK_PATH/logs

echo "> $APPLICATION_JAR 배포"
echo "> CodeDeploy Name : $APPLICATION_NAME" # codedeploy 에서 가져오는 변수
echo "> CodeDeploy Group Name : $DEPLOYMENT_GROUP_NAME" # codedeploy 에서 가져오는 변수

nohup java -jar $APPLICATION_JAR --spring.profiles.active=$DEPLOYMENT_GROUP_NAME --spring.config.location=classpath:/application.yml > $WORK_PATH/logs/nohup.out 2>&1 &
