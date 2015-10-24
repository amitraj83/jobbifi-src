<div class="list-group list-default" style="width:100%">


    <sec:authorize access="hasRole('ROLE_INTERVIEWEE')">
        <a href="<c:url value='/myinterview.do'/>" id="allinterview" class="list-group-item">All Interview</a>
        <a href="<c:url value='/publishinterview.do'/>" id="publishinterview" class="list-group-item">Publish
            Interviews</a>
        <a href="<c:url value='/myinterview.do?status=OPEN'/>" id="openinterview" class="list-group-item">Open
            Interviews</a>
        <a href="<c:url value='/myinterview.do?status=IN PROGRESS'/>" id="currentinterview" class="list-group-item">Current
            Interviews</a>
        <a href="<c:url value='/myinterview.do?status=COMPLETED'/>" id="completedinterview" class="list-group-item">Completed
            Interviews</a>
        <a href="<c:url value='/dispute.do'/>" id="nav-dispute" class="list-group-item">Disputes</a>
    </sec:authorize>

    <sec:authorize access="hasRole('ROLE_INTERVIEWER')">
    	<a class="list-group-item" id="sidenav_searchMock" title="Search Mocks" href="<c:url value='/mocks.do'/>">Search Mocks</a>
        <a href="<c:url value='/awardedbid.do'/>" id="myinterview" class="list-group-item">My Interviews</a>
        <a href="<c:url value='/allbidplaced.do'/>" id="allbid" class="list-group-item">My Bids</a>
        <a href="<c:url value='/dispute.do'/>" id="nav-dispute" class="list-group-item">Disputes</a>        
    </sec:authorize>

    <!--<a href="<c:url value='/dispute.do'/>" id="nav-dispute" class="list-group-item">Disputes</a>-->


</div>