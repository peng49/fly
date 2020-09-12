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
            找回密码
        </div>
        <div class="panel-content">
            <div class="form">
                <form action="">
                    <div class="input-group">
                        <div class="input-label">
                            <label class="label">邮箱</label>
                        </div>
                        <div class="input-block">
                            <input class="input" name="email" type="email" />
                        </div>
                    </div>
                    <div class="btn-group">
                        <div class="right">
                            <a href="javascript:;" class="weui-btn weui-btn_mini weui-btn_default">提交</a>
                        </div>
                    </div>
                </form>
                <div class="clear"></div>
            </div>
        </div>
    </div>
</div>
<#include "../base/footer.ftl" />
</body>
</html>