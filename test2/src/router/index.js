import Vue from 'vue'
import VueRouter from 'vue-router'
import BookManaging from '../views/Book/BookManaging'
import BookAdding from '../views/Book/BookAdding'
import Index from '../views/Index'
import BookUpdating from '../views/Book/BookUpdating'

Vue.use(VueRouter)

const routes = [
  {
    path:"/",
    name:"订单管理",
    component:Index,
    show:true,
    redirect:"/BookManage",
    children:[
      {
        path:"/BookManage",
        name:"查询订单",
        component:BookManaging
      },
      {
        path:"/AddBook",
        name:"添加订单",
        component:BookAdding
      }
    ]
  },
  {
    path:'/update',
    component:BookUpdating,
    show:false
  }
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
