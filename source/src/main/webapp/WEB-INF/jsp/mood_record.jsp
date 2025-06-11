<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- リクエスト属性空MoodRecordオブジェクトを取得 -->
<%
    model.MoodRecord record = (model.MoodRecord) request.getAttribute("record");
    int mood = (record != null) ? record.getMood() : 0;
    String comment = (record != null && record.getComment() != null) ? record.getComment() : "";
    String error = (String) request.getAttribute("error");
%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>気分登録</title>
		<link rel="stylesheet" href="css/mood_record.css">
	
	</head>

<body>
	<header class="header">
		  <div class="titlelogo"> 
		     <a href=""><img src="images/menu.png" alt="メニューバー"></a>
		     <a href=""><img src="images/sigoowabiyori_title.png" alt="しごおわ日和"></a>
		  </div> 
	</header>

	<form action="MoodRegisterServlet" method="post" id="moodForm">
			<!-- mood_new.pngでモーダル開く -->
		    <img id="selectedMoodImg" src="images/mood_new.png" alt="気分選択">
		    
		    <!-- 選択された気分画像を保持 -->
		    <input type="hidden" name="mood" id="selectedMoodValue" value="<%= mood %>"><br>
		    
		    <!-- 現在選択されている画像を表示 -->
		    <img id="currentMoodImg" src="<%= (mood == 0) ? "images/mood_new.png" : "images/mood" + mood + ".png" %>" alt="現在の気分" style="width:100px;">
		    
			<!-- コメント -->
		    <textarea name="comment" maxlength="140" placeholder="ひとこと記録しませんか？<br>※１４０文字以内"><%= comment %></textarea><br>
		
		    <input type="submit" value="登録"><br>
		    
		    <!-- エラー表示 -->
		    <% if (error != null) { %>
		      <p style="color:red;"><%= error %></p>
		    <% } %>
	</form>

	<!-- モーダル表示部分 -->
	<div id="moodModal">
	<div>
	  <p>お疲れ様です！</p><br>
	  <p>今の気分はいかがですか？</p>
		  <div>
		    <img src="images/mood_1.png" data-mood="1" class="moodChoice" style="width:60px; cursor:pointer;">
		    <img src="images/mood_2.png" data-mood="2" class="moodChoice" style="width:60px; cursor:pointer;">
		    <img src="images/mood_3.png" data-mood="3" class="moodChoice" style="width:60px; cursor:pointer;">
		    <img src="images/mood_4.png" data-mood="4" class="moodChoice" style="width:60px; cursor:pointer;">
		    <img src="images/mood_5.png" data-mood="5" class="moodChoice" style="width:60px; cursor:pointer;">
		  </div>
	  <button id="modalClose" type="button">閉じる</button>
	  </div>
	</div>

<script src="js/mood_record.js"></script>
</body>
</html>