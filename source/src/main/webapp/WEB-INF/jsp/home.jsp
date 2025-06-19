<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8" name="viewport"
	content="width=device-width, initial-scale=1.0">
<title>ホーム</title>

<!-- 全体共通CSS -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/common.css">
<!-- ヘッダーのCSS -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/header.css">
<!-- ホーム専用のCSS -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/home.css">

<script src="${pageContext.request.contextPath}/js/home.js"></script>
</head>
<body>

	<!-- ヘッダー -->
	<%@ include file="header.jsp"%>

<div class="homecontainer">
	<h2>${thisMonth}月</h2>
	<table>
		<thead>
			<tr>
				<th>月</th>
				<th>火</th>
				<th>水</th>
				<th>木</th>
				<th>金</th>
				<th>土</th>
				<th>日</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="week" items="${calwithmood}">
				<tr>
					<c:forEach var="day" items="${week}">
						<%
						Object[] d = (Object[]) pageContext.findAttribute("day");
						String cls = "";

						if (d[0] == "") {
							cls = "emptyday";
						} else {
							cls = "day" + d[0];
							if (d[1] != null) {
								cls += " mood" + d[1];
							}
						}
						%>
						<td class="<%=cls%>">${day[0]}</td>
					</c:forEach>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	</div>
	<script>
	"use strict";
	window.addEventListener("DOMContentLoaded", function () {
	  document.querySelectorAll("td").forEach(function(td) {
	    var match = td.className.match(/day(\d+)/);
	    if (match) {
	      var day = match[1];
	      td.style.cursor = "pointer";
	      td.addEventListener("click", function() {
	    	  // baseURLを埋め込みたいのでjspにJSを書く
	    	const baseURL = "${pageContext.request.contextPath}";
	        window.location.href = baseURL + "/MoodRegisterServlet?day=" + day;
	      });
	    }
	  });
	});
</script>
</body>
</html>