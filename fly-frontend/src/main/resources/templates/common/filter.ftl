<div id="post-filter" class="fly-panel-title fly-filter">
    <a href="" class="filter-all">全部</a>
    <span class="fly-mid"></span>
    <a href="" class="filter-essence">精华</a>

    <span class="fly-filter-right layui-hide-xs">
        <a href="" class="order-publish_at">按最新</a>
        <span class="fly-mid"></span>
        <a href="" class="order-reply_count">按热议</a>
    </span>
</div>
<script type="text/javascript">
    //init filter url by current url
    function initUrl(key, value) {
        let current = window.location.href
        //先判断url 中是否有指定的key
        let reg = new RegExp(key + '=\\w+')
        if (current.search(reg) >= 0) {
            //存在
            return current.replace(reg, key + '=' + value)
        } else {
            //不存在
            if (current.search(/\?/) >= 0) {
                return current + '&' + key + '=' + value
            } else {
                return current + '?' + key + '=' + value
            }
        }
    }

    let activeTags = []
    //filter
    let filterMatch = /list=(\w+)/.exec(window.location.href)
    activeTags[0] = filterMatch ? filterMatch[1] : 'all'

    //order
    let orderMatch = /orderBy=(\w+)/.exec(window.location.href)
    activeTags[1] = orderMatch ? orderMatch[1] : ''
    console.log(activeTags)
    //init url
    let tags = document.getElementById('post-filter').getElementsByTagName('a');
    for (let i in tags) {
        let a = tags[i]
        if (!a.className) {
            continue
        }
        let item = a.className.split(' ')[0].split('-')
        switch (item[0]) {
            case 'filter':
                if (item[1] == activeTags[0]) {
                    a.className = a.className + ' layui-this'
                }
                a.setAttribute("href", initUrl('list', item[1]))
                break;
            case 'order':
                if (item[1] === activeTags[1]) {
                    a.className = a.className + ' layui-this'
                }
                a.setAttribute("href", initUrl('orderBy', item[1]))
                break;
        }
    }
</script>