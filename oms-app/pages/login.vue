<template>
  <div class="container">
    <div class="row justify-content-center align-items-center min-vh-100">
      <div class="col-md-6 col-lg-4">
        <div class="card shadow-sm">
          <div class="card-body p-4">
            <h2 class="text-center mb-4">Đăng nhập</h2>

            <form @submit.prevent="handleSubmit">
              <div class="mb-3">
                <label for="email" class="form-label">Username</label>
                <input
                  type="username"
                  class="form-control"
                  :class="{ 'is-invalid': errors.username }"
                  id="username"
                  v-model="form.username"
                  @blur="validateEmail"
                />
                <div class="invalid-feedback" v-if="errors.username">
                  {{ errors.username }}
                </div>
              </div>

              <div class="mb-3">
                <label for="password" class="form-label">Password</label>
                <input
                  type="password"
                  class="form-control"
                  :class="{ 'is-invalid': errors.password }"
                  id="password"
                  v-model="form.password"
                  @blur="validatePassword"
                />
                <div class="invalid-feedback" v-if="errors.password">
                  {{ errors.password }}
                </div>
              </div>

              <button
                type="submit"
                class="btn btn-primary w-100"
                :disabled="isSubmitting"
              >
                <span
                  v-if="isSubmitting"
                  class="spinner-border spinner-border-sm me-2"
                  role="status"
                ></span>
                {{ isSubmitting ? "Đang xử lý..." : "Đăng nhập" }}
              </button>
            </form>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref, reactive } from "vue";
const config = useRuntimeConfig()
const router = useRouter()

definePageMeta({
  layout: "auth",
  middleware: ['redirect-if-auth']
});

const form = reactive({
  username: "",
  password: "",
});

const errors = reactive({
  username: "",
  password: "",
});

const isSubmitting = ref(false);

const validateUserName = () => {
  if (!form.password) {
    errors.username = "Vui lòng nhập username";
  } else {
    errors.username = "";
  }
};

const validatePassword = () => {
  if (!form.password) {
    errors.password = "Vui lòng nhập mật khẩu";
  } else if (form.password.length < 6) {
    errors.password = "Mật khẩu phải có ít nhất 6 ký tự";
  } else {
    errors.password = "";
  }
};

const handleSubmit = async () => {
  validateUserName();
  validatePassword();

  if (errors.username || errors.password) {
    return;
  }

  try {
    isSubmitting.value = true;
    const { data, error: fetchError } = await useFetch("/auth/login", {
      baseURL: config.public.apiBase,
      method: "POST",
      body: form,
    });

    const token = data.value.token;
    localStorage.setItem("auth_token", token);
    await router.push('/statistical')
  } catch (error) {
    console.error("Login error:", error);
  } finally {
    isSubmitting.value = false;
  }
};
</script>

<style scoped>
.card {
  border: 1px black solid;
  border-radius: 10px;
}

.form-control:focus {
  box-shadow: none;
  border-color: #0d6efd;
}

.btn-primary {
  padding: 0.6rem;
  font-weight: 500;
}
</style>
