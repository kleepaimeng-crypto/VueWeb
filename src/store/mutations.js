const mutations = {
    LOGIN: (state, value) => {
        localStorage.setItem('username', value)
        state.username = value
    },
    LOGOUT: (state) => {
        localStorage.removeItem('username')
        state.username = null
    }
}

export default mutations