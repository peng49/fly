<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta name="viewport"
          content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no">
    <meta charset='utf-8'>
    <title>登录</title>
    <jsp:include page="../common/link.jsp"/>
</head>
<body>
<jsp:include page="../common/header.jsp"/>
<jsp:include page="../common/nav.jsp"/>
<div class="container">
    <div class="panel">
        <div class="panel-head">
            登录
        </div>
        <div class="panel-content">
            <div class="form">
                <div class="input-group">
                    <div class="input-label">
                        <label class="label">用户名</label>
                    </div>
                    <div class="input-block">
                        <input class="input" type="text"/>
                    </div>
                </div>
                <div class="input-group">
                    <div class="input-label">
                        <label class="label">密码</label>
                    </div>
                    <div class="input-block">
                        <input class="input" type="text"/>
                    </div>
                </div>
                <div class="input-group">
                    <div class="right">
                        <a href="javascript:;" class="weui-btn weui-btn_mini weui-btn_default">登录</a>
                    </div>
                </div>
                <div class="clear"></div>
            </div>
        </div>
    </div>
</div>
</body>
</html>