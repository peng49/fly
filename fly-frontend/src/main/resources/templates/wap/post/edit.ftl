<!DOCTYPE html>
<html>
<head>
    <meta name="viewport"
          content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no">
    <meta charset='utf-8'>
    <title>用户中心</title>
    <#include "../base/link.ftl" />
</head>
<body>
<#include "../base/header.ftl" />
<#include "../base/nav.ftl" />

<div class="container">
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
                            <select class="weui-select" name="cloumnId">
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
                            <input class="weui-input" type="text" placeholder="请输入标题">
                        </div>
                    </div>
                </div>
                <div class="weui-cells__title">内容</div>
                <div id="editor" style="min-height: 400px;height: 400px;z-index: 100;"></div>
            </div>
        </div>
    </div>
</div>
<#include "../base/footer.ftl" />
<link rel="stylesheet" href="/static/editor.md/css/editormd.min.css"/>
<script src="/static/editor.md/editormd.min.js"></script>
<script type="text/javascript">
    let editorBar = ["undo", "redo", "|", "bold", "hr", "watch", "fullscreen", 'save2draft', 'publish'];

    let editor = editormd('editor', {
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
            $('#editor .CodeMirror').css({width: 0});
            $('#editor .editormd-preview').css({width: '100%'});
        },
        imageUpload: true, //开启图片上传
        imageUploadURL: '/post/upload', //图片上传后台地址
        onload: function () {
            $('.editormd-menu li .layui-btn').parent('li').addClass('pull-right');

            // 引入插件 执行监听方法
            editormd.loadPlugin("/static/editor.md/plugins/image-handle-paste/image-handle-paste", function () {
                editor.imagePaste();
            });
        },
        state: {
            preview: true
        }
    });
</script>
</body>
</html>
