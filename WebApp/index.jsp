<%--
  Created by IntelliJ IDEA.
  User: XUYH
  Date: 2019/1/15
  Time: 10:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Welcome to SpringBoot</title>
</head>
<body>
    <h1>欢迎来到SpringBoot</h1>
    <p>这是一个SpringBoot学习项目</p>
    <form method="post" action="/SpringBootDemo/upload" enctype="multipart/form-data">
        <input type="file" name="file"><br>
        <input type="file" name="file"><br>
        <input type="file" name="file"><br>
        <input type="file" name="file"><br>
        <input type="submit" value="提交">
    </form>
</body>
</html>
