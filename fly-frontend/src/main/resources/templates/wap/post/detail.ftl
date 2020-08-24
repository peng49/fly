<html>
<head>
    <meta name="viewport"
          content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no">
    <meta charset='utf-8'>
    <title>详情页</title>
    <link rel="stylesheet" href="/static/editor.md/css/editormd.min.css"/>
    <#include "../base/link.ftl" />
</head>
<body>
<#include "../base/header.ftl" />
<#include "../base/nav.ftl" />

<div class="post-box">
    <div class="post-detail">
        <h1 class="title">${post.title!}</h1>

        <div class="post-info">
            <span class="column">${(post.column.name)!}</span>

            <span class="post-nums" style="position: absolute;right: 0;font-size: 16px;">
                <a href="javascript:"><i title="回复" class="iconfont"></i> ${(post.replyCount)!0}</a>
                <i title="点击" class="iconfont"></i> ${(post.viewCount)!0}
            </span>
        </div>
        <div class="post-about">
            <a href="/u/${(post.author.id)!}" class="avatar">
                <img src="${(post.author.avatar)!''}" alt="knight">
            </a>
            <div class="post-author">
                <a href="/u/${(post.author.id)!}" class="link">
                    <cite>${(post.author.username)!}</cite>
                </a>
                <span>${post.publishAt}</span>
            </div>
            <div class="post-hits">
                &nbsp;
            </div>
        </div>

        <div class="markdown-body editormd-preview-container post-content">
            ${post.content!}
        </div>
    </div>
</div>

<div class="panel">
    <div class="panel-head">
        <p>共${comments?size}条评论</p>
    </div>
    <div class="panel-content">
        <ul class="comments">
            <#list comments as comment>
                <li class="item">
                    <div class="avatar">
                        <a href="">
                            <img src="${comment.user.avatar}">
                        </a>
                    </div>
                    <div class="content">
                        <div class="comment-head">
                        <span class="username">
                            <a href="">${comment.user.username}</a>
                        </span>
                            <span>
                            <time date="" title="">${comment.commentTime}</time>
                        </span>
                            <span class="right">#1</span>
                        </div>
                        <div class="comment">
                            <#if (comment.parent)??>
                                <div class="comment-parent">
                                    <div class="a">引用 @${(comment.parent.user.username)!} 的回复</div>
                                    <div>
                                        ${(comment.parent.content)!}
                                    </div>
                                </div>
                            </#if>
                            ${comment.content}
                        </div>
                    </div>
                </li>
            <#else>
                <li>暂无评论</li>
            </#list>
        </ul>
    </div>
</div>
<div class="panel">
    <div class="panel-head">
        <p>添加回复</p>
    </div>
    <div class="panel-content">
        <div id="comment-editor-toolbar" style="background-color:#f1f1f1; border:1px solid #ccc;"></div>
        <div id="comment-editor" style="border:1px solid #ccc; border-top:none; height:120px; z-index:10;"
             class="text"></div>
        <div>
            <div class="right" style="margin-top: 5px">
                <a id="submit-comment" href="javascript:;" class="weui-btn weui-btn_mini weui-btn_default">提交</a>
            </div>
            <div class="clear"></div>
        </div>
    </div>
</div>
<#include "../base/footer.ftl"/>

<script type="text/javascript" src="https://unpkg.com/wangeditor@3.1.1/release/wangEditor.min.js"></script>
<script type="text/javascript">
    let editor = new wangEditor(document.getElementById("comment-editor-toolbar"), document.getElementById("comment-editor"));
    editor.customConfig.menus = [
        'italic',  // 斜体
        'underline',  // 下划线
        'strikeThrough',  // 删除线
        'list',  // 列表
        'justify',  // 对齐方式
        'quote',  // 引用
        'code',  // 插入代码
        'undo',  // 撤销
        'redo'  // 重复
    ];
    editor.create();
    $('body').on('click', '#submit-comment', function () {
        let postId =
        ${post.id}
        if (!editor.txt.text().trim()) {
            return $.toast("评论内容不能为空", 'cancel');
        }
        $.ajax('/post/addComment', {
            method: "POST",
            data: JSON.stringify({postId: postId, content: editor.txt.html()}),
            dataType: 'json',
            headers: {
                'Content-Type': 'application/json',
            },
            success: function (res) {
                if (res.code === 'success') {
                    $.toast("提交成功");
                    return;
                } else {
                    $.toast(res.message, "text");
                }
            }
        });
    })

</script>
</body>
</html>
