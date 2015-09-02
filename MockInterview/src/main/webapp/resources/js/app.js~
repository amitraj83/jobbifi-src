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
	$.notify({message: message},{type: 'error', 
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

/** ----------------- **/

function loadNewMessageCount(){	
	$.ajax({
		url: BASE_URL + 'getnewmessagecount.do',
		type: "GET",
	}).done(function( res ) {
		var json = jQuery.parseJSON(res);
		if(json.NEW_MESSAGE_COUNT > 0){
			$("#messageCount").html('<span class="label label-success">' + json.NEW_MESSAGE_COUNT + '</span>');
		}
	});
	
	setTimeout(function(){loadNewMessageCount()}, 30*1000);
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
        if(json.response == 2){
        	showWarning("This username already exist. Please try with another username.");            
        } else if(json.response == 1){
            showSuccess("You have been registered successfully.");
            $("#myModal").modal("hide");
        } else if(json.response == -1){
            showError("Error occured while registration.");                      
        }
        
        $("#username").val("");   
        $("#email").val("");    
        $("#password").val("");    
        $("#confirmpassword").val("");  
    }).always(function(jqXHR, textStatus) {
		$("#submitloader").hide();		
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
			if(resData.RESULT && resData.RESULT == "SUCCESS"){
				showSuccess("A link to change your password has been sent at the email address you provided. " +
          		"Please visit the url to change your password");
				$("#forgotpass_email").val("");
				$("#myModal").modal("hide");
			} else {
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
	$("#loginform").validate({
		rules:{
			j_username : {required:true},
			j_password : {required:true, minlength:8}
		},
		 submitHandler: function(form) {
			login();
			return false;
		},
		 errorPlacement: function(error, element) {			
			error.insertAfter(element.parent());			
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
	
	if(gup("callbackj")!= ""){
		callF(gup("callbackj"));
	}
	
	 
	if(null != LOGIN_USER){
		loadNewMessageCount();
	} 
	
});