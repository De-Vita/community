<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 2023-05-09
  Time: 오후 1:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="/member/save" method="post" enctype="multipart/form-data">
    <input type="text" name="memberEmail" placeholder="이메일">
    <input type="text" name="memberPassword" placeholder="비밀번호">
    <input type="text" name="memberName" placeholder="이름">
    <input type="text" name="memberMobile" placeholder="전화번호">
    <input type="file" name="memberProfile" placeholder="프로필사진">
    <input type="submit" value="가입">
</form>
</body>
</html>
