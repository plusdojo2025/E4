window.addEventListener("DOMContentLoaded", function () {
  const selectedMoodImg = document.getElementById("selectedMoodImg");
  const moodModal = document.getElementById("moodModal");
  const modalCloseBtn = document.getElementById("modalClose");
  const moodChoices = document.querySelectorAll(".moodChoice");
  const selectedMoodValue = document.getElementById("selectedMoodValue");

  // 画像クリックでモーダル表示
  selectedMoodImg.addEventListener("click", function () {
    moodModal.style.display = "block";
  });

  // モーダルの閉じるボタン
  modalCloseBtn.addEventListener("click", function (e) {
    e.preventDefault();
    moodModal.style.display = "none";
  });

  // 気分画像選択
  moodChoices.forEach(function (img) {
    img.addEventListener("click", function () {
      const mood = img.getAttribute("data-mood");
      selectedMoodValue.value = mood;
      selectedMoodImg.src = img.src;
      moodModal.style.display = "none";
    });
  });
});