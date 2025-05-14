<template>
  <div class="layout-container">
    <!-- Top Navbar -->
    <nav class="navbar navbar-expand-lg navbar-light bg-light fixed-top">
      <div class="container-fluid">
        <!-- Logo -->
        <a class="navbar-brand" href="#">
          <img src="../assets/image/logo.png" alt="Logo" height="46" />
        </a>

        <div class="d-flex align-items-center">
          <div class="position-relative me-3">
            <NotificationBell :count="5" message="Bạn có 5 thông báo mới!" />
          </div>
          <div class="position-relative me-3">
            <i class="bi bi-box-arrow-right fs-5"></i>
          </div>
        </div>
      </div>
    </nav>
    <div class="content-wrapper">
      <!-- Sidebar -->
      <div class="sidebar bg-light">
        <div class="sidebar-sticky pt-3">
          <ul class="nav flex-column">
            <li class="nav-item">
              <a class="nav-link active" href="#">
                <i class="bi bi-speedometer2 me-2"></i>
                Dashboard
              </a>
            </li>
            <li class="nav-item">
              <a class="nav-link" href="#">
                <i class="bi-box-seam me-2"></i>
                Order
              </a>
            </li>
            <li class="nav-item">
              <a class="nav-link" href="#">
                <i class="bi bi-gear-fill me-2"></i>
                Setting
              </a>
            </li>
          </ul>
        </div>
      </div>
      <!-- Main Content Area -->
      <main class="main-content">
        <slot></slot>
      </main>
    </div>
  </div>
</template>

<script lang="ts">
import NotificationBell from './NotificationBell.vue';

export default {
  name: "HeaderCommont",
  components: {
    NotificationBell,
  },
  data() {
    return {
      cartCount: 0,
    };
  },
};
</script>

<style scoped>
.layout-container {
  min-height: 100vh;
}

.content-wrapper {
  display: flex;
  margin-top: 56px; /* Height of navbar */
}

.sidebar {
  width: 212px;
  min-height: calc(100vh - 56px);
  position: fixed;
  left: 0;
  overflow-y: auto;
  transition: all 0.3s;
  box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
  border-right: 1px solid #c8ced3;
}

.main-content {
  flex-grow: 1;
  margin-left: 250px;
  padding: 20px;
}

/* Responsive Design */
@media (max-width: 991.98px) {
  .sidebar {
    width: 200px;
  }

  .main-content {
    margin-left: 200px;
  }
}

@media (max-width: 767.98px) {
  .sidebar {
    width: 0;
    transform: translateX(-100%);
  }

  .main-content {
    margin-left: 0;
  }

  .sidebar.show {
    width: 250px;
    transform: translateX(0);
  }
}
.navbar-brand {
  height: 100%;
  width: 200px;
  border-right: 1px solid #c8ced3;
  img {
    height: 36px;
  }
}
.container-fluid {
  border-bottom: 1px solid #c8ced3;
  padding-bottom: 1px;
}
</style>
