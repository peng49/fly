<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2020/6/25
  Time: 10:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    int total = Integer.valueOf(request.getParameter("total"));
    int currentPage = 1;
    if (request.getParameter("page") != null) {
        currentPage = Integer.valueOf(request.getParameter("page"));
    }
    int pageSize = 3;
    if (request.getParameter("pageSize") != null) {
        pageSize = Integer.valueOf(request.getParameter("pageSize"));
    }
    pageSize = pageSize > 0 ? pageSize : 3;

    int pageCount = total / pageSize;
%>
<div class="laypage-main">
    <% if (currentPage > 1) {%>
    <a href="?page=<%=currentPage-1%>&pageSize=<%=pageSize%>" class="laypage-prev">上一页</a>
    <%}%>

    <% for (int i = 1; i <= pageCount; i++) {%>
    <a href="?pageSize=<%=pageSize%>&page=<%=i%>"><%=i%>
    </a>

    <%}%>
    <% if (currentPage != 1) {%>
    <a href="?page=1&pageSize=<%=pageSize%>" class="laypage-next">首页</a>
    <%}%>

    <% if (currentPage != pageCount) {%>
    <a href="?page=<%=currentPage+1%>&pageSize=<%=pageSize%>" class="laypage-next">下一页</a>
    <%}%>
</div>
