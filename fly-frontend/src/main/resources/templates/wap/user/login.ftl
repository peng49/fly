<!DOCTYPE html>
<html>
<head>
    <meta name="viewport"
          content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no">
    <meta charset='utf-8'>
    <title>登录</title>
    <#include "../base/link.ftl" />
</head>
<body>
<#include "../base/header.ftl" />
<#include "../base/nav.ftl" />
<div class="container">
    <div class="panel">
        <div class="panel-head">
            登录
        </div>
        <div class="panel-content">
            <div class="form">
                <div class="input-group">
                    <div class="input-label">
                        <label class="label">用户名</label>
                    </div>
                    <div class="input-block">
                        <input class="input" type="text"/>
                    </div>
                </div>
                <div class="input-group">
                    <div class="input-label">
                        <label class="label">密码</label>
                    </div>
                    <div class="input-block">
                        <input class="input" type="text"/>
                    </div>
                </div>
                <div class="btn-group">
                    <div class="right">
                        <span class="btn-left-text">没有账号？前往 <a href="/user/register">注册</a></span>
                        <a href="javascript:;" class="weui-btn weui-btn_mini weui-btn_default">登录</a>
                    </div>
                </div>
                <div class="clear"></div>
            </div>
        </div>
    </div>
</div>
</body>
</html>