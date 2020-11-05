<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>${column.name}</title>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <meta name="keywords" content="${column.name}">
    <meta name="description" content="${column.name}">
    <#include "../common/link.ftl" />
</head>
<body>
<#include "../common/header.ftl" />

<div class="layui-container">
    <div class="layui-row layui-col-space15">
        <div class="layui-col-md8">
            <div class="fly-panel" style="margin-bottom: 0;">

                <#include "../common/filter.ftl" />

                <ul class="fly-list">
                    <#list posts as post>
                        <li>
                            <a href="/u/${post.author.id}" class="fly-avatar">
                                <img src="${(post.author.avatar)!}" alt="${post.author.username}">
                            </a>
                            <h2>
                                <a class="layui-badge">${(post.column.name)!}</a>
                                <a href="/post/detail/${post.id}">${post.title}</a>
                            </h2>
                            <div class="fly-list-info">
                                <a href="/u/${post.author.id}" link>
                                    <cite>${post.author.username}</cite>
                                </a>
                                <span>${(post.publishAt)!}</span>

                                <span class="fly-list-nums">
                                    <i class="iconfont icon-pinglun1" title="回答"></i> ${post.replyCount}
                                </span>
                            </div>
                            <div class="fly-list-badge">
                                <#if post.top?? && post.top == 1 >
                                    <span class="layui-badge layui-bg-black">置顶</span>
                                </#if>
                                <#if post.essence?? && post.essence == 1>
                                    <span class="layui-badge layui-bg-red">精</span>
                                </#if>
                            </div>
                        </li>
                    </#list>
                </ul>
                <div style="text-align: center">
                    <#include "../common/pager.ftl" />
                </div>

            </div>
        </div>
        <div class="layui-col-md4">
            <#include "../common/slider/sign-in.ftl"/>

            <#include "../common/slider/top-comment.ftl" />

            <#include "../common/slider/ad.ftl" />

            <#include "../common/slider/friend-link.ftl" />
        </div>
    </div>
</div>

<#include "../common/footer.ftl" />

</body>
</html>