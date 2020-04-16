<template>
    <div id="app">
        <el-row :gutter="20">
            <el-col :span="16">
                <div class="grid-content bg-purple">

                </div></el-col>
            <el-col :span="8">
                <el-button>{{this.user}}</el-button>
                <el-button  @click="logouting">退出</el-button>
            </el-col>
        </el-row>

        <el-container style="height: 700px; border: 1px solid #eee">

            <el-aside width="200px" style="background-color: rgb(238, 241, 246)">

                <el-menu  :router="true" :default-active="$route.path" v-for="(item,index) in this.rules">
                    <el-submenu  index="/">
                        <template slot="title" >{{item.name}}</template>
                        <el-menu-item v-for="(item2,index2) in item.permissions"  :index="item2.url"
                                      :class="$route.path==item2.url?'is-active':''" >{{item2.name}}</el-menu-item>
                    </el-submenu>

                </el-menu>

            </el-aside>

            <el-main>
                <router-view></router-view>
            </el-main>

        </el-container>
    </div>
</template>

<style>
    .el-header {
        background-color: #B3C0D1;
        color: #333;
        line-height: 60px;
    }

    .el-aside {
        color: #333;
    }

    .grid-content {
        border-radius: 4px;
        min-height: 36px;
    }
    .bg-purple {
        background: #d3dce6;
    }
</style>

<script>


    export default {
        data() {
            return{
                user:'',
                rules:[],
                userId:0,
            }

        },
        methods:{
            logouting(){
                const _this=this
                axios.post('http://localhost:8181/sysuser/logouting').then(function(resp){
                    _this.$router.push({
                        path: '/Login'
                    }).catch(err => {})
                })
            }
        },
        created() {
            const _this=this
            axios.get('http://localhost:8181/sysuser/toHome').then(function(resp){
                console.log('当前用户')
                console.log(resp)
                _this.user=resp.data.user.nickname
                _this.rules=resp.data.rules
                _this.userId=resp.data.user.id
            })
        }
    };
</script>
