//すべてのHTMLが読み込まれた後に、処理を実行
window.addEventListener("DOMContentLoaded", function () {
  const selectedMoodImg = document.getElementById("selectedMoodImg");//押したらモーダル開く
  const moodModal = document.getElementById("moodModal");//モーダル本体
  const modalCloseBtn = document.getElementById("modalClose");//閉じるボタン
  const moodChoices = document.querySelectorAll(".moodChoice");//気分画像
  const selectedMoodValue = document.getElementById("selectedMoodValue");//選択された気分画像

  selectedMoodImg.addEventListener("click", function () {
    moodModal.style.display = "flex";
  });

  modalCloseBtn.addEventListener("click", function (e) {
    e.preventDefault();
    moodModal.style.display = "none";
  });

   moodChoices.forEach(function (img) {
    img.addEventListener("click", function () {
      const mood = img.getAttribute("data-mood");
      selectedMoodValue.value = mood;
      selectedMoodImg.src = "images/mood_" + mood + ".png";
      moodModal.style.display = "none";
    });
  });
});