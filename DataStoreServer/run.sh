#!/bin/bash
cd target
sudo java -cp DataStoreServer.jar -Xdebug -Xrunjdwp:transport=dt_socket,address=8007,server=y,suspend=n  com.interview.search.BootStrapper &

