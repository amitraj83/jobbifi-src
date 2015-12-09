/** Utility function */
var PM_LOADER_START = null;
function showLoader(){
	PM_LOADER_START = new Date();
	$("#processing-modal").modal("show");
};

function hideLoader(){	
	var time = 0;
	var seconds = ((new Date()).getTime() - PM_LOADER_START.getTime())/1000;
	if(seconds < 8) {
		time = 8 - seconds; 
	}
	setTimeout(function(){
		$("#processing-modal").modal("hide");	
	}, time * 1000);	
};

function showError(message){
	$.notify({message: message},{type: 'danger', 
		placement: {
			from: "top",
			align: "center"
		},z_index:2500});
}

function showSuccess(message){
	$.notify({message: message},{type: 'success',
		placement: {
			from: "top",
			align: "center"
		}, z_index:2500});
}

function showWarning(message){
	$.notify({message: message},{type: 'warning',
		placement: {
			from: "top",
			align: "center"
		}, z_index:2500});
}

function showInfo(message){
	$.notify({message: message},{type: 'info',
		placement: {
			from: "top",
			align: "center"
		}, z_index:2500});
}

function message(msg,type) {
    $("#message").html("<div class='alert alert-"+type+"'>"+msg+"</div>");
    setTimeout(function () {
        $("#message").html("");
    }, 3600 * 2);
}
/** ----------------- **/

function loadNewMessageCount(){	
	$.ajax({
		url: BASE_URL + 'getnewmessagecount.do',
		type: "GET",
	}).done(function( res ) {
		var json = jQuery.parseJSON(res);
		if(json.NEW_MESSAGE_COUNT > 0){
			$("#messageCount").html('<span class="label label-success">' + json.NEW_MESSAGE_COUNT + '</span>');
		}else{
			$("#messageCount").html('');
		}
	});
	
	//setTimeout(function(){loadNewMessageCount()}, 30*1000);
}

function gup( name ){
    name = name.replace(/[\[]/,"\\\[").replace(/[\]]/,"\\\]");  
    var regexS = "[\\?&]"+name+"=([^&#]*)";  
    var regex = new RegExp( regexS );  
    var results = regex.exec( window.location.href ); 
    if( results == null )
        return "";  
    else
      return results[1];
}

function gupurl( name , url){
    name = name.replace(/[\[]/,"\\\[").replace(/[\]]/,"\\\]");  
    var regexS = "[\\?&]"+name+"=([^&#]*)";  
    var regex = new RegExp( regexS );  
    var results = regex.exec( url); 
    if( results == null )
        return "";  
    else
      return results[1];
}

function callF(name){	
	window[name]();
}

function getURLParameter(name) {
    return decodeURI(
        (RegExp(name + '=' + '(.+?)(&|$)').exec(location.search)||[,null])[1]
    );
}

function registerUser(){	
	
	$("#signupbtnloader").show();
    var username = $("#username").val();   
    var email = $("#email").val();    
    var password = $("#password").val();    
    var password2 = $("#confirmpassword").val();    
    var usertype = $("#signupform input[name='usertype']:checked").val();    
    if(typeof usertype == 'undefined'){ 
        alert("Please select your user type");
        return;
    }

    var param = "username="+username+
                "&useremail="+email+
                "&password="+CryptoJS.SHA1(password)+
                "&type="+usertype;
    $.ajax({
        type: "POST",
        url: BASE_URL + "register.do",
        data:   param,
    }).done(function( msg ) {
        var json = jQuery.parseJSON(msg);
        $("html").find("[data-notify='container']").remove();
        if(json.response == 3){
        	showWarning("This email already exist. Please try with another email.");            
        }else if(json.response == 4){
        	showWarning("This username already exist. Please try with another username.");
        }else if(json.response == 1){
            $("#myModal").modal("hide");
            showSuccess("You have been registered successfully.");
            $("#j_username").val(username) ;
            $("#j_password").val(password);
            login();

        } else if(json.response == -1){
            showError("Error occured while registration.");                      
        }
        
        //$("#username").val("");   
        //$("#email").val("");    
        //$("#password").val("");    
        //$("#confirmpassword").val("");  
    }).always(function(jqXHR, textStatus) {
		$("#signupbtnloader").hide();
	});	
}

function sendForgotPasswordMail(){
	$("#passwordbtnloader").show();	
	 var data="useremail=" + $("#forgotpass_email").val();
	 $.ajax({
		 url: BASE_URL + "resetpasswordemail.do",
		 data:data,
		 type:'GET',
		 success : function(response){
			var resData = jQuery.parseJSON(response);	
			//if(resData.RESULT && resData.RESULT == "SUCCESS"){
			if(resData.response == 1){
				showSuccess("A link to change your password has been sent at the email address you provided. " +
          		"Please visit the url to change your password");
				$("#forgotpass_email").val("");
				$("#myModal").modal("hide");
			}else if(resData.response == 0){ 
				showError("We couldn't find a jobbify account associated with "+$("#forgotpass_email").val()+".");
			}else {
				showError("We are unable to send the email. Please verify your email address or try again later.");
			}
			$("#passwordbtnloader").hide();
		 },
		error: function(){
			showError("Unable to process the request. Please try again later.");
			$("#passwordbtnloader").hide();
		}
	 });
	 return false;
}

function logout(){
    $.ajax({
           type: "GET",
           url: BASE_URL + "logout.do",
           async: false,
    }).done(function( msg ) {
        window.location.href = (BASE_URL);    
    }).error(function(msg){
        console.log("Error occured while logout");
        window.location.href = (BASE_URL);
    });
}

function linkedinLogin(){		
	$("#myModal").modal("hide");	
	var message = "By clicking OK, you allows us to store your LinkedIn information in our database. " +
			" Please read our data policy. Do you accept?"		
	bootbox.confirm(message, function(result) {
		if(result){
			window.location=BASE_URL+'linkedinshowloginscreen.do';
		}
	}); 	
}

function showLoginBox(){	
	$('#forgotpasswordbox').hide();
	$('#signupbox').hide();
	$('#loginbox').show();	
	$("#myModal").modal("show");
}

function login(){	 

  $("#loginbtnloader").show();
  var data = "j_username=" + $("#j_username").val() + "&j_password=" + $("#j_password").val();    
  $.ajax({
      'type': 'POST',
      'url': BASE_URL + "j_spring_security_check",      
      'data': data,
      'dataType': 'json',
       success:function(response){
            if(response.RESULT != null && response.RESULT == "SUCCESS"){
               if(response.REDIRECT){
            	   // admin
                  window.top.location = BASE_URL + response.REDIRECT;
                  return;
               }
               
               var callback = "";
               if($("#callback").val() != ""){
            	   if(window.location.href.indexOf("?") > 0 ){
            		   callback = "&callbackj=" + $("#callback").val();
            	   } else {
            		   callback = "?callbackj=" + $("#callback").val();
            	   }
               }
               
               window.top.location.href = window.top.location.href + callback; 
               location.reload();
               $("#myModal").modal("hide");

            } else {
            	showError("Email or password is wrong.");                
            }
            $("#loginbtnloader").hide();
       }, 
       error : function(){
    	   showError("Unable to process the rquest.");
    	   $("#loginbtnloader").hide();
       }
    });
}



$(function(){
	
	$('#myModal').on('hidden.bs.modal', function () {
		$('#forgotpasswordbox').hide();
		$('#signupbox').hide();
		$('#loginbox').show();
	});
	
	
	$("#loginform").validate({
		rules:{
			j_username : {required:true,email:true},
			j_password : {required:true, minlength:8}
		},messages: {
			j_username: {
				required:"Email is required.",
				email:"Please enter valid email."
			},
			j_password: {
			required: "Password is required.",
			minlength: "Please enter at least 8 characters."
			}
		},
		 submitHandler: function(form) {
			login();
			return false;
		},
		 errorPlacement: function(error, element) {			
			error.insertAfter(element.parent());			
		}
	});
	$("#login-recordar").validate({
		rules : {
			email:{required:true, email:true}
		},
		 submitHandler: function(form) {
			 sendForgotPasswordMail();
			 return false;
		}
	});
	
	$("#signupform").validate({
		rules : {
			email:{required:true, email:true},
			username:{required:true, minlength:5},
			password:{required:true, minlength:8},
			confirmpassword:{required:true, minlength:8, equalTo:"#password"},
			usertype : {required:true}
		},
		 submitHandler: function(form) {
			 registerUser();
			 return false;
		}		
	});
	
	$("#signupform input[name='usertype']").on("click", function(){
		var value  = $(this).val();
		if(value == "INTERVIEWEE") {
			$("#intervieweeSignUpForm").show();
		} else {
			$("#intervieweeSignUpForm").hide();
		}
	});
	
	if(gup("callbackj")!= ""){
		callF(gup("callbackj"));
	}
	
	 
	if(null != LOGIN_USER){
		loadNewMessageCount();
	} 
	
});
