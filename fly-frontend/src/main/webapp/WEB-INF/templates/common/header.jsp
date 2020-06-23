<%@ page import="fly.frontend.entity.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="fly-header layui-bg-black">
    <div class="layui-container">
        <a class="fly-logo" href="/">
            <img src="/static/images/logo.png" alt="layui">
        </a>
        <ul class="layui-nav fly-nav layui-hide-xs">
            <li class="layui-nav-item layui-this">
                <a href="/"><i class="iconfont icon-jiaoliu"></i>交流</a>
            </li>
            <li class="layui-nav-item">
                <a href="javascript:"><i class="iconfont icon-iconmingxinganli"></i>案例</a>
            </li>
            <li class="layui-nav-item">
                <a href="javascript:" target="_blank"><i class="iconfont icon-ui"></i>框架</a>
            </li>
        </ul>

        <ul class="layui-nav fly-nav-user">
            <% User user = (User) request.getSession().getAttribute("login-user");%>
            <% if (user == null) { %>
            <!-- 未登入的状态 -->
            <li class="layui-nav-item">
                <a class="iconfont icon-touxiang layui-hide-xs" href="/user/login"></a>
            </li>
            <li class="layui-nav-item">
                <a href="/user/login">登入</a>
            </li>
            <li class="layui-nav-item">
                <a href="/user/register">注册</a>
            </li>
            <li class="layui-nav-item layui-hide-xs">
                <a href="" onclick="layer.msg('正在通过QQ登入', {icon:16, shade: 0.1, time:0})" title="QQ登入"
                   class="iconfont icon-qq"></a>
            </li>
            <li class="layui-nav-item layui-hide-xs">
                <a href="" onclick="layer.msg('正在通过微博登入', {icon:16, shade: 0.1, time:0})" title="微博登入"
                   class="iconfont icon-weibo"></a>
            </li>
            <%} else {%>
            <!-- 登入后的状态 -->
            <li class="layui-nav-item">
                <a class="fly-nav-avatar" href="javascript:;">
                    <cite class="layui-hide-xs"><%=user.getUsername()%></cite>
                    <%--<i class="iconfont icon-renzheng layui-hide-xs" title=""></i>--%>
                    <%--<i class="layui-badge fly-badge-vip layui-hide-xs">VIP3</i>--%>
                    <img src="https://tva1.sinaimg.cn/crop.0.0.118.118.180/5db11ff4gw1e77d3nqrv8j203b03cweg.jpg">
                </a>
                <dl class="layui-nav-child">
                    <dd><a href="/user/home#user-setting"><i class="layui-icon">&#xe620;</i>基本设置</a></dd>
                    <dd><a href="/user/home#user-message"><i class="iconfont icon-tongzhi" style="top: 4px;"></i>我的消息</a></dd>
                    <dd><a href="/user/home"><i class="layui-icon" style="margin-left: 2px; font-size: 22px;">&#xe68e;</i>我的主页</a>
                    </dd>
                    <hr style="margin: 5px 0;">
                    <dd><a href="/user/logout/" style="text-align: center;">退出</a></dd>
                </dl>
            </li>
            <%}%>
        </ul>
    </div>
</div>