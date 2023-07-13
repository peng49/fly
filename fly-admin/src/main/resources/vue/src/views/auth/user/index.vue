<template>
  <div class="app-container">
    <div class="header-box">
      <el-row class="right-row">
        <el-button
          type="primary"
          style="margin: 0 0 20px 20px"
          size="mini"
          icon="el-icon-circle-plus-outline"
          @click="onAdd"
        >添加用户</el-button>
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
        <el-table-column prop="id" label="ID" />
        <el-table-column prop="username" label="用户名" />
        <el-table-column prop="avatar" label="头像" />
        <el-table-column prop="createdAt" label="创建时间" />
        <el-table-column prop="updatedAt" label="更新时间" />
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
    <el-dialog :visible="edit" :close-on-click-modal="false" :title="editForm.id > 0?'编辑用户':'添加用户'">
      <el-form size="small" label-width="80px" class="demo-form-inline">
        <el-form-item label="用户名">
          <el-input v-model="editForm.username" style="width: 90%" />
        </el-form-item>
        <el-form-item label="名称">
          <el-input v-model="editForm.name" style="width: 90%" />
        </el-form-item>
        <el-form-item label="角色">
          <el-select v-model="editForm.roleIds" multiple style="width: 90%">
            <el-option v-for="role in roles" :key="role.id" :label="role.name" :value="role.id" />
          </el-select>
        </el-form-item>
        <el-form-item label="权限">
          <el-tree
            ref="tree"
            :data="permissionTree"
            show-checkbox
            :props="{label:'name',key:'id'}"
            node-key="id"
            @check-change="handleCheckChange"
          />
        </el-form-item>

        <el-form-item v-if="editForm.id > 0">
          <el-checkbox v-model="updatePassword"><strong>更新密码</strong></el-checkbox>
        </el-form-item>

        <div v-if="editForm.id == 0 || updatePassword">
          <el-form-item label="密码">
            <el-input
              v-model="editForm.password"
              type="password"
              style="width: 90%"
            />
          </el-form-item>
          <el-form-item label="确认密码">
            <el-input v-model="editForm.confirmPassword" type="password" style="width: 90%" />
          </el-form-item>
        </div>
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
import { getUsers, addUser, editUser, deleteUser } from '@/api/admin/user'
import { getPermissions } from '@/api/admin/permission'
import { buildTree } from '@/utils/tree'
import { getRoles } from '@/api/admin/role'

export default {
  name: 'AdminUserManager',
  data() {
    return {
      listLoading: true,
      users: [],
      roles: [],
      edit: false,
      updatePassword: false,
      editForm: {},
      permissionTree: [],
      pager: { page: 1, pageSize: 15 },
      total: 0
    }
  },
  created() {
    this.renderList()
    this.getRoles()
    this.initPermissions()
    console.log(this.$refs.tree)
  },
  methods: {
    handleSizeChange(size) {
      this.pager.pageSize = size
      this.renderList()
    },
    initPermissions() {
      getPermissions().then(resp => {
        this.permissionTree = buildTree(resp.data)
      })
    },
    renderList() {
      getUsers().then((resp) => {
        this.users = resp.data
        this.total = Number(resp.data.total)
        this.listLoading = false
      })
    },
    handleCheckChange(permission, checked, node) {
      this.editForm.permissionIds = this.$refs.tree.getCheckedKeys()
    },
    getRoles() {
      getRoles().then(resp => {
        this.roles = resp.data
      })
    },
    onAdd() {
      this.edit = true
      this.editForm = { id: 0 }

      if (this.$refs.tree) {
        this.$refs.tree.setCheckedKeys([])
      }
    },
    onEdit(user) {
      console.log(this.users)
      this.edit = true
      this.updatePassword = false

      this.$nextTick(() => {
        if (this.$refs.tree) {
          this.$refs.tree.setCheckedKeys(user.permissionIds)
        }
      })
      this.editForm = Object.assign({}, user)
    },
    onDelete(row) {
      this.$confirm('确认删除？', {
        closeOnPressEscape: false,
        closeOnClickModal: false
      }).then(() => {
        deleteUser(row).then((resp) => {
          this.renderList()
        })
      })
    },
    onCancel() {
      this.edit = false
    },
    onSubmit() {
      console.log(this.editForm)
      let res
      if (this.editForm.id > 0) {
        res = editUser(this.editForm)
      } else {
        res = addUser(this.editForm)
      }
      res.then((resp) => {
        console.log(resp)
        this.edit = false
        this.renderList()
      })
    }
  }
}
</script>
