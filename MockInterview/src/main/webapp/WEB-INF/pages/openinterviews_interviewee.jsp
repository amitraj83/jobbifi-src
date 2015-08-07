<%@ include file="/WEB-INF/pages/common/taglib.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <%@ include file="/WEB-INF/pages/common/css.jsp" %>
    <title>Open Interview</title>
</head>
<body>
<%@ include file="/WEB-INF/pages/common/header.jsp" %>


<div id="wrapper">
    <div class="container">
        <div class="row">
            <div class="col-md-3">
                <div class="list-group" style="width:100%">
                    <a href="<c:url value='allinterview.do'/>" id="interviewee_allinterview" class="list-group-item">All
                        Interview</a>
                    <a href="<c:url value='publishinterview.do'/>" id="interviewee_publishinterview"
                       class="list-group-item">Publish Interviews</a>
                    <a href="javascript:void(0);" id="interviewee_open" class="list-group-item active">Open
                        Interviews</a>
                    <a href="<c:url value='currentinterview.do'/>" id="interviewee_current" class="list-group-item">Current
                        Interviews</a>
                    <a href="<c:url value='completedinterview.do'/>" id="interviewee_completed" class="list-group-item">Completed
                        Interviews</a>
                    <a href="javascript:void(0);" id="interviewee_dispute" class="list-group-item">Disputes</a>
                </div>
            </div>
            <!-- Blog Entries Column -->


            <div class="col-md-8">
                <div id="interviewee_maincontent" class="interviewee_tabs">

                </div>


            </div>
        </div>
    </div>
</div>


<%@ include file="/WEB-INF/pages/common/footer.jsp" %>
<%@ include file="/WEB-INF/pages/common/js.jsp" %>

<script type="text/javascript">
    $(function () {
        loadAllInterview();
    });

    function loadAllInterview() {
        $.ajax({
            type: "GET",
            url: BASE_URL + "/getinterviewlist.do",
            async: false
        }).done(function (res) {
            var resData = jQuery.parseJSON(res);
            var allINterviewDiv = '<h3>All Interviews</h3>' +
                    '<hr>' +
                    '	<table class="table table-hover">' +
                    '	    <thead>' +
                    '		<tr>' +
                    '		    <th>Title&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</th>' +
                    '		    <th>Bids&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</th>' +
                    '		    <th>Status</th>' +
                    '		    <th>Date</th>' +
                    '		</tr>' +
                    '	    </thead>' +
                    '	    <tbody id="interviewee_allbody">' +
                    '	    </tbody>' +
                    '	</table>';

            $("#interviewee_maincontent").html(allINterviewDiv);
            for (var i = 0; i < resData.MY_INTERVIEW_AS_INTERVIEWEE.length; i++) {
                if (getInterviewStatusLiteral(resData.MY_INTERVIEW_AS_INTERVIEWEE[i].status) == 'OPEN') {
                    var action = "<select class=\"callintervieweeactions\" alt=\"" + resData.MY_INTERVIEW_AS_INTERVIEWEE[i].id + "\"></select>";
                    var interviewee_aninterview = '        <tr style="cursor: pointer;" class="interviewee_interview" iid="' + resData.MY_INTERVIEW_AS_INTERVIEWEE[i].id + '">' +
                                    '            <td> ' + resData.MY_INTERVIEW_AS_INTERVIEWEE[i].title + ' </td>' +
                                    '            <td> ' + resData.ibid.length + ' </td>' +
                                    '            <td> ' + getInterviewStatusLiteral(resData.MY_INTERVIEW_AS_INTERVIEWEE[i].status) + ' </td>' +
                                    '            <td> ' + prettyDate(new Date(resData.MY_INTERVIEW_AS_INTERVIEWEE[i].dt)) + ' </td>' +
                                    '        </tr>'
                            ;
                    $("#interviewee_allbody").append(interviewee_aninterview);
                }
            }
            console.log(res);
        });
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


    function getBidCount() {
        var count = 0;


        return count;
    }
    /*
     var postedInterviewsMap = new Map;
     var awardedInterviewsMap = new Map;
     var InterviewsWhereIBidMap = new Map;
     var allBidsReceived = new Map;
     var allBidsPosted = new Map;

     $(document).ready(function() {

     $(".interviewee_tabs").hide();

     var showAllInterviews = function(){
     var allINterviewDiv = '<h3>All Interviews</h3>'+
     '<hr>'+
     '	<table class="table table-hover">'+
     '	    <thead>'+
     '		<tr>'+
     '		    <th>Title&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</th>'+
     '		    <th>Bids&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</th>'+
     '		    <th>Status</th>'+
     '		    <th>Date</th>'+
     '		</tr>'+
     '	    </thead>'+
     '	    <tbody id="interviewee_allbody">'+
     '	    </tbody>'+
     '	</table>';


     $("#interviewee_maincontent").html(allINterviewDiv);

     for(var i = 0; i++ < postedInterviewsMap.size; postedInterviewsMap.next()){
     var interview = postedInterviewsMap.value();
     var action = "<select class=\"callintervieweeactions\" alt=\""+interview.getId()+"\">"+getInterviewActions()+"</select>";
     var interviewee_aninterview = '        <tr style="cursor: pointer;" class="interviewee_interview" iid="'+interview.getId()+'">'+
     '            <td> '+interview.getTitle()+' </td>'+
     '            <td> '+interview.getBidCount()+' </td>'+
     '            <td> '+getInterviewStatusLiteral(interview.getStatus())+' </td>'+
     '            <td> '+prettyDate(new Date(interview.getDate()))+' </td>'+
     '        </tr>'
     ;
     $("#interviewee_allbody").append(interviewee_aninterview);
     }

     $(".interviewee_interview").click(function(){
     var iid = $(this).attr('iid');
     showAnINterviewScreen(iid);
     });
     }

     showAllInterviews();
     $("#interviewee_allinterview").click(function(){showAllInterviews();});

     var showAnINterviewScreen = function(iid){
     $.get("interviewee_interview.html", function(data) {
     var interview = getInterviewById(iid);
     var attachment = "";
     if(interview.getAttachmentName() != ""){
     attachment = "<p><a target='_blank' href='"+BASE_URL+ interview.getAttachmentURL() +"'>"+interview.getAttachmentName()+"</a></p>";
     }
     var screen = data.split("$ititle$").join(interview.getTitle())
     .split("$istatus$").join(getInterviewStatusLiteral(interview.getStatus()))
     .split("$postedon$").join(dateFormat(interview.getDate(), "mmmm dS, yyyy, h:MM:ss TT"))
     .split("$idescription$").join(interview.getDescription())
     .split("$iskills$").join(jQuery.parseJSON(interview.getSkills()))
     .split("$attachment$").join(attachment);

     var bidJSON = getBidsForInterviewId(iid);
     var theBID ;
     var accepted = false;
     for(var n =0;n<bidJSON.length;n++){
     if(bidJSON[n].getIid() == iid){
     if(bidJSON[n].getStatus() == 1)
     {	accepted = true;
     theBID = bidJSON[n];
     }
     }
     }
     var allBids = '';
     for(var j =0;j<bidJSON.length;j++){
     if(bidJSON[j].getIid() == iid){
     var picurl = "";
     var onOrOff = "offline";
     var reputation = 0;
     var reviewcount = 0;


     $.ajax({
     type: "GET",
     url: BASE_URL+"userexternaldata.do",
     data:"username="+bidJSON[j].getBidder(),
     async:false
     }).done(function( res ) {
     var resData = jQuery.parseJSON(res);
     picurl = resData.profilepic;
     if(resData.isonline == true || res =="true")
     onOrOff = "online";
     else
     onOrOff = "offline";
     reputation = resData.rating;
     reviewcount = resData.reviewCount;

     });



     var action = '<button alt="'+iid+'" bidid="'+bidJSON[j].getId()+'"  type="button" class="btn btn-success btn-xs awardinterview">&nbsp;&nbsp;&nbsp;Award&nbsp;&nbsp;&nbsp;</button> ';
     if(accepted){
     if(bidJSON[j].getStatus() == 2)
     action = '<span class="label label-warning">Rejected</span>';
     if(bidJSON[j].getStatus() == 1)
     action = '<span class="label label-success">Accepted</span>';
     }

     var bidRow = '<tr>'+
     '<td class="col-md-4">	<div class="row"><div class="col-md-6">'+
     '	<img class="img-responsive img-hover" src="'+picurl+'" width="100px" height="100px" alt="">'+
     '	</div>'+
     '	<div class="col-md-6">'+getUserProfileLink(bidJSON[j].getBidder())+'<br>'+getStarView(reputation)+'<br>'+reviewcount+' Reviews</div></div></td>'+
     '<td class="col-md-4">'+bidJSON[j].getMessage()+'</td>'+
     '<td  class="col-md-2">'+bidJSON[j].getPrice()+'</td>'+
     '<td class="col-md-3">'+prettyDate(new Date(Number(bidJSON[j].getDate())))+'</td>'+
     '<td class="col-md-3">'+action+'</td>'+
     '</tr>';
     allBids = allBids + bidRow;


     }
     }

     screen = screen.split("$allbidrow$").join(allBids)
     .split("$postedby$").join(''+getUserProfileLink(interview.getInterviewee())+'')
     .split("$postedbyrating$").join(getStarView(user.getReputation()))
     .split("$postedbypic$").join(user.getProfilePic())
     .split("$totalescrowed$").join("$"+interview.getEB())
     ;

     var allrows = "";
     $.ajax({
     type: "POST",
     url: BASE_URL+"escrowlist.do",
     data: "iid="+interview.getId(),
     async:false
     }).done(function( res ) {
     var resData = jQuery.parseJSON(res);

     var totalReleased = 0;

     for(var i =0;i<resData.escrowlist.length;i++){
     var statusButton = "";
     if(resData.escrowlist[i].status == "0"){

     statusButton ='<button iid="'+resData.escrowlist[i].iid+'" escid="'+resData.escrowlist[i].id+'" esc_amount="'+resData.escrowlist[i].amount+'" type="button" class="btn btn-warning btn-xs releasefundsbutton">&nbsp;&nbsp;&nbsp;Release&nbsp;&nbsp;&nbsp;</button>';
     }else
     {
     statusButton = '<span class="label label-success">Released</span>';
     totalReleased = totalReleased + Number(resData.escrowlist[i].amount);
     }

     allrows = allrows + '<tr>'+
     '<td class="col-md-3">'+resData.escrowlist[i].visibleId+'</td>'+
     '<td  class="col-md-3">'+resData.escrowlist[i].amount+'</td>'+
     '<td class="col-md-3">'+statusButton+'</td>'+
     '<td class="col-md-3">'+prettyDate(new Date(Number(resData.escrowlist[i].date)))+'</td>'+
     '</tr>';


     }
     screen = screen.split("$totalreleased$").join("$"+totalReleased)
     .split("$allescrowareas$").join(allrows);

     });
     if(theBID != null && theBID != undefined)
     {
     screen = screen.split("$totalBudget$").join("$"+theBID.getPrice());
     $("#currentinterviewdata").attr("iid", iid);
     $("#currentinterviewdata").attr("interviewer", theBID.getBidder());

     }
     else
     screen = screen.split("$totalBudget$").join("N/A");

     screen = screen.split("$yourbalance$").join("$"+user.getBalance());
     screen = screen.split("$alt$").join(iid);

     $("#interviewee_maincontent").empty();;
     $("#interviewee_maincontent").html(screen);
     $(".interviewee_tabs").hide();
     if(theBID != null && theBID != undefined)
     {
     $("#currentinterviewdata").attr("iid", iid);
     $("#currentinterviewdata").attr("interviewer", theBID.getBidder());

     var param = "user="+user.getUserName()+"&iid="+iid+"&rateFor="+theBID.getBidder();
     $.ajax({
     type: "GET",
     url: BASE_URL+"ratingallowed.do",
     data: param,
     }).done(function( res ) {
     var resData = jQuery.parseJSON(res);
     if(resData.result ==  false){
     $("#rateinterviewer").html('<div class="alert alert-danger" role="alert">Rating disabled: You have already rated or not completed the interview.</div>');
     }
     });

     }
     else{
     $("#rateinterviewer").html('<div class="alert alert-danger" role="alert">Rating disabled: The interview is not completed yet.</div>');

     }



     $(document).on('click', "#submitrating", function(){
     var iid = $(this).attr("alt");
     var param = "";
     param = param + "1="+rating1+"&";
     param = param + "2="+rating2+"&";
     param = param + "3="+rating3+"&";
     param = param + "4="+rating4;

     var interview = getInterviewById(iid);
     var rateFor = "";
     if(user.getUserName() == interview.getInterviewee()){
     rateFor = interview.getInterviewer();
     }
     else{
     rateFor = interview.getInterviewee();
     }
     param = param +"&msg="+$("#new-review").val()+"&iid="+iid+"&rateFor="+rateFor;



     $.ajax({
     type: "GET",
     url: BASE_URL+"rate.do",
     data: param
     }).done(function( res ) {
     var resData = jQuery.parseJSON(res);
     if(resData.code ==  23){

     if(user.getUserName() == interview.getInterviewee()){
     //the user is interviewee
     interview.setIntervieweeRatedForInterviewer(true);
     }
     else{
     //the user is interviewer
     interview.getInterviewerRatedForInterviewee(true);
     }
     $("#rateinterviewer").prepend('<div class="alert alert-success" role="alert">Thank you. Your ratings have been saved.</div>');
     }
     else
     $("#rateinterviewer").prepend('<div class="alert alert-danger" role="alert">Sorry. Try again later.</div>');

     });
     return;

     });

     $(document).on('click',"#interviewee_interview_title",function(){
     preventDefault();
     });
     });
     }


     $(document).on('click',"#createescrowbutton",function(){
     alert("Creating escrow deposit");
     var iid = $("#currentinterviewdata").attr("iid");
     var interviewer = $("#currentinterviewdata").attr("interviewer");
     var escrowamount = $("#interviewee_interview_escrowamount").val();

     var bidJSON = getBidsForInterviewId(iid);
     var theBID ;
     var accepted = false;
     for(var n =0;n<bidJSON.length;n++){
     if(bidJSON[n].getIid() == iid){
     if(bidJSON[n].getStatus() == 1)
     {	accepted = true;
     theBID = bidJSON[n];
     }
     }
     }

     if(theBID != null && theBID != undefined)
     {
     var interview = getInterviewById(iid);
     var bal = user.getBalance();
     if(( Number(interview.getEB()) + Number(escrowamount) > Number(theBID.getPrice())) )
     {
     alert("Escrow more than total budget not allowed.");
     return;
     }
     if(Number(escrowamount) < Number(bal)  ){
     var param = "iid="+iid+"&interviewer="+interviewer+"&amount="+escrowamount;
     $.ajax({
     type: "POST",
     url: BASE_URL+"escrowdeposit.do",
     data: param
     }).done(function( res ) {
     var resData = jQuery.parseJSON(res);
     if(resData.code == 16)
     {

     var allrows = "";
     var totalReleased = 0;
     for(var i =0;i<resData.escrowlist.length;i++){
     var statusButton = "";
     if(resData.escrowlist[i].status == "0"){

     statusButton ='<button iid="'+resData.escrowlist[i].iid+'" escid="'+resData.escrowlist[i].id+'" esc_amount="'+resData.escrowlist[i].amount+'" type="button" class="btn btn-warning btn-xs releasefundsbutton">&nbsp;&nbsp;&nbsp;Release&nbsp;&nbsp;&nbsp;</button>';
     }else
     {
     statusButton = '<span class="label label-success">Released</span>';
     totalReleased = totalReleased + Number(resData.escrowlist[i].amount);
     }

     allrows = allrows + '<tr>'+
     '<td class="col-md-3">'+resData.escrowlist[i].visibleId+'</td>'+
     '<td  class="col-md-3">'+resData.escrowlist[i].amount+'</td>'+
     '<td class="col-md-3">'+statusButton+'</td>'+
     '<td class="col-md-3">'+prettyDate(new Date(Number(resData.escrowlist[i].date)))+'</td>'+
     '</tr>';


     }
     $("#interviewee_interview_allescrows").empty();
     $("#interviewee_interview_allescrows").html(allrows);
     var interview = getInterviewById(iid);
     interview.setStatus(resData.status);
     interview.setEB(resData.eb);
     user.setBalance(resData.bal);
     $("#escrow_screen_totalescrow").html("$"+resData.eb);
     $("#escrow_screen_totalreleased").html("$"+totalReleased);
     $("#escrowscreen_balance").html("$"+resData.bal);
     $("#interviewee_interview_escrowamount").html("");




     }
     else{
     alert("Escrow deposit failed:"+resData.message);
     }
     });

     }
     else{
     alert("Not enough balance");
     }
     }
     });


     $( document.body ).on('click', '.dropdown-menu li', function( event ) {
     var $target = $( event.currentTarget );
     $target.closest( '.btn-group' )
     .find( '[data-bind="label"]' ).text( $target.text()).end()
     .children( '.dropdown-toggle' ).dropdown( 'toggle' );
     return false;
     });



     $(document).on( 'click', '.releasefundsbutton', function () {

     var answer = confirm ("This money cannot be reverted back. Do you really want to release the funds?")
     if (answer){

     var iid = $(this).attr('iid');
     var interview = getInterviewById(iid);
     var param = "iid="+$(this).attr('iid')+"&interviewer="+interview.getInterviewer()+
     "&amount="+$(this).attr('esc_amount')+"&escid="+$(this).attr('escid');
     var element = $(this);

     $.ajax({
     type: "POST",
     url: BASE_URL+"releasefunds.do",
     data: param
     }).done(function( res ) {
     var resData = jQuery.parseJSON(res);
     if(resData.code == 19){
     var interview = getInterviewById(iid);
     interview.setStatus(resData.status);
     interview.setEB(resData.eb);
     user.setBalance(resData.bal);
     $(element).parent().html('<span class="label label-success">Released</span>');
     $("#escrow_screen_totalescrow").html("$"+resData.eb);
     $("#escrow_screen_totalreleased").html("$"+resData.totalreleased);
     $("#escrowscreen_balance").html("$"+resData.bal);
     var bidJSON = getBidsForInterviewId(iid);
     var theBID ;
     for(var n =0;n<bidJSON.length;n++){
     if(bidJSON[n].getIid() == iid){
     if(bidJSON[n].getStatus() == 1)
     theBID = bidJSON[n];

     }
     }

     if(Number(resData.totalreleased) >= Number(theBID.getPrice()))
     {
     $("#interviewee_interview_status").html(getInterviewStatusLiteral(interview.getStatus()));
     }

     }
     else{
     alert(resData.message);
     }
     });

     }
     });


     $("#interviewee_publishinterview").click(function(){
     $(".interviewee_tabs").hide();
     $("#interviewee_maincontent").html($("#interviewee_publish").html());
     $("#attachifile").click(function(){
     $("#postinterviewdoc").trigger('click');
     });

     $('#postinterviewdoc').fileupload("destroy");
     $('#postinterviewdoc').fileupload({
     dataType: 'json',
     maxChunkSize: 20000000,
     done: function (e, data) {
     var jsonResponse = jQuery.parseJSON(data.jqXHR.responseText);
     $("#selectedifile").html(jsonResponse.originalfn);
     $("#interviewdocid").val(jsonResponse._id);
     },
     add: function (e, data) {
     data.submit();
     }
     });

     $("#publishinterviewbtn").click(function(){
     var returnValue = postInterview($("#interviewee_title").val(),
     $("#ipublish_industry").val(),
     $("#interviewee_skills").val(),
     $("#ipublish_desc").val(),
     $("#ipublish_budget").val(),
     $("#ipublish_exp").val(),
     $("#interviewdocid").val());

     });
     });


     $("#interviewee_open").click(function(){
     $(".interviewee_tabs").hide();
     $("#interviewee_maincontent").html($("#interviewee_openi").html());
     for(var i = 0; i++ < postedInterviewsMap.size; postedInterviewsMap.next()){
     var interview = postedInterviewsMap.value();
     if(interview.getStatus() == 0 || interview.getStatus() == 7){
     var interviewee_aninterview = '<tr class="interviewee_interview" iid="'+interview.getId()+'">'+
     '            <td> '+interview.getTitle()+' </td>'+
     '            <td> '+interview.getBidCount()+' </td>'+
     '            <td> '+getInterviewStatusLiteral(interview.getStatus())+' </td>'+
     '            <td> '+prettyDate(new Date(interview.getDate()))+' </td>'+
     '        </tr>'
     ;
     $("#interviewee_openbody").append(interviewee_aninterview);
     }
     $("#interviewee_openinterviews").append(interviewee_aninterview);
     }
     $(".interviewee_interview").click(function(){
     var iid = $(this).attr('iid');
     showAnINterviewScreen(iid);
     });
     });

     $("#interviewee_current").click(function(){
     $(".interviewee_tabs").hide();
     $("#interviewee_maincontent").html($("#interviewee_currenti").html());
     for(var i = 0; i++ < postedInterviewsMap.size; postedInterviewsMap.next()){
     var interview = postedInterviewsMap.value();
     if(interview.getStatus() == 1 ||interview.getStatus() == 2 || interview.getStatus() == 3 || interview.getStatus() == 4){
     var interviewee_aninterview = '<tr class="interviewee_interview" iid="'+interview.getId()+'">'+
     '            <td> '+interview.getTitle()+' </td>'+
     '            <td> '+interview.getBidCount()+' </td>'+
     '            <td> '+getInterviewStatusLiteral(interview.getStatus())+' </td>'+
     '            <td> '+prettyDate(new Date(interview.getDate()))+' </td>'+
     '        </tr>'
     ;
     $("#interviewee_currentbody").append(interviewee_aninterview);
     }
     }
     $(".interviewee_interview").click(function(){
     var iid = $(this).attr('iid');
     showAnINterviewScreen(iid);
     });
     });


     $("#interviewee_completed").click(function(){
     $(".interviewee_tabs").hide();
     $("#interviewee_maincontent").html($("#interviewee_completedi").html());
     for(var i = 0; i++ < postedInterviewsMap.size; postedInterviewsMap.next()){
     var interview = postedInterviewsMap.value();
     if(interview.getStatus() == 5 ||interview.getStatus() == 6 || interview.getStatus() == 8 ){
     var interviewee_aninterview = '<tr class="interviewee_interview" iid="'+interview.getId()+'">'+
     '            <td> '+interview.getTitle()+' </td>'+
     '            <td> '+interview.getBidCount()+' </td>'+
     '            <td> '+getInterviewStatusLiteral(interview.getStatus())+' </td>'+
     '            <td> '+prettyDate(new Date(interview.getDate()))+' </td>'+
     '        </tr>'
     ;
     $("#interviewee_completedbody").append(interviewee_aninterview);
     }
     }
     $(".interviewee_interview").click(function(){
     var iid = $(this).attr('iid');
     showAnINterviewScreen(iid);
     });
     });


     $("#interviewee_search").click(function(){
     $(".interviewee_tabs").hide();
     $("#interviewee_maincontent").html($("#interviewee_searchview").html());

     $(".searchuserrating").jRating({
     type:'small',
     length : 5,
     isDisabled : true
     });
     });

     // This event is fire once as the bind to page load once
     $(document).on('click', '#senddisputemessage', function () {
     var did = $(this).attr("did");
     var fid = $("#disputemessagedocid").val();
     var msg = $("#dispute_message").val().replace(/\n/g, '<br />');
     $.ajax({
     type: "GET",
     url: BASE_URL+"senddisputemessage.do",
     data: "did="+did+"&fid="+fid+"&msg="+msg
     }).done(function( msg ) {
     var jsonResponse = jQuery.parseJSON(msg);
     if(jsonResponse.status == "1"){
     var fid = jsonResponse.message.fid;
     var attachment = "";
     if((fid != null) && (fid != "")){
     attachment = '<br><a href="'+jsonResponse[fid].url+'">'+jsonResponse[fid].fn+'</a>';
     }
     var messagetrail = '<div class="panel panel-warning">'+
     '<div class="panel-heading">'+
     '  <h3 class="panel-title"><span><i class="fa fa-user"></i>&nbsp'+jsonResponse.message.messageBy+'</span><span class="pull-right "><h5 style="margin-top:1px">'+prettyDate(new Date(Number(jsonResponse.message.time)))+'</h5></span></h3>'+
     '</div>'+
     '<div class="panel-body">'+jsonResponse.message.message +attachment+
     '</div>'+
     '</div>';
     $("#dispute_message").val("");
     $("#selecteddisputemessagefile").html("");
     $("#disputemessagedocid").val("");
     $("#yourmesssages").append(messagetrail);
     }
     });
     });

     $("#interviewee_dispute").click(function(){
     // need to remove the jquery listner from the rows attached
     // they are double firing the request

     $("#currentdisputestable").html('');
     for(var i = 0; i++ < openDisputes.size; openDisputes.next()){
     var dispute = openDisputes.value();
     var clsdate = "";
     if(Number(dispute.getCloseTime()) ==0)
     clsdate = "--";
     else
     clsdate = prettyDate(new Date(Number(dispute.getCloseTime())));
     $("#currentdisputestable").append(
     '<tr  class="disputerow" did="'+dispute.getId()+'"> '+
     '	<td><a href="#">#'+dispute.getVID()+'</a></td>'+
     '	<td>'+dispute.getITitle()+'</td>'+
     '	<td>'+dispute.getWith()+'</td>'+
     '	<td>'+dispute.getAmount()+'</td>'+
     '	<td>'+dispute.getResult()+'</td>'+
     '	<td>'+ clsdate+'</td>'+
     '</tr>');
     }

     $("#closeddisputestable").html('');
     for(var i = 0; i++ < closedDisputes.size; closedDisputes.next()){
     var dispute = closedDisputes.value();
     var clsdate = "";
     if(Number(dispute.getCloseTime()) ==0)
     clsdate = "--";
     else
     clsdate = prettyDate(new Date(Number(dispute.getCloseTime())));
     $("#closeddisputestable").append(
     '<tr  class="disputerow" did="'+dispute.getId()+'"> '+
     '	<td><a href="#">#'+dispute.getVID()+'</a></td>'+
     '	<td>'+dispute.getITitle()+'</td>'+
     '	<td>'+dispute.getWith()+'</td>'+
     '	<td>'+dispute.getAmount()+'</td>'+
     '	<td>'+dispute.getResult()+'</td>'+
     '	<td>'+ clsdate+'</td>'+
     '</tr>');
     }

     $(".interviewee_tabs").hide();
     $("#interviewee_maincontent").html($("#interviewee_disputeview").html());

     var disputeAbleNotFound = true;
     for(var i = 0; i++ < postedInterviewsMap.size; postedInterviewsMap.next()){
     var interview = postedInterviewsMap.value();
     if((Number(interview.getEB()) > 0 ) && (interview.getStatus() != 9) )
     {
     disputeAbleNotFound = false;
     $("#disputable_interviews").append('<option value="'+interview.getId()+'">'+interview.getTitle()+'</option>');
     //$('<option>').val(interview.getId()).text(interview.getTitle()).appendTo('#disputeinterviewselect');
     }
     }
     for(var i = 0; i++ < awardedInterviewsMap.size; awardedInterviewsMap.next()){
     var interview = awardedInterviewsMap.value();
     if((Number(interview.getEB()) > 0 ) && (interview.getStatus() != 9) )
     {
     disputeAbleNotFound = false;
     $("#disputable_interviews").append('<option value="'+interview.getId()+'">'+interview.getTitle()+'</option>');
     }
     }

     if(disputeAbleNotFound){
     $('#submitdispute').attr("disabled", true);
     }

     $(document).on('click', '#postdisputedocbtn', function () {
     $("#postdisputedoc").trigger('click');
     });

     $('#postdisputedoc').fileupload("destroy");
     $('#postdisputedoc').fileupload({
     dataType: 'json',
     maxChunkSize: 20000000,
     done: function (e, data) {
     var jsonResponse = jQuery.parseJSON(data.jqXHR.responseText);
     $("#selecteddisputefile").html(jsonResponse.originalfn)
     $("#disputedocid").val(jsonResponse._id);
     },
     });


     $(document).on('click', '#submitdispute', function () {
     var iid = $("#disputable_interviews option:selected").val();
     if(iid == -1){
     alert("You have not selected any interview.");
     return;
     } else {
     var disputefileid = $("#disputedocid").val();
     var disputeMessage = $("#disputemessage").val();
     var interview = getInterviewById(iid);
     $.ajax({
     type: "POST",
     url: BASE_URL+"createdispute.do",
     data: "iid="+iid+"&fileid="+disputefileid+"&msg="+disputeMessage+"&amount="+interview.getEB()
     }).done(function( msg ) {
     var jsonResponse = jQuery.parseJSON(msg);
     if(jsonResponse.status == "1")
     {

     interview.setStatus(9);

     $("#currentdisputestable").append(
     '<tr  class="disputerow" did="'+jsonResponse.id+'"> '+
     '	<td>#'+jsonResponse.vid+'</td>'+
     '	<td>'+jsonResponse.interview+'</td>'+
     '	<td>'+jsonResponse.disputewith+'</td>'+
     '	<td>'+jsonResponse.amount+'</td>'+
     '	<td>PENDING</td>'+
     '	<td>-</td>'+
     '</tr>');

     $("#createdispute").prepend('<div class="alert alert-success" role="alert">You have successfully created the dispute. Check the current dispute tab. </div>');
     $("#selecteddisputefile").html('')
     $("#disputedocid").val('');
     }
     else if(jsonResponse.status == "2"){
     $("#createdispute").prepend('<div class="alert alert-info" role="alert">The interview is already in the dispute status</div>');
     }
     else {
     $("#createdispute").prepend('<div class="alert alert-danger" role="alert">Sorry, You cannot raise the dispute at the moment. </div>');
     }
     });
     }
     });


     $(document).on("click", ".disputerow", function(){
     $(".interviewee_tabs").hide();

     var did = $(this).attr("did");
     var dwith = $(this).children().eq(2).text();
     var damount = $(this).children().eq(3).text();
     $.ajax({
     type: "GET",
     url: BASE_URL+"retrievedispute.do",
     data: "did="+did
     }).done(function( msg ) {
     var screen = $("#a_dispute").html();
     var jsonResponse = jQuery.parseJSON(msg);

     var status = jsonResponse.disputeStatus;

     var iid = jsonResponse.iid;
     var interview = getInterviewById(iid);

     screen = screen.split("$dititle$").join(interview.getTitle())
     .split("$you$").join(''+getUserProfileLink(user.getUserName())+'')
     .split("$with$").join(''+getUserProfileLink(dwith)+'')
     .split("$dstatus$").join(status)
     .split("$damount$").join(damount)
     .split("$damount$").join(damount)
     ;
     screen = screen.split("$did$").join(did)
     if(status == "OPEN")
     {
     screen = screen.split("$actionbutton$").join('Close and Release Funds');
     screen = screen.split("$dresult$").join("PENDING")
     }
     else{

     screen = screen.split("$dresult$").join(jsonResponse.disputeResult)
     }

     $("#interviewee_maincontent").html(screen);
     var CSS_Class="";
     $.each(jsonResponse.list, function(id, data) {
     var fid = data.fid;
     var attachment = "";
     if((fid != null) && (fid != "")){
     attachment = '<br><a href="'+jsonResponse[fid].url+'">'+jsonResponse[fid].fn+'</a>';
     }

     if(user.getUserName() === data.messageBy){
     CSS_Class="panel-warning";
     }else{
     CSS_Class="panel-danger";
     }
     var messagetrail = '<div class="panel '+CSS_Class+'">'+
     '<div class="panel-heading">'+
     '  <h3 class="panel-title"><span><i class="fa fa-user"></i>&nbsp'+data.messageBy+'</span><span class="pull-right "><h5 style="margin-top:1px">'+prettyDate(new Date(Number(data.time)))+'</h5></span></h3>'+
     '</div>'+
     '<div class="panel-body">'+data.message +attachment+
     '</div>'+
     '</div>';

     $("#messagetrail").append(messagetrail);

     });
     $("#messagetrail").append('<div id="yourmesssages"></div><div id="disputemessageform"></div>');
     var messagetrail = '<div class="panel panel-warning">'+
     '<div class="panel-heading">'+
     '  <h3 class="panel-title"><span><i class="fa fa-user"></i>&nbsp'+user.getUserName()+'</span><span class="pull-right"></span></h3>'+
     '</div>'+
     '<div class="panel-body" style="padding:5px;">'+
     '<textarea style="width:100%" id="dispute_message"></textarea>'+
     '<div class="row">'+
     '	<div class="col-md-7"><div class="form-group">'+
     '<input id="disputemessagedoc" type="file" name="file" data-url="aauth/fileupload.do?type=disputedocument" '+
     'multiple style="opacity:0; filter:alpha(opacity: 0);display:none">'+
     '<div><button id="disputemessagebtn" type="button" class="btn btn-sm btn-default">Upload</button></div>'+
     '<div id="selecteddisputemessagefile"></div><input type="hidden" id="disputemessagedocid">'+
     '</div></div>'+
     '	<div class="col-md-5"><button type="submit" did="'+did+'" id="senddisputemessage" class="btn btn-info pull-right">Send Message</button></div>'+
     '</div>'+
     '</div>'+
     '</div>';
     if(status == "OPEN") {

     $("#disputemessageform").html(messagetrail);

     $('#disputemessagebtn').click(function () {
     $("#disputemessagedoc").trigger('click');
     });

     $('#disputemessagedoc').fileupload("destroy");
     $('#disputemessagedoc').fileupload({
     dataType: 'json',
     maxChunkSize: 20000000,
     done: function (e, data) {
     var jsonResponse = jQuery.parseJSON(data.jqXHR.responseText);
     $("#selecteddisputemessagefile").html(jsonResponse.originalfn)
     $("#disputemessagedocid").val(jsonResponse._id);
     },
     });

     } else {
     $("#intervieweeclosingdispute").hide();
     var closingnote = 	'<div class="panel panel-danger">'+
     '	  <div class="panel-heading">'+
     '    <h3 class="panel-title"><span><i class="fa fa-user"></i>&nbsp;Admin</span><span class="pull-right "><h5 style="margin-top:1px">'+prettyDate(new Date(jsonResponse.timeclosed))+'</h5></span></h3>'+
     '  </div>'+
     '  <div class="panel-body">'+
     '  This dispute has been resolved and closed.'+
     '  </div>'+
     '</div>';
     $("#disputemessageform").html(closingnote);
     }

     /*if(status == "OPEN"){
     DISPUTE_MSG_BOX = DISPUTE_MSG_BOX.split("$did$").join(jsonResponse.did);
     $("#dmsgbox").append(DISPUTE_MSG_BOX);

     $(document).on('click', '#postdisputedocbtn', function () {
     $("#postdisputedoc").trigger('click');
     });

     $('#postdisputedoc').fileupload("destroy");
     $('#postdisputedoc').fileupload({
     dataType: 'json',
     maxChunkSize: 20000000,
     done: function (e, data) {
     var jsonResponse = jQuery.parseJSON(data.jqXHR.responseText);
     $("#selecteddisputefile").html(jsonResponse.originalfn)
     $("#disputedocid").attr("value",jsonResponse._id);
     },
     });
     }*/

    /*
     });
     });
     });

     $(document).on('click', '#intervieweeclosingdispute', function () {
     var element = $(this);
     var answer = confirm ("Your will loose your dispute funds and cannot be reverted. Are you sure?")
     if (answer)
     {
     var did = $(this).attr("did");
     $.ajax({
     type: "GET",
     url: BASE_URL+"intervieweeclosingdispute.do",
     data: "did="+did
     }).done(function( msg ) {
     var jsonResponse = jQuery.parseJSON(msg);
     if(jsonResponse.status == "1"){
     var updatedinterview = getInterviewById(jsonResponse.iid);
     updatedinterview.setStatus(Number(jsonResponse.istatus));
     }

     var closingnote = 	'<div class="panel panel-danger">'+
     '	  <div class="panel-heading">'+
     '    <h3 class="panel-title"><span><i class="fa fa-user"></i>&nbsp;Admin</span><span class="pull-right "><h5 style="margin-top:1px">'+prettyDate(new Date())+'</h5></span></h3>'+
     '  </div>'+
     '  <div class="panel-body">'+
     '  This dispute has been resolved and closed.'+
     '  </div>'+
     '</div>';
     $("#disputemessageform").html(closingnote);
     $("#headingdstatus").html("CLOSED");
     $("#disputesidebosresult").html("LOST");
     $(element).hide();
     });
     }
     });

     $('.list-group a').click(function(e) {
     $('.list-group-item').removeClass('active');
     var $this = $(this);
     if (!$this.hasClass('active')) {
     $this.addClass('active');
     }
     e.preventDefault();
     });

     });/*/
</script>
</body>
</html>