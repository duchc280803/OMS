export default defineNuxtRouteMiddleware((to) => {
    console.log(to.path)
    if (to.path === '/') {
        return navigateTo('/statistical', { redirectCode: 200 });
    }
});
