<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8" name="viewport"
	content="width=device-width, initial-scale=1.0">
<title>過去七日間の記録</title>

<!-- 全体共通CSS -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/common.css">
<!-- ヘッダーCSS -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/header.css">
<!-- レポートページ専用のCSS -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/report.css">

<!-- Chart.js CDN -->
<script src="https://cdn.jsdelivr.net/npm/chart.js"></script>

</head>
<body>

	<!-- ヘッダー -->
	<%@ include file="header.jsp"%>

	<main>
	
	
		<div class="report-container">
			<a href="${pageContext.request.contextPath}/HomeServlet" class="back-home-button">
          ←
        </a>
			<h2 class="section-title">過去七日間の記録</h2>

			<!-- 折れ線グラフ（スクロールラッパー） -->
			<div class="chart-area">
				<canvas id="chart"></canvas>
			</div>

			<!-- テキスト情報 -->
			<div class="report-info">
				<p>
					過去7日間の平均気分値は <span class="highlight">${fatigueLevel}</span>
					でした。
				</p>
				<p>
					最も疲れた日は <span class="highlight">${tiredDay}</span>
				</p>
				<p>
					ガチャの回数は <span class="highlight">${Gacha}</span> 回で、
				</p>
				<p>引いたご褒美は</p>
				<div class="rewards">
					<c:forEach var="reward" items="${weeklyReward}">
						<p class="highlight">${reward.gacha_item}</p>
					</c:forEach>
				</div>
				<p>でしたね！</p>
				<p>明日からも健やかに過ごしましょう！</p>
			</div>
		</div>
	</main>

	<!-- グラフ用データをJSONで渡す -->
	<script>
	const labels = [
		<c:forEach var="mood" items="${moodList}" varStatus="status">
			["<fmt:formatDate value='${mood.created_at}' pattern='yyyy-MM-dd' />", "<fmt:formatDate value='${mood.created_at}' pattern='HH:mm' />"]<c:if test="${!status.last}">,</c:if>
		</c:forEach>
	];

	const data = [
		<c:forEach var="mood" items="${moodList}" varStatus="status">
			${mood.mood}<c:if test="${!status.last}">,</c:if>
		</c:forEach>
	];
    </script>

	<!-- ページ専用JS -->
	<script src="${pageContext.request.contextPath}/js/report.js"></script>

</body>
</html>
