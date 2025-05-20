<template>
  <div class="layout-container">
    <!-- Top Navbar -->
    <nav class="navbar navbar-expand-lg navbar-light bg-light fixed-top">
      <div class="container-fluid">
        <!-- Logo -->
        <NuxtLink to="/" class="navbar-brand">
          <img src="../assets/image/logo.png" alt="Logo" height="46" />
        </NuxtLink>

        <div class="d-flex align-items-center">
          <div class="position-relative me-3">
            <NotificationBell :count="5" message="Bạn có 5 thông báo mới!" />
          </div>
          <div
            class="position-relative me-3"
            @click="logout"
            style="cursor: pointer"
          >
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
              <NuxtLink to="/statistical" class="nav-link active">
                <i class="bi bi-speedometer2 me-2"></i>
                Dashboard
              </NuxtLink>
            </li>
            <li class="nav-item">
              <NuxtLink to="/order" class="nav-link">
                <i class="bi bi-box-seam me-2"></i>
                Order
              </NuxtLink>
            </li>
            <li class="nav-item">
              <NuxtLink to="/setting" class="nav-link">
                <i class="bi bi-gear-fill me-2"></i>
                Setting
              </NuxtLink>
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

<script setup lang="ts">
import NotificationBell from "@/components/NotificationBell.vue";
const router = useRouter();

const logout = async () => {
  localStorage.removeItem("auth_token");
  await router.push("/login");
};
</script>

<style scoped>
.layout-container {
  min-height: 100vh;
}

.content-wrapper {
  display: flex;
  margin-top: 56px;
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
}

.navbar-brand img {
  height: 36px;
}

.container-fluid {
  border-bottom: 1px solid #c8ced3;
  padding-bottom: 1px;
}

a.router-link-active.router-link-exact-active {
  text-decoration: none;
}
</style>
