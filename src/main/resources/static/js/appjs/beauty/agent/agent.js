var prefix = ctx + "/beauty/agent"
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
    var e = "";
    if (s_edit_h != "hidden") {
        //图标形式
        e = '<a class="btn btn-primary btn-sm ' + s_edit_h + '" href="#" mce_href="#" title="编辑" onclick="edit(\''
            + row.id
            + '\' , ' + index + ')"><i class="fa fa-edit"></i></a> ';
    }
    var p = "";
    if (s_check_h != "hidden") {
        //图标形式
        if (row.agentStatus != '1') {
            p = '<a class="btn btn-warning btn-sm ' + s_check_h + '" href="#" title="通过"  mce_href="#" onclick="pass(\''
                + row.id + '\')"><i class="fa fa-check"></i></a> ';
        }
    }

    var d = "";
    if (s_check_h != "hidden") {
        //图标形式
        if (row.agentStatus != '-1') {
            d = '<a class="btn btn-warning btn-sm ' + s_check_h + '" href="#" title="拒绝"  mce_href="#" onclick="deny(\''
                + row.id + '\')"><i class="fa fa-remove"></i></a> ';
        }
    }
    return e + p + d;
}

//操作列事件
var operateEvent = {
    'click .check': function (e, value, row, index) {
        edit(row, 'check');
    },
    'click .edit': function (e, value, row, index) {
        edit(row, 'edit');
    },
    'click .remove': function (e, value, row, index) {
        remove(row.id);
    }
};

function reLoad() {
    $('#dataTable').bootstrapTable('refresh');
}

function photoUrlFormatter(value, row, index) {
    return value == null ? ''
        : '<img style="width: 50px;height: 50px;" src="' + value
        + '" alt="">';
}

function agentStatusFormatter(value, row, index) {
    if (value == '-1') {
        return "已拒绝";
    }
    if (value == '0') {
        return "待审核";
    }
    if (value == '1') {
        return "已通过";
    }
}

function edit(id, type) {
    /*var row = $('#dataTable').bootstrapTable('getData')[index];*/
    if (id == '' || id == null) {
        alertMsg("请选择一条记录!");
        return;
    }
    setPageValue(JSON.stringify(id));

    layer.open({
        type: 2,
        title: '编辑',
        maxmin: true,
        shadeClose: false, // 点击遮罩关闭层
        area: ['800px', '520px'],
        content: prefix + '/edit/' + id // iframe的url
    });
}

function pass(id) {
    alertConfirm('确定要通过？',
        function () {
            $.ajax({
                url: prefix + "/pass",
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

function deny(id) {
    alertConfirm('确定要拒绝？',
        function () {
            $.ajax({
                url: prefix + "/deny",
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

//重置输入框
function reSet() {
    cleardata("formSearch");
    reLoad();
}