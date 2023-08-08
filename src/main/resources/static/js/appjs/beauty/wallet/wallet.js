var prefix = ctx + "/beauty/agent/wallet"
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
    var f = "";
    if (s_edit_h != "hidden") {
        //图标形式
        e = '<a class="btn btn-primary btn-sm ' + s_edit_h + '" href="#" mce_href="#" title="充值" onclick="edit(\''
            + row.id
            + '\' , ' + index + ')"><i class="fa fa-money"></i></a> ';

        f = '<a class="btn btn-primary btn-sm ' + s_edit_h + '" href="#" mce_href="#" title="冻结" onclick="freeze(\''
            + row.id
            + '\' , ' + index + ')"><i class="fa fa-edit"></i></a> ';
    }
    return e + f;
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

function edit(id, type) {
    /*var row = $('#dataTable').bootstrapTable('getData')[index];*/
    if (id == '' || id == null) {
        alertMsg("请选择一条记录!");
        return;
    }
    setPageValue(JSON.stringify(id));

    layer.open({
        type: 2,
        title: '充值',
        maxmin: true,
        shadeClose: false, // 点击遮罩关闭层
        area: ['800px', '520px'],
        content: prefix + '/edit/' + id // iframe的url
    });
}

function freeze(id, type) {
    /*var row = $('#dataTable').bootstrapTable('getData')[index];*/
    if (id == '' || id == null) {
        alertMsg("请选择一条记录!");
        return;
    }
    setPageValue(JSON.stringify(id));

    layer.open({
        type: 2,
        title: '冻结',
        maxmin: true,
        shadeClose: false, // 点击遮罩关闭层
        area: ['800px', '520px'],
        content: prefix + '/freeze/' + id // iframe的url
    });
}

//重置输入框
function reSet() {
    cleardata("formSearch");
    reLoad();
}