<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ja">
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
   <a href="HomeServlet" class="arrow-link">
         <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 28 28" class="arrow-icon" width="28" height="28">
           <path d="M18 6 L8 14 L18 22 Z" />
         </svg>
        </a>

    <h2 class="account-title">アカウント情報</h2>
    <div class="account-label">メールアドレス</div>
    <div class="account-email">${email}</div>

    <form action="${pageContext.request.contextPath}/AccountServlet" method="post">
      <button type="submit" class="logout-button">ログアウト</button>
    </form>
  </div>
</main>

</body>
</html>
