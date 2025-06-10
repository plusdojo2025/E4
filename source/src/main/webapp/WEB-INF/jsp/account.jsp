<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<p>新規登録</P>
<input type="text" name="email" placeholder="メールアドレス"><br>
<input type="text" name="password" placeholder="PW"><br>
<p>※英数混合8文字以上</p>
		<!-- このあたりにエラーメッセージ -->
<button type="submit" name="loginButton" value="登録"></button><br>
<a href="E4/AccountServlet" >新規登録</a>


</body>
</html>