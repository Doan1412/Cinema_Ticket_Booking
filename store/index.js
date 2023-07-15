// ....
export const actions = {
    // https://nuxtjs.org/guide/vuex-store/#the-nuxtserverinit-action
    // automatically refresh the access token on the initial request to the server, if possible
    async nuxtServerInit ({ dispatch, commit, state }) {
      const { access_token, refresh_token } = state.auth
  
      if (access_token && refresh_token) {
        try {
          // refresh the access token
          await dispatch('/api/v1/auth/refresh-token')
        } catch (e) {
          // catch any errors and automatically logout the user
          await dispatch('/api/v1/auth/logout')
        }
      }
    },
  }