var prefix = ctx + "sys/menu"
$(function () {
    validateRule();
    //打开图标列表
    $("#ico-btn").click(function () {
        windows.open("http://www.fontawesome.com.cn/faicons/");
    });

    var value = getPageValue();
    $('#premssion').val(value);

    $('#btnDiv').hide();
    $("input[name='type']").click(function () {
        if ($(this).val() == '2') {
            $('#btnDiv').show();
            $("input[name='name']").parent().prev('label').text('按钮名称：');
            setText();
            $("input[name='url']").val('').attr("disabled", "disabled");
        } else {
            $('#btnDiv').hide();
            $("input[name='name']").parent().prev('label').text('菜单名称：');
            $("input[name='name']").val('');
            $("input[name='url']").val('').removeAttr("disabled");
            $("input[name='perms']").val('');
        }
    });
});
$.validator.setDefaults({
    submitHandler: function () {
        submit01();
    }
});

function submit01() {
    $.ajax({
        cache: true,
        type: "POST",
        url: prefix + "/save",
        data: $('#signupForm').serialize(),
        async: false,
        error: function (request) {
            laryer.alert("Connection error");
        },
        success: function (data) {
            if (data.code == 0) {
                parent.layer.msg("保存成功");
                parent.reLoad();
                var index = parent.layer.getFrameIndex(window.name); // 获取窗口索引
                parent.layer.close(index);

            } else {
                layer.alert(data.msg)
            }
        }
    });
}

function validateRule() {
    var icon = "<i class='fa fa-times-circle'></i> ";
    $("#signupForm").validate({
        rules: {
            name: {
                required: true
            },
            type: {
                required: true
            }
        },
        messages: {
            name: {
                required: icon + "请输入菜单名"
            },
            type: {
                required: icon + "请选择菜单类型"
            }
        }
    })
}

//选择改变赋值
function setText() {
    var text = $('#btnSelect option:selected').text();
    var selValue = $('#btnSelect').selectpicker('val');
    var obj = getCaption();
    if (text != '自定义') {
        $("input[name='name']").val(text);
    } else {
        $("input[name='name']").val('');
    }
    $("input[name='perms']").val(obj + selValue);
}

//权限字符串截取
function getCaption() {
    var obj = $('#premssion').val();
    var index = obj.lastIndexOf(":");
    obj = obj.substring(0, index + 1);
    //console.log(obj);
    return obj;
}