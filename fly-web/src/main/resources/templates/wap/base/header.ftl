<div class="header">
    <div class="left">

    </div>
    <div class="center">

    </div>
    <div class="right">
        <a href="/">首页</a>

        <@shiro.user>
            <a href="/u/<@shiro.principal property="id"/>">主页</a>
            <a href="/user/center">个人中心</a>
        </@shiro.user>

        <@shiro.guest>
        <a href="/user/login">登录</a>
        <a href="/user/register">注册</a>
        </@shiro.guest>
    </div>
</div>