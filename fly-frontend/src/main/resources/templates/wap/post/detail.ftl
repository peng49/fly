<html>
<head>
    <meta name="viewport"
          content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no">
    <meta charset='utf-8'>
    <title>详情页</title>
    <link rel="stylesheet" href="/static/editor.md/css/editormd.min.css"/>
    <#include "../base/link.ftl" />
</head>
<body>
<#include "../base/header.ftl" />
<#include "../base/nav.ftl" />

<div class="panel">
    <div class="panel-head">
        <p class="breadcrumb"><span>首页</span> / <a href="">详情</a></p>
        <h1 class="title">${post.title!}</h1>
    </div>
    <div class="panel-content">
        <div class="markdown-body editormd-preview-container">
            ${post.content!}
        </div>
    </div>
    <div class="panel-footer">
        <span><a class="column" href="">${(post.column.name)!}</a></span>
        <span class="point">•</span>
        <span><a class="author" href="">${(post.author.username)!}</a></span>
        <span class="point">•</span>
        <span><time>6分钟前</time></span>
    </div>
</div>

<div class="panel">
    <div class="panel-head">
        <p>共1条评论</p>
    </div>
    <div class="panel-content">
        <ul class="comments">
            <#list comments as comment>
                <li class="item">
                    <div class="avatar">
                        <a href="">
                            <img src="${comment.user.avatar}">
                        </a>
                    </div>
                    <div class="content">
                        <div class="comment-head">
                        <span class="username">
                            <a href="">${comment.user.username}</a>
                        </span>
                            <span>
                            <time date="" title="">4 天前</time>
                        </span>
                            <span class="right">#1</span>
                        </div>
                        <div class="comment">
                            <#if (comment.parent)??>
                                <div class="comment-parent">
                                    <div class="a">引用 @${(comment.parent.user.username)!} 的回复</div>
                                    <div>
                                        ${(comment.parent.content)!}
                                    </div>
                                </div>
                            </#if>
                            ${comment.content}
                        </div>
                    </div>
                </li>
            <#else>
            <#--暂无评论-->
            </#list>
        </ul>
    </div>
</div>

<#include "../base/footer.ftl"/>
</body>
</html>
