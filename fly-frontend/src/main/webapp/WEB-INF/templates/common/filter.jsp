<%@ page import="fly.frontend.utils.UrlUtils" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="fly-panel-title fly-filter">
    <a href="?<%=UrlUtils.setParameter(request,"list","all")%>"
       class="<%=(request.getParameter("list") == null || "all".equals(request.getParameter("list")))?"layui-this":""%>">全部</a>
    <span class="fly-mid"></span>
    <a href="?<%=UrlUtils.setParameter(request,"list","essence")%>"
       class="<%="essence".equals(request.getParameter("list"))?"layui-this":""%>">精华</a>
    <span class="fly-filter-right layui-hide-xs">
    <a href="?<%=UrlUtils.setParameter(request,"orderBy","publishAt")%>"
       class="<%="publishAt".equals(request.getParameter("orderBy"))?"layui-this":""%>">按最新</a>
    <span class="fly-mid"></span>
    <a href="?<%=UrlUtils.setParameter(request,"orderBy","replyCount")%>"
       class="<%="replyCount".equals(request.getParameter("orderBy"))?"layui-this":""%>">按热议</a>
  </span>
</div>