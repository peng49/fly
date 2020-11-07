<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>用户主页</title>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <meta name="keywords" content="用户主页">
    <meta name="description" content="用户主页">
    <#include "../common/link.ftl" />
</head>
<body style="margin-top: 65px;">

<#include "../common/header.ftl" />

<div class="fly-home fly-panel" style="background-image: url('http://');">
    <img src="${(user.avatar)!}" alt="${user.username}">
    <h1>
        ${user.username}
    </h1>


    <p class="fly-home-info">
        注册: <span>${user.createTime!''} </span>
        文章: <span style="color: #FF7200;">${user.experience!''}</span>
        回复: <span style="color: #FF7200;">${user.experience!''}</span>
    </p>

    <p class="fly-home-sign">（${user.signature!''}）</p>

    <div class="fly-sns" data-user="">
        <a href="javascript:;" class="layui-btn layui-btn-primary fly-imActive" data-type="addFriend">关注</a>
        <a href="javascript:;" class="layui-btn layui-btn-normal fly-imActive" data-type="chat">黑名单</a>
    </div>

</div>

<div class="layui-container">
    <div class="layui-row layui-col-space15">
        <div class="layui-col-md6 fly-home-jie">
            <div class="fly-panel">
                <h3 class="fly-panel-title">${user.username!''} 最近的提问</h3>
                <ul class="jie-row">
                    <#list posts as post>
                        <li>
                            <#if post.essence?? && post.essence == 1 >
                                <span class="fly-jing">精</span>
                            </#if>
                            <a href="/post/detail/${post.id?c}" class="jie-title">${post.title!''}</a>
                            <i>${post.publishAt!''}</i>
                            <em class="layui-hide-xs">${post.viewCount}阅/${post.replyCount}答</em>
                        </li>
                    <#else>
                        <li>无</li>
                    </#list>
                </ul>
            </div>
        </div>

        <div class="layui-col-md6 fly-home-da">
            <div class="fly-panel">
                <h3 class="fly-panel-title">${user.username} 最近的回答</h3>
                <ul class="home-jieda">
                    <#list comments as comment>
                        <li>
                            <p>
                                <span>${comment.createdAt}</span>
                                在<a href="/post/detail/${(comment.post.id?c)!}"
                                    target="_blank">${(comment.post.title)!}</a>中回答：
                            </p>
                            <div class="home-dacontent">
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
</div>

<#include "../common/footer.ftl" />
</body>
</html>