module.exports=function(e){"use strict";e.initConfig({qunit:{files:["test/index.html"]},lint:{files:["grunt.js","jquery.cookie.js"]},jshint:{options:{boss:!0,browser:!0,curly:!0,eqeqeq:!0,eqnull:!0,expr:!0,evil:!0,newcap:!0,noarg:!0,undef:!0},globals:{jQuery:!0}}}),e.registerTask("default","lint qunit"),e.registerTask("ci","default")};