<template>
    <div class="login-container">
        <el-form ref="form" :model="form" label-width="80px" class="login-form">
            <h2 class="login-title">酒店管理系统</h2>

            <h4 class="login-title" style="color:red">{{this.mes}}</h4>
            <el-form-item label="用户名" prop="username">
                <el-input v-model="form.username"></el-input>
            </el-form-item>
            <el-form-item label="密码" prop="password">
                <el-input v-model="form.password"></el-input>
            </el-form-item>

            <el-form-item>
                <el-button type="primary" @click="onSubmit">登录</el-button>
            </el-form-item>
        </el-form>
    </div>
</template>
<script>
    export default {
        data() {
            return {
                form: {
                    username: "",
                    password: "",
                },
                mes:'',
            };
        },
        methods: {
            onSubmit() {
                const _this=this
                console.log(this.form)
                axios.post('http://localhost:8181/logining', this.form).then(function (resp) {
                    console.log(resp)
                    // 0成功 	1验证码错误		2用户名不存在	3密码错误
                    if (resp.data == 0) {
                        _this.$router.push('/Home')
                    }else{
                        if (resp.data == 1){_this.mes="验证码错误"}
                        else if (resp.data == 2){_this.mes="用户名不存在"}
                        else if (resp.data == 3){_this.mes="密码错误"}
                    }
                })
            }
        }
    };
</script>

<style acoped>
    .login-form {
        width: 350px;
        margin: 160px auto; /* 上下间距160px，左右自动居中*/

        padding: 30px;
        border-radius: 20px; /* 圆角 */
    }

    /* 背景 */
    .login-container {
        position: absolute;
        width: 100%;
        height: 100%;

    }

    /* 标题 */
    .login-title {
        color: #303133;
        text-align: center;
    }
</style> -->