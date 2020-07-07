<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta name="viewport"
          content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no">
    <meta charset='utf-8'>
    <title>详情页</title>
    <jsp:include page="../common/link.jsp"/>
</head>
<body>
<jsp:include page="../common/header.jsp"/>
<jsp:include page="../common/nav.jsp"/>

<div class="weui-panel weui-panel_access">
    <div class="weui-panel__hd">
        <h3>首页/详情</h3>
        <h1>文章标题</h1>
    </div>
    <div class="weui-panel__bd">
        <div class="container">
            文章内容
        </div>
    </div>
    <div class="weui-panel__ft">
        底部内容
    </div>
</div>

<jsp:include page="../common/footer.jsp"/>
</body>
</html>
