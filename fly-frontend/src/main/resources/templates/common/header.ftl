<div class="fly-header fly-panel fly-column">
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

            <#if Session["login-user"]??>
                <!-- 用户登入后显示 -->
                <li class="layui-hide-xs layui-hide-sm layui-show-md-inline-block">
                    <a href="/user/center">我发表的贴</a>
                </li>
                <li class="layui-hide-xs layui-hide-sm layui-show-md-inline-block">
                    <a href="/user/center#collection">我收藏的贴</a></li>
            </#if>
        </ul>

        <div class="fly-column-right layui-hide-xs">
            <span class="fly-search">
                <i class="layui-icon icon-sousuo"></i>
            </span>
            <a href="/post/add" class="layui-btn">发表新帖</a>
        </div>
    </div>
    <script type="text/javascript">
        let columns = document.getElementById("columns").getElementsByClassName("column")
        for (let i in columns) {
            if (/^\d+/.test(i)) {
                let column = columns[i]
                console.log(column)
                let a = column.getElementsByTagName('a')[0]
                if(a.getAttribute("href") === window.location.pathname){
                    column.setAttribute("class",column.getAttribute("class")+" layui-this")
                }
            }
        }
    </script>
</div>