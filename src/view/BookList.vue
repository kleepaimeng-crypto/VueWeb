<template>
  <div class="bookListContainer">
    <div class="tableDiv">
      <el-table
          :data="tableData"
          stripe
          class="table"
      >
        <el-table-column prop="id" label="ID" width="120">
        </el-table-column>
        <el-table-column prop="book_name" label="书名">
        </el-table-column>
        <el-table-column prop="author" label="作者">
        </el-table-column>
        <el-table-column prop="publish_date" label="出版日期">
        </el-table-column>
        <el-table-column prop="pages" label="页数" width="120">
        </el-table-column>
        <el-table-column prop="price" label="价格" width="120">
        </el-table-column>
        <el-table-column label="操作" width="160">
          <template slot-scope="scope">
            <el-button
                size="mini"
                type="primary"
                round
                @click="showDetails(scope.row.id)">
              查看详情
            </el-button>
          </template>
        </el-table-column>
      </el-table>
    </div>

    <div class="pagination">
      <el-pagination
          background
          @size-change="handleSizeChange"
          @current-change="handleCurrentChange"
          :current-page="pageInfo.pageNow"
          :page-sizes="[5, 10, 15, 20]"
          :page-size="pageInfo.pageSize"
          layout="total, sizes, prev, pager, next, jumper"
          :total="pageInfo.totalBooks">
      </el-pagination>
    </div>
  </div>
</template>

<script>
import axios from 'axios'

export default {
  name: "BookList",
  data() {
    return {
      pageInfo: {
        pageNow: 1,     //当前页数
        pageSize: 10,   //每页显示的数据量
        totalBooks: 0    //总数据量
      },
      tableData: [],      //用来存储后端传来的书籍信息，并填入表格
    };
  },
  mounted() {
    //页面加载时发送请求数据
    this.getBookList()
  },
  methods: {
    getBookList() {
      axios.post("/getBookList", {
        pageNow: this.pageInfo.pageNow,
        pageSize: this.pageInfo.pageSize
      }).then((res) => {
        if (res.data.statusCode == 200) {
          //成功获取数据，更新表格数据和页码数据
          this.tableData = res.data.content.message.books
          this.pageInfo.totalBooks = res.data.content.message.bookCount
        } else if (res.data.statusCode == 500) {
          //数据获取失败，弹出提示信息
          return this.$message.error(res.data.statusMessage)
        }
      }).catch((error) => {
        return this.$message.error("获取图书列表失败：" + error)
      });
    },
    handleSizeChange(val) {
      //设置每页显示条数时，更新pageSize，重新获取图书列表
      this.pageInfo.pageSize = val
      this.getBookList()
    },
    handleCurrentChange(val) {
      //翻页或跳转特定页码时，更新当前页码pageNow，重新获取图书列表
      this.pageInfo.pageNow = val
      this.getBookList()
    },
    showDetails(id) {
      //点击查看详情按钮，路由带参跳转
      this.$router.push({
        name: 'bookDetails',
        params: {
          id: id
        }
      })
    },
  }
};
</script>

<style scoped>
.bookListContainer {
  height: 100%;
}

.tableDiv {
  width: 80%;
  text-align: center;
  margin: auto;
  background: #fff;
  padding: 20px;
  box-shadow: 0 10px 10px rgba(0, 0, 0, 0.15);
  vertical-align: middle
}

.table {
  line-height: 40px;
}

.pagination {
  margin-top: 40px;
}
</style>