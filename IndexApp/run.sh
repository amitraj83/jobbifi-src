#!/bin/bash
sudo kill -15 `ps -ef | grep indexapp.jar | awk '{print $2}' `
mvn -U clean package
cd target
java -Xdebug -Xrunjdwp:transport=dt_socket,address=8010,server=y,suspend=n  -cp indexapp.jar com.interview.index.BootStrapper ../src/main/resources/preproduction.properties &
