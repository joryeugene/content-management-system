
<nav class="navbar navbar-inverse navbar-fixed-top">
    <div class="container-fluid">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="${pageContext.request.contextPath}/admin">CrossFit Guild</a>
        </div>
        <div id="navbar" class="navbar-collapse collapse">
            <ul class="nav navbar-nav navbar-right">
                <li><a href="${pageContext.request.contextPath}/j_spring_security_logout">Logout <sec:authentication property="principal.username" /> </a></li>
            </ul>

            <ul class="nav navbar-nav navbar-right navbar-hide-on-large">
                <li role="presentation" class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopur="true" href="">Posts</a>
                    <ul class="dropdown-menu">
                        <li><a href="${pageContext.request.contextPath}/admin/posts">All Posts</a></li>
                        <li><a href="${pageContext.request.contextPath}/admin/post/add">Add Post</a></li>
                </li>
            </ul>

            <li role="presentation" class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopur="true" href="">Pages</a>
                <ul class="dropdown-menu">
                    <li><a href="${pageContext.request.contextPath}/admin/pages">All Pages</a></li>
                            <li><a href="${pageContext.request.contextPath}/admin/page/add">Add Page</a></li>
            </li>
            </ul>
            <li role="presentation" class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopur="true" href="">Users</a>
                <ul class="dropdown-menu">
                    <li><a href="">All Users</a></li>
                    <li><a href="">Add User</a></li>
            </li>
            </ul>

            </ul>
            <!--Maybe add back in in later version-->
            <!--                    <form class="navbar-form navbar-right">
                                    <input type="text" class="form-control" placeholder="Search...">
                                </form>-->
        </div>
    </div>
</nav>

