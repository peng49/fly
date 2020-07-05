<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="fly.frontend.service.FriendLinkService" %>
<%@ page import="org.springframework.web.context.support.WebApplicationContextUtils" %>
<%@ page import="org.springframework.web.context.WebApplicationContext" %>
<%@ page import="fly.frontend.entity.FriendLink" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2020/6/27
  Time: 12:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="fly-panel fly-link">
    <h3 class="fly-panel-title">友情链接</h3>
    <%
        WebApplicationContext webApplicationContext = WebApplicationContextUtils.getWebApplicationContext(request.getSession().getServletContext());
        FriendLinkService friendLinkService = webApplicationContext.getBean(FriendLinkService.class);
        List<FriendLink> links = friendLinkService.getAll();
    %>
    <dl class="fly-panel-main">
        <% for (FriendLink link : links) {%>
        <dd>
            <a href="<%=link.getUrl()%>" target="_blank"><%=link.getName()%></a>
        </dd>
        <%}%>
        <dd>
            <%--<a href="" class="fly-link">申请友链</a>--%>
        </dd>
    </dl>
</div>
