<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8" name="viewport" content="width=device-width, initial-scale=1.0">
<title>退勤ガチャ</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/common.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/header.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/gacha.css">
</head>
<body>

<%@ include file="header.jsp" %>
<div class="gacha-wrapper">
  <c:choose>

    <c:when test="${alreadyDrawn}">
      <div class="gacha-container">
        <a href="HomeServlet" class="arrow-link">
         <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 28 28" class="arrow-icon" width="28" height="28">
           <path d="M18 6 L8 14 L18 22 Z" />
         </svg>
        </a>

        <p class="gacha-finished">本日の退勤ガチャは終了しました</p>

        <div class="reward-message">
          <h2>今日のご褒美</h2>
          <p class="reward-text">
            <c:out value="${rewardItem != null ? rewardItem : 'ご褒美が見つかりませんでした'}" />
          </p>
        </div>
      </div>
    </c:when>

    <c:otherwise>
      <div class="gacha-container">
        <a href="HomeServlet" class="arrow-link">
         <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 28 28" class="arrow-icon" width="28" height="28">
           <path d="M18 6 L8 14 L18 22 Z" />
         </svg>
        </a>

        <div class="envelope" onclick="openEnvelope()">
          <img id="envelope-closed" src="<c:url value='${closedImage}' />" alt="封筒" width="120" class="floating"/>
          <img id="envelope-opened" src="<c:url value='${openedImage}' />" alt="開いた封筒" width="120" style="display:none;" />
        </div>

        <div class="tap-msg">画面をタップ！</div>

        <!-- モーダル（初回は非表示） -->
        <div class="overlay" id="overlay" style="display:none;"></div>
        <div class="modal" id="reward-modal" style="display:none;">
          <div class="close-btn" onclick="redirectToGacha()">×</div>
          <h2>今日のご褒美</h2>
          <p>
            <c:out value="${rewardItem != null ? rewardItem : 'ご褒美が見つかりませんでした'}" />
          </p>
        </div>
      </div>
    </c:otherwise>

  </c:choose>
</div>

<script>
//初回ガチャ用の封筒開封＋モーダル演出
let alreadyOpened = false;

function openEnvelope() {
    if (alreadyOpened) return;
    alreadyOpened = true;

    document.getElementById("envelope-closed").style.display = "none";
    const opened = document.getElementById("envelope-opened");
    opened.style.display = "inline";
    opened.classList.add("opened-smooth");

    // アニメーション終了イベントでfetch・モーダル表示を実行
    opened.addEventListener("animationend", () => {
        fetch("${pageContext.request.contextPath}/GachaServlet?action=draw")
            .then(response => response.json())
            .then(data => {
                const rewardModal = document.getElementById("reward-modal");
                const rewardText = rewardModal.querySelector("p");
                rewardText.textContent = data.rewardItem || 'ご褒美が見つかりませんでした';

                document.getElementById("overlay").style.display = "block";
                rewardModal.style.display = "block";
                rewardModal.classList.add("show");
                document.body.classList.add("modal-open");
            })
            .catch(error => {
                alert("エラーが発生しました");
                console.error(error);
            });
    }, { once: true });
}

function redirectToGacha() {
  document.body.classList.remove("modal-open");
  window.location.href = '${pageContext.request.contextPath}/GachaServlet';
}
</script>

</body>
</html>
