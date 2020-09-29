<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!doctype html>
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
          integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
            integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"
            integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>

    <title>AutoPark</title>

    <script>

        function displayAllSellOrders() {
            $("#allSellOrders tbody tr").empty();
            $.getJSON('<%=request.getContextPath()%>/getAllOrders.do', function (data) {
                for (var i = 0; i < data.length; i++) {
                    $('#allSellOrders > tbody:last-child').append('<tr><td style="display: none">' + 'Id' +'</td><td><img src="" class="rounded" alt=""></td><td></td><td><div class="row"><div class="col-sm-5">' + data[i].brand + " " + data[i].model + '</div><div class="col-sm-3">' + data[i].price + '</div><div class="col-sm-2">' + data[i].condition + '</div><div class="col-sm-2">' + data[i].mileage + '</div></div><div class="row"><div class="col-sm-1">' + data[i].engineVolume + '</div><div class="col-sm-2">' + data[i].engine + '</div><div class="col-sm-1">' + data[i].drive + '</div><div class="col-sm-8"></div></div><div class="row"><div class="col-sm-2">' + data[i].transmission + '</div><div class="col-sm-10"></div></div><div class="row"><div class="col-sm-2">' + data[i].body + '</div><div class="col-sm-10"></div></div></td></tr>');
                }
            });
        }

        $(document).ready(function () {
            displayAllSellOrders();
        });

        $(document).ready(function () {
            $("#allSellOrders").on("click", ".btn-danger", function () {
                var $row = $(this).closest("tr");
                var $id = $row.find(".id").text();
                $.post("<%=request.getContextPath()%>/delete.do",
                    "id=" + $id,
                    function () {
                   displayAllSellOrders();
                });
            });
        });

        $(document).ready(function () {
            $("#allSellOrders").on("click", ".btn-success", function () {
                var $row = $(this).closest("tr");
                var $id = $row.find(".id").text();
                $.post("<%=request.getContextPath()%>/change.do",
                    "id=" + $id,
                    function () {
                        displayAllSellOrders();
                    });
            });
        });

        $(document).ready(function () {
            $("#allSellOrders").on("click", ".btn-primary", function () {
                var $row = $(this).closest("tr");
                var $id = $row.find(".id").text();
                window.location.replace("<%=request.getContextPath()%>/update.jsp?id=" +
                    $id);
            });
        });

        $(document).ready(function () {
            $("#toggleList").click(function () {
                $('#allSellOrders tbody > tr .status').filter(function () {
                    return $(this).text() === "true";
                }).each(function () {
                    $(this).closest("tr").toggle();
                });
            });
        });

    </script>
</head>
<body>
<div class="container text-right">
        <a class="nav-link" href="<%=request.getContextPath()%>/login.do" id="login"> <c:out
                value="${user.name}"/> | Sign in</a>
</div>
<div class="container">
    <a href="<%=request.getContextPath()%>/create.do?userId=${userId}"
       class="btn btn-success btn-lg">Add
        sell
        order</a>
</div>
<div class="container pt-5">
    <div>
        <button  type="button" class="btn btn-primary btn-sm" id="toggleList">Show all | hide
            soled
        </button>
    </div>
</div>

<div class="container">
    <table class="table" id="allSellOrders" style="table-layout: fixed">
        <thead class="thead-light">
        <tr>
            <th style="display: none" >Id</th>
            <th style="width: 25%">Photo</th>
            <th style="width: 3%"></th>
            <th style="width: 72%">Description</th>
        </tr>
        </thead>
        <tbody>

        </tbody>
    </table>
</div>
</body>
</html>