<template>
  <div
    class="notification-bell position-relative"
    @mouseenter="showTooltip"
    @mouseleave="hideTooltip"
  >
    <i class="bi bi-bell fs-5"></i>
    <span
      class="position-absolute top-0 start-100 translate-middle badge rounded-pill bg-danger"
    >
      {{ count }}
    </span>

    <div class="notification-tooltip" :class="{ show: isTooltipVisible }">
      <div class="notification-card">
        <h4 class="notification-title">Thông báo</h4>
        <ul class="notification-list">
          <li class="notification-item">
            <span class="dot"></span>
            <div class="notification-content">
              <strong>Đơn hàng mới đã được tạo</strong>
              <p>Đơn hàng #1234 vừa được tạo.</p>
            </div>
            <span class="time">2 phút trước</span>
          </li>
          <li class="notification-item">
            <span class="dot"></span>
            <div class="notification-content">
              <strong>Bài viết của bạn đã được phê duyệt</strong>
              <p>Bài viết "Chủ đề blog mới n...</p>
            </div>
            <span class="time">1 giờ trước</span>
          </li>
          <li class="notification-item">
            <span class="dot"></span>
            <div class="notification-content">
              <strong>Mật khẩu của bạn đã thay đổi</strong>
              <p>Mật khẩu của bạn đã được...</p>
            </div>
            <span class="time">3 giờ trước</span>
          </li>
          <li class="notification-item">
            <span class="dot"></span>
            <div class="notification-content">
              <strong>Ứng dụng đã được cập nhật</strong>
              <p>Phiên bản mới của ứng dụng đ...</p>
            </div>
            <span class="time">Hôm qua</span>
          </li>
        </ul>
      </div>
    </div>
  </div>
</template>

<script setup lang="ts">
import { ref, defineComponent } from "vue";

const props = defineProps<{
  count: number;
  message: string;
}>();

const isTooltipVisible = ref(false);

const showTooltip = () => {
  isTooltipVisible.value = true;
};

const hideTooltip = () => {
  isTooltipVisible.value = false;
};

defineComponent({
  name: 'NotificationBell'
});
</script>

<style scoped>
.notification-bell {
  cursor: pointer;
}

.notification-tooltip {
  position: absolute;
  right: -270px;
  transform: translateX(-50%);
  color: white;
  padding: 5px 10px;
  border-radius: 4px;
  font-size: 0.875rem;
  white-space: nowrap;
  opacity: 0;
  visibility: hidden;
  transition: opacity 0.2s ease, visibility 0.2s ease;
}

.notification-tooltip::before {
  content: "";
  position: absolute;
  top: -5px;
  left: 83%;
  transform: translateX(-50%);
  border-left: 5px solid transparent;
  border-right: 5px solid transparent;
  border-bottom: 5px solid rgba(0, 0, 0, 0.8);
}

.notification-tooltip.show {
  opacity: 1;
  visibility: visible;
}

.card-title {
  border-bottom: 2px solid #007bff;
  padding-bottom: 5px;
}
.notification-card {
  width: 400px;
  background: #fff;
  border-radius: 12px;
  box-shadow: 0 4px 16px rgba(0, 0, 0, 0.06);
  font-family: Arial, sans-serif;
  padding: 20px;
}

.notification-title {
  font-size: 18px;
  font-weight: 600;
  border-bottom: 1px solid #eee;
  padding-bottom: 8px;
}

.notification-list {
  list-style: none;
  padding: 0;
  margin: 0;
}

.notification-item {
  display: flex;
  align-items: flex-start;
  padding: 12px 0;
  border-bottom: 1px solid #f0f0f0;
  position: relative;
}

.notification-item:last-child {
  border-bottom: none;
}

.dot {
  width: 12px;
  height: 12px;
  background-color: #2563eb;
  border-radius: 50%;
  margin-right: 12px;
  margin-top: 6px;
  flex-shrink: 0;
}

.notification-content {
  flex: 1;
}

.notification-content strong {
  display: block;
  font-weight: 600;
  margin-bottom: 4px;
  color: #111827;
}

.notification-content p {
  font-size: 14px;
  color: #6b7280;
  margin: 0;
}

.time {
  font-size: 12px;
  color: #9ca3af;
  white-space: nowrap;
  margin-left: 12px;
}
</style>
