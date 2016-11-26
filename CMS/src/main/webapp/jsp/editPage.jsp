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
        <link href="${pageContext.request.contextPath}/css/dashboard.css" rel="stylesheet">
        <!--TinyMCE-->
        <script src='https://cdn.tinymce.com/4/tinymce.min.js'></script>
        <title>Edit Page </title>
    </head>
    <body>
        <%@include file="fragment/topbar.jsp" %>
        <div class="container-fluid">
            <div class="row">
                <div class="col-sm-3 col-md-2 sidebar">

                    <ul class="nav nav-sidebar">
                        <li role="presentation" class=""dropdown"><a class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopur="true" href="">Posts</a>
                            <ul class="dropdown-menu">
                                <li><a href="">All Posts</a></li>
                                <li><a href="">Add Post</a></li>
                        </li>
                    </ul>

                    <li role="presentation" class=""dropdown"><a class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopur="true" href="">Pages</a>
                        <ul class="dropdown-menu">
                            <li><a href="">All Pages</a></li>
                            <li><a href="">Add Page</a></li>
                    </li>
                    </ul>
                    <li role="presentation" class=""dropdown"><a class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopur="true" href="">Users</a>
                        <ul class="dropdown-menu">
                            <li><a href="">All Users</a></li>
                            <li><a href="">Add User</a></li>
                    </li>
                    </ul>
                    </ul>

                </div>
                <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
                    <h1 class="page-header">Edit Page</h1>

                    <div class="row page-edit">
                        <div class="col-xs-12 col-sm-8">
                            <div>
                                <h3>Page Title</h3>
                                <form>
                                    <div class="form-group">
                                        <input class="form-control" type="text" />
                                    </div>
                                </form>

                            </div>
                            <div>  <form method="post">
                                    <textarea id="contentEditArea">Page Content Here</textarea>
                                </form></div>
                        </div>
                        <div class="col-xs-12 col-sm-4">
                            <div class="publish-box">
                                <h3>Publish</h3>
                                <form>
                                    <!--TODO Date Picker-->
                                    <input type="text" id="starting-date" /> <label>Starting Date</label>
                                    <input type="text" id="starting-date" /> <label>Ending Date</label>
                                    <div class="checkbox">
                                        <label>
                                            <input type="checkbox"> Publish post forever
                                        </label>
                                    </div>
                                    <button type="submit">Publish</button>
                                </form>
                            </div> 
                            <div class="categories-box">
                                <form>
                                <input type="text" class="form-control" name="createCategory" id="createCategory"/>
                                <button class="btn btn-default" type="submit">Create Category</button>
                                </form>
                                <form>
                                    <div class="form-group">   
                                <select class="form-control">
                                    <option>Choose Category</option>
                                    <option>2</option>
                                    <option>3</option>
                                    <option>4</option>
                                    <option>5</option>
                                </select>
                                    </div>
                                    <div class="form-group">
                                    <button class="btn btn-default" type="submit">Add Category to Page</button>
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
<script src="${pageContext.request.contextPath}/js/editPage.js"></script>
<!--Initialize TinyMCE-->
<script>
    tinymce.init({
        selector: '#contentEditArea'
    });
</script>
</body>
</html>