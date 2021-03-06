<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
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

        <title>Error</title>
    </head>
    <body>
        <%@include file="fragment/topbar.jsp" %>
        <div class="container-fluid">
            <div class="row">
                
                <%@include file="fragment/sidebar.jsp" %>
               <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
                    <h1>Uh Oh, Something went wrong...</h1>
                    
                    <h2>${errorMessage}</h2>
                     <a href="${pageContext.request.contextPath}/blog">Head back to the main page</a>
                </div> 
                
                   
                
            </div>
            <script src="${pageContext.request.contextPath}/js/jquery-2.2.4.min.js"></script>
            <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    </body>
</html>