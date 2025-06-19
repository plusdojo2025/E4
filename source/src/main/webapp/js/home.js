document.addEventListener("DOMContentLoaded", function() {
	const today = new Date();
	const date = today.getDate();

	const todayObj = document.querySelector('.day' + date);
	todayObj.setAttribute("style", "outline: 2px dotted #888;");
});
