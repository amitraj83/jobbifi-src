#!/bin/bash
mvn clean
mvn -U -DskipTests=true package
mkdir logs
cd target/
sudo java -cp BackendServer.jar -Xdebug -Xrunjdwp:transport=dt_socket,address=8002,server=y,suspend=n com.interview.search.BootStrapper  &
