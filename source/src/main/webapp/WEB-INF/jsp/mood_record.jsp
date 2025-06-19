<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="ja">
<head>
	<meta charset="UTF-8" name="viewport" content="width=device-width, initial-scale=1.0">
	<title>気分登録</title>

	<!-- 共通CSS -->
	<link rel="stylesheet" href="<c:url value='/css/common.css' />">
	<link rel="stylesheet" href="<c:url value='/css/header.css' />">

	<!-- 気分登録専用CSS -->
	<link rel="stylesheet" href="<c:url value='/css/mood_record.css' />">
</head>
<body>

	<%@ include file="header.jsp" %>

	<main>
		<div class="mood-container">

			 <!--  選択された日付を表示 -->
			
  			<c:if test="${not empty selectedDate}">
			    <div class="selected-date">
			      <p style="font-weight:bold; font-size: 1.2em;">
			      <fmt:formatDate value="${selectedDate}" pattern="d" />日
			      </p>
			    </div>
			 </c:if>
			
<!--------------------- 気分登録フォーム ----------------------->

		<!--  当日だけフォーム表示 -->
			<div id="formArea">
				<c:if test="${isToday}">
				<form action="<c:url value='/MoodRegisterServlet' />" method="post" class="mood-form">
					
					<!------------- 気分選択ボタン（画像） -------------->
					<div class="mood-select-area" id="moodSelectButton">
					
							<!--  気分が選択されていたらその画像を挿入 -->
							<c:choose>
									<c:when test="${not empty registeredMood}">
										<img id="selectedMood" src="<c:url value='/images/mood_${registeredMood}.png' />" alt="今日の気分" class="mood-selected-img">
									</c:when>
									
									<c:otherwise>
										<img id="selectedMood" src="<c:url value='/images/mood_new.png' />" alt="今日の気分は？" class="mood-selected-img">
									</c:otherwise>
							</c:choose>
						
						<!-- 選んだ気分をサーバーに送信 -->
						<input type="hidden" id="moodInput" name="mood" value="${registeredMood}">
					</div>
	
					<!-------------- コメント入力 ----------------->
						<!-- 登録ご再表示時前回の内容を保持 -->
						<div class="comment-area">
							<textarea name="comment" placeholder="ひとこと記録しませんか？※140字以内">${registeredComment}</textarea>
						</div>
					
					<!-- 登録した日付を送信するためのhidden -->
					    <input type = "hidden" id="dayInput" name = "day" value = "${selectedDate}">
					
					<!--------------- 登録ボタン ------------------->
						<div class="submit-area">
							<button type="submit" name="registerButton">登録</button>
						</div>
	
					<!---------- エラーメッセージ表示用 ------------>
						<div id="errorMessage" class="error-message"></div>
				</form>
				</c:if>
			</div>	

<!----------- ご褒美を引いたか否かで表示変更 ---------------------->

			<div class="reward-section">
				<h3>ご褒美</h3>
					<c:choose>
						 <c:when test="${alreadyDrawn}">
						    <p>${rewardItem}</p>
						 </c:when>
						 
						 <c:otherwise>
						    <p>まだご褒美はありません</p>
						 </c:otherwise>	
					</c:choose>
			</div>

<!----------------- 気分ログ -------------------------------->
			<div class="mood-log-section">
				<!--  追加用の空のリスト -->
				<ul id="moodLogList"></ul>
				
				<!--  moodListの中身を一件ずつ表示 -->
				<c:forEach var="record" items="${moodList}">
							
					    <!--  時間、気分画像、コメント表示 -->
						<div class="mood-log-entry">
								<span class="log-date">
		  							<fmt:formatDate value="${record.created_at}" pattern="HH:mm" />
								</span>
							<img src="<c:url value='/images/mood_${record.mood}.png' />" alt="気分" class="log-mood-img" />
							<p class="log-comment">${record.comment}</p>
						</div>
				</c:forEach>
			</div>

<!------------------- モーダル ---------------------------------------->
			<div id="moodSelectModal" class="modal hidden">
				<div class="modal-content">
					<span id="closeModal" class="close-button">&times;</span>
					<p>お疲れ様です！</p>
					<p>今の気分はいかがですか？</p>
					
						<!--  １～５の気分画像順番にまとめて表示 -->
						<div class="mood-options">
							<c:forEach begin="1" end="5" var="i">
								<img class="mood-image" data-mood="${i}" src="<c:url value='/images/mood_${i}.png' />" alt="気分${i}">
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
	
	<!-- Script リンク -->
	<script src="<c:url value='/js/mood_record.js' />"></script>

</body>
</html>
