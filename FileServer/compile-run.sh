#!/bin/bash
sudo kill -15 `ps -ef | grep FileServer.jar | awk '{print $2}' `
mvn clean
mvn -U -Dplocation=local.properties package
mkdir logs
cd target/
sudo java -Dplocation=local.properties -cp FileServer.jar -Xdebug -Xrunjdwp:transport=dt_socket,address=8004,server=y,suspend=n com.interview.main.BootStrapper  &
