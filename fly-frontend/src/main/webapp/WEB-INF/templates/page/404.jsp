<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>404 - Fly社区</title>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <meta name="keywords" content="fly,layui,前端社区">
    <meta name="description" content="Fly社区是模块化前端UI框架Layui的官网社区，致力于为web开发提供强劲动力">
    <jsp:include page="../common/link.jsp" />
</head>
<body>
<jsp:include page="../common/header.jsp"/>

<jsp:include page="../common/column.jsp"/>

<div class="layui-container fly-marginTop">
    <div class="fly-panel">
        <div class="fly-none">
            <h2><i class="iconfont icon-404"></i></h2>
            <p>页面或者数据被<a href="/" target="_blank"> 纸飞机 </a>运到火星了，啥都看不到了…</p>
        </div>
    </div>
</div>
<jsp:include page="../common/footer.jsp"/>
</body>
</html>