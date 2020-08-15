<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="org.springframework.web.context.support.WebApplicationContextUtils" %>
<%@ page import="fly.frontend.service.ColumnService" %>
<%@ page import="fly.frontend.entity.Column" %>
<%@ page import="org.springframework.web.context.WebApplicationContext" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2020/7/6
  Time: 17:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="nav-container">
    <div class="nav">
        <ul>
            <li class=""><a href="/">全部</a></li>
            <%
                WebApplicationContext webApplicationContext = WebApplicationContextUtils.getWebApplicationContext(request.getSession().getServletContext());
                ColumnService columnService = webApplicationContext.getBean(ColumnService.class);
                List<Column> columns = columnService.getAll();
            %>
            <% for (Column column : columns) {%>
                <li><a href=""><%=column.getName()%></a></li>
            <%}%>
        </ul>
        <a class="" href="/">发布</a></div>
</div>