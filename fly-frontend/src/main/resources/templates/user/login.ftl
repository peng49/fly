<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>登入</title>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <meta name="keywords" content="fly,layui,前端社区">
    <meta name="description" content="Fly社区是模块化前端UI框架Layui的官网社区，致力于为web开发提供强劲动力">
    <#include "../common/link.ftl" />
</head>
<body>

<#include "../common/header.ftl" />

<div id="login-container" class="layui-container fly-marginTop">
    <div class="fly-panel fly-panel-user" pad20>
        <div class="layui-tab layui-tab-brief">
            <ul class="layui-tab-title">
                <li class="layui-this">登入</li>
                <li><a href="/user/register">注册</a></li>
            </ul>
            <div class="layui-form layui-tab-content" style="padding: 20px 0;">
                <div class="layui-tab-item layui-show">
                    <div class="layui-form layui-form-pane">
                        <form method="post">
                            <div class="layui-form-item">
                                <label class="layui-form-label">用户名/邮箱</label>
                                <div class="layui-input-inline">
                                    <input type="text"
                                           v-model="loginForm.username"
                                           class="layui-input">
                                </div>
                            </div>
                            <div class="layui-form-item">
                                <label class="layui-form-label">密码</label>
                                <div class="layui-input-inline">
                                    <input type="password"
                                           v-model="loginForm.password"
                                           @keyup.enter.native="loginSubmit"
                                           class="layui-input">
                                </div>
                            </div>
                            <div class="layui-form-item">
                                <button type="button" class="layui-btn" @click="loginSubmit">立即登录</button>
                                <span style="padding-left:20px;"><a href="/user/forget">忘记密码？</a></span>
                            </div>
                            <div class="layui-form-item fly-form-app">
                                <div style="float: left;line-height: 33px;">或者使用第三方账号登录</div>
                                <div style="float: left;margin-left: 15px;display: inline-flex">
                                    <a href="/oauth/gitee/redirect" title="gitee登录"><i class="icon-gitee"></i></a>
                                    &nbsp;  &nbsp;
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
    new Vue({
        el: "#login-container",
        data: {
            loginForm: {
                username: "",
                password: ""
            }
        },
        methods: {
            loginSubmit: function () {
                axios.post('/user/login', this.loginForm)
                    .then(function (response) {
                        if (response.code === "success") {
                            //注册成功,转跳登录页面
                            layer.msg('登录成功');
                            setTimeout(function () {
                                let redirect = '${redirect}';
                                if (redirect === '') {
                                    redirect = '/user/center'
                                }
                                window.location.href = redirect;
                            }, 500);
                            return;
                        }
                        layer.msg(response.message)
                    })
                    .catch(function (error) {
                        console.log(error);
                    });
            }
        }
    })
</script>
</body>
</html>