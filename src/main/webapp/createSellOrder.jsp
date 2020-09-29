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

    <title>Create Sell Order</title>

    <script>

        function validate() {
            var result = true;
            var description = document.getElementById("description");

            if (description.value === "") {
                result = false;
            }

            if (!result) {
                if (description.value === "") {
                    alert("Please fill this field: Description");
                }

            }
            return result;
        }

        $(document).ready(function () {
            var url = new URL(window.location);
            var id = url.searchParams.get("userId");
            $("#userId").attr("value", id);
        })
    </script>
</head>
<body>
<div class="container pt-3">
    <div class="row">
        <div class="card" style="width: 100%">
            <div class="card-header">
                Create new sell order
            </div>
            <div class="card-body">
                <form action="<%=request.getContextPath()%>/post.do" method="post"
                      class="was-validate">
                    <div class="row">
                        <input type="hidden" class="form-control" name="userId" id="userId">
                        <div class="col-sm-3">
                            <input type="text" class="form-control" placeholder="Brand"
                                   name="brand" id="brand" required>
                        </div>
                        <div class="col-sm-3">
                            <input type="text" class="form-control" placeholder="Model"
                                   name="model" id="model" required>
                        </div>
                        <div class="col-sm-4">
                            <select class="form-control" name="body" id="body" required>
                                <option value="" disabled selected hidden>Body</option>
                                <option>Sedan</option>
                                <option>Hatchback</option>
                                <option>Cabriolet</option>
                                <option>Crossover</option>
                                <option>Coupe</option>
                                <option>Station wagon</option>
                                <option>Minivan</option>
                                <option>Pickup</option>
                                <option>Limousine</option>
                                <option>Pickup</option>
                                <option>Van</option>
                            </select>
                        </div>
                        <div class="col-sm-2">
                            <input type="text" class="form-control"
                                   placeholder="Year"
                                   name="condition" id="condition" required>
                        </div>
                    </div>
                    <br>
                    <div class="row">
                        <div class="col-sm-1">
                            <select class="form-control" name="engineVolume" id="engineVolume" required>
                                <option value="" disabled selected hidden>Volume l</option>
                                <option>0.2</option>
                                <option>0.4</option>
                                <option>0.6</option>
                                <option>0.8</option>
                                <option>1.0</option>
                                <option>1.2</option>
                                <option>1.3</option>
                                <option>1.4</option>
                                <option>1.6</option>
                                <option>1.8</option>
                                <option>2.0</option>
                                <option>2.2</option>
                                <option>2.4</option>
                                <option>2.6</option>
                                <option>2.8</option>
                                <option>3.0</option>
                                <option>3.5</option>
                                <option>4.0</option>
                                <option>4.5</option>
                                <option>5.0</option>
                                <option>5.5</option>
                                <option>6.0</option>
                                <option>7.0</option>
                                <option>8.0</option>
                                <option>9.0</option>
                                <option>10.0</option>
                            </select>
                        </div>
                        <div class="col-sm-3">
                            <select class="form-control" name="engine" id="engine" required>
                                <option value="" disabled selected hidden>Engine</option>
                                <option>Gasoline</option>
                                <option>Diesel</option>
                                <option>Hybrid</option>
                                <option>Electric</option>
                                <option>Turbo</option>
                                <option>Atmospheric</option>
                            </select>
                        </div>
                        <div class="col-sm-3">
                            <select class="form-control" name="drive" id="drive" required>
                                <option value="" disabled selected hidden>Drive</option>
                                <option>FWD</option>
                                <option>RWD</option>
                                <option>4WD</option>
                                <option>AWD</option>
                            </select>
                        </div>
                        <div class="col-sm-3">
                            <select class="form-control"  name="transmission" id="transmission" required>
                                <option value="" disabled selected hidden>Transmission</option>
                                <option>Manual</option>
                                <option>Automatic</option>
                            </select>
                        </div>
                        <div class="col-sm-2">
                            <input type="text" class="form-control" placeholder="Mileage"
                                   name="mileage" id="mileage" required>
                        </div>
                    </div>
                    <br>
                    <div class="row">
                        <div class="col-sm">
                            <input type="text" class="form-control" placeholder="Price"
                                   name="price" id="price" required>
                        </div>
                    </div>
                    <br>
                    <button type="submit" class="btn btn-success"
                            onclick="return validate();">Post sell order
                    </button>
                </form>
            </div>
        </div>
    </div>
</div>
</body>
</html>
