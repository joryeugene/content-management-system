<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
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

                <%@include file="fragment/dashboard.jsp" %>

                <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">

                    <h2 class="sub-header">Recent Pages</h2>
                    <div class="table table-responsive">
                        <table class = "pages-table table table-striped">
                            <thead>
                                <tr>
                                    <th>Page ID</th>
                                    <th>Title</th>
                                    <th>Author</th>
                                    <!--<th>Approve</th>-->
                                </tr>
                            </thead>  
                            <tbody id ="page-list"></tbody>
                        </table>
                    </div>

                    <h2 class="sub-header">Recent Posts</h2>
                    <div class="table table-responsive">
                        <table class = "posts-table table table-striped">
                            <thead>
                                <tr>
                                    <th>Post ID</th><th>Title</th><th>Author</th><th>Category</th><th>StartDate</th><th>EndDate</th><th>Approve</th>
                                </tr>
                            </thead>  
                            <tbody id ="post-list">

                            </tbody>
                        </table>
                    </div>
                </div>
            </div>

            <script src="${pageContext.request.contextPath}/js/jquery-2.2.4.min.js"></script>
            <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
            <script src="${pageContext.request.contextPath}/js/loadPages.js"></script>
    </body>
</html>