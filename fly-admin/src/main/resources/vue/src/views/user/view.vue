<template>
  <div class="app-container">
    <el-row :gutter="20">
      <el-col :span="6">
        <el-card class="box-card">
          <div class="text item" style="text-align: center;">
            <img :src="user.avatar" :alt="user.username" style="height:100px;width:100px;border-radius: 50px;">
            <p><strong>{{ user.username }}</strong></p>
          </div>
          <div class="text item">
            注册时间：<span>{{ user.createdAt }}</span>
          </div>
          <div class="text item">
            文章数量: {{ user.postCount }}
          </div>
          <div class="text item">
            评论数量: {{ user.commentCount }}
          </div>
        </el-card>

        <el-card class="box-card" style="margin-top: 15px;">
          <div slot="header" class="clearfix">
            <span>卡片名称</span>
            <el-button style="float: right; padding: 3px 0" type="text">操作按钮</el-button>
          </div>
          <div v-for="o in 4" :key="o" class="text item">
            {{ '列表内容 ' + o }}
          </div>
        </el-card>
      </el-col>
      <el-col :span="18">
        <el-tabs v-model="activeName" type="border-card" @tab-click="handleClick">
          <el-tab-pane label="用户管理" name="first">用户管理</el-tab-pane>
          <el-tab-pane label="配置管理" name="second">配置管理</el-tab-pane>
          <el-tab-pane label="角色管理" name="third">角色管理</el-tab-pane>
          <el-tab-pane label="定时任务补偿" name="fourth">定时任务补偿</el-tab-pane>
        </el-tabs>
      </el-col>
    </el-row>
  </div>

</template>
<script>
import { getUser } from '@/api/user'

export default {
  data() {
    return {
      user: {},
      activeName: 'second'
    }
  },
  created() {
    this.renderContent(this.$route.params.id)
  },
  methods: {
    handleClick(tab, event) {
      console.log(tab, event)
    },
    renderContent(id) {
      getUser(id).then(resp => {
        this.user = resp.data
      })
    }
  }
}
</script>
