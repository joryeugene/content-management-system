<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<div class="row badges">
    <div class="col-xs-12 col-sm-12 col-md-4 badges panel">
        <h3 class="panel-title">Posts Needing Approval </h3>
        <div class="panel-body">
            <sec:authorize access="hasAnyRole('writer')">
            <c:forEach begin="0" end="3" var="post" items="${queuedPosts}"> <!-- limit display of 5 at a time -->
                <a href="${pageContext.request.contextPath}/edit/post/${post.id}" class="btn btn-default btn-stacked pointer"><span class="glyphicon glyphicon-th"></span> ${fn:substring(post.title, 0, 25)}  <span class="badge"> ${post.numViews} </span></a> 
            </c:forEach>
        </sec:authorize>
        <sec:authorize access="hasAnyRole('admin')">
            <c:forEach begin="0" end="3" var="post" items="${queuedPosts}"> <!-- limit display of 5 at a time -->
                <a onclick="approveStatic(${post.id})"class="btn btn-default btn-stacked pointer"><span class="glyphicon glyphicon-th"></span> ${fn:substring(post.title, 0, 25)}  <span class="badge"> ${post.numViews} </span></a> 
            </c:forEach>
        </sec:authorize>
        </div>
    </div>
    <div class="col-xs-12 col-sm-12 col-md-4 badges panel">
        <h3 class="panel-title">Recent Post Performance</h3>
        <div class="panel-body">
            <c:forEach  begin="0" end="3" var="post" items="${viewsListRecent}">
                <a href="${pageContext.request.contextPath}/edit/post/${post.id}"class="btn btn-default btn-stacked"><span class="glyphicon glyphicon-th"></span> ${fn:substring(post.title, 0, 25)}  <span class="badge"> ${post.numViews} </span></a>
            </c:forEach>
        </div>
    </div>
    <div class="col-xs-12 col-sm-12 col-md-4 badges panel">
        <h3 class="panel-title">Most Viewed Posts--All Time</h3>
        <div class="panel-body">
            <c:forEach  begin="0" end="3" var="post" items="${viewsListAllTime}">
                <a href="${pageContext.request.contextPath}/edit/post/${post.id}"class="btn btn-default btn-stacked"><span class="glyphicon glyphicon-th"></span> ${fn:substring(post.title, 0, 25)}  <span class="badge"> ${post.numViews} </span></a>
            </c:forEach>
        </div>
    </div>
</div>

