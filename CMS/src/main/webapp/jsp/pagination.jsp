<%@ page import="java.util.*" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<%
    List<String> letters = Arrays.asList("a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z");
%>
<c:set var="letters" scope="session" value="<%=letters%>"/>
<c:set var="totalCount" scope="session" value="<%=letters.size()%>"/>
<c:set var="perPage" scope="session"  value="${5}"/>
<c:set var="pageStart" value="${param.start}"/>
<c:if test="${empty pageStart or pageStart < 0}">
    <c:set var="pageStart" value="0"/>
</c:if>
<c:if test="${totalCount < pageStart}">
    <c:set var="pageStart" value="${pageStart - perPage}"/>
</c:if>
<a href="?start=${pageStart - perPage}"><<</a>${pageStart + 1} - ${pageStart + perPage} 
<a href="?start=${pageStart + perPage}">>></a>                                               
<h1>Letters</h1>
<c:forEach var="letter" items="${letters}" 
           begin="${pageStart}" end="${pageStart + perPage - 1}">
    ${letter}
</c:forEach>