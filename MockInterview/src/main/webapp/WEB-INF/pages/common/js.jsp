<div class="modal modal-static fade" id="processing-modal" role="dialog" aria-hidden="true" data-backdrop="static"
     data-keyboard="false">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-body">
                <div class="text-center"><h4>Processing... </h4>
                <img src="<c:url value='/resources/images/loading.gif'/>" class="icon"/></div> </div>
        </div>
    </div>
</div>

<script type="text/javascript"> 
var BASE_URL = window.location.protocol + "//" + window.location.host + "${pageContext.request.contextPath}/";

/** Paginaion */
var DEFAULT_PAGE_SIZE = 10;
var LOGIN_USER = null;
<sec:authorize access="hasRole('ROLE_USER')">
LOGIN_USER = '<sec:authentication property="principal.username" />';
</sec:authorize> </script>

<!-- Scripts -->
<script type="text/javascript" src="js/jquery.js"></script>
<script>window.jQuery || document.write('<script src="' + "<c:url value='/resources/js/jquery-1.11.0.min.js'/>" + '"><\/script>')</script>
<script type="text/javascript" src="<c:url value='/resources/template/js/jquery.ba-outside-events.min.js' />"></script>
<script type="text/javascript" src="<c:url value='/resources/template/js/jquery.responsive-tabs.js' />"></script>
<script type="text/javascript" src="<c:url value='/resources/template/js/jquery-ui-1.10.4.custom.min.js' />"></script>
<script type="text/javascript" src="<c:url value='/js/jquery.inview.min.js' />"></script>
<script type="text/javascript" src="<c:url value='/resources/template/js/script.js'/>"></script>
<script type='text/javascript'
        src="<c:url value='/resources/js/jquery-fileupload/vendor/jquery.ui.widget.js'/>"></script>
<script type='text/javascript'
        src="<c:url value='/resources/js/jquery-fileupload/jquery.iframe-transport.js'/>"></script>
<script type='text/javascript' src="<c:url value='/resources/js/jquery-fileupload/jquery.fileupload.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/bootstrap.min.js' />"></script>
<script type="text/javascript" src="<c:url value='/resources/js/dateformat.js' />"></script>
<script type="text/javascript" src="<c:url value='/resources/js/bootstrap-datepicker.js' />"></script>
<script type="text/javascript" src="<c:url value='/resources/js/bootstrap-notify.js' />"></script>
<script type="text/javascript" src="<c:url value='/resources/js/bootbox.min.js' />"></script>
<script type="text/javascript" src="<c:url value='/resources/js/star-rating.js' />"></script>
<script type="text/javascript"
        src="<c:url value='/resources/js/jquery-validation-1.13.1/jquery.validate.min.js' />"></script>
<script type="text/javascript"
        src="<c:url value='/resources/js/jquery-validation-1.13.1/additional-methods.js' />"></script>
<script type="text/javascript" src="<c:url value='/resources/js/sha1.js' />"></script>

<!-- Application js -->
<script type="text/javascript" src="<c:url value='/resources/js/app.js' />"></script>

<script type="text/javascript" src="<c:url value='js/jquery.prettyPhoto.js' />"></script>
<script type="text/javascript" src="<c:url value='js/jquery.parallax.js' />"></script>
<script type="text/javascript" src="<c:url value='js/jquery.isotope.min.js' />"></script>
<script type="text/javascript" src="<c:url value='js/jquery.easypiechart.min.js' />"></script>
<script type="text/javascript" src="<c:url value='js/jquery.appear.js' />"></script>
<script type="text/javascript" src="<c:url value='js/wow.min.js' />"></script>
<script type="text/javascript" src="<c:url value='js/jquery.countTo.js' />"></script>
<script type="text/javascript" src="<c:url value='js/smooth-scroll.js' />"></script>
<script type="text/javascript" src="<c:url value='js/canvas.js' />"></script>
<script type="text/javascript" src="<c:url value='js/preloader.js' />"></script>
<script type="text/javascript" src="<c:url value='js/main.js' />"></script>
<script type="text/javascript" src="<c:url value='libraries/mustache/mustache.min.js' />"></script>
<script type="text/javascript" src="<c:url value='js/bootstrap-select.min.js' />"></script>
<script type="text/javascript" src="//maps.google.com/maps/api/js?sensor=true"></script>
<script type="text/javascript" src="<c:url value='js/gmaps.js' />"></script>
<script type="text/javascript" src="<c:url value='js/modernizr.custom.79639.js' />"></script>

<!--  CKEDITOR -->
<script type="text/javascript" src="<c:url value='libraries/ckeditor/ckeditor.js' />"></script>
<script type="text/javascript" src="<c:url value='libraries/ckeditor/adapters/jquery.js' />"></script>

<script type="text/javascript"> 
console.log(BASE_URL)
console.log(window.location.href)
if(BASE_URL == window.location.href  )
    $("#navigation").addClass("homeview");
else
    $("#navigation").addClass("otherview");
</script>    