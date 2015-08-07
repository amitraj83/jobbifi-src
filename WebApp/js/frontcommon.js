/* Constant Variable */
var loc = document.location + "";
var BASE_URL = loc.substring(0, loc.lastIndexOf("/")) + "/";
var AWARD_INTERVIEW = '<input type=\"button\" class=\"awardinterview\" value=\"Award Interview\" alt=\"$iid$\" bidid=\"$bid_id$\" >';
var defaultSearchTextforInterviewer = "Search interviews";
var defaultSearchTextforInterviewee = "Search interviewers";
var currentDefaultInSearchTextField = "";
var BOSH_SERVICE = 'https://162.243.74.91:5285/http-bind'
var XMPPService = '162.243.74.91';

/* Variable needs to be store in cookie */
var already_requested_user = new Array();
var users_available_for_message = new Array();
var interviewsAsInterviewer = new Array();
var interviewsAsInterviewee = new Array();
var interviewsWhereIBid = new Array();
var CHAT_FRIENDS_MAP = new Map;
var linkedInUser = null;
var user = null;
var financeTAB = "";
var connection = null;

function insertHeaderFooter() {
    if ($("#website_header").length == 0) {
        $('body').prepend($("<div id='website_header'></div>"))
    }
    ;
    $.get("header.html", function (data) {
        /* $("#website_header").html(data); */
        checkLogin();

        $("#btn-signup").click(function () {
            Register();
        });

        $('#loginwithlinkedin').click(function () {
            var accept = confirm("By clicking OK, you allows us to store your LinkedIn information in our database. Please read our data policy. Do you accept?");
            if (accept) {
                window.location = BASE_URL + 'linkedinshowloginscreen.do';
            }
        });

    });
    if ($("#website_footer").length == 0) {
        $('body').append($("<div id='website_footer'></div>"))
    }
    ;
    $.get("footer.html", function (data) {
        $("#website_footer").html(data);
    });
}

function Register() {
    var username = $("#username").val();
    if (username == "") {
        alert("Please provide username");
        return;
    }
    var email = $("#email").val();
    if (email == "") {
        alert("Please provide email address");
        return;
    }
    if (!validateEmail(email)) {
        alert("Please provide a valid email address");
        return;
    }
    var password = $("#password").val();
    if ($.trim(password) == "") {
        alert("Please provide password");
        return;
    }

    if ($.trim(password).length < 8) {
        alert("Password must be more than 8 character long");
        return;
    }

    var password2 = $("#confirmpassword").val();
    if (password2 == "") {
        alert("Please re-enter the password");
        return;
    }
    if (password != password2) {
        alert("The re-entered password does not match");
        return;
    }
    var usertype = $("#signupform input[name='usertype']:checked").val();
    if (typeof usertype == 'undefined') {
        alert("Please select your user type");
        return;
    }

    var param = "username=" + username +
        "&useremail=" + email +
        "&password=" + CryptoJS.SHA1(password) +
        "&type=" + usertype;
    $.ajax({
        type: "POST",
        url: BASE_URL + "register.do",
        data: param,
    }).done(function (msg) {
        var json = jQuery.parseJSON(msg);
        if (json.response == 2) {
            alert("This user already exist. Please create a unique user.");
        } else if (json.response == 1) {
            alert("The user has been registered successfully.");
            window.location.href = BASE_URL;
        }
    });
}

function validateEmail($email) {
    var emailReg = /^([\w-\.]+@([\w-]+\.)+[\w-]{2,4})?$/;
    if (!emailReg.test($email)) {
        return false;
    } else {
        return true;
    }
}

// flags to show contact box
var SHOW_MESSAGE_BOX = false;
var MESSAGE_TO_UESR = null;

var IS_USER_LOGIN = false;
function showLoginLink() {
    if (IS_USER_LOGIN) {
        $(".menu-login").hide();
        $(".menu-logout").show();
    } else {
        $(".menu-login").show();
        $(".menu-logout").hide();
    }
}

function checkLogin() {
    $.ajax({
        type: "GET",
        url: BASE_URL + "isloggedin.do"
    }).done(function (msg) {
        if (msg == "NL") {
            IS_USER_LOGIN = false;
        } else if (msg == "LI") {
            IS_USER_LOGIN = true;
            initializeUserData();
        }
        showLoginLink();
    });
}

$(document).ready(function () {
    insertHeaderFooter();

});

function login() {
    $("#loginboxmsg").html("");
    var data = "j_username=" + $("#j_username").val() + "&j_password=" + $("#j_password").val();
    $.ajax({
        'type': 'POST',
        'url': BASE_URL + "j_spring_security_check",
        'data': data,
        'dataType': 'json',
        success: function (response) {
            if (response.RESULT != null && response.RESULT == "SUCCESS") {
                if (response.REDIRECT) {
                    window.top.location = BASE_URL + response.REDIRECT;
                    return;
                }
                IS_USER_LOGIN = true;
                showLoginLink();
                $("#myModal").modal("hide");
                initializeUserData();

                if (SHOW_MESSAGE_BOX) {
                    showContactMeScreen(MESSAGE_TO_UESR);
                    SHOW_MESSAGE_BOX = false;
                    MESSAGE_TO_UESR = null;
                }

            } else {
                $("#loginboxmsg").html("<div class='alert alert-danger'>Email or password is wrong.</div>");
            }
        },
        error: function () {
            $("#loginboxmsg").html("<div class='alert alert-danger'>Unable to process the rquest.</div>");
        }
    });
}

function sendForgotPasswordMail() {
    var myuseremail = $("#forgotpass_email").val();
    if (!validateEmail(myuseremail)) {
        alert("Please enter valid email.");
        return;
    }
    $.ajax({
        type: "GET",
        url: BASE_URL + "resetpasswordemail.do",
        data: "useremail=" + myuseremail
    }).done(function (msg) {
        alert(msg);
        $("#forgotpasswordbox").hide();
    }).error(function (xhr, msg) {
        alert("Error occured while processing the request.");
    });
}

function showContactMeScreen(interviewer) {
    if (!IS_USER_LOGIN) {
        $("#loginboxmsg").html("<div class='alert alert-warning'>Login or register to get started.</div>");
        $("#myModal").modal('show');
        SHOW_MESSAGE_BOX = true;
        MESSAGE_TO_UESR = interviewer;
    } else {
        var html =
            '<div class="modal fade" id="contactForm" role="dialog" aria-hidden="true">' +
            '<div class="modal-dialog">' +
            '<div class="modal-content">' +
            '<div class="modal-header">' +
            '<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>' +
            '<h4 class="modal-title">Quick Contact</h4>' +
            '</div>' +
            '<div class="modal-body">' +
            '<div id="chatalert"></div>' +
            '<form role="form">' +
            '<input type="hidden" id="chatto" value="' + interviewer + '">' +
            '<div class="form-group">' +
            '<label>Your Message</label>' +
            '<textarea id="chatmessage" placeholder="Enter your message" class="form-control input-xs"></textarea>' +
            '</div>' +
            '<button onclick="sendChatMessage();" id="qucikContactBtn" class="btn btn-default" type="button">Send</button>' +
            '</form>' +
            '</div>' +
            '</div>' +
            '</div>' +
            '</div>';

        if ($("#qucikContact").length == 0) {
            $('body').append($("<div id='qucikContact'></div>").html(html))
        }
        ;
        $("#contactForm").modal("show");
    }
}