<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>注册</title>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <meta name="keywords" content="fly,layui,前端社区">
    <meta name="description" content="Fly社区是模块化前端UI框架Layui的官网社区，致力于为web开发提供强劲动力">
    <#include "../common/link.ftl" />
</head>
<body>
<#include "../common/header.ftl" />

<div id="register-container" class="layui-container fly-marginTop">
    <div class="fly-panel fly-panel-user" pad20>
        <div class="layui-tab layui-tab-brief">
            <ul class="layui-tab-title">
                <li><a href="/user/login">登入</a></li>
                <li class="layui-this">注册</li>
            </ul>
            <div class="layui-form layui-tab-content" style="padding: 20px 0;">
                <div class="layui-tab-item layui-show">
                    <div class="layui-form layui-form-pane">
                        <form method="post">
                            <div class="layui-form-item">
                                <label class="layui-form-label">邮箱</label>
                                <div class="layui-input-inline">
                                    <input type="text" name="email" v-model="registerForm.email" class="layui-input">
                                </div>
                                <div class="layui-form-mid layui-word-aux">将会成为您唯一的登入名</div>
                            </div>
                            <div class="layui-form-item">
                                <label class="layui-form-label">昵称</label>
                                <div class="layui-input-inline">
                                    <input type="text" name="username" v-model="registerForm.username"
                                           class="layui-input">
                                </div>
                            </div>
                            <div class="layui-form-item">
                                <label class="layui-form-label">密码</label>
                                <div class="layui-input-inline">
                                    <input type="password" name="password" v-model="registerForm.password"
                                           class="layui-input">
                                </div>
                                <div class="layui-form-mid layui-word-aux">6到16个字符</div>
                            </div>
                            <div class="layui-form-item">
                                <label class="layui-form-label">确认密码</label>
                                <div class="layui-input-inline">
                                    <input type="password" name="confirmPassword" v-model="registerForm.confirmPassword"
                                           class="layui-input">
                                </div>
                            </div>
                            <div class="layui-form-item">
                                <button type="button" class="layui-btn" v-on:click="registerSubmit">立即注册</button>
                            </div>
                            <div class="layui-form-item fly-form-app">
                                <div style="float: left;line-height: 33px;"><span>或者直接使用第三方账号快捷注册</span></div>
                                <div style="float: left;margin-left: 15px;display: inline-flex">
                                    <a href="/oauth/gitee/redirect" title="gitee登录"><i class="icon-gitee"></i></a>
                                    &nbsp; &nbsp;
                                    <a href="/oauth/github/redirect" title="github登录"><i class="icon-github"></i></a>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<#include "../common/footer.ftl" />
<script type="text/javascript">
    var app = Vue.createApp({
        data: () => ({
            registerForm: {
                email: "",
                username: "",
                password: "",
                confirmPassword: ""
            }
        }),
        methods: {
            registerSubmit: function (event) {
                axios.post('/user/register', this.registerForm)
                    .then(function (response) {
                        if (response.code === "success") {
                            //注册成功,转跳登录页面
                            layer.msg('注册成功,即将前往登录页面登录');
                            setTimeout(function () {
                                window.location.href = "/user/login";
                            }, 3000);
                            return;
                        }
                        layer.msg(response.message)
                    })
                    .catch(function (error) {
                        console.log(error);
                    });
            }
        }
    }).mount('#register-container');
</script>
</body>
</html>