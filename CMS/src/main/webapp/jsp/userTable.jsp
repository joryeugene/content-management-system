<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="shortcut icon" href="${pageContext.request.contextPath}/img/icon.png">
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/css/dashboard.css" rel="stylesheet">
        <title>Admin Dashboard</title>
    </head>

    <body>

        <%@include file="fragment/topbar.jsp" %>

        <div class="container-fluid">
            <div class="row">
                <!--sidebar here-->
                <%@include file="fragment/sidebar.jsp" %>

                <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">

                    <div class="col-sm-6">
                        <h2 class="sub-header">Users</h2>
                        <div class="table table-responsive">
                            <table class = "pages-table table table-striped">
                                <thead>
                                    <tr>
                                        <th>User ID</th><th>Email</th><th>Display Name</th><th>Authority</th></tr>
                                </thead>  
                                <tbody id ="user-list"></tbody>
                            </table>
                        </div>
                    </div>
                    <div class="col-sm-6">
                        <h2 class="sub-header" id="add-edit-title"></h2>
                        <form class="form-horizontal" role="form">
                            <div class="form-group">
                                <label for="user-email" class="col-sm-4 control-label">
                                    Email:
                                </label>
                                <div class="col-sm-8">
                                    <input type="email" class="form-control" id="user-email" placeholder="Email Address" />
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="user-display-name" class="col-sm-4 control-label">
                                    Display Name:
                                </label>
                                <div class="col-sm-8">
                                    <input type="text" class="form-control" id="user-display-name" placeholder="Display Name" />
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="user-authority" class="col-sm-4 control-label">
                                    Authority:
                                </label>
                                <div class="col-sm-8">
                                    <input type="text" class="form-control" id="user-authority" placeholder="Authority" />
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="user-avatar-url" class="col-sm-4 control-label">
                                    Avatar Url:
                                </label>
                                <div class="col-sm-8">
                                    <input type="text" class="form-control" id="user-avatar-url" placeholder="Avatar Url" />
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="user-password" class="col-sm-4 control-label">
                                    Password:
                                </label>
                                <div class="col-sm-8">
                                    <input type="password" class="form-control" id="user-password" placeholder="Password" />
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="col-sm-offset-4 col-sm-8">
                                    <button onclick="doAddUser();" id="user-btn" class="btn btn-primary">Add</button>
                                </div>
                            </div>
                        </form>
                        <div id="validationErrors" style="color: red"/>
                    </div>
                </div>
            </div>

            <script src="${pageContext.request.contextPath}/js/jquery-2.2.4.min.js"></script>
            <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
            <script src="${pageContext.request.contextPath}/js/user.js"></script>
    </body>
</html>