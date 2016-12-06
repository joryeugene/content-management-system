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
        <title>Admin Dashboard - Edit Page</title>
    </head>
    <body>
        <%@include file="fragment/topbar.jsp" %>
        <div class="container-fluid">
            <div class="row">
                <%@include file="fragment/sidebar.jsp" %>

                <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
                    <h1 class="page-header">Edit Page</h1>
                    <a href="javascript:history.back()"><- Back</a> | <a href="${pageContext.request.contextPath}/page/${page.id}">Visit Page</a>

                    <div class="row page-edit">
                        <div class="col-xs-12 col-sm-8">

                            <h3>Page Title</h3>
                            <sf:form modelAttribute="page" action="${pageContext.request.contextPath}/admin/page/edit/${page.id}" method="POST">
                                <div class="form-group">
                                    <sf:hidden path="id"/>
                                    <sf:input id="edit-title" path="title" class="form-control" type="text"></sf:input>
                                    <sf:errors path="title" cssClass="text-danger"></sf:errors>
                                    </div>
                                    <div class="form-group">
                                    <sf:textarea id="edit-content" class="form-control" path="content"></sf:textarea>
                                    <sf:errors path="content" cssClass="text-danger"></sf:errors>
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
        selector: "#edit-content",
        image_caption: true,
        height: 263,
        theme: 'modern',
        plugins: [
            'advlist autolink lists link image hr',
            'wordcount visualblocks visualchars code',
            'insertdatetime media nonbreaking save table contextmenu directionality',
            'paste textpattern imagetools toc'
        ],
        toolbar1: 'undo redo | insert | styleselect | bold italic | alignleft aligncenter alignright alignjustify | bullist numlist outdent indent | link image',
        image_advtab: true
    });
</script>
</body>
</html>