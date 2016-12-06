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
        <title>Admin - Settings</title>
    </head>

    <body>

        <%@include file="fragment/topbar.jsp" %>

        <div class="container-fluid">
            <div class="row">
                <!--sidebar here-->
                <%@include file="fragment/sidebar.jsp" %>

                <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">

                    <h2>Settings</h2>


                    <div id="settings" class="row badges">
                        <div class="col-xs-12 col-sm-12 col-md-4">
                            <h3 class="panel-title">Nav Background Color</h3>
                            <div class="panel-body">
                                <input id="nav-color" class="form-control" type="color" onchange="set()">
                            </div>
                            <h3 class="panel-title">Nav Text Color</h3>
                            <div class="panel-body">
                                <input id="text-color" class="form-control" type="color" onchange="set()">
                            </div>
                            <div class="panel-body">
                                <input class="btn btn-primary" type="submit" value="Reset to Default" onclick="setDefault()">
                            </div>
                        </div>
                        <div class="col-xs-12 col-sm-12 col-md-4">
                            <h3 class="panel-title">Blog Title</h3>
                            <div class="panel-body">
                                <input id="blog-title" class="form-control" type="text" onchange="set()">
                            </div>

                            <h3 class="panel-title">Subtitle</h3>
                            <div class="panel-body">
                                <input id="blog-subtitle" class="form-control" type="text" onchange="set()">
                            </div>
                            <h3 class="panel-title">Jumbotron Image</h3>
                            <div class="panel-body">
                                <input id="blog-image" class="form-control" type="text" onchange="set()"> <!--<input class="btn btn-default" type="submit" onclick="set()">-->
                            </div>
                        </div>
                        <div class="col-xs-12 col-sm-12 col-md-4">
                            <h3 class="panel-title">Preview</h3>

                            <div class="panel-body">
                                <div class="jumbotron" id="header-img" style="position: relative; background-size: cover; background-repeat: no-repeat;">
                                    <div>
                                        <h1 style="visibility: hidden;">Header</h1>
                                        <p id="config-subtitle" style="position: absolute; bottom: 0; left: 20px; color: white; text-shadow: 2px 2px #000;"></p>
                                    </div>
                                </div>
                            </div>
                        </div>

                    </div>
                </div>

            </div>

            <%@include file="fragment/commonScripts.jsp"%>
            <script src="${pageContext.request.contextPath}/js/settings.js"></script>
    </body>
</html>