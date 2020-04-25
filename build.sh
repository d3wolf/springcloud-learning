#!/bin/bash

RUN_HOME=/home/springcloud

MODULE=Eureka-Server

# eureka server
cd ${WORKSPACE}/${MODULE}
mvn clean package -DskipTests
cp target/${MODULE}-0.0.1-SNAPSHOT.jar ${RUN_HOME}/${MODULE}-0.0.1-SNAPSHOT.jar
java -jar ${RUN_HOME}/${MODULE}-0.0.1-SNAPSHOT.jar --Dspring.profiles.active=single

# provider
MODULE=User-Provider
cd ${WORKSPACE}/${MODULE}
mvn clean package -DskipTests
cp target/${MODULE}-0.0.1-SNAPSHOT.jar ${RUN_HOME}/${MODULE}-0.0.1-SNAPSHOT.jar
java -jar ${RUN_HOME}/${MODULE}-0.0.1-SNAPSHOT.jar --Dspring.profiles.active=provider1
java -jar ${RUN_HOME}/${MODULE}-0.0.1-SNAPSHOT.jar --Dspring.profiles.active=provider2

# consumer
MODULE=User-Consumer
cd ${WORKSPACE}/${MODULE}
mvn clean package -DskipTests
cp target/${MODULE}-0.0.1-SNAPSHOT.jar ${RUN_HOME}/${MODULE}-0.0.1-SNAPSHOT.jar
java -jar ${RUN_HOME}/${MODULE}-0.0.1-SNAPSHOT.jar --Dspring.profiles.active=consumer1
java -jar ${RUN_HOME}/${MODULE}-0.0.1-SNAPSHOT.jar --Dspring.profiles.active=consumer2

# admin
MODULE=Admin-Test
cd ${WORKSPACE}/${MODULE}
mvn clean package -DskipTests
cp target/${MODULE}-0.0.1-SNAPSHOT.jar ${RUN_HOME}/${MODULE}-0.0.1-SNAPSHOT.jar
java -jar ${RUN_HOME}/${MODULE}-0.0.1-SNAPSHOT.jar

# gateway
MODULE=User-Gateway
cd ${WORKSPACE}/${MODULE}
mvn clean package -DskipTests
cp target/${MODULE}-0.0.1-SNAPSHOT.jar ${RUN_HOME}/${MODULE}-0.0.1-SNAPSHOT.jar
java -jar ${RUN_HOME}/${MODULE}-0.0.1-SNAPSHOT.jar
