<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
    <img id="selectedMoodImg" src="<%= (mood == 0) ? "images/mood_new.png" : "images/mood" + mood + ".png" %>" alt="気分" style="width:100px; cursor:pointer;">
    <input type="hidden" name="mood" id="selectedMoodValue" value="<%= mood %>"><br>

    <textarea name="comment" maxlength="140" placeholder="ひとこと記録しませんか？※１４０文字以内"><%= comment %></textarea><br>

    <input type="submit" value="登録"><br>
    <% if (error != null) { %>
      <p style="color:red;"><%= error %></p>
    <% } %>
</form>

<!-- モーダル表示部分 -->
<div id="moodModal">
  <p>お疲れ様です！今の気分はいかがですか？</p>
  <div>
    <img src="images/mood1.png" data-mood="1" class="moodChoice" style="width:60px; cursor:pointer;">
    <img src="images/mood2.png" data-mood="2" class="moodChoice" style="width:60px; cursor:pointer;">
    <img src="images/mood3.png" data-mood="3" class="moodChoice" style="width:60px; cursor:pointer;">
    <img src="images/mood4.png" data-mood="4" class="moodChoice" style="width:60px; cursor:pointer;">
    <img src="images/mood5.png" data-mood="5" class="moodChoice" style="width:60px; cursor:pointer;">
  </div>
  <br>
  <button id="modalClose">閉じる</button>
</div>

<script src="js/mood_record.js"></script>
</body>
</html>