<template>
  <div>
    <h1>Đơn hàng: {{ id }}</h1>
  </div>
</template>

<script setup>
const { id } = useRoute().params;
</script>
