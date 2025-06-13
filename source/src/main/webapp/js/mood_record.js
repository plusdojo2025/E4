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
          this.modal.classList.add("hidden");
        }
      });
    });
  }
}
}