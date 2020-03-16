<template>
    <el-tabs v-model="activeName">
        <el-tab-pane label="first" name="first">
            <div>
                <p>dsdsfdfs</p>
                <p>dsdsd</p>
            </div>
        </el-tab-pane>

        <el-tab-pane label="second" name="second">
            <div>
                <p>你好</p>
                <p>你好歹</p>
            </div>
        </el-tab-pane>
    </el-tabs>

</template>

<script>
    export default {
        data() {
            return {
                activeName: "first",
            }
        },

    }
</script>

<style scoped>

</style>