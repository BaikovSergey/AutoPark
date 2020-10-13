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
            $("#allSellOrders tbody").empty();
            $.getJSON('<%=request.getContextPath()%>/getAllOrders.do', function (data) {
                for (var i = 0; i < data.length; i++) {

                    var badge = getBadge(data[i].status);
                    var currentUserId = "${user.id}";
                    var sellOrderUserId = data[i].user.id.toString();
                    var carPhoto = data[i].carPhoto;
                    var btnChangeOrder = getBtnChangeOrder(currentUserId, sellOrderUserId)
                    var btnChangeStatus = getBtnStatus(currentUserId, sellOrderUserId);
                    var btnAddPhoto = getBtnAddPhoto(currentUserId, sellOrderUserId);

                    $('#allSellOrders > tbody:last-child').append('<tr>'
                        + '<td style="display: none" id="orderId">' + data[i].id +'</td>'
                        + '<td style="display: none" id="status">' + data[i].status +'</td>'
                        + '<td style="display: none" id="userId">' + data[i].user.id + '</td>'
                        + '<td><img src="<c:url value="/download.do?name="/>' + carPhoto
                        + ' "class="rounded" width="205px" height="154px"/></td>'
                        + '<td></td><td><div class="row">'
                        + '<div class="col-sm-5">' + data[i].brand + " " + data[i].model + '</div>'
                        + '<div class="col-sm-3">' + data[i].price + " ₽" + '</div>'
                        + '<div class="col-sm-2">' + data[i].condition + " year" + '</div>'
                        + '<div class="col-sm-2">' + data[i].mileage + " km" + '</div>'
                        + '</div><br><div class="row">'
                        + '<div class="col-sm-1">' + data[i].engineVolume + '</div>'
                        + '<div class="col-sm-2">' + data[i].engine + '</div>'
                        + '<div class="col-sm-1">' + data[i].drive + '</div>'
                        + '<div class="col-sm-8"></div></div>'
                        + '<div class="row">'
                        + '<div class="col-sm-2">' + data[i].transmission + '</div>'
                        + '<div class="col-sm-10"></div></div>'
                        + '<br><div class="row">'
                        + '<div class="col-sm-2">' + data[i].body + '</div>'
                        + '<div class="col-sm-3"></div>'
                        + '<div class="col-sm-5">' + btnAddPhoto + btnChangeOrder + btnChangeStatus + '</div>'
                        + '<div class="col-sm-2">'
                        + '<h4 id="tag">'+ badge +'</h4></div></div></td></tr>');
                }
            });
        }

        function getBadge(status) {
            var result = "";
            if (status === false) {
                result = "<span class=\"badge badge-success\">On sale</span>";
            } else {
                result = "<span class=\"badge badge-danger\">Soled</span>";
            }
            return result;
        }

        function getBtnStatus(currentUserId, sellOrderUserId) {
            var result = "";
            if (currentUserId === sellOrderUserId) {
                result = "<button type=\"button\" "
                    + "class=\"btn btn-outline-primary btn-sm\" "
                    + "id=\"btn-change\">Change status</button>";
            }
            return result;
        }

        function getBtnAddPhoto(currentUserId, sellOrderUserId) {
            var result = "";
            if (currentUserId === sellOrderUserId) {
                result = "<button type=\"button\" "
                    + "class=\"btn btn-outline-info btn-sm\" "
                    + "id=\"btn-photo\">Add photo</button>";
            }
            return result;
        }

        function getBtnChangeOrder(currentUserId, sellOrderUserId) {
            var result = "";
            if (currentUserId === sellOrderUserId) {
                result = "<button type=\"button\" "
                    + "class=\"btn btn-outline-dark btn-sm\" "
                    + "id=\"btn-update\">Update order</button>";
            }
            return result;
        }

        $(document).ready(function () {
            displayAllSellOrders();
        });

        $(document).ready(function () {
            $("#allSellOrders").on("click", "#btn-change", function () {
                var $row = $(this).closest("tr");
                var $id = $row.find("td:nth-child(1)").text();
                    $.post("<%=request.getContextPath()%>/change.do",
                        "orderId=" + $id,
                        function () {
                            displayAllSellOrders();
                        });
            });
        });

        $(document).ready(function () {
            $("#allSellOrders").on("click", "#btn-photo", function () {
                var $row = $(this).closest("tr");
                var $id = $row.find("td:nth-child(1)").text();
                window.location.replace("<%=request.getContextPath()%>/uploadPage.do?orderId=" + $id);
            });
        });

        $(document).ready(function () {
            $("#allSellOrders").on("click", "#btn-update", function () {
                var $row = $(this).closest("tr");
                var $id = $row.find("td:nth-child(1)").text();
                window.location.replace("<%=request.getContextPath()%>/updateSellOrder.jsp?orderId=" + $id);
            });
        });

        $(document).ready(function () {
            $(document).on("click", "#toggleList", function () {
                $('#allSellOrders tbody > tr > td:contains(true)').filter(function () {
                    return $(this).text() === "true";
                }).each(function () {
                    $(this).closest("tr").toggle();
                });
            });
        });

        function signInOut() {
            var text = "Sign in";
            var user = "${user.name}";
            if (user !== "") {
                text = "Sign out";
            }
            return text;
        }

        $(document).ready(function () {
            $("#login").text(signInOut());
        });

    </script>
</head>
<body>
<div class="container">
    <div class="row align-items-center">
        <div class="col"> </div>
        <div class="col-sm-auto"></div>
            <div>
                <c:out value="${user.name}"/>
            </div>
        <div class="col-sm-auto">
            <div>
                <a class="nav-link"
                   href="<%=request.getContextPath()%>/login.do" id="login"></a>
            </div>
        </div>
    </div>
</div>
<div class="container">
    <a href="<%=request.getContextPath()%>/create.do?userId=${userId}"
       class="btn btn-success btn-lg">Add sell order</a>
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
            <th class="orderId" style="display: none" id="orderId">Id</th>
            <th style="width: 20%">Photo</th>
            <th style="width: 1%"></th>
            <th style="width: 79%">Description</th>
        </tr>
        </thead>
        <tbody>

        </tbody>
    </table>
</div>
</body>
</html>