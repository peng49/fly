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

    int step = 2;

    int pageCount = total / pageSize;

    int startPage = currentPage - step > 0 ? currentPage - step : 1;

    int endPage = currentPage + step < pageCount ? currentPage + step : pageCount;
%>
<div class="laypage-main">
    <% if (currentPage > 1) {%>
    <a href="?page=<%=currentPage-1%>&pageSize=<%=pageSize%>" class="laypage-prev">上一页</a>
    <%}%>

    <% if (currentPage != 1) {%>
    <a href="?page=1&pageSize=<%=pageSize%>" class="laypage-next">首页</a>
    <%}%>

    <% for (int i = startPage; i <= endPage; i++) {%>

    <% if (i == currentPage) {%>
    <span class="laypage-curr"><%=i%></span>
    <%} else {%>
    <a href="?pageSize=<%=pageSize%>&page=<%=i%>">
        <%=i%>
    </a>
    <%}%>
    <%}%>

    <% if (currentPage < pageCount) {%>
    <a href="?page=<%=pageCount%>&pageSize=<%=pageSize%>" class="laypage-last">尾页</a>
    <%}%>

    <% if (currentPage != pageCount && pageCount > currentPage) {%>
    <a href="?page=<%=currentPage+1%>&pageSize=<%=pageSize%>" class="laypage-next">下一页</a>
    <%}%>
</div>
