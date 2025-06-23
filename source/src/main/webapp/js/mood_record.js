// ゼロ埋め関数（2桁にする）※※※※消さない※※※※
function zeroPad(num, length) {
	return ('0' + num).slice(-length);
}

document.addEventListener("DOMContentLoaded", function () {
	const formArea = document.getElementById("formArea");
	const dayInput = document.getElementById("dayInput");
	const today = new Date();
	const todayStr = `${today.getFullYear()}-${zeroPad(today.getMonth() + 1, 2)}-${zeroPad(today.getDate(), 2)}`;

	// 日付が一致していなければ非表示
	if (dayInput) {
		const selectedDate = dayInput.value || "";
		formArea.style.display = selectedDate === todayStr ? "block" : "none";
	}

	initModal();
	initFormSubmit();
});

//------------------------- モーダル操作 -------------------------//
function initModal() {
	const moodButton = document.getElementById("moodSelectButton");
	const modal = document.getElementById("moodSelectModal");
	const closeButton = document.getElementById("closeModal");
	const moodImages = modal?.querySelectorAll(".mood-image") || [];
	const selectedMoodImg = document.getElementById("selectedMood");
	const moodInput = document.getElementById("moodInput");

	if (!moodButton || !modal || !closeButton) return;

	modal.classList.add("hidden");

	moodButton.addEventListener("click", () => {
		modal.classList.remove("hidden");
	});

	closeButton.addEventListener("click", () => {
		modal.classList.add("hidden");
	});

	moodImages.forEach(img => {
		const moodValue = img.getAttribute("data-mood");
		img.addEventListener("click", () => {
			if (!selectedMoodImg || !moodInput) return;
			selectedMoodImg.src = img.src;
			moodInput.value = moodValue;
			modal.classList.add("hidden");
		});
	});
}

//--------------------- 登録ボタン押したとき ----------------------//
function initFormSubmit() {
	const form = document.querySelector("form");
	if (!form) return;

	form.addEventListener("submit", function (e) {
		const mood = document.getElementById("moodInput")?.value || "";
		const comment = document.querySelector("textarea")?.value || "";
		const errorMessageDiv = document.getElementById("errorMessage");
		const selectedMoodImg = document.getElementById("selectedMood");
		const moodLogList = document.getElementById("moodLogList");

		const errors = [];
		if (!mood) errors.push("気分登録は必須です");
		if (comment.length > 140) errors.push("140文字以内で入力してください");

		if (errors.length > 0) {
			e.preventDefault();
			if (errorMessageDiv) errorMessageDiv.innerHTML = errors.join("<br>");
			return;
		}

		if (!confirm("本当に登録しますか？")) {
			e.preventDefault();
			return;
		}

		// 気分ログ表示（フロント側での確認用）
		const now = new Date();
		const time = `${zeroPad(now.getHours(), 2)}:${zeroPad(now.getMinutes(), 2)}`;
		const logDiv = document.createElement("div");
		logDiv.className = "mood-log-entry";
		logDiv.innerHTML = `
			<span>${time}</span>　
			<img src="${selectedMoodImg?.src}" alt="気分" style="width: 60px; height: auto; vertical-align: middle; margin: 0 10px;">
			<p>${comment}</p>`;

		if (moodLogList) {
			moodLogList.insertBefore(logDiv, moodLogList.firstChild);
		}
	});
}
