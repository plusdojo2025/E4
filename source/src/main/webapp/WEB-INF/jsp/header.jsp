<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ja">
<head>
  <meta charset="UTF-8">
  <title>しごおわ日和</title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/header.css">
  <script src="${pageContext.request.contextPath}/js/header.js" defer></script>
</head>
<body>

  <header class="header_1">
    <div class="titlelogo">
      <div class="menu-wrapper" id="menuToggle">
        <div class="hamburger-menu" id="hamburger"></div>
      </div>
      <a href="${pageContext.request.contextPath}/HomeServlet">
        <img src="images/sigoowabiyori_title.png" alt="しごおわ日和">
      </a>
    </div>
  </header>

  <div class="menu-overlay" id="overlay"></div>

  <div class="menu-items" id="menuItems">
    <a href="${pageContext.request.contextPath}/AccountServlet" class="menu-entry entry1">
      <span class="menu-icon">
        <img src="images/account_icon.png" alt="アカウント">
      </span>
      <span class="menu-label">アカウント情報</span>
    </a>

    <a href="${pageContext.request.contextPath}/GachaServlet" class="menu-entry entry2">
      <span class="menu-icon">
        <img src="images/present_icon.png" alt="退勤ガチャ">
      </span>
      <span class="menu-label">退勤ガチャ</span>
    </a>

    <a href="${pageContext.request.contextPath}/ReportServlet" class="menu-entry entry3">
      <span class="menu-icon">
        <img src="images/ReportConfirmation_icon.png" alt="レポート確認">
      </span>
      <span class="menu-label">レポート確認</span>
    </a>
  </div>

</body>
</html>
