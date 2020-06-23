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
    <jsp:include page="../common/link.jsp" />
</head>
<body>
<jsp:include page="../common/header.jsp"/>

<jsp:include page="../common/column.jsp"/>

<div class="layui-container">
    <div class="layui-row layui-col-space15">
        <div class="layui-col-md8">
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
                            <a href="user/${post.author.id}" class="fly-avatar">
                                <img src="${post.author.avatar}" alt="${post.author.username}">
                            </a>
                            <h2>
                                <a class="layui-badge">分享</a>
                                <a href="/post/detail/${post.id}">${post.title}</a>
                            </h2>
                            <div class="fly-list-info">
                                <a href="user/${post.author.id}" link>
                                    <cite>${post.author.username}</cite>
                                </a>
                                <span>刚刚</span>

                                <span class="fly-list-kiss layui-hide-xs" title="悬赏飞吻"><i
                                        class="iconfont icon-kiss"></i> 60 </span>
                                <!--<span class="layui-badge fly-badge-accept layui-hide-xs">已结</span>-->
                                <span class="fly-list-nums">
                <i class="iconfont icon-pinglun1" title="回答"></i> ${post.replyCount}
              </span>
                            </div>
                            <div class="fly-list-badge">
                                <span class="layui-badge layui-bg-black">置顶</span>
                                <!--<span class="layui-badge layui-bg-red">精帖</span>-->
                            </div>
                        </li>
                    </c:forEach>
                </ul>
                <div style="text-align: center">
                    <div class="laypage-main"><span class="laypage-curr">1</span><a href="/jie/page/2/">2</a><a
                            href="/jie/page/3/">3</a><a href="/jie/page/4/">4</a><a
                            href="/jie/page/5/">5</a><span>…</span><a href="/jie/page/148/" class="laypage-last"
                                                                      title="尾页">尾页</a><a href="/jie/page/2/"
                                                                                          class="laypage-next">下一页</a>
                    </div>
                </div>

            </div>
        </div>
        <div class="layui-col-md4">
            <dl class="fly-panel fly-list-one">
                <dt class="fly-panel-title">本周热议</dt>
                <dd>
                    <a href="">基于 layui 的极简社区页面模版</a>
                    <span><i class="iconfont icon-pinglun1"></i> 16</span>
                </dd>
                <dd>
                    <a href="">基于 layui 的极简社区页面模版</a>
                    <span><i class="iconfont icon-pinglun1"></i> 16</span>
                </dd>
                <dd>
                    <a href="">基于 layui 的极简社区页面模版</a>
                    <span><i class="iconfont icon-pinglun1"></i> 16</span>
                </dd>
                <dd>
                    <a href="">基于 layui 的极简社区页面模版</a>
                    <span><i class="iconfont icon-pinglun1"></i> 16</span>
                </dd>
                <dd>
                    <a href="">基于 layui 的极简社区页面模版</a>
                    <span><i class="iconfont icon-pinglun1"></i> 16</span>
                </dd>
                <dd>
                    <a href="">基于 layui 的极简社区页面模版</a>
                    <span><i class="iconfont icon-pinglun1"></i> 16</span>
                </dd>
                <dd>
                    <a href="">基于 layui 的极简社区页面模版</a>
                    <span><i class="iconfont icon-pinglun1"></i> 16</span>
                </dd>
                <dd>
                    <a href="">基于 layui 的极简社区页面模版</a>
                    <span><i class="iconfont icon-pinglun1"></i> 16</span>
                </dd>
                <dd>
                    <a href="">基于 layui 的极简社区页面模版</a>
                    <span><i class="iconfont icon-pinglun1"></i> 16</span>
                </dd>
                <dd>
                    <a href="">基于 layui 的极简社区页面模版</a>
                    <span><i class="iconfont icon-pinglun1"></i> 16</span>
                </dd>

                <!-- 无数据时 -->
                <!--
                <div class="fly-none">没有相关数据</div>
                -->
            </dl>

            <div class="fly-panel">
                <div class="fly-panel-title">
                    这里可作为广告区域
                </div>
                <div class="fly-panel-main">
                    <a href="" target="_blank" class="fly-zanzhu" style="background-color: #393D49;">虚席以待</a>
                </div>
            </div>

            <div class="fly-panel fly-link">
                <h3 class="fly-panel-title">友情链接</h3>
                <dl class="fly-panel-main">
                    <dd><a href="http://www.layui.com/" target="_blank">layui</a>
                    <dd>
                    <dd><a href="http://layim.layui.com/" target="_blank">WebIM</a>
                    <dd>
                    <dd><a href="http://layer.layui.com/" target="_blank">layer</a>
                    <dd>
                    <dd><a href="http://www.layui.com/laydate/" target="_blank">layDate</a>
                    <dd>
                    <dd>
                        <a href="mailto:xianxin@layui-inc.com?subject=%E7%94%B3%E8%AF%B7Fly%E7%A4%BE%E5%8C%BA%E5%8F%8B%E9%93%BE"
                           class="fly-link">申请友链</a>
                    <dd>
                </dl>
            </div>

        </div>
    </div>
</div>

<jsp:include page="../common/footer.jsp"/>

</body>
</html>