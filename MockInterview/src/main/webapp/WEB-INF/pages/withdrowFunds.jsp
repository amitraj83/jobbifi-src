<%@ include file="/WEB-INF/pages/common/taglib.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <%@ include file="/WEB-INF/pages/common/css.jsp" %>
    <title>Withdraw Funds</title>
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
                        <a href="<c:url value='/depositfund.do' />" class="list-group-item">Deposit Funds</a>
                        <a href="<c:url value='/withdrawfund.do' />" class="list-group-item active">Withdraw Funds</a>
                    </div>
                </div>

                <div class="col-md-9 page-content">

                    <div class="clearfix">
                        <div class="row">
                            <div class="col-xs-12 col-md-12"><h1 style="margin-top:0px">Withdraw Funds</h1>
                                <hr/>
                                <div class="panel panel-default">
                                    <div class="panel-heading">
                                        <h3 class="panel-title">Withdraw to Paypal Account</h3>
                                    </div>
                                    <div class="panel-body">
                                        <p class="help-block" style="text-align:center">
                                            You need a paypal account to withdraw fund from your account.
                                            For more information, visit
                                            <a target="_blank" href="https://www.paypal.com/">Paypal</a>.
                                        </p>

                                        <form class="form form-horizontal" id="withdrawFundForm">
                                            <fieldset>
                                                <div class="form-group">
                                                    <label class="col-sm-3 control-label">Available Balance</label>

                                                    <div class="col-sm-6 form-control-static" id="available_balance">

                                                    </div>
                                                </div>
                                                <div class="form-group">
                                                    <label class="col-sm-3 control-label">Paypal Address</label>

                                                    <div class="col-sm-6">
                                                        <input type="text" class="form-control" name="paypalAddress"
                                                               placeholder="Paypal Address">
                                                    </div>
                                                </div>
                                                <div class="form-group">
                                                    <label class="col-sm-3 control-label">Amount</label>

                                                    <div class="col-sm-6">
                                                        <input type="text" class="form-control" name="amount"
                                                               placeholder="Amount">
                                                    </div>
                                                </div>
                                                <div class="form-group">
                                                    <div class="col-sm-offset-3 col-sm-9">
                                                        <button type="submit" class="btn btn-default" role="button">
                                                            Withdrow Funds
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
</div>

<%@ include file="/WEB-INF/pages/common/footer.jsp" %>
<%@ include file="/WEB-INF/pages/common/js.jsp" %>
<script type="text/javascript">
    var balance = parseFloat("${User_Balance}");
    $("#available_balance").html("$" + balance.toFixed(2));
    $(function () {
        $("#withdrawFundForm").validate({
            rules: {
                amount: {required: true, number: true},
                paypalAddress: {required: true}
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
            url: "<c:url value='/withdrawfunds.do'/>",
            data: $("#withdrawFundForm").serialize()
        }).done(function (msg) {
            var json = jQuery.parseJSON(msg);
            if (json.result == 1) {
                showSuccess("We got your withdraw fund request. We will notify you once the payment processed.");
            } else {
                showError("You do not have enough balance to withdraw this amount.");
            }
            $("#withdrawFundForm").trigger("reset");
        });
    }

    $.validator.addMethod('lessThan', function (value, el, param) {
        var i = parseFloat(amount);
        var j = parseFloat(balance);
        return (i < j) ? true : false;
    });
</script>
</body>
</html>
