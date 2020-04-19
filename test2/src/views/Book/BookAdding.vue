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
            <el-form-item label="房间类别" prop="roomtype">
                <el-select v-model="ruleForm.roomtype" style="width: 100%" placeholder="请选择"
                           value-key="id" @change="changeSelect">
                    <el-option v-for="(item,index) in this.ruleForm.options"
                                :label="item.name" :key="item.id" :value="item"></el-option>
                </el-select>
            </el-form-item>
            <el-form-item label="说明" prop="content" >
                <el-input v-model="ruleForm.content" readonly></el-input>
            </el-form-item>
            <el-form-item label="房间号" prop="room">
                <el-select v-model="ruleForm.room" style="width: 100%" placeholder="请选择"
                           value-key="id" @change="changeRoom">
                    <el-option v-for="(item,index) in this.ruleForm.rooms"
                               :label="item.num" :key="item.id" :value="item"></el-option>
                </el-select>
            </el-form-item>
            <el-form-item label="预订者" prop="customername">
                <el-input v-model="ruleForm.customername"></el-input>
            </el-form-item>
            <el-form-item label="预订者电话" prop="customerphone">
                <el-input v-model="ruleForm.customerphone"></el-input>
            </el-form-item>
            <el-form-item label="入住者" prop="guestname">
                <el-input v-model="ruleForm.guestname"></el-input>
            </el-form-item>
            <el-form-item label="入住者电话" prop="guestphone">
                <el-input v-model="ruleForm.guestphone"></el-input>
            </el-form-item>

            <el-form-item>
                <el-button type="primary" @click="submitForm('ruleForm')">添加</el-button>
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
                    content: '',
                    roomtype: '',
                    typeid:'',
                    options:[],
                    rooms:[],
                    string:'',
                    isbooked:1,
                },
                lastPricce:0,
                rules: {
                    room: [
                        {required: true, message: '房间号不能为空', trigger: 'blur'}
                    ],
                    customername: [
                        {required: true, message: '预订者不能为空', trigger: 'blur'}
                    ],
                    customerphone: [
                        {required: true, message: '预订者电话不能为空', trigger: 'blur'}
                    ],
                    guestname: [
                        {required: true, message: '入住者不能为空', trigger: 'blur'}
                    ],
                    guestphone: [
                        {required: true, message: '入住者电话不能为空', trigger: 'blur'}
                    ],
                    startday: [
                        {required: true, message: '入住时间不能为空', trigger: 'blur'}
                    ],
                    endday: [
                        {required: true, validator: checkEnding, trigger: 'blur'}
                    ],


                }
            };
        },
        methods: {
            submitForm(formName) {
                const _this = this
                if (this.ruleForm.isbooked){
                    this.$alert('该房间在预定时间内已有入住者！', '消息', {
                        confirmButtonText: '确定',
                    })
                    return false;
                }
                this.$confirm('此订单需收款 '+this.lastPricce+' 元', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                }).then(() => {
                    axios.post('http://localhost:8181/book/save', this.ruleForm).then(function (resp) {
                        if (resp.data == 'success') {
                            _this.$alert('订单添加成功！', '消息', {
                                confirmButtonText: '确定',
                                callback: action => {
                                    _this.$router.push('/BookManage')
                                }
                            })
                        }
                    })
                }).catch(() => {
                    this.$message({
                        type: 'info',
                        message: '已取消订单'
                    });
                });
                // this.$refs[formName].validate((valid) => {
                //     if (valid ) {
                //         axios.post('http://localhost:8181/book/save', this.ruleForm).then(function (resp) {
                //             if (resp.data == 'success') {
                //                 _this.$alert('订单添加成功！', '消息', {
                //                     confirmButtonText: '确定',
                //                     callback: action => {
                //                         _this.$router.push('/BookManage')
                //                     }
                //                 })
                //             }
                //         })
                //     } else {
                //         return false;
                //     }
                // });
            },
            changeSelect(value){
                this.ruleForm.roomtype=value.name
                this.ruleForm.typeid=value.id
                var string="价格为"+value.price+"元，"+value.lives+"人间，占地"+value.area+"平方"
                const _this = this
                axios.get('http://localhost:8181/type/getFacilities/'+value.id).then(function (resp) {
                    console.log(resp)
                    string+=resp.data+"等房间设施"
                    _this.ruleForm.content=string
                    _this.ruleForm.string=string
                }),
                axios.get('http://localhost:8181/room/getAllByType/'+value.id).then(function (resp) {
                    _this.ruleForm.rooms = resp.data
                }),
                    axios.post('http://localhost:8181/band/getPrice',this.ruleForm).then(function (resp) {
                        _this.lastPricce = resp.data
                        console.log("最终付款："+_this.lastPricce )
                    })
            },
            changeRoom(value){
                const _this = this
                axios.post('http://localhost:8181/room/isBooked',this.ruleForm).then(function (resp) {
                    console.log(resp)
                    if (resp.data == 'failed') {
                        _this.ruleForm.content="该房间在预定时间内已有入住者"
                        _this.ruleForm.isbooked=1
                    }else {
                        _this.ruleForm.content=_this.ruleForm.string
                        _this.ruleForm.isbooked=0
                    }
                })
            },
        },
        created() {
            const _this = this
            axios.get('http://localhost:8181/type/getAll/').then(function (resp) {
                console.log(resp)
                _this.ruleForm.options = resp.data
            })
        }
    }
</script>

