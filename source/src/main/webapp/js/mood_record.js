// ゼロ埋め関数（2桁にする）※※※※消さない※※※※
function zeroPad(num, length) {
	return ('0' + num).slice(-length);
}

document.addEventListener("DOMContentLoaded", function () {
	var formArea = document.getElementById("formArea");
	var dayInput = document.getElementById("dayInput");
	var today = new Date();
	var todayStr = today.getFullYear() + '-' + zeroPad(today.getMonth() + 1, 2) + '-' + zeroPad(today.getDate(), 2);

	if (dayInput) {
		var selectedDate = dayInput.value || "";
		formArea.style.display = (selectedDate === todayStr) ? "block" : "none";
	}

	initModal();
	initFormSubmit();
});

//------------------------- モーダル操作 -------------------------//
function initModal() {
	var moodButton = document.getElementById("moodSelectButton");
	var modal = document.getElementById("moodSelectModal");
	var closeButton = document.getElementById("closeModal");
	var selectedMoodImg = document.getElementById("selectedMood");
	var moodInput = document.getElementById("moodInput");

	if (!moodButton || !modal || !closeButton) return;

	if (modal.className.indexOf("hidden") === -1) {
		modal.className += " hidden";
	}

	moodButton.addEventListener("click", function () {
		modal.className = modal.className.replace("hidden", "").trim();
	});

	closeButton.addEventListener("click", function () {
		if (modal.className.indexOf("hidden") === -1) {
			modal.className += " hidden";
		}
	});

	var moodImages = modal.getElementsByClassName("mood-image");
	for (var i = 0; i < moodImages.length; i++) {
		(function (img) {
			var moodValue = img.getAttribute("data-mood");
			img.addEventListener("click", function () {
				if (!selectedMoodImg || !moodInput) return;
				selectedMoodImg.src = img.src;
				moodInput.value = moodValue;
				if (modal.className.indexOf("hidden") === -1) {
					modal.className += " hidden";
				}
			});
		})(moodImages[i]);
	}
}

//--------------------- 登録ボタン押したとき ----------------------//
function initFormSubmit() {
	var form = document.querySelector("form");
	if (!form) return;

	form.addEventListener("submit", function (e) {
		var moodInput = document.getElementById("moodInput");
		var selectedMoodImg = document.getElementById("selectedMood");
		var commentArea = document.querySelector("textarea");
		var errorMessageDiv = document.getElementById("errorMessage");
		var moodLogList = document.getElementById("moodLogList");

		var mood = moodInput ? moodInput.value : "";
		var comment = commentArea ? commentArea.value : "";
		var errors = [];

		if (!mood) errors.push("気分登録は必須です");
		if (comment.length > 140) errors.push("140文字以内で入力してください");

		if (errors.length > 0) {
			e.preventDefault();
			if (errorMessageDiv) {
				errorMessageDiv.innerHTML = errors.join("<br>");
			}
			return;
		}

		if (!confirm("本当に登録しますか？")) {
			e.preventDefault();
			return;
		}

		var now = new Date();
		var time = zeroPad(now.getHours(), 2) + ":" + zeroPad(now.getMinutes(), 2);

		var logDiv = document.createElement("div");
		logDiv.className = "mood-log-entry";
		logDiv.innerHTML =
			'<span>' + time + '</span>　' +
			'<img src="' + (selectedMoodImg ? selectedMoodImg.src : '') + '" alt="気分" style="width: 60px; height: auto; vertical-align: middle; margin: 0 10px;">' +
			'<p>' + comment + '</p>';

		if (moodLogList) {
			moodLogList.insertBefore(logDiv, moodLogList.firstChild);
		}
	});
}
