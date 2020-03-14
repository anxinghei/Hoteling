<template>
    <div align="center">
        <el-form style="width: 60%" :model="ruleForm" :rules="rules" ref="ruleForm" label-width="100px"
                 class="demo-ruleForm">

            <el-form-item label="入住时间" prop="startday">
                <el-input v-model="ruleForm.startday"></el-input>
            </el-form-item>
            <el-form-item label="退房时间" prop="endday">
                <el-input v-model="ruleForm.endday"></el-input>
            </el-form-item>
            <el-form-item label="房间号" prop="roomNum">
                <el-input v-model="ruleForm.roomNum"></el-input>
            </el-form-item>
            <el-form-item label="入住者" prop="guestname">
                <el-input v-model="ruleForm.guestname" readOnly></el-input>
            </el-form-item>
            <el-form-item label="入住者电话" prop="guestphone">
                <el-input v-model="ruleForm.guestphone" readOnly></el-input>
            </el-form-item>

            <el-form-item>
                <el-button type="primary" @click="submitForm('ruleForm')">修改</el-button>
            </el-form-item>

        </el-form>
    </div>
</template>

<script>
    export default {
        data() {
            var checkEnding = (rule, value, callback) => {
                if (!value) {
                    return callback(new Error('退房时间不能为空'));
                }
                setTimeout(() => {
                    if (value <= this.ruleForm.startday) {
                        callback(new Error('退房时间必须晚于入住时间'));
                    } else {
                        callback();
                    }
                }, 1000);
            };
            return {
                ruleForm: {
                    bookid: '',
                    roomNum: '',
                    guestname: '',
                    sex: '',
                    guestphone: '',
                    startday: '',
                    endday: ''
                },
                rules: {
                    roomNum: [
                        {required: true, message: '房间号不能为空', trigger: 'blur'}
                    ],
                    startday: [
                        {required: true, message: '入住时间不能为空', trigger: 'blur'}
                    ],
                    endday: [
                        {required: true, validator: checkEnding, trigger: 'blur'}
                    ]
                }
            };
        },
        methods: {
            submitForm(formName) {
                const _this = this
                console.log(this.ruleForm)
                this.$refs[formName].validate((valid) => {
                    if (valid) {
                        console.log(_this.ruleForm.typeid + "---")
                        axios.post('http://localhost:8181/room/isBooked', this.ruleForm).then(function (resp) {
                            console.log(resp)
                            if (resp.data == 'failed') {
                                _this.ruleForm.typeid = 1
                            } else {
                                _this.ruleForm.typeid = 0
                            }
                            if (_this.ruleForm.typeid) {
                                _this.$alert('该房间在预定时间内已有入住者！', '消息', {
                                    confirmButtonText: '确定',
                                })
                                return false;
                            } else {
                                axios.put('http://localhost:8181/book/update', _this.ruleForm).then(function (resp) {
                                    if (resp.data == 'success') {
                                        console.log("1111111")
                                        _this.$alert('订单修改成功！', '消息', {
                                            confirmButtonText: '确定',
                                            callback: action => {
                                                _this.$router.push('/BookManage')
                                            }
                                        })
                                    }
                                })
                            }
                        })
                    } else {
                        return false;
                    }
                });
            }
        },
        created() {
            const _this = this
            axios.get('http://localhost:8181/book/findById/' + this.$route.query.id).then(function (resp) {
                _this.ruleForm = resp.data
            })
        }
    }
</script>