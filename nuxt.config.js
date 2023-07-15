export default {
  // Global page headers: https://go.nuxtjs.dev/config-head
  head: {
    title: 'Ticket_Booking',
    htmlAttrs: {
      lang: 'en',
    },
    meta: [
      { charset: 'utf-8' },
      { name: 'viewport', content: 'width=device-width, initial-scale=1' },
      { hid: 'description', name: 'description', content: '' },
      { name: 'format-detection', content: 'telephone=no' },
    ],
    link: [{ rel: 'icon', type: 'image/x-icon', href: '/favicon.ico' }],
  },

  // Global CSS: https://go.nuxtjs.dev/config-css
  css: [],

  // Plugins to run before rendering page: https://go.nuxtjs.dev/config-plugins
  plugins: ['~/plugins/local-storage', '~/plugins/axios'],

  // Auto import components: https://go.nuxtjs.dev/config-components
  components: true,

  // Modules for dev and build (recommended): https://go.nuxtjs.dev/config-modules
  buildModules: [
    // https://go.nuxtjs.dev/typescript
    '@nuxt/typescript-build',
    // https://go.nuxtjs.dev/tailwindcss
    '@nuxtjs/tailwindcss',
  ],

  // Modules: https://go.nuxtjs.dev/config-modules
  modules: [
    // https://go.nuxtjs.dev/axios
    '@nuxtjs/axios',
    '@nuxtjs/tailwindcss',
    '@nuxtjs/auth-next',
  ],

  // Axios module configuration: https://go.nuxtjs.dev/config-axios
  axios: {
    // Workaround to avoid enforcing hard-coded localhost:3000: https://github.com/nuxt-community/axios-module/issues/308
    baseURL: '/',
    headers: {
      'Content-Type': 'application/json',
    },
  },
  store: true,

  // Build Configuration: https://go.nuxtjs.dev/config-build
  build: {},
  // router: {
  //   middleware: ['auth'],
  // },
  // auth: {
  //   strategies: {
  //     local: {
  //       scheme: 'refresh',
  //       token: {
  //         property: 'access_token',
  //         maxAge: 86400000,
  //         global: true,
  //         // type: 'Bearer'
  //       },
  //       refreshToken: {
  //         property: 'refresh_token',
  //         data: 'refresh_token',
  //         maxAge: 604800000,
  //       },
  //       endpoints: {
  //         refresh: {
  //           url: 'http://localhost:8080/api/v1/auth/refresh-token',
  //           method: 'post',
  //         },
  //         register: {
  //           url: 'http://localhost:8080/api/v1/auth/register',
  //           method: 'post',
  //         },
  //         login: {
  //           url: 'http://localhost:8080/api/v1/auth/authenticate',
  //           method: 'post',
  //         },
  //         logout: {
  //           url: 'http://localhost:8080/api/auth/logout',
  //           method: 'post',
  //         },
  //         user: { url: '/api/v1/auth/user', method: 'get', propertyName: 'user' },
  //         //user: { url: '/api/v1/auth/user', method: 'get', propertyName: 'user' }
  //       },
  //       tokenType: 'Bearer',
  //     },
  //   },
  //   redirect: {
  //     login: '/login',
  //     logout: '/login',
  //     home: '/',
  //   },
  //   watchLoggedIn: true,
  //   rewriteRedirect: true,
  // },
}
