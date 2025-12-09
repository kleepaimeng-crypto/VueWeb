<template>
  <div class="header">
    <el-row>
      <el-col :span="1">
        <div class="grid-content">
          <img class="titleImg" src="../assets/logo.jpg" alt="logo"/>
        </div>
      </el-col>
      <el-col :span="3">
        <div class="grid-content">
          <p class="text">BookSystem</p>
        </div>
      </el-col>
      <el-col :span="18">
        <div class="grid-content"></div>
      </el-col>
      <el-col :span="2">
        <div class="grid-content">
          <el-dropdown>
                        <span class="el-dropdown-link">
                            <img class="avatar" src="../assets/Avatar.jpg"/>
                        </span>
            <el-dropdown-menu slot="dropdown">
              <el-dropdown-item @click.native="logout">注销</el-dropdown-item>
            </el-dropdown-menu>
          </el-dropdown>
        </div>
      </el-col>
    </el-row>
  </div>
</template>

<script>
export default {
  name: "Header",
  methods: {
    //注销
    logout() {
      this.$router.push("/");
      this.$store.dispatch("UserLogout");
    },
  },
};
</script>

<style scoped>
.header {
  padding: 0;
}

.titleImg {
  height: 40px;
  width: 40px;
  float: right;
}

.text {
  display: inline;
  font: 25px "微软雅黑";
  margin: 0 0 5px 10px;
  font-weight: 500;
  text-align: center;
  color: white;
  float: left;
}

.avatar {
  width: 32px;
  height: 32px;
  border-radius: 50%;
}

.el-row {
  margin: 0;
  margin-top: 15px;
}

.grid-content {
  min-height: 40px;
  margin: 0;
}
</style>
