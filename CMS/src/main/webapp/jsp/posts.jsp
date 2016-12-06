<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
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

                <div class="col-xs-12 col-sm-9">
                    <p class="pull-right visible-xs">
                        <button type="button" class="btn btn-primary btn-xs" data-toggle="offcanvas">Toggle nav</button>
                    </p>

                    <div class="main-margin">

                        <h2 class="text-center">${title}</h2>

                        <c:set var="totalCount" scope="session" value="${fn:length(posts)}"/>
                        <c:set var="perPage" scope="session"  value="${10}"/>
                        <c:set var="pageStart" value="${param.start}"/>
                        <c:if test="${empty pageStart or pageStart < 0}">
                            <c:set var="pageStart" value="0"/>
                        </c:if>
                        <c:if test="${totalCount < pageStart}">
                            <c:set var="pageStart" value="${pageStart - perPage}"/>
                        </c:if>

                        <c:forEach var="post" items="${posts}" begin="${pageStart}" end="${pageStart + perPage - 1}">
                            <div class="row">
                                <h4><a href="${pageContext.request.contextPath}/post/${post.id}">${post.title}</a></h4>
                                <p>${post.startDate.month} ${post.startDate.dayOfMonth}, ${post.startDate.year} | ${post.author.displayName} | <a href="${pageContext.request.contextPath}/category/${post.category.id}">${post.category.name}</a></p>
                            </div>
                        </c:forEach>

                        <c:if test="${totalCount > 10}">
                            <p class="text-center">
                            <a href="?start=${pageStart - perPage}"><<</a>${pageStart + 1} - ${pageStart + perPage} 
                            <a href="?start=${pageStart + perPage}">>></a>
                        </p>
                        </c:if>
                        
                    </div>
                </div><!--/.col-xs-12.col-sm-9-->

                <div class="col-xs-6 col-sm-3 sidebar-offcanvas" id="sidebar">
                    <%@include file="fragment/categories.jsp"%>
                </div><!--/.sidebar-offcanvas-->
            </div><!--/row-->

            <hr>

        </div>

        <%@include file="fragment/commonScripts.jsp"%>
        <script src="${pageContext.request.contextPath}/js/offcanvas.js"></script>
    </body>
</html>