import Vue from 'vue'
import VueRouter from 'vue-router'
import BookManaging from '../views/Book/BookManaging'
import BookAdding from '../views/Book/BookAdding'
import Index from '../views/Index'
import BookUpdating from '../views/Book/BookUpdating'
import RoomManaging from '../views/Room/RoomManaging'
import FacilityManager from '../views/Hotel/FacilityManager'
import GuestList from '../views/Hotel/GuestList'
import Payment from '../views/Hotel/Payment'
import RoomOf from '../views/Hotel/RoomOf'
import TypeUpdate from '../views/Hotel/TypeUpdate'
import DiscountAdd from '../views/Hotel/DiscountAdd'


Vue.use(VueRouter)

const routes = [
    {
        path: "/",
        name: "订单管理",
        component: Index,
        show: true,
        redirect: "/BookManage",
        children: [
            {
                path: "/BookManage",
                name: "查询订单",
                component: BookManaging
            },
            {
                path: "/AddBook",
                name: "添加订单",
                component: BookAdding
            }
        ]
    },
    {
        path: '/update',
        component: BookUpdating,
        show: false
    },

    {
        path: "/RoomManage",
        name: "房间管理",
        show: true,
        component: Index,
        // RoomManaging
        redirect: "/RoomManage",
        children: [
            {
                path: "/RoomManage",
                name: "查看房间",
                component: RoomManaging
            }
        ]
    },

    {
        path: "/GuestList",
        name: "酒店管理",
        show: true,
        component: Index,
        redirect: "/GuestList",
        children: [

            {
                path: "/GuestList",
                name: "顾客列表",
                component: GuestList
            },
            {
                path: "/RoomOf",
                name: "房间相关",
                component: RoomOf
            },
            {
                path: "/FacilityManager",
                name: "设施管理",
                component: FacilityManager
            },
            {
                path: "/Payment",
                name: "收费统计",
                component: Payment
            },
        ]
    },
    {
        path: '/TypeUpdate',
        name: "房间类别修改",
        component: TypeUpdate,
        show: false
    },
    {
        path: '/DiscountAdd',
        name: "折扣修改",
        component: DiscountAdd,
        show: false
    },
]

const router = new VueRouter({
    mode: 'history',
    base: process.env.BASE_URL,
    routes
})

export default router
