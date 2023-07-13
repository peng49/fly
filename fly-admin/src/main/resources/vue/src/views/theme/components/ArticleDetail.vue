<template>
  <div class="createPost-container">
    <el-form ref="postForm" :model="postForm" :rules="rules" class="form-container">

      <sticky :z-index="10" :class-name="'sub-navbar '+postForm.status">
        <el-button v-loading="loading" style="margin-left: 10px;" type="success" @click="submitForm">
          发布
        </el-button>
        <el-button v-loading="loading" type="warning" @click="draftForm">
          保存到草稿
        </el-button>
      </sticky>

      <div class="createPost-main-container">
        <el-row>

          <el-col :span="24">
            <el-form-item style="margin-bottom: 40px;" prop="title">
              <MDinput v-model="postForm.title" :maxlength="100" name="name" required>
                标题
              </MDinput>
            </el-form-item>
            <div class="postInfo-container">
              <el-row>
                <el-col :span="8">
                  <el-form-item label-width="60px" label="作者:" class="postInfo-container-item">
                    <el-select
                      v-model="postForm.authorId"
                      :remote-method="getRemoteUserList"
                      filterable
                      default-first-option
                      remote
                      placeholder="Search user"
                    >
                      <el-option v-for="user in users" :key="user.id" :label="user.username" :value="user.id" />
                    </el-select>
                  </el-form-item>
                </el-col>

                <el-col :span="10">
                  <el-form-item label-width="120px" label="发布时间:" class="postInfo-container-item">
                    <el-date-picker v-model="displayTime" type="datetime" format="yyyy-MM-dd HH:mm:ss" placeholder="Select date and time" />
                  </el-form-item>
                </el-col>

                <el-col :span="6">
                  <el-form-item label-width="90px" label="栏目:" class="postInfo-container-item">
                    <el-select
                      v-model="postForm.columnId"
                      :remote-method="getColumns"
                      filterable
                      default-first-option
                      remote
                      placeholder="Search Column"
                    >
                      <el-option v-for="column in columns" :key="column.id" :label="column.name" :value="column.id" />
                    </el-select>
                  </el-form-item>
                </el-col>
              </el-row>
            </div>
          </el-col>
        </el-row>
        <el-form-item prop="content" style="margin-bottom: 30px;">
          <Tinymce ref="editor" v-model="postForm.content" :height="400" />
        </el-form-item>
      </div>
    </el-form>
  </div>
</template>

<script>
import Sticky from '@/components/Sticky' // 粘性header组件
import Tinymce from '@/components/Tinymce'
import MDinput from '@/components/MDinput'
import { addPost, getPost, updatePost } from '@/api/post'
import { queryUser, getUser } from '@/api/user'
import { queryColumn, getColumn } from '@/api/column'

const defaultForm = {
  authorId: 0,
  columnId: 0,
  status: 'draft',
  title: '', // 文章题目
  content: '', // 文章内容
  id: undefined
}
export default {
  name: 'ArticleDetail',
  components: { Sticky, Tinymce, MDinput },
  props: {
    isEdit: {
      type: Boolean,
      default: false
    }
  },
  data() {
    return {
      postForm: Object.assign({}, defaultForm),
      loading: false,
      users: [],
      columns: [],
      rules: {

      },
      tempRoute: {}
    }
  },
  computed: {
    contentShortLength() {
      return this.postForm.content_short.length
    },
    displayTime: {
      get() {
        return (+new Date(this.postForm.display_time))
      },
      set(val) {
        this.postForm.display_time = new Date(val)
      }
    }
  },
  created() {
    if (this.isEdit) {
      const id = this.$route.params && this.$route.params.id
      this.fetchData(id)
    }
    // this.tempRoute = Object.assign({}, this.$route)
  },
  methods: {
    fetchData(id) {
      getPost(id).then(resp => {
        const post = resp.data
        this.postForm.id = post.id
        this.postForm.title = post.title
        this.postForm.content = post.content

        if (post.author.id) {
          getUser(post.author.id).then(resp => {
            this.users = [resp.data]
            this.postForm.authorId = post.author.id
          })
        }

        if (post.column.id) {
          getColumn(post.column.id).then(resp => {
            this.columns = [resp.data]
            this.postForm.columnId = post.column.id
          })
        }
      })
    },
    setTagsViewTitle() {
      const title = 'Edit Article'
      const route = Object.assign({}, this.tempRoute, { title: `${title}-${this.postForm.id}` })
      this.$store.dispatch('tagsView/updateVisitedView', route)
    },
    setPageTitle() {
      const title = 'Edit Article'
      document.title = `${title} - ${this.postForm.id}`
    },
    submitForm() {
      console.log(this.postForm)
      this.$refs.postForm.validate(valid => {
        if (valid) {
          this.loading = true
          this.postForm.status = 1

          let res
          if (this.isEdit) {
            res = updatePost(this.postForm)
          } else {
            res = addPost(this.postForm)
          }
          res.then(resp => {
            this.$notify({
              title: '成功',
              message: '操作',
              type: 'success',
              duration: 2000
            })
          }).finally(res => {
            this.loading = false
          })
        } else {
          console.log('error submit!!')
          return false
        }
      })
    },
    draftForm() {
      if (this.postForm.content.length === 0 || this.postForm.title.length === 0) {
        this.$message({
          message: '请填写必要的标题和内容',
          type: 'warning'
        })
        return
      }
      this.$message({
        message: '保存成功',
        type: 'success',
        showClose: true,
        duration: 1000
      })
      this.postForm.status = 0
    },
    getRemoteUserList(keyword) {
      console.log(keyword)
      queryUser({ pageSize: 15, keyword: keyword }).then(resp => {
        this.users = resp.data.items
      })
    },
    getColumns(keyword) {
      queryColumn({ pageSize: 15, keyword: keyword }).then(resp => {
        this.columns = resp.data.items
      })
    }
  }
}
</script>

<style lang="scss" scoped>
@import "~@/styles/mixin.scss";
.createPost-container {
  position: relative;
  .createPost-main-container {
    padding: 40px 45px 20px 50px;
    .postInfo-container {
      position: relative;
      @include clearfix;
      margin-bottom: 10px;
      .postInfo-container-item {
        float: left;
      }
    }
  }
  .word-counter {
    width: 40px;
    position: absolute;
    right: 10px;
    top: 0px;
  }
}
.article-textarea ::v-deep {
  textarea {
    padding-right: 40px;
    resize: none;
    border: none;
    border-radius: 0px;
    border-bottom: 1px solid #bfcbd9;
  }
}
</style>
