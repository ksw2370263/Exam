<%@page contentType="text/html; charset=UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<form action="Test.action" method="post">
<p>名前<input type="text" name="keyword"></p>
<p>入学年度<input type="text" name="keyword2"></p>
<input type="submit" value="検索">
</form>
<hr>

<table style="border-collapse:separate;border-spacing:10px">
<c:forEach var="student" items="${list}">
<tr>
<td>${student.entYear}</td>
<td>${student.classNum}</td>
<td>${student.no}</td>
<td>${student.name}</td>
</tr>
</c:forEach>
</table>

