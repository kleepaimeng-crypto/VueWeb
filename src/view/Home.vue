<template>
  <el-container>
    <el-header>
      <Header/>
    </el-header>
    <el-main>
      <router-view/>
    </el-main>
  </el-container>
</template>

<script>
import Header from "../components/Header.vue"

export default {
  name: "Home",
  components: {
    Header
  }
}
</script>

<style scoped>
.el-header {
  background-color: #B3C0D1;
  text-align: center;
  height: 60px;
}

.el-main {
  margin: 0;
  background: linear-gradient(#F0FFFF, #FFF0F5);
  text-align: center;
  line-height: 160px;
  height: calc(100vh - 60px);
}
</style>