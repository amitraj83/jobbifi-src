function prettyDate(e){var t;t=e instanceof Date?e:new Date((e||"").replace(/-/g,"/").replace(/[TZ]/g," "));var a=new Date,n=new Date(a.getFullYear(),a.getMonth(),a.getDate());if(t.getFullYear()!=n.getFullYear())return t.getMonth()+"/"+t.getDate()+"/"+t.getFullYear().toString().slice(2);if(t>=n){var r="AM",i=t.getHours();i>11&&(r="PM"),i>12&&(i-=12),0==i&&(i=12);var m=t.getMinutes();return 10>m&&(m="0"+m),10>i&&(i="0"+i),i+":"+m+" "+r}if(864e5>n-t)return"Yesterday";var s=dateFormat;return s.i18n.monthNames[t.getMonth()]+" "+t.getDate()}var dateFormat=function(){var e=/d{1,4}|m{1,4}|yy(?:yy)?|([HhMsTt])\1?|[LloSZ]|"[^"]*"|'[^']*'/g,t=/\b(?:[PMCEA][SDP]T|(?:Pacific|Mountain|Central|Eastern|Atlantic) (?:Standard|Daylight|Prevailing) Time|(?:GMT|UTC)(?:[-+]\d{4})?)\b/g,a=/[^-+\dA-Z]/g,n=function(e,t){for(e=String(e),t=t||2;e.length<t;)e="0"+e;return e};return function(r,i,m){var s=dateFormat;if(1==arguments.length&&("string"==typeof r||r instanceof String)&&!/\d/.test(r)&&(i=r,r=void 0),r=r?new Date(r):new Date,isNaN(r))throw new SyntaxError("invalid date");i=String(s.masks[i]||i||s.masks["default"]),"UTC:"==i.slice(0,4)&&(i=i.slice(4),m=!0);var d=m?"getUTC":"get",o=r[d+"Date"](),y=r[d+"Day"](),u=r[d+"Month"](),g=r[d+"FullYear"](),l=r[d+"Hours"](),M=r[d+"Minutes"](),h=r[d+"Seconds"](),c=r[d+"Milliseconds"](),T=m?0:r.getTimezoneOffset(),D={d:o,dd:n(o),ddd:s.i18n.dayNames[y],dddd:s.i18n.dayNames[y+7],m:u+1,mm:n(u+1),mmm:s.i18n.monthNames[u],mmmm:s.i18n.monthNames[u+12],yy:String(g).slice(2),yyyy:g,h:l%12||12,hh:n(l%12||12),H:l,HH:n(l),M:M,MM:n(M),s:h,ss:n(h),l:n(c,3),L:n(c>99?Math.round(c/10):c),t:12>l?"a":"p",tt:12>l?"am":"pm",T:12>l?"A":"P",TT:12>l?"AM":"PM",Z:m?"UTC":(String(r).match(t)||[""]).pop().replace(a,""),o:(T>0?"-":"+")+n(100*Math.floor(Math.abs(T)/60)+Math.abs(T)%60,4),S:["th","st","nd","rd"][o%10>3?0:(o%100-o%10!=10)*o%10]};return i.replace(e,function(e){return e in D?D[e]:e.slice(1,e.length-1)})}}();dateFormat.masks={"default":"ddd mmm dd yyyy HH:MM:ss",shortDate:"m/d/yy",mediumDate:"mmm d, yyyy",longDate:"mmmm d, yyyy",fullDate:"dddd, mmmm d, yyyy",shortTime:"h:MM TT",mediumTime:"h:MM:ss TT",longTime:"h:MM:ss TT Z",isoDate:"yyyy-mm-dd",isoTime:"HH:MM:ss",isoDateTime:"yyyy-mm-dd'T'HH:MM:ss",isoUtcDateTime:"UTC:yyyy-mm-dd'T'HH:MM:ss'Z'"},dateFormat.i18n={dayNames:["Sun","Mon","Tue","Wed","Thu","Fri","Sat","Sunday","Monday","Tuesday","Wednesday","Thursday","Friday","Saturday"],monthNames:["Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec","January","February","March","April","May","June","July","August","September","October","November","December"]},Date.prototype.format=function(e,t){return dateFormat(this,e,t)},"undefined"!=typeof jQuery&&(jQuery.fn.prettyDate=function(){return this.each(function(){var e=prettyDate(this.title);e&&jQuery(this).text(e)})});var postedOnDateFormat=function(e){e.getMonth(),e.getDate(),e.getHours(),e.getMinutes(),e.getSeconds()},myDateFormat=function(e){var t=e.getMonth(),a=e.getDate(),n=e.getHours(),r=e.getMinutes(),i=e.getSeconds(),m=e.getFullYear()+"/"+((""+t).length<2?"0":"")+t+"/"+((""+a).length<2?"0":"")+a+" "+((""+n).length<2?"0":"")+n+":"+((""+r).length<2?"0":"")+r+":"+((""+i).length<2?"0":"")+i;return m};