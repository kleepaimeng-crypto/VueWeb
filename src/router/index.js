import Vue from "vue";
import VueRouter from "vue-router";

Vue.use(VueRouter)

const Login = () => import("../view/Login.vue")
const Home = () => import("../view/Home.vue")
const BookList = () => import('../view/BookList.vue')
const BookDetails = () => import('../view/BookDetails.vue')


const routes = [
    {
        path: '/',
        name: 'login',
        component: Login,
        meta: {
            title: '登录'
        }
    },
    {
        path: '/home',
        component: Home,
        meta: {
            title: '主页'
        },
        children: [
            {
                path: '/',
                redirect: '/bookList'
            },
            {
                path: '/bookList',
                name: 'bookList',
                component: BookList,
                meta: {
                    title: '图书列表'
                }
            },
            {
                path: '/bookDetails',
                name: 'bookDetails',
                component: BookDetails,
                meta: {
                    title: '图书详情'
                }
            }
        ]
    }

]

const router = new VueRouter({
    mode: 'history',
    base: process.env.BASE_URL,
    routes
})

//全局路由守卫，登录拦截
router.beforeEach((to, from, next) => {
    let username = localStorage.getItem('username')
    if (to.path != '/') {
        if (username) {
            next()
        } else {
            next('/')
        }
    } else {
        next()
    }

})

export default router