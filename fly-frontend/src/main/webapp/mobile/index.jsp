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
    <div class="weui-panel__hd">图文组合列表</div>
    <div class="weui-panel__bd">
        <a href="javascript:void(0);" class="weui-media-box weui-media-box_appmsg">
            <div class="weui-media-box__hd list-avatar">
                <img class="weui-media-box__thumb" src="">
            </div>
            <div class="weui-media-box__bd">
                <h4 class="weui-media-box__title">标题一</h4>
                <p class="weui-media-box__desc">由各种物质组成的巨型球状天体，叫做星球。星球有一定的形状，有自己的运行轨道。</p>
            </div>
        </a>
        <a href="javascript:void(0);" class="weui-media-box weui-media-box_appmsg">
            <div class="weui-media-box__hd">
                <img class="weui-media-box__thumb" src="">
            </div>
            <div class="weui-media-box__bd">
                <h4 class="weui-media-box__title">标题二</h4>
                <p class="weui-media-box__desc">由各种物质组成的巨型球状天体，叫做星球。星球有一定的形状，有自己的运行轨道。</p>
            </div>
        </a>
    </div>
    <div class="weui-panel__ft">
        <a href="javascript:void(0);" class="weui-cell weui-cell_access weui-cell_link">
            <div class="weui-cell__bd">查看更多</div>
            <span class="weui-cell__ft"></span>
        </a>
    </div>
</div>

<jsp:include page="common/footer.jsp"/>
</body>
</html>
