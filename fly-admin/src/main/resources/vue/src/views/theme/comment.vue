<template>
  <div class="app-container">
    <div class="content-box">
      <el-table
        v-loading="listLoading"
        :data="comments"
        element-loading-text="Loading"
        border
      >
        <el-table-column type="selection" width="55" align="center" />
        <el-table-column prop="post.title" label="文章标题" />
        <el-table-column prop="post.publishAt" label="发布时间" />
        <el-table-column prop="commentTime" label="评论时间" />
        <el-table-column label="评论内容">
          <template slot-scope="{ row }">
            <div v-html="row.content" />
          </template>
        </el-table-column>

        <el-table-column label="操作">
          <template slot-scope="{ row }">
            <el-button
              size="mini"
              type="danger"
              @click="onDelete(row)"
            >删除</el-button>
          </template>
        </el-table-column>
      </el-table>
    </div>
    <div class="pagination-container">
      <el-pagination
        background
        :current-page.sync="pager.page"
        :page-sizes="[10, 20, 50, 100]"
        :page-size="pager.pageSize"
        layout="total, sizes, prev, pager, next, jumper"
        :total="total"
        @size-change="handleSizeChange"
        @current-change="renderList"
      />
    </div>
  </div>
</template>
<script>
import { deleteComment, queryComment } from '@/api/postComment'

export default {
  data() {
    return {
      listLoading: true,
      comments: [],
      pager: { page: 1, pageSize: 10 },
      total: 0
    }
  },
  created() {
    this.renderList()
  },
  methods: {
    handleSizeChange(size) {
      this.pager.pageSize = size
      this.renderList()
    },
    renderList() {
      queryComment(this.pager).then(resp => {
        this.comments = resp.data.items
        this.total = Number(resp.data.total)
        this.listLoading = false
      })
    },
    onDelete(comment) {
      this.$confirm('确认删除？', {
        closeOnPressEscape: false,
        closeOnClickModal: false
      }).then(() => {
        deleteComment(comment).then((resp) => {
          this.renderList()
        })
      })
    }
  }
}
</script>
