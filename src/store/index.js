import Vue from 'vue'
import Vuex from 'vuex'
import actions from './actions.js'
import mutations from './mutations.js';
import state from './state.js'

Vue.use(Vuex)

const store = new Vuex.Store({
    actions,
    mutations,
    state
})

export default store