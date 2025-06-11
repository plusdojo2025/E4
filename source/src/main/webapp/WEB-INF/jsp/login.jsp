<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ログイン</title>
  <!-- 全体共通CSS -->
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/common.css">
  <!-- ログインページ専用のCSS -->
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/login.css">
</head>

<body>
<header class="header_2">
  <div class="titlelogo_white"> 
     <a href=""><img src="images/sigoowabiyori_title_white.png" alt="しごおわ日和"></a>
  </div>          
</header>
	

<main class="login-container">
    <h2>ログイン</h2>
	<form action="LoginServlet" method="post" class="">
        <input type="text" name="email" placeholder="メールアドレス" required><br>
        <input type="password" name="password" placeholder="PW" required><br>
        <p class="">※英数字混合8文字以上</p>
        <button type="submit">ログイン</button>
    </form>

	<div class="">
        <a href="${pageContext.request.contextPath}/SignUpServlet">アカウントをお持ちでない方はこちら</a>
    </div>
</main>

</body>
</html>