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
            <p>我的文章</p>
        </div>
        <div class="cells">
            <div v-for="post in posts" class="cell">
                <template v-if="posts.length > 0">
                    <div class="weui-cell">
                        <div class="weui-cell__bd">
                            <p>
                            <span style="background:#393D49;color:white;margin-right: 4px; padding: 1px 5px; border-radius: 3px;"
                                  v-if="post.status ==0">草稿</span>
                                {{post.title}}
                            </p>
                        </div>
                        <div class="weui-cell__ft">{{post.publishAt}}</div>
                    </div>
                    <div style="text-align: right;padding: 5px 15px"><a :href="'/post/edit/'+post.id">编辑</a></div>
                </template>
                <template v-else>
                    <div class="cell" style="padding: 5px 15px">
                        暂无文章
                    </div>
                </template>
            </div>
        </div>
    </div>
</template>
<template id="collection-template">
    <div class="panel">
        <div class="panel-head">
            <p>我的收藏</p>
        </div>
        <div class="cells">
            <template v-if="posts.length > 0">
                <div v-for="post in posts" class="cell">
                    <div class="weui-cell">
                        <div class="weui-cell__bd">
                            <p>
                                {{post.title}}
                            </p>
                        </div>
                        <div class="weui-cell__ft">{{post.publishAt}}</div>
                    </div>
                    <div style="text-align: right;padding: 5px 15px"><a :href="'/post/detail/'+post.id">查看</a></div>
                </div>
            </template>
            <template v-else>
                <div class="cell" style="padding: 5px 15px">
                    暂无收藏
                </div>
            </template>
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
                <a class="weui-navbar__item" href="#tab2">
                    头像
                </a>
            </div>
            <div class="weui-tab__bd">
                <div id="tab1" class="weui-tab__bd-item weui-tab__bd-item--active">
                    <div class="input-group">
                        <div class="input-label">
                            <label class="label">邮箱</label>
                        </div>
                        <div class="input-block">
                            <input class="input" v-model="user.email" type="text"/>
                        </div>
                    </div>
                    <div class="input-group">
                        <div class="input-label">
                            <label class="label">昵称</label>
                        </div>
                        <div class="input-block">
                            <input class="input" v-model="user.username" type="text"/>
                        </div>
                    </div>
                    <div class="input-group">
                        <div class="input-label">
                            <label class="label">城市</label>
                        </div>
                        <div class="input-block">
                            <input class="input" v-model="user.city" type="text"/>
                        </div>
                    </div>
                    <div class="input-group">
                        <div class="input-label">
                            <label class="label">签名</label>
                        </div>
                        <div class="input-block">
                            <textarea v-model="user.signature"></textarea>
                        </div>
                    </div>
                    <div class="btn-group">
                        <div class="right">
                            <a href="javascript:;" @click="submitForm"
                               class="weui-btn weui-btn_mini weui-btn_default">提交</a>
                        </div>
                    </div>
                </div>
                <div id="tab2" class="weui-tab__bd-item">
                    <div class="weui-cells weui-cells_form">
                        <div class="weui-cell">
                            <div class="weui-cell__bd">
                                <div class="weui-uploader">
                                    <div class="weui-uploader__hd">
                                        <p class="weui-uploader__title">修改头像</p>
                                    </div>
                                    <div class="weui-uploader__bd">
                                        <ul class="weui-uploader__files">
                                            <li class="weui-uploader__file" :style="avatarBg">
                                                <div class="weui-uploader__input-box">
                                                    <input class="weui-uploader__input" type="file"
                                                           @change="uploadAvatar" accept="image/*" multiple="">
                                                </div>
                                            </li>

                                        </ul>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>
<template id="reset-password-template">
    <div class="panel">
        <div class="panel-head">
            修改密码
        </div>
        <div class="panel-content">
            <div class="input-group">
                <div class="input-label">
                    <label class="label">原密码</label>
                </div>
                <div class="input-block">
                    <input class="input" v-model="resetData.oldPassword" type="password"/>
                </div>
            </div>
            <div class="input-group">
                <div class="input-label">
                    <label class="label">新密码</label>
                </div>
                <div class="input-block">
                    <input class="input" v-model="resetData.password" type="password"/>
                </div>
            </div>
            <div class="input-group">
                <div class="input-label">
                    <label class="label">确认密码</label>
                </div>
                <div class="input-block">
                    <input class="input" v-model="resetData.confirmPassword" type="password"/>
                </div>
            </div>
            <div class="btn-group">
                <div class="right">
                    <a href="javascript:;" @click="updatePassword"
                       class="weui-btn weui-btn_mini weui-btn_default">提交</a>
                </div>
            </div>
            <div class="clear"></div>
        </div>
    </div>
</template>
<template id="user-message">
    <div class="panel">
        <div class="panel-head">
            我的消息
        </div>
        <div class="panel-content">
            <ul class="messages">
                <li class="message" v-for="message in messages">
                    <p>
                        <a href="" target="_blank" v-if="message.sender"><cite>{{message.sender.username}}</cite></a>
                        <span v-if="message.type == 'reply'">回复了你的评论</span>
                        <span v-if="message.type == 'comment'">评论了你的文章</span>
                    </p>
                    <div class="message-content" v-html="message.content"></div>
                </li>
            </ul>
        </div>
    </div>

</template>

<div id="app" class="container">
    <component :is="component"></component>

    <br/>
    <div style="text-align: center">
        <button class="weui-btn weui-btn_mini weui-btn_default" @click="backOrLogout">退出/返回</button>
    </div>
</div>
<#include "../base/footer.ftl" />
<script src="https://cdn.jsdelivr.net/npm/vue@2.6.0"></script>
<script type="text/javascript">
    Vue.component("center", {
        data: function () {
            return {
                items: [
                    {label: "我的文章", url: "javascript:", component: "posts"},
                    {label: "我的收藏", url: "javascript:", component: "collection"},
                    {label: "用户设置", url: "javascript:", component: "settings"},
                    {label: "修改密码", url: "javascript:", component: "reset-password"},
                    {label: "我的消息", url: "javascript:", component: "messages"}
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
                ]
            }
        },
        created: function () {
            let _this = this
            request({
                url: "/user/posts?type=my",
                success: function (res) {
                    console.log(res);
                    console.log(_this.posts);
                    _this.posts = res.data
                }
            })
        },
        template: "#posts-template"
    });
    Vue.component("collection", {
        data: function () {
            return {
                posts: []
            }
        },
        created: function () {
            let _this = this
            request({
                url: "/user/posts?type=collection",
                success: function (res) {
                    console.log(res);
                    console.log(_this.posts);
                    _this.posts = res.data
                }
            })
        },
        template: "#collection-template"
    });

    Vue.component("settings", {
        data: function () {
            return {
                user: {
                    email: "",
                    username: "",
                    city: "",
                    signature: "",
                    avatar: ""
                },
                avatar: "",
                avatarBg: ''
            }
        },
        created: function () {
            let _this = this
            request({
                url: '/user/info',
                success: function (res) {
                    if (res.code === 'success') {
                        _this.user = res.data
                        _this.avatarBg = 'background:url("' + _this.user.avatar + '")'
                    }
                }
            })
        },
        template: "#settings-template",
        methods: {
            submitForm: function () {
                let _this = this
                request({
                    url: "/user/updateInfo",
                    method: "POST",
                    data: _this.user
                })
            },
            uploadAvatar: function (event) {
                let _this = this;
                _this.avatar = event.target.files[0];
                let Form = new FormData();
                Form.append("avatar", _this.avatar);
                console.log(_this.avatar)

                uploadFile({
                    url: "/user/uploadAvatar",
                    data: Form,
                    success: function (res) {
                        if (typeof (res) == 'string') {
                            res = JSON.parse(res)
                        }
                        console.log(res)
                        _this.user = res.data;
                        _this.avatarBg = 'background:url("' + _this.user.avatar + '")'

                        console.log(_this.avatarBg)
                    }
                })
            }
        }
    });
    Vue.component("reset-password", {
        data: function () {
            return {
                resetData: {
                    oldPassword: "",
                    password: "",
                    confirmPassword: ""
                }
            }
        },
        template: "#reset-password-template",
        methods: {
            updatePassword: function () {
                let _this = this
                request({
                    url: "/user/updatePassword",
                    method: "POST",
                    data: _this.resetData
                })
            }
        }
    });

    Vue.component("messages", {
        template: "#user-message",
        data: function () {
            return {
                messages: []
            }
        },
        created: function () {
            this.requestMessages()
        },
        methods: {
            requestMessages: function () {
                let _this = this
                request({
                    url: "/userMessage",
                    success: function (resp) {
                        console.log(resp)
                        _this.messages = resp.data
                    }
                });
            }
        }
    })

    new Vue({
        el: "#app",
        data: {
            list: ['center'],
            component: "center"
        },
        methods: {
            loadComponent: function (component) {
                if (!component) {
                    return false;
                }
                this.component = component
                this.list.push(component)
                console.log(this.list)
            },
            backOrLogout: function () {
                if (this.component === "center") {
                    //退出
                    return window.location.href = "/user/logout";
                }
                this.list.pop();
                let component = this.list.pop();
                if (!component) {
                    component = "center";
                }
                this.component = component
            }
        }
    });
</script>
</body>
</html>