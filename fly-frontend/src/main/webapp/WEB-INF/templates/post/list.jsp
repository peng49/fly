<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>基于 layui 的极简社区页面模版</title>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <meta name="keywords" content="fly,layui,前端社区">
    <meta name="description" content="Fly社区是模块化前端UI框架Layui的官网社区，致力于为web开发提供强劲动力">
    <jsp:include page="../common/link.jsp"/>
</head>
<body>
<jsp:include page="../common/header.jsp"/>

<jsp:include page="../common/column.jsp"/>

<div class="layui-container">
    <div class="layui-row layui-col-space15">
        <div class="layui-col-md8">
            <div class="fly-panel" style="margin-bottom: 0;">

                <jsp:include page="../common/filter.jsp"/>

                <ul class="fly-list">
                    <c:forEach items="${posts}" var="post">
                        <li>
                            <a href="/user/index/${post.author.id}" class="fly-avatar">
                                <img src="${post.author.avatar}" alt="${post.author.username}">
                            </a>
                            <h2>
                                <a class="layui-badge">分享</a>
                                <a href="/post/detail/${post.id}">${post.title}</a>
                            </h2>
                            <div class="fly-list-info">
                                <a href="/user/index/${post.author.id}" link>
                                    <cite>${post.author.username}</cite>
                                </a>
                                <span>刚刚</span>

                                <span class="fly-list-nums">
                                    <i class="iconfont icon-pinglun1" title="回答"></i> ${post.replyCount}
                                </span>
                            </div>
                            <div class="fly-list-badge">
                                <c:if test="${post.top == 1}">
                                    <span class="layui-badge layui-bg-black">置顶</span>
                                </c:if>
                                <c:if test="${post.essence == 1}">
                                    <span class="layui-badge layui-bg-red">精</span>
                                </c:if>
                            </div>
                        </li>
                    </c:forEach>
                </ul>
                <div style="text-align: center">
                    <jsp:include page="../common/pager.jsp">
                        <jsp:param name="total" value="${posts.getTotal()}"/>
                    </jsp:include>
                </div>

            </div>
        </div>
        <div class="layui-col-md4">
            <jsp:include page="../common/slider/top-comment.jsp"/>

            <div class="fly-panel">
                <div class="fly-panel-title">
                    这里可作为广告区域
                </div>
                <div class="fly-panel-main">
                    <a href="" target="_blank" class="fly-zanzhu" style="background-color: #393D49;">虚席以待</a>
                </div>
            </div>

            <jsp:include page="../common/slider/friend-link.jsp"/>
        </div>
    </div>
</div>

<jsp:include page="../common/footer.jsp"/>

</body>
</html>