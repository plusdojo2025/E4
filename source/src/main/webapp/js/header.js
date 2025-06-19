document.addEventListener("DOMContentLoaded", function () {
  const toggle = document.getElementById("menuToggle");
  const menu = document.getElementById("hamburger");
  const overlay = document.getElementById("overlay");
  const items = document.getElementById("menuItems");
  const gachaLink = document.getElementById('gachaLink');
  const gachaLabel = document.getElementById('gachaLabel');

  // フラグはページロード時はfalse、モーダル開いた時にチェックする形にする
  let moodNotRegistered = false;

  // メニュー開閉処理
  toggle.addEventListener("click", function () {
    menu.classList.toggle("active");
    items.classList.toggle("open");

    if (overlay.style.display === "block") {
      overlay.style.display = "none";

      // モーダル閉じた時は必ずリセット
      resetGachaLink();
    } else {
      overlay.style.display = "block";

      // モーダル開いた瞬間にサーバーに状態問い合わせ
      fetch('MoodCheckServlet', {
        method: 'GET',
        credentials: 'same-origin'
      })
        .then(response => response.json())
        .then(data => {
          moodNotRegistered = data.moodNotRegistered;

          if (moodNotRegistered) {
            disableGachaLink();
          } else {
            resetGachaLink();
          }
        })
        .catch(error => {
          console.error('Mood check failed:', error);
          // 念のためリセット
          resetGachaLink();
        });
    }
  });

  // オーバーレイクリック時にもモーダル閉じる
  overlay.addEventListener("click", function () {
    menu.classList.remove("active");
    items.classList.remove("open");
    overlay.style.display = "none";

    resetGachaLink();
  });

  // 薄暗くして警告表示を追加する関数
  function disableGachaLink() {
    gachaLink.removeAttribute('href');
    gachaLink.classList.add('dimmed');
    gachaLabel.classList.add('dimmed-label');

    if (!gachaLink.querySelector('.warning-text')) {
      const warning = document.createElement('div');
      warning.textContent = '※先に気分の登録を行ってください';
      warning.className = 'warning-text';
      gachaLink.querySelector('.gacha-wrapper').appendChild(warning);
    }
  }

  // 元に戻す関数（薄暗くも警告も消す）
  function resetGachaLink() {
    // hrefが無ければ復元（URLは環境に合わせて変更してください）
    if (!gachaLink.getAttribute('href')) {
      gachaLink.setAttribute('href', `${window.location.origin}${window.location.pathname.replace(/\/[^/]*$/, '')}/GachaServlet`);
    }

    gachaLink.classList.remove('dimmed');
    gachaLabel.classList.remove('dimmed-label');

    const warning = gachaLink.querySelector('.warning-text');
    if (warning) warning.remove();
  }
});
