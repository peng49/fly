<template>
  <div class="app-container">
    <div class="header-box">
      <el-row class="right-row">
        <el-button
          type="primary"
          style="margin: 0 0 20px 0px"
          size="mini"
          @click="handleFilter"
        ><svg-icon icon-class="filter" /> 筛选</el-button>
        <router-link :to="{name:'post.create'}">
          <el-button
            plain
            type="success"
            style="float:right"
            size="mini"
            icon="el-icon-circle-plus-outline"
          >新增文章</el-button>
        </router-link>
      </el-row>
      <el-row v-if="filter">
        <el-form :inline="true" size="mini">
          <el-form-item>
            <el-input v-model="queryForm.keyword" placeholder="请输入查询标题" />
          </el-form-item>
          <el-form-item label-width="90px" label="栏目:" class="postInfo-container-item">
            <el-select
              v-model="queryForm.columnId"
              :remote-method="getColumns"
              filterable
              default-first-option
              remote
              placeholder="Search Column"
            >
              <el-option v-for="column in columns" :key="column.id" :label="column.name" :value="column.id" />
            </el-select>
          </el-form-item>
          <el-form-item>
            <el-button type="primary" icon="el-icon-search" plain @click="renderList">查询</el-button>
            <el-button @click="resetQuery">重置</el-button>
          </el-form-item>
        </el-form>
      </el-row>
    </div>
    <div class="content-box">
      <el-table
        v-loading="listLoading"
        :data="posts"
        element-loading-text="Loading"
        border
        @selection-change="handleSelectionChange"
      >
        <el-table-column type="selection" width="55" align="center" />
        <el-table-column prop="title" label="标题" align="center" />
        <el-table-column prop="column.name" label="栏目" />
        <el-table-column prop="author.username" label="作者" />
        <el-table-column prop="status" label="状态" align="center" />
        <el-table-column prop="viewCount" label="点击数" align="center" />
        <el-table-column prop="replyCount" label="回复数" align="center" />
        <el-table-column prop="createdAt" label="创建时间" />
        <el-table-column prop="publishAt" label="发布时间" />
        <el-table-column label="操作">
          <template slot-scope="{ row }">
            <router-link
              :to="{name:'post.edit',params:{'id':row.id}}"
            >
              <el-button size="mini">编辑</el-button>
            </router-link>
            <!-- <el-button
              size="mini"
              type="danger"
              @click="onDelete(row)"
            >删除</el-button> -->
          </template>
        </el-table-column>
      </el-table>
    </div>
    <el-dialog
      :visible.sync="edit"
      :close-on-click-modal="false"
      :title="editForm.id > 0 ? '编辑栏目' : '添加栏目'"
    >
      <el-form size="small" label-width="120px" class="demo-form-inline">
        <el-form-item label="名称">
          <el-input v-model="editForm.name" style="width: 90%" />
        </el-form-item>
        <el-form-item label="排序">
          <el-input v-model="editForm.sort" style="width: 90%" />
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button @click="onCancel">取 消</el-button>
        <el-button type="primary" @click="onSubmit">确 定</el-button>
      </span>
    </el-dialog>
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
import { queryPosts } from '@/api/post'
import { queryColumn } from '@/api/column'

export default {
  name: 'PostManager',
  data() {
    return {
      filter: false,
      listLoading: true,
      posts: [],
      edit: false,
      editForm: {

      },
      columns: [],
      queryForm: {
        keyword: ''
      },
      selectedRows: [],
      pager: { page: 1, pageSize: 10 },
      total: 0
    }
  },
  created() {
    this.renderList()
  },
  methods: {
    handleFilter() {
      if (this.filter) {
        this.filter = false
      } else {
        this.filter = true
      }
    },
    resetQuery() {
      this.queryForm = {}
      this.renderList()
    },
    renderList() {
      this.listLoading = true
      const queryBody = Object.assign({}, this.pager, this.queryForm)
      queryPosts(queryBody).then(resp => {
        this.posts = resp.data.items
        this.total = Number(resp.data.total)
        this.listLoading = false
      })
    },
    handleSizeChange(pageSize) {
      this.pager.pageSize = pageSize
      this.renderList()
    },
    onAdd() {
      this.editForm = {}
      this.edit = true
    },
    onEdit(column) {
      this.editForm = column
      this.edit = true
    },
    onCancel() {
      this.edit = false
    },
    onSubmit() {

    },
    onDelete(column) {
      this.$confirm('确认删除？', {
        closeOnPressEscape: false,
        closeOnClickModal: false
      }).then(() => {
        // deleteColumn(column).then((resp) => {
        //   this.renderList()
        // })
      })
    },
    handleSelectionChange(rows) {
      console.log(rows)
      this.selectedRows = rows
    },
    getColumns(keyword) {
      queryColumn({ pageSize: 15, keyword: keyword }).then(resp => {
        this.columns = resp.data.items
      })
    }
  }
}
</script>
