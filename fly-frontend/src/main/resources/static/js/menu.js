//获取文章内容
let content = document.getElementsByClassName('detail-body')[0].innerHTML
// let content = $('.detail-body').html()

//通过正则获取html内容中的所有html标签
let matches = content.match(/<h(\d) .*?>.*?<\/h\d>/g)
//遍历标签生成一个目录数组
let allMenu = []
let rootLevel = 1
let parents = []
for (i in matches) {
    let h = matches[i]
    let m = h.match(/<h(\d) /)
    let level = Number(m[1])
    if (allMenu.length === 0) {
        rootLevel = level
    }
    let con = h.replace(/<\/?\w.*?>/g, '')
    //console.log(con)
    console.log(level)
    let id = Number(i) + 1
    let target = h.match(/<h\d id="(.*?)"/)[1];

    if ($('[id="' + target + '"]').length > 1) {
        let newId = target + Math.random().toString(36).slice(-10);
        $($('[id=' + target + ']').get(0)).attr('id', newId)
        target = newId
    }
    console.log(target)
    let offsetTop = $('#'+target).offset().top;

    if (rootLevel === level) {
        allMenu.push({id: id, name: con, target: target, offsetTop: offsetTop, level: level, parentId: 0})

        parents = [{id: 0, level: 1}, {id: id, level: level}]
    } else {
        if (level - parents[parents.length - 1].level < 0) {
            parents.pop()
        }

        allMenu.push({
            id: id,
            name: con,
            target: target,
            offsetTop: offsetTop,
            level: level,
            parentId: parents[parents.length - 1].id
        });

        if (level - parents[parents.length - 1].level > 1) {
            parents.push({id: id, level: level})
        }
    }
}
console.log(allMenu)

//渲染数组生成标签
function getChildren(items, parent) {
    let result = []
    for (i in items) {
        let item = items[i]
        if (item.parentId == parent.id) {
            let children = getChildren(items, item)
            if (children.length > 0) {
                item.children = children
            }
            result.push(item)
        }
    }
    return result
}

let result = []
for (i in allMenu) {
    let item = allMenu[i]
    if (item.parentId == 0) {
        item.children = getChildren(allMenu, item)
        result.push(item)
    }
}
console.log(result)

function renderTree(items, level) {
    let ul = ""
    for (i in items) {
        let item = items[i]
        let childrenHtml = ""
        if (item.children && item.children.length > 0) {
            level++
            childrenHtml = "<ul class='sub-list d" + level + "'>" + renderTree(item.children, level) + "</ul>"
        }
        ul += "<li><a href='javascript:' data-target='" + item.target + "'>" + item.name + "</a>" + childrenHtml + "</li>"
    }
    return ul
}

let ul = "<ul id='post-menus'>" + renderTree(result, 1) + "</ul>";

$("#slider").append('<div id="menu-box" class="fly-panel slider-menu"><div class="fly-panel-title">目录</div><div class="fly-panel-main">' + ul + '</div></div>');

//点击目录滑动到指定div
$("body").on("click", "#post-menus a", function (e) {
    let id = $(e.target).attr("data-target")
    $(window).scrollTop(document.getElementById(id).offsetTop)
});

let menuDom = $('#menu-box');
let offsetTop = $('#slider').offset().top;
let width = $('#slider').width();
let menuOffsetTop = $("#menu-box").offset().top;
let scrollFlag = true;
let clientHeight = $(window).height();

function getActiveMenu()
{
    let scrollTop = $(window).scrollTop()
    for(let i in allMenu){
        let menu = allMenu[i];
        let margin = 0;
        if ($('.fly-column').get(0)) {
            margin = $('.fly-column').get(0).clientHeight
        }
        if (menu.offsetTop - margin > scrollTop) {
            return menu
        }
    }
    return null;
}

$(window).scroll(function () {
    if ($(window).scrollTop() > menuOffsetTop) {
        if (scrollFlag) {
            menuDom.css({position: "fixed", top: offsetTop, width: width});
            menuDom.find('.fly-panel-main').css({overflowY:'scroll',maxHeight:clientHeight+'px'});
            scrollFlag = false
        }
    } else {
        menuDom.attr("style", "");
        scrollFlag = true
    }

    let activeMenu = getActiveMenu();
    if(activeMenu !== null){
        $('#menu-box a').removeClass('active');
        $('#menu-box a[data-target="'+activeMenu.target+'"]').addClass('active')
    }
});
