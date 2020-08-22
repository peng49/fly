<div class="header">
    <div class="left">

    </div>
    <div class="center">

    </div>
    <div class="right">
        <a href="/">首页</a>

        <#if Session["login-user"]??>
            <a href="/u/${(Session['login-user'].id)!}">主页</a>&nbsp;&nbsp;
            <a href="/user/center">个人中心</a>
        <#else>
            <a href="/user/login">登录</a>
            <a href="/user/register">注册</a>
        </#if>
    </div>
</div>