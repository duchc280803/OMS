<template>
  <div class="table-wrapper">
    <div class="table-responsive">
      <table class="table align-middle text-center">
        <thead class="table-light">
          <tr>
            <th v-for="field in fields" :key="field.key">{{ field.label }}</th>
            <th v-if="$slots.actions">Hành động</th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="(item, index) in pagedItems" :key="index">
            <td v-for="field in fields" :key="field.key">
              <span
                v-if="field.key === 'status'"
                :class="['badge', statusClass(item[field.key])]"
              >
                {{ item[field.key] }}
              </span>
              <span v-else>{{ item[field.key] }}</span>
            </td>
            <td v-if="$slots.actions">
              <slot name="actions" :item="item"></slot>
            </td>
          </tr>
        </tbody>
      </table>
    </div>

    <!-- Pagination -->
    <nav
      v-if="showPagination"
      aria-label="Page navigation"
      class="d-flex justify-content-center mt-3"
    >
      <ul class="pagination mb-0">
        <li
          class="page-item"
          :class="{ disabled: currentPage === 1 }"
          @click="changePage(currentPage - 1)"
        >
          <a class="page-link" href="#" tabindex="-1" aria-disabled="true">‹</a>
        </li>

        <li
          v-for="page in totalPages"
          :key="page"
          class="page-item"
          :class="{ active: currentPage === page }"
          @click="changePage(page)"
        >
          <a class="page-link" href="#">{{ page }}</a>
        </li>

        <li
          class="page-item"
          :class="{ disabled: currentPage === totalPages }"
          @click="changePage(currentPage + 1)"
        >
          <a class="page-link" href="#">›</a>
        </li>
      </ul>
    </nav>
  </div>
</template>

<script setup>
import { ref, computed, watch } from "vue";

const props = defineProps({
  fields: {
    type: Array,
    required: true,
    validator: (value) =>
      value.every((field) => "key" in field && "label" in field),
  },
  items: {
    type: Array,
    required: true,
  },
  pageSize: {
    type: Number,
    default: 5,
  },
  showPagination: {
    type: Boolean,
    default: true,
  },
});

const currentPage = ref(1);

const totalPages = computed(() =>
  Math.ceil(props.items.length / props.pageSize)
);

const pagedItems = computed(() => {
  const start = (currentPage.value - 1) * props.pageSize;
  return props.items.slice(start, start + props.pageSize);
});

function changePage(page) {
  if (page < 1 || page > totalPages.value) return;
  currentPage.value = page;
}

// Giữ hàm statusClass như cũ
const statusClass = (status) => {
  switch (status) {
    case "Pending":
      return "bg-warning text-dark";
    case "Processing":
      return "bg-primary";
    case "Shipped":
      return "bg-info text-white";
    case "Canceled":
      return "bg-danger";
    case "Completed":
      return "bg-success";
    default:
      return "bg-secondary";
  }
};
</script>

<style scoped>
.table-wrapper {
  background: #fff;
  padding: 1rem;
  border-radius: 8px;
  box-shadow: 0 4px 12px rgba(0, 0, 0, 0.05);
}

.table thead {
  font-weight: bold;
}

.badge {
  padding: 0.5em 0.75em;
  border-radius: 0.5rem;
  font-size: 0.85rem;
  white-space: nowrap;
  user-select: none;
  pointer-events: none;
}

.pagination {
  user-select: none;
  cursor: pointer;
  gap: 0.5rem;
}

.page-item .page-link {
  border: none;
  border-radius: 8px;
  color: #0d6efd;
  background-color: #f8f9fa;
  padding: 0.5rem 0.75rem;
  transition: all 0.2s ease;
  box-shadow: 0 2px 6px rgba(0, 0, 0, 0.05);
}

.page-item .page-link:hover {
  background-color: #e2e6ea;
}

.page-item.active .page-link {
  background-color: #0d6efd;
  color: white;
  font-weight: bold;
  box-shadow: 0 0 0 2px rgba(13, 110, 253, 0.25);
}

.page-item.disabled .page-link {
  opacity: 0.5;
  cursor: not-allowed;
}
</style>
