let serializeArrayToJson = function (array) {
    let object = {};
    array.forEach(function (item) {
        object[item['name']] = item['value']
    });
    return object;
};
let message = function (msg, type) {
    if ($.toast) {
        if (type && type === 'error') {
            $.toast(msg, 'text')
        } else {
            $.toast(msg)
        }
    } else if (layer) {
        layer.msg(msg)
    } else {
        alert(msg)
    }
};

let request = function (data) {
    $.ajax({
        url: data.url,
        method: data.method ? data.method : "GET",
        data: JSON.stringify(data.data),
        dataType: 'json',
        headers: {
            'Content-Type': 'application/json',
        },
        success: data.success ? data.success : function (res) {
            if (res.code === 'success') {
                message("操作成功");
            } else {
                message(res.message, "error");
            }
        }
    })
};

let uploadFile = function (data) {
    $.ajax({
        "type": 'post',
        "cache": false,
        "url": data.url,
        "data": data.data,
        "dateType": "json",
        "processData": false,
        "contentType": false,
        "mimeType": "multipart/form-data",
        success: data.success ? data.success : function (res) {
            if (res.code === 'success') {
                message("操作成功");
            } else {
                message(res.message, "error");
            }
        }
    })
};

let getUrlParam = function (key) {
    let queryString = window.location.search.replace(/^\?/, '');
    let array = queryString.split('&');
    let item = {};
    for (let i in array) {
        let kv = array[i].split('=')
        item[kv[0]] = decodeURIComponent(kv[1])
    }
    return item[key]
};

let loginSubmit = function (ele) {
    let Form = $(ele).parents('form');
    let data = JSON.stringify(serializeArrayToJson(Form.serializeArray()));
    let reload = Form.data("reload");
    $.ajax('/user/login', {
        method: "POST",
        data: data,
        dataType: 'json',
        headers: {
            'Content-Type': 'application/json',
        },
        success: function (res) {
            if (res.code === 'success') {
                message("登录成功");
                if(reload){
                    return window.location.reload();
                }
                let redirect = getUrlParam('redirect');
                if (!redirect) {
                    redirect = '/user/center'
                }
                return window.location.href = redirect;
            } else {
                message(res.message, "error");
            }
        }
    })
};

//register
let registerSubmit = function (ele) {
    let Form = $(ele).parents('form');
    let data = JSON.stringify(serializeArrayToJson(Form.serializeArray()));
    $.ajax('/user/register', {
        method: "POST",
        data: data,
        dataType: 'json',
        headers: {
            'Content-Type': 'application/json',
        },
        success: function (res) {
            console.log(res);
            if (res.code === 'success') {
                message("注册成功");
                return window.location.href = '/user/login'
            } else {
                message(res.message, "text");
            }
        }
    })
};

$(function () {
    $('pre').append("<div class='code-copy' style='position: absolute;top: 2px;right: 2px;border-radius: 4px;cursor: pointer;color: white;padding: 2px;font-size: 12px;width:30px;height:30px;  display: none;  border: 1px solid #ddd;  opacity: 0.6;'><i style='background-image: url(/static/images/copy.png);background-size: 100%;background-repeat: no-repeat;width: 100%;display: block;height: 100%;'></i></div>")
    $('.code-copy').click(function () {
        let pre = $(this).parent('pre');
        let text = pre[0];
        let range;
        if (document.body.createTextRange) {//$.browser.msie
            range = document.body.createTextRange();
            range.moveToElementText(text);
            range.select();
        } else if (document.createRange) { //$.browser.mozilla || $.browser.opera
            let selection = window.getSelection();
            range = document.createRange();
            range.selectNodeContents(text);
            selection.removeAllRanges();
            selection.addRange(range);
        } else if (window.getSelection && window.getSelection().setBaseAndExtent) { //$.browser.safari
            let selection = window.getSelection();
            selection.setBaseAndExtent(text, 0, text, 1);
        }

        document.execCommand("Copy");

        message("复制成功")

        window.getSelection().empty();
    })
})