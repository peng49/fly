<div class="nav-container">
    <div class="nav">
        <ul id="nav-list">
            <li class=""><a href="/">全部</a></li>
            <#list columns as column>
                <li><a href="/column/${column.id}">${column.name}</a></li>
            </#list>
        </ul>

        <a class="" href="/">发布</a></div>
</div>
<script type="text/javascript">
    let columns = document.getElementById("nav-list").getElementsByTagName("a")
    for (let i in columns) {
        if (/^\d+/.test(i)) {
            let a = columns[i]
            if(a.getAttribute("href") === window.location.pathname){
                a.setAttribute("class",a.getAttribute("class")+" active")
            }
        }
    }
</script>