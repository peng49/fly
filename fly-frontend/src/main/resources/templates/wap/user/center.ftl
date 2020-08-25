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
<div class="container">
    <div class="panel">
        <div class="panel-head">
            <p>个人中心</p>
        </div>
        <div class="cells">
            <a class="weui-cell weui-cell_access" href="javascript:;">
                <div class="weui-cell__bd">
                    <p>我的文章</p>
                </div>
                <div class="weui-cell__ft">
                </div>
            </a>
            <a class="weui-cell weui-cell_access" href="javascript:;">
                <div class="weui-cell__bd">
                    <p>我的收藏</p>
                </div>
                <div class="weui-cell__ft">
                </div>
            </a>
            <a class="weui-cell weui-cell_access" href="javascript:;">
                <div class="weui-cell__bd">
                    <p>用户设置</p>
                </div>
                <div class="weui-cell__ft">
                </div>
            </a>
            <a class="weui-cell weui-cell_access" href="javascript:;">
                <div class="weui-cell__bd">
                    <p>我的消息</p>
                </div>
                <div class="weui-cell__ft">
                </div>
            </a>
            <a class="weui-cell weui-cell_access" href="/user/logout">
                <div class="weui-cell__bd">
                    <p>退出</p>
                </div>
                <div class="weui-cell__ft">
                </div>
            </a>
        </div>
    </div>
</div>
</body>
</html>