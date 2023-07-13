<template>
  <div class="app-container">
    <div class="header-box">
      <el-row class="right-row">
        <el-form :inline="true" size="mini">
          <el-form-item>
            <el-input v-model="queryForm.keyword" placeholder="请输入查询标题" />
          </el-form-item>
          <el-form-item>
            <el-button type="primary" icon="el-icon-search" plain @click="renderList">查询</el-button>
            <el-button @click="resetQuery">重置</el-button>
          </el-form-item>

          <el-button
            type="primary"
            style="margin: 0 0 20px 20px;float:right"
            size="mini"
            icon="el-icon-circle-plus-outline"
            @click="onAdd"
          >添加用户</el-button>
        </el-form>
      </el-row>
    </div>
    <div class="content-box">
      <el-table
        v-loading="listLoading"
        :data="users"
        element-loading-text="Loading"
        border
        fit
        highlight-current-row
      >
        <el-table-column label="用户名">
          <template slot-scope="{ row }">
            <router-link
              :to="{name:'user.view',params:{'id':row.id}}"
              class="link"
              v-text="row.username"
            />
          </template>
        </el-table-column>
        <el-table-column prop="name" label="名称" />
        <el-table-column prop="email" label="邮箱" />
        <el-table-column prop="signature" label="签名" />
        <el-table-column prop="city" label="城市" />
        <el-table-column prop="createdAt" label="注册时间" />
        <el-table-column prop="isAdmin" label="是否是管理员" align="center">
          <template slot-scope="{ row }">
            <el-tag v-if="row.isAdmin == 1">是</el-tag>
            <el-tag v-else type="info">否</el-tag>
          </template>
        </el-table-column>
        <el-table-column label="操作">
          <template slot-scope="{ row }">
            <el-button size="mini" @click="onEdit(row)">编辑</el-button>
            <el-button
              size="mini"
              type="danger"
              @click="onDelete(row)"
            >删除</el-button>
          </template>
        </el-table-column>
      </el-table>
    </div>
    <el-dialog
      :visible.sync="edit"
      :close-on-click-modal="false"
      :title="editForm.id > 0 ? '编辑用户' : '添加用户'"
    >
      <el-form size="small" label-width="120px" class="demo-form-inline">
        <el-form-item label="用户名">
          <el-input v-model="editForm.username" style="width: 90%" />
        </el-form-item>
        <el-form-item label="邮箱">
          <el-input v-model="editForm.email" style="width: 90%" />
        </el-form-item>
        <el-form-item label="名称">
          <el-input v-model="editForm.name" style="width: 90%" />
        </el-form-item>
        <el-form-item label="密码">
          <el-input v-model="editForm.password" style="width: 90%" />
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button @click="onCancel">取 消</el-button>
        <el-button type="primary" @click="onSubmit">确 定</el-button>
      </span>
    </el-dialog>
    <div class="pagination-container">
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
  </div>
</template>
<script>
import { addUser, editUser, queryUser } from '@/api/user'

export default {
  name: 'UserManager',
  data() {
    return {
      listLoading: true,
      users: [],
      edit: false,
      editForm: {

      },
      queryForm: {
        keyword: ''
      },
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
    resetQuery() {
      this.queryForm.keyword = ''
      this.renderList()
    },
    renderList() {
      this.listLoading = true
      queryUser(Object.assign(this.pager, this.queryForm)).then(resp => {
        this.users = resp.data.items
        this.total = Number(resp.data.total)
        this.listLoading = false
      })
    },
    onAdd() {
      this.edit = true
      this.editForm = {}
    },
    onEdit(user) {
      this.editForm = user
      this.edit = true
    },
    onCancel() {
      this.edit = false
    },
    onSubmit() {
      let res
      if (this.editForm.id > 0) {
        res = editUser(this.editForm)
      } else {
        res = addUser(this.editForm)
      }
      res.then(resp => {
        this.edit = false
        this.renderList()
      })
    },
    onDelete(column) {
      // this.$confirm('确认删除？', {
      //   closeOnPressEscape: false,
      //   closeOnClickModal: false
      // }).then(() => {
      //   deleteUser(column).then((resp) => {
      //     this.renderList()
      //   })
      // })
    }
  }
}
</script>
