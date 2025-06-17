<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" name="viewport" content="width=device-width, initial-scale=1.0">
<title>アカウント情報</title>
  <!-- 全体共通CSS -->
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/common.css"> 
  <!-- ヘッダーCSS -->
   <link rel="stylesheet" href="${pageContext.request.contextPath}/css/header.css"> 
  <!-- アカウント情報専用のCSS -->
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/account.css">
</head>
<body>

<!-- ヘッダー -->
<%@ include file="header.jsp" %>

  <main class="account-main">
  	<div class="account-card">
	      <h2 class="account-title">アカウント情報</h2>
	      <div class="account-label">メールアドレス</div>
	      <div class="account-email">${email}</div>  <!-- セッションから取得したアドレスを表示 -->
      
	      <form action="${pageContext.request.contextPath}/AccountServlet" method="post">
	      	<button type="submit" class="logout-button">ログアウト</button>
	      </form>
    </div>
  </main>
  
</body>
</html>