<div class="list-group list-default" style="width:100%">
    <sec:authorize access="hasRole('ROLE_INTERVIEWER')">
        <a href="<c:url value='/jobapplications.do'/>" id="sidenav_jobapplications" class="list-group-item">Job Applications</a>        
        <a href="<c:url value='/postjob.do'/>" id="sidenav_postjob" class="list-group-item">Post Job</a>
        <a href="<c:url value='/searchcandidates.do'/>" id="sidenav_searchcandidates" class="list-group-item">Search Candidates</a>
    </sec:authorize>
</div>