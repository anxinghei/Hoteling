<template>
    <div class="el-tabs__nav">
        <el-tabs v-model="activeName" type="card" @tab-click="handleClick" style="width:50% ">
            <el-tab-pane label="已入住房间" name="booked">
                <el-table
                        :data="bookedData"
                        stripe
                        style="width: 100%"
                        :default-sort="{prop: 'roomNum', order: 'ascending'}">
                    <el-table-column
                            prop="roomNum"
                            label="房间号"
                            sortable>
                    </el-table-column>
                    <el-table-column
                            prop="typeName"
                            label="房间类别"
                            sortable>
                    </el-table-column>
                    <el-table-column
                            prop="guestName"
                            label="入住者"
                            sortable>
                    </el-table-column>
                    <el-table-column
                            prop="guestPhone"
                            label="联系方式"
                            sortable>
                    </el-table-column>
                    <el-table-column
                            prop="startDay"
                            label="入住时间"
                            sortable>
                    </el-table-column>
                    <el-table-column
                            prop="endDay"
                            label="退房时间"
                            sortable>
                    </el-table-column>
                </el-table>
            </el-tab-pane>

            <el-tab-pane label="未入住房间" name="unbooked">
                <el-table
                        :data="unbookedData"
                        stripe
                        style="width: 100%"
                        :default-sort="{prop: 'roomNum', order: 'ascending'}">
                    <el-table-column
                            prop="roomNum"
                            label="房间号"
                            sortable>
                    </el-table-column>
                    <el-table-column
                            prop="typeName"
                            label="房间类别"
                            sortable>
                    </el-table-column>
                </el-table>
            </el-tab-pane>
        </el-tabs>
    </div>
</template>

<script>
    export default {
        data() {
            return {
                activeName: "booked",
                bookedData: [{
                    startDay: '2016-05-02',
                    guestName: '王小虎',
                    guestPhone: '13229435832',
                }],
                unbookedData:[]
            }
        },
        methods: {
            handleClick(tab, event) {
                this.activeName = tab.name
                console.log(this.activeName)
            }
        },
        created() {
            const _this = this
            axios.get('http://localhost:8181/room/getRoom').then(function (resp) {
                console.log(resp)
                _this.bookedData = resp.data
                axios.get('http://localhost:8181/room/getUnroom').then(function (resp) {
                    console.log(resp)
                    _this.unbookedData = resp.data
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