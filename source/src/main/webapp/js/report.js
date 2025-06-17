document.addEventListener("DOMContentLoaded", function () {
  const canvas = document.getElementById('chart');
  const ctx = canvas.getContext('2d');

   // ここは色固定でもいい。一旦色変えてる
  const moodColors = {
    1: "#1E90FF", // 青 
    2: "#3CB371", // 緑
    3: "#FFD700", // 黄
    4: "#FFA500", // オレンジ
    5: "#FF4500"  // 赤 
  };

  const pointColors = data.map(mood => moodColors[mood] || "#888");

  const perPointWidth = 60;
  const baseWidth = 600;
  const calcWidth = Math.max(baseWidth, data.length * perPointWidth);
  const fixedHeight = 400; 

  canvas.width = calcWidth;
  canvas.height = fixedHeight;

  const config = {
    type: 'line',
    data: {
      labels: labels,
      datasets: [{
        label: '疲労度',
        data: data,
        fill: false,
        borderColor: '#94cc6b',
        backgroundColor: '#94cc6b',
        tension: 0.2,
        pointRadius: 4,
        pointBackgroundColor: pointColors,
        pointHoverBackgroundColor: pointColors
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
            precision: 0
          },
          grace: 0.5,
          title: {
            display: true,
            text: '疲労度'
          }
        },
        x: {
          reverse: true,
          offset: true,
          ticks: {
            autoSkip: false,
            maxRotation: 0,
            minRotation: 0
          },
          title: {
            display: true,
            text: '登録時間 (HH:mm)'
          }
        }
      },
      plugins: {
        tooltip: {
          enabled: true
        },
        legend: {
          display: false
        }
      }
    }
  };

  new Chart(ctx, config);
});
