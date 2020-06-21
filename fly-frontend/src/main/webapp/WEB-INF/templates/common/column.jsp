<%@ page import="fly.frontend.entity.User" %>
<%@ page import="java.util.List" %>
<%@ page import="fly.frontend.entity.Column" %>
<%@ page import="fly.frontend.service.ColumnService" %>
<%@ page import="org.springframework.web.context.support.WebApplicationContextUtils" %>
<%@ page import="org.springframework.web.context.WebApplicationContext" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="fly-panel fly-column">
    <div class="layui-container">
        <ul class="layui-clear">
            <li class="layui-hide-xs layui-this"><a href="/">首页</a></li>
            <%
                WebApplicationContext webApplicationContext = WebApplicationContextUtils.getWebApplicationContext(request.getSession().getServletContext());
                ColumnService columnService = webApplicationContext.getBean(ColumnService.class);
                List<Column> columns = columnService.getAll();
            %>

            <% for (Column column : columns) {%>
            <li>
                <a href="/column/<%=column.getId()%>">
                    <%=column.getName()%>
                </a>
            </li>
            <%}%>
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