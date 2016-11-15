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
        <title>CrossFit Guild Blog</title>
    </head>

    <body>
        <%@include file="fragment/nav.jsp"%>

        <div class="container">

            <div class="row row-offcanvas row-offcanvas-right">

                <div class="col-xs-12 col-sm-9">
                    <p class="pull-right visible-xs">
                        <button type="button" class="btn btn-primary btn-xs" data-toggle="offcanvas">Toggle nav</button>
                    </p>
                    <div class="row">
                        <h1>Post Title</h1>
                        <p>Post details</p>
                    </div>
                    <div class="row">
                        <p>Nunc malesuada mauris erat, non interdum ex suscipit a. Nulla interdum laoreet elit. Integer auctor vehicula elit, id volutpat lectus ullamcorper sed. Vivamus vitae aliquet sem. Pellentesque vitae mauris libero. Pellentesque lacus dolor, posuere vel ex vitae, tincidunt facilisis urna. Mauris nec feugiat mauris. Mauris eleifend lorem sem, nec sagittis tortor sollicitudin ut. Suspendisse vestibulum diam sit amet dui mollis tincidunt. Proin nec purus lorem. Sed sit amet lectus ut sapien sollicitudin porttitor. In tincidunt libero mauris, non venenatis elit aliquet vel. Sed euismod sapien id sem pellentesque, at eleifend massa varius.</p>
                        <p>In sed neque consequat, maximus nulla vitae, iaculis justo. Quisque euismod enim vel feugiat pretium. Integer metus magna, finibus ac suscipit a, fermentum lobortis nunc. Ut at metus vitae elit egestas ultricies non in elit. Fusce tempus sollicitudin suscipit. Integer blandit consequat mollis. Nam maximus justo at tellus aliquet interdum. Nullam tempor ac sapien sit amet sagittis. Suspendisse magna purus, congue sed nibh non, feugiat tempus diam. Aliquam erat volutpat. Nam viverra commodo iaculis.</p>
                    </div><!--/row-->
                </div><!--/.col-xs-12.col-sm-9-->

                <div class="col-xs-6 col-sm-3 sidebar-offcanvas" id="sidebar">
                    <%@include file="fragment/categories.jsp"%>
                </div><!--/.sidebar-offcanvas-->
            </div><!--/row-->

            <hr>

        </div>
        
        <script src="${pageContext.request.contextPath}/js/jquery-2.2.4.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/offcanvas.js"></script>
    </body>
</html>