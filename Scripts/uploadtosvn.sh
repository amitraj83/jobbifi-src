#svn pushkar
#ksdh^%&*#987

cd /home/sayandeep/Code/mockinterview/compiled-libraries
svn update

cd /home/sayandeep/Code/mockinterview/interview-code/BackendServer/target
cp BackendServer.jar /home/sayandeep/Code/mockinterview/compiled-libraries/Backend/

cd /home/sayandeep/Code/mockinterview/interview-code/DataStoreServer/target
cp DataStoreServer.jar /home/sayandeep/Code/mockinterview/compiled-libraries/Datastore

cd /home/sayandeep/Code/mockinterview/interview-code/FileServer/target 
cp FileServer.jar /home/sayandeep/Code/mockinterview/compiled-libraries/Fileserver

cd /home/sayandeep/Code/mockinterview/interview-code/SearchServer/target
cp SearchServer-jar-with-dependencies.jar /home/sayandeep/Code/mockinterview/compiled-libraries/SearchServer

cd /home/sayandeep/Code/mockinterview/compiled-libraries
# svn checkout svn://162.243.74.91/mockinterview/compiled-libraries/interviewbackend
svn update
svn delete --force *
cd /home/sayandeep/Code/jobbifi-webapp/
cp -r * /home/sayandeep/Code/mockinterview/compiled-libraries/interviewbackend
sv add *

cd /home/sayandeep/Code/mockinterview/compiled-libraries
svn commit -m "Committing a new version of compiled-libraries"
