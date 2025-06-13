document.addEventListener("DOMContentLoaded", function () {
  const toggle = document.getElementById("menuToggle");
  const menu = document.getElementById("hamburger");
  const overlay = document.getElementById("overlay");
  const items = document.getElementById("menuItems");

  toggle.addEventListener("click", function () {
    menu.classList.toggle("active");
    items.classList.toggle("open");
    if (overlay.style.display === "block") {
      overlay.style.display = "none";
    } else {
      overlay.style.display = "block";
    }
  });

  overlay.addEventListener("click", function () {
    menu.classList.remove("active");
    items.classList.remove("open");
    overlay.style.display = "none";
  });
});
