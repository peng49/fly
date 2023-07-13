<template>
  <div class="app-container">
    <div class="header-box">
      <el-row class="right-row">
        <el-form :inline="true" size="mini">
          <el-form-item>
            <el-input v-model="queryForm.keyword" placeholder="请输入导航名查询" />
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
          >添加导航</el-button>
        </el-form>
      </el-row>
    </div>
    <div class="content-box">
      <el-table
        v-loading="listLoading"
        :data="navigations"
        element-loading-text="Loading"
        border
        fit
        highlight-current-row
      >
        <el-table-column prop="id" label="ID" width="80" align="center" />
        <el-table-column prop="title" label="标题" />
        <el-table-column prop="url" label="Url" />
        <el-table-column prop="sort" label="排序" align="center" />
        <el-table-column prop="status" label="状态">
          <template slot-scope="{ row }">
            <el-tag :type="row.status == 1 ? 'success' : 'info'">
              {{ row.status == 1 ? "启用" : "禁用" }}
            </el-tag>
          </template>
        </el-table-column>
        <el-table-column label="操作">
          <template slot-scope="{ row }">
            <el-button size="mini" @click="onEdit(row)">编辑</el-button>
            <!-- <el-button
              size="mini"
              type="danger"
              @click="onDelete(row)"
            >删除</el-button> -->
            <el-button size="mini" :type="row.status == 1?'danger':'primary'" @click="updateStatus(row,row.status == 1?0:1)" v-text="row.status == 1?'禁用':'启用'" />
          </template>
        </el-table-column>
      </el-table>
    </div>
    <el-dialog
      :visible.sync="edit"
      :close-on-click-modal="false"
      :title="editForm.id > 0 ? '编辑导航' : '添加导航'"
    >
      <el-form size="small" label-width="120px" class="demo-form-inline">
        <el-form-item label="标题">
          <el-input v-model="editForm.title" style="width: 90%" />
        </el-form-item>
        <el-form-item label="Url">
          <el-input v-model="editForm.url" style="width: 90%" />
        </el-form-item>
        <el-form-item label="排序">
          <el-input-number v-model="editForm.sort" :min="0" />
        </el-form-item>
        <el-form-item label="状态">
          <el-switch v-model="editForm.status" active-value="1" inactive-value="0" />
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
import { addNavigation, queryNavigation, editNavigation, deleteNavigation } from '@/api/navigation'

export default {
  name: 'NavigationManager',
  data() {
    return {
      listLoading: true,
      navigations: [],
      edit: false,
      editForm: {},
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
      this.queryForm = {}
      this.renderList()
    },
    renderList() {
      this.listLoading = true
      queryNavigation(Object.assign({}, this.pager, this.queryForm)).then(resp => {
        this.navigations = resp.data.items
        this.total = Number(resp.data.total)
        this.listLoading = false
      })
    },
    onAdd() {
      this.editForm = { status: '1', sort: 0 }
      this.edit = true
    },
    onEdit(navigation) {
      navigation.status = String(navigation.status)
      this.editForm = navigation
      this.edit = true
    },
    onCancel() {
      this.edit = false
    },
    onSubmit() {
      let res
      if (this.editForm.id > 0) {
        res = editNavigation(this.editForm)
      } else {
        res = addNavigation(this.editForm)
      }
      res.then(resp => {
        this.edit = false
        this.renderList()
      })
    },
    updateStatus(navigation, status) {
      this.$confirm('确认' + (Number(status) === 1 ? '启用' : '禁用') + '改导航？', {
        closeOnPressEscape: false,
        closeOnClickModal: false
      }).then(() => {
        navigation.status = status
        editNavigation(navigation)
      })
    },
    onDelete(navigation) {
      this.$confirm('确认删除？', {
        closeOnPressEscape: false,
        closeOnClickModal: false
      }).then(() => {
        deleteNavigation(navigation).then((resp) => {
          this.renderList()
        })
      })
    }
  }
}
</script>
