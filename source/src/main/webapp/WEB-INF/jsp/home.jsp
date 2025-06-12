<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" name="viewport"
	content="width=device-width, initial-scale=1.0">
<title>ホーム</title>
<!-- 全体共通CSS -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/common.css">
<!-- ホーム専用のCSS -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/home.css">
</head>
<body>

	<header class="header_1">
		<div class="titlelogo">
			<a href=""><img src="images/menu.png" alt="メニューバー"></a> <a
				href=""><img src="images/sigoowabiyori_title.png" alt="しごおわ日和"></a>
		</div>
	</header>
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
			<c:forEach var="week" items="${calender}">
				<tr>
					<c:forEach var="day" items="${week}">
						<td class="${day == '' ? 'empty' : day}">${day}</td>
					</c:forEach>
				</tr>
			</c:forEach>
		</tbody>
	</table>


</body>
</html>