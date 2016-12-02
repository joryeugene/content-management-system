


<div class="col-sm-3 col-md-2 sidebar">
    <ul class="nav nav-sidebar">
        <li role="presentation" class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopur="true" href="">Posts</a>
            <ul class="dropdown-menu">
                <li><a href="${pageContext.request.contextPath}/admin/posts">All Posts</a></li>
                <li><a href="${pageContext.request.contextPath}/admin/post/add">Add Post</a></li>
        </li>
    </ul>

    <li role="presentation" class=""dropdown"><a class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopur="true" href="">Pages</a>
        <ul class="dropdown-menu">
            <li><a href="${pageContext.request.contextPath}/admin/pages">All Pages</a></li>
            <li><a href="${pageContext.request.contextPath}/admin/page/add">Add Page</a></li>
    </li>
</ul>
<sec:authorize access="hasRole('admin')">
<li><a href="${pageContext.request.contextPath}/admin/userTable">Users</a></li>
</sec:authorize>
</ul>
</ul>

</div>