#!/bin/bash
cd target/
sudo java -Dplocation=local.properties -cp FileServer.jar -Xdebug -Xrunjdwp:transport=dt_socket,address=8004,server=y,suspend=n com.interview.main.BootStrapper  &
