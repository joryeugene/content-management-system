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
        <!--TinyMCE-->
        <script src='https://cdn.tinymce.com/4/tinymce.min.js'></script>
        <title>Admin Dashboard - Add Page</title>
    </head>
    <body>
        <%@include file="fragment/topbar.jsp" %>
        <div class="container-fluid">
            <div class="row">
                <%@include file="fragment/sidebar.jsp" %>

                <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
                    <c:if test="${successMessage == 'true'}">
                        <div class="alert alert-success alert-dismissable" role="alert">Success! You edited the page!
                            <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                                <span aria-hidden="true">&times;</span></button>
                        </div>
                    </c:if>
                    <h1 class="page-header">Add Page </h1>
                    <a href="javascript:history.back()"><- Back</a>

                    <div class="row page-edit">
                        <div class="col-xs-12 col-sm-8">

                            <h3>Page Title </h3>
                            <sf:form modelAttribute="page" action="${pageContext.request.contextPath}/admin/page/add" method="POST">
                                <sf:input type="text"  id="edit-email" path="email" />
                                <div class="form-group">
                                    <sf:input id="edit-title" path="title" class="form-control" type="text"></sf:input>
                                    </div>
                                    <div class="form-group">
                                    <sf:textarea id="edit-content" class="form-control" path="content"></sf:textarea>
                                    </div>
                                    <button type="submit" id="publish-page" class="btn btn-primary">Publish</button>
                            </sf:form>
                        </div>

                    </div> 

                </div>
            </div>
        </div>
    </div>
</div>
</div>

<script src="${pageContext.request.contextPath}/js/jquery-2.2.4.min.js"></script>
<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>

<!--Initialize TinyMCE-->
<script>
    tinymce.init({
        selector: "#edit-content"
    });

    function loadUsername() {
        var currentUser = '${username}';
        $("#edit-email").val(currentUser);
    }
    loadUsername();
</script>
</body>
</html>