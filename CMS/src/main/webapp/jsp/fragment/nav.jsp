<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<nav id="super-nav" class="navbar navbar-fixed-top navbar-inverse">
    <div class="container">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="${pageContext.request.contextPath}/blog"></a>
        </div>
        <div id="navbar" class="collapse navbar-collapse">
            <ul class="nav navbar-nav">
                <c:forEach var="nav" items="${navs}">
                    <li><a href="${pageContext.request.contextPath}/page/${nav.pageId}">${nav.menuName}</a></li>
                </c:forEach>
                <sec:authorize access="hasAnyRole('admin', 'writer')">
                    <li><a href="${pageContext.request.contextPath}/admin">Admin</a></li>
                </sec:authorize>
            </ul>
            <!-- Secret login link -->
            <a href="${pageContext.request.contextPath}/login"><div style="width:100px; height:40px; float:right; cursor:default;"></div></a>
        </div><!-- /.nav-collapse -->
    </div><!-- /.container -->
</nav><!-- /.navbar -->