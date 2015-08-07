#!/bin/bash

mvn clean
mvn -U -DskipTests=true package
sudo rm -rf /var/lib/tomcat7/webapps/interviewbackend/WEB-INF
sudo cp -r target/interviewbackend/WEB-INF/ /var/lib/tomcat7/webapps/interviewbackend/WEB-INF
sudo rm -rf /var/lib/tomcat7/webapps/interviewbackend/resources
sudo cp -r target/interviewbackend/resources /var/lib/tomcat7/webapps/interviewbackend/resources
sudo rm -f /var/lib/tomcat7/logs/*
sudo service tomcat7 restart
