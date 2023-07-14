<div class="fly-panel">
    <@shiro.guest>
        <#-- 游客(未登录)        -->
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
                    <span style="padding-left: 20px;"><a href="/user/forget">忘记密码？</a></span>
                </div>
            </form>
        </div>
    </@shiro.guest>

    <@shiro.user>
        <#-- 已登录   -->
        <div class="fly-panel-title">
            用户信息
        </div>
        <div class="fly-panel-main">
            <div class="avatar" style="width: 80px;height: 80px;display: inline-block">
                <img style="width: 100%;height: 100%;border-radius: 40px;" src="<@shiro.principal property="avatar"/>"
                     alt="<@shiro.principal property="username"/>">
            </div>
        </div>
    </@shiro.user>
</div>