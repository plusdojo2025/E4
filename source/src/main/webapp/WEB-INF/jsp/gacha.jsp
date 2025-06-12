<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
  <!-- 全体共通CSS -->
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/common.css">
  <!-- ヘッダーCSS -->
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/header.css">
  <!-- ガチャ画面専用のCSS -->
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/gacha.css">
</head>
<body>

<!-- ヘッダー -->
<%@ include file="header.jsp" %>

    <!-- ① 初期表示：封筒（閉じ） -->
    <div class="envelope" onclick="openEnvelope()">
    <img id="envelope-closed" src="${pageContext.request.contextPath}/${closedImage}" alt="封筒" width="120" />
    <img id="envelope-opened" src="${pageContext.request.contextPath}/${openedImage}" alt="開いた封筒" width="120" style="display:none;" />
    </div>   
    <div class="tap-msg">画面をタップ！</div>

    <!-- ③ ご褒美モーダル -->
    <div class="overlay" id="overlay"></div>
    <div class="modal" id="reward-modal">
        <div class="close-btn" onclick="location.href='HomeServlet'">×</div>
        <h2>今日のご褒美</h2>
        <p><%= request.getAttribute("rewardItem") != null ? request.getAttribute("rewardItem") : "ご褒美が見つかりませんでした" %></p>
    </div>

</body>

</body>
</html>