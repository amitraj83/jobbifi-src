#!/bin/bash
mvn -U -DskipTests=true clean package
mvn install:install-file -Dfile="./target/framework-1.0-SNAPSHOT.jar" -DgroupId=com.interview.framework -DartifactId=framework -Dversion=1.0-SNAPSHOT -Dpackaging=jar
