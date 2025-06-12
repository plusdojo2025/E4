<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>アカウント情報</title>
  <!-- 全体共通CSS -->
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/common.css">
  <!-- ホーム専用のCSS -->
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/home.css">
</head>

<body>
<header class="header_1">
  <div class="titlelogo"> 
     <a href=""><img src="images/menu.png" alt="メニューバー"></a>
     <a href=""><img src="images/sigoowabiyori_title.png" alt="しごおわ日和"></a>
  </div>          
</header>
  <main>
    <h2>アカウント情報</h2>
      <div class="">メールアドレス</div>
      <div>${email}</div>  <!-- セッションから取得したアドレスを表示 -->
      
      <form action="/E4/LogoutServlet" method="get">
      	<button type="submit" class="logout-button">ログアウト</button>
      </form>
  </main>
</body>
</html>