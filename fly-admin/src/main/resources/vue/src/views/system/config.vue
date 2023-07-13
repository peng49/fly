<template>
  <div class="app-container">
    <div class="header-box">
      <el-row class="right-row">
        <el-form :inline="true" size="mini">
          <el-form-item>
            <el-input v-model="queryForm.keyword" placeholder="请输入关键字查询" />
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
          >添加配置</el-button>
        </el-form>
      </el-row>
    </div>
    <div class="content-box">
      <el-table
        v-loading="listLoading"
        :data="configList"
        element-loading-text="Loading"
        border
        fit
        highlight-current-row
      >
        <el-table-column prop="id" label="ID" width="80" align="center" />
        <el-table-column prop="attribute" label="属性名" />
        <el-table-column prop="remark" label="备注" />
        <el-table-column prop="value" label="属性值" align="center" />
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
    <el-dialog
      :visible.sync="edit"
      :close-on-click-modal="false"
      :title="editForm.id > 0 ? '编辑栏目' : '添加栏目'"
    >
      <el-form size="small" label-width="120px" class="demo-form-inline">
        <el-form-item label="属性名">
          <el-input v-model="editForm.attribute" style="width: 90%" :disabled="editForm.id > 0" />
        </el-form-item>
        <el-form-item label="备注">
          <el-input v-model="editForm.remark" type="textarea" :rows="5" />
        </el-form-item>
        <el-form-item label="属性值">
          <el-input v-model="editForm.value" />
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
import { addSystemConfig, querySystemConfig, editSystemConfig, deleteSystemConfig } from '@/api/systemConfig'

export default {
  name: 'SystemConfigManager',
  data() {
    return {
      listLoading: true,
      configList: [],
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
      querySystemConfig(Object.assign({}, this.pager, this.queryForm)).then(resp => {
        this.configList = resp.data.items
        this.total = Number(resp.data.total)
        this.listLoading = false
      })
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
      let res
      if (this.editForm.id > 0) {
        res = editSystemConfig(this.editForm)
      } else {
        res = addSystemConfig(this.editForm)
      }
      res.then(resp => {
        this.edit = false
        this.renderList()
      })
    },
    onDelete(column) {
      this.$confirm('确认删除？', {
        closeOnPressEscape: false,
        closeOnClickModal: false
      }).then(() => {
        deleteSystemConfig(column.id).then((resp) => {
          this.renderList()
        })
      })
    }
  }
}
</script>
