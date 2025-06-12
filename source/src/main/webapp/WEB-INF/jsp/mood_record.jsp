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
			<link rel="stylesheet"
				href="${pageContext.request.contextPath}/css/home.css">
				
			<link rel="stylesheet" href="${pageContext.request.contextPath}/css/mood_record.css">
	</head>
<body>
		<%@ include file="header.jsp" %>
<!---------------↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑共通↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑--------------------------------->

	 <!-- カレンダーから日付を持ってくる -->
	 
	 <!-- mood_new.pngを押すことでモーダルを開く -->
	 <div id=moodSelectButton>
	 <img src="${pageContext.request.contextPath}/images/mood_new.png" alt="今日の気分は？"><!-- 絶対パス -->
	 </div>
	 
	 
	 
	 
	 <!-- モーダル本体 -->
	 <div id="moodSelectModal"  class="hidden">
		 <div class="modal-content">
		 
		 <!--  ✕ボタン -->
	     <span id="closeModal" class="close-button">&times;</span>
	     
			 <p>お疲れ様です！</p>
			 <p>今の気分はいかがですか？</p>
				 <div><!-- 絶対パス -->
					 <img src="${pageContext.request.contextPath}/images/mood_1.png" alt="気分1">
					 <img src="${pageContext.request.contextPath}/images/mood_2.png" alt="気分2">
					 <img src="${pageContext.request.contextPath}/images/mood_3.png" alt="気分3">
					 <img src="${pageContext.request.contextPath}/images/mood_4.png" alt="気分4">
					 <img src="${pageContext.request.contextPath}/images/mood_5.png" alt="気分5">
				 </div>
		 </div>	 	
	 </div>
	 
	 
	 
	 
	 
<script type="module">
  import { ModalController } from './js/mood_record.js';
  new ModalController();
</script>
	
	</body>
	</html>