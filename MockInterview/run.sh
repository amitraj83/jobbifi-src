#!/bin/bash
mvn clean
mvn -DskipTests=true assembly:assembly
sudo rm -rf /var/lib/tomcat7/webapps/interviewbackend/WEB-INF
sudo cp -r target/interviewbackend/WEB-INF/ /var/lib/tomcat7/webapps/interviewbackend/WEB-INF
sudo rm -f /var/lib/tomcat7/logs/*
sudo service tomcat7 restart
