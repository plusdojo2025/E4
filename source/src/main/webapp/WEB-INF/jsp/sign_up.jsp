<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>新規登録</title>
</head>
<body>

<header class="header">
  <div class="titlelogo_white"> 
     <a href=""><img src="images/sigoowabiyori_title_white.png" alt="しごおわ日和"></a>
  </div>          
</header>

<p>新規登録</P>
<input type="text" name="email" placeholder="メールアドレス"><br>
<input type="text" name="password" placeholder="PW"><br>
<p>※英数混合8文字以上</p>
		<!-- このあたりにエラーメッセージ -->
<button type="submit" name="loginButton" value="登録"></button><br>
<a href="${pageContext.request.contextPath}/LoginServlet">ログインはこちら</a>


</body>
</html>