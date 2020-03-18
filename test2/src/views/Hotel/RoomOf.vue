<template>
    <div class="el-tabs__nav">
        <el-tabs v-model="activeName" type="card" @tab-click="handleClick" style="width:50% ">
            <el-tab-pane label="房间类别" name="type">
                <el-table
                        :data="typeData"
                        stripe
                        style="width: 100%"
                        :default-sort="{prop: 'name', order: 'ascending'}">
                    <el-table-column
                            prop="name"
                            label="类别名"
                            sortable>
                    </el-table-column>
                    <el-table-column
                            prop="price"
                            label="价格"
                            sortable>
                    </el-table-column>
                    <el-table-column
                            prop="area"
                            label="大小"
                            sortable>
                    </el-table-column>
                    <el-table-column
                            fixed="right"
                            label="操作"
                            width="150">
                        <template slot-scope="scope">
                            <el-button @click="edit(scope.row)" type="text" size="small">修改</el-button>
                        </template>
                    </el-table-column>
                </el-table>
                <el-pagination
                        background
                        layout="prev, pager, next"
                        :page-size="typePageSize"
                        :total="typeTotal"
                        @current-change="typePage">
                </el-pagination>
            </el-tab-pane>

            <el-tab-pane label="折扣" name="discount">
                <el-table
                        :data="discountData"
                        stripe
                        style="width: 100%"
                        :default-sort="{prop: 'name', order: 'ascending'}">
                    <el-table-column
                            prop="name"
                            label="预订者"
                            sortable>
                    </el-table-column>
                    <el-table-column
                            prop="phone"
                            label="联系方式"
                            sortable>
                    </el-table-column>
                </el-table>
                <el-pagination
                        background
                        layout="prev, pager, next"
                        :page-size="discountPageSize"
                        :total="discountTotal"
                        @current-change="discountPage">
                </el-pagination>
            </el-tab-pane>
        </el-tabs>
    </div>
</template>

<script>
    export default {
        data() {
            return {
                activeName: "type",
                typeData: [{
                    name: '',
                    price: '',
                    area: '',
                }],
                discountData:[],
                typePageSize:10,
                typeTotal:0,
                discountPageSize:10,
                discountTotal:0,
            }
        },
        methods: {
            handleClick(tab, event) {
                this.activeName = tab.name
                console.log(this.activeName)
            },
            typePage(pageNum){
                const _this = this
                axios.get('http://localhost:8181/type/findAll/'+(pageNum)+'/10').then(function(resp){
                    _this.typeData = resp.data.list
                    _this.pageSize = resp.data.pageSize
                    _this.total = resp.data.total
                })
            },
            discountPage(pageNum){
                const _this = this
                axios.get('http://localhost:8181/customer/findAll/'+(pageNum)+'/10').then(function(resp){
                    _this.discountData = resp.data.list
                    _this.discountPageSize = resp.data.pageSize
                    _this.discountTotal = resp.data.total
                })
            },
            edit(row) {
                this.$router.push({
                    path: '/TypeUpdate',
                    query:{
                        id:row.id
                    }
                })
            },
        },
        created() {
            const _this = this
            axios.get('http://localhost:8181/type/findAll/0/10').then(function(resp){
                _this.typeData = resp.data.list
                _this.typePageSize = resp.data.pageSize
                _this.typeTotal = resp.data.total
                axios.get('http://localhost:8181/customer/findAll/0/10').then(function(resp){
                    _this.discountData = resp.data.list
                    _this.discountPageSize = resp.data.pageSize
                    _this.discountTotal = resp.data.total
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