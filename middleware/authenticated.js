
export default function ({ store, redirect }) {
  const isAuthenticated = store.getters.isAuthenticated
  console.log(isAuthenticated);
    // If the user is not authenticated
    if (!isAuthenticated) {
      return redirect('/login')
    }
  }