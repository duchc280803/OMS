<template>
  <div class="container">
    <div class="row g-3 mb-4">
      <!-- Tổng quan -->
      <div class="col-md-3">
        <div class="card text-center shadow-sm">
          <div class="card-body">
            <h6 class="text-muted">TỔNG QUAN</h6>
            <h5 class="fw-bold">TỔNG SỐ ĐƠN HÀNG</h5>
            <h1 class="fw-bold text-primary">120</h1>
          </div>
        </div>
      </div>

      <!-- Tuần này -->
      <div class="col-md-3">
        <div class="card text-center shadow-sm">
          <div class="card-body">
            <h6 class="text-muted">TUẦN NÀY</h6>
            <h1 class="fw-bold text-dark">1,245</h1>
            <small class="text-muted">Tuần này</small>
          </div>
        </div>
      </div>

      <!-- Doanh thu dự kiến -->
      <div class="col-md-3">
        <div class="card text-center shadow-sm">
          <div class="card-body">
            <h6 class="text-muted">DOANH THU DỰ KIẾN</h6>
            <h1 class="fw-bold text-success">₫250.000.000</h1>
          </div>
        </div>
      </div>

      <!-- Tình trạng các đơn -->
      <div class="col-md-3">
        <div class="card shadow-sm">
          <div class="card-body">
            <h6 class="text-muted">TÌNH TRẠNG CÁC ĐƠN</h6>
            <ul class="list-unstyled mb-0">
              <li><span class="text-primary fw-semibold">Pending:</span> 25</li>
              <li><span class="text-info fw-semibold">Processing:</span> 30</li>
              <li><span class="text-success fw-semibold">Shipped:</span> 40</li>
              <li><span class="text-danger fw-semibold">Canceled:</span> 10</li>
            </ul>
          </div>
        </div>
      </div>
    </div>
    <div class="row g-3 mb-4">
      <div class="col-md-6">
        <div class="card order">
          <div class="card-body">
            <h5 class="fw-bold">Đơn hàng theo ngày/tuần</h5>
            <div ref="chartRef" class="w-full" style="height: 300px"></div>
          </div>
        </div>
      </div>
      <div class="col-md-6">
        <div class="card order">
          <div class="card-body">
            <h5 class="fw-bold">Đơn hàng theo trạng thái</h5>
            <div ref="pieChartRef" class="w-full" style="height: 300px"></div>
          </div>
        </div>
      </div>
    </div>
    <div class="row g-3">
      <div class="col-md-6">
        <div class="card order">
          <div class="card-body">
            <h5 class="fw-bold">Tổng sản phẩm không bán chạy</h5>
            <div
              ref="horizontalChartNotBestSellerRef"
              class="w-full"
              style="height: 250px"
            ></div>
          </div>
        </div>
      </div>
      <div class="col-md-6">
        <div class="card order">
          <div class="card-body">
            <h5 class="fw-bold">Tổng sản phẩm bán chạy</h5>
            <div
              ref="horizontalChartBestSellerRef"
              class="w-full"
              style="height: 250px"
            ></div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import * as echarts from "echarts";
import { onMounted, ref } from "vue";

const chartRef = ref(null);
const pieChartRef = ref(null);
const horizontalChartBestSellerRef = ref(null);
const horizontalChartNotBestSellerRef = ref(null);

onMounted(() => {
  const chart = echarts.init(chartRef.value);

  const option = {
    tooltip: {
      trigger: "axis",
    },
    xAxis: {
      type: "category",
      data: ["Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun"],
    },
    yAxis: {
      type: "value",
    },
    series: [
      {
        name: "Orders",
        type: "bar",
        data: [22, 24, 18, 25, 27, 28, 25],
        itemStyle: {
          color: "#4e73df",
        },
        barWidth: "50%",
      },
    ],
  };

  chart.setOption(option);

  // Pie chart
  const pieChart = echarts.init(pieChartRef.value);
  const pieOption = {
    tooltip: { trigger: "item" },
    legend: { bottom: "0%" },
    series: [
      {
        name: "Trạng thái",
        type: "pie",
        radius: "60%",
        data: [
          { value: 25, name: "Pending" },
          { value: 30, name: "Processing" },
          { value: 40, name: "Shipped" },
          { value: 10, name: "Canceled" },
        ],
        emphasis: {
          itemStyle: {
            shadowBlur: 10,
            shadowOffsetX: 0,
            shadowColor: "rgba(0, 0, 0, 0.5)",
          },
        },
      },
    ],
  };
  pieChart.setOption(pieOption);

  const horizontalChart = echarts.init(horizontalChartBestSellerRef.value);
  const horizontalOption = {
    tooltip: { trigger: "axis" },
    grid: {
      left: "3%",
      right: "4%",
      bottom: "3%",
      containLabel: true,
    },
    xAxis: {
      type: "value",
      boundaryGap: [0, 0.01],
    },
    yAxis: {
      type: "category",
      data: ["Sản phẩm A", "Sản phẩm B", "Sản phẩm C", "Sản phẩm D"],
    },
    series: [
      {
        name: "Số lượng bán",
        type: "bar",
        data: [320, 240, 180, 90],
        itemStyle: {
          color: "#2ecc71",
        },
        barWidth: "40%",
      },
    ],
  };

  horizontalChart.setOption(horizontalOption);

  const horizontalChartBestSeller = echarts.init(
    horizontalChartBestSellerRef.value
  );
  const horizontalOptionBestSeller = {
    tooltip: { trigger: "axis" },
    grid: {
      left: "3%",
      right: "4%",
      bottom: "3%",
      containLabel: true,
    },
    xAxis: {
      type: "value",
      boundaryGap: [0, 0.01],
    },
    yAxis: {
      type: "category",
      data: ["Sản phẩm A", "Sản phẩm B", "Sản phẩm C", "Sản phẩm D"],
    },
    series: [
      {
        name: "Số lượng bán",
        type: "bar",
        data: [320, 240, 180, 90],
        itemStyle: {
          color: "#2ecc71",
        },
        barWidth: "40%",
      },
    ],
  };

  horizontalChartBestSeller.setOption(horizontalOptionBestSeller);

  const horizontalChartNotBestSeller = echarts.init(
    horizontalChartNotBestSellerRef.value
  );
  const horizontalOptionNotBestSeller = {
    tooltip: { trigger: "axis" },
    grid: {
      left: "3%",
      right: "4%",
      bottom: "3%",
      containLabel: true,
    },
    xAxis: {
      type: "value",
      boundaryGap: [0, 0.01],
    },
    yAxis: {
      type: "category",
      data: ["Sản phẩm A", "Sản phẩm B", "Sản phẩm C", "Sản phẩm D"],
    },
    series: [
      {
        name: "Số lượng bán",
        type: "bar",
        data: [320, 240, 180, 90],
        itemStyle: {
          color: "#2ecc71",
        },
        barWidth: "40%",
      },
    ],
  };

  horizontalChartNotBestSeller.setOption(horizontalOptionNotBestSeller);

  // Resize responsive
  window.addEventListener("resize", () => {
    chart.resize();
    pieChart.resize();
    horizontalChart.resize();
    horizontalOptionBestSeller.resize();
  });
});
</script>

<style scoped>
.card {
  border-radius: 12px;
}

.card.text-center.shadow-sm {
  height: 157px;
}
.card.order {
  height: 305px;
}
</style>
