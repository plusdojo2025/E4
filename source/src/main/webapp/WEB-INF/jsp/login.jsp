<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ログイン</title>
<link rel="stylesheet" href="">
</head>

<body>
<header class="header">
	<div class="titlelogo">
    	<a href=""><img src="images/sigoowabiyori_title" alt="しごおわ日和"></a>
	</div>
</header>
	

<main class="login-container">
    <h2>ログイン</h2>
	<form action="LoginServlet" method="post" class="">
        <input type="text" name="email" placeholder="メールアドレス" required><br>
        <input type="password" name="password" placeholder="パスワード" required><br>
        <p class="">※英数字混合8文字以上</p>
        <button type="submit">ログイン</button>
    </form>

	<div class="">
        <a href="">アカウントをお持ちでない方はこちら</a>
    </div>
</main>

</body>
</html>