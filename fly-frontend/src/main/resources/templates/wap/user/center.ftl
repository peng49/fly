<!DOCTYPE html>
<html>
<head>
    <meta name="viewport"
          content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no">
    <meta charset='utf-8'>
    <title>个人中心</title>
    <link rel="stylesheet" href="/static/editor.md/css/editormd.min.css"/>
    <#include "../base/link.ftl" />
</head>
<body>
<#include "../base/header.ftl" />
<#include "../base/nav.ftl" />
<template id="center-template">
    <div class="panel">
        <div class="panel-head">
            <p>个人中心</p>
        </div>
        <div class="cells">
            <a v-for="item in items"
               @click="$parent.loadComponent(item.component)"
               class="weui-cell weui-cell_access"
               :href="item.url">
                <div class="weui-cell__bd">
                    <p>{{item.label}}</p>
                </div>
                <div class="weui-cell__ft">
                </div>
            </a>
        </div>
    </div>
</template>
<template id="posts-template">
    <div class="panel">
        <div class="panel-head">
            <p>文章列表</p>
        </div>
        <div class="cells">
            <div v-for="post in posts" class="weui-cell">
                <div class="weui-cell__bd">
                    <p>{{post.title}}</p>
                </div>
                <div class="weui-cell__ft">{{post.publishAt}}</div>
            </div>
        </div>
    </div>
</template>
<template id="settings-template">
    <div class="row">
        <div class="weui-tab">
            <div class="weui-navbar">
                <a class="weui-navbar__item weui-bar__item--on" href="#tab1">
                    基本信息
                </a>
            </div>
            <div class="weui-tab__bd">
                <div id="tab1" class="weui-tab__bd-item weui-tab__bd-item--active">
                    <h1>页面一</h1>
                </div>
            </div>
        </div>
    </div>
</template>
<template id="reset-password-template">
    <p> 重置密码</p>
</template>

<div id="app" class="container">
    <component :is="component"></component>

    <br/>
    <div style="text-align: center">
        <button class="weui-btn weui-btn_mini weui-btn_default">退出/返回</button>
    </div>
</div>
<#include "../base/footer.ftl" />
<script src="https://cdn.jsdelivr.net/npm/vue@2.6.0"></script>
<script type="text/javascript">
    let request = function (data) {

    };
    Vue.component("center", {
        data: function () {
            return {
                items: [
                    {label: "我的文章", url: "javascript:", component: "posts"},
                    {label: "我的收藏", url: "javascript:", component: "collection"},
                    {label: "用户设置", url: "javascript:", component: "settings"},
                    {label: "修改密码", url: "javascript:", component: "reset-password"},
                    {label: "我的消息", url: "javascript:", component: "message"}
                ]
            }
        },
        template: "#center-template",
        methods: {}
    });
    Vue.component("posts", {
        data: function () {
            return {
                posts: [
                    {title: "标题", publishAt: "发布时间", status: "", top: 0}
                ]
            }
        },
        template: "#posts-template"
    });

    Vue.component("settings", {
        data: function () {
            return {}
        },
        template: "#settings-template"
    });
    Vue.component("reset-password", {
        data: function () {
            return {}
        },
        template: "#reset-password-template"
    });

    new Vue({
        el: "#app",
        data: {
            component: "center"
        },
        methods: {
            loadComponent: function (component) {
                if (!component) {
                    return false;
                }
                this.component = component
            }
        }
    });
</script>
</body>
</html>