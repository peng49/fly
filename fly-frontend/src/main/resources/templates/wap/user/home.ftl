<!DOCTYPE html>
<html>
<head>
    <meta name="viewport"
          content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no">
    <meta charset='utf-8'>
    <title>用户中心</title>
    <#include "../base/link.ftl" />
</head>
<body>
<#include "../base/header.ftl" />
<#include "../base/nav.ftl" />
<div class="container">
    <div class="row home-user-info">
        <img src="${(user.avatar)!}" alt="${user.username}">
        <h1>
            ${user.username}
        </h1>

        <p class="home-info">
            经验值: <span style="color: #FF7200;">${user.experience!''}</span>
            注册时间: <span>${user.createTime!''}</span>
        </p>
    </div>

    <div class="panel">
        <div class="panel-head">
            <p>${user.username!''} 最近的提问</p>
        </div>
        <div class="panel-content">
            <ul class="user-posts">
                <#list posts as post>
                    <li class="user-post">
                        <#if post.essence?? && post.essence == 1 >
                            <span class="jing">精</span>
                        </#if>
                        <a href="/post/detail/${post.id?c}" class="title">${post.title}</a>
                        <i>${(post.publishAt)!}</i>
                        <em class="hide-xs">${post.viewCount}阅/${post.replyCount}答</em>
                    </li>
                <#else>
                    <li>无</li>
                </#list>
            </ul>
        </div>
    </div>

    <div class="panel">
        <div class="panel-head">
            <p>${user.username} 最近的回答</p>
        </div>
        <div class="panel-content">
            <ul class="user-comments">
                <#list comments as comment>
                    <li>
                        <p>
                            <span>${(comment.createdAt)!}</span>
                            在<a href="/post/detail/${comment.post.id}"><strong>${comment.post.title}</strong></a>中回答：
                        </p>
                        <div class="content">
                            ${comment.content}
                        </div>
                    </li>
                <#else>
                    <li>无</li>
                </#list>
            </ul>
        </div>
    </div>
</div>
<#include "../base/footer.ftl" />
</body>
</html>