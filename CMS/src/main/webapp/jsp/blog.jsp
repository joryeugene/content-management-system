<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <%@include file="fragment/head.jsp"%>
        <link href="${pageContext.request.contextPath}/css/offcanvas.css" rel="stylesheet">
        <title id="main-blog-title"></title>
    </head>

    <body>
        <%@include file="fragment/nav.jsp"%>

        <div class="container">

            <div class="row row-offcanvas row-offcanvas-right">

                <div class="col-xs-12 col-sm-9">
                    
                    <div class="jumbotron" id="header-img" style="position: relative; background-size: cover; background-repeat: no-repeat;">
                        <div>
                            <h1 style="visibility: hidden;">Header</h1>
                            <p id="config-subtitle" style="position: absolute; bottom: 0; left: 20px; color: white; text-shadow: 2px 2px #000;"></p>
                        </div>
                    </div>
                    <p class="text-right visible-xs" style="margin: 0; padding:0;">
                        <button type="button" class="btn btn-primary btn-xs" data-toggle="offcanvas">Toggle nav</button>
                    </p>
                    <div class="row" id="main-page-posts">

                    </div><!--/row-->
                </div><!--/.col-xs-12.col-sm-9-->

                <div class="col-xs-6 col-sm-3 sidebar-offcanvas" id="sidebar">
                    <%@include file="fragment/categories.jsp"%>
                </div><!--/.sidebar-offcanvas-->
            </div><!--/row-->

            <hr>

        </div>
                
        <%@include file="fragment/commonScripts.jsp"%>
        <script src="${pageContext.request.contextPath}/js/offcanvas.js"></script>
        <script src="${pageContext.request.contextPath}/js/blogMainPage.js"></script>
    </body>
</html>