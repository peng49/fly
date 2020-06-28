<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="org.springframework.web.context.support.WebApplicationContextUtils" %>
<%@ page import="org.springframework.web.context.WebApplicationContext" %>
<%@ page import="fly.frontend.service.PostService" %>
<%@ page import="fly.frontend.entity.Post" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2020/6/27
  Time: 12:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<dl class="fly-panel fly-list-one">
    <%
        WebApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(request.getServletContext());
        PostService postService = context.getBean(PostService.class);
        List<Post> posts = postService.getEveryWeekCommentMax(10);
        request.setAttribute("weekMaxCommentPosts", posts);
    %>
    <dt class="fly-panel-title">本周热议</dt>
    <c:choose>
        <c:when test="${weekMaxCommentPosts.size() > 0}">
            <c:forEach items="${weekMaxCommentPosts}" var="post">
                <dd>
                    <a href="/post/detail/${post.id}">${post.title}</a>
                    <span><i class="iconfont icon-pinglun1"></i> ${post.replyCount}</span>
                </dd>
            </c:forEach>
        </c:when>
        <c:otherwise>
            <div class="fly-none">没有相关数据</div>
        </c:otherwise>
    </c:choose>
</dl>
