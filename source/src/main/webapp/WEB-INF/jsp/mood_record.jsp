<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>気分登録</title>
<link rel="stylesheet" href="css/mood_record.css">
</head>
<body>

<!-- 押したらモーダル開くー -->
<img  src="images/mood_new.png" alt="気分を選択" style="width:60px;">

<!-- モーダル -->
<div id="moodModal" style="display:none; position:fixed; top:20%; left:30%; background:white; border:1px solid #ccc; padding:10px;">
  <p>気分を選んでください</p>
  <img src="images/happy.png" data-mood="1" class="moodSelect" style="width:60px;">
  <img src="images/normal.png" data-mood="2" class="moodSelect" style="width:60px;">
  <img src="images/sad.png" data-mood="3" class="moodSelect" style="width:60px;">
  <br><button id="modalClose">閉じる</button>
</div>
<input type="textarea" name="commentArea" placeholder="ひとこと記録しませんか？<dr>※１４０文字以内"><br>
<imput type="submit" name="registButton" value="登録">
エラーメッセージ表示所
<p>今日のご褒美</p><br>
ご褒美の内容



</body>
</html>