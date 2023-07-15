// reusable aliases for mutations
export const AUTH_MUTATIONS = {
    SET_USER: 'SET_USER',
    SET_PAYLOAD: 'SET_PAYLOAD',
    LOGOUT: 'LOGOUT',
  }
  
  export const state = () => ({
    access_token: null, // JWT access token
    refresh_token: null, // JWT refresh token
    id: null, // user id
    username: null, // user email address
  })
  
  export const mutations = {
    // store the logged in user in the state
    [AUTH_MUTATIONS.SET_USER] (state, { name, username, role }) {
      state.name = name
      state.username = username
      state.role = role
    },
  
    // store new or updated token fields in the state
    [AUTH_MUTATIONS.SET_PAYLOAD] (state, { access_token, refresh_token = null }) {
      state.access_token = access_token
  
      // refresh token is optional, only set it if present
      if (refresh_token) {
        state.refresh_token = refresh_token
      }
    },
  
    // clear our the state, essentially logging out the user
    [AUTH_MUTATIONS.LOGOUT] (state) {
      state.name = null
      state.username = null
      state.role = null
      state.access_token = null
      state.refresh_token = null
    },
  }
  
  export const actions = {
    async login ({ commit, dispatch }, { username, password }) {
      // make an API call to login the user with an email address and password
      const { data } = await this.$axios.post('http://localhost:8080/api/v1/auth/authenticate', { username, password });
      localStorage.setItem('username',username);
      // commit the user and tokens to the state
      commit(AUTH_MUTATIONS.SET_USER, {
        username: 'string',
        name : data.name,
        role : data.role,
      });
      // commit(AUTH_MUTATIONS.SET_PAYLOAD, payload)
      commit(AUTH_MUTATIONS.SET_PAYLOAD, {
        access_token: data.access_token,
        refresh_token: data.refresh_token
      });
      // if(data.role == 'admin') this.$route.push('/admin')
      // else this.$route.push('/')
    },
  
    async register ({ commit }, { name, email, phone, username, password, roles }) {
      // make an API call to register the user
      const { data } = await this.$axios.post(
        'http://localhost:8080/api/v1/auth/register', 
        { name, email, phone, username, password, roles }
      )
      
      // commit the user and tokens to the state
      // commit(AUTH_MUTATIONS.SET_USER, user)
      commit(AUTH_MUTATIONS.SET_USER, {
        username: username,
        name : data.name,
        role : data.role,
      });
      // commit(AUTH_MUTATIONS.SET_PAYLOAD, payload)
      commit(AUTH_MUTATIONS.SET_PAYLOAD, {
        access_token: data.access_token,
        refresh_token: data.refresh_token
      });
    },
  
    // given the current refresh token, refresh the user's access token to prevent expiry
    async refresh ({ commit, state }) {
      const { refresh_token } = state
  
      // make an API call using the refresh token to generate a new access token
      const { data: { data: { payload } } } = await this.$axios.post(
        'http://localhost:8080/api/v1/auth/refresh-token', 
        { refresh_token }
      )
  
      commit(AUTH_MUTATIONS.SET_USER, {
        username: username,
        name : data.name,
        role : data.role,
      });
      // commit(AUTH_MUTATIONS.SET_PAYLOAD, payload)
      commit(AUTH_MUTATIONS.SET_PAYLOAD, {
        access_token: data.access_token,
        refresh_token: data.refresh_token
      });
    },
  
    // logout the user
    async logout ({ commit, state }) {
      try {
        await this.$axios.get("http://localhost:8080/api/v1/auth/logout");
        commit(AUTH_MUTATIONS.LOGOUT)
        localStorage.removeItem('username')  // remove from local storage as well for security reasons
        
      } catch (error) {
        console.log(error);
      }
    },
  }
  
  export const getters = {
    getUsername: (state) => {
      return state.username;
    },
    // determine if the user is authenticated based on the presence of the access token
    isAuthenticated: (state) => {
      return state.access_token && state.access_token !== ''
    },
  }
  