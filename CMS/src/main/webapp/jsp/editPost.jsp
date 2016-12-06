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
        <link href="${pageContext.request.contextPath}/css/jquery.tagit.css" rel="stylesheet" type="text/css">
        <link href="http://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css" rel="stylesheet" type="text/css">
        <!--TinyMCE-->
        <script src='https://cdn.tinymce.com/4/tinymce.min.js'></script>
        <title>Edit Post </title>
    </head>
    <body>
        <%@include file="fragment/topbar.jsp" %>
        <div class="container-fluid">
            <div class="row">
                <%@include file="fragment/sidebar.jsp" %>
                <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
                    <h1 class="page-header">Edit Post</h1>
                    <a href="javascript:history.back()"><- Back</a> | <a href="${pageContext.request.contextPath}/post/${post.id}" >Visit Post</a>

                    <div class="row page-edit">
                        <div class="col-xs-12 col-sm-8">
                            <div>
                                <h3>Post Title</h3>
                                <sf:form modelAttribute="post" action="${pageContext.request.contextPath}/edit/post/${post.id}" method="POST">
                                    <div class="form-group">
                                        <sf:input id="edit-title" path="title" class="form-control" type="text"></sf:input>
                                        <sf:errors path="title" cssClass="error"></sf:errors>
                                        </div>


                                    </div>

                                    <div class="form-group">
                                    <sf:textarea path="content" id="contentEditArea"></sf:textarea>
                                    </div>
                                </div>
                                <div class="col-xs-12 col-sm-4">
                                    <div class="publish-box">
                                        <h3>Publish</h3>

                                        <!--TODO Date Picker-->
                                        <div class="form-group">
                                        <sf:input path="stringStartDate" type="date" id="starting-date"></sf:input>
                                        <sf:errors path="stringStartDate" cssClass="error"></sf:errors>
                                        <label>Start Publishing</label>
                                        </div>
                                        <div class="form-group">
                                        <sf:input path="stringEndDate" type="date" id="ending-date"></sf:input>
                                        <sf:errors path="stringEndDate" cssClass="error"></sf:errors>
                                        <label>End Publishing</label>
                                        </div>
                                        <div class="form-group"> 
                                            <div  class="form-group">
                                            <label>Category</label>   
                                        <sf:select path="category.id" cssClass="form-control">
                                            <sf:options items="${allCategories}" itemValue="id" itemLabel="name"/>
                                        </sf:select>
                                    </div>
                                        
                                        <div class="form-group">
                                            <label>Hashtags</label>
                                        <sf:input type="text" path="hashtags" id="hashtags"></sf:input>
                                        </div>
                                    <button class="btn btn-primary" type="submit">Publish</button>
                                </sf:form>
                            </div> 
                            <div class="categories-box">

                                <form>
                                    <div class="bg-danger" id="validation-errors-category"></div>
                                    <div class="form-group">
                                        <input type="text" class="form-control" name="createCategory" id="createCategory"/>
                                    </div>
                                    <div class="form-group">
                                        <button onclick="doAddCategory() "class="btn btn-default" type="submit">Create A New Category</button>
                                    </div>

                                </form>



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
<script src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.12.1/jquery-ui.min.js" type="text/javascript" charset="utf-8"></script>
<script src="${pageContext.request.contextPath}/js/tag-it.js" type="text/javascript" charset="utf-8"></script>
<script src="${pageContext.request.contextPath}/js/editPost.js" type="text/javascript" charset="utf-8"></script>

</body>
</html>