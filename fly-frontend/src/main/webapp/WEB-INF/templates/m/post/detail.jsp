<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta name="viewport"
          content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no">
    <meta charset='utf-8'>
    <title>详情页</title>
    <link rel="stylesheet" href="/static/editor.md/css/editormd.min.css"/>
    <jsp:include page="../common/link.jsp"/>
</head>
<body>
<jsp:include page="../common/header.jsp"/>
<jsp:include page="../common/nav.jsp"/>

<div class="panel">
    <div class="panel-head">
        <p class="breadcrumb"><span>首页</span> / <a href="">详情</a></p>
        <h1 class="title">${post.title}</h1>
    </div>
    <div class="panel-content">
        <div class="markdown-body editormd-preview-container">
            ${post.content}
        </div>
    </div>
    <div class="panel-footer">
        <span><a class="column" href="">${post.column.name}</a></span>
        <span class="point">•</span>
        <span><a class="author" href="">${post.author.username}</a></span>
        <span class="point">•</span>
        <span><time>6分钟前</time></span>
    </div>
</div>

<div class="panel">
    <div class="panel-head">
        <p>共1条评论</p>
    </div>
    <div class="panel-content">
        <ul class="comments">
            <c:forEach items="${comments}" var="comment">
                <li class="item">
                    <div class="avatar">
                        <a href="">
                            <img src="${comment.user.avatar}">
                        </a>
                    </div>
                    <div class="content">
                        <div class="comment-head">
                        <span class="username">
                            <a href="">${comment.user.username}</a>
                        </span>
                            <span>
                            <time date="" title="">4 天前</time>
                        </span>
                            <span class="right">#1</span>
                        </div>
                        <div class="comment">
                            <c:if test="${comment.parent != null}">
                                <div class="comment-parent">
                                    <div class="a">引用 @${comment.parent.user.username} 的回复</div>
                                    <div>
                                            ${comment.parent.content}
                                    </div>
                                </div>
                            </c:if>
                                ${comment.content}
                        </div>
                    </div>
                </li>
            </c:forEach>
        </ul>
    </div>
</div>

<jsp:include page="../common/footer.jsp"/>
</body>
</html>
