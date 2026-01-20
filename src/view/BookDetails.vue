<template>
  <div class="bookDetails">
    <el-row>
      <el-col :span="10">
        <div class="grid-content">
          <div class="demo-image__preview">
            <el-image
                class="coverImg"
                :src="coverUrl"
                :preview-src-list="coverUrl | filterImg">
            </el-image>
          </div>
        </div>
      </el-col>
      <el-col :span="14">
        <div class="grid-content">
          <el-row>
            <el-descriptions :column="1">
              <el-descriptions-item label="作者">{{bookInfo.author}}</el-descriptions-item>
              <el-descriptions-item label="出版时间">{{bookInfo.publish_date}}</el-descriptions-item>
              <el-descriptions-item label="页数">{{bookInfo.pages}}</el-descriptions-item>
              <el-descriptions-item label="单价（元）">{{bookInfo.price}}</el-descriptions-item>
              <el-descriptions-item label="内容提要">{{bookInfo.content}}</el-descriptions-item>
            </el-descriptions>
          </el-row>
          <el-row>
            <el-button type="danger" icon="el-icon-shopping-cart-full" round>加入购物车</el-button>
            <el-button type="info" icon="el-icon-view" round>在线预览</el-button>
            <el-button type="success" icon="el-icon-check" round>购买电子书</el-button>
          </el-row>
        </div>
      </el-col>
    </el-row>
  </div>
</template>

<script>
import axios from 'axios'

export default {
  name: "BookDetails",
  data() {
    return {
      id: '',           //图书id
      bookInfo: {},     //图书详细信息哈哈哈哈哈
      coverUrl: ''      //图书封面地址
    };
  },
  methods: {
    //获取图书列表详情
    getBookDetails() {
      axios.post("/getBookDetails", {
        id: this.$route.params.id
      }).then((res) => {
        if (res.data.statusCode == 200) {
          //将获取的书籍详细信息装入bookInfo
          this.bookInfo = res.data.content.message
          //使用require避免webpack编译后图片地址改变而造成图片无法显示的情况,采用三段式解决动态加载图片链接的错误
          this.coverUrl = this.bookInfo.picture ? require('../assets/cover/' + this.bookInfo.picture) : ''
        } else {
          return this.$message.error(res.data.statusMessage)
        }
      }).catch((error) => {
        return this.$message.error("获取图书详情失败：" + error)
      })
    }
  },
  filters: {
    //将后端传入的字符串格式图片转换为数组类型
    filterImg(item) {
      if (typeof item === "string") {
        return item.split("|");
      } else {
        return item
      }
    },
  },
  mounted() {
    //页面加载时发送请求数据
    this.getBookDetails()
  }
}
</script>

<style scoped>
.bookDetails {
  padding: 25px;
  background: white;
  box-shadow: 0 10px 10px rgba(0, 0, 0, 0.15);
  width: 80%;
  text-align: center;
  margin: auto;
  border-radius: 10px;
}

.coverImg {
  width: 400px;
  height: 480px;
}
</style>