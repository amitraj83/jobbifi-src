#!/bin/bash
sudo kill -15 `ps -ef | grep SearchServer.jar | awk '{print $2}' `
mvn clean
mvn -U -DskipTests=true package
mkdir logs
cd target
sudo java -cp SearchServer.jar -Xdebug -Xrunjdwp:transport=dt_socket,address=8003,server=y,suspend=n com.interview.search.BootStrapper ../conf/local.properties &
