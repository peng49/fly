<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta name="viewport"
          content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no">
    <meta charset='utf-8'>
    <title>首页</title>
    <jsp:include page="common/link.jsp"/>
</head>
<body>
<jsp:include page="common/header.jsp"/>
<jsp:include page="common/nav.jsp"/>

<div class="weui-panel weui-panel_access">
    <div class="weui-panel__hd"><h3 class="black">全部</h3></div>
    <ul class="weui-panel__bd">
        <li>
            <div class="item">
                <div class="avatar">
                    <a href="">
                        <img src="https://img.boxopened.com/avatars/x129s291i.png">
                    </a>
                </div>
                <div class="content">
                    <div class="title">
                        <a href="/mobile/post/view.jsp">title</a>
                    </div>
                    <div class="bottom">
                        <span><a class="column" href="">column</a></span>
                        <span class="point">•</span>
                        <span><a class="author" href="">author</a></span>
                        <span class="point">•</span>
                        <span><time>time</time></span>
                    </div>
                </div>
                <div class="right">
                    <a class="number" href="">0</a>
                </div>
            </div>
        </li>
    </ul>
    <div class="weui-panel__ft">
        <a href="javascript:void(0);" class="weui-cell weui-cell_access weui-cell_link">
            <div class="weui-cell__bd">下一页</div>
            <span class="weui-cell__ft"></span>
        </a>
    </div>
</div>

<jsp:include page="common/footer.jsp"/>
</body>
</html>
