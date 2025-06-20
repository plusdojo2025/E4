document.addEventListener("DOMContentLoaded", function () {
  const toggle = document.getElementById("menuToggle");
  const menu = document.getElementById("hamburger");
  const overlay = document.getElementById("overlay");
  const items = document.getElementById("menuItems");
  const gachaLink = document.getElementById('gachaLink');
  const gachaLabel = document.getElementById('gachaLabel');

  let moodNotRegistered = false;

  toggle.addEventListener("click", function () {
    menu.classList.toggle("active");
    items.classList.toggle("open");

    if (overlay.style.display === "block") {
      overlay.style.display = "none";
      resetGachaLink();
    } else {
      overlay.style.display = "block";

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
          resetGachaLink();
        });
    }
  });

  overlay.addEventListener("click", function () {
    menu.classList.remove("active");
    items.classList.remove("open");
    overlay.style.display = "none";
    resetGachaLink();
  });

  function disableGachaLink() {
    gachaLink.removeAttribute('href');
    gachaLink.classList.add('dimmed-link');
    gachaLabel.classList.add('dimmed-label');

    const gachaIcon = gachaLink.querySelector('.menu-icon.gacha img');
    if (gachaIcon) {
      gachaIcon.classList.add('dimmed-icon');
    }

    const wrapper = gachaLink.querySelector('.gacha-wrapper-header');

    if (!wrapper.querySelector('.warning-text')) {
      const textWrapper = document.createElement('div');
      textWrapper.className = 'gacha-texts';

      if (gachaLabel) {
        textWrapper.appendChild(gachaLabel);
      }

      const warning = document.createElement('div');
      warning.textContent = '※気分未登録';
      warning.className = 'warning-text';

      textWrapper.appendChild(warning);
      wrapper.appendChild(textWrapper);
    }
  }

  function resetGachaLink() {
    if (!gachaLink.getAttribute('href')) {
      gachaLink.setAttribute('href', `${window.location.origin}${window.location.pathname.replace(/\/[^/]*$/, '')}/GachaServlet`);
    }

    gachaLink.classList.remove('dimmed-link');
    gachaLabel.classList.remove('dimmed-label');

    const gachaIcon = gachaLink.querySelector('.menu-icon.gacha img');
    if (gachaIcon) {
      gachaIcon.classList.remove('dimmed-icon');
    }

    const wrapper = gachaLink.querySelector('.gacha-wrapper-header');
    const textWrapper = wrapper.querySelector('.gacha-texts');
    if (textWrapper) {
      const label = textWrapper.querySelector('#gachaLabel');
      if (label) {
        wrapper.appendChild(label);
      }
      textWrapper.remove();
    }
  }
});
