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
        <title>Edit Page </title>
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
                        <div class="col-xs-6 col-sm-4 badges panel">

                            <div class="panel-body">
                                <span class="glyphicon glyphicon-file"></span> Posts Needing Approval  <span class="badge">16</span>
                            </div>
                        </div>
                        <div class="col-xs-6 col-sm-4 badges panel">
                            <h3 class="panel-title">Recent Posts</h3>
                            <div class="panel-body">
                                <button class="btn btn-default btn-stacked"><span class="glyphicon glyphicon-th"></span> Working Out is Great  <span class="badge">700</span></button> 
                                <button class="btn btn-default btn-stacked"><span class="glyphicon glyphicon-th"></span> Nail Your Double-Unders  <span class="badge">208</span></button> 
                                <button class="btn btn-default btn-stacked"><span class="glyphicon glyphicon-th"></span> How are you preparing for the open?  <span class="badge">15</span></button> 
                            </div>
                        </div>
                        <div class="col-xs-6 col-sm-4 badges panel">
                            <h3 class="panel-title">Most Viewed Posts</h3>
                            <div class="panel-body">
                                <button class="btn btn-default btn-stacked"><span class="glyphicon glyphicon-th"></span> Working Out is Great  <span class="badge">700</span></button> 
                                <button class="btn btn-default btn-stacked"><span class="glyphicon glyphicon-th"></span> Nail Your Double-Unders  <span class="badge">208</span></button> 
                                <button class="btn btn-default btn-stacked"><span class="glyphicon glyphicon-th"></span> How are you preparing for the open?  <span class="badge">15</span></button> 
                            </div>

                        </div>

                    </div>
                    <script src="${pageContext.request.contextPath}/js/jquery-2.2.4.min.js"></script>
                    <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
                    </body>
                    </html>