<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" name="viewport"
	content="width=device-width, initial-scale=1.0">
<title>今週の記録</title>
<!-- 全体共通CSS -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/common.css">
<!-- ヘッダーCSS -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/header.css">
<!-- レポートページ専用のCSS -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/report.css">
</head>
<body>

	<!-- ヘッダー -->
	<%@ include file="header.jsp"%>

	<main>
		<div class="report-container">
			<h2 class="section-title">今週の記録</h2>

			<!-- 折れ線グラフ -->
			<div id="chart" class="chart-area">
				<!-- 描画されるエリア -->

			</div>

			<!-- テキスト -->
			<div class="report-info">
				<p>
					今週の平均疲労度は <strong>${averageFatigueLevel}</strong> でした
				</p>
				<p>
					最も疲れた日は <strong>${mostFatiguedDay}</strong>
				</p>
				<p>
					ガチャは <strong>${gachaCount}</strong> 回で
				</p>
				<p>ご褒美は</p>
				<div class="rewards">
					<%-- ご褒美一覧 --%>
					<c:forEach var="reward" items="${weeklyReward}">
						<%-- Javaのリストを繰り返し表示 --%>
						<p>${reward}</p>
					</c:forEach>
				</div>
				<p>でしたね</p>
				<p>来週も健やかに過ごしましょう</p>
			</div>
		</div>
	</main>


</body>
</html>