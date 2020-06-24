<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>登入</title>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <meta name="keywords" content="fly,layui,前端社区">
    <meta name="description" content="Fly社区是模块化前端UI框架Layui的官网社区，致力于为web开发提供强劲动力">
    <jsp:include page="../common/link.jsp" />
</head>
<body>

<jsp:include page="../common/header.jsp"/>

<div id="login-container" class="layui-container fly-marginTop">
    <div class="fly-panel fly-panel-user" pad20>
        <div class="layui-tab layui-tab-brief" lay-filter="user">
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
                                    <input type="text" v-model="loginForm.username" class="layui-input">
                                </div>
                            </div>
                            <div class="layui-form-item">
                                <label class="layui-form-label">密码</label>
                                <div class="layui-input-inline">
                                    <input type="password" v-model="loginForm.password" @keyup.enter="loginSubmit" class="layui-input">
                                </div>
                            </div>
                            <%--                            <div class="layui-form-item">
                                                            <label for="L_vercode" class="layui-form-label">人类验证</label>
                                                            <div class="layui-input-inline">
                                                                <input type="text" id="L_vercode" name="vercode" required lay-verify="required"
                                                                       placeholder="请回答后面的问题" autocomplete="off" class="layui-input">
                                                            </div>
                                                            <div class="layui-form-mid">
                                                                <span style="color: #c00;">{{d.vercode}}</span>
                                                            </div>
                                                        </div>--%>
                            <div class="layui-form-item">
                                <button type="button" class="layui-btn" v-on:click="loginSubmit">立即登录</button>
                                <span style="padding-left:20px;"><a href="forget.html">忘记密码？</a></span>
                            </div>
                            <div class="layui-form-item fly-form-app">
                                <span>或者使用社交账号登入</span>
                                <a href="" onclick="layer.msg('正在通过QQ登入', {icon:16, shade: 0.1, time:0})"
                                   class="iconfont icon-qq" title="QQ登入"></a>
                                <a href="" onclick="layer.msg('正在通过微博登入', {icon:16, shade: 0.1, time:0})"
                                   class="iconfont icon-weibo" title="微博登入"></a>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<jsp:include page="../common/footer.jsp"/>
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
                                window.location.href = "/user/home";
                            }, 1500);
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