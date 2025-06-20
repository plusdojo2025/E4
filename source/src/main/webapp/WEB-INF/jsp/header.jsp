<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!--  これはjspの一部に読み込まれるファイルなのでbodyとかheaderとかは書けません -->

<script src="${pageContext.request.contextPath}/js/header.js" defer></script>
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
  
  <!-- ▼ 改善済：退勤ガチャ 表示制御エリア -->
  <div class="menu-entry entry2">
    <a id="gachaLink" href="${pageContext.request.contextPath}/GachaServlet">
      <span class="gacha-wrapper-header">
        <span class="menu-icon gacha">
          <img src="images/present_icon.png" alt="退勤ガチャ">
        </span>
        <span class="menu-label" id="gachaLabel">退勤ガチャ</span>
      </span>
    </a>
  </div>
  
  <a href="${pageContext.request.contextPath}/ReportServlet" class="menu-entry entry3">
    <span class="menu-icon">
      <img src="images/ReportConfirmation_icon.png" alt="レポート確認">
    </span>
    <span class="menu-label">レポート確認</span>
  </a>
</div>
