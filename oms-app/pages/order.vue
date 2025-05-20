<script setup>
import { ref, onMounted } from "vue";
import BaseTable from "@/components/TableComponent.vue";

// Import Modal từ Bootstrap JS
let Modal;
if (process.client) {
  Modal = await import("bootstrap").then((m) => m.Modal);
}

const modalRef = ref(null);
let modalInstance = null;

const selectedOrder = ref(null);

const view = (item) => {
  selectedOrder.value = item;
  modalInstance?.show();
};

const edit = (item) => console.log("Sửa", item);
const remove = (item) => console.log("Xoá", item);

onMounted(() => {
  if (modalRef.value && Modal) {
    modalInstance = new Modal(modalRef.value);
  }
});

// Table fields
const fields = [
  { key: "orderId", label: "Mã đơn hàng" },
  { key: "customer", label: "Tên khách hàng" },
  { key: "date", label: "Ngày đặt" },
  { key: "status", label: "Trạng thái" },
];

const fieldProducts = [
  { key: "name", label: "SẢN PHẨM" },
  { key: "quantity", label: "SỐ LƯỢNG" },
  { key: "price", label: "ĐƠN GIÁ" },
  { key: "total", label: "THÀNH TIẾN" },
];

const orders = [
  {
    orderId: "1001",
    customer: "Nguyễn Văn A",
    date: "2024-04-23",
    status: "Pending",
  },
  {
    orderId: "1002",
    customer: "Trần Thị B",
    date: "2024-04-23",
    status: "Processing",
  },
  {
    orderId: "1003",
    customer: "Lê Văn C",
    date: "2024-04-23",
    status: "Shipped",
  },
  {
    orderId: "1004",
    customer: "Phạm Minh D",
    date: "2024-04-23",
    status: "Canceled",
  },
  {
    orderId: "1005",
    customer: "Hoàng Thị E",
    date: "2024-04-23",
    status: "Processing",
  },
  {
    orderId: "1006",
    customer: "Mai Văn F",
    date: "2024-04-24",
    status: "Completed",
  },
];

const products = [
  { name: "Sản phẩm A", quantity: 2, price: "500.000đ", total: "1.000.000đ" },
  { name: "Sản phẩm B", quantity: 1, price: "300.000đ", total: "300.000đ" },
];
</script>

<template>
  <div>
    <!-- Bảng danh sách đơn -->
    <BaseTable :fields="fields" :items="orders" :pageSize="3">
      <template #actions="{ item }">
        <div class="action-links">
          <a href="#" class="action-link view" @click.prevent="view(item)">
            <i class="bi bi-eye me-1"></i> Xem
          </a>
          <a href="#" class="action-link edit" @click.prevent="edit(item)">
            <i class="bi bi-pencil-square me-1"></i> Sửa
          </a>
          <a href="#" class="action-link delete" @click.prevent="remove(item)">
            <i class="bi bi-x-circle me-1"></i> Huỷ
          </a>
        </div>
      </template>
    </BaseTable>

    <!-- Modal -->
    <div
      class="modal fade"
      tabindex="-1"
      ref="modalRef"
      aria-labelledby="exampleModalLabel"
      aria-hidden="true"
    >
      <div class="modal-dialog modal-lg">
        <div class="modal-content">
          <div class="modal-header">
            <h5 class="modal-title">Chi tiết đơn hàng</h5>
            <button
              type="button"
              class="btn-close"
              data-bs-dismiss="modal"
              aria-label="Close"
            ></button>
          </div>
          <div class="modal-body">
            <div class="container py-2">
              <div class="row">
                <div class="col-md-6">
                  <div class="mb-3">
                    <h5>THÔNG TIN ĐƠN</h5>
                    <p>
                      <strong>Mã Đơn:</strong> #{{ selectedOrder?.orderId }}
                    </p>
                    <p><strong>Ngày Tạo:</strong> {{ selectedOrder?.date }}</p>
                    <p>
                      <strong>Trạng Thái:</strong> {{ selectedOrder?.status }}
                    </p>
                  </div>
                </div>
                <div class="col-md-6">
                  <div>
                    <h5>THÔNG TIN KHÁCH HÀNG</h5>
                    <p>
                      <strong>Họ và tên: </strong>{{ selectedOrder?.customer }}
                    </p>
                    <p><strong>Địa chỉ:</strong> 123 Đường A, Quận B, TP.HCM</p>
                    <p><strong>Số điện thoại: </strong> 456 789</p>
                  </div>
                </div>
              </div>
              <hr />
              <h5>DANH SÁCH SẢN PHẨM</h5>
              <BaseTable
                :fields="fieldProducts"
                :items="products"
                :showPagination="false"
              >
              </BaseTable>
              <br />
              <div class="text-end me-3">
                <p>Phí Vận Chuyển: <strong>20.000đ</strong></p>
                <p>Giảm Giá: <strong>-50.000đ</strong></p>
                <h5><strong>Tổng Thanh Toán: 1.270.000đ</strong></h5>
              </div>
            </div>
          </div>
          <div class="modal-footer custom-footer">
            <button class="btn btn-update">
              <i class="bi bi-check-circle-fill me-2"></i> Cập nhật trạng thái
            </button>

            <button class="btn btn-print">
              <i class="bi bi-printer me-2"></i> In hoá đơn
            </button>

            <button class="btn btn-export">
              <i class="bi bi-box-arrow-up-right me-2"></i> Xuất
            </button>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>
<style scoped>
.custom-footer {
  display: flex;
  justify-content: space-between;
  gap: 1rem;
  padding: 1rem;
  background-color: #f9fafb;
  border-top: 1px solid #dee2e6;
}

.custom-footer .btn {
  flex: 1;
  padding: 0.6rem 1rem;
  font-weight: 600;
  font-size: 1rem;
  border-radius: 12px;
  transition: all 0.25s ease-in-out;
  display: flex;
  align-items: center;
  justify-content: center;
}

.btn-update {
  background: linear-gradient(135deg, #0d6efd, #4e9eff);
  color: white;
  border: none;
}

.btn-update:hover {
  background: linear-gradient(135deg, #0b5ed7, #3c8be9);
}

.btn-print {
  border: 2px solid #adb5bd;
  color: #495057;
  background-color: white;
}

.btn-print:hover {
  background-color: #f1f3f5;
  border-color: #868e96;
}

.btn-export {
  background: white;
  border: 2px solid #0d6efd;
  color: #0d6efd;
}

.btn-export:hover {
  background: #e7f1ff;
  border-color: #0b5ed7;
}

.action-links {
  display: flex;
  gap: 1rem;
  justify-content: center;
  align-items: center;
  font-size: 0.95rem;
}

.action-link {
  display: inline-flex;
  align-items: center;
  padding: 0.25rem 0.6rem;
  border-radius: 8px;
  font-weight: 500;
  text-decoration: none;
  transition: all 0.2s ease-in-out;
}

.action-link.view {
  color: #0d6efd;
  background-color: #e7f1ff;
}

.action-link.view:hover {
  background-color: #d0e5ff;
  color: #0a58ca;
}

.action-link.edit {
  color: #198754;
  background-color: #e6f4ec;
}

.action-link.edit:hover {
  background-color: #d1ead9;
  color: #146c43;
}

.action-link.delete {
  color: #dc3545;
  background-color: #fcebec;
}

.action-link.delete:hover {
  background-color: #f8d7da;
  color: #b02a37;
}
</style>
