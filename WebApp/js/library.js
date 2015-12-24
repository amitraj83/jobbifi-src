function getBigStarView(rating) {
    var fontsize = "2em";
    if (Number(rating) > 5)
        rating = rating / 2;

    if (rating == 0) {
        return '<i class="fa fa-star-o" style="color: #f0ad4e;text-shadow: 1px 1px 1px #ccc;font-size: ' + fontsize + ';"></i><i class="fa fa-star-o" style="color: #f0ad4e;text-shadow: 1px 1px 1px #ccc;font-size: ' + fontsize + ';"></i><i class="fa fa-star-o" style="color: #f0ad4e;text-shadow: 1px 1px 1px #ccc;font-size: ' + fontsize + ';"></i><i class="fa fa-star-o" style="color: #f0ad4e;text-shadow: 1px 1px 1px #ccc;font-size: ' + fontsize + ';"></i><i class="fa fa-star-o" style="color: #f0ad4e;text-shadow: 1px 1px 1px #ccc;font-size: ' + fontsize + ';"></i>';
    }
    else if (0 < rating && rating < 1) {
        return '<i class="fa fa-star-half-o" style="color: #f0ad4e;text-shadow: 1px 1px 1px #ccc;font-size: ' + fontsize + ';"></i><i class="fa fa-star-o" style="color: #f0ad4e;text-shadow: 1px 1px 1px #ccc;font-size: ' + fontsize + ';"></i><i class="fa fa-star-o" style="color: #f0ad4e;text-shadow: 1px 1px 1px #ccc;font-size: ' + fontsize + ';"></i><i class="fa fa-star-o" style="color: #f0ad4e;text-shadow: 1px 1px 1px #ccc;font-size: ' + fontsize + ';"></i><i class="fa fa-star-o" style="color: #f0ad4e;text-shadow: 1px 1px 1px #ccc;font-size: ' + fontsize + ';"></i>';
    }
    else if (rating == 1) {
        return '<i class="fa fa-star" style="color: #f0ad4e;text-shadow: 1px 1px 1px #ccc;font-size: ' + fontsize + ';"></i><i class="fa fa-star-o" style="color: #f0ad4e;text-shadow: 1px 1px 1px #ccc;font-size: ' + fontsize + ';"></i><i class="fa fa-star-o" style="color: #f0ad4e;text-shadow: 1px 1px 1px #ccc;font-size: ' + fontsize + ';"></i><i class="fa fa-star-o" style="color: #f0ad4e;text-shadow: 1px 1px 1px #ccc;font-size: ' + fontsize + ';"></i><i class="fa fa-star-o" style="color: #f0ad4e;text-shadow: 1px 1px 1px #ccc;font-size: ' + fontsize + ';"></i>';
    }
    else if (1 < rating && rating < 2) {
        return '<i class="fa fa-star" style="color: #f0ad4e;text-shadow: 1px 1px 1px #ccc;font-size: ' + fontsize + ';"></i><i class="fa fa-star-half-o" style="color: #f0ad4e;text-shadow: 1px 1px 1px #ccc;font-size: ' + fontsize + ';"></i><i class="fa fa-star-o" style="color: #f0ad4e;text-shadow: 1px 1px 1px #ccc;font-size: ' + fontsize + ';"></i><i class="fa fa-star-o" style="color: #f0ad4e;text-shadow: 1px 1px 1px #ccc;font-size: ' + fontsize + ';"></i><i class="fa fa-star-o" style="color: #f0ad4e;text-shadow: 1px 1px 1px #ccc;font-size: ' + fontsize + ';"></i>';
    }
    else if (rating == 2) {
        return '<i class="fa fa-star" style="color: #f0ad4e;text-shadow: 1px 1px 1px #ccc;font-size: ' + fontsize + ';"></i><i class="fa fa-star" style="color: #f0ad4e;text-shadow: 1px 1px 1px #ccc;font-size: ' + fontsize + ';"></i><i class="fa fa-star-o" style="color: #f0ad4e;text-shadow: 1px 1px 1px #ccc;font-size: ' + fontsize + ';"></i><i class="fa fa-star-o" style="color: #f0ad4e;text-shadow: 1px 1px 1px #ccc;font-size: ' + fontsize + ';"></i><i class="fa fa-star-o" style="color: #f0ad4e;text-shadow: 1px 1px 1px #ccc;font-size: ' + fontsize + ';"></i>';
    }
    else if (2 < rating && rating < 3) {
        return '<i class="fa fa-star" style="color: #f0ad4e;text-shadow: 1px 1px 1px #ccc;font-size: ' + fontsize + ';"></i><i class="fa fa-star" style="color: #f0ad4e;text-shadow: 1px 1px 1px #ccc;font-size: ' + fontsize + ';"></i><i class="fa fa-star-half-o" style="color: #f0ad4e;text-shadow: 1px 1px 1px #ccc;font-size: ' + fontsize + ';"></i><i class="fa fa-star-o" style="color: #f0ad4e;text-shadow: 1px 1px 1px #ccc;font-size: ' + fontsize + ';"></i><i class="fa fa-star-o" style="color: #f0ad4e;text-shadow: 1px 1px 1px #ccc;font-size: ' + fontsize + ';"></i>';
    }
    else if (rating == 3) {
        return '<i class="fa fa-star" style="color: #f0ad4e;text-shadow: 1px 1px 1px #ccc;font-size: ' + fontsize + ';"></i><i class="fa fa-star" style="color: #f0ad4e;text-shadow: 1px 1px 1px #ccc;font-size: ' + fontsize + ';"></i><i class="fa fa-star" style="color: #f0ad4e;text-shadow: 1px 1px 1px #ccc;font-size: ' + fontsize + ';"></i><i class="fa fa-star-o" style="color: #f0ad4e;text-shadow: 1px 1px 1px #ccc;font-size: ' + fontsize + ';"></i><i class="fa fa-star-o" style="color: #f0ad4e;text-shadow: 1px 1px 1px #ccc;font-size: ' + fontsize + ';"></i>';
    }
    else if (3 < rating && rating < 4) {
        return '<i class="fa fa-star" style="color: #f0ad4e;text-shadow: 1px 1px 1px #ccc;font-size: ' + fontsize + ';"></i><i class="fa fa-star" style="color: #f0ad4e;text-shadow: 1px 1px 1px #ccc;font-size: ' + fontsize + ';"></i><i class="fa fa-star" style="color: #f0ad4e;text-shadow: 1px 1px 1px #ccc;font-size: ' + fontsize + ';"></i><i class="fa fa-star-half-o" style="color: #f0ad4e;text-shadow: 1px 1px 1px #ccc;font-size: ' + fontsize + ';"></i><i class="fa fa-star-o" style="color: #f0ad4e;text-shadow: 1px 1px 1px #ccc;font-size: ' + fontsize + ';"></i>';
    }
    else if (rating == 4) {
        return '<i class="fa fa-star" style="color: #f0ad4e;text-shadow: 1px 1px 1px #ccc;font-size: ' + fontsize + ';"></i><i class="fa fa-star" style="color: #f0ad4e;text-shadow: 1px 1px 1px #ccc;font-size: ' + fontsize + ';"></i><i class="fa fa-star" style="color: #f0ad4e;text-shadow: 1px 1px 1px #ccc;font-size: ' + fontsize + ';"></i><i class="fa fa-star" style="color: #f0ad4e;text-shadow: 1px 1px 1px #ccc;font-size: ' + fontsize + ';"></i><i class="fa fa-star-o" style="color: #f0ad4e;text-shadow: 1px 1px 1px #ccc;font-size: ' + fontsize + ';"></i>';
    }
    else if (4 < rating && rating < 5) {
        return '<i class="fa fa-star" style="color: #f0ad4e;text-shadow: 1px 1px 1px #ccc;font-size: ' + fontsize + ';"></i><i class="fa fa-star" style="color: #f0ad4e;text-shadow: 1px 1px 1px #ccc;font-size: ' + fontsize + ';"></i><i class="fa fa-star" style="color: #f0ad4e;text-shadow: 1px 1px 1px #ccc;font-size: ' + fontsize + ';"></i><i class="fa fa-star" style="color: #f0ad4e;text-shadow: 1px 1px 1px #ccc;font-size: ' + fontsize + ';"></i><i class="fa fa-star-half-o" style="color: #f0ad4e;text-shadow: 1px 1px 1px #ccc;font-size: ' + fontsize + ';"></i>';
    }
    else if (rating == 5) {
        return '<i class="fa fa-star" style="color: #f0ad4e;text-shadow: 1px 1px 1px #ccc;font-size: ' + fontsize + ';"></i><i class="fa fa-star" style="color: #f0ad4e;text-shadow: 1px 1px 1px #ccc;font-size: ' + fontsize + ';"></i><i class="fa fa-star" style="color: #f0ad4e;text-shadow: 1px 1px 1px #ccc;font-size: ' + fontsize + ';"></i><i class="fa fa-star" style="color: #f0ad4e;text-shadow: 1px 1px 1px #ccc;font-size: ' + fontsize + ';"></i><i class="fa fa-star" style="color: #f0ad4e;text-shadow: 1px 1px 1px #ccc;font-size: ' + fontsize + ';"></i>';
    }
}

function getStarView(rating) {

    if (Number(rating) > 5)
        rating = rating / 2;

    if (rating == 0) {
        return '<i class="fa fa-star-o" style="color: #f0ad4e;text-shadow: 1px 1px 1px #ccc;"></i><i class="fa fa-star-o" style="color: #f0ad4e;text-shadow: 1px 1px 1px #ccc;"></i><i class="fa fa-star-o" style="color: #f0ad4e;text-shadow: 1px 1px 1px #ccc;"></i><i class="fa fa-star-o" style="color: #f0ad4e;text-shadow: 1px 1px 1px #ccc;"></i><i class="fa fa-star-o" style="color: #f0ad4e;text-shadow: 1px 1px 1px #ccc;"></i>';
    }
    else if (rating == 1) {
        return '<i class="fa fa-star" style="color: #f0ad4e;text-shadow: 1px 1px 1px #ccc;"></i><i class="fa fa-star-o" style="color: #f0ad4e;text-shadow: 1px 1px 1px #ccc;"></i><i class="fa fa-star-o" style="color: #f0ad4e;text-shadow: 1px 1px 1px #ccc;"></i><i class="fa fa-star-o" style="color: #f0ad4e;text-shadow: 1px 1px 1px #ccc;"></i><i class="fa fa-star-o" style="color: #f0ad4e;text-shadow: 1px 1px 1px #ccc;"></i>';
    }
    else if (rating == 2) {
        return '<i class="fa fa-star" style="color: #f0ad4e;text-shadow: 1px 1px 1px #ccc;"></i><i class="fa fa-star" style="color: #f0ad4e;text-shadow: 1px 1px 1px #ccc;"></i><i class="fa fa-star-o" style="color: #f0ad4e;text-shadow: 1px 1px 1px #ccc;"></i><i class="fa fa-star-o" style="color: #f0ad4e;text-shadow: 1px 1px 1px #ccc;"></i><i class="fa fa-star-o" style="color: #f0ad4e;text-shadow: 1px 1px 1px #ccc;"></i>';
    }
    else if (rating == 3) {
        return '<i class="fa fa-star" style="color: #f0ad4e;text-shadow: 1px 1px 1px #ccc;"></i><i class="fa fa-star" style="color: #f0ad4e;text-shadow: 1px 1px 1px #ccc;"></i><i class="fa fa-star" style="color: #f0ad4e;text-shadow: 1px 1px 1px #ccc;"></i><i class="fa fa-star-o" style="color: #f0ad4e;text-shadow: 1px 1px 1px #ccc;"></i><i class="fa fa-star-o" style="color: #f0ad4e;text-shadow: 1px 1px 1px #ccc;"></i>';
    }
    else if (rating == 4) {
        return '<i class="fa fa-star" style="color: #f0ad4e;text-shadow: 1px 1px 1px #ccc;" style="color: #f0ad4e;text-shadow: 1px 1px 1px #ccc;"></i><i class="fa fa-star" style="color: #f0ad4e;text-shadow: 1px 1px 1px #ccc;"></i><i class="fa fa-star" style="color: #f0ad4e;text-shadow: 1px 1px 1px #ccc;"></i><i class="fa fa-star" style="color: #f0ad4e;text-shadow: 1px 1px 1px #ccc;"></i><i class="fa fa-star-o" style="color: #f0ad4e;text-shadow: 1px 1px 1px #ccc;"></i>';
    }
    else if (rating == 5) {
        return '<i class="fa fa-star" style="color: #f0ad4e;text-shadow: 1px 1px 1px #ccc;"></i><i class="fa fa-star" style="color: #f0ad4e;text-shadow: 1px 1px 1px #ccc;"></i><i class="fa fa-star" style="color: #f0ad4e;text-shadow: 1px 1px 1px #ccc;"></i><i class="fa fa-star" style="color: #f0ad4e;text-shadow: 1px 1px 1px #ccc;"></i><i class="fa fa-star" style="color: #f0ad4e;text-shadow: 1px 1px 1px #ccc;"></i>';
    }
}


function getInterviewStatusLiteral(status) {
    if (status == 0) {
        return "OPEN";
    }
    else if (status == 1) {
        return "IN PROGRESS";
    }
    else if (status == 2) {
        return "IN PROGRESS";
    }
    else if (status == 3) {
        return "IN PROGRESS";
    }
    else if (status == 4) {
        return "APPROVAL PENDING";
    }
    else if (status == 5) {
        return "COMPLETED";
    }
    else if (status == 6) {
        return "CANCELLED";
    }
    else if (status == 7) {
        return "REPOSTED";
    }
    else if (status == 8) {
        return "USER RATED";
    }
    else if (status == 9) {
        return "DISPUTE";
    }
}

/*****************************************************/

jQuery.loadScript = function (url, callback) {
    jQuery.ajax({
        url: url,
        dataType: 'script',
        success: callback,
        async: true
    });
}

function downloadCSV(strData, strFileName, strMimeType) {
    var D = document,
        a = D.createElement("a");
    strMimeType = strMimeType || "application/octet-stream";

    if (window.MSBlobBuilder) { //IE10+ routine
        var bb = new MSBlobBuilder();
        bb.append(strData);
        return navigator.msSaveBlob(bb, strFileName);
    }
    /* end if(window.MSBlobBuilder) */

    if ('download' in a) { //html5 A[download]
        a.href = "data:" + strMimeType + "," + encodeURIComponent(strData);
        a.setAttribute("download", strFileName);
        a.innerHTML = "downloading...";
        D.body.appendChild(a);
        setTimeout(function () {
            a.click();
            D.body.removeChild(a);
        }, 66);
        return true;
    }
    /* end if('download' in a) */

    //do iframe dataURL download (old ch+FF):
    var f = D.createElement("iframe");
    D.body.appendChild(f);
    f.src = "data:" + strMimeType + "," + encodeURIComponent(strData);

    setTimeout(function () {
        D.body.removeChild(f);
    }, 333);
    return true;
}
/* end download() */


var getBidStatusLiteral = function ($status) {
    if ($status == "0")
        return "PENDING";
    if ($status == "1")
        return "ACCEPTED";
    if ($status == "2")
        return "REJECTED";
};


var Education = function (id, degree, fos, school, sy, ey) {
    this.id = id;
    this.degree = degree;
    this.fos = fos;
    this.school = school;
    this.sy = sy;
    this.ey = ey;
};

$.extend(Education.prototype, {
    getID: function () {
        return this.id;
    },
    getDegree: function () {
        return this.degree;
    },
    getFieldOfStudy: function () {
        return this.fos;
    },
    getSchool: function () {
        return this.school;
    },
    getStartYear: function () {
        return this.sy;
    },
    getEndYear: function () {
        return this.ey;
    }
});


var Review = function (ititle, message, ratedby, average, dt) {
    this.ititle = ititle;
    this.message = message;
    this.ratedby = ratedby;
    this.average = average;
    this.dt = dt;
};

$.extend(Review.prototype, {
    getITitle: function () {
        return this.ititle;
    },
    getMessage: function () {
        return this.message;
    },
    getRatedBy: function () {
        return this.ratedby;
    },
    getAverage: function () {
        return this.average;
    },
    getDateTime: function () {
        return this.dt;
    }
});


var Position = function (id, companyName, description, title, startyear, endyear) {
    this.id = id;
    this.companyName = companyName;
    this.description = description;
    this.title = title;
    this.startyear = startyear;
    this.endyear = endyear;
};
$.extend(Position.prototype, {

    getId: function () {
        return this.id;
    },
    setId: function ($id) {
        return this.id = $id;
    },
    getCompanyName: function () {
        return this.companyName;
    },
    setCompanyName: function ($cn) {
        return this.companyName = $cn;
    },
    getDescription: function () {
        return this.description;
    },
    setDescription: function ($desc) {
        return this.description = $desc;
    },
    getTitle: function () {
        return this.title;
    },
    setTitle: function ($title) {
        return this.title = $title;
    },
    getStartYear: function () {
        return this.startyear;
    },
    setStartYear: function ($sy) {
        return this.startyear = $sy;
    },
    getEndYear: function () {
        return this.endyear;
    },
    setEndYear: function ($ey) {
        return this.endyear = $ey;
    }
});

var allCalendarEvents = new Map;

var CalendarEvents = function (_id, userid, title, starttime, endtime, eventtype, isrecursive, recurdays, time) {
    this._id = _id;
    this.userid = userid;
    this.title = title;
    this.starttime = starttime;
    this.endtime = endtime;
    this.eventtype = eventtype;
    this.isrecursive = isrecursive;
    this.recurdays = recurdays;
    this.time = time;
    allCalendarEvents.put(_id, this);
};

$.extend(CalendarEvents.prototype, {

    getId: function () {
        return this._id;
    },
    getUserId: function () {
        return this.userid;
    },
    getTitle: function () {
        return this.title;
    },
    getStart: function () {
        return this.starttime;
    },
    setStart: function ($start) {
        this.starttime = $start;
    },
    getEnd: function () {
        return this.endtime;
    },
    setEnd: function ($endtime) {
        this.endtime = $endtime;
    },
    getEventType: function () {
        return this.eventtype;
    },
    setEventType: function ($eventtype) {
        this.eventtype = $eventtype;
    },
    getIsRecursive: function () {
        return this.isrecursive;
    },
    setIsRecursive: function ($isrecursive) {
        this.isrecursive = $isrecursive;
    },
    getRecurDays: function () {
        return this.recurdays;
    },
    setRecurDays: function ($recurdays) {
        this.recurdays = $recurdays;
    },
    getTime: function () {
        return this.time;
    },
    setTime: function ($time) {
        this.time = $time;
    }

});

var Dispute = function (_id, vid, ititle, disputewith, amount, result, closetime, messages, createdby) {
    this._id = _id;
    this.vid = vid;
    this.ititle = ititle;
    this.disputewith = disputewith;
    this.amount = amount;
    this.result = result;
    this.closetime = closetime;
    this.messages = messages;
    this.createdby = createdby;
};

$.extend(Dispute.prototype, {

    getId: function () {
        return this._id;
    },
    setId: function ($id) {
        this.time = $id;
    },
    getVID: function () {
        return this.vid;
    },
    setVID: function ($vid) {
        this.vid = $vid;
    },
    getITitle: function () {
        return this.ititle;
    },
    setITitle: function ($ititle) {
        this.ititle = $ititle;
    },
    getWith: function () {
        return this.disputewith;
    },
    setWith: function ($disputewith) {
        this.disputewith = $disputewith;
    },
    getAmount: function () {
        return this.amount;
    },
    setAmount: function ($amount) {
        this.amount = $amount;
    },
    getResult: function () {
        return this.result;
    },
    setResult: function ($result) {
        this.result = $result;
    },
    getCloseTime: function () {
        return this.closetime;
    },
    setCloseTime: function ($closetime) {
        this.closetime = $closetime;
    },
    getMessages: function () {
        return this.messages;
    },
    setMessages: function ($messages) {
        this.messages = $messages;
    },
    getCreatedby: function () {
        return this.createdby;
    },
    setCreatedby: function ($createdby) {
        this.createdby = $createdby;
    },

});

var Bid = function (_id, bidder, iid, msg, price, status, dt, reputation, bidderreviewcount) {
    this._id = _id;
    this.bidder = bidder;
    this.iid = iid;
    this.msg = msg;
    this.price = price;
    this.status = status;
    this.dt = dt;
    this.reputation = reputation;
    this.attachment = "";
    this.attachmentName = "";
    this.bidderreviewcount = bidderreviewcount;
};

$.extend(Bid.prototype, {

    getId: function () {
        return this._id;
    },
    getBidder: function () {
        return this.bidder;
    },
    getIid: function () {
        return this.iid;
    },
    getMessage: function () {
        return this.msg;
    },
    getPrice: function () {
        return this.price;
    },
    getStatus: function () {
        return this.status;
    },
    getDate: function () {
        return this.dt;
    },
    getReputation: function () {
        return this.reputation;
    },
    setAttachment: function ($attachment) {
        this.attachment = $attachment;
    },
    getAttachment: function () {
        return this.attachment;
    },
    setAttachmentName: function ($attachmentName) {
        this.attachmentName = $attachmentName;
    },
    getAttachmentName: function () {
        return this.attachmentName;
    },
    getBidderReviewCount: function () {
        return this.bidderreviewcount;
    }

});


var Interview = function (_id, title, desc, interviewee, interviewer, skills, status, eb, dt, iAttachmentName, attachmentURL) {
    this._id = _id;
    this.title = title;
    this.desc = desc;
    this.interviewee = interviewee;
    this.interviewer = interviewer;
    this.skills = skills;
    this.status = status;
    this.eb = eb;
    this.dt = dt;
    this.interviewerRatedForInterviewee = false;
    this.intervieweeRatedForInterviewer = false;
    this.iAttachmentName = iAttachmentName;
    this.attachmentURL = attachmentURL;
};

$.extend(Interview.prototype, {

    getId: function () {
        return this._id;
    },
    getTitle: function () {
        return this.title;
    },
    getDescription: function () {
        return this.desc;
    },
    getInterviewee: function () {
        return this.interviewee;
    },
    getInterviewer: function () {
        return this.interviewer;
    },
    setInterviewer: function ($awardedinterviewer) {
        this.interviewer = $awardedinterviewer;
    },

    getSkills: function () {
        return this.skills;
    },
    getStatus: function () {
        return this.status;
    },
    getEB: function () {
        return this.eb;
    },
    setEB: function ($eb) {
        this.eb = $eb;
    },
    getDate: function () {
        return this.dt;
    },
    getAttachmentName: function () {
        return this.iAttachmentName;
    },
    getAttachmentURL: function () {
        return this.attachmentURL;
    },

    getInterviewerRatedForInterviewee: function () {
        return this.interviewerRatedForInterviewee;
    },
    getIntervieweeRatedForInterviewer: function () {
        return this.intervieweeRatedForInterviewer;
    },
    setInterviewerRatedForInterviewee: function ($val) {
        this.interviewerRatedForInterviewee = $val;
    },
    setIntervieweeRatedForInterviewer: function ($val) {
        this.intervieweeRatedForInterviewer = $val;
    },

    getBidCount: function () {
        var count = 0;

        for (var i = 0; i++ < allBidsReceived.size; allBidsReceived.next()) {
            var bid = allBidsReceived.value();
            if (bid.iid == this._id) {
                count++;
            }
        }

        return count;
    },
    setStatus: function ($status) {
        this.status = $status;
    },

    hasInterviewRatedBy: function ($user) {
        if ($user == this.interviewee) {
            //checking if interviewee has rated interviewer
            return this.intervieweeRatedForInterviewer;
        }
        else {
            //checking if interviewer has rated intervieweee
            return this.interviewerRatedForInterviewee;
        }
    }


});


//, reviews, educations, positions
var User = function (un, sk, cmp, cnt, bal, rt, cv, ic, ip, reputation, type, profile_pic, reviewCount, chatpass, reviews, educations, positions, individualratingscounts, skilllist) {
    this.un = un;
    this.sk = sk;
    this.cmp = cmp;
    this.cnt = cnt;
    this.bal = bal;
    this.rt = rt;
    this.cv = cv;
    this.ic = ic;
    this.ip = ip;
    this.reputation = reputation;
    this.type = type;
    this.profile_pic = profile_pic;
    this.reviewCount = reviewCount;
    this.chatpass = chatpass;
    this.reviews = reviews;
    this.educations = educations;
    this.positions = positions;
    this.individualratingscounts = individualratingscounts;
    this.skilllist = skilllist;
};

$.extend(User.prototype, {
    getUserName: function () {
        return this.un;
    },
    getSkills: function () {
        var skArray = new Array();
        var skillsJSON = jQuery.parseJSON(this.sk);
        $.each(skillsJSON, function (id, skill) {
            skArray.push(skill);
        });
        return skArray;
    },
    setSkills: function ($newskills) {
        this.sk = JSON.stringify($newskills);
    },
    getCompanies: function () {
        var companies = new Array();
        var companiesJSON = jQuery.parseJSON(this.cmp);
        $.each(companiesJSON, function (id, comp) {
            companies.push(comp);
        });
        return companies;
    },
    setCompanies: function ($newcompanies) {
        this.cmp = JSON.stringify($newcompanies);
    },
    getCountry: function () {
        return this.cnt;
    },
    setCountry: function ($newcont) {
        this.cnt = $newcont;
    },
    getBalance: function () {
        return this.bal;
    },
    setBalance: function ($bal) {
        this.bal = $bal;
    },
    getRate: function () {
        return this.rt;
    },
    setRate: function ($newrate) {
        this.rt = $newrate;
    },
    getCV: function () {
        return this.cv;
    },
    setCV: function ($newcv) {
        this.cv = $newcv;
    },
    setChatPass: function ($chatpass) {
        alert("ChatPass:" + $chatpass);
        this.chatpass = $chatpass;
    },
    getNumberOfInterviewCompleted: function () {
        return this.ic;
    },
    getNumberOfInterviewInProgress: function () {
        return this.ip;
    },
    getReputation: function () {
        return this.reputation;
    },
    getType: function () {
        return this.type;
    },
    getProfilePic: function () {
        return this.profile_pic;
    },
    getReviewCount: function () {
        return this.reviewCount;
    },
    getChatPass: function () {
        return this.chatpass;
    },
    getReviews: function () {
        return this.reviews;
    },
    getEducations: function () {
        return this.educations;
    },
    getPositions: function () {
        return this.positions;
    },
    getIndividualRatingsCount: function () {
        return this.individualratingscounts;
    },
    getSkillList: function () {
        return this.skilllist;
    },
    setSkillList: function ($skilllist) {
        this.skilllist = skilllist;
    }

});

var availableSkills = [
    "ActionScript",
    "AppleScript",
    "Asp",
    "BASIC",
    "C",
    "C++",
    "Clojure",
    "COBOL",
    "ColdFusion",
    "Erlang",
    "Fortran",
    "Groovy",
    "Haskell",
    "Java",
    "JavaScript",
    "Lisp",
    "Perl",
    "PHP",
    "Python",
    "Ruby",
    "Scala",
    "Scheme"
];


var availableCompanies = [
    "Microsoft",
    "IBM",
    "Google",
    "Facebook",
    "Cisco",
    "Cornell",
    "Oracle",
    "Dell",
    "RSA"
];


var allCountriesOption = '<option value="">Country...</option>'
    + '<option value="Afganistan">Afghanistan</option>'
    + '<option value="Albania">Albania</option>'
    + '<option value="Algeria">Algeria</option>'
    + '<option value="American Samoa">American Samoa</option>'
    + '<option value="Andorra">Andorra</option>'
    + '<option value="Angola">Angola</option>'
    + '<option value="Anguilla">Anguilla</option>'
    + '<option value="Antigua &amp; Barbuda">Antigua &amp; Barbuda</option>'
    + '<option value="Argentina">Argentina</option>'
    + '<option value="Armenia">Armenia</option>'
    + '<option value="Aruba">Aruba</option>'
    + '<option value="Australia">Australia</option>'
    + '<option value="Austria">Austria</option>'
    + '<option value="Azerbaijan">Azerbaijan</option>'
    + '<option value="Bahamas">Bahamas</option>'
    + '<option value="Bahrain">Bahrain</option>'
    + '<option value="Bangladesh">Bangladesh</option>'
    + '<option value="Barbados">Barbados</option>'
    + '<option value="Belarus">Belarus</option>'
    + '<option value="Belgium">Belgium</option>'
    + '<option value="Belize">Belize</option>'
    + '<option value="Benin">Benin</option>'
    + '<option value="Bermuda">Bermuda</option>'
    + '<option value="Bhutan">Bhutan</option>'
    + '<option value="Bolivia">Bolivia</option>'
    + '<option value="Bonaire">Bonaire</option>'
    + '<option value="Bosnia &amp; Herzegovina">Bosnia &amp; Herzegovina</option>'
    + '<option value="Botswana">Botswana</option>'
    + '<option value="Brazil">Brazil</option>'
    + '<option value="British Indian Ocean Ter">British Indian Ocean Ter</option>'
    + '<option value="Brunei">Brunei</option>'
    + '<option value="Bulgaria">Bulgaria</option>'
    + '<option value="Burkina Faso">Burkina Faso</option>'
    + '<option value="Burundi">Burundi</option>'
    + '<option value="Cambodia">Cambodia</option>'
    + '<option value="Cameroon">Cameroon</option>'
    + '<option value="Canada">Canada</option>'
    + '<option value="Canary Islands">Canary Islands</option>'
    + '<option value="Cape Verde">Cape Verde</option>'
    + '<option value="Cayman Islands">Cayman Islands</option>'
    + '<option value="Central African Republic">Central African Republic</option>'
    + '<option value="Chad">Chad</option>'
    + '<option value="Channel Islands">Channel Islands</option>'
    + '<option value="Chile">Chile</option>'
    + '<option value="China">China</option>'
    + '<option value="Christmas Island">Christmas Island</option>'
    + '<option value="Cocos Island">Cocos Island</option>'
    + '<option value="Colombia">Colombia</option>'
    + '<option value="Comoros">Comoros</option>'
    + '<option value="Congo">Congo</option>'
    + '<option value="Cook Islands">Cook Islands</option>'
    + '<option value="Costa Rica">Costa Rica</option>'
    + '<option value="Cote DIvoire">Cote DIvoire</option>'
    + '<option value="Croatia">Croatia</option>'
    + '<option value="Cuba">Cuba</option>'
    + '<option value="Curaco">Curacao</option>'
    + '<option value="Cyprus">Cyprus</option>'
    + '<option value="Czech Republic">Czech Republic</option>'
    + '<option value="Denmark">Denmark</option>'
    + '<option value="Djibouti">Djibouti</option>'
    + '<option value="Dominica">Dominica</option>'
    + '<option value="Dominican Republic">Dominican Republic</option>'
    + '<option value="East Timor">East Timor</option>'
    + '<option value="Ecuador">Ecuador</option>'
    + '<option value="Egypt">Egypt</option>'
    + '<option value="El Salvador">El Salvador</option>'
    + '<option value="Equatorial Guinea">Equatorial Guinea</option>'
    + '<option value="Eritrea">Eritrea</option>'
    + '<option value="Estonia">Estonia</option>'
    + '<option value="Ethiopia">Ethiopia</option>'
    + '<option value="Falkland Islands">Falkland Islands</option>'
    + '<option value="Faroe Islands">Faroe Islands</option>'
    + '<option value="Fiji">Fiji</option>'
    + '<option value="Finland">Finland</option>'
    + '<option value="France">France</option>'
    + '<option value="French Guiana">French Guiana</option>'
    + '<option value="French Polynesia">French Polynesia</option>'
    + '<option value="French Southern Ter">French Southern Ter</option>'
    + '<option value="Gabon">Gabon</option>'
    + '<option value="Gambia">Gambia</option>'
    + '<option value="Georgia">Georgia</option>'
    + '<option value="Germany">Germany</option>'
    + '<option value="Ghana">Ghana</option>'
    + '<option value="Gibraltar">Gibraltar</option>'
    + '<option value="Great Britain">Great Britain</option>'
    + '<option value="Greece">Greece</option>'
    + '<option value="Greenland">Greenland</option>'
    + '<option value="Grenada">Grenada</option>'
    + '<option value="Guadeloupe">Guadeloupe</option>'
    + '<option value="Guam">Guam</option>'
    + '<option value="Guatemala">Guatemala</option>'
    + '<option value="Guinea">Guinea</option>'
    + '<option value="Guyana">Guyana</option>'
    + '<option value="Haiti">Haiti</option>'
    + '<option value="Hawaii">Hawaii</option>'
    + '<option value="Honduras">Honduras</option>'
    + '<option value="Hong Kong">Hong Kong</option>'
    + '<option value="Hungary">Hungary</option>'
    + '<option value="Iceland">Iceland</option>'
    + '<option value="India">India</option>'
    + '<option value="Indonesia">Indonesia</option>'
    + '<option value="Iran">Iran</option>'
    + '<option value="Iraq">Iraq</option>'
    + '<option value="Ireland">Ireland</option>'
    + '<option value="Isle of Man">Isle of Man</option>'
    + '<option value="Israel">Israel</option>'
    + '<option value="Italy">Italy</option>'
    + '<option value="Jamaica">Jamaica</option>'
    + '<option value="Japan">Japan</option>'
    + '<option value="Jordan">Jordan</option>'
    + '<option value="Kazakhstan">Kazakhstan</option>'
    + '<option value="Kenya">Kenya</option>'
    + '<option value="Kiribati">Kiribati</option>'
    + '<option value="Korea North">Korea North</option>'
    + '<option value="Korea Sout">Korea South</option>'
    + '<option value="Kuwait">Kuwait</option>'
    + '<option value="Kyrgyzstan">Kyrgyzstan</option>'
    + '<option value="Laos">Laos</option>'
    + '<option value="Latvia">Latvia</option>'
    + '<option value="Lebanon">Lebanon</option>'
    + '<option value="Lesotho">Lesotho</option>'
    + '<option value="Liberia">Liberia</option>'
    + '<option value="Libya">Libya</option>'
    + '<option value="Liechtenstein">Liechtenstein</option>'
    + '<option value="Lithuania">Lithuania</option>'
    + '<option value="Luxembourg">Luxembourg</option>'
    + '<option value="Macau">Macau</option>'
    + '<option value="Macedonia">Macedonia</option>'
    + '<option value="Madagascar">Madagascar</option>'
    + '<option value="Malaysia">Malaysia</option>'
    + '<option value="Malawi">Malawi</option>'
    + '<option value="Maldives">Maldives</option>'
    + '<option value="Mali">Mali</option>'
    + '<option value="Malta">Malta</option>'
    + '<option value="Marshall Islands">Marshall Islands</option>'
    + '<option value="Martinique">Martinique</option>'
    + '<option value="Mauritania">Mauritania</option>'
    + '<option value="Mauritius">Mauritius</option>'
    + '<option value="Mayotte">Mayotte</option>'
    + '<option value="Mexico">Mexico</option>'
    + '<option value="Midway Islands">Midway Islands</option>'
    + '<option value="Moldova">Moldova</option>'
    + '<option value="Monaco">Monaco</option>'
    + '<option value="Mongolia">Mongolia</option>'
    + '<option value="Montserrat">Montserrat</option>'
    + '<option value="Morocco">Morocco</option>'
    + '<option value="Mozambique">Mozambique</option>'
    + '<option value="Myanmar">Myanmar</option>'
    + '<option value="Nambia">Nambia</option>'
    + '<option value="Nauru">Nauru</option>'
    + '<option value="Nepal">Nepal</option>'
    + '<option value="Netherland Antilles">Netherland Antilles</option>'
    + '<option value="Netherlands">Netherlands (Holland, Europe)</option>'
    + '<option value="Nevis">Nevis</option>'
    + '<option value="New Caledonia">New Caledonia</option>'
    + '<option value="New Zealand">New Zealand</option>'
    + '<option value="Nicaragua">Nicaragua</option>'
    + '<option value="Niger">Niger</option>'
    + '<option value="Nigeria">Nigeria</option>'
    + '<option value="Niue">Niue</option>'
    + '<option value="Norfolk Island">Norfolk Island</option>'
    + '<option value="Norway">Norway</option>'
    + '<option value="Oman">Oman</option>'
    + '<option value="Pakistan">Pakistan</option>'
    + '<option value="Palau Island">Palau Island</option>'
    + '<option value="Palestine">Palestine</option>'
    + '<option value="Panama">Panama</option>'
    + '<option value="Papua New Guinea">Papua New Guinea</option>'
    + '<option value="Paraguay">Paraguay</option>'
    + '<option value="Peru">Peru</option>'
    + '<option value="Phillipines">Philippines</option>'
    + '<option value="Pitcairn Island">Pitcairn Island</option>'
    + '<option value="Poland">Poland</option>'
    + '<option value="Portugal">Portugal</option>'
    + '<option value="Puerto Rico">Puerto Rico</option>'
    + '<option value="Qatar">Qatar</option>'
    + '<option value="Republic of Montenegro">Republic of Montenegro</option>'
    + '<option value="Republic of Serbia">Republic of Serbia</option>'
    + '<option value="Reunion">Reunion</option>'
    + '<option value="Romania">Romania</option>'
    + '<option value="Russia">Russia</option>'
    + '<option value="Rwanda">Rwanda</option>'
    + '<option value="St Barthelemy">St Barthelemy</option>'
    + '<option value="St Eustatius">St Eustatius</option>'
    + '<option value="St Helena">St Helena</option>'
    + '<option value="St Kitts-Nevis">St Kitts-Nevis</option>'
    + '<option value="St Lucia">St Lucia</option>'
    + '<option value="St Maarten">St Maarten</option>'
    + '<option value="St Pierre &amp; Miquelon">St Pierre &amp; Miquelon</option>'
    + '<option value="St Vincent &amp; Grenadines">St Vincent &amp; Grenadines</option>'
    + '<option value="Saipan">Saipan</option>'
    + '<option value="Samoa">Samoa</option>'
    + '<option value="Samoa American">Samoa American</option>'
    + '<option value="San Marino">San Marino</option>'
    + '<option value="Sao Tome & Principe">Sao Tome &amp; Principe</option>'
    + '<option value="Saudi Arabia">Saudi Arabia</option>'
    + '<option value="Senegal">Senegal</option>'
    + '<option value="Seychelles">Seychelles</option>'
    + '<option value="Sierra Leone">Sierra Leone</option>'
    + '<option value="Singapore">Singapore</option>'
    + '<option value="Slovakia">Slovakia</option>'
    + '<option value="Slovenia">Slovenia</option>'
    + '<option value="Solomon Islands">Solomon Islands</option>'
    + '<option value="Somalia">Somalia</option>'
    + '<option value="South Africa">South Africa</option>'
    + '<option value="Spain">Spain</option>'
    + '<option value="Sri Lanka">Sri Lanka</option>'
    + '<option value="Sudan">Sudan</option>'
    + '<option value="Suriname">Suriname</option>'
    + '<option value="Swaziland">Swaziland</option>'
    + '<option value="Sweden">Sweden</option>'
    + '<option value="Switzerland">Switzerland</option>'
    + '<option value="Syria">Syria</option>'
    + '<option value="Tahiti">Tahiti</option>'
    + '<option value="Taiwan">Taiwan</option>'
    + '<option value="Tajikistan">Tajikistan</option>'
    + '<option value="Tanzania">Tanzania</option>'
    + '<option value="Thailand">Thailand</option>'
    + '<option value="Togo">Togo</option>'
    + '<option value="Tokelau">Tokelau</option>'
    + '<option value="Tonga">Tonga</option>'
    + '<option value="Trinidad &amp; Tobago">Trinidad &amp; Tobago</option>'
    + '<option value="Tunisia">Tunisia</option>'
    + '<option value="Turkey">Turkey</option>'
    + '<option value="Turkmenistan">Turkmenistan</option>'
    + '<option value="Turks &amp; Caicos Is">Turks &amp; Caicos Is</option>'
    + '<option value="Tuvalu">Tuvalu</option>'
    + '<option value="Uganda">Uganda</option>'
    + '<option value="Ukraine">Ukraine</option>'
    + '<option value="United Arab Erimates">United Arab Emirates</option>'
    + '<option value="United Kingdom">United Kingdom</option>'
    + '<option value="United States of America">United States of America</option>'
    + '<option value="Uraguay">Uruguay</option>'
    + '<option value="Uzbekistan">Uzbekistan</option>'
    + '<option value="Vanuatu">Vanuatu</option>'
    + '<option value="Vatican City State">Vatican City State</option>'
    + '<option value="Venezuela">Venezuela</option>'
    + '<option value="Vietnam">Vietnam</option>'
    + '<option value="Virgin Islands (Brit)">Virgin Islands (Brit)</option>'
    + '<option value="Virgin Islands (USA)">Virgin Islands (USA)</option>'
    + '<option value="Wake Island">Wake Island</option>'
    + '<option value="Wallis &amp; Futana Is">Wallis &amp; Futana Is</option>'
    + '<option value="Yemen">Yemen</option>'
    + '<option value="Zaire">Zaire</option>'
    + '<option value="Zambia">Zambia</option>'
    + '<option value="Zimbabwe">Zimbabwe</option>';


var postedInterviewsMap = new Map;
var awardedInterviewsMap = new Map;
var InterviewsWhereIBidMap = new Map;
var allBidsReceived = new Map;
var allBidsPosted = new Map;

var DETAILED_INTERVIEW_SCREEN = "<table width=\"100%\" border=\"0\"><tr><td width=\"40%\"><table border=\"0\" width=\"100%\">"
    + "<tr><td><h2>$ititle$</h2></td></tr>"
    + "<tr><td><h3>Posted By: $interviewee$</h3></td></tr>"
    + "<tr><td><h3>Skills: $skills$</h3></td></tr>"
    + "<tr><td><h3>Description: $desc$</h3></td></tr>"
    + "<tr><td><h3>Escrow Balance: $eb$</h3></td></tr>"
    + "<tr><td><h3>Status : $status$ $ratingLink$</h3></td></tr>"
    + "<tr><td><h3>Posted on : $date$</h3></td></tr>"
    + "<tr><td><h3>Attachment : $attachment$</h3></td></tr>"
    + "<tr><td><br></td></tr>"
    + "</table></td><td width=\"60%\" style=\"vertical-align:top\"><div id=\"itimeoptiondiv\"></div></td></tr></table>";


var NIN_HTML = "<table><tr><td>New Interview</td></tr>"
    + "<tr><td><h2><div class=\"interviewdetails\" iid=\"$iid$\" style=\"cursor: pointer; \">$title$</div></h2></td></tr>"
    + "<tr><td><h3>By: $createdBy$</h3></td></tr>"
    + "<tr><td><h3>Skills: $skills$</h3></td></tr>"
    + "<tr><td><h3>Date: $date$</h3></td></tr>"
    + "<tr><td><div id=\"bidmsgamnt_$iid$\" style=\"display:inline-block\"> Place your bid : "
    + "<input type=\"text\" id=\"$txtiid$_msg\"/>"
    + "<input size=\"3\" type=\"text\" id=\"$priceiid$_price\"/></div>"
    + "</td></tr>"
    + "<tr><td>"
    + '<input id="biddocupload_$iid$" type="file" name="file" data-url="aauth/fileupload.do?type=biddocupload" '
    + ' multiple style="opacity: 0; filter:alpha(opacity: 0);"/>'
    + '<input type="hidden" id="ninbiddoc_$iid$" value=""/>'
    + '<div id="biddocuploadedfn_$iid$"></div><div class="mytestclass"></div>'
    + '<div id="biddocuploadAdd_$iid$" style="cursor: pointer;">Add files</div>'
    + "</td></tr>"
    + "<tr><td>"
    + "<br><input type=\"button\" class=\"ibid\" id=\"$btniid$\" value=\"   Bid   \"></td></tr>"
    + "</table><hr>";


var NBN_HTML = "<table><tr><td>New Bid</td></tr>"
    + "<tr><td><h2>$currentuser$ posted a bid for interview "
    + "<div class=\"interviewdetails\" iid=\"$iid$\" style=\"cursor: pointer; \">$title$</div></h2></td></tr>"
    + "<tr><td><h3>Price: $price$</h3></td></tr>"
    + "<tr><td><h3>Date: $date$</h3></td></tr>"
    + "</table><hr>";

var FIN_HTML = '<table><tr><td>Financial activity</td></tr>'
        + '<tr><td><h2>$sender$ released funds to $receiver$ for interview $title$</h2></td></tr>'
        + '<tr><td>Date: $date$</td></tr>'
        + '</table><hr>'
    ;

var AIN_HTML = '<table><tr><td>Interview awarded</td></tr>'
        + '<tr><td><h2>$interviewee$ accepted $interviewer$ for the interview $title$ for $price$</h2></td></tr>'
        + '<tr><td>Date: $date$</td></tr>'
        + '</table><hr>'
    ;

var DFN_HTML = '<table><tr><td>Fund deposited</td></tr>'
        + '<tr><td><h2>$owner$ have deposited $netamount$ in your account</h2></td></tr>'
        + '<tr><td>Date: $date$</td></tr>'
        + '</table><hr>'
    ;

var ESN_HTML = '<table><tr><td>Escrow deposited</td></tr>'
        + '<tr><td><h2>$sender$ deposited $eb$ in escrow account of the interview $title$</h2></td></tr>'
        + '<tr><td>Date: $date$</td></tr>'
        + '</table><hr>'
    ;

var RTN_HTML = '<table><tr><td>New Rating</td></tr>'
        + '<tr><td><h2>$ratedby$ rated $user$ for interview $title$</h2></td></tr>'
        + '<tr><td>Rating: $ravg$</td></tr>'
        + '<tr><td>Date: $date$</td></tr>'
        + '</table><hr>'
    ;


var FORGOT_PASS_SCREEN = '<h2>Password retrieval system</h2>'
        + '<table>'
        + '<tr><h3><td>Email :</td><td><input type="text" id="forgotpass_email" /> </td></h3></tr>'
        + '<tr><h3><td></td><td>or</td></h3></td>'
            //+'<tr><h3><td>Username :</td><td><input type="text" id="forgotpass_username" /> </td></h3></tr>'
        + '<tr><h3><td></td><td><input type="button" id="forgotpass_button" value="Retrieve Password"/> </td></h3></tr>'
        + '</table>'
    ;
var PASS_CHANGE_SCREEN = '<h1>Change your password</h1>'
        + '<table>'
        + '<tr><h2><td></td><td><div id="changepasserror"></div></td></h2></tr>'
        + '<tr><h2><td>New password</td><td><input type="password" id="changepass1"></td></h2></tr>'
        + '<tr><h2><td>Repeat New password</td><td><input type="password" id="changepass2"></td></h2></tr>'
        + '<tr><h2><td></td><td><input type="button" id="changepass_button" value="Change Password"/> </td></h2></tr>'
        + '<tr><td></td><td><input type="hidden" id="changepass_authinstance" value="$authinstance$"/> </td></tr>'
        + '<tr><td></td><td><input type="hidden" id="changepass_authid" value="$authid$"/> </td></tr>'
        + '<tr><td></td><td><input type="hidden" id="changepass_authtoken" value="$authtoken$"/> </td></tr>'
        + '</table>'
    ;

var PROFILE_HTML = '<a href="#editprofile">edit</a><table>'
        + '<img  src="$ppic_src$" width="140px" height="140px"/>'
        + '<tr><td><h3>Email : </h3></td><td><h3>$username$</h3></td></tr>'
        + '<tr><td><h3>Skills : </h3></td><td><h3>$userskills$</h3></td></tr>'
        + '<tr><td><h3>Rate : </h3></td><td><h3>$userrate$</h3></td></tr>'
        + '<tr><td><h3>Companies : </h3></td><td><h3>$usercompanies$</h3></td></tr>'
        + '<tr><td><h3>Country : </h3></td><td><h3>$usercountry$</h3></td></tr>'
        + '<tr><td><h3>Availability : </h3></td><td><h3>$calendar$</h3></td></tr>'
        + '<tr><td><h3>Reputation : </h3></td><td><h3>'
        + '$reviewcount$ &nbsp;<div class="searchrating" data-average="$userreputation$" data-id=""  style="display:inline-block;vertical-align:middle;" ></div>'
        + '$userreputationnumber$/10</h3></td></tr>'
        + '<tr><td><h3>Balance : </h3></td><td><h3>$userbalance$'
        + ' &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;<input type=\"text\" id=\"dep_amount\" size=\"5\"/> '
        + '<input id=\"dep_button\" type=\"button\" value=\"Deposit Funds\"/></h3></td></tr>'
        + '</table>'
    ;


var EDIT_PROFILE_HTML = '<table>'
        + '<tr><td><h3>Profile pic : </h3></td><td><h3><img id="editprofilepic" src="img/defaultprofilepic.jpg" width="140px" height="140px"/></td></tr>'
        + '<input id="upload" type="file" name="file" data-url="aauth/fileupload.do?type=profilepicupdate" multiple style="opacity: 0; filter:alpha(opacity: 0);"></h3></td></tr>'
        + '<tr><td><h3>Skills : </h3></td><td><h3><div id="edit_profile_skills"><ul></ul></div></h3></td></tr>'
        + '<tr><td><h3>Rate : </h3></td><td><h3><input type="text" id="edit_profile_rate"/></h3></td></tr>'
        + '<tr><td><h3>Companies : </h3></td><td><div  id="edit_profile_companies"><ul></ul></div></td></tr>'
        + '<tr><td><h3>Availability : </h3></td><td><h3><input type="button" value="Edit Availability Calendar" id="editavailabilitycalendar"></h3></td></tr>'
        + '<tr><td><h3>Country : </h3></td><td><h3>' + allCountriesOption + '</h3></td></tr>'
        + '<tr><td><h3>CV : </h3></td><td><h3><textarea id="updatedcv"></textarea></h3></td></tr>'
        + '<tr><td></td><td><input id=\"edit_profile_button\" type=\"button\" value=\"Update Profile\"/></td></tr>'
        + '</table>'
    ;

var direct_ecount = 0
var DIRECT_USER_EDU = '<table><tr><td>Degree&nbsp;:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <input id="degree_$ecount$" size="15" type="text" value="" />&nbsp;&nbsp;&nbsp;&nbsp; Major&nbsp;:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <input id="fieldofstudy_$ecount$" type="text" value="" /> </td></tr>' +
    '<tr><td>Start Year: &nbsp; <input id="startyear_$ecount$" size="15" type="text" value=""/>&nbsp;&nbsp;&nbsp;&nbsp; End Year: &nbsp; <input id="endyear_$ecount$" type="text" value=""></td></tr>' +
    '<tr><td>School &nbsp;: &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input id="school_$ecount$" size="53" type="text" value="" /> &nbsp;&nbsp;&nbsp;&nbsp;  </td></tr>'
    + '</table><br>';
var direct_pcount = 0;
var DIRECT_USER_POSITION = '<table><tr><td>Title</td><td><input id="title_$pcount$" type="text" value="" /></td><td>Company:</td><td><input id="comp_$pcount$" type="text" value="" /></td></tr>' +
    '<tr><td>Start Year:</td><td><input id="syear_$pcount$" type="text" value="" /></td><td>End Year</td><td><input id="eyear_$pcount$" type="text" value="" /></td></tr>' +
    '<tr><td colspan="4"><textarea id="desc_$pcount$" rows=\"6\" cols=\"50\" value=""></textarea></td> </tr>' +
    '</table>';


function getURLParameter(name) {
    return decodeURI(
        (RegExp(name + '=' + '(.+?)(&|$)').exec(location.search) || [, null])[1]
    );
}

var openDisputes = new Map;
var closedDisputes = new Map;

function processDisputes(data) {
    var allDisputes = (data.data);

    for (var i = 0; i < allDisputes.length; i++) {
        console.log(allDisputes[i]);
        if (allDisputes[i].dstatus == "OPEN") {
            openDisputes.put(allDisputes[i].did, new Dispute(allDisputes[i].did, allDisputes[i].vid, allDisputes[i].ititle, allDisputes[i].dwith, allDisputes[i].amount, allDisputes[i].result, allDisputes[i].closedtime, "", allDisputes[i].createdby));
        }
        if (allDisputes[i].dstatus == "CLOSED") {
            closedDisputes.put(allDisputes[i].did, new Dispute(allDisputes[i].did, allDisputes[i].vid, allDisputes[i].ititle, allDisputes[i].dwith, allDisputes[i].amount, allDisputes[i].result, allDisputes[i].closedtime, "", allDisputes[i].createdby));
        }
    }
}

function populateInboxCount(data) {
    $("#inboxcount").html(data.count);
}

function pageloadbyhash(hashURL) {
    var mainTarget = hashURL.value.split('|');
    var resetPassURL = hashURL.value.split('?');

    if (mainTarget[0] == "forgotpassword") {
        showForgotPasswordScreen();
        return;
    }
    if (mainTarget[0] == "register") {
        showRegistrationScreen();
        return;
    }
    if (mainTarget[0] == "linkedinuserregistration") {
        showLinkedInUserRegistrationScreen();
        return;
    }

    if (sessionLoggedIn == null)
        window.location(BASE_URL + "index.jsp");

    if (resetPassURL[0] == "resetpasswordscreen") {
        var statusValue = "";
        var authInstanceValue = "";
        var authIdValue = "";
        var authTokenValue = "";

        var parameter_name = "status";
        var token = location.hash.substring(location.hash.indexOf(parameter_name) + parameter_name.length);
        if (token.indexOf('&') >= 0) token = token.substring(1, token.indexOf('&'));
        statusValue = token;

        parameter_name = "authinstance";
        token = location.hash.substring(location.hash.indexOf(parameter_name) + parameter_name.length);
        if (token.indexOf('&') >= 0) token = token.substring(1, token.indexOf('&'));
        authInstanceValue = token;

        parameter_name = "authid";
        token = location.hash.substring(location.hash.indexOf(parameter_name) + parameter_name.length);
        if (token.indexOf('&') >= 0) token = token.substring(1, token.indexOf('&'));
        authIdValue = token;

        parameter_name = "authtoken";
        token = location.hash.substring(location.hash.indexOf(parameter_name) + parameter_name.length);
        if (token.indexOf('&') >= 0) token = token.substring(1, token.indexOf('&'));
        authTokenValue = token;


        showChangePasswordScreen(statusValue, authInstanceValue, authIdValue, authTokenValue);
        return;
    }

    if (mainTarget[0] == "login")
        login();

    if (sessionLoggedIn != "true")
        login();


    if (BASE_URL + 'welcome.do' == window.location.href)
        showHomeView();

    else if (mainTarget[0] == "inbox") {
        showMessageBox();
    }
    else if (mainTarget[0] == "dispute") {
        showDisputeBox();
    }
    else if (mainTarget[0] == "rateuser") {
        showRatingScreen(mainTarget[1]);
    }
    else if (mainTarget[0] == "finances" && mainTarget[1] == null) {
        var d = new Date();
        d.setMonth(1);
        financeTAB = "";
        showFinanceScreen(d, new Date());
    }
    else if (mainTarget[0] == "finances" && mainTarget[1] == "escrowfunds") {
        var d = new Date();
        d.setMonth(1);
        financeTAB = "escrowfunds";
        showFinanceScreen(d, new Date());
    }
    else if (mainTarget[0] == "myinterviews") {

        if (user.getType() == "INTERVIEWEE")
            showIntervieweeView();
        if (user.getType() == "INTERVIEWER")
            showInterviewerView();

    }
    else if (mainTarget[0] == "postmyinterview") {
        postMyInterview();
    }
    else if (mainTarget[0] == "login") {
        //$("#main").html("<center><h2>You are already Logged In</h2></center><a href=\"http://localhost:8080/interviewbackend/logout.do\" ><h3>logout</h3></a>");
    }
    //else if(mainTarget[0] == "userdetails"){
    //	showMemberHome();
    //}
    else if (mainTarget[0] == "home") {
        window.location = BASE_URL + "welcome.do";
//		showHomeView();
    }
    else if (mainTarget[0] == "interviewdetails") {
        showInterviewDetails(mainTarget[1]);
    }
    else if (mainTarget[0] == "userdetails") {
        if (mainTarget[1] == null)
            showUserDetails(user.getUserName());
        else
            showUserDetails(mainTarget[1]);

    }
    else if (mainTarget[0] == "editprofile") {
        showeditProfileScreen();
    }
    else if (mainTarget[0] == "support") {
        showSupportTab();
    }


    else if (mainTarget[0] == "mycurrentbids") {
        $("#curr_interviews").css("font-weight", "Normal");
        $("#curr_bids").css("font-weight", "Bold");
        showMyBidsView();
    }
    else if (mainTarget[0] == "mycurrentinterviews") {
        $("#curr_bids").css("font-weight", "Normal");
        $("#curr_interviews").css("font-weight", "Bold");
        showMyCurrentInterviews();
    }
    else if (mainTarget[0] == "asinterviewer") {
        showInterviewerView();
    }
    else if (mainTarget[0] == "asinterviewee") {
        showIntervieweeView();
    }


}


var LINKEDIN_USER_EDU = '<table><tr><td>Degree&nbsp;:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <input id="degree_$ecount$" size="15" type="text" value="$degree$" />&nbsp;&nbsp;&nbsp;&nbsp; Major&nbsp;:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <input id="fieldofstudy_$ecount$" type="text" value="$fieldofstudy$" /> </td></tr>' +
    '<tr><td>Start Year: &nbsp; <input id="startyear_$ecount$" size="15" type="text" value="$startYear$"/>&nbsp;&nbsp;&nbsp;&nbsp; End Year: &nbsp; <input id="endyear_$ecount$" type="text" value="$endYear$"></td></tr>' +
    '<tr><td>School &nbsp;: &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input id="school_$ecount$" size="53" type="text" value="$schoolname$" /> &nbsp;&nbsp;&nbsp;&nbsp;  </td></tr></table><br>';
var LINKEDIN_USER_SKILL = '<table><tr><td>$skillname$</td><td>$proficiency$</td><td>$exp$</td></tr></table>';
var LINKEDIN_USER_POSITION = '<table><tr><td>Title</td><td><input id="title_$pcount$" type="text" value="$title$" /></td><td>Company:</td><td><input id="comp_$pcount$" type="text" value="$companyName$" /></td></tr>' +
    '<tr><td>Start Year:</td><td><input id="syear_$pcount$" type="text" value="$startyear$" /></td><td>End Year</td><td><input id="eyear_$pcount$" type="text" value="$endyear$" /></td></tr>' +
    '<tr><td colspan="4"><textarea id="desc_$pcount$" rows=\"6\" cols=\"50\" value="$desc$"></textarea></td> </tr></table>';
var LINKEDIN_USER_REG_SCREEN = '<table>' +
        '<tr><td colspan="2"><div style="color:green"><h2>New User Registration</h2></div></td></tr>' +
        '<tr><td colspan="2"><div id="in_reg_error" style="color:red"></div></td></tr>' +
        '<tr>' +
        '<td style="vertical-align:top"><h3>Profile pic</h3></td>' +
        '<td><h3><img id="inuserprofilepic" src="$inuserprofilepic_src$" width="140px" height="140px"/></h3></td>' +
        '</tr>' +
        '<tr><td style="vertical-align:top"><h3>Username:</h3></td><td style="vertical-align:top"><h3><input id="in_gen_un" size="30" type="text" value="$genratedusername$" /></h3></td></tr>' +
        '<tr><td style="vertical-align:top"><h3>Email:</h3></td><td style="vertical-align:top"><h3><input size="30" type="text" value="$emailfromin$" id="inemail" disabled/></h3></td></tr>' +
        '<tr><td style="vertical-align:top"><h3>You are:</h3></td><td style="vertical-align:top"><h3>' +
        '<select id="in_user_role" style="width:200px;">' +
        '<option value="0" >select</option>' +
        '<option value="INTERVIEWER">INTERVIEWER</option>' +
        '<option value="INTERVIEWEE">INTERVIEWEE</option>' +
        '</select>&nbsp;&nbsp;&nbsp;&nbsp;<span id="in_user_role_rate"></span></h3></td></tr>' +
        '<tr><td style="vertical-align:top"><h3>Profile:</h3></td><td style="vertical-align:top" ><h3><div id="in_user_summary">$summary$</div></h3></td></tr>' +
        '<tr><td style="vertical-align:top"><h3>Industry:</h3></td><td style="vertical-align:top"><h3><div id="in_user_industry">$industry$</div></h3></td></tr>' +
        '<tr><td style="vertical-align:top"><h3>Skills:</h3></td><td style="vertical-align:top"><h3><ul id="linkedinuser_specialities">$specialities$</ul></h3></td></tr>' +
        '<tr><td style="vertical-align:top"><h3>Education:</h3></td><td style="vertical-align:top"><h3>$educations$</h3></td></tr>' +
        '<tr><td style="vertical-align:top"><h3>Positions:</h3></td><td style="vertical-align:top"><h3>$positions$</h3></td></tr>' +
        '<tr><td style="vertical-align:top"><h3>Location:</h3></td><td style="vertical-align:top"><h3>$location$</h3></td></tr>' +
        '<tr><td colspan="2" style="text-align:center"><span><input id="inuserregCancel" type="button" value="Cancel"/><input id="inuserregButton" type="button" value="Register"/></span></td></tr>' +
        '</table>'
    ;


var showLinkedInUserRegistrationScreen = function () {
    if (linkedInUser == null) {
        window.location.href = BASE_URL + 'index.jsp';
        window.location.hash = '';

    }
    var specialitiesArray = linkedInUser.specialities + "".split(",");
    var tags = "";
    for (var i = 0; i < specialitiesArray.length; i++) {
        tags = tags + "<li>" + specialitiesArray + "</li>";
    }

    var ecount = 0;
    var educations = "";
    $.each(linkedInUser.educationList, function (num, education) {
        var ed = LINKEDIN_USER_EDU
                .split("$degree$").join(education.degree == "null" ? "" : education.degree)
                .split("$schoolname$").join(education.schoolname == "null" ? "" : education.schoolname)
                .split("$fieldofstudy$").join(education.fieldOfStudy == "null" ? "" : education.fieldOfStudy)
                .split("$startYear$").join(education.startYear == 0 ? "" : education.startYear)
                .split("$endYear$").join(education.endYear == 0 ? "" : education.endYear)
                .split("$ecount$").join(ecount++)
            ;
        educations = educations + ed;
    });

    $.each(linkedInUser.skillsList, function (num, skill) {
        tags = tags + "<li>" + skill.skillname + "</li>";
    });

    var pcount = 0;
    var poss = "";
    $.each(linkedInUser.positionsList, function (num, position) {
        var ps = LINKEDIN_USER_POSITION
                .split("$title$").join(position.title == null ? "" : position.title)
                .split("$companyName$").join(position.companyName == null ? "" : position.companyName)
                .split("$startyear$").join(position.startYear == 0 ? "" : position.startYear)
                .split("$endyear$").join(position.endYear == 0 ? "" : position.endYear)
                .split("$desc$").join(position.description)
                .split("$pcount$").join(pcount++)
            ;
        poss = poss + ps;
    });


    var mylinkedInUser = linkedInUser;
    var scr = LINKEDIN_USER_REG_SCREEN
            .split("$inuserprofilepic_src$").join(linkedInUser.profileURL)
            .split("$summary$").join(linkedInUser.summary)
            .split("$industry$").join(linkedInUser.industry)
            .split("$specialities$").join(tags)
            .split("$educations$").join(educations)
            .split("$positions$").join(poss)
            .split("$location$").join(linkedInUser.location)
            .split("$genratedusername$").join(IN_REG_USERNAME)
            .split("$emailfromin$").join(linkedInUser.emailAddress)
        ;

    $("#main").html(scr);
    $("#linkedinuser_specialities").tagit();
    $('#in_user_role').on('change', function (e) {
        var role = $(this).val();
        if (role == 'INTERVIEWER') {
            $("#in_user_role_rate").html('Interview Rate:&nbsp;&nbsp; <input type="text" id="in_interviewer_rate">');
        }
        else
            $("#in_user_role_rate").html('');
    });

    $(document).on('click', '#inuserregCancel', function () {

        $.ajax({
            type: "POST",
            url: BASE_URL + "linkedinuserregistercancel.do",
        }).done(function (msg) {

            if (msg == "DONE")
                window.location.href = BASE_URL + 'index.jsp';
        });

    });

    $(document).on('click', '#inuserregButton', function () {
        $("#in_reg_error").html("");
        var generateUserName = $("#in_gen_un").val().trim();
        if (generateUserName == "") {
            $("#in_reg_error").html("Please provide a username");
            return;
        }
        if (generateUserName.length > 10) {
            $("#in_reg_error").html("Username size must be less than 10 characters");
            return;
        }

        var inUserRole = $("#in_user_role").val();
        if (inUserRole == 0) {
            $("#in_reg_error").html("Please select your role");
            return;
        }


        if (inUserRole == 'INTERVIEWER') {
            var hourlyRate = $("#in_interviewer_rate").val().trim();
            if (hourlyRate == "") {
                $("#in_reg_error").html("If your role is Interviewer, you must provide your hourly rate.");
                return;
            }
            if (!isFinite(String(hourlyRate))) {
                $("#in_reg_error").html("Please provide numerical value as your hourly rate.");
                return;
            }
        }

        var profile = $("#in_user_summary").text();

        var industry = $("#in_user_industry").text();
        var skills = getTags($('#linkedinuser_specialities').tagit('tags'))

        var educationParam = "";
        for (var i = 0; i < ecount; i++) {
            if ($("#degree_" + i).val().trim() == "") {
                $("#in_reg_error").html("Please provide degree of education " + (i + 1) + ".");
                return;
            }
            if ($("#fieldofstudy_" + i).val().trim() == "") {
                $("#in_reg_error").html("Please provide field of study in education " + (i + 1) + ".");
                return;
            }
            if ($("#startyear_" + i).val().trim() == "") {
                $("#in_reg_error").html("Please provide start year of education " + (i + 1) + ".");
                return;
            }
            if ($("#endyear_" + i).val().trim() == "") {
                $("#in_reg_error").html("Please provide end year of education " + (i + 1) + ".");
                return;
            }
            if ($("#school_" + i).val().trim() == "") {
                $("#in_reg_error").html("Please provide school name of education " + (i + 1) + ".");
                return;
            }
            educationParam = educationParam
                + "degree_" + i + "=" + $("#degree_" + i).val().trim() + "&"
                + "fieldofstudy_" + i + "=" + $("#fieldofstudy_" + i).val().trim() + "&"
                + "startyear_" + i + "=" + $("#startyear_" + i).val().trim() + "&"
                + "endyear_" + i + "=" + $("#endyear_" + i).val().trim() + "&"
                + "school_" + i + "=" + $("#school_" + i).val().trim() + "&"
            ;
        }

        var positionParam = "";
        for (var i = 0; i < pcount; i++) {
            if ($("#title_" + i).val().trim() == "") {
                $("#in_reg_error").html("Please provide title of position " + (i + 1) + ".");
                return;
            }
            if ($("#comp_" + i).val().trim() == "") {
                $("#in_reg_error").html("Please provide company name in position " + (i + 1) + ".");
                return;
            }
            if ($("#syear_" + i).val().trim() == "") {
                $("#in_reg_error").html("Please provide start year of position " + (i + 1) + ".");
                return;
            }
            if ($("#eyear_" + i).val().trim() == "") {
                $("#in_reg_error").html("Please provide end year of position " + (i + 1) + ".");
                return;
            }
            positionParam = positionParam
                + "title_" + i + "=" + $("#title_" + i).val().trim() + "&"
                + "comp_" + i + "=" + $("#comp_" + i).val().trim() + "&"
                + "syear_" + i + "=" + $("#syear_" + i).val().trim() + "&"
                + "eyear_" + i + "=" + $("#eyear_" + i).val().trim() + "&"
                + "desc_" + i + "=" + $("#desc_" + i).val().trim() + "&"
            ;
        }

        var param = "username=" + generateUserName +
                "&useremail=" + $("#inemail").val() +
                "&type=" + inUserRole +
                "&rate=" + (inUserRole == 'INTERVIEWER' ? $("#in_interviewer_rate").val().trim() : "") +
                "&cv=" + profile +
                "&industry=" + industry +
                "&skills=" + skills +
                "&country=" + linkedInUser.location +
                "&educationcount=" + ecount +
                "&" + educationParam +
                "&positioncount=" + pcount +
                "&" + positionParam +
                "&profilepic=" + linkedInUser.profileURL
            ;

        $.ajax({
            type: "POST",
            url: BASE_URL + "linkedinuserregister.do",
            data: param
        }).done(function (msg) {
            var jsonResponse = jQuery.parseJSON(msg);
            if (jsonResponse.result == "1") {
                alert("You have been successfully registered");
                window.location.hash = "login";
            }
            if (jsonResponse.result == "2") {
                alert("User already exist. ");
            }
        });

    });

};


var CREATE_DISPUTE_HTML = '<table><tr><td width="200px"><h3>Select Interview</h3></td>'
    + '<td><h3>'
    + '<select id="disputeinterviewselect">'
    + '<option value="0">Select</option>'
    + '</select>'
    + '</h3></td></tr>'
    + '<tr><td><h3>Supporting Documents(.zip)</h3></td><td>'
    + '<div id="uploaddisputefile" style="cursor: pointer;">Upload File</div>'
    + '<input id="upload" type="file" name="file" data-url="aauth/fileupload.do?type=disputedocument" '
    + ' multiple style="opacity: 0; filter:alpha(opacity: 0);"/>'
    + '<input type="hidden" id="disputefileid" value="" />'
    + '</td></h2></tr>'
    + '<tr><td><h3>Message:</h3></td>'
    + '<td><h3><textarea rows=\"10\" cols=\"50\" id=\"dispute_message\"></textarea></h3></td></tr>'
    + '<tr><td></td><td><input type="button" id="submitdispute" value="Create Dispute"></td></tr>'
    + '</table>';

var DISPUTE_MSG_TEMPLATE = '<table width="60%" border="0"><tr><td width="134px"  style="vertical-align:top">$user$<br>$time$</td><td  style="vertical-align:top">$message$</td></tr>'
    + '<tr><td></td><td  style="vertical-align:top">$attachment$</td></tr>'
    + '<tr><td></td><td></td></tr>'
    + '</table><hr>';
var DISPUTE_MSG_BOX = '<table border="0" width="59%" >'
    + '<tr><td style="vertical-align:top"  >Your message</td>'
    + '<td><textarea rows=\"6\" cols=\"50\" id=\"dispute_message\"></textarea></td></tr>'
    + '<tr><td  width="200px"  style="vertical-align:top">Attach Documents</td><td>'
    + '<div id="uploaddisputefile" style="cursor: pointer;">Upload File</div>'
    + '<input id="upload" type="file" name="file" data-url="aauth/fileupload.do?type=disputedocument" '
    + ' multiple style="opacity: 0; filter:alpha(opacity: 0);"/>'
    + '<input type="hidden" id="disputefileid" value="" /><input type="hidden" id="disputeid" value="$did$">'
    + '</td></tr>'
    + '<tr><td></td><td><input type="button" id="senddisputemessage" value="Send Message"></td></tr>'
    + '</table>';

var showDisputeBox = function () {

    /*$("#main").html("<h1>This is the dispute box</h1> "
     +"<span id=\"createdispute\" style=\"cursor: pointer;\">Create Dispute</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"
     +"<span style=\"cursor: pointer;\" id=\"trackdispute\">Track Dispute</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"
     +"<span style=\"cursor: pointer;\" id=\"closeddispute\">Closed Disputes</span>"
     +"<div id=\"disputeregion\"></div>");*/
    //alert(postedInterviewsMap.size);


    /*$(document).on('click', '#createdispute', function () {
     $("#disputeregion").html(CREATE_DISPUTE_HTML);


     $(document).on('click', '#uploaddisputefile', function () {
     if(!disputeAbleNotFound)
     $("#upload").trigger('click');
     });

     $('#upload').fileupload({
     dataType: 'json',
     maxChunkSize: 20000000, // 10 MB
     done: function (e, data) {
     var jsonResponse = jQuery.parseJSON(data.jqXHR.responseText);
     $("#uploaddisputefile").html("Cancel  &nbsp;&nbsp;&nbsp;&nbsp;"+jsonResponse.originalfn+"")
     $("#disputefileid").attr("value", jsonResponse._id);
     alert(jsonResponse.originalfn);
     },
     });

     });*/

    $(document).on('click', '#closeddispute', function () {

        var optiondiv = '<select id="disputeinterviewselect">'
            + '<option value="0">Select</option>'
            + '</select><div id="disputetrail"></div><div id="dmsgbox"></div>';

        $("#disputeregion").html(optiondiv);

        for (var i = 0; i++ < closedDisputes.size; closedDisputes.next()) {
            $('<option>').val(closedDisputes.key()).text(closedDisputes.value()).appendTo('#disputeinterviewselect');
        }

        activateDisputeDropDown();
    });

    $(document).on('click', '#trackdispute', function () {
        var optiondiv = '<select id="disputeinterviewselect">'
            + '<option value="0">Select</option>'
            + '</select><div id="disputetrail"></div><div id="dmsgbox"></div>';

        $("#disputeregion").html(optiondiv);

        activateDisputeDropDown();
        $(document).on('click', '#interviewerclosingdispute', function () {
            var did = $("#disputeid").attr("value");
            $.ajax({
                type: "GET",
                url: BASE_URL + "interviewerclosingdispute.do",
                data: "did=" + did
            }).done(function (msg) {
                var jsonResponse = jQuery.parseJSON(msg);
                $("#disputetrail").html(jsonResponse.message);
                $("#dmsgbox").html("");
                if (jsonResponse.status == "1") {
                    var updatedinterview = getInterviewById(jsonResponse.iid);
                    updatedinterview.setStatus(Number(jsonResponse.istatus));
                }
            });
        });
    });
}

var activateDisputeDropDown = function () {

    $("#disputeinterviewselect").change(function () {
        var did = $(this).val();

    });


};


var EVENT_DIV = '<table width="100%" ><tr ><td style="vertical-align:middle;margin-top:15px;padding:5px;">Event Title:</td>'
    + '<td style="padding:5px;"><input style="margin-bottom:0px; width:70%; padding: 2px;" '
    + 'type="text" value="$interview$" id="eventtitle"/></td></tr>'
    + '<tr><td style="padding:5px;">Time </td><td style="padding:5px;">'
    + '<input id="startinput" type="text" style="width:50px;padding: 2px;" value="$start$"/> - '
    + '<input id="endinput" type="text" style="width:50px;padding: 2px;" value="$end$"/>$day$</td></tr>'
    + '<tr><td style="padding:5px;">Event type:</td><td style="padding:5px;">'
    + '<select style="padding: 2px;" id="eventtype">'
    + '<option value="A">Available</option>'
        //+'<option value="B">Busy in interview</option>'
    + '</select>'
    + '</td></tr>'
    + '<tr><td style="padding:5px;">Recurrence</td><td style="padding:5px;">'
    + '<select style="padding: 2px;" id="eventrecurrence">'
    + '<option value="0">No recurrence</option>'
    + '<option value="7">1 week</option>'
    + '<option value="30">1 month</option>'
    + '<option value="90">3 month</option>'
    + '</select></td></tr>'
    + '</table>';

var EVENT_MOVE_CONFIRMATION = '<table width="100%">'
        + '<tr><td colspan="2"></td></tr>'
        + '<tr ><td style="vertical-align:middle;margin-top:15px;padding:5px;">Event Title:</td>'
        + '<td style="padding:5px;">$title$</td></tr>'
        + '<tr><td style="padding:5px;">Was Scheduled At :</td><td style="padding:5px;">$before$</td></tr>'
        + '<tr><td style="padding:5px;">Now Scheduled At :</td><td style="padding:5px;">$after$</td></tr>'
        + '</table>'
    ;


var showeditProfileScreen = function () {
    $("#main").html(EDIT_PROFILE_HTML);

    $("#editprofilepic").attr("src", user.getProfilePic());


    $(document).on('click', '#editavailabilitycalendar', function () {
        $("#availabilitycalendar").hide();
        $('#availabilitycalendar').html("");
        $("#availabilitycalendar").dialog({
            title: "Edit Your availability Calendar",
            width: 610,
            open: function (event, ui) {
                //$('#availabilitycalendar').html("This is dynamic dialog");
                var date = new Date();
                var d = date.getDate();
                var m = date.getMonth();
                var y = date.getFullYear();
                var calendar = $('#availabilitycalendar').fullCalendar({
                    header: {
                        left: 'prev,next today',
                        center: 'title',
                        right: 'month,agendaWeek,agendaDay'
                    },
                    defaultView: 'agendaWeek',
                    firstHour: 17,
                    allDaySlot: false,
                    selectable: true,
                    eventStartEditable: true,
                    eventDurationEditable: true,
                    selectHelper: true,
                    height: 400,
                    editable: true,
                    select: function (start, end, allDay) {
                        $("#eventconfirmation").hide();
                        $("#eventconfirmation").html("");
                        var scr = EVENT_DIV.split("$start$").join(start.format("H:MM"))
                            .split("$end$").join(end.format("H:MM"))
                            .split("$interview$").join("Available")
                            .split("$day$").join(end.format("(ddd) dd mmm"));
                        $("#eventconfirmation").append(scr);
                        $('#startinput').timepicker({'timeFormat': 'G:i', 'disableTextInput': true});
                        $('#endinput').timepicker({'timeFormat': 'G:i', 'disableTextInput': true});
                        $("#eventconfirmation").dialog({
                            autoOpen: true,
                            height: 235,
                            width: 350,
                            modal: true,
                            closeOnEscape: true,
                            buttons: {
                                "Confirm": function () {
                                    var mypopup = $(this);
                                    $.ajax({
                                        type: "GET",
                                        url: BASE_URL + "schedulecalendar.do",
                                        data: "title=" + $("#eventtitle").val() + "&start=" + start.getTime() + "&end=" + end.getTime() + "&recur=" + $("#eventrecurrence").val() + "&eventtype=" + $("#eventtype").val()
                                    }).done(function (msg) {
                                        var resData = jQuery.parseJSON(msg);
                                        if (resData.status == "1") {
                                            calendarRender(calendar, resData.eventId, $("#eventtitle").val(), start, end, $("#eventrecurrence").val());
                                            mypopup.dialog("close");
                                        }
                                    });

                                },
                                Cancel: function () {
                                    $(this).dialog("close");
                                }
                            },
                            close: function () {
                                $(this).dialog("close");
                            }
                        });


                    },

                    events: function (start, end, callback) {

                        $.ajax({
                            type: "GET",
                            url: BASE_URL + "getcalendarevents.do",
                            data: "start=" + start.getTime() + "&end=" + end.getTime()
                        }).done(function (msg) {
                            //alert(msg);
                            var events = [];
                            var resData = jQuery.parseJSON(msg);
                            $.each(resData.list, function (index, data) {
                                new CalendarEvents(data._id, data.userid, data.title, data.starttime, data.endtime,
                                    data.eventtype, data.isrecursive, data.recurdays, data.time);
                                if (data.isrecursive == true) {
                                    var days = Number(data.recurdays);
                                    //alert(days);
                                    var start = new Date(Number(data.starttime));
                                    var end = new Date(Number(data.endtime));

                                    for (var i = 0; i < days; i++) {
                                        var newStart = new Date(start.getTime() + (i * 24 * 60 * 60 * 1000));
                                        var newEnd = new Date(end.getTime() + (i * 24 * 60 * 60 * 1000));

                                        events.push({
                                            id: data._id,
                                            title: data.title,
                                            start: newStart,
                                            end: newEnd,
                                            allDay: false
                                        });
                                    }
                                }
                                else {
                                    events.push({
                                        id: data._id,
                                        title: data.title,
                                        start: new Date(Number(data.starttime)),
                                        end: new Date(Number(data.endtime)),
                                        allDay: false
                                    });
                                }
                            });
                            callback(events);
                        });


                    },
                    eventResize: function (event, dayDelta, minuteDelta, revertFunc) {
                        rescheduleDropEvent(calendar, event);
                    },
                    eventDrop: function (event, dayDelta, minuteDelta, allDay, revertFunc, jsEvent, ui, view) {
                        var myevent = allCalendarEvents.get(event.id);
                        if (Number(myevent.getRecurDays()) > 0) {
                            if (dayDelta == 0)
                                rescheduleDropEvent(calendar, event);
                            else {
                                alert("Sorry, you can only change the duration, not the starting date, of a recurring event.");
                                revertFunc();
                            }
                        }
                        else
                            rescheduleDropEvent(calendar, event);
                    },
                    eventClick: function (calEvent, jsEvent, view) {
                        var event = allCalendarEvents.get(calEvent.id);

                        var scr = EVENT_DIV.split("$start$").join(new Date(event.getStart()).format("H:MM"))
                            .split("$end$").join(new Date(event.getEnd()).format("H:MM"))
                            .split("$interview$").join(event.getTitle())
                            .split("$day$").join(new Date(event.getEnd()).format("(ddd) dd mmm"));
                        $("#eventconfirmation").html(scr);
                        $('#startinput').timepicker({'timeFormat': 'G:i', 'disableTextInput': true});
                        $('#endinput').timepicker({'timeFormat': 'G:i', 'disableTextInput': true});
                        if (event.getIsRecursive() == true) {
                            $("#eventrecurrence").val(event.getRecurDays());
                        }

                        $("#eventconfirmation").dialog({
                            autoOpen: true,
                            height: 235,
                            width: 350,
                            modal: true,
                            closeOnEscape: true,
                            buttons: {
                                "Update": function () {
                                    var mypopup = $(this);
                                    var updatedStart = $('#startinput').timepicker('getTime', new Date(event.getStart()));
                                    var updatedEnd = $('#endinput').timepicker('getTime', new Date(event.getEnd()));
                                    if (updatedStart.getTime() < updatedEnd.getTime()) {
                                        var output = updateCalendarEvent(calEvent.id,
                                            $("#eventtitle").val(),
                                            updatedStart.getTime(),
                                            updatedEnd.getTime(),
                                            $("#eventrecurrence").val(),
                                            $("#eventtype").val());
                                        if (output == 1) {
                                            var updatedevent = allCalendarEvents.get(calEvent.id);
                                            calendar.fullCalendar('removeEvents', calEvent.id);

                                            calendarRender(calendar, updatedevent.getId(),
                                                updatedevent.getTitle(), updatedevent.getStart(), updatedevent.getEnd(), updatedevent.getRecurDays());
                                            alert("Success!!");
                                            mypopup.dialog("close");
                                        }
                                    }
                                    else {
                                        alert("Start time cannot be greater than end time.");
                                    }
                                },
                                "Delete": function () {
                                    var mypopup = $(this);
                                    var del = confirm("Do you want to delete this event?");
                                    if (del) {
                                        $.ajax({
                                            type: "GET",
                                            url: BASE_URL + "deletecalendarevent.do",
                                            data: "eventid=" + calEvent.id + "&user=" + user.getUserName()
                                        }).done(function (msg) {
                                            var resData = jQuery.parseJSON(msg);
                                            if (resData.status == "1") {
                                                calendar.fullCalendar('removeEvents', calEvent.id);
                                                mypopup.dialog("close");
                                                alert("Event Deleted");
                                            }
                                            else if (resData.status == "2")
                                                alert("You do not have permission to delete this event.");
                                            else
                                                alert("Event Cannot be deleted at the moment. Try again later.");
                                        });

                                    }

                                },
                                Cancel: function () {
                                    $(this).dialog("close");
                                }
                            },
                            close: function () {
                                $(this).dialog("close");
                            }
                        });


                    }

                });
            }
        });

    });


    var rescheduleDropEvent = function ($calendar, $event) {

        var myevent = allCalendarEvents.get($event.id);
        var before = new Date(myevent.getStart()).format("H:MM") + " - " + new Date(myevent.getEnd()).format("H:MM (ddd) dd mmm");
        var after = $event.start.format("H:MM") + " - " + $event.end.format("H:MM (ddd) dd mmm");
        var scr = EVENT_MOVE_CONFIRMATION.split("$title$").join($event.title)
                .split("$before$").join(before)
                .split("$after$").join(after)
            ;
        $("#eventconfirmation").html(scr);
        var confirmed = false;
        $("#eventconfirmation").dialog({
            autoOpen: true,
            height: 200,
            width: 350,
            modal: true,
            closeOnEscape: true,
            buttons: {
                "Confirm": function () {
                    confirmed = true;
                    var output;
                    if (Number(myevent.getRecurDays()) == 0) {
                        output = updateCalendarEvent($event.id,
                            $event.title,
                            $event.start.getTime(),
                            $event.end.getTime(),
                            myevent.getRecurDays(),
                            myevent.getEventType());
                    }
                    else if (Number(myevent.getRecurDays()) > 0) {
                        var startDate = new Date(myevent.getStart());
                        var year = startDate.getFullYear();
                        var month = startDate.getMonth();
                        var day = startDate.getDate();
                        var hours = $event.start.getHours();
                        var minutes = $event.start.getMinutes();
                        var updatedStart = new Date(year, month, day, hours, minutes, 0, 0);

                        var endDate = new Date(myevent.getEnd());
                        year = endDate.getFullYear();
                        month = endDate.getMonth();
                        day = endDate.getDate();
                        hours = $event.end.getHours();
                        minutes = $event.end.getMinutes();
                        var updatedEnd = new Date(year, month, day, hours, minutes, 0, 0);

                        output = updateCalendarEvent($event.id,
                            $event.title,
                            updatedStart.getTime(),
                            updatedEnd.getTime(),
                            myevent.getRecurDays(),
                            myevent.getEventType());


                    }
                    if (output == 1)
                        alert("Success!!");
                    else
                        alert("Request Failed");
                    $(this).dialog("close");
                },
                Cancel: function () {
                    $calendar.fullCalendar('removeEvents', myevent.getId());
                    calendarRender($calendar, myevent.getId(), myevent.getTitle(), myevent.getStart(), myevent.getEnd(), myevent.getRecurDays());
                    $(this).dialog("close");
                }
            },
            close: function () {
                if (!confirmed) {
                    $calendar.fullCalendar('removeEvents', myevent.getId());
                    calendarRender($calendar, myevent.getId(), myevent.getTitle(), myevent.getStart(), myevent.getEnd(), myevent.getRecurDays());
                }
                $(this).dialog("close");
            }
        });
    };


    var updateCalendarEvent = function ($id, $title, $start, $end, $recur, $eventType) {
        var out = 0;
        $.ajax({
            type: "GET",
            url: BASE_URL + "updatecalendar.do",
            async: false,
            data: "eventid=" + $id + "&title=" + $title + "&start=" + $start
            + "&end=" + $end + "&recur=" + $recur + "&eventtype=" + $eventType
        }).done(function (msg) {
            var resData = jQuery.parseJSON(msg);
            if (resData.status == "1") {
                var updatedRecurDays = Number($recur);
                var isRecursive = false;
                if (updatedRecurDays != 0)
                    isRecursive = true;

                new CalendarEvents($id, user.getUserName(), $title, $start,
                    $end, $eventType, isRecursive, updatedRecurDays, new Date());
                out = 1;
            }
            else if (resData.status == "2") {
                alert("Event cannot be udpated at the moment.");
                out = 2;
            }
            else if (resData.status == "3") {
                alert("You do not have permission to update this event.");
                out = 3;
            }
        });
        return out;
    };


    var calendarRender = function ($calendar, $id, $title, $start, $end, $recurdayds) {
        var days = Number($recurdayds);
        //alert(days);
        if (days > 0) {
            var start = new Date($start);
            var end = new Date($end);
            for (var i = 0; i < days; i++) {
                var newStart = new Date(start.getTime() + (i * 24 * 60 * 60 * 1000));
                var newEnd = new Date(end.getTime() + (i * 24 * 60 * 60 * 1000));
                $calendar.fullCalendar('renderEvent',
                    {
                        id: $id,
                        title: $title,
                        start: newStart,
                        end: newEnd,
                        allDay: false,
                        startEditable: true,
                        durationEditable: true,
                        editable: false
                    },
                    true
                );
            }
        }
        else {
            $calendar.fullCalendar('renderEvent',
                {
                    id: $id,
                    title: $title,
                    start: new Date($start),
                    end: new Date($end),
                    allDay: false
                },
                true
            );
        }
        $calendar.fullCalendar('unselect');
    };


    $(document).on('click', '#editprofilepic', function () {
        $("#upload").trigger('click');
    });

    $('#upload').fileupload({
        dataType: 'json',
        maxChunkSize: 20000000, // 10 MB
        done: function (e, data) {
            var jsonResponse = jQuery.parseJSON(data.jqXHR.responseText);

            //alert(jsonResponse);
            alert(jsonResponse.fn);
            var mime = jsonResponse.mime;
            var st = jsonResponse.st;
            var src = "profilepic/" + st + "/" + jsonResponse.fn;
            $("#editprofilepic").attr("src", src);
            //$("#profilepicname").val(src);
        },
        start: function (e, data) {
        },
        submit: function (e, data) {
            /*$.each(data.files, function (index, file) {
             $("#filename").html(file.name);
             });*/
        },
        progress: function (e, data) {
            /*var progress = parseInt(data.loaded / data.total * 100, 10);
             $("#msgbox").html(progress);*/
        }
    });


    $("#edit_profile_rate").val(user.getRate());
    $('#edit_profile_skills ul').tagit({
        initialTags: user.getSkills(), tagSource: function (search, showChoices) {

            $.ajax({
                type: "GET",
                url: BASE_URL + "relatedskills.do",
                data: "terms=" + search.term
            }).done(function (msg) {
                var resData = jQuery.parseJSON(msg);
                showChoices(resData.list);
            });
        },
        sortable: true, tagsChanged: function (a, b) {
        }
    });
    $('#edit_profile_companies ul').tagit({
        tagSource: availableCompanies, initialTags: user.getCompanies(), sortable: true, tagsChanged2: function (a, b) {
            //alert(a+" is added");
        }
    });

    $("#country").val(user.getCountry());
    $("#updatedcv").val(user.getCV());
};

var getTags = function (tags) {
    var skillsarray = new Array();
    for (var i in tags)
        skillsarray[i] = tags[i].value;
    return skillsarray;
}

var showChangePasswordScreen = function ($status, $authinstance, $authid, $authtoken) {
    if ($status == 1) {
        var scr = PASS_CHANGE_SCREEN.split("$authinstance$").join($authinstance).split("$authid$").join($authid).split("$authtoken$").join($authtoken);
        $("#main").html(scr);
    }
    if ($status == 0)
        $("#main").html("<h3>This link has been expired. Please request to change the password again.</h3>");
    if ($status == 2)
        $("#main").html("<h3>This is a broken link. Please check the url.</h3>");
};

var showForgotPasswordScreen = function () {
    $("#main").html(FORGOT_PASS_SCREEN);
};


var getInterviewById = function ($iid) {
    var i = postedInterviewsMap.get($iid);
    if (i != null) {
        return i;
    } else if (awardedInterviewsMap.get($iid) != null) {
        return awardedInterviewsMap.get($iid);
    } else if (InterviewsWhereIBidMap.get($iid) != null) {
        return InterviewsWhereIBidMap.get($iid);
    }
};

var getPostedBidById = function ($id) {
    return allBidsPosted.get($id);
};

var getReceivedBidById = function ($id) {
    return allBidsReceived($id);
};

var getBidById = function ($bidid) {
    if (allBidsPosted.get($bidid) != null)
        return allBidsPosted.get($bidid);
    else if (allBidsReceived.get($bidid) != null)
        return allBidsReceived.get($bidid);

};

var getBidsForInterviewId = function ($iid) {

    var allBids = new Array();
    for (var i = 0; i++ < allBidsReceived.size; allBidsReceived.next()) {
        var bid = allBidsReceived.value();
        if (bid.iid == $iid) {
            allBids.push(bid);
        }
    }
    return allBids;
};

var getMyPostedBidForInterviewId = function ($iid) {
    for (var i = 0; i++ < allBidsPosted.size; allBidsPosted.next()) {
        var bid = allBidsPosted.value();
        if (bid.iid == $iid) {
            return bid;
        }
    }
}

var populatesInterviews = function ($allInterviews) {
    var myInterviewsJSON = $allInterviews;
    for (var i = 0; i < myInterviewsJSON.ibid.length; i++) {
        var myinterview = myInterviewsJSON.ibid[i];
        var iFileName = "";
        var url = "";
        $.each(myInterviewsJSON.FILEMAP, function (id, data) {
            if (id == myinterview.id) {
                iFileName = data.original_name;
                url = data.url;
            }
        });

        var interviewObj = new Interview(myinterview.id, myinterview.title, myinterview.description,
            myinterview.interviewee, myinterview.interviewer,
            myinterview.skills, myinterview.status, myinterview.eb, myinterview.dt, iFileName, url);
        InterviewsWhereIBidMap.put(myinterview.id, interviewObj);
    }

    for (var i = 0; i < myInterviewsJSON.MY_INTERVIEW_AS_INTERVIEWER.length; i++) {
        var myinterview = myInterviewsJSON.MY_INTERVIEW_AS_INTERVIEWER[i];
        var iFileName = "";
        var url = "";
        $.each(myInterviewsJSON.FILEMAP, function (id, data) {
            if (id == myinterview.id) {
                iFileName = data.original_name;
                url = data.url;
            }
        });

        var interviewObj = new Interview(myinterview.id, myinterview.title, myinterview.description,
            myinterview.interviewee, myinterview.interviewer,
            myinterview.skills, myinterview.status, myinterview.eb, myinterview.dt, iFileName, url);
        awardedInterviewsMap.put(myinterview.id, interviewObj);
    }

    for (var i = 0; i < myInterviewsJSON.MY_INTERVIEW_AS_INTERVIEWEE.length; i++) {
        var myinterview = myInterviewsJSON.MY_INTERVIEW_AS_INTERVIEWEE[i];
        var iFileName = "";
        var url = "";
        $.each(myInterviewsJSON.FILEMAP, function (id, data) {
            if (id == myinterview.id) {
                iFileName = data.original_name;
                url = data.url;
            }
        });

        var interviewObj = new Interview(myinterview.id, myinterview.title, myinterview.description,
            myinterview.interviewee, myinterview.interviewer,
            myinterview.skills, myinterview.status, myinterview.eb, myinterview.dt, iFileName, url);
        postedInterviewsMap.put(myinterview.id, interviewObj);
    }
};

var populateBids = function ($MY_BIDS) {

    var my_bids = $MY_BIDS;
    if (user.getType() == "INTERVIEWER") {
        var bidJSON = my_bids.BIDS_POSTED;
        for (var j = 0; j < bidJSON.length; j++) {
            var bidObject = new Bid(bidJSON[j].id, bidJSON[j].bidder, bidJSON[j].iid, bidJSON[j].msg, bidJSON[j].price, bidJSON[j].status, bidJSON[j].dt);

            allBidsPosted.put(bidJSON[j].id, bidObject);
        }
    }

    bidJSON = my_bids.BIDS_RECEIVED;
    for (var j = 0; j < bidJSON.length; j++) {
        var attachment = "-";
        var attachmentName = "";
        if (bidJSON[j].bid_atch_url != null && bidJSON[j].bid_atch_name != null) {
            attachmentName = bidJSON[j].bid_atch_name;
            attachment = bidJSON[j].bid_atch_url;
        }

        var bidObject = new Bid(bidJSON[j].id, bidJSON[j].bidder, bidJSON[j].iid, bidJSON[j].msg,
            bidJSON[j].price, bidJSON[j].status, bidJSON[j].dt, bidJSON[j].reputation, bidJSON[j].bidderreviewcount);
        bidObject.setAttachment(attachment);
        bidObject.setAttachmentName(attachmentName);
        allBidsReceived.put(bidJSON[j].id, bidObject);
    }

};

function processEvents(events) {

    var parsed_event = jQuery.parseJSON(events);
    $.each(parsed_event, function (id, data) {

        var jsonData = jQuery.parseJSON(data);
        if (jsonData.type == "CHAT_OFFLINE") {
            $("#" + jsonData.data + "_status_img").html('<img src="img/red.png" height="12" width="12"/>');
        }
        else if (jsonData.type == "CHAT_ONLINE") {
            $("#" + jsonData.data + "_status_img").html('<img src="img/green.png" height="12" width="12"/>');
        }
        else if (jsonData.type == "FIRST_REQUEST_ACCEPT") {
            $("#" + jsonData.data + "_REQ_SENT").html("Accepted");
        }
        else if (jsonData.type == "FIRST_REQUEST_REJECT") {
            $("#" + jsonData.data + "_REQ_SENT").html("Rejected");
        }
        else if (jsonData.type == "NIN") {

            //alert("NIN");

            var NINHtml = '<table><tr><td><h2>$title$</h2></td></tr>'
                + '<tr><td><h3>Required Skills:$skills$</h3><td></tr>'
                + '<tr><td><a href="$iid$">more details...</a></td></tr>'
                + '</table>';
            var interviewData = jQuery.parseJSON(jsonData.data);
            var scr = NINHtml.split("$title$").join(interviewData.title).split("$skills$").join(interviewData.skills).split("$iid$").join(interviewData.iid);
            $("#mydialog").html(scr);

            $('#mydialog').attr('title', 'New Interview available');
            $("#mydialog").dialog({
                position: {
                    my: "right bottom",
                    at: "right bottom",
                    of: window
                },
                hide: "fade",
                open: function (event, ui) {
                    setTimeout(function () {
                        $('#mydialog').dialog("close")
                    }, 5000);
                }
            });

        }
    });
}

function long_polling() {
    $.ajax({
        type: "GET",
        url: BASE_URL + "live.do",
        data: "user=" + myusername
    }).done(function (msg) {
        processEvents(msg);
        long_polling();

    });
}


var transRows = '<tr><td>$date$</td><td>$type$</td><td>$from$</td><td>$receiver$</td><td>$status$</td><td>$details$</td><td>$gross$</td><td>$fee$</td><td>$net$</td><td>$balance$</td></tr>';
var FINANCE_SCREEN = '<div id="financediv"><table width="100%" border="0">'
    + '<tr>'
    + '<td width="14%"></td><td width="10%"></td><td width="8%"></td>'
    + '<td width="8%"></td><td width="13%"></td><td width="15%"></td><td width="10%"></td><td colspan="3"><h1>Balance:&nbsp;$bal$</h1></td></tr>'
    + '<tr>'
    + '<td><h2>All activities</h2></td><td colspan="5" style=\"vertical-align:top\">'
    + 'From: <input type="text" id="transfrom" size="12" style="cursor: pointer;" disabled />&nbsp;&nbsp;'
    + 'To: <input type="text" id="transto" size="12" style="cursor: pointer;" disabled />&nbsp;&nbsp;&nbsp;'
    + '<input type="button" id="showtransbutton" value="&nbsp;&nbsp;Show&nbsp;&nbsp;"/>'
    + '</td><td></td>'
    + '<td colspan="3"><h2>Download <span id="findowndloadpdf" style="cursor: pointer;">pdf</span>&nbsp;|&nbsp;'
    + '<span id="findowndloadcsv" style="cursor: pointer;">csv</span></h2></td></tr>'
    + '<tr><td><h3>Date</h3></td><td><h3>Type</h3></td><td><h3>From</h3></td><td><h3>To</h3></td><td><h3>Payment Status</h3></td>'
    + '<td><h3>Details</h3></td><td><h3>Amount</h3></td><td><h3>Fee</h3></td><td><h3>Net</h3></td><td><h3>Balance</h3></td></tr>'
    + '$transRows$'
    + '</table></div>';


$(document).on('click', '#showtransbutton', function () {
    var fromDate = $("#transfrom").datepicker("getDate");
    var toDate = $("#transto").datepicker("getDate");
    showFinanceScreen(fromDate, toDate);
});


var allRows;
var showFinanceScreen = function (fromdate, todate) {


    var $head = $("head");
    var linkElement = "<link rel='stylesheet' type='text/css' href='css/stylesheet1.css'>";
    //$head.append(linkElement);
    linkElement = "<link href='style.css' rel='stylesheet' type='text/css' />";
    $head.append(linkElement);
    linkElement = "<link rel='stylesheet' href='//code.jquery.com/ui/1.10.4/themes/smoothness/jquery-ui.css'>";
    $head.append(linkElement);
    linkElement = "<link rel='stylesheet' href='demos.css'>";
    $head.append(linkElement);

    $.get("finance.html", function (data) {
        $("#bodyhtml").html(data);
        $("#transfrom").datepicker({
            showOn: "button",
            buttonImage: "img/calendar.gif",
            buttonImageOnly: true,
            dateFormat: "d M, yy",
        });
        $("#transfrom").datepicker("setDate", fromdate);
        $("#transto").datepicker({
            showOn: "button",
            buttonImage: "img/calendar.gif",
            buttonImageOnly: true,
            dateFormat: "d M, yy",
        });
        $("#transto").datepicker("setDate", todate);
        $("#finance_bal").html(user.getBalance());

        $.loadScript(BASE_URL + 'libraries/jsPDF-master/jspdf.js', function () {
        });
        $.loadScript(BASE_URL + 'libraries/jsPDF-master/libs/Deflate/adler32cs.js', function () {
        });
        $.loadScript(BASE_URL + 'libraries/jsPDF-master/libs/FileSaver.js/FileSaver.js', function () {
        });
        //$.loadScript('http://localhost:8080/interviewbackend/libraries/jsPDF-master/jsPDF-master/libs/Blob.js/BlobBuilder.js', function(){});
        $.loadScript(BASE_URL + 'libraries/jsPDF-master/jspdf.plugin.addimage.js', function () {
        });
        $.loadScript(BASE_URL + 'libraries/jsPDF-master/jspdf.plugin.standard_fonts_metrics.js', function () {
        });
        $.loadScript(BASE_URL + 'libraries/jsPDF-master/jspdf.plugin.split_text_to_size.js', function () {
        });
        $.loadScript(BASE_URL + 'libraries/jsPDF-master/jspdf.plugin.from_html.js', function () {
        });
        $.loadScript(BASE_URL + 'libraries/jsPDF-master/examples/js/basic.js', function () {
        });
        $.loadScript(BASE_URL + 'libraries/jsPDF-master/libs/Deflate/adler32cs.js', function () {
        });


        $('#finance_export').on('change', function () {
            alert(this.value);
            if (this.value == "PDF") {
                findowndloadpdf();
            }
            if (this.value == "CSV") {
                findowndloadcsv()
            }

        });


    });
};

function findowndloadcsv() {
    var csvContent = "Date, From, To, Description, Amount, Fee, Net Amount, Net Balance\n";
    for (var i = 0; i < allRows.length; i++) {


        csvContent += new Date(Number(allRows[i].time)).format("h:MM TT - mmm d yyyy ") + ","
            + allRows[i].owner + ","
            + allRows[i].otherParty + ","
            + allRows[i].details + ","
            + allRows[i].gross + ","
            + allRows[i].fee + ","
            + allRows[i].netamount + ","
            + allRows[i].balance + ",\n"
        ;

    }

    downloadCSV(csvContent, user.getUserName() + "-transactions.csv", "text/csv");

}

function findowndloadpdf() {

    alert("PDF Called");


    //var generatestatementpdf = function (data)
    {
        var doc = new jsPDF();
        var imgData = 'data:image/jpeg;base64,/9j/4AAQSkZJRgABAQEAYABgAAD/2wBDAAIBAQIBAQICAgICAgICAwUDAwMDAwYEBAMFBwYHBwcGBwcICQsJCAgKCAcHCg0KCgsMDAwMBwkODw0MDgsMDAz/2wBDAQICAgMDAwYDAwYMCAcIDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAz/wAARCAArAKQDASIAAhEBAxEB/8QAHwAAAQUBAQEBAQEAAAAAAAAAAAECAwQFBgcICQoL/8QAtRAAAgEDAwIEAwUFBAQAAAF9AQIDAAQRBRIhMUEGE1FhByJxFDKBkaEII0KxwRVS0fAkM2JyggkKFhcYGRolJicoKSo0NTY3ODk6Q0RFRkdISUpTVFVWV1hZWmNkZWZnaGlqc3R1dnd4eXqDhIWGh4iJipKTlJWWl5iZmqKjpKWmp6ipqrKztLW2t7i5usLDxMXGx8jJytLT1NXW19jZ2uHi4+Tl5ufo6erx8vP09fb3+Pn6/8QAHwEAAwEBAQEBAQEBAQAAAAAAAAECAwQFBgcICQoL/8QAtREAAgECBAQDBAcFBAQAAQJ3AAECAxEEBSExBhJBUQdhcRMiMoEIFEKRobHBCSMzUvAVYnLRChYkNOEl8RcYGRomJygpKjU2Nzg5OkNERUZHSElKU1RVVldYWVpjZGVmZ2hpanN0dXZ3eHl6goOEhYaHiImKkpOUlZaXmJmaoqOkpaanqKmqsrO0tba3uLm6wsPExcbHyMnK0tPU1dbX2Nna4uPk5ebn6Onq8vP09fb3+Pn6/9oADAMBAAIRAxEAPwD9/KKK82179r34c+GPioPBV/4litvEpuIrT7I1pcbRLKFKKZRH5YyHXktgZ5xWtKjUqy5aUXJ76K+hFSrCnHmqNJeeh6TRWV438baV8OPCd/rut3kdhpWmRGa5uHVmEaj2UFiSSAAASSQACaxPg78evCfx+0e71Dwlqw1a0sZvs87/AGaaAxvtDYxKik8EcgYpRo1JQdRRfKt3bRerCVSCkoNq72Xf0OwooorMsKK5D46/GrSv2fPhre+KdZt9QutPsXijkjskR5iZHVBgOyjqwzz0rovDmuQ+J/D1hqVusiQajbx3MayAB1V1DAEAkZwecE1p7KfJ7W3u3tfz3t9xDqRU/Z31te3le1/vLtFcr4Y+Nvhjxj8Rda8J6bqf2nxB4eUNqFr9mlT7ODjHzsoRvvD7rHrXVUp05RtzK19fk9mOM4yvyu9tH69gorK8W+OtE8A2MV1rus6VottNIIY5r+7jto5JCCQgZyAWIBOBzwa1QQwBByDU8rte2g7q9gooopDCiivPfj/+0poX7OFpok2uWmsXaa9eixtxYQJKUcjOW3OuB9Mn0FaUqU6s1Tpq7eyIqVI04uc3ZLVnoVFFFZlhRRRQAUUUUAFfnb8f/hjJ8TP2nPjybQONT8P6Vb6zZOnDo8AtWbaRznyy+Md8V+iVeI/Dr9mfWPDH7XPj/wAd6hPpFxoHi2wWzgto5JGuVwIA3mKUCgHy26Meo/D1coxf1apUq3s+R29bxaX4HBmVD21KFO11zxv6Wkn+Z5D8VvjYn7Xfgf4M+B7KZZbnxzPDf+IVQHMUVsSJlPsZI5SP+uQ9ap/sq/FS3/Z6+CXxz8Sx2kcq6J4jmW1th8qNIzeXEnHRdzLnHYHFd3+x3+wTefs5/GXxB4k1S90y/tTHJa6ClvLI8tvC8hZjIGRQr7Ao+UsPmfn10Phf+xLfWHwu+KXhbxPf6ebbx9qkt7bTae7ytaqW3Rs4dE+ZXCnaMg4xmvXr4vBRpzw9J+47S9W5xbS9IJL5Hm0MNiXUhUqr3ovl+ShJX+cmcV4j+IHx08Ffs8WfxeuPHGj3lvKsOqTeF/7BhW3itZ3ARBOP3xwrpkZBHPznGW6L4mftYeKPiZ4t+GXg/wCHtxa+G9R+IOmR6zc6ldWy3cmlwEM5VI2+RmAilB3Dn5cFc7hTvv2UPjL4s+Edl8L9X8UeBIvA1oY7ZtRtbe5bVprWFt0SNGwEWfljBAYEbR8zYO7pvjH+xpqn/CVeAvFPw21TTdL8Q+AbKLS7eHV1d7W8tUUqqu0YLA7WkBwvzB+ChANS6uB9oufl+KXLZaKPL7vNor+9be776DjTxfs3y8yfKr3f2rq/Lrppfay2tqcl+2h4a8beE/2KPGVn408S2Hi2RdQsWsdRhsFsZniM0O5ZYk+RSH3AbScjBJzwJvgZ8cvF37R3xB0LQfBGsReG/BvgbT7SPXrmS3hkvdUl8tAYUimUvGuVZQ+1cYc5J2rXV/F34C/FH48/s4eJPDnibWPBn/CRaveW0tnFYJPDptlDE8bEeYyNKzNtY8g4JwDjpU1n9kHxL4N+K/g/xx4Bv9D07W7Kwh07xLaXc00dpq8aRohYFI2O4he6gZWNuoOYw9eh7CVKs487k2tFyp8kUm1ZLo1tZS1s0ViaNV1I1KSlyqNnq+ZrnbaTve9rPe9tLpnJfBK+l0v9uf493MD7J7fTPNjbAO1lWMg4PHUd653wD8aPjf8AE79kzU/iJD480rTR4XW5YwJodvLPq4jIdzMzKEi2KcKI0+bB3c817J4F/Zf1/wAMftEfFHxbcXmjvpvjaxNtYxxyyGeJiqjMoMYUDg/dZqz/AIO/sk+I/h7+xZ4l+HF7e6JLrmsxXqQzwTStaKZkCruYxhxgjnCH8aTxeHVNS91yUaSV0nsmpLVff/mkX9Xqus1qoudRuzto7cu3nex4l+3B461z44fsl/DPxtLqkNnZ6rdRR3WlQ2a+X9tAmU3CyMS4UbHURnIw2SSRXqP7Snx+8Yfsk/Cjw5o2oeL7PXPE3im9ki/4SK50hLdNKtQEDSfZotwdk3gjg55yrdKs+L/2Hdd8W/sT+Gfh42qaRb+JvDNyLyG4DyNZSyeZMdpbYHC7Jeuw8r0xzWn8Yv2X/Hfx6+H3hrUNY1nwvovxJ8HX73um3OlRztpzL8hVH8zMgO6NGLbSBgjac1t9ZwbcaTcfZxqT0t9l25WtL2vv5aO9kjGNHFW9pZ+0dOK3+0r3v09Ol9d7s4f9mz9srVdY/aY0zwXJ4+tfijofiK0do9SGgHRp9NuI0kkKeXtUOpVOTzyy4K7SGq/CX4t/Hf49fGDxXpOgeI9Es9C8I+JJI7u4vLWBZXtfPZFtUCwtn5I3O4qDnq/QV7z8LNJ+MV14whuvHWr+BLXR7SJ1Fj4etrh2v3YYBlkuOYwhGRs+9uIPQVjfsn/s4658CfGfxH1HV7rSrmDxhrB1CzWzlkd4o/MmbEm5FAbEi/dLDg81hUxWFi5zUI8yirbNN83lGKvy9l011ujSNDESUYOUknLXdNLlfVyk7Xtu99uhwvwn/aq8R+C/HXxl8OePNUGp3fge3m1bSpGt4bdpbVASF/dooJIeAgkE5c1w2qftO/FXw3+zH8NfE174kYav4x8RtHK5060GbIkqke3ytoB2ltwG7DjntXcftm/sLeJPjt8Tk8Q+ENX0jSDqmmjTNaS9lkT7SiurKVCRvu4AByR/q1966r9p/wDZJ1H4p+Afh/oHhafSrG18F30EpF9JJGGgijCBV2I2WwB1wPetKFfAfuqklG8nHmVlZcqaeltOZ2fn1JxNLFv2sIN2ipuLT3ckuVX68uq8tDhP2p/20L7w1+0NJ4Es/Gln8NtK0e1S4vtcfRTrE9xM6Blt1h2sAu11JOAcqfm/hbE8B/tfePPiZ+zn8Uf7I8R2t/4i+Hrx3ll4ig02OEatZGRyxa3kTaj+XG5+4OqjGQWb1L4p/sxeM9J/aMf4nfDTWPD1tq+pWgstU07XUm+x3KhAocNCC2fki+XA5TO4gla9C+GPhPxrqXhrWYviRqXh7UptZBhGn6PavHZWMOwoyK8n7yTeDuO/oSQOMVzvEYSGEjyRi5JRbva/MpJvTlu01prLlt0udHssRLFPmk1G/Tbl5f8AEknfy5r63sfM37QP7d3i3T/gn8MNV8H3wTWtZ059U1zZZxS/JDtikyrIwRDKJfmXBAXivS9Y/aI1/wCIf7XHw88L+E9XNp4cvdEXxBrKraxSNPC6l40ZnVmTICD5Sp/e9elch+zz/wAE5NX+H2peK4/FWq6Vqul6holzoejxwSyyNaxTyM7MweNQjZwflLcs349X+wz+xt4g/Z017XNZ8W6lpOq6te2lvp1k9jLJKLe2jGCpLxoR92MAAHhOtddeeWxjJ0rNx5mtN3O6S/7cVvQ5KUMc+WM762i9dlHlbl5c3vL7kfR9FFFfJn0QUUUUAFFFFABRRRQAUUUUAFFFFABRRRQAUUUUAFFFFABRRRQAUUUUAFFFFAH/2Q==';
        //doc.addImage(imgData, 'JPEG', 25, 20, 35, 13);

        doc.setFont("helvetica");
        doc.setFontType("normal");
        doc.setFontSize(11);
        doc.text(157, 25, "Bonham Street");
        doc.text(155, 30, "Dublin 8, Ireland");


        doc.setLineWidth(0.5);
        doc.line(24, 33, 185, 33);

        doc.setFontType("bold");
        doc.setFontSize(17);
        doc.text(75, 45, "Transaction Report");
        var username = "amitraj";
        doc.setFontType("normal");
        doc.setFontSize(11);
        doc.text(26, 60, "Username: " + username);
        doc.text(26, 65, "Date: " + new Date().format("d mmm, yyyy"));

        doc.text(170, 60, "Balance");
        doc.setFontType("bold");
        doc.setFontSize(17);
        doc.setTextColor(0, 153, 0);
        doc.text(170, 67, user.getBalance());


        doc.setTextColor(0, 0, 0);
        doc.setFontType("normal");
        doc.setFontSize(9);
        doc.text(179, 67, "");


        var dateX = 26;
        var FromX = 52;
        var ToX = 65;
        var DescX = 84;
        var AmountX = 128;
        var FeeX = 145;
        var NetAmntX = 157;
        var NetBalX = 172;
        doc.setFontType("bold");
        doc.text(dateX, 77, "Date");
        doc.text(FromX, 77, "From");
        doc.text(ToX, 77, "To");
        doc.text(DescX, 77, "Description");
        doc.text(AmountX, 77, "Amount");
        doc.text(FeeX, 77, "Fee");
        doc.text(NetAmntX, 77, "Net");
        doc.text(NetBalX, 77, "Balance");
        doc.setLineWidth(0.2);
        doc.line(24, 80, 185, 80);

        var tableY = 87;
        var vDiff = 6;
        doc.setFontSize(6);
        doc.setFontType("normal");
        for (var i = 0; i < allRows.length; i++) {

            doc.text(dateX, tableY, new Date(Number(allRows[i].time)).format("h:MM TT - mmm d, yyyy ") + "");
            doc.text(FromX, tableY, allRows[i].owner);
            doc.text(ToX, tableY, allRows[i].otherParty);
            doc.text(DescX, tableY, allRows[i].details);
            doc.text(AmountX, tableY, allRows[i].gross + "");
            doc.text(FeeX, tableY, allRows[i].fee + "");
            doc.text(NetAmntX, tableY, allRows[i].netamount + "");
            doc.text(NetBalX, tableY, allRows[i].balance + "");
            tableY += vDiff;

            if (tableY > 257) {
                doc.setLineWidth(0.2);
                doc.line(24, 257, 185, 257);

                doc.addPage();
                doc.text(dateX, 30, "Date");
                doc.text(FromX, 30, "From");
                doc.text(ToX, 30, "To");
                doc.text(DescX, 30, "Description");
                doc.text(AmountX, 30, "Amount");
                doc.text(FeeX, 30, "Fee");
                doc.text(NetAmntX, 30, "Net");
                doc.text(NetBalX, 30, "Balance");
                doc.setLineWidth(0.2);
                doc.line(24, 33, 185, 33);
                tableY = 35;
                tableY += vDiff;
            }
        }

        doc.save('transactionreport.pdf');
        //doc.output('datauri');
    }
    ;
};


var instantiateFirstRequests = function ($REQ_SENT, $REQ_RECEIVED) {
    var iREQ_SENT = jQuery.parseJSON($REQ_SENT);
    $.each(iREQ_SENT, function (id, data) {
        already_requested_user.push(data.interviewer);
        if (data.status == "1") {
            var user = new Object();
            user.name = data.interviewer;
            user.online = data.online;
            users_available_for_message.push(user);
        }
    });

    var iREQ_RECEIVED = jQuery.parseJSON($REQ_RECEIVED);
    $.each(iREQ_RECEIVED, function (id, data) {
        if (data.status == "1") {
            var user = new Object();
            user.name = data.interviewee;
            user.online = data.online;

            users_available_for_message.push(user);
        }
    });
};

var populateAvailableUsers = function ($AVAILABLE_USERS) {
    var AVAILABLE_USERS_list = ($AVAILABLE_USERS);
    $.each(AVAILABLE_USERS_list, function (userid, status) {
        var user = new Object();
        user.name = userid;
        user.online = status;
        users_available_for_message.push(user);
    });
};


var getBIDActions = function () {
    return "<option value=\"0\">Select Action</option><option value=\"3\">Request Escrow</option><option value=\"1\">Withdraw</option><option value=\"2\">Delete</option>";
};

var getInterviewerActions = function () {
    return "<option value=\"0\">Select</option>"
        + "<option value=\"5\">Request Payment</option>"
        + "<option value=\"6\">Rate interviewee</option>"
        + "<option value=\"4\">Withdraw</option>"
        + "<option value=\"7\">Raise dispute</option>";
};

var getInterviewActions = function () {
    return "<option value=\"1\">Action</option>"
        + "<option value=\"6\">Delete</option>"
        + "<option value=\"7\">Repost</option>"
        + "<option value=\"8\">Cancel</option>"
        ;
};

var getIntervieweeActions = function () {
    return "<option value=\"1\">Select Action</option>"
        + "<option value=\"2\">Escrow deposit</option>"
        + "<option value=\"3\">Release Funds</option>"
        + "<option value=\"5\">Rate interviewer</option>"
        + "<option value=\"9\">Raise dispute</option>";
};


$(document).on('change', '.statusaction', function () {
    var answer = confirm('Are you sure you want to change the status?');
    if (answer) {
        var param = "iid=" + $(this).attr("alt") + "&bid=" + $(this).attr("bidvalue") + "&status=" + $(this).val();
        $.ajax({
            type: "GET",
            url: BASE_URL + "statuschange.do",
            data: param
        }).done(function (res) {
            var resData = jQuery.parseJSON(res);
            populatesInterviews(resData.MY_INTERVIEW);
            //$("#MY_INTERVIEW").val(resData.MY_INTERVIEW);
            populateBids(resData.MY_BIDS);
            //$("#MY_BIDS").val(resData.MY_BIDS);				
            alert(resData.status);
        });
    } else {

    }
});

var renderIScheduleScreen = function ($iid) {
    var interview = getInterviewById($iid);
    if (interview.getStatus() == 1) {
        var INTERVIEW_TIME_OPTIONS = '<table border=\"1\">'
                + '<tr>'
                + '<td colspan="4"><div id="schedulemessage"><h2>Interview Not yet Scheduled</h2></div></td>'
                + '</tr>'
                + '<tr>'
                + '<td width="40px">Day</td>'
                + '<td><input type="text" id="idateoption1" size="18" style="cursor: pointer;" disabled /></td>'
                + '<td><input type="text" id="idateoption2" size="18" style="cursor: pointer;" disabled ></td>'
                + '<td><input type="text" id="idateoption3" size="18" style="cursor: pointer;" disabled ></td>'
                + '</tr>'
                + '<tr>'
                + '<td>Time</td>'
                + '<td><input type="text" class="ui-timepicker-input" id="itimeoption1" size="18" style="cursor: pointer;" ></td>'
                + '<td><input type="text" class="ui-timepicker-input" id="itimeoption2" size="18" style="cursor: pointer;" ></td>'
                + '<td><input type="text" class="ui-timepicker-input" id="itimeoption3" size="18" style="cursor: pointer;" ></td>'
                + '</tr>'
                + '<tr>'
                + '<td id="topName"></td>'
                + '<td><center><div id="top1"></div></center></td>'
                + '<td><center><div id="top2"></div></center></td>'
                + '<td><center><div id="top3"></div></center></td></tr>'
                + '<tr>'
                + '<td id="botName"></td>'
                + '<td id="bot1"></td>'
                + '<td id="bot2"></td>'
                + '<td id="bot3"></td></tr>'
                + '<tr>'
                + '<td></td>'
                + '<td colspan="3"><input type="button" value="Save" id="saveischedule">'
                + '<span id="editbuttonplace"></span>'
                + '</td></tr>'
                + '</table>'
            ;


        $("#itimeoptiondiv").html(INTERVIEW_TIME_OPTIONS);

        $("#topName").html(interview.getInterviewee());
        $("#botName").html(interview.getInterviewer());
        $("#idateoption1").datepicker({
            showOn: "button",
            buttonImage: "img/calendar.gif",
            buttonImageOnly: true,
            dateFormat: "(D) d M, yy",
            minDate: null
        });
        $("#idateoption2").datepicker({
            showOn: "button",
            buttonImage: "img/calendar.gif",
            buttonImageOnly: true,
            dateFormat: "(D) d M, yy",
            minDate: null
        });
        $("#idateoption3").datepicker({
            showOn: "button",
            buttonImage: "img/calendar.gif",
            buttonImageOnly: true,
            dateFormat: "(D) d M, yy",
            minDate: null
        });
        $('#itimeoption1').timepicker({'scrollDefaultNow': true, 'timeFormat': 'h:i A', 'disableTextInput': true});
        $('#itimeoption2').timepicker({'scrollDefaultNow': true, 'timeFormat': 'h:i A', 'disableTextInput': true});
        $('#itimeoption3').timepicker({'scrollDefaultNow': true, 'timeFormat': 'h:i A', 'disableTextInput': true});

        var intervieweeCBDisabled = "";
        var interviewerCBDisabled = "";
        if (user.getUserName() == interview.getInterviewee()) {
            interviewerCBDisabled = "disabled";
            $("#idateoption1").datepicker("option", "disabled", true);
            $("#idateoption2").datepicker("option", "disabled", true);
            $("#idateoption3").datepicker("option", "disabled", true);

        }
        if (user.getUserName() == interview.getInterviewer()) {
            intervieweeCBDisabled = "disabled";
        }

        $("#top1").html('<input type="checkbox" id="cbtop1" ' + intervieweeCBDisabled + '/>');
        $("#top2").html('<input type="checkbox" id="cbtop2" ' + intervieweeCBDisabled + '/>');
        $("#top3").html('<input type="checkbox" id="cbtop3" ' + intervieweeCBDisabled + '/>');

        $("#bot1").html('<center><input type="checkbox" id="cbbot1" name="grp1" disabled/></center>');
        $("#bot2").html('<center><input type="checkbox" id="cbbot2" name="grp1" disabled/></center>');
        $("#bot3").html('<center><input type="checkbox" id="cbbot3" name="grp1" disabled/></center>');

        $("input:checkbox").change(function () {
            var group = ":checkbox[name='" + $(this).attr("name") + "']";
            if ($(this).is(':checked')) {
                $(group).not($(this)).attr("checked", false);
            }
        });


        var oth_option1 = false;
        var oth_option2 = false;
        var oth_option3 = false;
        var oth_opted = false;
        var finaldate = 0;


        var intervieweeAnswered = false;
        $.ajax({
            type: "POST",
            async: false,
            url: BASE_URL + "getinterviewschedule.do",
            data: "iid=" + $iid
        }).done(function (res) {
            //alert(res);
            var resData = jQuery.parseJSON(res);

            if (resData.status == 1) {
                intervieweeAnswered = Boolean(resData.oth_opted);
                var date1 = new Date(Number(resData.date1));
                var date2 = new Date(Number(resData.date2));
                var date3 = new Date(Number(resData.date3));

                $("#idateoption1").datepicker("setDate", date1);
                $("#idateoption2").datepicker("setDate", date2);
                $("#idateoption3").datepicker("setDate", date3);
                $("#idateoption1").datepicker("option", "disabled", true);
                $("#idateoption2").datepicker("option", "disabled", true);
                $("#idateoption3").datepicker("option", "disabled", true);


                $('#itimeoption1').timepicker('setTime', date1);
                $('#itimeoption2').timepicker('setTime', date2);
                $('#itimeoption3').timepicker('setTime', date3);
                $('#itimeoption1').attr('disabled', true);
                $('#itimeoption2').attr('disabled', true);
                $('#itimeoption3').attr('disabled', true);
                oth_opted = Boolean(resData.oth_opted);
                if (oth_opted == true) {
                    if (user.getUserName() == interview.getInterviewer()) {
                        $("#cbbot1").attr("disabled", false);
                        $("#cbbot2").attr("disabled", false);
                        $("#cbbot3").attr("disabled", false);
                        $("#saveischedule").attr("value", "Confirm");
                    }
                    oth_option1 = Boolean(resData.oth_option1);
                    if (oth_option1 == true)
                        $("#cbtop1").attr("checked", true);

                    oth_option2 = Boolean(resData.oth_option2);
                    if (oth_option2 == true)
                        $("#cbtop2").attr("checked", true);

                    oth_option3 = Boolean(resData.oth_option3);
                    if (oth_option3 == true)
                        $("#cbtop3").attr("checked", true);

                    if (user.getUserName() == interview.getInterviewee()) {
                        $("#cbtop1").attr("disabled", true);
                        $("#cbtop2").attr("disabled", true);
                        $("#cbtop3").attr("disabled", true);
                    }

                }
                if (user.getUserName() == interview.getInterviewer())
                    $("#editbuttonplace").html('<input type="button" value="Edit" id="editschedule"/>');

                if (Number(resData.final_option) == 1 || Number(resData.final_option) == 2 || Number(resData.final_option) == 3) {
                    $("#cbbot1").attr("disabled", true);
                    $("#cbbot2").attr("disabled", true);
                    $("#cbbot3").attr("disabled", true);
                    $("#saveischedule").attr("disabled", true);
                    $("#schedulemessage").html("<h2>Interview Scheduled: " + new Date(Number(resData.finaldate)).format("h:MM TT, dd mmm  yyyy (ddd)") + "</h2>");
                }


                if (Number(resData.final_option) == 1)
                    $("#cbbot1").attr("checked", true);
                if (Number(resData.final_option) == 2)
                    $("#cbbot2").attr("checked", true);
                if (Number(resData.final_option) == 3)
                    $("#cbbot3").attr("checked", true);


            }
            else {


            }
        });


        $(document).on('click', '#editschedule', function () {
            $("#idateoption1").datepicker("option", "disabled", false);
            $("#idateoption2").datepicker("option", "disabled", false);
            $("#idateoption3").datepicker("option", "disabled", false);
            $('#itimeoption1').attr('disabled', false);
            $('#itimeoption2').attr('disabled', false);
            $('#itimeoption3').attr('disabled', false);


        });


        $(document).on('click', '#saveischedule', function () {

            var final_option = 0;
            var sender = "";


            if ($("#idateoption1").val() == "" || $("#idateoption2").val() == "" || $("#idateoption3").val() == "") {
                alert("Please provide all the three date options");
                return;
            }
            if ($('#itimeoption1').val() == "" || $('#itimeoption2').val() == "" || $('#itimeoption3').val() == "") {
                alert("Please specify the date.");
                return;
            }

            var date1 = new Date($("#idateoption1").datepicker("getDate"));
            var timeDate1 = new Date($('#itimeoption1').timepicker('getTime'));
            date1.setHours(timeDate1.getHours());
            date1.setMinutes(timeDate1.getMinutes());

            var date2 = new Date($("#idateoption2").datepicker("getDate"));
            var timeDate2 = new Date($('#itimeoption2').timepicker('getTime'));
            date2.setHours(timeDate2.getHours());
            date2.setMinutes(timeDate2.getMinutes());

            var date3 = new Date($("#idateoption3").datepicker("getDate"));
            var timeDate3 = new Date($('#itimeoption3').timepicker('getTime'));
            date3.setHours(timeDate3.getHours());
            date3.setMinutes(timeDate3.getMinutes());

            if ($('#cbtop1').is(':checked') == true || $('#cbtop2').is(':checked') == true || $('#cbtop3').is(':checked') == true)
                oth_opted = true;

            if ($('#cbtop1').is(':checked') == true)
                oth_option1 = true;
            if ($('#cbtop2').is(':checked') == true)
                oth_option2 = true;
            if ($('#cbtop3').is(':checked') == true)
                oth_option3 = true;


            if (user.getUserName() == interview.getInterviewee()) {
                sender = "INTERVIEWEE";

                if (oth_opted == false) {
                    alert("You are required to select at least one option.");
                    return;
                }
                var param = "iid=" + $iid + "&oth_opted=" + oth_opted
                    + "&oth_option1=" + oth_option1 + "&oth_option2=" + oth_option2
                    + "&oth_option3=" + oth_option3;

                $.ajax({
                    type: "POST",
                    url: BASE_URL + "intervieweeopted.do",
                    data: param
                }).done(function (res) {
                    var resData = jQuery.parseJSON(res);
                    alert(resData.status);
                });

            }
            if (user.getUserName() == interview.getInterviewer()) {
                sender = "INTERVIEWER";
                if ($('#cbbot1').is(':checked') == true) {
                    if ($('#cbtop1').is(':checked') == true) {
                        finaldate = date1;
                        final_option = 1;
                    }
                    else {
                        alert("You cannot confirm a date which interviewee has not choosen. Check option 1. ");
                        return;
                    }
                }
                else if ($('#cbbot2').is(':checked') == true) {
                    if ($('#cbtop2').is(':checked') == true) {
                        finaldate = date2;
                        final_option = 2;
                    }
                    else {
                        alert("You cannot confirm a date which interviewee has not choosen. Check option 2. ");
                        return;
                    }
                }
                else if ($('#cbbot3').is(':checked') == true) {
                    if ($('#cbtop3').is(':checked') == true) {
                        finaldate = date3;
                        final_option = 3;
                    }
                    else {
                        alert("You cannot confirm a date which interviewee has not choosen. Check option 3. ");
                        return;
                    }
                }
                var param = "user=" + user.getUserName() + "&date1=" + date1.getTime()
                    + "&date2=" + date2.getTime() + "&date3=" + date3.getTime()
                    + "&iid=" + $iid + "&sender=" + sender + "&oth_opted=" + oth_opted
                    + "&oth_option1=" + oth_option1 + "&oth_option2=" + oth_option2
                    + "&oth_option3=" + oth_option3 + "&finaldate=" + (finaldate == 0 ? 0 : finaldate.getTime()) + "&final_option=" + final_option;

                $.ajax({
                    type: "POST",
                    url: BASE_URL + "saveinterviewschedule.do",
                    data: param
                }).done(function (res) {
                    var resData = jQuery.parseJSON(res);
                    alert(resData.status);
                });

            }
        });


    }
};


$(document).on('click', '.mybiddetails', function () {
    var iid = $(this).attr('alt');
    var type = $(this).attr('showtype');
    var memberHTML = "";
    var found = false;
    var bid_id;
    if (type == "bid") {
        var ttable = "<table width=\"100%\" ><tr><td><h3>Title</h3></td><td><h3>Candidate</h3></td></td><td><h3>Message</h3></td><td><h3>Price</h3></td><td><h3>Date</h3></td><td><h3>Action</h3></td></tr>";
        bid_id = $(this).attr('bid_id');
        var bid_posted = getPostedBidById(bid_id);
        var interview = getInterviewById(bid_posted.getIid());
        var action = "<select class=\"statusaction\" alt=\"" + bid_posted.getIid() + "\" bidvalue=\"" + bid_posted.getId() + "\">" + getBIDActions() + "</select>"
        ttable = ttable + "<tr><td><div class=\"mybiddetails\" showtype=\"bid\" style=\"cursor: pointer; \" "
            + "alt=\"" + interview.getId() + "\">" + interview.getTitle() + "</div></td><td>" + interview.getInterviewee() + "</td>"
            + "<td>" + bid_posted.getMessage() + "</td>"
            + "<td>" + bid_posted.getPrice() + "</td>"
            + "<td>" + prettyDate(new Date(interview.getDate())) + "</td>"
            + "<td>" + action + "</td>"
            + "</tr>";
        ttable = ttable + "</table>";
        memberHTML = memberHTML + ttable;
    }

    if (type == "interviewer") {
        var interview = getInterviewById(iid);
        memberHTML = memberHTML + "<div id=\"detailed_interview_home\">" + detailedInterviewScreen(iid) + "</div>";
        memberHTML = memberHTML + "<h2>My Bid Details</h2>";

        var ttable = "<table width=\"100%\"><tr><td><h3>Title</h3></td><td><h3>Candidate</h3></td></td><td><h3>Message</h3></td><td><h3>Price</h3></td><td><h3>Date</h3></td><td><h3>Action</h3></td></tr>";

        var thisBid = getMyPostedBidForInterviewId(iid);
        var action = "<select class=\"statusaction\" alt=\"" + thisBid.getIid() + "\" bidvalue=\"" + thisBid.getId() + "\">" + getBIDActions() + "</select>"
        ttable = ttable + "<tr><td><div class=\"mybiddetails\" showtype=\"bid\" style=\"cursor: pointer; \""
            + "alt=\"" + interview.getId() + "\">" + interview.getTitle() + "</div></td><td>" + interview.getInterviewee() + "</td>"
            + "<td>" + thisBid.getMessage() + "</td>"
            + "<td>" + thisBid.getPrice() + "</td>"
            + "<td>" + prettyDate(new Date(interview.getDate())) + "</td>"
            + "<td>" + action + "</td>"
            + "</tr>";
        ttable = ttable + "</table>";
        memberHTML = memberHTML + ttable;
    }
    $("#main").html(memberHTML);
    renderIScheduleScreen(iid);
});


$(document).on('click', '#rateuser', function () {
    var iid = $(this).attr('alt');
    alert("here" + iid);
    showRatingScreen(iid);
});

var showRatingScreen = function ($iid) {
    $.get("ratescreen.html", function (data) {
        $("#bodyhtml").html(data);
    });
};

var detailedInterviewScreen = function ($iid) {

    var interview = getInterviewById($iid);
    var ratingLink = "";
    if (getInterviewStatusLiteral(interview.getStatus()) == "COMPLETED") {
        ratingLink = '<div id="rateuser" alt="' + $iid + '" style="cursor: pointer;">Rate User</div>';
    }

    var scr = DETAILED_INTERVIEW_SCREEN.split("$ititle$").join(interview.getTitle())
            .split("$interviewee$").join(showUsernameLink(interview.getInterviewee()))
            .split("$skills$").join(interview.getSkills())
            .split("$desc$").join(interview.getDescription())
            .split("$eb$").join(interview.getEB())
            .split("$status$").join(getInterviewStatusLiteral(interview.getStatus()))
            .split("$ratingLink$").join(ratingLink)
            .split("$date$").join(prettyDate(new Date(Number(interview.getDate()))))
            .split("$attachment$").join('<a href="' + interview.getAttachmentURL() + '">' + interview.getAttachmentName() + '</a>')
        ;

    return scr;

};

function bidHTMLView(iid) {
    var bidsHTML = "<table width=\"100%\"><tr><td><h3>Bidder</h3></td><td><h3>Message</h3></td><td><h3>Price</h3></td><td><h3>Attachment</h3></td><td><h3>Reputation</h3></td><td><h3>Date</h3></td><td><h3>Status</h3></td></tr>";
    var accepted = false;
    var bidJSON = getBidsForInterviewId(iid);
    for (var j = 0; j < bidJSON.length; j++) {
        if (bidJSON[j].getIid() == iid) {
            if (bidJSON[j].getStatus() == 1)
                accepted = true;
        }
    }
    for (var j = 0; j < bidJSON.length; j++) {
        if (bidJSON[j].getIid() == iid) {
            var action = AWARD_INTERVIEW.split("$iid$").join(bidJSON[j].getIid()).split("$bid_id$").join(bidJSON[j].getId());
            if (accepted) {
                if (bidJSON[j].getStatus() == 2)
                    action = "REJECTED";
                if (bidJSON[j].getStatus() == 1)
                    action = "ACCEPTED";
            }
            var rep = '(' + bidJSON[j].getBidderReviewCount() + ')&nbsp;&nbsp;<div class="bidder_rating" data-average="' + bidJSON[j].getReputation() + '" '
                + 'data-id="bidder_rating_' + bidJSON[j].getBidder() + '"  style=\"display:inline-block;vertical-align:middle;\" ></div>&nbsp;&nbsp;(' + bidJSON[j].getReputation() + '/10)';
            bidsHTML = bidsHTML + "<tr><td>" + bidJSON[j].getBidder() + "</td>"
                + "<td>" + bidJSON[j].getMessage() + "</td><td>" + bidJSON[j].getPrice() + "</td><td><a href=\"" + bidJSON[j].getAttachment() + "\">" + bidJSON[j].getAttachmentName() + "</a></td>"
                + "<td>" + rep + "</td><td>" + prettyDate(new Date(Number(bidJSON[j].getDate()))) + "</td><td>" + action + "</td></tr>";

        }
    }
    bidsHTML = bidsHTML + "</table>";
    return bidsHTML;
}


var showInterviewDetails = function (iid) {


    var $head = $("head");

    var linkElement = "<link rel='stylesheet' type='text/css' href='css/stylesheet1.css'>";
    $head.append(linkElement);


    $.get("interview.html", function (data) {
        $("#bodyhtml").html(data);

        var interview = getInterviewById(iid);

        if (interview.getInterviewer() == user.getUserName()) {
            $("#interview_bidstatistics").hide();
            $("#interview_allbidsview").hide();
        }

        $("#interview_title").html(interview.getTitle());
        $("#interview_totalbids").html(interview.getBidCount());
        $("#interview_postedby").html(interview.getInterviewee());
        $("#interview_skills").append("<li>" + interview.getSkills() + "</li>");
        $("#interview_idesc").append(interview.getDescription());
        $("#interview_postdate").html("On " + prettyDate(new Date(Number(interview.getDate()))));
        $("#interview_iattachment").html('<a href="' + interview.getAttachmentURL() + '">' + interview.getAttachmentName() + '</a>');
        $("#interview_status").html(getInterviewStatusLiteral(interview.getStatus()));
        $.ajax({
            type: "GET",
            url: BASE_URL + "userpicurl.do",
            data: "username=" + interview.getInterviewee(),
        }).done(function (res) {


            $("#interview_postedbyface").html('<img width="168px" height="170px" class="face" src="' + BASE_URL + '' + res + '" >');
        });
        $.ajax({
            type: "GET",
            url: BASE_URL + "isuseronline.do",
            data: "username=" + interview.getInterviewee(),
        }).done(function (res) {

            if (res == true || res == "true")
                $("#interview_online").html('<img src="images2/online.png" alt="online" style="vertical-align: middle"/>Online');
            else
                $("#interview_online").html('<img src="images2/offline.png" alt="online" style="vertical-align: middle"/>Offline');
        });

        var bidJSON = getBidsForInterviewId(iid);

        var accepted = false;
        for (var n = 0; n < bidJSON.length; n++) {
            if (bidJSON[n].getIid() == iid) {
                if (bidJSON[n].getStatus() == 1)
                    accepted = true;
            }
        }
        for (var j = 0; j < bidJSON.length; j++) {
            if (bidJSON[j].getIid() == iid) {
                var picurl = "";
                $.ajax({
                    type: "GET",
                    url: BASE_URL + "userpicurl.do",
                    data: "username=" + bidJSON[j].getBidder(),
                    async: false
                }).done(function (res) {
                    picurl = res;
                });
                var onOrOff = "offline";
                $.ajax({
                    type: "GET",
                    url: BASE_URL + "isuseronline.do",
                    data: "username=" + bidJSON[j].getBidder(),
                    async: false
                }).done(function (res) {
                    if (res == true || res == "true")
                        onOrOff = "online";
                    else
                        onOrOff = "offline";
                });


                var action = '<button alt="' + iid + '" bidid="' + bidJSON[j].getId() + '" type="submit" class="awardinterview">Award Interview</button>';
                if (accepted) {
                    if (bidJSON[j].getStatus() == 2)
                        action = "REJECTED";
                    if (bidJSON[j].getStatus() == 1)
                        action = '<select class="callintervieweeactions" alt="' + iid + '">' + getIntervieweeActions() + '</select>';
                }


                var bidbody = '<tr>' +
                    '<td class="big">' +
                    '	<div class="profile">' +
                    '  <img src="' + BASE_URL + '' + picurl + '" />' +
                    '  <p>' +
                    '  <strong>' +
                    '  <span class="online">' +
                    '  	<img src="images2/' + onOrOff + '.png" alt="online" />' +
                    '  </span>' + bidJSON[j].getBidder() + ' </strong>             ' +
                    '  </p><span class="flag"><img src="images2/flag.png" alt="flag" /></span>' +
                    '  <div id="rateClip4" style="overflow: auto; width: 110px; float:left"></div>' +
                    '  </div>' +
                    '</td>' +
                    '<td class="big">' +
                    '<p class="table_desc">' + bidJSON[j].getMessage() + '<a href="' + bidJSON[j].getAttachment() + '">' +
                    bidJSON[j].getAttachmentName() + '</a></p> </td>' +
                    ' <td class="small"><p>' + bidJSON[j].getPrice() + '</p></td>' +
                    ' <td class="small"><p>' + prettyDate(new Date(Number(bidJSON[j].getDate()))) + '</p></td>' +
                    ' <td class="small"><p>' + action + '</p></td>' +
                    '    </tr><hr>';


                $("#interview_bidbody").append(bidbody);
            }
        }


    });
    /*
     //var iid = $(this).attr('alt');
     var memberHTML = "";
     var found = false;


     memberHTML = memberHTML + "<table width=\"100%\" border=\"1\"><tr>"
     +"<td><div id=\"detailed_interview_home\">"+detailedInterviewScreen(iid)+"</div></td>"
     +"</tr></table>";
     memberHTML = memberHTML + "<div> <h2>Selected Interviewer</h2> </div>"
     memberHTML = memberHTML + "<div id=\"selected_interviewer\">"+selectedInterviewerHTML(iid)+"</div>";
     memberHTML = memberHTML + "<div> <h2>Bids History</h2> </div>"
     memberHTML = memberHTML + "<div id=\"bid_history_div\">"+bidHTMLView(iid)+"</div>";
     $("#main").html(memberHTML);
     renderRatingSreen();
     renderIScheduleScreen(iid);
     */
};

var interviewScheduleGUI = function ($iid) {


};


$(document).on('change', '.callintervieweeactions', function () {
    var iid = $(this).attr("alt");
    if ($(this).val() == "1") {
        //Edit
    }
    else if ($(this).val() == "2") {
        //Escrow deposit
        var answer = confirm('Are you sure you want to deposit into escrow account?');
        if (answer) {
            window.location = BASE_URL + "welcome.do#finances|escrowfunds"
        }
        else {
        }
    }
    else if ($(this).val() == "3") {
        //RELEASE FUNDS
        var answer = confirm('Are you sure you want to release the funds. These funds will be release from your account and will be deposited into the receivers account.');
        if (answer) {
            release_funds(iid);
        }
        else {
        }
    }
    else if ($(this).val() == "4") {
        //Complete
        completeInterview(iid);
    }
    else if ($(this).val() == "5") {
        //Rate interviewer
        window.location = BASE_URL + "welcome.do#rateuser|" + iid;
        //showRatingScreen(iid);
    }
    else if ($(this).val() == "6") {
        //Delete
        deleteInterview(iid);
    }
    else if ($(this).val() == "7") {
        //Repost
        repostInterview(iid);
    }
    else if ($(this).val() == "8") {
        //Cancel
        cancelInterview(iid);
    }
    else if ($(this).val() == "9") {
        //Raise dispute
    }

});

var cancelInterview = function ($iid) {

    var answer = confirm('Are you sure you want to cancel this interview? After cancelling, you will not be able to receive bids.');
    if (answer) {
        var param = "iid=" + $iid;
        $.ajax({
            type: "GET",
            url: BASE_URL + "cancelinterview.do",
            data: param
        }).done(function (res) {
            var resData = jQuery.parseJSON(res);
            if (resData.code == 9) {
                var istatus = resData.istatus;
                var interview = getInterviewById($iid);
                interview.setStatus(istatus);
                alert(resData.message);
            }
            else {
                alert("Cancel operation failed:" + resData.message);
            }
        });
    }
}

var repostInterview = function ($iid) {
    var answer = confirm('Are you sure you want to repost this interview?');
    if (answer) {
        var interview = getInterviewById($iid);
        postInterview(interview.getTitle() + " - (repost)", interview.getInterviewer, interview.getSkills(), interview.getDescription());
    }

};


var deleteInterview = function ($iid) {
    var answer = confirm('Are you sure you want to delete this interview?');
    if (answer) {
        var param = "iid=" + $iid;
        $.ajax({
            type: "GET",
            url: BASE_URL + "deleteinterview.do",
            data: param
        }).done(function (res) {
            var resData = jQuery.parseJSON(res);
            if (resData.code == 0) {


                if (postedInterviewsMap.get($iid) != null) {
                    postedInterviewsMap.remove($iid);
                }
                else if (awardedInterviewsMap.get($iid) != null) {
                    awardedInterviewsMap.remove($iid);
                }
                else if (InterviewsWhereIBidMap.get($iid) != null) {
                    InterviewsWhereIBidMap.remove($iid);
                }
                alert("Interview delted successfully");
            }
            else {
                alert("Interview deletion failed");
            }
        });
    }
};


var completeInterview = function ($iid) {

    var param = "iid=" + $iid;
    $.ajax({
        type: "GET",
        url: BASE_URL + "completeinterview.do",
        data: param
    }).done(function (res) {
        var resData = jQuery.parseJSON(res);
        if (resData.code == 21) {
            var interview = getInterviewById($iid);
            interview.setStatus(resData.status);
            alert(resData.message);
            var scr = detailedInterviewScreen($iid);
            $("#detailed_interview_home").html(scr);
        }
        else
            alert(resData.message);
    });

};

/*
 var RELEASE_FUNDS_SCREEN = '<div> <table> '
 +'<tr><td><h3>Interview Name:</h3></td><td><h3 id="relName">$iname$</h3></td></tr>'
 +'<tr><td><h3>Interviewer Name:</h3></td><td><h3 id="relRecName">$iiname$</h3></td></tr>'
 +'<tr><td><h3>Escrow Balance:</h3></td><td><h3 id="relSenderBal">$balance$</h3></td></tr>'
 +'<tr><td><h3>Amount:</h3></td><td><h3><input id="relAmount" type="text" /></h3></td></tr>'
 +'<tr><td></td><td><input type="button" id="rel_dep" value="Release Funds"/></td></tr>'
 +'<tr><td></td><td><h3><input id="reliid" type="text" value="$iid$" hidden/></h3></td></tr>'
 +'</table></div>';


 var release_funds = function($iid){
 var relHTML
 var interview = getInterviewById($iid);
 relHTML  =	RELEASE_FUNDS_SCREEN.split("$iname$").join(interview.getTitle())
 .split("$iiname$").join(showUsernameLink(interview.getInterviewer()))
 .split("$balance$").join(interview.getEB())
 .split("$iid$").join($iid)
 ;
 $("#main").html(relHTML);

 };
 */

/*
 var ESCROW_DEPOSIT_SCREEN = '<div id="escrow"> <table> '
 +'<tr><td><h3>Interview Name:</h3></td><td><h3 id="escName">$iname$</h3></td></tr>'
 +'<tr><td><h3>Interviewer Name:</h3></td><td><h3 id="escRecName">$iiname$</h3></td></tr>'
 +'<tr><td><h3>Your Balance:</h3></td><td><h3 id="escSenderBal">$balance$</h3></td></tr>'
 +'<tr><td><h3>Amount:</h3></td><td><h3><input id="escAmount" type="text" /></h3></td></tr>'
 +'<tr><td></td><td><input type="button" id="esc_dep" value="Deposit"/></td></tr>'
 +'<tr><td></td><td><h3><input id="esciid" type="text" value="$iid$" hidden/></h3></td></tr>'
 +'</table></div>';

 */


$(document).on('click', '.awardinterview', function () {
    var iid = $(this).attr('alt');
    var thiselement = $(this);
    var bidid = $(this).attr("bidid");
    var answer = confirm('Are you sure you want to award this interview?');
    if (answer) {
        var param = "iid=" + $(this).attr("alt") + "&bid=" + $(this).attr("bidid");
        $.ajax({
            type: "GET",
            url: BASE_URL + "awardinterview.do",
            data: param
        }).done(function (res) {
            console.log("After Interview awarded:" + res);
            $(thiselement).parent().html('<span class="label label-success">Accepted</span>');
            $(".awardinterview").parent().html('<span class="label label-warning">Rejected</span>');

            var resData = jQuery.parseJSON(res);
            //populatesInterviews(resData.MY_INTERVIEW);
            //populateBids(resData.MY_BIDS);
            //$("#MY_INTERVIEW").val(resData.MY_INTERVIEW);
            //$("#MY_BIDS").val(resData.MY_BIDS);


            // $("#bid_history_div").html(bidHTMLView(iid));
            // $("#selected_interviewer").html(selectedInterviewerHTML(iid));
            var interview = getInterviewById(iid);
            interview.setStatus(1);
            $("#interviewee_interview_status").html(getInterviewStatusLiteral(interview.getStatus()));
            $("#escrow_screen_totalreleased").html("$0");


            postedInterviewsMap.put(iid, interview);
            //renderRatingSreen();

            var currbid = getBidById(bidid);
            $("#currentinterviewdata").attr("iid", iid);
            $("#currentinterviewdata").attr("interviewer", currbid.getBidder());

            $("#escrow_screen_totalbudget").html(currbid.getPrice());
            interview.setInterviewer(currbid.getBidder());

            //var scr = detailedInterviewScreen(iid);
            //$("#detailed_interview_home").html(scr);


        });
    }
    else {
        //alert('cancel');
    }


});


var postInterview = function ($title, $industry, $skills, $description, $budget, $exp, $file) {
    var param = "title=" + $title +
        "&industry=" + $industry +
        "&skills=" + $skills +
        "&description=" + $description +
        "&budget=" + $budget +
        "&experience=" + $exp +
        "&ifile=" + $file;

    $.ajax({
        type: "POST",
        url: BASE_URL + "postinterview.do",
        data: param,
        async: false
    }).done(function (res) {
        var resData = jQuery.parseJSON(res);
        if (resData.status == 1) {
            $("#interviewee_publishresponse").html('<div id="success-alert" class="alert alert-success alert-dismissible"' +
                ' role="alert">Congratulations!! Your interview is posted successfully.</div>');

            $("#interviewee_title").val("");
            $("#ipublish_industry").val("-1");
            $("#interviewee_skills").val("");
            $("#ipublish_desc").val("");
            $("#ipublish_budget").val("");
            $("#ipublish_exp").val("");
            $("#postinterviewdoc").val("");
            $("#selectedifile").html("");
            $("#interviewdocid").val("");

            $("#success-alert").fadeTo(5000, 1000).slideUp(1000, function () {
                $("#success-alert").alert('close');
            });
        }
        else {
            $("#interviewee_publishresponse").html('<div id="failed-alert" class="alert alert-danger alert-dismissible"' +
                ' role="alert">Sorry!! Your interview was not posted. Please Try Again.</div>');

            $("#failed-alert").fadeTo(5000, 1000).slideUp(1000, function () {
                $("#failed-alert").alert('close');
            });
        }
    });
};

$(document).on('click', '#interview_post', function () {
    postInterview($("#interview_title").val(), $("#interviewer_options").val(), $("#interview_skills").val(), $("#interview_message").val());
});

var showUserDetails = function ($username) {
    if ($username == user.getUserName()) {
        showMemberHome();
    }
    else {
        $.ajax({
            type: "GET",
            url: "user/" + $username + ".do",
        }).done(function (msg) {
            var profile = jQuery.parseJSON(msg);
            var memberHTML = "<table>";
            memberHTML = memberHTML + "<tr><td><h3>Username : </h3></td><td><h3>" + profile.username + "</h3></td></tr>";
            ;
            memberHTML = memberHTML + "<tr><td><h3>Skills : </h3></td><td><h3>" + profile.skills + "</h3></td></tr>";
            memberHTML = memberHTML + "<tr><td><h3>Rate : </h3></td><td><h3>" + profile.rate + "</h3></td></tr>";
            memberHTML = memberHTML + "<tr><td><h3>Companies : </h3></td><td><h3>" + profile.companies + "</h3></td></tr>";
            memberHTML = memberHTML + "<tr><td><h3>Country : </h3></td><td><h3>" + profile.country + "</h3></td></tr>";
            memberHTML = memberHTML + "<tr><td><h3>Balance : </h3></td><td><h3>" + profile.balance + "</h3></td></tr>";
            memberHTML = memberHTML + "</table>"
            $("#main").html(memberHTML);
        });
    }
};

/*$(document).on( 'click', '.profile', function () {
 var url = $(this).attr("url");
 $.ajax({
 type: "GET",
 url: url+".do",
 }).done(function( msg ) {
 var profile = jQuery.parseJSON(msg);
 var memberHTML = "<table>";
 memberHTML = memberHTML + "<tr><td><h3>Username : </h3></td><td><h3>"+profile.username+"</h3></td></tr>";;
 memberHTML = memberHTML + "<tr><td><h3>Skills : </h3></td><td><h3>"+profile.skills+"</h3></td></tr>";
 memberHTML = memberHTML + "<tr><td><h3>Rate : </h3></td><td><h3>"+profile.rate+"</h3></td></tr>";
 memberHTML = memberHTML + "<tr><td><h3>Companies : </h3></td><td><h3>"+profile.companies+"</h3></td></tr>";
 memberHTML = memberHTML + "<tr><td><h3>Country : </h3></td><td><h3>"+profile.country+"</h3></td></tr>";
 memberHTML = memberHTML + "<tr><td><h3>Balance : </h3></td><td><h3>"+profile.balance+"</h3></td></tr>";
 memberHTML = memberHTML + "</table>"

 $("#main").html(memberHTML);
 });

 });
 */


$(document).on('click', '.interviewdetails', function () {

    var iid = $(".interviewdetails").attr("iid");
    $.ajax({
        type: "GET",
        url: BASE_URL + "interview/" + iid + ".do",
    }).done(function (msg) {
        var interview = jQuery.parseJSON(msg);
        var attachment = "";
        if (interview.original_name != null) {
            attachment = '<a href="' + interview.url + '">' + interview.original_name + '</a>'
        }

        var scr = detailedInterviewScreen(iid);

        ("#main").html(scr);

    });
});


var MY_SHOW_BIDS = '';

var selectedInterviewerHTML = function ($iid) {
    var bidsHTML = "<table width=\"100%\"><tr><td><h3>Bidder</h3></td><td><h3>Message</h3></td><td><h3>Price</h3></td><td><h3>Date</h3></td><td><h3>Action</h3></td></tr>";
    var allReceivedBids = getBidsForInterviewId($iid);

    for (var j = 0; j < allReceivedBids.length; j++) {
        var bid = allReceivedBids[j];
        var action = "<select class=\"callintervieweeactions\" alt=\"" + $iid + "\">" + getIntervieweeActions() + "</select>";
        if (bid.getStatus() == 1) {
            bidsHTML = bidsHTML + "<tr><td>" + bid.getBidder() + "</td><td>" + bid.getMessage() + "</td><td>" + bid.getPrice() + "</td><td>" + prettyDate(new Date(Number(bid.getDate()))) + "</td><td>" + action + "</td></tr>";
        }
    }
    bidsHTML = bidsHTML + "</table>";
    return bidsHTML;
};


var showIntervieweeView = function () {

    var $head = $("head");

    var linkElement = "<link rel='stylesheet' type='text/css' href='css/stylesheet1.css'>";
    $head.append(linkElement);
    linkElement = "<link rel='stylesheet' type='text/css' href='css/jRating.jquery.css' media='screen' />";
    $head.append(linkElement);


    $.get("interviewee.html", function (data) {
        $("#bodyhtml").html(data);
        $('#rateClip1').html("<div class='exemple2' data-average='" + user.getReputation() + "' data-id='1'></div>");
        $('#rateClip2').html("<div class='exemple2' data-average='" + user.getReputation() + "' data-id='1'></div>");
        $('#rateClip3').html("<div class='exemple2' data-average='" + user.getReputation() + "' data-id='1'></div>");
        $(".exemple2").jRating({
            type: 'small',
            length: 10,
            isDisabled: true
        });


        for (var i = 0; i++ < postedInterviewsMap.size; postedInterviewsMap.next()) {
            var interview = postedInterviewsMap.value();
            var action = "<select class=\"callintervieweeactions\" alt=\"" + interview.getId() + "\">" + getInterviewActions() + "</select>";
            var interviewee_aninterview = '' +
                '<div class="tablebody">                     ' +
                '<table class="tab_table">' +
                '    <tbody>' +
                '        <tr class="clickableRow" href="#interviewdetails|' + interview.getId() + '">' +
                '            <td> ' + interview.getTitle() + ' </td>' +
                '            <td> ' + interview.getSkills() + ' </td>' +
                '            <td> ' + interview.getDescription() + ' </td>' +
                '            <td> ' + interview.getBidCount() + ' </td>' +
                '            <td> ' + getInterviewStatusLiteral(interview.getStatus()) + ' </td>' +
                '            <td> ' + prettyDate(new Date(interview.getDate())) + ' </td>' +
                '            <td> ' + action +
                '            </td> ' +
                '        </tr>' +
                '        ' +
                '    </tbody>' +
                '</table>            ' +
                ' </div>';
            $("#interviewee_allinterviews").append(interviewee_aninterview);
            if (interview.getStatus() == 0 || interview.getStatus() == 7)
                $("#interviewee_openinterviews").append(interviewee_aninterview);
            if (interview.getStatus() == 1 || interview.getStatus() == 2 || interview.getStatus() == 3 || interview.getStatus() == 4)
                $("#interviewee_currentinterviews").append(interviewee_aninterview);
            if (interview.getStatus() == 5 || interview.getStatus() == 6 || interview.getStatus() == 8 || interview.getStatus() == 9)
                $("#interviewee_pastinterviews").append(interviewee_aninterview);


        }


        $(".clickableRow").click(function () {
            window.document.location = $(this).attr("href");
        });

    });

};


var showMyBidsView = function () {

    $("#interviewer_section").html("");
    var my_bids = jQuery.parseJSON($("#MY_BIDS").val());
    {
        //$("#interviewer_section").append("<h2>My Bids</h2>");			
        var ttable = "<table width=\"100%\"><tr><td><h3>Title</h3></td><td><h3>Candidate</h3></td><td><h3>Description</h3></td><td><h3>Message</h3></td><td><h3>Price</h3></td><td><h3>Date</h3></td><td><h3>Status</h3></td><td><h3>Action</h3></td></tr>";
        for (var i = 0; i++ < allBidsPosted.size; allBidsPosted.next()) {

            var bidPosted = allBidsPosted.value();
            if (bidPosted.getStatus() == "0" || bidPosted.getStatus() == "2") {
                var statusString = getBidStatusLiteral(bidPosted.getStatus());
                var interview = getInterviewById(bidPosted.getIid());
                var action = "<select class=\"statusaction\" alt=\"" + bidPosted.getIid() + "\" bidvalue=\"" + bidPosted.getId() + "\">" + getBIDActions() + "</select>"
                ttable = ttable + "<tr><td><div class=\"mybiddetails\" showtype=\"bid\" "
                    + "bid_id=\"" + bidPosted.getId() + "\" style=\"cursor: pointer; \" "
                    + "alt=\"" + interview.getId() + "\">" + interview.getTitle() + "</div></td>"
                    + "<td>" + showUsernameLink(interview.getInterviewee()) + "</td>"
                    + "<td>" + interview.getDescription() + "</td>"
                    + "<td>" + bidPosted.getMessage() + "</td>"
                    + "<td>" + bidPosted.getPrice() + "</td>"
                    + "<td>" + prettyDate(new Date(interview.getDate())) + "</td>"
                    + "<td>" + statusString + "</td><td>" + action + "</td>"
                    + "</tr>";
            }

        }
        ttable = ttable + "</table>";
        $("#interviewer_section").append(ttable);
    }

};


var showInterviewerView = function () {

    var $head = $("head");

    var linkElement = "<link rel='stylesheet' type='text/css' href='css/stylesheet1.css'>";
    $head.append(linkElement);
    linkElement = "<link rel='stylesheet' type='text/css' href='css/jRating.jquery.css' media='screen' />";
    $head.append(linkElement);


    $.get("interviewer.html", function (data) {
        $("#bodyhtml").html(data);
        for (var i = 0; i++ < awardedInterviewsMap.size; awardedInterviewsMap.next()) {
            var interview = awardedInterviewsMap.value();
            var action = "<select class=\"statusaction\" alt=\"" + interview.getId() + "\">" + getInterviewerActions() + "</select>"
            var interviewer_aninterview = '' +
                '<div class="tablebody">                     ' +
                '<table class="tab_table">' +
                '    <tbody>' +
                '        <tr class="clickableRow" href="#interviewdetails|' + interview.getId() + '">' +
                '            <td> ' + interview.getInterviewee() + ' </td>' +
                '            <td> ' + interview.getTitle() + ' </td>' +
                '            <td> ' + interview.getDescription() + ' </td>' +
                '            <td> ' + interview.getSkills() + ' </td>' +
                '            <td> ' + getInterviewStatusLiteral(interview.getStatus()) + ' </td>' +
                '            <td> ' + prettyDate(new Date(interview.getDate())) + ' </td>' +
                '            <td> ' + action +
                '            </td> ' +
                '        </tr>' +
                '        ' +
                '    </tbody>' +
                '</table>            ' +
                ' </div>';
            $("#interviewer_allinterviews").append(interviewer_aninterview);
            if (interview.getStatus() == 0 || interview.getStatus() == 7)
                $("#interviewer_openinterviews").append(interviewer_aninterview);
            if (interview.getStatus() == 1 || interview.getStatus() == 2 || interview.getStatus() == 3 || interview.getStatus() == 4)
                $("#interviewer_currentinterviews").append(interviewer_aninterview);
            if (interview.getStatus() == 5 || interview.getStatus() == 6 || interview.getStatus() == 8 || interview.getStatus() == 9)
                $("#interviewer_pastinterviews").append(interviewer_aninterview);
        }


        $(".clickableRow").click(function () {
            window.document.location = $(this).attr("href");
        });
    });
}


var renderRatingSreen = function () {

    $(".bidder_rating").jRating({
        type: 'small', // type of the rate.. can be set to 'small' or 'big'
        length: 10, // nb of stars
        isDisabled: true,
        decimalLength: 1
    });
};

function getSelectOptions(selected) {

    var pendingSelected = "";
    var acceptSelected = "";
    var rejectSelected = "";

    if (selected == 0) {
        pendingSelected = "selected";
    }
    else if (selected == 1) {
        acceptSelected = "selected";
    }
    else if (selected == 2) {
        rejectSelected = "selected";
    }

    var localoption = "<option value=\"0\" " + pendingSelected + ">Pending</option>"
        + "<option value=\"1\" " + acceptSelected + ">Accept</option>"
        + "<option value=\"2\" " + rejectSelected + ">Reject</option>";

    return localoption;
}

function showHomeView() {
    var WALL = '<table border="0" width="100%"> <tr> <td width="15%" style="vertical-align:top;">All </td> <td><div id="wallsection"></div></td> <td width="20%"></td> </tr> </table>';
    $("#main").html(WALL);

    $.ajax({
        type: "GET",
        url: BASE_URL + "wall.do",
    }).done(function (msg) {


        var mywall = jQuery.parseJSON(msg);

        $.each(mywall.response, function (id, data) {
            if (data.type == "NIN") {


                var scr = NIN_HTML.split("$iid$").join(data.content.iid)
                        .split("$title$").join(data.content.title)
                        .split("$createdBy$").join(showUsernameLink(data.createdBy))
                        .split("$skills$").join(data.content.skills)
                        .split("$date$").join(prettyDate(new Date(Number(data.content.dt))))
                        .split("$btniid$").join(data.content.iid)
                        .split("$priceiid$").join(data.content.iid)
                        .split("$txtiid$").join(data.content.iid)
                    ;
                $("#wallsection").append(scr);

                var bid = getMyPostedBidForInterviewId(data.content.iid);

                if (bid != null) {
                    $("#" + data.content.iid).attr("disabled", true);
                    $("#" + data.content.iid).attr("value", "Already bid placed");
                    $("#biddocupload_" + data.content.iid).hide();
                    $("#biddocuploadAdd_" + data.content.iid).hide();
                    $("#bidmsgamnt_" + data.content.iid).hide();
                }


                var currentIID = data.content.iid;
                $('#biddocupload_' + data.content.iid).fileupload({
                    dataType: 'json',
                    maxChunkSize: 20000000, // 10 MB
                    done: function (e, data) {
                        var jsonResponse = jQuery.parseJSON(data.jqXHR.responseText);
                        $("#ninbiddoc_" + currentIID).attr("value", jsonResponse._id);
                        $("#biddocuploadedfn_" + currentIID).html("File Name : " + jsonResponse.originalfn);
                    },
                    start: function (e, data) {
                    },
                    submit: function (e, data) {
                    },
                    progress: function (e, data) {
                    }
                });
                $("#biddocuploadAdd_" + data.content.iid).click(function () {
                    $("#biddocupload_" + data.content.iid).trigger('click');
                });


            }
            else if (data.type == "NBN") {
                var currentuser = "";
                if (data.content.bidder == user.getUserName())
                    currentuser = "You have";
                else
                    currentuser = data.content.bidder + " has";


                var scr = NBN_HTML.split("$currentuser$").join(currentuser)
                    .split("$iid$").join(data.content.iid)
                    .split("$title$").join(data.content.title)
                    .split("$price$").join(data.content.price)
                    .split("$date$").join(prettyDate(new Date(Number(data.content.dt))));


                $("#wallsection").append(scr);
            }
            else if (data.type == "FIN") {
                var currentreceiver = "";
                var currentsender = "";
                if (data.content.interviewee == user.getUserName())
                    currentsender = "You have";
                else
                    currentsender = data.content.interviewee + " has";

                if (data.content.interviewer == user.getUserName())
                    currentreceiver = "you";
                else
                    currentreceiver = data.content.interviewer;


                var scr = FIN_HTML.split("$sender$").join(currentsender)
                    .split("$receiver$").join(currentreceiver)
                    .split("$title$").join(data.content.title)
                    .split("$date$").join(prettyDate(new Date(Number(data.entryDate))));

                $("#wallsection").append(scr);
            }
            else if (data.type == "AIN") {
                var currentinterviewer = "";
                var currentinterviewee = "";
                if (data.content.interviewee == user.getUserName())
                    currentinterviewee = "You have";
                else
                    currentinterviewee = data.content.interviewee + " has";

                if (data.content.interviewer == user.getUserName())
                    currentinterviewer = "you";
                else
                    currentinterviewer = data.content.interviewer;

                var scr = AIN_HTML.split("$interviewee$").join(currentinterviewee)
                        .split("$interviewer$").join(currentinterviewer)
                        .split("$title$").join(data.content.title)
                        .split("$date$").join(prettyDate(new Date(Number(data.entryDate))))
                        .split("$price$").join(data.content.price)
                    ;

                $("#wallsection").append(scr);

            }
            else if (data.type == "DFN") {
                var scr = DFN_HTML.split("$owner$").join("you")
                        .split("$netamount$").join(data.content.netamount)
                        .split("$date$").join(prettyDate(new Date(Number(data.entryDate))))
                    ;

                $("#wallsection").append(scr);
            }
            else if (data.type == "ESN") {
                var currentSender = "";
                if (data.content.interviewee == user.getUserName())
                    currentSender = "you have";
                else
                    currentSender = data.content.interviewee + " has ";

                var scr = ESN_HTML.split("$sender$").join(currentSender)
                        .split("$eb$").join(data.content.eb)
                        .split("$title$").join(data.content.title)
                        .split("$date$").join(prettyDate(new Date(Number(data.entryDate))))
                    ;

                $("#wallsection").append(scr);

            }
            else if (data.type == "RTN") {
                var currentRatedBy = "";
                var currentUser = "";

                if (data.content.username == user.getUserName())
                    currentUser = "you";
                else
                    currentUser = data.content.username;

                if (data.createdBy == user.getUserName())
                    currentRatedBy = "you have ";
                else
                    currentRatedBy = data.createdBy + " has ";


                var scr = RTN_HTML.split("$ratedby$").join(currentRatedBy)
                        .split("$ravg$").join(data.content.average)
                        .split("$user$").join(currentUser)
                        .split("$title$").join(data.content.title)
                        .split("$date$").join(prettyDate(new Date(Number(data.entryDate))))
                    ;

                $("#wallsection").append(scr);
            }
        });
    });
}

function showChatWindow(username) {
}

$(document).on('click', '.chatwindow', function () {
    $("#chatid").html("<h2>You are chatting with " + $(this).attr('id') + "</h2>");
    $("#activechatuser").html($(this).attr('id') + "@" + XMPPService);
    $("#chatbox").val("");
    $("#chatcontent").html("");
});


$(document).on('click', '#loginwithlinkedin', function () {
    var accept = confirm("By clicking OK, you allows us to store your LinkedIn information in our database. Please read our data policy. Do you accept?");
    if (accept)
        window.location = BASE_URL + 'linkedinshowloginscreen.do';
});


function showMemberHome() {


    var $head = $("head");
    var linkElement = "<link rel='stylesheet' href='demos.css'>";
    $head.append(linkElement);
    linkElement = "<link rel='stylesheet' href='//code.jquery.com/ui/1.10.4/themes/smoothness/jquery-ui.css'> ";
    $head.append(linkElement);
    linkElement = "<link rel='stylesheet' type='text/css' href='css/jRating.jquery.css' media='screen' />";
    $head.append(linkElement);


    $.get("profile.html", function (data) {
        $("#bodyhtml").html(data);
        $("#profilepicdiv").html("<img src='" + user.getProfilePic() + "' />");
        $("#profileusertype").html(user.getType());
        $("#profileusername").html(user.getUserName());
        $("#profileuserlocation").html(user.getCountry());
        $("#profileuserrate").html(user.getRate());
        $("#profileusersummary").html(user.getCV());
        $("#profileuserbalance").html(user.getBalance());
        $("#profilereviewcounts").html("(" + user.getReviewCount() + " Reviews)");


        $.each(user.getSkills(), function (index, value) {
            $("#profileuserskills").append("<div class='skills'>" + value + "</div>");
        });

        $.each(user.getCompanies(), function (index, value) {
            $("#profileuserscompanies").append("<div class='skills'>" + value + "</div>");
        });

        $("#profilereputation").html(user.getReputation() + "/10");
        $('#rateClip').html("<div class='exemple2' data-average='" + user.getReputation() + "' data-id='1'></div>");
        $(".exemple2").jRating({
            type: 'small', // type of the rate.. can be set to 'small' or 'big'
            length: 5, // nb of stars
            isDisabled: true
        });

    });

    /*



     var scr = PROFILE_HTML.split("$username$").join(user.getUserName())
     .split("$userskills$").join(user.getSkills())
     .split("$userrate$").join(user.getRate())
     .split("$usercompanies$").join(user.getCompanies())
     .split("$usercountry$").join(user.getCountry())
     .split("$userreputation$").join(user.getReputation())
     .split("$userreputationnumber$").join(parseFloat(user.getReputation()).toFixed(1))
     .split("$reviewcount$").join(user.getReviewCount())
     .split("$userbalance$").join(user.getBalance())
     .split("$ppic_src$").join(user.getProfilePic())
     .split("$calendar$").join('<input type="button" value="Show Calendar" alt="'+user.getUserName()+'" id="showcalendarbutton"/>')
     ;

     $("#main").html(scr);
     $(".searchrating").jRating({
     type:'small', // type of the rate.. can be set to 'small' or 'big'
     length : 10, // nb of stars
     isDisabled : true,
     decimalLength : 1
     });

     $(document).on('click', '#showcalendarbutton', function () {
     var username = $(this).attr("alt");
     $("#availabilitycalendar").html("");
     $("#availabilitycalendar").dialog({
     title : "Your availability Calendar",
     width : 610,
     open: function(event, ui) {
     var date = new Date();
     var d = date.getDate();
     var m = date.getMonth();
     var y = date.getFullYear();

     var calendar = $('#availabilitycalendar').fullCalendar({
     header: {
     left: 'prev,next today',
     center: 'title',
     right: 'month,agendaWeek,agendaDay'
     },
     defaultView : 'agendaWeek',
     firstHour : 17,
     allDaySlot : false,
     selectable: false,
     eventStartEditable  : false,
     eventDurationEditable : false,
     selectHelper: true,
     height: 400,
     editable: false,
     events: function(start, end, callback) {


     $.ajax({
     type: "GET",
     url: BASE_URL+"getcalendarevents.do",
     data: "start="+start.getTime()+"&end="+end.getTime()
     }).done(function( msg ) {
     //alert(msg);
     var events = [];
     var resData = jQuery.parseJSON(msg);
     $.each(resData.list, function (index, data) {
     new CalendarEvents(data._id, data.userid, data.title, data.starttime, data.endtime, 
     data.eventtype, data.isrecursive, data.recurdays, data.time);
     if(data.isrecursive == true){
     var days = Number(data.recurdays);
     //alert(days);
     var start = new Date(Number(data.starttime));
     var end = new Date(Number(data.endtime));

     for(var i=0;i<days;i++){
     var newStart = new Date(start.getTime()+(i*24*60*60*1000));
     var newEnd = new Date(end.getTime()+(i*24*60*60*1000));

     events.push({
     id : data._id,
     title: data.title,
     start: newStart,
     end: newEnd,
     allDay: false
     });
     }
     }
     else
     {
     events.push({
     id : data._id,
     title: data.title,
     start: new Date(Number(data.starttime)),
     end: new Date(Number(data.endtime)),
     allDay: false
     });
     }	
     });
     callback(events);
     });


     },
     });

     }
     });
     });
     */
}

function processRequestResponse(username, id, res) {
    var isAccepted = false;
    if (res == "YES") {
        $.ajax({
            type: "GET",
            url: BASE_URL + "acceptrequest.do",
            data: "rid=" + id,
        }).done(function (msg) {
            $("#div" + username).html("Accepted");
        });
    }


    if (res == "NO") {
        $.ajax({
            type: "GET",
            url: BASE_URL + "rejectrequest.do",
            data: "rid=" + id,
        }).done(function (msg) {
            $("#div" + username).html("Rejected");
        });
    }
}

function getNameCookieValue() {
    return document.cookie.replace(/(?:(?:^|.*;\s*)username\s*\=\s*([^;]*).*$)|^.*$/, "$1");
}

function isLoogedInCookieValue() {
    return document.cookie.replace(/(?:(?:^|.*;\s*)sessionLoggedIn\s*\=\s*([^;]*).*$)|^.*$/, "$1");
}

function validateEmail($email) {
    var emailReg = /^([\w-\.]+@([\w-]+\.)+[\w-]{2,4})?$/;
    if (!emailReg.test($email)) {
        return false;
    } else {
        return true;
    }
}

function getInterviewSearchResults($searchKey, currentPage) {
    var skey = $searchKey;
    var start = ((currentPage - 1) * 10);
    $.ajax({
        type: "GET",
        url: BASE_URL + "interviewsearch.do",
        data: "searchkey=" + skey + "&start=" + start,
    }).done(function (msg) {
        var wholeJSON = jQuery.parseJSON(msg);
        var json = wholeJSON.JSON_DOC_LIST;
        var resultsFound = false;
        var memberHTML = "<table>";
        $.get("search_interviews_view.html", function (data) {
            var allResultsView = "";
            $.each(json, function (i) {
                if (json[i].interviewee != user.getUserName()) {
                    var bid = getMyPostedBidForInterviewId(json[i].id);
                    var makebidVar = "";
                    if (bid != null) {
                        makebidVar = '<span class="label label-success">Bid Placed</span>';
                    } else {
                        makebidVar = '<div><div class="col-md-3 biddiv" iid="' + json[i].id + '" style="padding:0px 5px 0px 0px"></div></div>';
                    }
                    var attachment = "";
                    if (null != wholeJSON.iidFiles[json[i].id] && "" != wholeJSON.iidFiles[json[i].id]) {
                        attachment = '<p><small><b>Attachment:</b>&nbsp;<a href="' + BASE_URL + wholeJSON.iidFiles[json[i].id].url + '">' + wholeJSON.iidFiles[json[i].id].original_name + '</a></small> </p>';
                    }

                    var profilepic = "images/face.jpg";
                    if ("" != wholeJSON.pics[json[i].interviewee]) {
                        profilepic = wholeJSON.pics[json[i].interviewee];
                    }
                    var oneresult = '<div class="panel panel-default interviewresult">' +
                        '	<div class="panel-body">' +
                        '  	<div class="row">' +
                        '	<div class="col-md-3">' +
                        '	<img class="img-responsive img-hover" src="' + profilepic + '" alt="">' +
                        '	</div>' +
                        '	<div class="col-md-6" style="padding:0px">' +
                        '	<p><i class="fa fa-tasks"></i>&nbsp;<a href="#" onclick= "showAnINterviewScreenForSearch();" class="interviewer_interview_search" iid="' + json[i].id + '">' + json[i].title + '</a></p>' +
                        '	<p><small><b>Posted by:</b> &nbsp; <i class="fa fa-user"></i> ' +
                        '	&nbsp;' + getUserProfileLink(json[i].interviewee) + '&nbsp;&nbsp; on <i class="fa fa-calendar"></i>' +
                        '	&nbsp;' + prettyDate(new Date(Number(json[i].dt))) + '</small></p>' +
                        '	<p><small><b>Skills Required:</b>' + json[i].skills + '</small></p>' +
                        attachment +
                        '	<p><small><b>Status:</b>&nbsp;' + getInterviewStatusLiteral(wholeJSON.istatus[json[i].id]) + '</small> </p>' +
                        '	</div>' + makebidVar +
                        '	</div>' +
                        '	</div>' +
                        '</div>';

                    allResultsView = allResultsView + oneresult;
                    resultsFound = true;
                    /*var bid = getMyPostedBidForInterviewId(json[i].id);
                     var makebidVar = "Already bid placed";

                     makebidVar = "<div style=\"display:inline-block\">"
                     +"<input type=\"text\" id=\""+json[i].id+"_msg\"/>"
                     +"<input size=\"3\" type=\"text\" id=\""+json[i].id+"_price\"/>"
                     +"<input type=\"button\" class=\"ibid\" id=\""+json[i].id+"\" value=\"   Bid   \">"
                     +"</div>";
                     }

                     memberHTML = memberHTML + "<tr><td><h1>""</h1></td></tr>";
                     memberHTML = memberHTML + "<tr><td><h3>Posted By:</h3 ""></td></tr>";
                     memberHTML = memberHTML + "<tr><td><h3>Skills:</h3> ""</td></tr>";
                     memberHTML = memberHTML + "<tr><td><h3>Description:</h3> ""</td></tr>";
                     memberHTML = memberHTML + "<tr><td><h3>Status :</h3> ""</td></tr>";
                     memberHTML = memberHTML + "<tr><td><h3>Posted on :</h3> ""</td></tr>";
                     memberHTML = memberHTML + "<tr><td>"+makebidVar+"</td></tr>";

                     memberHTML = memberHTML + "<tr><td><br></td></tr>"

                     */
                }

            });

            var screen = data.split("$skey$").join(skey);
            if (resultsFound == true) {
                var totalRecords = wholeJSON.NUM_OF_RESULTS;
                var pagination = getPaginationHTML(totalRecords, currentPage, $searchKey, "INTERVIEW");
                allResultsView = allResultsView + pagination;
                screen = screen.split("$searchresults$").join(allResultsView);
            }
            else
                screen = screen.split("$searchresults$").join("No Results Found");

            $("#maincontainer").html(screen);
            // window need to scroll up
        });
    });
}


var A_PROFILE = '<table><tr><td  style="vertical-align:top;"><img src="$profileimgsrc$" width="100px" height="100px"/></td><td><table>'
        + '<tr><td><font size="4"><a href="javascript:showUserDetails(\'$search_username$\');">$search_username$</a></font>$status$</td></tr>'
        + '<tr><td><table><tr><td style="vertical-align:top;">Positions:</td><td>$search_positions$</td></tr></table></td></tr>'
        + '<tr><td><table><tr><td style="vertical-align:top;">Education:</td><td>$search_educations$</td></tr></table></td></tr>'
        + '<tr><td>Skills:$search_skills$</td></tr>'
        + '<tr><td>Country:$search_country$</td></tr>'
        + '</table></td></tr></table><br>'
    ;
var INTERVIEWER_SEARCH_RESULTS = '<table>'
        + '<tr><td></td>'
        + '<td>'
        + '<div id="interviewer_search_results_div">'
        + '</div>'
        + '</td>'
        + '</tr>'
        + '</table>'
    ;


function saveQucikChatMessage(chatmessage, from, to) {
    var currD = new Date();
    var type = "OFFLINE";
    if (checkWhetherUserIsOnline(to)) {
        type = "ONLINE";
    }
    $.ajax({
        type: "GET",
        url: BASE_URL + "savequickchat.do",
        data: "from=" + from + "&to=" + to + "&time=" + currD.getTime() + "&message=" + chatmessage + "&type=" + type
    }).done(function (msg) {
        console.log(msg);
    });
}

function sendChatMessage() {

    if (user == null) {
        alert("You need to login to send the message.");
        return false;
    }

    var to = $("#chatto").val();
    var from = user.getUserName();
    var chatmessage = $("#chatmessage").val();
    if ("undefined" === typeof CHAT_FRIENDS_MAP.get(to)) {
        addContact(to + "@" + XMPPService);
        CHAT_FRIENDS_MAP.put(to, offlineStatus);
    }

    var message = $msg({to: $.trim($(".cfli.selected").find("a").text()) + "@" + XMPPService, type: "chat"}).c("body")
        .t(chatmessage).up().c('active', {xmlns: "http://jabber.org/protocol/chatstates"});
    ;
    connection.sendIQ(message);
    saveQucikChatMessage(chatmessage, from, to);
    $("#chatmessage").val("");
    $("#chatalert").html("<div class='alert alert-success'>Your message sent.</div>");

    setTimeout(function () {
        $("#chatalert").html("");
        $("#contactForm").modal("hide");
    }, 800);

}

function addContact(contactJID) {
    var data = contactJID.split("@");
    var iq = $iq({type: "set"}).c("query", {xmlns: "jabber:iq:roster"}).c("item", {jid: contactJID, name: data[0]});
    connection.sendIQ(iq);
    var subscribe = $pres({to: contactJID, "type": "subscribe"});
    connection.send(subscribe);
};

function openPMB(targetUser) {
    $("#" + targetUser + "_pmb").css("display", "block");
    $("#" + targetUser + "_pmb").show();

    $(document).on('click', '#' + targetUser + '_pmb_send', function () {
        alert(CHAT_FRIENDS_MAP.get(targetUser));
        if (CHAT_FRIENDS_MAP.get(targetUser) === undefined) {
            alert("Adding contact");
            addContact(targetUser + "@" + XMPPService);
        }
        $("#" + targetUser + "_pmb").css("display", "none");
    });
    $(document).on('click', '#' + targetUser + '_pmb_cancel', function () {
        $("#" + targetUser + "_pmb").css("display", "none");
    });
}

var PAGE_SIZE = 10;
function showInterviewerSearchResults(searchKey, currentPage) {
    var start = ((currentPage - 1) * PAGE_SIZE);
    $.get("search_interviewers_view.html", function (data) {
        $("#maincontainer").html(data);
        $("#key_searchview").html(searchKey);
        $.ajax({
            type: "POST",
            url: BASE_URL + "search.do",
            data: "searchkey=" + searchKey + "&start=" + start,
            async: false
        }).done(function (msg) {
            var jresponse = jQuery.parseJSON(msg);
            var json = jresponse.JSON_DOC_LIST;
            var resultsFound = false;
            $.each(json, function (i) {
                var skillsJson = json[i].skills.replace(/\[/, '').replace(/\]/, '').split(",");
                var mySkills = '';
                for (var j = 0; j < (skillsJson.length < 5 ? skillsJson.length : 5); j++) {
                    mySkills = mySkills + skillsJson[j] + ",";
                }
                var myEducation = '';
                if (json[i].school != null) {
                    var schoolJson = json[i].school.replace(/\[/, '').replace(/\]/, '').split(",");
                    var degreeJson = json[i].degree.replace(/\[/, '').replace(/\]/, '').split(",");
                    var fieldofstudyJson = json[i].fieldofstudy.replace(/\[/, '').replace(/\]/, '').split(",");
                    for (var j = 0; j < (schoolJson.length < 3 ? schoolJson.length : 2); j++) {
                        myEducation = myEducation + '<p><small><i class="fa fa-graduation-cap"></i>&nbsp;' + degreeJson[j] + ' - ' + fieldofstudyJson[j] + ', ' + schoolJson[j] + ' 2007</small> </p>';
                    }
                }

                var myPositions = '';
                if (json[i].position != null) {
                    var compJson = json[i].companies.replace(/\[/, '').replace(/\]/, '').split(",");
                    var posJson = json[i].position.replace(/\[/, '').replace(/\]/, '').split(",");
                    for (var j = 0; j < (compJson.length < 3 ? compJson.length : 2); j++) {
                        myPositions = myPositions +
                            '	 <p><small><i class="fa fa-suitcase"></i>&nbsp;&nbsp; ' + posJson[j] + ', ' + compJson[j] + ' 2010-2012</small></p>';

                    }
                }
                var status = "";
                if (json[i].additional.online == "0")
                    status = "red.png";
                else
                    status = "green.png";
                var profilepic = "images/face.jpg";
                if ("" != json[i].additional.profilepic) {
                    profilepic = json[i].additional.profilepic;
                }
                var searchItem = '<div class="panel panel-default  interviewerresult" uname="' + json[i].username + '">' +
                    '<div class="panel-body">' +
                    '<div class="row">' +
                    '<div class="col-md-3">' +
                    '<div style="position: relative; left: 0; top: 0;">' +
                    '	<img style="position: relative; top: 0; left: 0;" class="img-responsive img-hover" src="' + profilepic + '" width="110px" height="110px" alt="">' +
                    '<img src="images/' + status + '" width="18px" height="18px"  style="position: absolute; top: 5px; left: 90px;"/>' +
                    '</div>	' +
                    '	<span style="margin:0px 0px 0px 18px;">' + getStarView(json[i].additional.rating) +
                    '	</span>' +
                    '	<span style="margin:0px 0px 0px 35px;"><a href="#"><small>(312)</small></a></span>' +
                    '</div>' +
                    '<div class="col-md-8" style="padding:0px">' +
                    '	<p><i class="fa fa-user"></i>&nbsp;<a href="#">' + json[i].username + '</a></p><p><i class="fa fa-flag-checkered"></i>&nbsp; ' + json[i].country + ' </p><p><i class="fa fa-usd"></i>&nbsp;' + json[i].rate + '</p>' +
                    '	<p><small><b>Skills:</b>' + mySkills + '</small></p>' + myEducation + myPositions +
                    '</div>' +
                    '</div>  ' +
                    '</div>' +
                    '</div>';

                $("#allsearchresults").append(searchItem);
                resultsFound = true;
            });

            if (resultsFound == false) {
                $("#allsearchresults").html("No results found.");
                $(".pagination").hide();
            }
            /*

             if(json[i].additional != null)
             {
             var rating ;
             var ratingScr = "Not yet rated";
             if(json[i].additional.rating != null)
             {
             SEARCH_RESULT_BOX = SEARCH_RESULT_BOX.split("$ratingnum$").join(json[i].additional.rating+'/5');
             SEARCH_RESULT_BOX = SEARCH_RESULT_BOX.split("$rateavg$").join(json[i].additional.rating);
             }
             SEARCH_RESULT_BOX = SEARCH_RESULT_BOX.split("$profilepicsrc$").join(json[i].additional.profilepic);



             var onlineStatus = "";
             if(json[i].additional.online == "1")
             SEARCH_RESULT_BOX = SEARCH_RESULT_BOX.split("$statusimage$").join("img/green.png");
             else 
             SEARCH_RESULT_BOX = SEARCH_RESULT_BOX.split("$statusimage$").join("img/red.png");

             SEARCH_RESULT_BOX = SEARCH_RESULT_BOX.split("$name$").join(json[i].username);

             }	

             SEARCH_RESULT_BOX = SEARCH_RESULT_BOX.split("$currentposition$").join(currentposition);	
             SEARCH_RESULT_BOX = SEARCH_RESULT_BOX.split("$skills$").join(mySkills);

             $(".search_results_right").append(SEARCH_RESULT_BOX);

             });*/


            var totalRecords = jresponse.NUM_OF_RESULTS;
            var html = getPaginationHTML(totalRecords, currentPage, searchKey, "INTERVIEWER");
            $("#paginationdiv").html(html);

        });

    });


    /*

     //json[i].position
     var searchHTML = A_PROFILE.split("$search_username$").join(json[i].username)
     .split("$status$").join("&nbsp;&nbsp;"+onlineStatus +
     "&nbsp;&nbsp;<a href=\"javascript:openPMB('"+json[i].username+"')\"> Contact Me</a>")
     //"&nbsp;&nbsp;<div style=\"cursor: pointer;display:inline-block \" class=\"searchuserslist\" id=\""
     //+json[i].username+"\">Contact me</div>")
     .split("$search_positions$").join(myPositions)
     .split("$search_educations$").join(myEducation)
     .split("$search_skills$").join(mySkills)
     .split("$search_country$").join(json[i].country)
     .split("$profileimgsrc$").join(json[i].additional.profilepic != null ? json[i].additional.profilepic  : "")
     ;

     $("#interviewer_search_results_div").append("<table><tr><td>"+searchHTML+"</td><td style=\"vertical-align:top\">"
     +"<div id=\""+json[i].username+"_pmb\" style=\"display: none;\">"
     +"<input type='text' size='50' id='"+json[i].username+"_pmb_text'/><br>"
     +"<input type='button' id='"+json[i].username+"_pmb_send' value='Send'>"
     +"<input type='button' id='"+json[i].username+"_pmb_cancel' value='Cancel'>"
     +"</div></td></tr></table>");
     resultsFound = true;
     });



     });	

     */
}


function getPaginationHTML(totalRecords, currentPage, searchKey, searchFor) {
    var totalPages = Math.ceil(totalRecords / PAGE_SIZE);
    var html = "";
    if (totalPages > 1) {
        html += "<ul class='pagination pull-right'>"
        for (var i = 0; i < totalPages; i++) {
            if (currentPage == (i + 1)) {
                html = html + "<li class='active'><a href='javascript:void(0)'>" + (i + 1) + "</a></li>";
            } else {
                if (searchFor == "INTERVIEW") {
                    html = html + "<li class=''><a href='javascript:getInterviewSearchResults(\"" + searchKey + "\"," + (i + 1) + ")'>" + (i + 1) + "</a></li>";
                } else if (searchFor == "INTERVIEWER") {
                    html = html + "<li class=''><a href='javascript:showInterviewerSearchResults(\"" + searchKey + "\"," + (i + 1) + ")'>" + (i + 1) + "</a></li>";
                }
            }
        }
        html += "</ul>"
    }
    return html;
}

function sendRequest(interviewer) {
    var postData = "interviewee=" + myusername + "&interviewer=" + interviewer;
    $.ajax({
        type: "POST",
        url: BASE_URL + "firstirequest.do",
        data: postData,
    }).done(function (msg) {
        var res = jQuery.parseJSON(msg);
        if (res.error == undefined) {
            var valueREQ_SENT = jQuery.parseJSON($("#REQ_SENT").val());
            $("#div" + interviewer).html("Request Sent");
        }
    });
}


function showSupportTab() {
    $.get("dispute.html", function (data) {
        $("#bodyhtml").html(data);
    });
}

function getUserProfileLink(userName) {
    return '<a id="userProfile" href="javascript:void(0)">' + userName + '</a>';
}

var showAnINterviewScreenForSearch = function () {
    var iid = $(".interviewer_interview_search").attr('iid');
    $.get("interviewer_interview_search.html", function (data) {
        var interview = getInterviewById(iid);

        /* Check the root cause for the behaviour */
        var skills = "";
        if ($.isArray(interview.getSkills()) && interview.getSkills().length > 1) {
            skills = interview.getSkills().join();
        } else {
            skills = jQuery.parseJSON(interview.getSkills());
        }

        var screen = data.split("$ititle$").join(interview.getTitle())
            .split("$istatus$").join(getInterviewStatusLiteral(interview.getStatus()))
            .split("$postedon$").join(dateFormat(interview.getDate(), "mmmm dS, yyyy, h:MM:ss TT"))
            .split("$idescription$").join(interview.getDescription())
            .split("$iskills$").join(skills);


        // get bid on interview and check for accepted									

        screen = screen.split("$postedby$").join('' + getUserProfileLink(interview.getInterviewee()) + '')
            .split("$postedbyrating$").join(getStarView(user.getReputation()));
        // get picture				
        $.ajax({
            type: "GET",
            url: BASE_URL + "userpicurl.do",
            data: "username=" + interview.getInterviewee(),
            async: false
        }).done(function (msg) {
            screen = screen.split("$postedbypic$").join(msg)
                .split("$totalescrowed$").join("$" + interview.getEB());
        });


        // get accepted bid							
        $("#searchview_main").empty();
        ;
        $("#searchview_main").html(screen);
        $(".interviewee_tabs").hide();
    });
}

// show the profile of selected user
$(document).on("click", "#userProfile", function () {
    var profilename = $(this).html();
    $.get("single_profile.html", function (data) {
        $("#maincontainer").html(data);
        $.ajax({
            type: "GET",
            url: BASE_URL + "userexternaldata.do",
            data: "username=" + profilename,
            async: false
        }).done(function (res) {
            var resData = jQuery.parseJSON(res);
            if (resData.profilepic == null || resData.profilepic == ""
                || resData.profilepic == 0) {
                resData.profilepic = "images/face.jpg";
            }
            $("#profile_userpic").attr("src", resData.profilepic);
            $("#profile_username").html(resData.username);
            $("#profile_country").html(resData.country);
            $("#profile_rating").html(resData.rating);
            $("#reviewcount").html(resData.reviewCount);
            $("#userratingstar").html(getBigStarView(resData.rating));

            if (resData.isonline == true || res == "true")
                $('<img src="images/green.png" width="20px" height="20px"  style="position: absolute; top: 5px; left: 225px;"/>').insertAfter("#profile_userpic");
            else
                $('<img src="images/red.png" width="20px" height="20px"  style="position: absolute; top: 5px; left: 225px;"/>').insertAfter("#profile_userpic");


            if (Number(resData.reviewCount) > 0) {
                if (Number(resData.individualratings.rate5) > 0) {
                    $("#howmany5").attr("style", 'width: ' + (Number(resData.individualratings.rate5) / Number(resData.reviewCount) * 100 ) + '%');
                    $("#howmanyvalue5").html((Number(resData.individualratings.rate5) / Number(resData.reviewCount) * 100 ) + "%");
                }
                if (Number(resData.individualratings.rate4) > 0) {
                    $("#howmany4").attr("style", 'width: ' + (Number(resData.individualratings.rate4) / Number(resData.reviewCount) * 100 ) + '%');
                    $("#howmanyvalue4").html((Number(resData.individualratings.rate4) / Number(resData.reviewCount) * 100 ) + "%");
                }
                if (Number(resData.individualratings.rate3) > 0) {
                    $("#howmany3").attr("style", 'width: ' + (Number(resData.individualratings.rate3) / Number(resData.reviewCount) * 100 ) + '%');
                    $("#howmanyvalue3").html((Number(resData.individualratings.rate3) / Number(resData.reviewCount) * 100 ) + "%");
                }
                if (Number(resData.individualratings.rate2) > 0) {
                    $("#howmany2").attr("style", 'width: ' + (Number(resData.individualratings.rate2) / Number(resData.reviewCount) * 100 ) + '%');
                    $("#howmanyvalue2").html((Number(resData.individualratings.rate2) / Number(resData.reviewCount) * 100 ) + "%");
                }
                if (Number(resData.individualratings.rate1) > 0) {
                    $("#howmany1").attr("style", 'width: ' + (Number(resData.individualratings.rate1) / Number(resData.reviewCount) * 100 ) + '%');
                    $("#howmanyvalue1").html((Number(resData.individualratings.rate1) / Number(resData.reviewCount) * 100 ) + "%");
                }
            }
            var cv = $.trim(resData.cv);
            if (cv.length > 280) {
                cv = cv.substring(0, 280);
                cv = cv + '...&nbsp;&nbsp;&nbsp;<a href="#">show more </a>';
            }
            $("#usershortcv").html(cv);
            var workexp = "<br>";
            for (var i = 0; i < resData.positions.length; i++) {
                workexp = workexp + '<span><h5><b>' + resData.positions[i].title + ' - ' + resData.positions[i].companyName + ' (' + resData.positions[i].startYear + '-' + resData.positions[i].endYear + ')</b></h5></span>' + resData.positions[i].description + '<hr>';
            }

            $("#workexperience").html(workexp);


            for (var i = 0; i < resData.educations.length; i++) {
                var education = '<tr> ' +
                    '<td>' + resData.educations[i].schoolname + '</td>' +
                    '<td>' + resData.educations[i].degree + '</td>' +
                    '<td>' + resData.educations[i].fieldOfStudy + '</td>' +
                    '<td>' + resData.educations[i].startYear + '-' + resData.educations[i].endYear + '</td>' +
                    '</tr>';
                $("#userprofileeducation").append(education);
            }

            var allskills = resData.skilllist;
            for (var i = 0; i < allskills.length; i++) {
                var per = Math.round((allskills[i].skillYear / 15) * 100);
                if (per > 100) {
                    per = 100;
                }
                var askill = '<div class="row">' +
                    '<div class="col-md-3">&nbsp;&nbsp;&nbsp;' + allskills[i].skill + '</div>' +
                    '<div class="col-md-6 ">' +
                    '		<div class="progress">' +
                    ' 		<div class="progress-bar progress-bar-success" ' +
                    '		role="progressbar" aria-valuenow="' + per + '" aria-valuemin="0" aria-valuemax="100" style="width: ' + per + '%">' +
                    '    			<span class="sr-only">' + per + '% </span>' +
                    ' 		</div>' +
                    '	</div>' +
                    '</div>' +
                    '<div class="col-md-3">' +
                    '' + allskills[i].skillYear + '	years' +
                    '</div>' +
                    '</div>';
                $("#skills").append(askill);
            }

            for (var i = 0; i < resData.allreviews.length; i++) {
                var areview = '<div class="panel panel-default">' +
                    '<div class="panel-heading">' +
                    '<div class="row">' +
                    '<div class="col-md-8"><span class="panel-title">' + resData.allreviews[i].ititle + '</span> - <span><small>' + prettyDate(new Date(Number(resData.allreviews[i].dt))) + '</small></span></div>' +
                    '<div class="col-md-4"><span style="pull-right">' +
                    '' + getStarView(resData.allreviews[i].average) + ' (' + resData.allreviews[i].average + '/5)' +
                    '	</span></div></div></div>' +
                    '<div class="panel-body">' +
                    '    <i>' + resData.allreviews[i].message + '</i>' +
                    '<div class="row">' +
                    '<div class="col-md-8"></div>' +
                    '<div class="col-md-4"><span style="pull-right">by ' + resData.allreviews[i].ratedby + ' on </span><div><small>&nbsp;&nbsp;' + prettyDate(new Date(Number(resData.allreviews[i].dt))) + '</small></div></div>' +
                    '  </div>' +
                    '  </div>' +
                    '</div>';
                $("#reviews").append(areview);
            }

            $("#chatto").val(resData.username);
        });

    });
});

function initializeUserData() {
    $.ajax({
        type: "GET",
        url: BASE_URL + "welcome.do"
    }).done(function (msg) {
        var jsonResponse = jQuery.parseJSON(msg);
        if (jsonResponse != null) {
            var individualratingsCount = new Array();
            individualratingsCount[0] = jsonResponse.user_info.individualratings.rate1;
            individualratingsCount[1] = jsonResponse.user_info.individualratings.rate2;
            individualratingsCount[2] = jsonResponse.user_info.individualratings.rate3;
            individualratingsCount[3] = jsonResponse.user_info.individualratings.rate4;
            individualratingsCount[4] = jsonResponse.user_info.individualratings.rate5;

            var positions = new Array();
            for (var i = 0; i < jsonResponse.user_info.positions.length; i++) {
                var pos = new Position(jsonResponse.user_info.positions[i]._id,
                    jsonResponse.user_info.positions[i].companyName,
                    jsonResponse.user_info.positions[i].description,
                    jsonResponse.user_info.positions[i].title,
                    jsonResponse.user_info.positions[i].startYear,
                    jsonResponse.user_info.positions[i].endYear);
                positions.push(pos);
            }

            var educations = new Array();
            for (var i = 0; i < jsonResponse.user_info.educations.length; i++) {
                var edu = new Education(jsonResponse.user_info.educations[i]._id,
                    jsonResponse.user_info.educations[i].degree,
                    jsonResponse.user_info.educations[i].fieldOfStudy,
                    jsonResponse.user_info.educations[i].schoolname,
                    jsonResponse.user_info.educations[i].startYear,
                    jsonResponse.user_info.educations[i].endYear
                );
                educations.push(edu);
            }

            var allreviews = new Array();
            for (var i = 0; i < jsonResponse.user_info.allreviews.length; i++) {
                console.log("R:" + jsonResponse.user_info.allreviews[i]);
                var review = new Review(jsonResponse.user_info.allreviews[i].ititle,
                    jsonResponse.user_info.allreviews[i].message,
                    jsonResponse.user_info.allreviews[i].ratedby,
                    jsonResponse.user_info.allreviews[i].average,
                    jsonResponse.user_info.allreviews[i].dt
                );
                allreviews.push(review);
            }

            if (jsonResponse.user_info.profilepic == null || jsonResponse.user_info.profilepic == ""
                || jsonResponse.user_info.profilepic == 0) {
                jsonResponse.user_info.profilepic = "images/face.jpg";
            }
            user = new User(jsonResponse.user_info.username, jsonResponse.user_info.skills,
                jsonResponse.user_info.companies, jsonResponse.user_info.country,
                jsonResponse.user_info.balance, jsonResponse.user_info.rate, jsonResponse.user_info.cv,
                "", "", jsonResponse.user_info.rating, jsonResponse.user_info.type,
                jsonResponse.user_info.profilepic, jsonResponse.user_info.reviewCount, jsonResponse.user_info.chatpass,
                allreviews, educations, positions, individualratingsCount, jsonResponse.user_info.skilllist);

            if (user.getType() == "INTERVIEWER") {
                $("#query").attr("placeholder", "Search Interviews...");
            } else {
                $("#query").attr("placeholder", "Search Interviewers...");
            }

            $("#nav_visiblename").html(user.getUserName() + '<b class="caret"></b>');
            populatesInterviews(jsonResponse.MY_INTERVIEW);
            populateBids(jsonResponse.MY_BIDS);
            populateAvailableUsers(jsonResponse.AVAILABLE_USERS);
            processDisputes(jsonResponse.DISPUTES);
            populateInboxCount(jsonResponse.OFFLINE_CHAT_COUNT);
            signInOnChat();
        } else {
            window.location = BASE_URL;
        }
    });
}

function logout() {
    sessionLoggedIn = null;
    user_info = null;
    signOutOnChat();
    $.ajax({
        type: "GET",
        url: BASE_URL + "logout.do",
        async: false,
    }).done(function (msg) {
        window.location.href = (BASE_URL);
    }).error(function (msg) {
        console.log("error occured while logout");
    });
}