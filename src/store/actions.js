const actions = {
    UserLogin(context, value) {
        context.commit('LOGIN', value)
    },

    UserLogout(context) {
        context.commit('LOGOUT')
    }
}

export default actions