//日付選択に応じた画面表示切り替え
// ゼロ埋め関数（2桁にする）※※※※消さない※※※※
function zeroPad(num, length) {
  return ('0'.repeat(length) + num).slice(-length);
}

document.addEventListener("DOMContentLoaded", function () {
  const formArea = document.getElementById("formArea");//可変の範囲
  const dayInput = document.getElementById("dayInput");//選択された日付hidden
  const today = new Date();//今日の日時

  const yyyy = today.getFullYear();//西暦
  const mm = zeroPad(today.getMonth() + 1, 2); // 月（2桁）
  const dd = zeroPad(today.getDate(), 2);      // 日（2桁）
  const todayStr = `${yyyy}-${mm}-${dd}`;//「今日」の日付作成
  
  if (dayInput) {//日が選択されてる時だけ処理
    const selectedDate = dayInput.value;//サーバーから選択された日を取得

    if (selectedDate !== todayStr) {
      if (formArea) formArea.style.display = "none";  // 今日じゃなければ非表示
    } else {
      if (formArea) formArea.style.display = "block"; // 今日なら表示
    }
  }
 new ModalController();
});

//モーダル操作
class ModalController {
  constructor() {//ｊｓｐから取得
    this.moodButton = document.getElementById("moodSelectButton");//モーダル開く画像ボタン
    this.modal = document.getElementById("moodSelectModal");//モーダル本体
    this.closeButton = document.getElementById("closeModal");//閉じるボタン
   

	if (this.modal) {
            this.modal.classList.add("hidden");
     }
        
    this.setupEvents();
  }

  setupEvents() {
    if (this.moodButton && this.modal && this.closeButton) {//それぞれのボタンがクリックされた時の処理
    
        this.moodButton.addEventListener("click", () => {//moodSelectButtonクリック
        this.modal.classList.remove("hidden");//hidden(非表示)を外して表示
      });
		// モーダルを閉じる処理（✕ボタン）
        this.closeButton.addEventListener("click", () => {//×ボタンを押したとき
        this.modal.classList.add("hidden");//hidden（非表示）を追加して非表示に
      });
      
      
 // 気分画像のクリックで画像切り替え＆input反映
    const moodImages = this.modal.querySelectorAll(".mood-image");//気分画像5つ取得
    const selectedMoodImg = document.getElementById("selectedMood");//選択された気分画像取得
    const moodInput = document.getElementById("moodInput");//気分を1～5でServletに送る

//各気分画像の処理
    moodImages.forEach((img) => {
      const moodValue = img.dataset.mood;//dataset.moodに画像番号を入れる
      
      //クリックされた時の処理
      img.addEventListener("click", () => {
        if (selectedMoodImg && moodInput) {//選択がnullでないことを確認
          selectedMoodImg.src = img.src;//選択された気分画像を、 mood_new.pngにコピー
          moodInput.value = moodValue;//気分番号をhidden inputにセット
          this.modal.classList.add("hidden");//モーダル閉じる
        }
      });
    });
   }
   }
   }
    
 document.querySelector("form").addEventListener("submit", function(e) {
  // 確認ダイアログを追加
  if (!confirm("本当に登録しますか？")) {
    e.preventDefault();  // キャンセルなら送信中止
    return;
  }

//気分とコメントが未入力だった場合

    const mood = document.getElementById("moodInput").value;
    const comment = document.querySelector("textarea").value;
    const errorMessageDiv = document.getElementById("errorMessage");
    
     const selectedMoodImg = document.getElementById("selectedMood");
      const moodLogList = document.getElementById("moodLogList");
      
     errorMessageDiv.textContent = ""; // 前のエラーを消す

     // 気分が選ばれていない
//      if (!mood) {
//        errorMessageDiv.textContent = "気分登録は必須です";
//        e.preventDefault(); // フォーム送信キャンセル
//        return;
//      }

      // コメントが140文字を超えている
//      if (comment.length > 140) {
//        errorMessageDiv.textContent = "140文字以内で入力してください。";
//        e.preventDefault(); // フォーム送信キャンセル
//        return;
//      }
      
      const errors = [];

if (!mood) {
  errors.push("気分登録は必須です");
}

if (comment.length > 140) {
  errors.push("140文字以内で入力してください。");
}

if (errors.length > 0) {
  errorMessageDiv.innerHTML = errors.join("<br>"); // 改行して複数表示
  e.preventDefault(); // 送信キャンセル
  return;
}
      
      // ▼ログをその場で画面上に追加（上に表示）

      // 現在時刻をHH:mmで取得
      const now = new Date();
      const hh = zeroPad(now.getHours(), 2);//時
	  const mm = zeroPad(now.getMinutes(), 2);//分
      const time = `${hh}:${mm}`;//時と分結合

      // ログ表示用の要素作成
      const logDiv = document.createElement("div");//divクラス作成
      logDiv.classList.add("mood-log-entry"); // CSS調整用クラス名
      //<div>の中身HTML
      logDiv.innerHTML = `
        <span>${time}</span>　
        <img src="${selectedMoodImg.src}" alt="気分" style="width: 60px; height: auto; vertical-align: middle; margin: 0 10px;">
        <p>${comment}</p>
      `;

      // 作ったHTMLをmoodLogListの先頭に追加（新しい順）
      if (moodLogList) {
        moodLogList.insertBefore(logDiv, moodLogList.firstChild);
      }
    });