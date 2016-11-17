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
                <div class="col-sm-3 col-md-2 sidebar">

                    <ul class="nav nav-sidebar">
                        <li role="presentation" class=""dropdown"><a class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopur="true" href="">Posts</a>
                            <ul class="dropdown-menu">
                                <li><a href="">All Posts</a></li>
                                <li><a href="">Add Post</a></li>
                        </li>
                    </ul>

                    <li role="presentation" class=""dropdown"><a class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopur="true" href="">Pages</a>
                        <ul class="dropdown-menu">
                            <li><a href="">All Pages</a></li>
                            <li><a href="">Add Page</a></li>
                    </li>
                    </ul>
                    <li role="presentation" class=""dropdown"><a class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopur="true" href="">Users</a>
                        <ul class="dropdown-menu">
                            <li><a href="">All Users</a></li>
                            <li><a href="">Add User</a></li>
                    </li>
                    </ul>
                    </ul>

                </div>
                <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
                    <h1 class="page-header">Dashboard</h1>

                    <div class="row badges">
                        <div class="col-xs-6 col-sm-3 badges panel">
                            <span class="glyphicon glyphicon-file"></span> Posts Waiting for Approval  <span class="badge">16</span>
                        </div>
                        <div class="col-xs-6 col-sm-3 badges panel">
                            <button class="btn btn-default"><span class="glyphicon glyphicon-th"></span> Posts Title Views </button> <span class="badge">700</span>
                        </div>
                        <div class="col-xs-6 col-sm-3 badges panel">
                            <button class="btn btn-default"><span class="glyphicon glyphicon-th"></span> Posts Title Views </button> <span class="badge">208</span>
                        </div>
                        <div class="col-xs-6 col-sm-3 badges panel">
                            <button class="btn btn-default"><span class="glyphicon glyphicon-th"></span> Posts Title Views </button> <span class="badge">15</span>
                        </div>
                    </div>

                    <h2 class="sub-header">All Pages</h2>
                    <div class="table-responsive">
                        <table class="table table-striped">
                            <thead>
                                <tr>
                                    <th>Page ID</th>
                                    <th>Title</th>
                                    <th>Category</th>
                                    <th>Start Date</th>
                                    <th>End Date</th>
                                    <th>Approve</th>
                                </tr>
                            </thead>
                            <tbody>
                                <tr>
                                    <td><a href="#">1,001</a></td>
                                    <td>Lorem</td>
                                    <td>ipsum</td>
                                    <td>dolor</td>
                                    <td>sit</td>
                                    <td><button class="btn btn-primary">Approve</button> </td>

                                </tr>
                                <tr>
                                    <td><a href="#">1,001</a></td>
                                    <td>Lorem</td>
                                    <td>ipsum</td>
                                    <td>dolor</td>
                                    <td>sit</td>
                                    <td><button class="btn btn-primary">Approve</button> </td>

                                </tr>
                                <tr>
                                    <td><a href="#">1,001</a></td>
                                    <td>Lorem</td>
                                    <td>ipsum</td>
                                    <td>dolor</td>
                                    <td>sit</td>
                                    <td><button class="btn btn-primary">Approve</button> </td>

                                </tr>
                                <tr>
                                    <td><a href="#">1,001</a></td>
                                    <td>Lorem</td>
                                    <td>ipsum</td>
                                    <td>dolor</td>
                                    <td>sit</td>
                                    <td><button class="btn btn-primary">Approve</button> </td>

                                </tr>
                                <tr>
                                    <td><a href="#">1,001</a></td>
                                    <td>Lorem</td>
                                    <td>ipsum</td>
                                    <td>dolor</td>
                                    <td>sit</td>
                                    <td><button class="btn btn-primary">Approve</button> </td>

                                </tr>
                                <tr>
                                    <td><a href="#">1,001</a></td>
                                    <td>Lorem</td>
                                    <td>ipsum</td>
                                    <td>dolor</td>
                                    <td>sit</td>
                                    <td><button class="btn btn-primary">Approve</button> </td>

                                </tr>
                                <tr>
                                    <td><a href="#">1,001</a></td>
                                    <td>Lorem</td>
                                    <td>ipsum</td>
                                    <td>dolor</td>
                                    <td>sit</td>
                                    <td><button class="btn btn-primary">Approve</button> </td>

                                </tr>
                                <tr>
                                    <td><a href="#">1,001</a></td>
                                    <td>Lorem</td>
                                    <td>ipsum</td>
                                    <td>dolor</td>
                                    <td>sit</td>
                                    <td><button class="btn btn-primary">Approve</button> </td>

                                </tr>
                                <tr>
                                    <td><a href="#">1,001</a></td>
                                    <td>Lorem</td>
                                    <td>ipsum</td>
                                    <td>dolor</td>
                                    <td>sit</td>
                                    <td><button class="btn btn-primary">Approve</button> </td>

                                </tr>
                                <tr>
                                    <td><a href="#">1,001</a></td>
                                    <td>Lorem</td>
                                    <td>ipsum</td>
                                    <td>dolor</td>
                                    <td>sit</td>
                                    <td><button class="btn btn-primary">Approve</button> </td>
                                </tr>

                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>

        <script src="${pageContext.request.contextPath}/js/jquery-2.2.4.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    </body>
</html>