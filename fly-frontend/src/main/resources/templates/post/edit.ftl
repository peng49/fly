<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>发表问题</title>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <#include "../common/link.ftl" />
    <style>
        .fly-header {
            z-index: 0
        }
    </style>
</head>
<body>

<#include "../common/header.ftl" />

<div id="post-container" class="layui-container fly-marginTop">
    <div class="fly-panel" pad20 style="padding-top: 5px;">
        <div class="layui-form layui-form-pane">
            <div class="layui-tab layui-tab-brief">
                <ul class="layui-tab-title">
                    <li class="layui-this">
                        <span v-if="postId > 0">编辑主题</span>
                        <span v-else>发表主题</span>
                    </li>
                </ul>
                <div class="layui-form layui-tab-content" style="padding: 20px 0;">
                    <div class="layui-tab-item layui-show">
                        <form action="" method="post">
                            <div class="layui-row layui-col-space15 layui-form-item">
                                <div class="layui-col-md3">
                                    <label class="layui-form-label">所在专栏</label>
                                    <div class="layui-input-block">
                                        <select v-model="postForm.columnId">
                                            <#list columns as column>
                                                <option value="${column.id}">
                                                    ${column.name}
                                                </option>
                                            </#list>
                                        </select>
                                    </div>
                                </div>
                                <div class="layui-col-md9">
                                    <label class="layui-form-label">标题</label>
                                    <div class="layui-input-block">
                                        <input type="text" v-model="postForm.title" class="layui-input">
                                    </div>
                                </div>
                            </div>
                            <div class="layui-form-item layui-form-text">
                                <div class="editor-block" style="height: 650px">
                                    <div id="editor"><textarea>${(post.originalContent)!}</textarea>
                                    </div>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<#include "../common/footer.ftl" />
<link rel="stylesheet" href="/static/editor.md/css/editormd.min.css"/>
<script src="/static/editor.md/editormd.min.js"></script>
<script type="text/javascript">
    new Vue({
        el: "#post-container",
        data: {
            editor: '',
            postId: '${(post.id?c)!}',
            postStatus: '${(post.status)!}',
            saveUrl: window.location.pathname,
            postForm: {
                action: 'update',
                postId: '${(post.id?c)!}',
                columnId: '${(post.columnId)!1}',
                title: "${(post.title)!}",
                originalContent: "",
                content: ""
            },
            postDraft:{

            },
            draftLock: false
        },
        mounted: function () {
            let _this = this;
            let editorBar = ["undo", "redo", "|", "bold", "hr", "watch", "fullscreen"];


            if (_this.postId > 0) {
                if (Number(_this.postStatus) === 0) {//如果未发布
                    editorBar.push('publish');
                }
                editorBar.push('update');
            } else {
                editorBar.push('publish');
                editorBar.push('save2draft');
            }

            //新增的时候取本地缓存的数据
            if (!_this.postId && localStorage && localStorage.getItem("markdownContent")) {
                //设置markdown
                console.log("通过localStorage初始化编辑器的markdown内容");
                document.getElementById("editor").getElementsByTagName("textarea")[0].innerText = localStorage.getItem("markdownContent");
            }

            _this.editor = editormd('editor', {
                path: "/static/editor.md/lib/",
                /**
                 *  full:["undo","redo","|","bold","del","italic","quote","ucwords","uppercase","lowercase","|","h1","h2","h3","h4","h5","h6","|","list-ul","list-ol","hr","|","link","reference-link","image","code","preformatted-text","code-block","table","datetime","emoji","html-entities","pagebreak","|","goto-line","watch","preview","fullscreen","clear","search","|","help","info"],
                 simple:["undo","redo","|","bold","del","italic","quote","uppercase","lowercase","|","h1","h2","h3","h4","h5","h6","|","list-ul","list-ol","hr","|","watch","preview","fullscreen","|","help","info"],
                 mini:["undo","redo","|","watch","preview","|","help","info"]
                 * @returns {string[]}
                 */
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
                watch: true,
                imageUpload: true, //开启图片上传
                imageUploadURL: '/post/upload', //图片上传后台地址
                flowChart: true,
                onload: function () {
                    $('.editormd-menu li .layui-btn').parent('li').addClass('pull-right');

                    // 引入插件 执行监听方法
                    editormd.loadPlugin("/static/editor.md/plugins/image-handle-paste/image-handle-paste", function () {
                        _this.editor.imagePaste();
                    });
                },
                onchange:function(){
                    //保存到本地 localStorage
                   /* if (!_this.postId && _this.editor.getMarkdown() !== localStorage.getItem("markdownContent")) {
                        console.log("设置localStorage中的markdown");
                        localStorage.setItem("markdownContent", _this.editor.getMarkdown())
                    } else {
                        console.log("没变动，不保存")
                        return;
                    }*/

                    if(!_this.draftLock){
                        console.log("save draft")
                        _this.draftLock = true
                        //5秒后保存
                        setTimeout(function () {
                            _this.postForm.originalContent = _this.editor.getMarkdown();
                            _this.postForm.content = "缺省值";//无用字段
                            axios.post('/post/draft', _this.postForm)
                                .then(function (response) {
                                    _this.draftLock = false; //放开锁
                                    if (response.code === 'success') {
                                        //清除自动保存的定时任务
                                        console.log("备份数据成功")
                                    }
                                })
                        }, 5000);
                    }
                }
            });

            //通过jquery监听新加的按钮触发提交
            $('body').on('click', '.post-submit-btn', function () {
                _this.postForm.action = $(this).data('action');
                _this.postSubmit()
            });
        },
        methods: {
            postSubmit: function () {
                let _this = this;
                // console.log(this.editor.getMarkdown());
                // console.log(this.editor.getHTML());
                // console.log(this.editor.watch().getPreviewedHTML());

                let originWatch = this.editor.settings.watch;

                _this.draftLock = true

                //开启预览，获取预览html  (review -> onchange)
                let previewContent = this.editor.watch().getPreviewedHTML();
                if (!originWatch) {
                    //如果原本预览是关闭的,获取预览html后就关闭预览
                    this.editor.unwatch()
                }

                this.postForm.originalContent = this.editor.getMarkdown();
                this.postForm.content = previewContent;

                _this.draftLock = false


                axios.post(_this.saveUrl, this.postForm)
                    .then(function (response) {
                        if (response.code === "success") {
                            if (response.data && response.data.id) {
                                //修改浏览器url
                                history.pushState({}, "", '/post/edit/' + response.data.id);

                                console.log("删除localStorage中的markdown内容");
                                localStorage.removeItem("markdownContent");
                                _this.postForm.postId = _this.postId = response.data.id
                                _this.saveUrl = '/post/edit/' + response.data.id;
                            }
                            console.log(response);
                            layer.msg('操作成功');
                            return;
                        }
                        layer.msg(response.message)
                    })
                    .catch(function (error) {
                        console.log(error);
                    })
            },
            getDraft: function () {
                //
            },
            useDraft: function(){

            }
        }
    })
</script>
</body>
</html>