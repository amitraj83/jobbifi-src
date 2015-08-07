#!/bin/bash
sudo kill -15 `ps -ef | grep BackendServer.jar | awk '{print $2}' `
cd target/
sudo java -cp BackendServer.jar  -Xdebug -Xrunjdwp:transport=dt_socket,address=8002,server=y,suspend=n com.interview.search.BootStrapper &


