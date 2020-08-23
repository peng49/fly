//login
let serializeArrayToJson = function (array) {
    let object = {};
    array.forEach(function (item) {
        object[item['name']] = item['value']
    });
    return object;
};

let loginSubmit = function (ele) {
    let Form = $(ele).parents('form');
    let data = JSON.stringify(serializeArrayToJson(Form.serializeArray()));
    console.log(data);
    $.ajax('/user/login', {
        method: "POST",
        data: data,
        dataType: 'json',
        headers: {
            'Content-Type': 'application/json',
        },
        success: function (res) {
            if (res.code === 'success') {
                $.toast("登录成功");
                return window.location.href = "/user/center";
            } else {
                $.toast(res.message, "text");
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
                $.toast("注册成功");
                return window.location.href = '/user/login'
            } else {
                $.toast(res.message, "text");
            }
        }
    })
};

$(function () {
    $('pre').append("<div class='code-copy' style='position: absolute;top: -35px;right: 0px;border-radius: 4px;cursor: pointer;color: white;padding: 2px;font-size: 12px;width:30px;height:30px;  display: none;  background: url(/static/images/copy.png) no-repeat;  background-size: 100% 100%;  border: 1px solid #ddd;  opacity: 0.6;'></div>")
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

        $.toast("复制成功")

        window.getSelection().empty();
    })
})