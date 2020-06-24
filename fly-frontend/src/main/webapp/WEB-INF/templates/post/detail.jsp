<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>Fly Template v3.0，基于 layui 的极简社区页面模版</title>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <meta name="keywords" content="fly,layui,前端社区">
    <meta name="description" content="Fly社区是模块化前端UI框架Layui的官网社区，致力于为web开发提供强劲动力">
    <jsp:include page="../common/link.jsp"/>
</head>
<body>

<jsp:include page="../common/header.jsp"/>

<div class="layui-hide-xs">
    <jsp:include page="../common/column.jsp"/>
</div>

<div id="post-container" class="layui-container">
    <div class="layui-row layui-col-space15">
        <div class="layui-col-md8 content detail">
            <div class="fly-panel detail-box">
                <h1>${post.title}</h1>
                <div class="fly-detail-info">
                    <!-- <span class="layui-badge">审核中</span> -->
                    <span class="layui-badge layui-bg-green fly-detail-column">动态</span>

                    <span class="layui-badge" style="background-color: #999;">未结</span>
                    <!-- <span class="layui-badge" style="background-color: #5FB878;">已结</span> -->

                    <span class="layui-badge layui-bg-black">置顶</span>
                    <span class="layui-badge layui-bg-red">精帖</span>

                    <div class="fly-admin-box" data-id="123">
                        <span class="layui-btn layui-btn-xs jie-admin" type="del">删除</span>

                        <span class="layui-btn layui-btn-xs jie-admin" type="set" field="stick" rank="1">置顶</span>
                        <!-- <span class="layui-btn layui-btn-xs jie-admin" type="set" field="stick" rank="0" style="background-color:#ccc;">取消置顶</span> -->

                        <span class="layui-btn layui-btn-xs jie-admin" type="set" field="status" rank="1">加精</span>
                        <!-- <span class="layui-btn layui-btn-xs jie-admin" type="set" field="status" rank="0" style="background-color:#ccc;">取消加精</span> -->
                    </div>
                    <span class="fly-list-nums">
            <a href="#comment"><i class="iconfont" title="回答">&#xe60c;</i> ${post.replyCount}</a>
            <i class="iconfont" title="人气">&#xe60b;</i> ${post.viewCount}
          </span>
                </div>
                <div class="detail-about">
                    <a class="fly-avatar" href="/user/${post.author.id}">
                        <img src="${post.author.avatar}"
                             alt="${post.author.username}">
                    </a>
                    <div class="fly-detail-user">
                        <a href="/user/index/${post.author.id}" class="fly-link">
                            <cite>${post.author.username}</cite>
                            <%--<i class="iconfont icon-renzheng" title="认证信息：{{ rows.user.approve }}"></i>
                            <i class="layui-badge fly-badge-vip">VIP3</i>--%>
                        </a>
                        <span>${post.publishAt}</span>
                    </div>
                    <div class="detail-hits" id="LAY_jieAdmin" data-id="123">
                        <%--                        <span style="padding-right: 10px; color: #FF7200">悬赏：60飞吻</span>--%>
                        <span class="layui-btn layui-btn-xs jie-admin" type="edit"><a
                                href="../post/add.html">编辑此贴</a></span>
                    </div>
                </div>
                <div class="detail-body photos">
                    ${post.content}
                </div>
            </div>

            <div class="fly-panel detail-box" id="flyReply">
                <fieldset class="layui-elem-field layui-field-title" style="text-align: center;">
                    <legend>回帖</legend>
                </fieldset>

                <ul class="jieda" id="jieda">
                    <c:choose>
                        <c:when test="${comments.size() > 0}">
                            <c:forEach items="${comments}" var="comment">
                                <li data-id="111" class="jieda-daan">
                                    <div class="detail-about detail-about-reply">
                                        <a class="fly-avatar" href="/user/index/${comment.user.id}">
                                            <img src="${comment.user.avatar}" alt=" ">
                                        </a>
                                        <div class="fly-detail-user">
                                            <a href="/user/index/${comment.user.id}" class="fly-link">
                                                <cite>${comment.user.username}</cite>
                                                <i class="iconfont icon-renzheng" title="认证信息：XXX"></i>
                                                <i class="layui-badge fly-badge-vip">VIP3</i>
                                            </a>
                                            <c:if test="${post.author.id == comment.user.id}">
                                                <span>(楼主)</span>
                                            </c:if>
                                        </div>
                                        <div class="detail-hits">
                                            <span>${comment.commentTime}</span>
                                        </div>
                                        <i class="iconfont icon-caina" title="最佳答案"></i>
                                    </div>
                                    <div class="detail-body jieda-body photos">
                                        <p>${comment.content}</p>
                                    </div>
                                    <div class="jieda-reply">
                                        <span class="jieda-zan zanok">
                                            <i class="iconfont icon-zan"></i>
                                            <em>${comment.agreeCount}</em>
                                        </span>
                                        <span>
                                            <i class="iconfont icon-svgmoban53"></i>回复
                                        </span>
                                        <div class="jieda-admin">
<%--                                            <span>编辑</span>--%>
<%--                                            <span>删除</span>--%>
                                            <!-- <span class="jieda-accept" type="accept">采纳</span> -->
                                        </div>
                                    </div>
                                </li>
                            </c:forEach>
                        </c:when>
                        <c:otherwise>
                            <li class="fly-none">消灭零回复</li>
                        </c:otherwise>
                    </c:choose>
                </ul>

                <div class="layui-form layui-form-pane">
                    <div class="layui-form-item layui-form-text">
                        <div class="layui-input-block">
                            <textarea placeholder="请输入内容" v-model="comment.content" class="layui-textarea fly-editor" style="height: 150px;"></textarea>
                        </div>
                    </div>
                    <div class="layui-form-item">
                        <button @click="submitComment" class="layui-btn">提交回复</button>
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
                    <a href="http://layim.layui.com/?from=fly" target="_blank" class="fly-zanzhu"
                       time-limit="2017.09.25-2099.01.01" style="background-color: #5FB878;">LayIM 3.0 - layui 旗舰之作</a>
                </div>
            </div>

            <div class="fly-panel" style="padding: 20px 0; text-align: center;">
                <img src="/static/images/weixin.jpg" style="max-width: 100%;" alt="layui">
                <p style="position: relative; color: #666;">微信扫码关注 layui 公众号</p>
            </div>

        </div>
    </div>
</div>

<jsp:include page="../common/footer.jsp"/>
<script type="text/javascript">
    new Vue({
        el:"#post-container",
        data:{
            comment:{
                postId:'${post.id}',
                content:"",
                parentId:0
            }
        },
        methods:{
            submitComment:function(){
                console.log(this.comment)
                axios.post('/post/addComment', this.comment)
                    .then(function (response) {
                        if (response.code === "success") {
                            //注册成功,转跳登录页面
                            layer.msg('评论成功');
                            return;
                        }
                        layer.msg(response.message)
                    })
                    .catch(function (error) {
                        console.log(error);
                    });
            }
        }
    })
</script>
</body>
</html>