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
                            <h3 class="panel-title">Colors</h3>
                            <div class="panel-body">
                                <p>
                                    <input id="nav-color" type="color" onchange="set()"><b> Nav Background</b>
                                </p>
                                <p>
                                    <input id="text-color" type="color" onchange="set()"><b> Nav Text Color</b>
                                </p>
                            </div>
                        </div>
                        <div class="col-xs-12 col-sm-12 col-md-4">
                            <h3 class="panel-title">Blog Title</h3>
                            <div class="panel-body">
                                <input id="blog-title" type="text" onchange="set()">
                            </div>
                        </div>
                        <div class="col-xs-12 col-sm-12 col-md-4">
                            <h3 class="panel-title">Jumbotron Image</h3>
                            <div class="panel-body">
                                <input id="blog-image" type="text"> <input class="btn btn-default" type="submit" onclick="set()">
                            </div>
                        </div>
                        
                    </div>
                    
                    <input class="btn btn-default" type="submit" value="Return to Default" onclick="setDefault()">


                </div>

            </div>

            <%@include file="fragment/commonScripts.jsp"%>
            <script src="${pageContext.request.contextPath}/js/settings.js"></script>
    </body>
</html>