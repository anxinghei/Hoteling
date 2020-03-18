<template>
    <div align="center">
        <el-table
                :data="tableData"
                border
                style="width: 60% "
                :default-sort="{prop: 'endday', order: 'ascending'}">
            <el-table-column
                    prop="roomNum"
                    label="房间号"
                    width="120"
                    sortable>
            </el-table-column>
            <el-table-column
                    prop="guestname"
                    label="入住者"
                    width="120">
            </el-table-column>
            <el-table-column
                    prop="guestphone"
                    label="入住者联系方式"
                    width="120">
            </el-table-column>
            <el-table-column
                    prop="startday"
                    label="入住时间"
                    width="120"
                    sortable>
            </el-table-column>
            <el-table-column
                    prop="endday"
                    label="退房时间"
                    width="120"
                    sortable>
            </el-table-column>
            <el-table-column
                    fixed="right"
                    label="操作"
                    width="150">
                <template slot-scope="scope">
                    <el-button @click="edit(scope.row)" type="text" size="small">修改</el-button>
                    <el-button @click="deleteBook(scope.row)" type="text" size="small">退房</el-button>
                </template>
            </el-table-column>
        </el-table>

        <el-pagination
                background
                layout="prev, pager, next"
                :page-size="pageSize"
                :total="total"
                @current-change="page">
        </el-pagination>
    </div>
</template>

<script>
    export default {
        methods: {
            deleteBook(row){
                const _this = this
                axios.delete('http://localhost:8181/book/deleteById/'+row.bookid).then(function(resp){
                    _this.$alert('退房成功！', '消息', {
                        confirmButtonText: '确定',
                        callback: action => {
                            window.location.reload()
                        }
                    })
                })
            },
            edit(row) {
                this.$router.push({
                    path: '/update',
                    query:{
                        id:row.bookid
                    }
                })
            },
            page(pageNum){
                const _this = this
                axios.get('http://localhost:8181/book/findAll/'+(pageNum)+'/8').then(function(resp){
                    console.log(resp)
                    _this.tableData = resp.data.list
                    _this.pageSize = resp.data.pageSize
                    _this.total = resp.data.total
                })
            }
        },

        data() {
            return {
                pageSize:10,
                total:10,
                tableData: [{
                    id: 1,
                    roomNum: 1,
                    startday: 20200303
                }, {
                    id: 2,
                    roomNum: 12,
                    startday: 20200304
                }]
            }
        },

        created() {
            const _this = this
            axios.get('http://localhost:8181/book/findAll/0/8').then(function(resp){
                console.log(resp)
                _this.tableData = resp.data.list
                _this.pageSize = resp.data.pageSize
                _this.total = resp.data.total
            })
        }
    }
</script>