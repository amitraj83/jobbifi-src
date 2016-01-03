<%@ include file="/WEB-INF/pages/common/taglib.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <%@ include file="/WEB-INF/pages/common/css.jsp" %>
    <title>Deposit Funds</title>
</head>
<body>
<%@ include file="/WEB-INF/pages/common/header.jsp" %>

<div id="wrapper">
    <div id="page-content">
        <div class="container">
            <div class="row">
                <div class="col-md-3 page-sidebar">
                    <div class="list-group list-default">
                        <a href="<c:url value='/finance.do' />" class="list-group-item">Transactions History</a>
                        <a href="<c:url value='/depositfund.do' />" class="list-group-item active">Deposit Funds</a>
                        <a href="<c:url value='/withdrawfund.do' />" class="list-group-item">Withdraw Funds</a>
                    </div>
                </div>

                <div class="col-md-9 page-content">

                    <div class="clearfix">
                        <div class="row">
                            <div class="col-xs-12 col-md-12">

                                <div id="depositalert"></div>
                                <h1 style="margin-top:0px;">Deposit Funds</h1>
                                <hr/>
                                <div class="panel panel-default">
                                    <div class="panel-heading">
                                        <h3 class="panel-title">Deposit funds through Paypal</h3>
                                    </div>
                                    <div class="panel-body">
                                        <p class="help-block " style="text-align:center">
                                            You need a paypal account to deposit the fund in your account.
                                            For more information, visit
                                            <a target="_blank" href="https://www.paypal.com/">Paypal</a>.
                                        </p>

                                        <form class="form-horizontal" id="depositFundForm">
                                            <fieldset>
                                                <div class="form-group">
                                                    <label class="col-sm-3 control-label">Amount </label>

                                                    <div class="col-sm-6">
                                                    	<div class="input-group">
                                                        	<span class="input-group-addon">$</span>
                                                        	<input type="text" class="form-control" name="amount"
                                                            	   id="amounttodeposit">
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="form-group">
                                                    <div class="col-sm-offset-3 col-sm-9">
                                                        <button type="submit" class="btn btn-default">Deposit Fund
                                                        </button>
                                                    </div>
                                                </div>
                                            </fieldset>
                                        </form>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>


                </div>
            </div>
        </div>
    </div>

    <%@ include file="/WEB-INF/pages/common/footer.jsp" %>
    <%@ include file="/WEB-INF/pages/common/js.jsp" %>

    <script type="text/javascript">
    $(document).ready(function () {
    	$("#footer").css("position","fixed");
    	$("#footer").addClass( "navbar-fixed-bottom");
    });
    
        $(function () {
            $("#depositFundForm").validate({
                rules: {
                    amount: {required: true, number: true}
                },
                submitHandler: function (form) {
                    submitDepositForm();
                    return;
                }
            });
        });

        function submitDepositForm() {
            $.ajax({
                type: "POST",
                url: "<c:url value='depositfunds.do'/>",
                data: $("#depositFundForm").serialize()
            }).done(function (msg) {
                var json = jQuery.parseJSON(msg);
                window.location.href = json.REDIRECT_URL;
            });
        }
    </script>
</body>
</html>
