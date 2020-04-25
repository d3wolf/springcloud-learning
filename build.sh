#!/bin/bash

RUN_HOME=/home/springcloud

MODULE=Eureka-Server

# eureka server
cd ${WORKSPACE}/${MODULE}
mvn clean package -DskipTests
cp target/${MODULE}-1.0-SNAPSHOT.jar ${RUN_HOME}/${MODULE}-1.0-SNAPSHOT.jar
nohup java -jar java -jar ${RUN_HOME}/${MODULE}-1.0-SNAPSHOT.jar --Dspring.profiles.active=single &

# provider
MODULE=User-Provider
cd ${WORKSPACE}/${MODULE}
mvn clean package -DskipTests
cp target/${MODULE}-1.0-SNAPSHOT.jar ${RUN_HOME}/${MODULE}-1.0-SNAPSHOT.jar
nohup java -jar ${RUN_HOME}/${MODULE}-1.0-SNAPSHOT.jar --Dspring.profiles.active=provider1 &
nohup java -jar ${RUN_HOME}/${MODULE}-1.0-SNAPSHOT.jar --Dspring.profiles.active=provider2 &

# consumer
MODULE=User-Consumer
cd ${WORKSPACE}/${MODULE}
mvn clean package -DskipTests
cp target/${MODULE}-1.0-SNAPSHOT.jar ${RUN_HOME}/${MODULE}-1.0-SNAPSHOT.jar
nohup java -jar ${RUN_HOME}/${MODULE}-1.0-SNAPSHOT.jar --Dspring.profiles.active=consumer1 &
nohup java -jar ${RUN_HOME}/${MODULE}-1.0-SNAPSHOT.jar --Dspring.profiles.active=consumer2 &

# admin
MODULE=Admin-Test
cd ${WORKSPACE}/${MODULE}
mvn clean package -DskipTests
cp target/${MODULE}-1.0-SNAPSHOT.jar ${RUN_HOME}/${MODULE}-1.0-SNAPSHOT.jar
nohup java -jar ${RUN_HOME}/${MODULE}-1.0-SNAPSHOT.jar &

# gateway
MODULE=User-Gateway
cd ${WORKSPACE}/${MODULE}
mvn clean package -DskipTests
cp target/${MODULE}-1.0-SNAPSHOT.jar ${RUN_HOME}/${MODULE}-1.0-SNAPSHOT.jar
nohup java -jar ${RUN_HOME}/${MODULE}-1.0-SNAPSHOT.jar &
