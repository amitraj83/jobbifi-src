#!/bin/bash

mvn install:install-file -Dfile="./linkedin-j-core-1.0.417-SNAPSHOT.jar" -DgroupId=com.googlecode.linkedin-j -DartifactId=linkedin-j-core -Dversion=1.0.417-SNAPSHOT -Dpackaging=jar
