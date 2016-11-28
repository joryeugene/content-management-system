<div class="list-group">
    <c:forEach var="category" items="${categories}">
        <a class="list-group-item" href="${pageContext.request.contextPath}/category/${category.id}">${category.name}</a>
    </c:forEach>
</div>