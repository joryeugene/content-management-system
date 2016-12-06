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
        <link href="${pageContext.request.contextPath}/css/offcanvas.css" rel="stylesheet">
        <title id="main-blog-title"></title>
    </head>

    <body>
        <%@include file="fragment/nav.jsp"%>

        <div class="container">

            <div class="row row-offcanvas row-offcanvas-right">

                <div class="col-xs-12">
                    <p class="pull-right visible-xs">
                        <button type="button" class="btn btn-primary btn-xs" data-toggle="offcanvas">Toggle nav</button>
                    </p>
                    
                    <div class="main-margin">
                        <div class="row">
                            <h1>${page.title}</h1>
                            <p></p>
                        </div>
                        <div class="row">
                            ${page.content}
                        </div><!--/row-->
                    </div>
                </div><!--/.col-xs-12.col-sm-9-->
            </div><!--/row-->

            <hr>

        </div>

        <%@include file="fragment/commonScripts.jsp"%>
        <script src="${pageContext.request.contextPath}/js/offcanvas.js"></script>
    </body>
</html>