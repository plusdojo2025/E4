//----------------日付選択に応じた画面表示切り替え--------------//
// ゼロ埋め関数（2桁にする）※※※※消さない※※※※
function zeroPad(num, length) {
  return ('0' + num).slice(-length);
}

document.addEventListener("DOMContentLoaded", function () {
  var formArea = document.getElementById("formArea");
  var dayInput = document.getElementById("dayInput");
  var today = new Date();

  var yyyy = today.getFullYear();
  var mm = zeroPad(today.getMonth() + 1, 2);
  var dd = zeroPad(today.getDate(), 2);
  var todayStr = yyyy + "-" + mm + "-" + dd;

  if (dayInput) {
    var selectedDate = dayInput.value;
    if (selectedDate !== todayStr) {
      if (formArea) formArea.style.display = "none";
    } else {
      if (formArea) formArea.style.display = "block";
    }
  }

  new ModalController();
});

//-------------------------モーダル操作-------------------------//
function ModalController() {
  this.moodButton = document.getElementById("moodSelectButton");
  this.modal = document.getElementById("moodSelectModal");
  this.closeButton = document.getElementById("closeModal");

  if (this.modal) {
    this.modal.className += " hidden";
  }

  this.setupEvents();
}

ModalController.prototype.setupEvents = function () {
  var self = this;

  if (self.moodButton && self.modal && self.closeButton) {
    self.moodButton.addEventListener("click", function () {
      self.modal.className = self.modal.className.replace("hidden", "").trim();
    });

    self.closeButton.addEventListener("click", function () {
      if (self.modal.className.indexOf("hidden") === -1) {
        self.modal.className += " hidden";
      }
    });

    var moodImages = self.modal.querySelectorAll(".mood-image");
    var selectedMoodImg = document.getElementById("selectedMood");
    var moodInput = document.getElementById("moodInput");

    for (var i = 0; i < moodImages.length; i++) {
      (function (img) {
        var moodValue = img.getAttribute("data-mood");

        img.addEventListener("click", function () {
          if (selectedMoodImg && moodInput) {
            selectedMoodImg.src = img.src;
            moodInput.value = moodValue;
            if (self.modal.className.indexOf("hidden") === -1) {
              self.modal.className += " hidden";
            }
          }
        });
      })(moodImages[i]);
    }
  }
};

//---------------------登録ボタン押したとき----------------------//
document.querySelector("form").addEventListener("submit", function (e) {
  if (!confirm("本当に登録しますか？")) {
    e.preventDefault();
    return;
  }

  var mood = document.getElementById("moodInput").value;
  var comment = document.querySelector("textarea").value;
  var errorMessageDiv = document.getElementById("errorMessage");
  var selectedMoodImg = document.getElementById("selectedMood");
  var moodLogList = document.getElementById("moodLogList");

  errorMessageDiv.textContent = "";

  var errors = [];

  if (!mood) {
    errors.push("気分登録は必須です");
  }
  if (comment.length > 140) {
    errors.push("140文字以内で入力してください。");
  }
  if (errors.length > 0) {
    errorMessageDiv.innerHTML = errors.join("<br>");
    e.preventDefault();
    return;
  }

  var now = new Date();
  var hh = zeroPad(now.getHours(), 2);
  var mm = zeroPad(now.getMinutes(), 2);
  var time = hh + ":" + mm;

  var logDiv = document.createElement("div");
  logDiv.className = "mood-log-entry";
  logDiv.innerHTML =
    '<span>' + time + '</span>　' +
    '<img src="' + selectedMoodImg.src + '" alt="気分" style="width: 60px; height: auto; vertical-align: middle; margin: 0 10px;">' +
    '<p>' + comment + '</p>';

  if (moodLogList) {
    moodLogList.insertBefore(logDiv, moodLogList.firstChild);
  }
});
