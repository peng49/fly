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

            <@shiro.user>
                <!-- 用户登入后显示 -->
                <li class="layui-hide-xs layui-show-md-inline-block">
                    <a href="/user/center">个人中心</a>
                </li>
            </@shiro.user>
        </ul>

        <div class="fly-column-right layui-hide-xs">
            <div class="fly-search">
                <p class="search-container">
                    <input type="text" name="keyword" value="" placeholder="请输入查询内容">
                    <i class="layui-icon icon-sousuo"></i>
                </p>
            </div>
            <a href="/post/add" class="layui-btn">发表新帖</a>
        </div>
    </div>
    <script type="text/javascript">
        let columns = document.getElementById("columns").getElementsByClassName("column")
        for (let i in columns) {
            if (/^\d+/.test(i)) {
                let column = columns[i]
                // console.log(column)
                let a = column.getElementsByTagName('a')[0]
                if (a.getAttribute("href") === window.location.pathname) {
                    column.setAttribute("class", column.getAttribute("class") + " layui-this")
                }
            }
        }
    </script>
</div>