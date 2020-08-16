<#assign current_page= current_page />
<#assign page_size = page_size />
<#assign page_count = list_total / page_size />

<div id="${page_count}" class="laypage-main">
    <#if current_page gt 1>
        <a href="?page=${current_page - 1}&pageSize=${page_size}" class="laypage-prev">上一页</a>
    </#if>

    <#if current_page != 1>
        <a href="?page=1&pageSize=${page_size}" class="laypage-next">首页</a>
    </#if>

    <#if current_page lt page_count>
        <a href="?page=${page_count}&pageSize=${page_size}" class="laypage-last">尾页</a>
    </#if>

    <#if current_page != page_count && page_count gt current_page>
        <a href="?page=${current_page+1}&pageSize=${page_size}" class="laypage-next">下一页</a>
    </#if>
</div>
