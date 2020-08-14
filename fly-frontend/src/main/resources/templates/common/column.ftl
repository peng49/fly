<div class="fly-panel fly-column">
    <div class="layui-container">
        <ul id="columns" class="layui-clear">
            <li class="column"><a href="/">首页</a></li>

            <#list columns as column>
                <li class="column">
                    <a href="/column/${column.id}">
                        ${column.name}
                    </a>
                </li>
            </#list>

            <li class="layui-hide-xs layui-hide-sm layui-show-md-inline-block"><span class="fly-mid"></span></li>

            <#if Session["login-user"]??>
                <!-- 用户登入后显示 -->
                <li class="layui-hide-xs layui-hide-sm layui-show-md-inline-block">
                    <a href="/user/home">我发表的贴</a>
                </li>
                <li class="layui-hide-xs layui-hide-sm layui-show-md-inline-block">
                    <a href="/user/home#collection">我收藏的贴</a></li>
            </#if>
        </ul>

        <div class="fly-column-right layui-hide-xs">
            <span class="fly-search">
                <i class="layui-icon icon-sousuo"></i>
            </span>
            <a href="/post/add" class="layui-btn">发表新帖</a>
        </div>
    </div>
</div>
<script type="text/javascript">
    let columns = document.getElementById("columns").getElementsByClassName("column")
    for (let i in columns) {
        if (window.location.pathname == columns.item(i).getElementsByTagName("a")[0].getAttribute("href")) {
            columns.item(i).className = columns.item(i).className + " layui-this"
        }
    }
</script>