<template>
  <div>
    <vue-particles class="login-bg"
                   color="#39AFFD"
                   :particleOpacity="0.7"
                   :particlesNumber="100"
                   shapeType="circle"
                   :particleSize="4"
                   linesColor="#8DD1FE"
                   :linesWidth="1"
                   :lineLinked="true"
                   :lineOpacity="0.4"
                   :linesDistance="150"
                   :moveSpeed="3"
                   :hoverEffect="true"
                   hoverMode="grab"
                   :clickEffect="true"
                   clickMode="push">
    </vue-particles>
    <el-form ref="loginFormRef"
             class="loginContainer"
             :rules="rules"
             :model="readerInfo"
             @keydown.enter.native="submitForm()">
      <h2 class="title"><i class="el-icon-s-management"/>用户登录</h2>
      <el-form-item prop="username" class="input-box">
        <el-input type="text"
                  class="input"
                  v-model="readerInfo.username"
                  placeholder="请输入用户名"
                  prefix-icon="el-icon-user-solid"
                  auto-complete="off"
                  clearable>
        </el-input>
      </el-form-item>
      <el-form-item prop="password" class="input-box">
        <el-input type="password"
                  v-model="readerInfo.password"
                  placeholder="请输入密码"
                  prefix-icon="el-icon-lock"
                  auto-complete="off"
                  clearable>
        </el-input>
      </el-form-item>
      <el-button class="input-box"
                 type="primary"
                 @click="submitForm()">
        登录
      </el-button>
    </el-form>
  </div>
</template>

<script>
import axios from 'axios'

export default {
  name: "Login",
  data() {
    return {
      readerInfo: {
        username: '', //用户名
        password: ''  //密码
      },
      //前端校验信息
      rules: {
        username: {
          required: true,
          message: '用户名不能为空',
          trigger: 'blur'
        },
        password: {
          required: true,
          message: '密码不能为空',
          trigger: 'blur'
        }
      }
    }
  },
  methods: {
    submitForm() {
      this.$refs.loginFormRef.validate(valid => {
        //未验证通过则直接return
        if (!valid)
          return this.$message.error("登录失败")
        //发送登录请求验证
        axios.post('/login', {
          username: this.readerInfo.username,
          password: this.readerInfo.password
        }).then((res) => {
          if (res.data.statusCode == 200) {
            this.$store.dispatch('UserLogin', res.data.content.username)
            //避免登录后浏览器回退生效，造成拦截失效
            this.$router.replace('/home')
            return this.$message.success("登录成功")
          } else {
            //直接显示后端错误信息
            return this.$message.error(res.data.statusMessage)
          }
        }).catch((error) => {
          //登录失败发送失败信息
          return this.$message.error("登录错误：" + error)
        })
      })
    }
  }
}
</script>

<style scoped>
.loginContainer {
  position: absolute;
  top: 25%;
  left: 36%;
  background: #fff;
  max-width: 350px;
  width: 100%;
  padding: 25px 30px;
  border-radius: 5px;
  box-shadow: 0 10px 10px rgba(0, 0, 0, 0.15);
}

.loginContainer .title {
  font-size: 30px;
  font-weight: 600;
  margin: 20px 0 10px 0;
  position: relative;
}

.loginContainer .input-box {
  width: 100%;
  height: 45px;
  margin-top: 25px;
  position: relative;
}

.login-bg {
  width: 100%;
  height: 100%;
  position: absolute;
  top: 0;
  left: 0;
  overflow: hidden;
  z-index: -1;
  background-image: url("../assets/background.jpg");
  background-size: cover;
}
</style>