<template>
  <div class="notification-bell position-relative" @mouseenter="showTooltip" @mouseleave="hideTooltip">
    <i class="bi bi-bell fs-5"></i>
    <span class="position-absolute top-0 start-100 translate-middle badge rounded-pill bg-danger">
      {{ count }}
    </span>
    
    <div class="notification-tooltip" :class="{ 'show': isTooltipVisible }">
      {{ message }}
    </div>
  </div>
</template>

<script setup lang="ts">
import { ref } from 'vue'

const props = defineProps<{
  count: number,
  message: string
}>()

const isTooltipVisible = ref(false)

const showTooltip = () => {
  isTooltipVisible.value = true
}

const hideTooltip = () => {
  isTooltipVisible.value = false
}
</script>

<style scoped>
.notification-bell {
  cursor: pointer;
}

.notification-tooltip {
  position: absolute;
  bottom: -35px;
  left: 50%;
  transform: translateX(-50%);
  background: rgba(0, 0, 0, 0.8);
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
  content: '';
  position: absolute;
  top: -5px;
  left: 50%;
  transform: translateX(-50%);
  border-left: 5px solid transparent;
  border-right: 5px solid transparent;
  border-bottom: 5px solid rgba(0, 0, 0, 0.8);
}

.notification-tooltip.show {
  opacity: 1;
  visibility: visible;
}
</style>
