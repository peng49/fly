<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>发表问题</title>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <meta name="keywords" content="fly,layui,前端社区">
    <meta name="description" content="Fly社区是模块化前端UI框架Layui的官网社区，致力于为web开发提供强劲动力">
    <jsp:include page="../common/link.jsp"/>
</head>
<body>

<jsp:include page="../common/header.jsp"/>

<div id="post-container" class="layui-container fly-marginTop">
    <div class="fly-panel" pad20 style="padding-top: 5px;">
        <div class="layui-form layui-form-pane">
            <div class="layui-tab layui-tab-brief">
                <ul class="layui-tab-title">
                    <li class="layui-this">发表新帖<!-- 编辑帖子 --></li>
                </ul>
                <div class="layui-form layui-tab-content" style="padding: 20px 0;">
                    <div class="layui-tab-item layui-show">
                        <form action="" method="post">
                            <div class="layui-row layui-col-space15 layui-form-item">
                                <div class="layui-col-md3">
                                    <label class="layui-form-label">所在专栏</label>
                                    <div class="layui-input-block">
                                        <select v-model="postForm.columnId">
                                            <c:forEach items="${columns}" var="column">
                                                <option value="${column.id}">
                                                        ${column.name}
                                                </option>
                                            </c:forEach>
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
                                <div class="editor-block" style="height: 500px">
                                    <div id="editor" ref="editor"></div>
                                </div>
                            </div>
                            <div class="layui-form-item">
                                <button type="button" class="layui-btn" @click="submitForm">立即发布</button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<jsp:include page="../common/footer.jsp"/>
<link rel="stylesheet" href="/static/editor.md/css/editormd.min.css"/>
<script src="/static/editor.md/editormd.min.js"></script>
<script type="text/javascript">

</script>

<script type="text/javascript">
    new Vue({
        el: "#post-container",
        data: {
            editor:'',
            postForm: {
                columnId: 1,
                title: "",
                content: ""
            }
        },
        mounted:function(){
            this.editor = editormd('editor', {
                path: "/static/editor.md/lib/",
                saveHTMLToTextarea : true,
                height: "100%",
            })
        },
        methods: {
            submitForm: function () {
                console.log(this.editor.getMarkdown());
                console.log(this.editor.getHTML());
                console.log(this.editor.getPreviewedHTML());

                this.postForm.content = this.editor.getPreviewedHTML();
                axios.post('/post/add', this.postForm)
                    .then(function (response) {
                        if (response.code === "success") {
                            //注册成功,转跳登录页面
                            layer.msg('添加成功');
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