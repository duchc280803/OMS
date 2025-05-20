export default defineNuxtRouteMiddleware((to) => {
  if (process.server) return  // tránh lỗi SSR
  const token = localStorage.getItem('auth_token')
  if (!token && to.path !== '/login') {
    return navigateTo('/login')
  }
})
