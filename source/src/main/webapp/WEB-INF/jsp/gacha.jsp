<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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

<c:choose>
  <c:when test="${alreadyDrawn}">
    <div class="gacha-container">
      <p>本日の退勤ガチャは終了しました</p>
      <div class="tap-msg">引いたご褒美はこちら！</div>
      <div class="envelope" onclick="openEnvelope()">
          <img id="envelope-closed" src="${closedImage}" alt="封筒" width="120" />
          <img id="envelope-opened" src="${openedImage}" alt="開いた封筒" width="120" style="display:none;" />
      </div>
    </div>
  </c:when>
  <c:otherwise>
    <div class="gacha-container">
      <div class="envelope" onclick="openEnvelope()">
          <img id="envelope-closed" src="${closedImage}" alt="封筒" width="120" />
          <img id="envelope-opened" src="${openedImage}" alt="開いた封筒" width="120" style="display:none;" />
      </div>   
      <div class="tap-msg">画面をタップ！</div>
    </div>
  </c:otherwise>
</c:choose>

<!-- モーダル（どちらの状態でも表示できるように外に出して共通化） -->
<div class="overlay" id="overlay" style="display:none;"></div>
<div class="modal" id="reward-modal" style="display:none;">
    <div class="close-btn" onclick="location.href='GachaServlet?forceDrawn=1'">×</div>
    <h2>今日のご褒美</h2>
    <p>
      <c:out value="${rewardItem != null ? rewardItem : 'ご褒美が見つかりませんでした'}" />
    </p>
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
