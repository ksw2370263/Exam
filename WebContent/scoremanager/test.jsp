<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<form action="Test.action" method="post">
    <p>入学年度<input type="text" name="keyword"></p>
    <p>クラス<input type="text" name="keyword2"></p>
    <p>科目<input type="text" name="keyword2"></p>
    <input type="submit" value="検索">
</form>
<hr>

<c:if test="${not empty list}">
    <table style="border-collapse: separate; border-spacing: 10px;">
        <tr>
            <th>入学年度</th>
            <th>クラス番号</th>
            <th>学籍番号</th>
            <th>名前</th>
        </tr>
        <c:forEach var="student" items="${list}">
            <tr>
                <td>${student.entYear}</td>
                <td>${student.classNum}</td>
                <td>${student.no}</td>
                <td>${student.name}</td>
            </tr>
        </c:forEach>
    </table>
</c:if>
<c:if test="${empty list}">
    <p>検索結果がありません。</p>
    <table style="border-collapse: separate; border-spacing: 10px;">
        <tr>
            <th>入学年度</th>
            <th>クラス</th>
            <th>学生番号</th>
            <th>氏名</th>
        </tr>
        <c:forEach var="student" items="${list}">
            <tr>
                <td>${student.entYear}</td>
                <td>${student.classNum}</td>
                <td>${student.no}</td>
                <td>${student.name}</td>
            </tr>
        </c:forEach>
    </table>
</c:if>
