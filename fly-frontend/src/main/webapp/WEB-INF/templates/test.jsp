<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>test page</title>
</head>
<body>
<form action="/user/uploadAvatar" method="post" enctype="multipart/form-data">
    <input type="file" accept="image/*" name="avatar">
    <button type="submit">提交</button>
</form>
<h2>hello spring ${word}</h2>
</body>
</html>