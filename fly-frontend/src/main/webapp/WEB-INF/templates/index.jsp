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
    <jsp:include page="common/link.jsp"/>
</head>
<body>

<jsp:include page="common/header.jsp"/>

<jsp:include page="common/column.jsp"/>

<div class="layui-container">
    <div class="layui-row layui-col-space15">
        <div class="layui-col-md8">
            <div class="fly-panel">
                <div class="fly-panel-title fly-filter">
                    <a>置顶</a>
                    <a href="#signin" class="layui-hide-sm layui-show-xs-block fly-right" id="LAY_goSignin"
                       style="color: #FF5722;">去签到</a>
                </div>
                <ul class="fly-list">
                    <c:forEach items="${topPosts}" var="post">
                        <li>
                            <a href="/user/index/${post.author.id}" class="fly-avatar">
                                <img src="${post.author.avatar}"
                                     alt="${post.author.username}">
                            </a>
                            <h2>
                                <a class="layui-badge">动态</a>
                                <a href="/post/detail/${post.id}">${post.title}</a>
                            </h2>
                            <div class="fly-list-info">
                                <a href="user/${post.author.id}" link>
                                    <cite>${post.author.username}</cite>
                                </a>
                                <span>刚刚</span>

                                <span class="fly-list-kiss layui-hide-xs" title="悬赏飞吻"><i
                                        class="iconfont icon-kiss"></i> 60</span>
                                <span class="layui-badge fly-badge-accept layui-hide-xs">已结</span>
                                <span class="fly-list-nums">
                <i class="iconfont icon-pinglun1" title="回答"></i> 66
              </span>
                            </div>
                            <div class="fly-list-badge">
                                <span class="layui-badge layui-bg-red">精帖</span>
                            </div>
                        </li>
                    </c:forEach>
                </ul>
            </div>

            <div class="fly-panel" style="margin-bottom: 0;">

                <div class="fly-panel-title fly-filter">
                    <a href="" class="layui-this">综合</a>
                    <span class="fly-mid"></span>
                    <a href="">未结</a>
                    <span class="fly-mid"></span>
                    <a href="">已结</a>
                    <span class="fly-mid"></span>
                    <a href="">精华</a>
                    <span class="fly-filter-right layui-hide-xs">
            <a href="" class="layui-this">按最新</a>
            <span class="fly-mid"></span>
            <a href="">按热议</a>
          </span>
                </div>

                <ul class="fly-list">
                    <c:forEach items="${posts}" var="post">
                        <li>
                            <a href="/user/index/${post.author.id}" class="fly-avatar">
                                <img src="${post.author.avatar}"
                                     alt="${post.author.username}">
                            </a>
                            <h2>
                                <a class="layui-badge">动态</a>
                                <a href="/post/detail/${post.id}">${post.title}</a>
                            </h2>
                            <div class="fly-list-info">
                                <a href="user/${post.author.id}" link>
                                    <cite>${post.author.username}</cite>
                                    <!--<i class="iconfont icon-renzheng" title="认证信息：XXX"></i>-->
                                        <%--                                    <i class="layui-badge fly-badge-vip">VIP3</i>--%>
                                </a>
                                <span>刚刚</span>

                                <span class="fly-list-kiss layui-hide-xs" title="悬赏飞吻"><i
                                        class="iconfont icon-kiss"></i> 60</span>
                                <span class="layui-badge fly-badge-accept layui-hide-xs">已结</span>
                                <span class="fly-list-nums">
                <i class="iconfont icon-pinglun1" title="回答"></i> 66
              </span>
                            </div>
                            <div class="fly-list-badge">
                                <span class="layui-badge layui-bg-red">精帖</span>
                            </div>
                        </li>
                    </c:forEach>
                </ul>
                <div style="text-align: center">
                    <div class="laypage-main">
                        <a href="javascript:" class="laypage-next">更多求解</a>
                    </div>
                </div>
            </div>
        </div>
        <div class="layui-col-md4">
            <div class="fly-panel">
                <h3 class="fly-panel-title">温馨通道</h3>
                <ul class="fly-panel-main fly-list-static">
                    <li>
                        <a href="http://fly.layui.com/jie/4281/" target="_blank">layui 的 GitHub 及 Gitee (码云)
                            仓库，欢迎Star</a>
                    </li>
                    <li>
                        <a href="http://fly.layui.com/jie/5366/" target="_blank">
                            layui 常见问题的处理和实用干货集锦
                        </a>
                    </li>
                    <li>
                        <a href="http://fly.layui.com/jie/4281/" target="_blank">layui 的 GitHub 及 Gitee (码云)
                            仓库，欢迎Star</a>
                    </li>
                    <li>
                        <a href="http://fly.layui.com/jie/5366/" target="_blank">
                            layui 常见问题的处理和实用干货集锦
                        </a>
                    </li>
                    <li>
                        <a href="http://fly.layui.com/jie/4281/" target="_blank">layui 的 GitHub 及 Gitee (码云)
                            仓库，欢迎Star</a>
                    </li>
                </ul>
            </div>

            <jsp:include page="common/slider/sign-in.jsp"/>

            <jsp:include page="common/slider/top-comment-user.jsp"/>

            <jsp:include page="common/slider/top-comment.jsp"/>


            <div class="fly-panel">
                <div class="fly-panel-title">
                    这里可作为广告区域
                </div>
                <div class="fly-panel-main">
                    <a href="http://layim.layui.com/?from=fly" target="_blank" class="fly-zanzhu"
                       time-limit="2017.09.25-2099.01.01" style="background-color: #5FB878;">LayIM 3.0 - layui 旗舰之作</a>
                </div>
            </div>
            <jsp:include page="common/slider/friend-link.jsp"/>
        </div>
    </div>
</div>

<jsp:include page="common/footer.jsp"/>
</body>
</html>