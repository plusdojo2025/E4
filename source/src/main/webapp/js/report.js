document.addEventListener("DOMContentLoaded", function () {
  const ctx = document.getElementById("chart").getContext("2d");

  // データとラベルはJSPから受け取り済み
  const pointCount = labels.length;

  // キャンバス幅を件数に応じて設定
  const canvas = document.getElementById("chart");
  const minWidthPerPoint = 80; // 1点あたりの幅(px)
  const baseWidth = pointCount * minWidthPerPoint;
  canvas.style.width = (baseWidth < 800 ? "100%" : baseWidth + "px");
  canvas.style.height = "100%";

  // 修正した
  const pointColors = data.map(value => {
    if (value === 1) return "#40C4FF"; // 青
    if (value === 2) return "#4CAF50"; // 緑
    if (value === 3 || value === 4) return "#FFEB3B"; // 黄
    if (value === 5) return "#FF5722"; // 赤
    return "#999"; // fallback
  });

  // グラフ描画
  new Chart(ctx, {
    type: "line",
    data: {
      labels: labels,
      datasets: [{
        label: "疲労度",
        data: data,
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
            stepSize: 1
          },
          title: {
            display: true,
            text: "疲労度"
          }
        },
        x: {
          title: {
            display: true,
            text: "登録時間 (HH:mm)"
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
