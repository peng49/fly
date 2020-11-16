<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>${post.title}</title>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <meta name="keywords" content="${post.title}">
    <meta name="description" content="${post.title}">
    <#include "../common/link.ftl" />
    <link rel="stylesheet" href="/static/editor.md/css/editormd.min.css">
</head>
<body>

<#include "../common/header.ftl"/>

<div id="post-container" class="layui-container">
    <div class="layui-row layui-col-space15">
        <div class="layui-col-md8 content detail">
            <div class="fly-panel detail-box">
                <h1>${post.title}</h1>
                <div class="fly-detail-info">
                    <span class="layui-badge layui-bg-green fly-detail-column">${post.column.name}</span>

                    <span v-if="post.top == 1" class="layui-badge layui-bg-black">置顶</span>

                    <span v-if="post.essence == 1" class="layui-badge layui-bg-red">精帖</span>


                    <div class="fly-admin-box" data-id="123">
                        <#if user?? && user.isAdmin == 1 >
                            <span @click="move2delete" class="layui-btn layui-btn-xs jie-admin">删除</span>

                            <span v-if="post.top == 1"
                                  @click="top"
                                  class="layui-btn layui-btn-xs jie-admin"
                                  style="background-color:#ccc;">取消置顶</span>
                            <span @click="top" class="layui-btn layui-btn-xs jie-admin" v-else>置顶</span>


                            <span v-if="post.essence == 1" @click="essence" class="layui-btn layui-btn-xs jie-admin"
                                  style="background-color:#ccc;">取消加精</span>
                            <span v-else @click="essence" class="layui-btn layui-btn-xs jie-admin">加精</span>
                        </#if>
                    </div>
                    <span class="fly-list-nums">
            <a href="#reply"><i class="iconfont" title="回答">&#xe60c;</i> ${(post.replyCount)!0}</a>
            <i class="iconfont" title="人气">&#xe60b;</i> ${(post.viewCount)!0}
          </span>
                </div>
                <div class="detail-about">
                    <a class="fly-avatar" href="/u/${post.author.id?c}">
                        <img src="${(post.author.avatar)!}"
                             alt="${post.author.username}">
                    </a>
                    <div class="fly-detail-user">
                        <a href="/u/${post.author.id}" class="fly-link">
                            <cite>${post.author.username}</cite>
                        </a>
                        <span>${(post.publishAt)!}</span>
                    </div>
                    <div class="detail-hits">
                        <#if allowEdit == true>
                            <span class="layui-btn layui-btn-xs jie-admin" type="edit">
                                <a href="/post/edit/${post.id?c}">编辑此贴</a>
                            </span>
                        </#if>
                        &nbsp
                    </div>
                </div>
                <div class="detail-body photos markdown-body editormd-preview-container" style="padding: 0">
                    ${post.content}
                </div>
                <div class="detail-handle" style="text-align: center">
                    <a href="javascript:" @click="collection"><i class="icon-coll"></i> 收藏(0)</a>
                    <a href="javascript:"><i class="icon-recommend"></i>推荐</a>
                </div>
            </div>

            <div class="fly-panel detail-box" id="flyReply">
                <fieldset class="layui-elem-field layui-field-title" style="text-align: center;">
                    <legend>回帖</legend>
                </fieldset>

                <ul class="jieda" id="jieda">
                    <#list comments as comment>
                        <li id="reply${comment.id?c}" class="jieda-daan">
                            <div class="detail-about detail-about-reply">
                                <a class="fly-avatar" href="/u/${(comment.user.id?c)!}">
                                    <img src="${(comment.user.avatar)!}" alt=" ">
                                </a>
                                <div class="fly-detail-user">
                                    <a href="/u/${comment.user.id?c}" class="fly-link">
                                        <cite>${comment.user.username}</cite>
                                    </a>
                                    <#if post.author.id == comment.user.id >
                                        <span>(楼主)</span>
                                    </#if>
                                </div>
                                <div class="detail-hits">
                                    <span>${(comment.createdAt)!}</span>
                                </div>
                            </div>
                            <div class="detail-body jieda-body photos">
                                <div class="comment-content">${comment.content}</div>
                            </div>
                            <div class="jieda-reply">
                                        <span class="jieda-zan" @click="commentAgree(${comment.id})">
                                            <i class="iconfont icon-zan"></i>
                                            <em>${comment.agreeCount}</em>
                                        </span>
                                <span @click="reply($event)" data-id="${comment.id?c}"
                                      data-username="${comment.user.username}">
                                            <i class="iconfont icon-svgmoban53"></i>回复
                                        </span>
                            </div>
                        </li>
                    </#list>
                </ul>


                <div class="layui-form layui-form-pane">
                    <div class="layui-form-item layui-form-text">
                        <div class="layui-input-block" id="reply">
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
            <#include "../common/slider/sign-in.ftl"/>

            <#include "../common/slider/top-comment.ftl"/>

            <#include "../common/slider/ad.ftl"/>

            <#include "../common/slider/wechat.ftl"/>
        </div>
    </div>
</div>

<#include "../common/footer.ftl"/>
<script type="text/javascript" src="https://unpkg.com/wangeditor@3.1.1/release/wangEditor.min.js"></script>
<script type="text/javascript">
    initCopyButton();
    new Vue({
        el: "#post-container",
        data: {
            post: {
                top: '${(post.top)!0}',
                essence: '${(post.essence)!0}',
                status: '${(post.status)!}',
            },
            postCollection: {
                postId: '${post.id?c}'
            },
            editor: null,
            parentCon: {
                username: "回复用户",
                content: "回复的内容"
            },
            comment: {
                postId: '${post.id?c}',
                content: "",
                parentId: 0
            }
        },
        mounted() {
            this.initEditor()
            this.editor.txt.html(this.comment.content);

        },
        methods: {
            initEditor: function () {
                let E = window.wangEditor
                this.editor = new E(this.$refs.toolbar, this.$refs.editor);
                //文档: https://www.kancloud.cn/wangfupeng/wangeditor3/335777
                this.editor.customConfig.menus = [
                    'italic',  // 斜体
                    'underline',  // 下划线
                    'strikeThrough',  // 删除线
                    'link',  // 插入链接
                    'list',  // 列表
                    'justify',  // 对齐方式
                    'quote',  // 引用
                    'code',  // 插入代码
                    'undo',  // 撤销
                    'redo'  // 重复
                ];
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
                            window.location.reload();
                            return;
                        }
                        layer.msg(response.message)
                    })
                    .catch(function (error) {
                        console.log(error);
                    });
            },
            top: function () {//置顶
                let _this = this
                var formData = new FormData();
                formData.append("postId", ${post.id});

                axios.post('/post/top', formData)
                    .then(function (response) {
                        if (response.code === "success") {
                            layer.msg('操作成功');
                            _this.post.top = response.data.top
                            return;
                        }
                        layer.msg(response.message)
                    })
                    .catch(function (error) {
                        console.log(error);
                    });
            },
            essence: function () {//加精
                let _this = this
                var formData = new FormData();
                formData.append("postId", ${post.id});
                axios.post('/post/essence', formData)
                    .then(function (response) {
                        if (response.code === "success") {
                            layer.msg('操作成功');
                            _this.post.essence = response.data.essence
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

                let appendContent = '<div class="reply-content">\n' +
                    '        <div>\n' +
                    '            @<a class="fly-link" href="#">' + this.parentCon.username + '</a>\n' +
                    '            <div>' + this.parentCon.content + '</div>\n' +
                    '        </div>\n' +
                    '    </div>';

                this.editor.txt.append(appendContent)

                document.getElementById("reply").scrollIntoView();
                this.editor.selection.restoreSelection()
            },
            replyCancel: function () {
                this.comment.parentId = 0;
            },
            collection: function () {
                axios.post('/user/collection', this.postCollection)
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
            commentAgree: function (commentId) {
                axios.post('/user/commentAgree', {commentId: commentId})
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
            move2delete: function () {
                let _this = this;
                axios.get('/post/delete/${post.id}').then(function (response) {
                    if (response.code === 'success') {
                        layer.msg('操作成功');
                        _this.post.status = response.post.status
                    }
                })
            }
        }
    });
</script>
</body>
</html>