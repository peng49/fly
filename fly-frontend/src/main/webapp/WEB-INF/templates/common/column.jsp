<%@ page import="fly.frontend.entity.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="fly-panel fly-column">
    <div class="layui-container">
        <ul class="layui-clear">
            <li class="layui-hide-xs layui-this"><a href="/">首页</a></li>
            <li><a href="/category/1">提问</a></li>
            <li><a href="/category/1">分享<span class="layui-badge-dot"></span></a></li>
            <li><a href="/category/1">讨论</a></li>
            <li><a href="/category/1">建议</a></li>
            <li><a href="/category/1">公告</a></li>
            <li><a href="/category/1">动态</a></li>
            <li class="layui-hide-xs layui-hide-sm layui-show-md-inline-block"><span class="fly-mid"></span></li>

            <% User user = (User) request.getSession().getAttribute("login-user");%>
            <% if (user != null) {%>

            <!-- 用户登入后显示 -->
            <li class="layui-hide-xs layui-hide-sm layui-show-md-inline-block">
                <a href="user/home">我发表的贴</a>
            </li>
            <li class="layui-hide-xs layui-hide-sm layui-show-md-inline-block">
                <a href="user/home#collection">我收藏的贴</a></li>
            <% } %>
        </ul>

        <div class="fly-column-right layui-hide-xs">
            <span class="fly-search"><i class="layui-icon"></i></span>
            <a href="/post/add" class="layui-btn">发表新帖</a>
        </div>
        <div class="layui-hide-sm layui-show-xs-block"
             style="margin-top: -10px; padding-bottom: 10px; text-align: center;">
            <a href="/post/add" class="layui-btn">发表新帖</a>
        </div>
    </div>
</div>