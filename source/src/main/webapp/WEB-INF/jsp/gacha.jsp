<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" name="viewport" content="width=device-width, initial-scale=1.0">
<title>退勤ガチャ</title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/common.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/header.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/gacha.css">
</head>
<body>

<%@ include file="header.jsp" %>

<div class="gacha-container">
    <div class="envelope" onclick="openEnvelope()">
        <img id="envelope-closed" src="${closedImage}" alt="封筒" width="120" />
        <img id="envelope-opened" src="${openedImage}" alt="開いた封筒" width="120" style="display:none;" />
    </div>   
    <div class="tap-msg">画面をタップ！</div>
</div>

<p>contextPath: <%= request.getContextPath() %></p>

<div class="overlay" id="overlay" style="display:none;"></div>
<div class="modal" id="reward-modal" style="display:none;">
    <div class="close-btn" onclick="location.href='HomeServlet'">×</div>
    <h2>今日のご褒美</h2>
    <p><%= request.getAttribute("rewardItem") != null ? request.getAttribute("rewardItem") : "ご褒美が見つかりませんでした" %></p>
</div>

<script>
function openEnvelope() {
    document.getElementById("envelope-closed").style.display = "none";
    document.getElementById("envelope-opened").style.display = "inline";

    document.getElementById("overlay").style.display = "block";
    document.getElementById("reward-modal").style.display = "block";
}
</script>

</body>
</html>
