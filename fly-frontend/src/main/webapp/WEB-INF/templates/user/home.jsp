<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>用户中心</title>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <meta name="keywords" content="fly,layui,前端社区">
    <meta name="description" content="Fly社区是模块化前端UI框架Layui的官网社区，致力于为web开发提供强劲动力">
    <jsp:include page="../common/link.jsp"/>
</head>
<body>
<jsp:include page="../common/header.jsp"/>

<div id="user-center" class="layui-container fly-marginTop fly-user-main">
    <user-nav></user-nav>
    <user-posts></user-posts>
</div>
<jsp:include page="../common/footer.jsp"/>
<script type="text/javascript">
    Vue.component('user-nav', {
        data: function () {
            return {
                navs:[
                    {label:"我的主页",icon:"&#xe609;",href:"/user/1"},
                    {label:"用户中心",icon:"&#xe612;",href:"javascript:"},
                    {label:"基本设置",icon:"&#xe620;",href:"javascript:"},
                    {label:"我的消息",icon:"&#xe611;",href:"javascript:"}
                ],
                active:1
            }
        },
        methods:{
            activeNav:function(index){
                this.active = index
            }
        },
        template: '<ul class="layui-nav layui-nav-tree layui-inline">' +
            '    <li v-for="(nav,index) in navs" class="layui-nav-item" :class="{active:index==active}" @click="activeNav(index)">' +
            '        <a :href="nav.href">' +
            '            <i class="layui-icon" v-html=nav.icon></i>' +
            '            {{nav.label}}' +
            '        </a>' +
            '    </li>' +
            '</ul>'
    });

    Vue.component("user-posts",{
        data:function(){
            return {
                tabs:[
                    {label:"我发的贴",total:0},
                    {label:"我收藏的帖",total:0}
                ],
                active:0,
            }
        },
        methods:{
            flushPosts:function(index){
                 this.active = index;
            }
        },
        template:'    <div class="fly-panel fly-panel-user" pad20>\n' +
            '        <div class="layui-tab layui-tab-brief">\n' +
            '            <ul class="layui-tab-title" >\n' +
            '                <li v-for="(tab,index) in tabs" v-on:click="flushPosts(index)" :class="{active:index==active}">{{tab.label}}（<span>{{tab.total}}</span>）</li>\n' +
            '            </ul>\n' +
            '            <div class="layui-tab-content" style="padding: 20px 0;">\n' +
            '                <div class="layui-tab-item layui-show">\n' +
            '                    <ul class="mine-view jie-row">\n' +
            '                        <li>\n' +
            '                            <a class="jie-title" href="/post/detail/1" target="_blank">基于 layui 的极简社区页面模版</a>\n' +
            '                            <i>2017/3/14 上午8:30:00</i>\n' +
            '                            <a class="mine-edit" href="/jie/edit/8116">编辑</a>\n' +
            '                            <em>661阅/10答</em>\n' +
            '                        </li>\n' +
            '                    </ul>\n' +
            '                </div>\n' +
            '            </div>\n' +
            '        </div>\n' +
            '    </div>'
    });

    new Vue({
        el:"#user-center",
        data:{
            table:"center",
            target:"collection",
        },
        methods:{

        }
    })
</script>
</body>
</html>