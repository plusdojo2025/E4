<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
 <head>
 <meta charset="UTF-8" name="viewport" content="width=device-width, initial-scale=1.0">
	<title>新規登録</title>
	  <!-- 全体共通CSS -->
	  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/common.css">
	  <!-- 新規登録ページ専用のCSS -->
	  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/sign_up.css">
  </head>
<body>

<header class="header_2">
  <div class="titlelogo_white"> 
     <a href=""><img src="images/sigoowabiyori_title_white.png" alt="しごおわ日和"></a>
  </div>          
</header>

<main class="signup-container">
	<form action="SignUpServlet" method="post">
	<h2>新規登録</h2>
		<input type="text" name="email" placeholder="メールアドレス"><br>
		<input type="text" name="password" placeholder="PW"><br>
		<p>※英数字混合8文字以上</p>
				<!-- このあたりにエラーメッセージ -->
		<button type="submit" name="loginButton" value="登録">登録</button>
		<a href="${pageContext.request.contextPath}/LoginServlet">ログインはこちら</a>
	</form>
</main>


</body>
</html>