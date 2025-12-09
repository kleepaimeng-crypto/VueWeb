import Vue from 'vue'
import App from './App.vue'
import router from './router/index'
import store from './store/index'
import axios from 'axios'
import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'
import VueParticles from 'vue-particles';

Vue.use(ElementUI)
Vue.use(VueParticles)
Vue.config.productionTip = false

//配置axios全局使用
axios.defaults.baseURL = 'http://localhost:8081/'
//解决servlet后端接收到axios请求的数据为null的问题
axios.defaults.headers.post["Content-Type"] = "application/x-www-form-urlencoded"
//解决后端存储不了session的问题
axios.defaults.withCredentials = true
Vue.prototype.$axios = axios

new Vue({
    router,
    store,
    render: h => h(App),
}).$mount('#app')
