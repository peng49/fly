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

<div class="post-box">
    <div class="post-detail">
        <h1 class="title">${post.title!}</h1>

        <div class="post-info">
            <span class="column">${(post.column.name)!}</span>

            <span class="post-nums" style="position: absolute;right: 0;font-size: 16px;">
                <a href="javascript:"><i title="回复" class="iconfont"></i> ${(post.replyCount)!0}</a>
                <i title="点击" class="iconfont"></i> ${(post.viewCount)!0}
            </span>
        </div>
        <div class="post-about">
            <a href="/u/${(post.author.id)!}" class="avatar">
                <img src="${(post.author.avatar)!''}" alt="knight">
            </a>
            <div class="post-author">
                <a href="/u/${(post.author.id)!}" class="link">
                    <cite>${(post.author.username)!}</cite>
                </a>
                <span>${post.publishAt}</span>
            </div>
            <div class="post-hits">
                &nbsp;
            </div>
        </div>

        <div class="markdown-body editormd-preview-container post-content">
            ${post.content!}
        </div>
    </div>
</div>

<div class="panel">
    <div class="panel-head">
        <p>共${comments?size}条评论</p>
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
                <li>暂无评论</li>
            </#list>
        </ul>
    </div>
</div>

<#include "../base/footer.ftl"/>
</body>
</html>
