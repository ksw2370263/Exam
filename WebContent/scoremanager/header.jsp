<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <title>得点管理システム</title>
    <style>
        .header {
            display: flex;
            justify-content: space-between;
            align-items: center;
            padding: 10px;
            border-bottom: 1px solid #ccc;
            background-color: lightblue; /* ヘッダーの背景色を水色に設定 */
        }
        .header h1 {
            margin: 0;
        }
        .user-info {
            display: flex;
            align-items: center;
        }
        .user-info span {
            margin-right: 10px;
        }
        .container {
            display: flex;
        }
        .content {
            flex-grow: 1;
            padding: 20px;
            display: flex; /* メニュー項目を横並びにするためにフレックスボックスを使用 */
            flex-wrap: wrap;
            justify-content: space-around;
        }
        .col {
            display: flex;
            align-items: center;
            justify-content: center;
            margin: 10px;
            border-radius: 10px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            height: 10rem;
            text-align: center;
            color: white;
            font-weight: bold;
            flex: 1; /* メニュー項目が均等に並ぶようにする */
        }
        .student-management {
            background-color: #dbb;
        }
        .grade-management {
            background-color: #00FF00;
            flex-direction: column;
            color: black;
        }
        .subject-management {
            background-color: #BA55D3;
        }
    </style>
</head>
<body>
    <header class="header">
        <h1>得点管理システム</h1>
        <div class="user-info">
            <span><span id="userName">山田太郎</span></span>
            <a href="logout-in.jsp">ログアウト</a>
        </div>
    </header>
</body>
</html>
