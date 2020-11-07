<#assign current_page= current_page />
<#assign page_size = page_size />
<#assign page_count = (list_total / page_size)?ceiling />

<#if current_page - 3 gte 1>
    <#assign start_page = current_page - 3 />
<#else>
    <#assign start_page = 1 />
</#if>

<#if start_page + 6 lte page_count>
    <#assign end_page = start_page + 6 />
<#else>
    <#assign end_page = page_count />
</#if>

<#if page_count gt 1 >
    <div id="${page_count}" class="laypage-main">
        <#if current_page gt 1>
            <a href="?page=${current_page - 1}&pageSize=${page_size}" class="laypage-prev"> < </a>
        <#else>
            <a href="javascript:" class="laypage-prev disabled"> < </a>
        </#if>

        <#if start_page - 1 gt 1>
            <a href="?page=1&pageSize=${page_size}" class="laypage-prev">1</a>
            <a href="?page=2&pageSize=${page_size}" class="laypage-prev">2</a>
            <a href="javascript:" class="laypage-prev disabled">...</a>
        </#if>

        <#list start_page..end_page as page>
            <a href="?page=${page}&pageSize=${page_size}"
               class="laypage-last ${(current_page == page)?string("laypage-curr","")}">${page}</a>
        </#list>

        <#if end_page + 2 lt page_count>
            <a href="javascript:" class="laypage-prev disabled">...</a>
            <a href="?page=${page_count-1}&pageSize=${page_size}" class="laypage-prev">${page_count-1}</a>
            <a href="?page=${page_count}&pageSize=${page_size}" class="laypage-prev">${page_count}</a>
        </#if>

        <#if current_page != page_count && page_count gt current_page>
            <a href="?page=${current_page+1}&pageSize=${page_size}" class="laypage-next"> > </a>
        <#else>
            <a href="javascript:" class="laypage-next disabled"> > </a>
        </#if>
    </div>
</#if>