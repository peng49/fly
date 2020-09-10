<div class="fly-panel">
    <#if Session["login-user"]??>
        <div class="fly-panel-title">
            用户信息
        </div>
        <div class="fly-panel-main">
            <div class="avatar" style="width: 80px;height: 80px;display: inline-block">
                <img style="width: 100%;height: 100%;border-radius: 40px;" src="${(Session["login-user"].avatar)!}"
                     alt="${(Session["login-user"].username)!}">
            </div>
        </div>
    <#else>
        <div class="fly-panel-title">
            登录
        </div>
        <div class="fly-panel-main">
            <#-- 登录 -->
            <form action="" method="post" data-reload="true">
                <div class="layui-form-item">
                    <div class="layui-input-inline">
                        <input type="text" name="username" placeholder="请输入用户名/邮箱" class="layui-input">
                    </div>
                </div>
                <div class="layui-form-item">
                    <div class="layui-input-inline">
                        <input type="password" name="password" placeholder="请输入密码" class="layui-input">
                    </div>
                </div>
                <div class="layui-form-item">
                    <button type="button" onclick="loginSubmit(this)" class="layui-btn">立即登录</button>
                    <span style="padding-left: 20px;"><a href="#">忘记密码？</a></span>
                </div>
            </form>
        </div>
    </#if>
</div>