var prefix = ctx + "/crm/customer"
$(function () {
    loadUser();
});

function loadUser() {
    var html = "";
    var deptId = $("#deptId").val();
    $.ajax({
        url: ctx + '/sys/user/list/select/' + deptId,
        success: function (data) {
            //加载数据
            for (var i = 0; i < data.length; i++) {
                html += '<option value="' + data[i].userId + '">' + data[i].name + '</option>'
            }
            $("#userId").append(html);
            $("#userId").chosen({
                maxHeight: 200
            });
            $('#userId').on('change', function (e, params) {
                var options = $("#userId option:selected");
                parent.loadUser(options.val(), options.text());
                var index = parent.layer.getFrameIndex(window.name); // 获取窗口索引
                parent.layer.close(index);
            });
        }
    });
};

