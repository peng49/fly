//获取文章内容
let content = document.getElementsByClassName('detail-body')[0].innerHTML
// let content = $('.detail-body').html()

//通过正则获取html内容中的所有html标签
let matches = content.match(/<h(\d) .*?>.*?<\/h\d>/g)
//遍历标签生成一个目录数组
let tree = []
let rootLevel = 1
let parents = []
for (i in matches) {
    let h = matches[i]
    let m = h.match(/<h(\d) /)
    let level = Number(m[1])
    if (tree.length === 0) {
        rootLevel = level
    }
    let con = h.replace(/<\/?\w.*?>/g, '')
    //console.log(con)
    console.log(level)
    let id = Number(i) + 1
    let target = h.match(/<h\d id="(.*?)"/)[1]

    if (rootLevel === level) {
        tree.push({id: id, name: con, target: target, level: level, parentId: 0})

        parents = [{id: 0, level: 1}, {id: id, level: level}]
    } else {
        if (level - parents[parents.length - 1].level < 0) {
            parents.pop()
        }

        tree.push({id: id, name: con, target: target, level: level, parentId: parents[parents.length - 1].id})

        if (level - parents[parents.length - 1].level > 1) {
            parents.push({id: id, level: level})
        }
    }
}
console.log(tree)

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
for (i in tree) {
    let item = tree[i]
    if (item.parentId == 0) {
        item.children = getChildren(tree, item)
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
        ul += "<li><a href='#" + item.target + "'>" + item.name + "</a>" + childrenHtml + "</li>"
    }
    return ul
}

let ul = "<ul>" + renderTree(result, 1) + "</ul>";

$("#slider").append('<div class="fly-panel slider-menu"><div class="fly-panel-title">目录</div><div class="fly-panel-main">' + ul + '</div></div>');
