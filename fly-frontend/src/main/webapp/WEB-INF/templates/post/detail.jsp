<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>${post.title}</title>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <meta name="keywords" content="fly,layui,前端社区">
    <meta name="description" content="Fly社区是模块化前端UI框架Layui的官网社区，致力于为web开发提供强劲动力">
    <jsp:include page="../common/link.jsp"/>
    <link rel="stylesheet" href="/static/editor.md/css/editormd.min.css">
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
                    <span class="layui-badge layui-bg-green fly-detail-column">${post.column.name}</span>

                    <%--<span class="layui-badge" style="background-color: #999;">未结</span>--%>
                    <!-- <span class="layui-badge" style="background-color: #5FB878;">已结</span> -->
                    <c:if test="${post.top == 1}">
                        <span class="layui-badge layui-bg-black">置顶</span>
                    </c:if>
                    <c:if test="${post.essence == 1}">
                        <span class="layui-badge layui-bg-red">精帖</span>
                    </c:if>

                    <div class="fly-admin-box" data-id="123">
                        <c:if test="${user != null && user.isAdmin == 1}">
                            <span class="layui-btn layui-btn-xs jie-admin" type="del">删除</span>

                            <c:choose>
                                <c:when test="${post.top == 1}">
                                    <span @click="top" class="layui-btn layui-btn-xs jie-admin"
                                          style="background-color:#ccc;">取消置顶</span>
                                </c:when>
                                <c:otherwise>
                                    <span @click="top" class="layui-btn layui-btn-xs jie-admin">置顶</span>
                                </c:otherwise>
                            </c:choose>

                            <c:choose>
                                <c:when test="${post.essence == 1}">
                                    <span @click="essence" class="layui-btn layui-btn-xs jie-admin"
                                          style="background-color:#ccc;">取消加精</span>
                                </c:when>
                                <c:otherwise>
                                    <span @click="essence" class="layui-btn layui-btn-xs jie-admin">加精</span>
                                </c:otherwise>
                            </c:choose>
                        </c:if>
                    </div>
                    <span class="fly-list-nums">
            <a href="#reply"><i class="iconfont" title="回答">&#xe60c;</i> ${post.replyCount}</a>
            <i class="iconfont" title="人气">&#xe60b;</i> ${post.viewCount}
          </span>
                </div>
                <div class="detail-about">
                    <a class="fly-avatar" href="/user/index/${post.author.id}">
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
                    <div class="detail-hits">
                        <c:if test="${allowEdit == true}">
                            <span class="layui-btn layui-btn-xs jie-admin" type="edit">
                                <a href="/post/edit/${post.id}">编辑此贴</a>
                            </span>
                        </c:if>
                        &nbsp;
                    </div>
                </div>
                <div class="detail-body photos markdown-body editormd-preview-container" style="padding: 0">
                    ${post.content}
                </div>
                <div class="detail-handle" style="text-align: center">
                    <a href="javascript:"><i class="layui-icon"></i> 收藏(0)</a>
                    <a href="javascript:">分享</a>
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
                                <li id="reply${comment.id}" class="jieda-daan">
                                    <div class="detail-about detail-about-reply">
                                        <a class="fly-avatar" href="/user/index/${comment.user.id}">
                                            <img src="${comment.user.avatar}" alt=" ">
                                        </a>
                                        <div class="fly-detail-user">
                                            <a href="/user/index/${comment.user.id}" class="fly-link">
                                                <cite>${comment.user.username}</cite>
                                            </a>
                                            <c:if test="${post.author.id == comment.user.id}">
                                                <span>(楼主)</span>
                                            </c:if>
                                        </div>
                                        <div class="detail-hits">
                                            <span>${comment.commentTime}</span>
                                        </div>
                                    </div>
                                    <div class="detail-body jieda-body photos">
                                        <c:if test="${comment.parent.id != null}">
                                            <div class="reply-content"><a href="#reply${comment.parent.id}"
                                                                          class="fly-link">@${comment.parent.user.username}</a> ${comment.parent.content}
                                            </div>
                                        </c:if>
                                        <div class="comment-content">${comment.content}</div>
                                    </div>
                                    <div class="jieda-reply">
                                        <span class="jieda-zan zanok">
                                            <i class="iconfont icon-zan"></i>
                                            <em>${comment.agreeCount}</em>
                                        </span>
                                        <span @click="reply($event)" data-id="${comment.id}"
                                              data-username="${comment.user.username}">
                                            <i class="iconfont icon-svgmoban53"></i>回复
                                        </span>
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
                        <div class="layui-input-block" id="reply">
                            <div v-if="comment.parentId" class="reply-content">
                                <p>
                                    <a class="fly-link" href="#">@{{parentCon.username}}</a>
                                    <a class="reply-cancel fly-link" href="javascript:" @click="replyCancel">取消</a>
                                    <span v-html="parentCon.content"></span>
                                </p>
                            </div>
                            <div ref="toolbar" style="background-color:#f1f1f1; border:1px solid #ccc;"
                                 class="toolbar"></div>
                            <div ref="editor" style="border:1px solid #ccc; border-top:none; height:180px; z-index:10;"
                                 class="text"></div>
                        </div>
                    </div>
                    <div class="layui-form-item">
                        <button @click="submitComment" class="layui-btn">提交回复</button>
                    </div>
                </div>
            </div>
        </div>
        <div class="layui-col-md4">
            <jsp:include page="../common/slider/top-comment.jsp"/>

            <jsp:include page="../common/slider/ad.jsp"/>

            <jsp:include page="../common/slider/wechat.jsp"/>
        </div>
    </div>
</div>

<jsp:include page="../common/footer.jsp"/>
<script type="text/javascript" src="https://unpkg.com/wangeditor@3.1.1/release/wangEditor.min.js"></script>
<script type="text/javascript">
    new Vue({
        el: "#post-container",
        data: {
            editor: null,
            parentCon: {
                username: "回复用户",
                content: "回复的内容"
            },
            comment: {
                postId: '${post.id}',
                content: "",
                parentId: 0
            }
        },
        mounted() {
            this.initEditor()
            this.editor.txt.html(this.comment.content)
        },
        methods: {
            initEditor: function () {
                var E = window.wangEditor
                this.editor = new E(this.$refs.toolbar, this.$refs.editor);
                this.editor.create()
            },
            submitComment: function () {
                if (!this.editor.txt.text().trim()) {
                    return layer.msg("评论内容不能为空");
                }
                console.log(this.comment)
                this.comment.content = this.editor.txt.html();
                axios.post('/post/addComment', this.comment)
                    .then(function (response) {
                        if (response.code === "success") {
                            layer.msg('评论成功');
                            return;
                        }
                        layer.msg(response.message)
                    })
                    .catch(function (error) {
                        console.log(error);
                    });
            },
            top: function () {
                var formData = new FormData();
                formData.append("postId", ${post.id});

                axios.post('/post/top', formData)
                    .then(function (response) {
                        if (response.code === "success") {
                            layer.msg('操作成功');
                            return;
                        }
                        layer.msg(response.message)
                    })
                    .catch(function (error) {
                        console.log(error);
                    });
            },
            essence: function () {
                var formData = new FormData();
                formData.append("postId", ${post.id});
                axios.post('/post/essence', formData)
                    .then(function (response) {
                        if (response.code === "success") {
                            layer.msg('操作成功');
                            return;
                        }
                        layer.msg(response.message)
                    })
                    .catch(function (error) {
                        console.log(error);
                    });
            },
            reply: function (ele) {
                let dom = ele.currentTarget;
                this.comment.parentId = dom.getAttribute("data-id")
                this.parentCon.username = dom.getAttribute("data-username");
                this.parentCon.content = $(dom).parents('li').find(".comment-content").html();

                document.getElementById("reply").scrollIntoView();
                this.editor.selection.restoreSelection()
            },
            replyCancel: function () {
                this.comment.parentId = 0;
            }
        }
    });
</script>
</body>
</html>