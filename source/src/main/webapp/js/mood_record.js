window.addEventListener("DOMContentLoaded", function () {
  const selectedMoodImg = document.getElementById("selectedMoodImg");
  const moodModal = document.getElementById("moodModal");
  const modalCloseBtn = document.getElementById("modalClose");
  const moodChoices = document.querySelectorAll(".moodChoice");
  const selectedMoodValue = document.getElementById("selectedMoodValue");

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