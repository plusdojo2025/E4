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
      
      
      //  気分画像のクリックで画像差し替え
    const moodImages = this.modal.querySelectorAll(".mood-image"); // モーダル内の気分画像
    const selectedMood = document.getElementById("selectedMood"); // 表示されているメイン画像

    moodImages.forEach(image => {
      image.addEventListener("click", () => {
        if (selectedMood) {
          selectedMood.src = image.src; // 画像を差し替える
          //this.modal.classList.add("hidden"); // モーダルを閉じる
        }
      });
    });
    
    
    }
  }
}
