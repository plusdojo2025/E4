<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   

<!DOCTYPE html>
<html>
<!-- ------------↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓共通↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓------------------------->
	<head>
			<meta charset="UTF-8" name="viewport"
				content="width=device-width, initial-scale=1.0">
			<title>気分登録</title>
			<!-- 全体共通CSS -->
			<link rel="stylesheet"
				href="${pageContext.request.contextPath}/css/common.css">
			<!-- ヘッダーCSS -->
  				<link rel="stylesheet" href="${pageContext.request.contextPath}/css/header.css">
  				
			<!-- ホーム専用のCSS -->
			<!-- <link rel="stylesheet"
				href="${pageContext.request.contextPath}/css/home.css">-->
				
			<!-- 気分登録専用のCSS -->
			<link rel="stylesheet" href="${pageContext.request.contextPath}/css/mood_record.css">
	</head>
<body>
		<%@ include file="header.jsp" %>
<!---------------↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑共通↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑--------------------------------->

	 <!-- カレンダーから日付を持ってくる -->
	 
	 
	 <form>
	 <!-- mood_new.pngを押すことでモーダルを開く -->
	 <div id=moodSelectButton>
		 <img id="selectedMood"
					src="${pageContext.request.contextPath}/images/mood_new.png" alt="今日の気分は？"
					style="width: 100px; height: auto;"><!-- 絶対パス -->
		<!-- 選択された気分差し込み用 -->
		 <input type="hidden" name="mood" id="moodInput" value="">
	 </div>
	 
	 <div id="commentArea">
	 	 <textarea name="commentArea" placeholder="ひとこと記録しませんか？※140字以内"></textarea>
	 </div>
	 
	 <div id="registerButton">
	 	 <button type="submit" name="registerButton">登録</button>
	 </div>
	 
	 </form>
	 
	 <p>今日のご褒美</p>
	 <!-- ガチャ結果表示 -->
	 
	 <!-- ログ表示 -->
	 <div>
	 	<ul id="moodLogList"></ul>
	 </div>
	 
	 
	 
	 
	 <!-- モーダル本体 -->
	 <div id="moodSelectModal"  class="hidden">
		 <div class="modal-content">
		 
		 <!--  ✕ボタン -->
	     <span id="closeModal" class="close-button">&times;</span>
	     
			 <p>お疲れ様です！</p>
			 <p>今の気分はいかがですか？</p>
				 <div><!-- 絶対パス -->
					<img class="mood-image" data-mood="1" src="${pageContext.request.contextPath}/images/mood_1.png" alt="気分1" style="width: 100px; height: auto;">
					<img class="mood-image" data-mood="2" src="${pageContext.request.contextPath}/images/mood_2.png" alt="気分2" style="width: 100px; height: auto;">
					<img class="mood-image" data-mood="3" src="${pageContext.request.contextPath}/images/mood_3.png" alt="気分3" style="width: 100px; height: auto;">
					<img class="mood-image" data-mood="4" src="${pageContext.request.contextPath}/images/mood_4.png" alt="気分4" style="width: 100px; height: auto;">
					<img class="mood-image" data-mood="5" src="${pageContext.request.contextPath}/images/mood_5.png" alt="気分5" style="width: 100px; height: auto;">
				 </div>
				 <div>
					 <p>BAD</p>
					 <p>GOOD</p>
				 </div>
		 </div>	 	
	 </div>
	 
	 
	 
	 
	 
<script type="module">
  import { ModalController } from './js/mood_record.js';
  new ModalController();
</script>
	
	</body>
	</html>