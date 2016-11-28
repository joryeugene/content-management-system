<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<div class="list-group">
    <a class="list-group-item active" href="${pageContext.request.contextPath}/summary/">All Categories</a>
    <c:forEach var="category" items="${categories}">
        <a class="list-group-item" href="${pageContext.request.contextPath}/category/${category.id}">${category.name}</a>
    </c:forEach>

    <div class="text-center" style="margin-top: 20px;">
        <c:forEach var="hashtag" items="${hashtags}">
            <c:set var="hashtagLink" value="${fn:replace(hashtag, '#', '')}" />
            <a href="${pageContext.request.contextPath}/hashtag/${hashtagLink}">${hashtag}</a>
        </c:forEach>
    </div>
</div>