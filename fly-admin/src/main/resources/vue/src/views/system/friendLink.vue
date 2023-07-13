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
        >添加友链</el-button>
      </el-row>
    </div>
    <div class="content-box">
      <el-table
        v-loading="listLoading"
        :data="links"
        element-loading-text="Loading"
        border
      >
        <el-table-column prop="id" label="ID" width="80" align="center" />
        <el-table-column prop="name" label="名称" />
        <el-table-column prop="url" label="Url" align="center" />
        <el-table-column prop="status" label="状态" align="center" />
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
      :title="editForm.id > 0 ? '编辑栏目' : '添加栏目'"
    >
      <el-form size="small" label-width="120px" class="demo-form-inline">
        <el-form-item label="名称">
          <el-input v-model="editForm.name" style="width: 90%" />
        </el-form-item>
        <el-form-item label="Url">
          <el-input v-model="editForm.url" style="width: 90%" />
        </el-form-item>
        <el-form-item label="状态">
          <el-input v-model="editForm.status" style="width: 90%" />
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
import { addLink, deleteLink, updateLink, queryLinks } from '@/api/friendLink'

export default {
  data() {
    return {
      listLoading: true,
      links: [],
      edit: false,
      editForm: {

      },
      pager: { page: 1, pageSize: 15 },
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
      this.listLoading = true
      queryLinks(this.pager).then(resp => {
        this.links = resp.data.items
        this.total = Number(resp.data.total)
        this.listLoading = false
      })
    },
    onAdd() {
      this.editForm = {}
      this.edit = true
    },
    onDelete(link) {
      this.$confirm('确认删除？', {
        closeOnPressEscape: false,
        closeOnClickModal: false
      }).then(() => {
        deleteLink(link.id).then((resp) => {
          this.renderList()
        })
      })
    },
    onEdit(link) {
      this.edit = true
      this.editForm = link
    },
    onCancel() {
      this.edit = false
    },
    onSubmit() {
      let res
      if (this.editForm.id > 0) {
        res = updateLink(this.editForm)
      } else {
        res = addLink(this.editForm)
      }
      res.then(resp => {
        this.edit = false
        this.renderList()
      })
    }
  }
}
</script>
