// 以下为官方示例
$().ready(function () {
    validateRule();
});

$.validator.setDefaults({
    submitHandler: function () {
        var tableName = getPageValue();//列表页面传值(row)
        console.log("tableName---------", tableName);
        update();
        console.log('提交修改');
        if (tableName) {
            console.log("ctx---------", ctx);
            location.href = ctx + "/common/generator/code/" + tableName;
        }
        setInterval('aa();', 1000);
    }
});

function update() {
    $.ajax({
        cache: true,
        type: "POST",
        url: ctx + "/common/generator/update",
        data: $('#signupForm').serialize(),// 你的formid
        async: false,
        error: function (request) {
            parent.layer.alert("网络连接超时");
        },
        success: function (data) {
            if (data.code == 0) {
                parent.layer.msg(data.msg);

            } else {
                parent.layer.msg(data.msg);
            }

        }
    });

}

function validateRule() {
    var icon = "<i class='fa fa-times-circle'></i> ";
    $("#signupForm").validate({
        rules: {
            author: {
                required: true
            },
            /*email : {
                required : true,
            },*/
            package: {
                required: true,
            },

        },
        messages: {

            author: {
                required: icon + "请输入作者"
            },
            /*email : {
                required : icon + "请输入email",
            },*/
            package: {
                required: icon + "请输入包名",
            },
        }
    })
}

//关闭当前页
function aa() {
    var index = parent.layer.getFrameIndex(window.name);
    parent.layer.close(index);
}
