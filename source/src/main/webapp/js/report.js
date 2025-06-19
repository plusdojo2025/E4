document.addEventListener("DOMContentLoaded", function () {
  const ctx = document.getElementById("chart").getContext("2d");

  // データとラベルは JSP から受け取り済み
  // 新しい登録が右になるように逆順を修正
  const reversedLabels = [...labels].reverse();
  const reversedData = [...data].reverse();

  const pointCount = reversedLabels.length;

  // キャンバス幅を件数に応じて設定
  const canvas = document.getElementById("chart");
  const minWidthPerPoint = 80; // 1点あたりの幅(px)
  const baseWidth = pointCount * minWidthPerPoint;
  canvas.style.width = baseWidth < 800 ? "100%" : baseWidth + "px";
  canvas.style.height = "100%";

  // 5段階に色分け（新色で統一）
  const pointColors = reversedData.map(value => {
    if (value === 1) return "#1E90FF"; // 青
    if (value === 2) return "#3CB371"; // 緑
    if (value === 3) return "#FFD700"; // 金
    if (value === 4) return "#FFA500"; // オレンジ
    if (value === 5) return "#FF4500"; // 赤
    return "#999"; // fallback
  });

  // Chart.js 設定
  new Chart(ctx, {
    type: "line",
    data: {
      labels: reversedLabels,
      datasets: [{
        label: "疲労度",
        data: reversedData,
        tension: 0.3,
        borderColor: "#8BC34A",
        backgroundColor: "#8BC34A44",
        pointBackgroundColor: pointColors,
        pointBorderColor: pointColors,
        pointRadius: 5,
        pointHoverRadius: 7
      }]
    },
    options: {
      responsive: false,
      maintainAspectRatio: false,
      scales: {
  y: {
    min: 0,
    max: 5.5,
    ticks: {
      stepSize: 1,
      callback: function(value) {
        const labels = {
          1: "BAD",
          5: "GOOD"
        };
        return labels[value] || "";
      },
      font: {
    size: 10
    }
    },
    grid: {
      color: function(context) {
        // 0 と 5.5 の線だけ透明にして非表示にする
        return (context.tick.value === 0 || context.tick.value === 5.5) ? 'rgba(0,0,0,0)' : '#ccc';
      }
    },
    title: {
      display: false
    }
  },
  x: {
	type: 'category', 
    title: {
      display: true,
      text: "登録時間 (HH:mm)",
      align: 'start',
    }
  }
},
      plugins: {
        legend: {
          display: false
        },
        tooltip: {
          callbacks: {
            label: context => `疲労度: ${context.parsed.y}`
          }
        }
      }
    }
  });
});
