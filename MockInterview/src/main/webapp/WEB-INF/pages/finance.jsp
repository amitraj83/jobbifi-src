<%@ include file="/WEB-INF/pages/common/taglib.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <%@ include file="/WEB-INF/pages/common/css.jsp" %>
    <title>Finance</title>
</head>
<body>
<%@ include file="/WEB-INF/pages/common/header.jsp" %>


<div id="wrapper">
    <div id="page-content">

        <div class="container">
            <div class="row">

                <div class="col-md-3">
                    <div class="list-group list-default">
                        <a href="<c:url value='/finance.do'/>" class="list-group-item active">Transactions History</a>
                        <a href="<c:url value='/depositfund.do'/>" class="list-group-item">Deposit Funds</a>
                        <a href="<c:url value='/withdrawfund.do'/>" class="list-group-item">Withdraw Funds</a>
                    </div>
                </div>
                <div class="col-md-9">
                    <div class="row">
                        <div class="col-md-12" style="margin-top:0px">
                            <h1>Transaction History</h1>
                            <hr style="margin-bottom:44px;"/>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-4">
                            <div class="input-group">
                                <span class="input-group-addon">From</span>
                                <input type="text" class="form-control" data-date-format="dd-mm-yyyy"
                                       data-provide="datepicker" id="fromdatepicker">
                            </div>
                        </div>
                        <div class="col-md-4">
                            <div class="input-group">
                                <span class="input-group-addon">To</span>
                                <input type="text" class="form-control" data-date-format="dd-mm-yyyy"
                                       data-provide="datepicker" id="todatepicker">
                            </div>
                        </div>
                        <div class="col-md-3">
                            <button id="showTransHistoryButton" type="button" class="btn btn-default">Show</button>
                        </div>
                        <div class="col-md-1">

                                <span data-toggle="tooltip" data-placement="bottom" title="Export as Excel">
                                    <i class="fa fa-file-excel-o"></i>
                                </span> 
                                <span data-toggle="tooltip" data-placement="bottom" title="Export as PDF">
                                    <i class="fa fa-file-pdf-o"></i>
                                </span>

                        </div>
                    </div>

                    <br/>

                    <div class="row">
                        <div class="col-md-12">
                            <table class="table table-striped">
                                <thead>
                                <tr>
                                    <th width="20%">Date</th>
                                    <th width="15%">From</th>
                                    <th width="15%">To</th>
                                    <th width="15%">Amount</th>
                                    <th width="15%">Fees</th>
                                    <th width="15%">Balance</th>
                                </tr>
                                </thead>
                                <tbody id="finance_transactionbody">
                                <tr>
                                    <td colspan="6">Loading....</td>
                                </tr>
                                </tbody>
                            </table>
                            <ul class="pagination pull-right" id="transaction_pagination">
                            </ul>
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

    var showTransHistory = function (pagenum) {
        var from;
        var to;

        if (pagenum == null || pagenum == undefined) {

            var now = new Date();
            var lastWeek = new Date(now.getTime() - (7 * 24 * 60 * 60 * 1000));

            $('#todatepicker').datepicker("update", new Date());
            $('#fromdatepicker').datepicker("update", lastWeek);

            from = $('#fromdatepicker').datepicker("getDate");
            to = $('#todatepicker').datepicker("getDate");
            pagenum = 1;

        } else {
            from = $('#fromdatepicker').datepicker("getDate");
            to = $('#todatepicker').datepicker("getDate");
        }

        var param = "fromDate=" + from.getTime() + "&toDate=" + to.getTime() + "&pagenum=" + pagenum;
        $.ajax({
            type: "POST",
            url: "<c:url value='transhistory.do'/>",
            data: param
        }).done(function (res) {
            var transData = jQuery.parseJSON(res);
            var allRows = jQuery.parseJSON(transData.list);

            $("#transaction_pagination").html("");
            if (Number(transData.numberofrecords) > 10) {
                var pagination = "";
                var totalpages = Number(transData.numberofrecords) / 10;
                for (var i = 0; i < totalpages; i++) {
                    if (pagenum == null || pagenum == undefined && i == 0) {
                        pagination = pagination + '<li class="active"><a class="transpage" href="javascript:void(0)">' + (i + 1) + '</a></li>';
                    } else if (pagenum == i + 1) {
                        pagination = pagination + '<li class="active"><a class="transpage" href="javascript:void(0)">' + (i + 1) + '</a></li>';
                    } else {
                        pagination = pagination + '<li><a class="transpage" href="javascript:void(0)">' + (i + 1) + '</a></li>';
                    }
                }
                $("#transaction_pagination").html(pagination);
            }

            var rows = "";
            var size = "1";

            $("#finance_transactionbody").html("");
            if (allRows.length > 0) {
                for (var i = 0; i < allRows.length; i++) {
                    var star = allRows[i].owner == "ESCROW" ? "*" : "";
                    var row = '<tr>		' +
                            '<td>' + (new Date(Number(allRows[i].time)).format("d mmm, yyyy ") ) + '</td>' +
                            '<td>' + allRows[i].owner + '</td>' +
                            '<td>' + allRows[i].otherParty + '</td>' +
                            '<td>$' + allRows[i].gross + '' + star + '</td>' +
                            '<td>$' + allRows[i].fee + '</td>' +
                            '<td>$' + (allRows[i].balance).toFixed(2) + '</td>' +
                            '</tr>';
                    $("#finance_transactionbody").append(row);
                }
            } else {
                var noresult = "<tr><td colspan='6'>No records found.</td></tr>";
                $("#finance_transactionbody").html(noresult);
            }
        });
    }

    $(document).ready(function () {
        if (getURLParameter("ps") == 1) {
            showSuccess("Amount successfully deposited into your account.");
        }
        showTransHistory();
        $('.datepicker').datepicker({format: 'dd-mm-yyyy'});

        /* pagination */
        $(document).on("click", ".transpage", function (event) {
            event.preventDefault();
            showTransHistory($(this).text());
        });

        $(document).on('click', '#showTransHistoryButton', function () {
            showTransHistory(1);
        });
    });
</script>
</body>
</html>
