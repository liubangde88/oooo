$().ready(function () {
    validateRule();
    $("#bannerBegin").datepicker({
        language: "zh-CN"
    });
    $("#bannerEnd").datepicker({
        language: "zh-CN"
    });
});

$.validator.setDefaults({
    submitHandler: function () {
        save();
    }
});

function save() {
    $.ajax({
        cache: true,
        type: "POST",
        url: ctx + "/beauty/banner/save",
        data: $('#signupForm').serialize(),// 你的formid
        async: false,
        error: function (request) {
            parent.layer.alert("Connection error");
        },
        success: function (data) {
            if (data.code == 0) {
                parent.layer.msg("操作成功");
                parent.reLoad();
                var index = parent.layer.getFrameIndex(window.name); // 获取窗口索引
                parent.layer.close(index);

            } else {
                parent.layer.alert(data.msg)
            }

        }
    });

}

function validateRule() {
    var icon = "<i class='fa fa-times-circle'></i> ";
    $("#signupForm").validate({
        rules: {
            id: {
                required: true,
            }, bannerType: {
                required: true,
            }, bannerSite: {
                required: true,
            }, bannerUrl: {
                required: true,
            }, bannerCover: {
                required: true,
            }, bannerBegin: {
                required: true,
            }, bannerEnd: {
                required: true,
            }, createTime: {
                required: true,
            }, updateTime: {
                required: true,
            }
        },
        messages: {}
    })
}