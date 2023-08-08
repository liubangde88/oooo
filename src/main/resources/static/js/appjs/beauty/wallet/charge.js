var prefix = ctx + "/beauty/wallet/charge"
$(function () {
    load();
});

function load() {
    $('#dataTable')
        .bootstrapTable(
            {
                method: 'get', // 服务器数据的请求方式 get or post
                url: prefix + "/list", // 服务器数据的加载地址
                clickToSelect: true,
                iconSize: 'outline',
                toolbar: '#exampleToolbar',
                pagination: true, // 设置为true会在底部显示分页条
                striped: true, // 设置为true会有隔行变色效果
                dataType: "json", // 服务器返回的数据类型
                pagination: true, // 设置为true会在底部显示分页条
                singleSelect: false, // 设置为true将禁止多选
                pageNumber: 1, // 如果设置了分布，首页页码
                pageSize: 10, // 如果设置了分页，每页数据条数
                pageList: [5, 10, 50, 100, 500],  //记录数可选列表
                sortStable: true,
                sortable: true,                     //是否启用排序
                sortOrder: "asc",                   //排序方式
                showColumns: false, // 是否显示内容下拉框（选择显示的列）
                showRefresh: true,
                showToggle: false,
                search: false,
                sidePagination: "server", // 设置在哪里进行分页，可选值为"client" 或者 "server"
                queryParams: function (params) {
                    var formData = $('#formSearch').serializeObject();
                    if (!formData) {
                        formData = {};
                    }
                    formData.limit = params.limit;
                    formData.offset = params.offset;
                    formData.sort = this.sortName;
                    formData.order = this.sortOrder;
                    return formData;
                }

            });
    //掩藏id列
    $('#dataTable').bootstrapTable('hideColumn', 'id');
}

/*格式化"操作"按钮列*/
function operateFormatter(value, row, index) {
    var d = '<a class="btn btn-warning btn-sm " href="#" title="撤销"  mce_href="#" onclick="remove(\''
        + row.id + '\')"><i class="fa fa-remove"></i></a> ';
    return d;
}

function remove(id) {
    alertConfirm('确定要撤销？',
        function () {
            $.ajax({
                url: prefix + "/remove",
                type: "post",
                data: {
                    'id': id
                },
                success: function (r) {
                    if (r.code == 0) {
                        alertMsg(r.msg);
                        reLoad();
                    } else {
                        alertMsg(r.msg);
                    }
                }
            });
        })
}

function typeFormatter(value, row, index) {
    if (value == 1) {
        return "冻结";
    }
    if (value == 0) {
        return "充值";
    }
}

function reLoad() {
    $('#dataTable').bootstrapTable('refresh');
}

//重置输入框
function reSet() {
    cleardata("formSearch");
    reLoad();
}