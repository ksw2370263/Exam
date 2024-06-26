<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <title>得点管理システム</title>
    <style>
        .container {
            display: flex;
        }
        .sidebar {
            width: 200px;
            padding: 10px;
            border-right: 1px solid #ccc;
        }
        .sidebar ul {
            list-style-type: none;
            padding: 0;
        }
        .sidebar li {
            margin: 10px 0;
        }
        .sidebar a {
            text-decoration: none;
            color: #000;
        }
        .sidebar label {
            font-weight: bold;
        }
        .sub-menu {
            margin-left: 20px;
        }
        .content {
            flex-grow: 1;
            padding: 20px;
            display: flex; /* メニュー項目を横並びにするためにフレックスボックスを使用 */
            flex-wrap: wrap;
            justify-content: space-around;
        }
        .footer {
            display: flex;
            justify-content: center;
            align-items: center;
            padding: 10px;
            border-top: 1px solid #ccc;
            margin-top: 20px;
        }
        .footer p {
            margin: 0 20px;
        }
    </style>
</head>
<body>
    <%@ include file="header.jsp" %>
    <div class="container">
        <%@ include file="sideber.jsp" %>
        <div class="content">

        </div>
    </div>
    <%@ include file="footer.jsp" %>
</body>
</html>
