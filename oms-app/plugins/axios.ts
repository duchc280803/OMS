export default defineNuxtPlugin(() => {
  const config = useRuntimeConfig();
  return {
    provide: {
      apiFetch: (path: string, opts: any = {}) => {
        const token = localStorage.getItem("auth_token");
        return $fetch(path, {
          baseURL: config.public.apiBase,
          headers: {
            Authorization: token ? `Bearer ${token}` : "",
          },
          ...opts,
        });
      },
    },
  };
});
