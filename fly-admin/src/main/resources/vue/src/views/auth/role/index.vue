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
        >添加角色</el-button>
      </el-row>
    </div>
    <div class="content-box">
      <el-table
        v-loading="listLoading"
        :data="roles"
        element-loading-text="Loading"
        border
        fit
        highlight-current-row
      >
        <el-table-column prop="id" label="ID" />
        <el-table-column prop="name" label="角色名" />
        <el-table-column prop="createdAt" label="创建时间" align="center" />
        <el-table-column prop="updatedAt" label="更新时间" align="center" />
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
      :title="editForm.id > 0 ? '编辑角色' : '添加角色'"
    >
      <el-form size="small" label-width="80px" class="demo-form-inline">
        <el-form-item label="角色名">
          <el-input v-model="editForm.name" style="width: 90%" />
        </el-form-item>
        <el-form-item label="Slug">
          <el-input v-model="editForm.slug" style="width: 90%" />
        </el-form-item>
        <el-form-item label="权限">
          <el-transfer
            v-model="permissionIds"
            filterable
            :data="permissions"
            :titles="['可选权限', '已选权限']"
          />
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
        @current-change="renderRoles"
      />
    </div>
  </div>
</template>

<script>
import { getRoles, editRole, addRole, deleteRole } from '@/api/admin/role'
import { getPermissions } from '@/api/admin/permission'

export default {
  name: 'AdminRoleManager',
  data() {
    return {
      listLoading: true,
      roles: [],
      edit: false,
      permissions: [],
      permissionIds: [],
      editForm: {
      },
      pager: { page: 1, pageSize: 15 },
      total: 0
    }
  },
  created() {
    this.renderRoles()
    this.getPermissions()
  },
  methods: {
    handleSizeChange(size) {
      this.pager.pageSize = size
      this.renderRoles()
    },
    renderRoles() {
      getRoles().then((resp) => {
        this.roles = resp.data
        this.total = Number(resp.data.total)
        this.listLoading = false
      })
    },
    getPermissions() {
      getPermissions().then(resp => {
        const items = []
        resp.data.forEach(permission => {
          items.push({
            key: permission.id,
            label: permission.name
          })
        })
        this.permissions = items
        console.log(this.permissions)
      })
    },
    onAdd() {
      this.edit = true
      this.editForm = {}
      this.permissionIds = []
    },
    onEdit(role) {
      const _this = this
      _this.edit = true
      _this.editForm = role

      // 已有权限
      _this.permissionIds = []
      role.permissions.forEach(permission => {
        _this.permissionIds.push(permission.id)
      })
    },
    onDelete(role) {
      this.$confirm('确认删除？', {
        closeOnPressEscape: false,
        closeOnClickModal: false
      }).then(() => {
        deleteRole(role).then((resp) => {
          this.renderRoles()
        })
      })
    },
    onCancel() {
      this.edit = false
    },
    onSubmit() {
      this.editForm.permissionIds = this.permissionIds
      let res
      if (this.editForm.id > 0) {
        res = editRole(this.editForm)
      } else {
        res = addRole(this.editForm)
      }
      res.then((resp) => {
        console.log(resp)
        this.edit = false
        this.renderRoles()
      })
    }
  }
}
</script>
