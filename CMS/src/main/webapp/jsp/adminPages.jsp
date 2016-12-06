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
        <title>Admin - All Pages</title>
    </head>

    <body>

        <%@include file="fragment/topbar.jsp" %>

        <div class="container-fluid">
            <div class="row">
                <%@include file="fragment/sidebar.jsp" %>
                
                <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">

                    <c:if test="${successMessage == 'true'}"><div class="alert alert-success alert-dismissable" role="alert">Success! The page has been updated!<button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button></div></c:if>  
                    <c:if test="${addMessage == 'true'}"><div class="alert alert-success alert-dismissable" role="alert">Success! The page has been added!<button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button></div></c:if> 

                <h2 class="sub-header">Pages</h2>
                
                <sec:authorize access="hasAnyRole('admin')">
                    <p class="text-center"><b><a id="edit-nav-title" onclick="editNavTitle();" class="pointer">** Edit Nav Title / Order **</a></b></p>
                </sec:authorize>
                
                <div class="table table-responsive">
                    <table class = "pages-table table table-striped">
                        <thead>
                            <tr>
                                <th width="7%">ID</th>
                                <th width="24%">Title</th>
                                <th width="26%">Nav Title</th>
                                <th width="15%">Order</th>
                                <th width="28%">Author</th></tr>
                        </thead>  
                        <tbody id ="page-list"></tbody>
                    </table>
                </div>
            </div>
        </div>

        <%@include file="fragment/commonScripts.jsp"%> 
        <script src="${pageContext.request.contextPath}/js/adminPages.js"></script>
    </body>
</html>