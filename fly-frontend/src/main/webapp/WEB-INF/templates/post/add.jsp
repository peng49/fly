<%@ page import="java.util.List" %>
<%@ page import="fly.frontend.entity.Column" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>发表问题 编辑问题 公用</title>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <meta name="keywords" content="fly,layui,前端社区">
    <meta name="description" content="Fly社区是模块化前端UI框架Layui的官网社区，致力于为web开发提供强劲动力">
    <jsp:include page="../common/link.jsp" />
</head>
<body>

<jsp:include page="../common/header.jsp"/>

<div id="post-container" class="layui-container fly-marginTop">
    <div class="fly-panel" pad20 style="padding-top: 5px;">
        <!--<div class="fly-none">没有权限</div>-->
        <div class="layui-form layui-form-pane">
            <div class="layui-tab layui-tab-brief" lay-filter="user">
                <ul class="layui-tab-title">
                    <li class="layui-this">发表新帖<!-- 编辑帖子 --></li>
                </ul>
                <div class="layui-form layui-tab-content" id="LAY_ucm" style="padding: 20px 0;">
                    <div class="layui-tab-item layui-show">
                        <form action="" method="post">
                            <div class="layui-row layui-col-space15 layui-form-item">
                                <div class="layui-col-md3">
                                    <label class="layui-form-label">所在专栏</label>
                                    <div class="layui-input-block">
                                        <select v-model="postForm.columnId">
                                            <% List<Column> columns = (List<Column>) request.getAttribute("columns"); %>
                                            <% for (Column column : columns) {%>
                                            <option value="<%=column.getId()%>">
                                                <%=column.getName()%>
                                            </option>
                                            <%}%>
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
                            <div class="layui-row layui-col-space15 layui-form-item layui-hide" id="LAY_quiz">
                                <div class="layui-col-md3">
                                    <label class="layui-form-label">所属产品</label>
                                    <div class="layui-input-block">
                                        <select name="project">
                                            <option></option>
                                            <option value="layui">layui</option>
                                            <option value="独立版layer">独立版layer</option>
                                            <option value="独立版layDate">独立版layDate</option>
                                            <option value="LayIM">LayIM</option>
                                            <option value="Fly社区模板">Fly社区模板</option>
                                        </select>
                                    </div>
                                </div>
                                <div class="layui-col-md3">
                                    <label class="layui-form-label" for="L_version">版本号</label>
                                    <div class="layui-input-block">
                                        <input type="text" id="L_version" value="" name="version" autocomplete="off"
                                               class="layui-input">
                                    </div>
                                </div>
                                <div class="layui-col-md6">
                                    <label class="layui-form-label" for="L_browser">浏览器</label>
                                    <div class="layui-input-block">
                                        <input type="text" id="L_browser" value="" name="browser"
                                               placeholder="浏览器名称及版本，如：IE 11" autocomplete="off" class="layui-input">
                                    </div>
                                </div>
                            </div>
                            <div class="layui-form-item layui-form-text">
                                <div class="layui-input-block">
                                    <textarea v-model="postForm.content"
                                              class="layui-textarea fly-editor"
                                              style="height: 260px;"></textarea>
                                </div>
                            </div>
                            <div class="layui-form-item">
                                <button type="button" class="layui-btn" v-on:click="submitForm">立即发布</button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<jsp:include page="../common/footer.jsp"/>
<script type="text/javascript">
    new Vue({
        el: "#post-container",
        data: {
            postForm: {
                columnId: 1,
                title: "",
                content: ""
            }
        },
        methods: {
            submitForm: function () {
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