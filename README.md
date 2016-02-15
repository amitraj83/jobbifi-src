JOBBIFI PROJECTS
----------------

Directories

* BackendServer: Business logic
* DataStoreServer: Database connector
* FileServer: Manages uploaded files
* Framework: Classes and methods common to all projects
* IndexApp: Indexes data triggered by database operations
* LinkedinLibrary: Customised LinkedIn API connector
* MockInterview: Creates classes needed for web server
* Scripts: (Old) scripts to run all above projects (now superseded by deployment manager)
* WebApp: JSP, HTML, CSS, images, etc. needed by Tomcat server

Run MongoServer
sudo /usr/bin/mongod --config /etc/mongod.conf
Also, create indexes and set text search enabled

To create indexes:
db.interviewer.ensureIndex({"companies":"text", "country":"text","cv":"text","skills":"text"})

To enable text search:
mongo
use admin
db.runCommand( { setParameter: 1, textSearchEnabled: true } );

