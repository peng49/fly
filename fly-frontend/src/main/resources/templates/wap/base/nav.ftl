<div class="nav-container">
    <div class="nav">
        <ul id="nav-list">
            <#list __nav__ as nav>
                <li><a href="${nav.url}">${nav.title}</a></li>
            </#list>
        </ul>
        <a class="" href="/post/add">发布</a></div>
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