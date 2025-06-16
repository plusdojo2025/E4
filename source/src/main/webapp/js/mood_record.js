export class ModalController {
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
        if (selectedMoodImg && moodInput) {//選択がnull出ないことを確認
          selectedMoodImg.src = img.src;//選択された気分画像を、 mood_new.pngにコピー
          moodInput.value = moodValue;//気分番号をhidden inputにセット
          this.modal.classList.add("hidden");//モーダル閉じる
        }
      });
    });
   }
    
    //const form = document.querySelector("form");//<form>取得
    //const moodLogList = document.getElementById("moodLogList");//ログ追加場所取得


//form.addEventListener("submit", (event) => {//登録ボタンが押された場合
  
  // 現在時刻を取得 (HH:mm形式)
  //時刻の表示方法をそろえるための関数
 // function zeroPad(num, length) {
 // return ('0'.repeat(length) + num).slice(-length);
//}

  //const now = new Date();//現在時刻取得
 // const hh = zeroPad(now.getHours(), 2);//時
 // const mm = zeroPad(now.getMinutes(), 2);//分
  //const currentTime = `${hh}:${mm}`;//最終的な表示方法

 // const moodSrc = selectedMoodImg.src;//選択された気分画像

  // ログに追加するli要素を作成
 // const li = document.createElement("li");//新しく追加するための<li>作成
  //時間と画像を入れる
  //li.innerHTML = `
    //<span class="recordTimelist">${currentTime}</span>
    //<img src="${moodSrc}" alt="気分" style="width: 50px; vertical-align: middle; margin-left: 10px;">
//  `;
////moodLogListに追加
  //moodLogList.appendChild(li);
//});

//新しい気分が上に追加
//request.setAttribute("moodList", moodList);


//気分とコメントが未入力だった場合
document.querySelector("form").addEventListener("submit", function(e) {
    const mood = document.getElementById("moodInput").value;
    const comment = document.querySelector("textarea").value;
    const errorMessageDiv = document.getElementById("errorMessage");
    
     errorMessageDiv.textContent = ""; // 前のエラーを消す

     // 気分が選ばれていない
      if (!mood) {
        errorMessageDiv.textContent = "気分登録は必須です";
        e.preventDefault(); // フォーム送信キャンセル
        return;
      }

      // コメントが140文字を超えている
      if (comment.length > 140) {
        errorMessageDiv.textContent = "140文字以内で入力してください。";
        e.preventDefault(); // フォーム送信キャンセル
        return;
      }
      // ログ一覧の先頭に追加（上に表示）
      moodLogList.insertBefore(logDiv, moodLogList.firstChild);
  });


	
    
  }
}
