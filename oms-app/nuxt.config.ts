// https://nuxt.com/docs/api/configuration/nuxt-config
export default defineNuxtConfig({
  compatibilityDate: '2024-11-01',
  devtools: { enabled: false },
  css: [
    "bootstrap/dist/css/bootstrap.min.css",
    'bootstrap-icons/font/bootstrap-icons.css' 
  ],
  build: {
    transpile: ['bootstrap'],
  },
  modules: ['@pinia/nuxt'],
  runtimeConfig: {
    public : {
      apiBase: process.env.API_LOCALHOST_URL || 'http://localhost:8080/'
    }
  }
})
