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