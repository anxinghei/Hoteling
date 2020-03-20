<template>
    <div class="el-tabs__nav">
        <el-tabs v-model="activeName" type="card" @tab-click="handleClick" style="width:50% ">
            <el-tab-pane label="所有账单" name="payed">
                <el-table
                        :data="payedData"
                        stripe
                        style="width: 100%"
                        :default-sort="{prop: 'roomNum', order: 'ascending'}">
                    <el-table-column
                            prop="roomNum"
                            label="房间号"
                            sortable>
                    </el-table-column>
                    <el-table-column
                            prop="guestname"
                            label="入住者"
                            sortable>
                    </el-table-column>
                    <el-table-column
                            prop="amount"
                            label="付款金额"
                            sortable>
                    </el-table-column>
                    <el-table-column
                            prop="date"
                            label="付款时间"
                            sortable>
                    </el-table-column>
                    <el-table-column
                            prop="startday"
                            label="入住时间"
                            sortable>
                    </el-table-column>
                    <el-table-column
                            prop="endday"
                            label="退房时间"
                            sortable>
                    </el-table-column>
                </el-table>
                <el-pagination
                        background
                        layout="prev, pager, next"
                        :page-size="payedPageSize"
                        :total="payedTotal"
                        @current-change="payedPage">
                </el-pagination>
            </el-tab-pane>

            <el-tab-pane label="统计" name="total">
                <el-form style="width: 60%"  ref="ruleForm" label-width="100px"
                         class="demo-ruleForm">
                    <el-form-item label="今日订单数" prop="dayPayment">
                        <el-input v-model="totalData.dayPayment" readonly></el-input>
                    </el-form-item>
                    <el-form-item label="今日收款" prop="daySum">
                        <el-input v-model="totalData.daySum" readonly></el-input>
                    </el-form-item>
                    <el-form-item label="本月订单数" prop="monthPayment" >
                        <el-input v-model="totalData.monthPayment" readonly></el-input>
                    </el-form-item>
                    <el-form-item label="本月收款" prop="monthSum" >
                        <el-input v-model="totalData.monthSum" readonly></el-input>
                    </el-form-item>
                    <el-form-item label="今年订单数" prop="yearPayment" >
                        <el-input v-model="totalData.yearPayment" readonly></el-input>
                    </el-form-item>
                    <el-form-item label="今年收款" prop="yearSum" >
                        <el-input v-model="totalData.yearSum" readonly></el-input>
                    </el-form-item>
                </el-form>
            </el-tab-pane>
        </el-tabs>
    </div>
</template>

<script>
    export default {
        data() {
            return {
                activeName: "payed",
                payedData: [{
                    guestName: '王小虎',
                    guestPhone: '13229435832',
                }],
                payedPageSize:10,
                payedTotal:0,
                totalData:[],
            }
        },
        methods: {
            handleClick(tab, event) {
                this.activeName = tab.name
                console.log(this.activeName)
            },
            payedPage(pageNum){
                const _this = this
                axios.get('http://localhost:8181/payment/findAll/'+(pageNum)+'/8').then(function(resp){
                    _this.payedData = resp.data.list
                    _this.payedpageSize = resp.data.pageSize
                    _this.payedtotal = resp.data.total
                })
            },
        },
        created() {
            const _this = this
            axios.get('http://localhost:8181/payment/findAll/0/8').then(function(resp){
                _this.payedData = resp.data.list
                _this.payedPageSize = resp.data.pageSize
                _this.payedTotal = resp.data.total
                axios.get('http://localhost:8181/payment/getTotal/').then(function(resp){
                    console.log(resp)
                    _this.totalData = resp.data
                })
            })
        }
    }
</script>

<style scoped>
    .el-tabs__nav {
        width: 100%;
        display: flex;
        justify-content: space-around;
    }
</style>