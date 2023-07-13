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
        >添加权限</el-button>
      </el-row>
    </div>
    <div class="content-box">
      <el-table
        v-loading="listLoading"
        :data="permissionsTree"
        row-key="id"
        element-loading-text="Loading"
        border
        fit
        highlight-current-row
      >
        <el-table-column prop="id" label="ID" />
        <el-table-column prop="name" label="权限名" />
        <el-table-column prop="slug" label="标识" />
        <el-table-column prop="httpMethod" label="Http方法" />
        <el-table-column prop="httpPath" label="路径" />
        <el-table-column prop="createdAt" label="创建时间" />
        <el-table-column prop="updatedAt" label="更新时间" />
        <el-table-column label="操作">
          <template slot-scope="{ row }">
            <el-button size="mini" @click="onEdit(row)">编辑</el-button>
            <el-button size="mini" type="danger" @click="onDelete(row)">
              删除
            </el-button>
          </template>
        </el-table-column>
      </el-table>
    </div>
    <el-dialog
      :visible.sync="edit"
      :close-on-click-modal="false"
      :title="editForm.id > 0 ? '编辑权限' : '添加权限'"
    >
      <el-form size="small" label-width="120px" class="demo-form-inline">
        <el-form-item label="上级权限">
          <el-cascader
            v-model="editForm.parentId"
            :options="permissionsTree"
            :props="{ label: 'name', value: 'id' }"
            change-on-select
          />
        </el-form-item>
        <el-form-item label="名称">
          <el-input v-model="editForm.name" style="width: 90%" />
        </el-form-item>
        <el-form-item label="标识">
          <el-input v-model="editForm.slug" style="width: 90%" />
        </el-form-item>
        <el-form-item label="Http Method">
          <el-input v-model="editForm.httpMethod" style="width: 90%" />
        </el-form-item>
        <el-form-item label="Http Path">
          <el-input v-model="editForm.httpPath" type="textarea" :rows="10" style="width: 90%" />
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button @click="onCancel">取 消</el-button>
        <el-button type="primary" @click="onSubmit">确 定</el-button>
      </span>
    </el-dialog>
  </div>
</template>

<script>
import {
  getPermissions,
  addPermission,
  editPermission,
  deletePermission
} from '@/api/admin/permission'
import { buildTree } from '@/utils/tree'

export default {
  name: 'AdminUserManager',
  data() {
    return {
      listLoading: true,
      permissions: [],
      permissionsTree: [],
      edit: false,
      editForm: {}
    }
  },
  created() {
    this.renderList()
  },
  methods: {
    renderList() {
      console.log('getPermissions')
      getPermissions().then((resp) => {
        this.permissions = resp.data
        this.permissionsTree = buildTree(this.permissions)
        console.log(this.permissionsTree)
        this.listLoading = false
      })
    },
    onAdd() {
      this.edit = true
      this.editForm = {}
    },
    onEdit(permission) {
      this.edit = true
      this.editForm = permission
    },
    onDelete(row) {
      this.$confirm('确认删除？', {
        closeOnPressEscape: false,
        closeOnClickModal: false
      }).then(() => {
        deletePermission(row).then((resp) => {
          this.renderList()
        })
      })
    },
    onCancel() {
      this.edit = false
    },
    onSubmit() {
      if (this.editForm.parentId.length > 0) {
        this.editForm.parentId = this.editForm.parentId[
          this.editForm.parentId.length - 1
        ]
      } else {
        if (this.editForm.parentId === undefined) {
          this.editForm.parentId = 0
        }
      }
      let res
      if (this.editForm.id > 0) {
        res = editPermission(this.editForm)
      } else {
        res = addPermission(this.editForm)
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
