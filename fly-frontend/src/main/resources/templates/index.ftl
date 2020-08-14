<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>基于 layui 的极简社区页面模版</title>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <meta name="keywords" content="fly,layui,前端社区">
    <meta name="description" content="Fly社区是模块化前端UI框架Layui的官网社区，致力于为web开发提供强劲动力">

    <#include "common/link.ftl"/>

</head>
<body>
<#include "common/header.ftl"/>

<#include "common/column.ftl"/>

<div class="layui-container">
    <div class="layui-row layui-col-space15">
        <div class="layui-col-md8">
            <div class="fly-panel">
                <div class="fly-panel-title fly-filter">
                    <a>置顶</a>
                    <a href="#signin" class="layui-hide-sm layui-show-xs-block fly-right" id="LAY_goSignin"
                       style="color: #FF5722;">去签到</a>
                </div>
                <ul class="fly-list">
                    <#list topPosts as post>
                        <li>
                            <a href="/user/index/${post.author.id}" class="fly-avatar">
                                <img src="${post.author.avatar}" alt="${post.author.username}">
                            </a>
                            <h2>
                                <a class="layui-badge">${post.column.name}</a>
                                <a href="/post/detail/${post.id}">${post.title}</a>
                            </h2>
                            <div class="fly-list-info">
                                <a href="user/${post.author.id}" link>
                                    <cite>${post.author.username}</cite>
                                </a>
                                <span>${post.publishAt}</span>
                                <span class="fly-list-nums">
                                    <i class="iconfont icon-pinglun1" title="回答"></i> ${post.replyCount}
                                </span>
                            </div>
                            <div class="fly-list-badge">
                                <#if post.top == 1>
                                    <span class="layui-badge layui-bg-black">置顶</span>
                                </#if>
                                <#if post.essence == 1>
                                    <span class="layui-badge layui-bg-red">精</span>
                                </#if>
                            </div>
                        </li>
                    </#list>
                </ul>
            </div>

            <div class="fly-panel" style="margin-bottom: 0;">

                <#include "common/filter.ftl"/>
                <ul class="fly-list">
                    <#list posts as post>
                        <li>
                            <a href="/user/index/${post.author.id}" class="fly-avatar">
                                <img src="${post.author.avatar}" alt="${post.author.username}">
                            </a>
                            <h2>
                                <a class="layui-badge">${post.column.name}</a>
                                <a href="/post/detail/${post.id}">${post.title}</a>
                            </h2>
                            <div class="fly-list-info">
                                <a href="user/${post.author.id}" link>
                                    <cite>${post.author.username}</cite>
                                </a>
                                <span>${post.publishAt}</span>
                                <span class="fly-list-nums">
                                    <i class="iconfont icon-pinglun1" title="回答"></i> ${post.replyCount}
                                </span>
                            </div>
                            <div class="fly-list-badge">
                                <#if post.top == 1>
                                    <span class="layui-badge layui-bg-black">置顶</span>
                                </#if>
                                <#if post.essence == 1>
                                    <span class="layui-badge layui-bg-red">精</span>
                                </#if>
                            </div>
                        </li>
                    </#list>
                </ul>
                <div style="text-align: center">
                    <div class="laypage-main">
                        <a href="javascript:" class="laypage-next">更多求解</a>
                    </div>
                </div>
            </div>
        </div>
        <div class="layui-col-md4">
            <div class="fly-panel">
                <h3 class="fly-panel-title">温馨通道</h3>
                <ul class="fly-panel-main fly-list-static">
                    <li>
                        <a href="http://fly.layui.com/jie/4281/" target="_blank">
                            layui 的 GitHub 及 Gitee (码云) 仓库，欢迎Star
                        </a>
                    </li>
                </ul>
            </div>

            <#include "common/slider/sign-in.ftl"/>

            <#include "common/slider/top-comment-user.ftl"/>

            <#include "common/slider/top-comment.ftl"/>

            <#include "common/slider/ad.ftl"/>

            <#include "common/slider/friend-link.ftl"/>
        </div>
    </div>
</div>


</body>
</html>