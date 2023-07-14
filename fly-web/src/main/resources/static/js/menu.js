//获取文章内容

let menus = [];
let minNumber = 6;
document.querySelector(".detail-body").querySelectorAll("h1,h2,h3,h4,h5,h6").forEach(hTag => {
    "use strict";
    let tagNumber = Number(/h(\d)/i.exec(hTag.tagName)[1]);
    if (tagNumber < minNumber) {
        minNumber = tagNumber;
    }
    let offsetTop= $('#'+hTag.id).offset().top;
    menus.push({
        tag: hTag.tagName,
        tagNumber: tagNumber,
        offsetTop: offsetTop,
        id: hTag.id,
        title: hTag.innerText
    });
});
let liContent = "";
menus.forEach(menu => {
    "use strict";
    let padding = 0;
    if(menu.tagNumber - minNumber > 0){
        padding = (menu.tagNumber - minNumber) * 15;
    }
    liContent += "<li><a style='padding-left: "+padding+"px' href='javascript:' data-target='" + menu.id + "'>" + menu.title + "</a></li>";
});

let ul = "<ul id='post-menus'>"+liContent+"</ul>";

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
    for(let i in menus){
        let menu = menus[i];
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
        $('#menu-box a[data-target="'+activeMenu.id+'"]').addClass('active')
    }
});
