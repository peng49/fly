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
    <div class="fly-panel fly-panel-user" pad20>
        <component :is="component"></component>
    </div>
</div>
<jsp:include page="../common/footer.jsp"/>
<script type="text/javascript">
    Vue.component('user-nav', {
        data: function () {
            return {
                navs: [
                    {label: "我的主页", icon: "&#xe609;", href: "/user/index/1", component: ""},
                    {label: "用户中心", icon: "&#xe612;", href: "javascript:", component: "user-center"},
                    {label: "基本设置", icon: "&#xe620;", href: "javascript:", component: "user-setting"},
                    {label: "我的消息", icon: "&#xe611;", href: "javascript:", component: "user-message"},
                ],
                active: 1
            }
        },
        created: function () {
            this.setActiveTabByHash();
            // 创建vm实例后执行
            // 浏览器控制按钮前进后退触发函数
            window.addEventListener('popstate', this.popstate, false);
        },
        destroyed() {
            // 销毁vm组件
            // 避免堆栈溢出，多次创建、多次触发
            window.removeEventListener('popstate', this.popstate, false);
        },
        methods: {
            setActiveTabByHash: function () {
                let hash = window.location.hash;
                for (let index in this.navs) {
                    if (hash.slice(1) === this.navs[index].component && hash) {
                        this.active = index;
                        this.$parent.component = this.navs[index].component;
                    }
                }
            },
            popstate: function () {
                this.setActiveTabByHash()
            },
            activeNav: function (index) {
                this.active = index;
                this.$parent.component = this.navs[index].component;
                window.location.hash = "#" + this.navs[index].component
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
    Vue.component("user-center", {
        data: function () {
            return {
                tabs: [
                    {label: "我发的贴", total: 0, key: "my"},
                    {label: "我收藏的帖", total: 0, key: "collection"}
                ],
                active: 0,
                posts: []
            }
        },
        created: function () {
            this.flushPosts(this.active)
        },
        methods: {
            flushPosts: function (index) {
                let _this = this;
                _this.active = index;

                axios.get("/user/posts?type=" + this.tabs[index].key).then(function (response) {
                    if (response.code === "success") {
                        _this.posts = response.posts
                    } else {
                        console.log(response.message)
                    }
                }).catch(function (error) {
                    console.log(error)
                })
            }
        },
        template: ` <div class="layui-tab layui-tab-brief">
            <ul class="layui-tab-title" >
                <li v-for="(tab,index) in tabs" v-on:click="flushPosts(index)" :class="{active:index==active}">{{tab.label}}（<span>{{tab.total}}</span>）</li>
            </ul>
            <div class="layui-tab-content" style="padding: 20px 0;">
                <div class="layui-tab-item layui-show">
                    <ul class="mine-view jie-row">
                        <li v-for="post in posts">
                            <a class="jie-title" :href="'/post/detail/'+post.id" target="_blank">{{post.title}}</a>
                            <i>{{post.publishAt}}</i>
                            <a class="mine-edit" :href="'/post/edit/'+post.id">编辑</a>
                            <em>{{post.viewCount}}阅/{{post.replyCount}}答</em>
                        </li>
                    </ul>
                </div>
            </div>
        </div>`
    });
    Vue.component("user-setting", {
        components: {
            "info": {
                data: function () {
                    return {
                        info: {
                            email: "",
                            username: "",
                            city: "",
                            signature: ""
                        }
                    }
                },
                created: async function () {
                    let info = await this.$parent.getInfo();
                    this.info = info;
                },
                methods: {
                    updateInfo: function () {
                        let _this = this;
                        axios.post('/user/updateInfo', _this.info)
                            .then(function (response) {
                                if (response.code === "success") {
                                    layer.msg("操作成功");
                                    _this.info = response.user;
                                }
                            })
                            .catch(function (error) {
                                console.log(error);
                            });
                    }
                },
                template: '<div class="container">' +
                    '       <div class="layui-form-item">' +
                    '            <label class="layui-form-label">邮箱</label>' +
                    '            <div class="layui-input-inline">' +
                    '                <input type="text" v-model="info.email" class="layui-input">' +
                    '            </div>' +
                    '            <div class="layui-form-mid layui-word-aux">' +
                    '               如果您在邮箱已激活的情况下，变更了邮箱，需<span style="font-size: 12px; color: #4f99cf;">重新验证邮箱</span>' +
                    '            </div>' +
                    '        </div>' +
                    '        <div class="layui-form-item">' +
                    '            <label class="layui-form-label">昵称</label>' +
                    '            <div class="layui-input-inline">' +
                    '                <input type="text" v-model="info.username" class="layui-input">' +
                    '            </div>' +
                    '            <div class="layui-inline">' +
                    '                <div class="layui-input-inline">' +
                    '                    <div class="layui-unselect layui-form-radio layui-form-radioed"><i class="layui-anim layui-icon"></i><span>男</span></div>' +
                    '                    <div class="layui-unselect layui-form-radio"><i class="layui-anim layui-icon"></i><span>女</span></div>' +
                    '                </div>' +
                    '            </div>' +
                    '        </div>' +
                    '        <div class="layui-form-item">' +
                    '            <label class="layui-form-label">城市</label>' +
                    '            <div class="layui-input-inline">' +
                    '                <input type="text" v-model="info.city" class="layui-input">' +
                    '            </div>' +
                    '        </div>' +
                    '        <div class="layui-form-item layui-form-text">' +
                    '            <label class="layui-form-label">签名</label>' +
                    '            <div class="layui-input-block">' +
                    '                <textarea placeholder="随便写些什么刷下存在感" v-model="info.signature" class="layui-textarea" style="height: 80px;"></textarea>' +
                    '            </div>' +
                    '        </div>' +
                    '        <div class="layui-form-item">' +
                    '            <button class="layui-btn" type="button" @click="updateInfo">确认修改</button>' +
                    '        </div>' +
                    '</div>'
            },
            "avatar": {
                data: function () {
                    return {
                        avatar:{},
                        previewUrl:"https://tva1.sinaimg.cn/crop.0.0.118.118.180/5db11ff4gw1e77d3nqrv8j203b03cweg.jpg"
                    }
                },
                methods: {
                    uploadAvatar: function (event) {
                        this.avatar = event.target.files[0];
                        let url = null;
                        if (window.createObjectURL != undefined) { // basic
                            url = window.createObjectURL(this.avatar);
                        } else if (window.URL != undefined) { // mozilla(firefox)
                            url = window.URL.createObjectURL(this.avatar);
                        } else if (window.webkitURL != undefined) { // webkit or chrome
                            url = window.webkitURL.createObjectURL(this.avatar);
                        }
                        this.previewUrl = url;

                        let Form = new FormData();
                        Form.append("avatar",Form);

                        axios.post('/user/uploadAvatar',Form)
                            .then(function (response) {
                                if (response.code === "success") {

                                }
                                console.log(response)
                            })
                            .catch(function (error) {
                                console.log(error);
                            });



                    }
                },
                template: '<div class="container">' +
                    '           <div class="layui-form-item">\n' +
                    '              <div class="avatar-add">\n' +
                    '                <p>建议尺寸168*168，支持jpg、png、gif，最大不能超过50KB</p>\n' +
                    '                <button @click="uploadAvatar" type="button" class="layui-btn upload-img">\n' +
                    '                  <i class="layui-icon">&#xe67c;</i>上传头像\n' +
                    '                  <input type="file" class="upload-avatar" @change="uploadAvatar" />' +
                    '                </button>\n' +
                    '                <img :src="previewUrl">\n' +
                    '                <span class="loading"></span>\n' +
                    '              </div>\n' +
                    '            </div>' +
                    '       </div>'
            },
            "password": {
                methods: {
                    resetPassword: function () {

                    },
                },
                template: '<div class="container">' +
                    '         <div class="layui-form-item">\n' +
                    '           <label class="layui-form-label">当前密码</label>\n' +
                    '           <div class="layui-input-inline">\n' +
                    '             <input type="password" class="layui-input">\n' +
                    '           </div>\n' +
                    '         </div>\n' +
                    '         <div class="layui-form-item">\n' +
                    '           <label  class="layui-form-label">新密码</label>\n' +
                    '           <div class="layui-input-inline">\n' +
                    '             <input type="password"  class="layui-input">\n' +
                    '           </div>\n' +
                    '           <div class="layui-form-mid layui-word-aux">6到16个字符</div>\n' +
                    '         </div>\n' +
                    '         <div class="layui-form-item">\n' +
                    '           <label class="layui-form-label">确认密码</label>\n' +
                    '           <div class="layui-input-inline">\n' +
                    '             <input type="password"  class="layui-input">\n' +
                    '           </div>\n' +
                    '         </div>\n' +
                    '         <div class="layui-form-item">\n' +
                    '           <button class="layui-btn" type="submit" @click="resetPassword">确认修改</button>\n' +
                    '         </div>' +
                    '   </div>'
            },
            "account": {
                template: '<div class="container">' +
                    '       <ul class="app-bind">\n' +
                    '          <li class="fly-msg app-havebind">\n' +
                    '            <i class="iconfont icon-qq"></i>\n' +
                    '            <span>已成功绑定，您可以使用QQ帐号直接登录Fly社区，当然，您也可以</span>\n' +
                    '            <a href="javascript:;" class="acc-unbind" type="qq_id">解除绑定</a>\n' +
                    '          </li>\n' +
                    '          <li class="fly-msg">\n' +
                    '            <i class="iconfont icon-weibo"></i>\n' +
                    '            <a href="" class="acc-weibo" type="weibo_id"  onclick="layer.msg(\'正在绑定微博\', {icon:16, shade: 0.1, time:0})" >立即绑定</a>\n' +
                    '            <span>，即可使用微博帐号登录Fly社区</span>\n' +
                    '          </li>\n' +
                    '       </ul>' +
                    '   </div>'
            }
        },
        data: function () {
            return {
                tabs: [
                    {label: "我的资料", component: "info"},
                    {label: "头像", component: "avatar"},
                    {label: "密码", component: "password"},
                    {label: "帐号绑定", component: "account"}
                ],
                active: 0,
                component: "info"
            }
        },
        methods: {
            activeTab: function (index) {
                this.active = index;
                this.component = this.tabs[index].component
            },
            getInfo: async function () {
                let info = {};
                await axios.get('/user/info')
                    .then(function (response) {
                        if (response.code === "success") {
                            console.log("data:", response.user)
                            info = response.user;
                        }
                    })
                    .catch(function (error) {
                        console.log(error);
                    });
                console.log(info);
                return info;
            }
        },
        template: '<div class="layui-tab layui-tab-brief" lay-filter="user">' +
            '            <ul class="layui-tab-title" id="LAY_mine">' +
            '                <li v-for="(tab,index) in tabs" :class="{active:index==active}" @click="activeTab(index)">{{tab.label}}</li>' +
            '            </ul>' +
            '            <div class="layui-tab-content" style="padding: 20px 0;">' +
            '                <div class="layui-form layui-form-pane layui-tab-item layui-show">' +
            '                     <component :is="component"></component>' +
            '                </div>' +
            '            </div>' +
            '        </div>'
    });
    Vue.component("user-message", {
        template: `<div class="layui-tab layui-tab-brief" style="margin-top: 15px;">
            <button class="layui-btn layui-btn-danger">清空全部消息</button>
            <div style="margin-top: 10px;">
                <ul class="mine-msg">
                    <li data-id="123">
                        <blockquote class="layui-elem-quote">
                            <a href="/jump?username=Absolutely" target="_blank"><cite>Absolutely</cite></a>回答了您的求解
                            <a target="_blank" href="/jie/8153.html/page/0/#item-1489505778669">
                                <cite>layui后台框架</cite>
                            </a>
                        </blockquote>
                        <p>
                            <span>1小时前</span>
                            <a href="javascript:;" class="layui-btn layui-btn-small layui-btn-danger fly-delete">删除</a>
                        </p>
                    </li>
                    <li data-id="123">
                        <blockquote class="layui-elem-quote">
                            系统消息：欢迎使用 layui
                        </blockquote>
                        <p>
                            <span>1小时前</span>
                            <a href="javascript:;" class="layui-btn layui-btn-small layui-btn-danger fly-delete">删除</a>
                        </p>
                    </li>
                </ul>
            </div>
        </div>`
    });

    new Vue({
        el: "#user-center",
        data: {
            component: window.location.hash ? window.location.hash.slice(1) : "user-center",
        },
        methods: {}
    })
</script>
</body>
</html>