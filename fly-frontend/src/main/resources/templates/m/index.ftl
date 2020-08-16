<html>
<head>
    <meta name="viewport"
          content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no">
    <meta charset='utf-8'>
    <title>首页</title>
    <#include "common/link.ftl "/>
</head>
<body>
<#include "common/header.ftl "/>
<#include "common/nav.ftl "/>

<div class="weui-panel weui-panel_access">
    <div class="weui-panel__hd"><h3 class="black">全部</h3></div>
    <ul class="weui-panel__bd">
        <#list posts as post>
            <li>
                <div class="item">
                    <div class="avatar">
                        <a href="">
                            <img src="${(post.author.avatar)!}">
                        </a>
                    </div>
                    <div class="content">
                        <div class="title">
                            <a href="/m/p/${post.id}">${post.title}</a>
                        </div>
                        <div class="bottom">
                            <span><a class="column" href="">${(post.column.name)!}</a></span>
                            <span class="point">•</span>
                            <span><a class="author" href="">${(post.author.username)!}</a></span>
                            <span class="point">•</span>
                            <span><time>3分钟前</time></span>
                        </div>
                    </div>
                    <div class="right">
                        <a class="number" href="javascript:">${post.replyCount}</a>
                    </div>
                </div>
            </li>
        </#list>
    </ul>
    <div class="weui-panel__ft">
        <a href="javascript:void(0);" class="weui-cell weui-cell_access weui-cell_link">
            <div class="weui-cell__bd">下一页</div>
            <span class="weui-cell__ft"></span>
        </a>
    </div>
</div>
<#include "common/footer.ftl "/>
</body>
</html>
