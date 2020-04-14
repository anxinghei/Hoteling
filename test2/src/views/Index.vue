<template>
    <div id="app">
        <el-row :gutter="20">
            <el-col :span="16">
                <div class="grid-content bg-purple">

                </div></el-col>
            <el-col :span="8">
                <el-button  @click="logouting">退出</el-button>
            </el-col>
        </el-row>

        <el-container style="height: 700px; border: 1px solid #eee">

            <el-aside width="200px" style="background-color: rgb(238, 241, 246)">

                <el-menu  :router="true" :default-active="$route.path">
                    <el-submenu v-for="(item,index) in $router.options.routes" :index="item.path" v-if="item.show">
                        <template slot="title">{{item.name}}</template>
                        <el-menu-item v-for="(item2,index2) in item.children" :index="item2.path"
                                      :class="$route.path==item2.path?'is-active':''">{{item2.name}}</el-menu-item>
                        <!--            <el-menu-item index="/login" >登录</el-menu-item>-->
                    </el-submenu>
                </el-menu>

            </el-aside>

            <el-main>
                <router-view></router-view>
            </el-main>

        </el-container>
    </div>
</template>

<script>
    export default {
        name: "Index",
        methods:{
            logouting(){
                const _this=this
                axios.post('http://localhost:8181/sysuser/logouting').then(function(resp){
                    _this.$router.push({
                        path: '/Login'
                    }).catch(err => {})
                })
            }
        }
    }
</script>

