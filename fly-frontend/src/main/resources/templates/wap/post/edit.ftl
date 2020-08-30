<!DOCTYPE html>
<html>
<head>
    <meta name="viewport"
          content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no">
    <meta charset='utf-8'>
    <title>发布/编辑 文章</title>
    <#include "../base/link.ftl" />
</head>
<body>
<#include "../base/header.ftl" />
<#include "../base/nav.ftl" />

<div class="container" id="post-edit">
    <div class="container">
        <div class="panel">
            <div class="panel-head">
                注册
            </div>
            <div class="panel-content" style="padding: 0">
                <div class="weui-cells__title">栏目</div>
                <div class="weui-cells">
                    <div class="weui-cell weui-cell_select">
                        <div class="weui-cell__bd">
                            <select class="weui-select" v-model="postForm.columnId">
                                <#list columns as column>
                                    <option value="${column.id}">${column.name}</option>
                                </#list>
                            </select>
                        </div>
                    </div>
                </div>
                <div class="weui-cells__title">标题</div>
                <div class="weui-cells">
                    <div class="weui-cell">
                        <div class="weui-cell__bd">
                            <input class="weui-input" type="text" v-model="postForm.title" placeholder="请输入标题">
                        </div>
                    </div>
                </div>
                <div class="weui-cells__title">内容</div>
                <div id="editor" style="min-height: 400px;height: 400px;"><textarea>${(post.originalContent)!}</textarea></div>
            </div>
        </div>
    </div>
</div>
<#include "../base/footer.ftl" />
<link rel="stylesheet" href="/static/editor.md/css/editormd.min.css"/>
<script src="https://cdn.jsdelivr.net/npm/vue@2.6.0"></script>
<script src="/static/editor.md/editormd.min.js"></script>
<script type="text/javascript">
    new Vue({
        el: "#post-edit",
        data: {
            editor: '',
            postId: '${(post.id)!}',
            postStatus: '${(post.status)!}',
            postForm: {
                action: '',
                columnId: '${(post.column.id)!1}',
                title: "${(post.title)!}",
                originalContent: "",
                content: ""
            }
        },
        mounted: function () {
            let _this = this;
            let editorBar = ["undo", "redo", "|", "bold", "hr", "watch", "fullscreen"];


            if (_this.postId > 0) {
                if (_this.postStatus === '0') {//如果未发布
                    editorBar.push('publish');
                }
                editorBar.push('update');
            } else {
                editorBar.push('publish');
                editorBar.push('save2draft');
            }

            _this.editor = editormd('editor', {
                path: "/static/editor.md/lib/",
                toolbarIcons: function () {
                    return editorBar
                },
                toolbarCustomIcons: {
                    save2draft: "<button type='button' data-action='draft' class='layui-btn post-submit-btn'>保存到草稿</button>",
                    update: "<button type='button' data-action='update' class='layui-btn post-submit-btn'>更新</button>",
                    publish: "<button type='button' data-action='publish' class='layui-btn post-submit-btn'>立即发布</button>"
                },
                saveHTMLToTextarea: true,
                height: "100%",
                watch: false,
                onwatch: function () {
                    $('#editor .CodeMirror').css({display: 'none'});
                    $('#editor .editormd-preview').css({width: '100%'});
                },
                onunwatch:function(){
                    $('#editor .CodeMirror').css({display: 'block'});
                },
                onfullscreen: function() {
                    if(this.settings.watch){
                        $('#editor .CodeMirror').css({display: 'none'});
                        $('#editor .editormd-preview').css({width: '100%'});
                    }else{
                        $('#editor .CodeMirror').css({display: 'block'});
                    }
                },
                onfullscreenExit: function() {
                    if(this.settings.watch){
                        $('#editor .CodeMirror').css({display: 'none'});
                        $('#editor .editormd-preview').css({width: '100%'});
                    }else{
                        $('#editor .CodeMirror').css({display: 'block'});
                    }
                },
                imageUpload: true, //开启图片上传
                imageUploadURL: '/post/upload', //图片上传后台地址
                onload: function () {
                    $('.editormd-menu li .layui-btn').parent('li').addClass('pull-right');

                    // 引入插件 执行监听方法
                    // editormd.loadPlugin("/static/editor.md/plugins/image-handle-paste/image-handle-paste", function () {
                    //     _this.editor.imagePaste();
                    // });
                }
            });

            //通过jquery监听新加的按钮触发提交
            $('body').on('click', '.post-submit-btn', function () {
                let action = $(this).data('action');
                _this.postForm.action = action;
                _this.submitForm()
            })
        },
        methods: {
            submitForm: function () {
                let _this = this;
                let originWatch = this.editor.settings.watch;

                //开启预览，获取预览html
                let previewContent = this.editor.watch().getPreviewedHTML();

                if (!originWatch) {
                    //如果原本预览是关闭的,获取预览html后就关闭预览
                    this.editor.unwatch()
                }

                this.postForm.originalContent = this.editor.getMarkdown();
                this.postForm.content = previewContent;
                request({
                    url: window.location.pathname,
                    method: 'POST',
                    data:_this.postForm,
                    success: function (response) {
                        if (response.code === "success") {
                            //注册成功,转跳登录页面
                            $.toast('操作成功');
                            return;
                        }
                        $.toast(response.message, 'cancel')
                    }
                });
            }
        }
    });
</script>
</body>
</html>
