<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8" name="viewport" content="width=device-width, initial-scale=1.0">
	<title>気分登録</title>

	<!-- 共通CSS -->
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/common.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/header.css">

	<!-- 気分登録専用CSS -->
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/mood_record.css">
</head>
<body>

	<%@ include file="header.jsp" %>

	<main>
		<div class="mood-container">

			 <!--  選択された日付を表示 -->
  			<c:if test="${not empty selectedDate}">
			    <div class="selected-date">
			      <p style="font-weight:bold; font-size: 1.2em;">${selectedDate} </p>
			    </div>
			 </c:if>
			
			<!-- 気分登録フォーム -->
		<div id="formArea">
			<form action="MoodRegisterServlet" method="post" class="mood-form">
				
				<!-- 気分選択ボタン（画像） -->
			
				<div class="mood-select-area" id="moodSelectButton">
					<c:choose>
						<c:when test="${not empty registeredMood}">
							<img id="selectedMood" src="${pageContext.request.contextPath}/images/mood_${registeredMood}.png" alt="今日の気分" class="mood-selected-img">
						</c:when>
						<c:otherwise>
							<img id="selectedMood" src="${pageContext.request.contextPath}/images/mood_new.png" alt="今日の気分は？" class="mood-selected-img">
						</c:otherwise>
					</c:choose>
					<input type="hidden" id="moodInput" name="mood" value="${registeredMood}">
				</div>

				<!-- コメント入力 -->
				<div class="comment-area">
					<textarea name="comment" placeholder="ひとこと記録しませんか？※140字以内">${registeredComment}</textarea>
				</div>
				<!-- 選択された日付持ってくる -->
				<input type = "hidden" id="dayInput" name = "day" value = "${selectedDate}">
				
				<!-- 登録ボタン -->
				<div class="submit-area">
					<button type="submit" name="registerButton">登録</button>
				</div>

				<!-- エラーメッセージ表示 -->
				<div id="errorMessage" class="error-message"></div>
			</form>
		</div>	

			<!-- 今日のご褒美 -->
			<div class="reward-section">
				<h3>今日のご褒美</h3>
					<c:choose>
						 <c:when test="${alreadyDrawn}">
						    <p>${rewardItem}</p>
						 </c:when>
						 <c:otherwise>
						    <p>まだご褒美はありません</p>
						 </c:otherwise>
					</c:choose>
			</div>

			<!-- 気分ログ -->
			<div class="mood-log-section">
				<ul id="moodLogList"></ul>

				<c:forEach var="record" items="${moodList}">
					<div class="mood-log-entry">
						<span class="log-date">
  							<fmt:formatDate value="${record.created_at}" pattern="HH:mm" />
						</span>
						<img src="${pageContext.request.contextPath}/images/mood_${record.mood}.png" alt="気分" class="log-mood-img" />
						<p class="log-comment">${record.comment}</p>
					</div>
				</c:forEach>
			</div>

			<!-- モーダル -->
			<div id="moodSelectModal" class="modal hidden">
				<div class="modal-content">
					<span class="close-button" onclick="closeModal()">×</span>
					<span id="closeModal" class="close-button">&times;</span>
					<p>お疲れ様です！</p>
					<p>今の気分はいかがですか？</p>

					<div class="mood-options">
						<c:forEach begin="1" end="5" var="i">
							<img class="mood-image" data-mood="${i}" src="${pageContext.request.contextPath}/images/mood_${i}.png" alt="気分${i}">
						</c:forEach>
					</div>

					<div class="mood-labels">
						<p>BAD</p>
						<p>GOOD</p>
					</div>
				</div>
			</div>

		</div>
	</main>

	<!-- モーダル制御用JS -->
	<script type="module">
		import { ModalController } from './js/mood_record.js';
		new ModalController();
	</script>

</body>
</html>
