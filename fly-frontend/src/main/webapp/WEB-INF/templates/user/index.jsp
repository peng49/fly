<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>用户主页</title>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <meta name="keywords" content="fly,layui,前端社区">
    <meta name="description" content="Fly社区是模块化前端UI框架Layui的官网社区，致力于为web开发提供强劲动力">
    <jsp:include page="../common/link.jsp"/>
</head>
<body style="margin-top: 65px;">

<jsp:include page="../common/header.jsp"/>

<div class="fly-home fly-panel" style="background-image: url('http://');">
    <img src="${user.avatar}" alt="${user.username}">
    <%--    <i class="iconfont icon-renzheng" title=""></i>--%>
    <h1>
        ${user.username}
        <%--<i class="iconfont icon-nan"></i>--%>
        <!-- <i class="iconfont icon-nv"></i>  -->
        <%--<i class="layui-badge fly-badge-vip">VIP3</i>--%>
        <!--
        <span style="color:#c00;">（管理员）</span>
        <span style="color:#5FB878;">（社区之光）</span>
        <span>（该号已被封）</span>
        -->
    </h1>

    <%--<p style="padding: 10px 0; color: #5FB878;">认证信息：layui 作者</p>--%>

    <p class="fly-home-info">
        经验值: <span style="color: #FF7200;">${user.experience}</span>
        <i class="iconfont icon-shijian"></i><span>${user.createTime} 加入</span>
        <i class="iconfont icon-chengshi"></i><span>来自${user.city}</span>
    </p>

    <p class="fly-home-sign">（${user.signature}）</p>

    <div class="fly-sns" data-user="">
        <a href="javascript:;" class="layui-btn layui-btn-primary fly-imActive" data-type="addFriend">加为好友</a>
        <a href="javascript:;" class="layui-btn layui-btn-normal fly-imActive" data-type="chat">发起会话</a>
    </div>

</div>

<div class="layui-container">
    <div class="layui-row layui-col-space15">
        <div class="layui-col-md6 fly-home-jie">
            <div class="fly-panel">
                <h3 class="fly-panel-title">${user.username} 最近的提问</h3>
                <ul class="jie-row">
                    <c:choose>
                        <c:when test="${posts.size() > 0}">
                            <c:forEach items="${posts}" var="post">
                                <li>
                                    <span class="fly-jing">精</span>
                                    <a href="/post/detail/${post.id}" class="jie-title">${post.title}</a>
                                    <i>${post.publishAt}</i>
                                    <em class="layui-hide-xs">${post.viewCount}阅/${post.replyCount}答</em>
                                </li>
                            </c:forEach>
                        </c:when>
                        <c:otherwise>
                            <div class="fly-none" style="min-height: 50px; padding:30px 0; height:auto;">
                                <i style="font-size:14px;">没有发表任何求解</i>
                            </div>
                        </c:otherwise>
                    </c:choose>
                </ul>
            </div>
        </div>

        <div class="layui-col-md6 fly-home-da">
            <div class="fly-panel">
                <h3 class="fly-panel-title">${user.username} 最近的回答</h3>
                <ul class="home-jieda">
                    <c:choose>
                        <c:when test="${comments.size() > 0}">
                            <f:forEach items="${comments}" var="comment">
                                <li>
                                    <p>
                                        <span>${comment.commentTime}</span>
                                        在<a href="/post/detail/${comment.post.id}" target="_blank">${comment.post.title}</a>中回答：
                                    </p>
                                    <div class="home-dacontent">
                                        ${comment.content}
                                    </div>
                                </li>
                            </f:forEach>
                        </c:when>
                        <c:otherwise>
                            <div class="fly-none" style="min-height: 50px; padding:30px 0; height:auto;">
                                <span>没有回答任何问题</span>
                            </div>
                        </c:otherwise>
                    </c:choose>
                </ul>
            </div>
        </div>
    </div>
</div>

<jsp:include page="../common/footer.jsp"/>
</body>
</html>