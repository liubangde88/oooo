/**
 * 弹出消息框(自动消失)
 * @param msg
 */
function alertMsg(msg) {
    layer.msg(msg);
}

/**
 * 弹出消息框(手动确认)
 * @param msg
 */
function alertWarn(msg, callbackFun) {

    var index = layer.alert(msg, {
        closeBtn: 0
    }, function () {//确定按钮回调
        if ($.isFunction(callbackFun)) {
            !callbackFun();
        }
        layer.close(index);
    });
}

/**
 * 在指定元素在显示tips
 * @param msg
 * @param ctlId
 * @param params={position:1~4, color:'#3595CC', time: 3000}
 * @returns
 */
function alertTips(msg, ctlId, params) {
    if (!params) {
        params = {position: 2, color: '#1ab394', time: 3000};
    }
    layer.tips(msg, "#" + ctlId, {
        tips: [params.position, params.color],
        time: params.time
    });
}

/**
 * 弹出消息确认框
 * @param msg
 * @param callback
 */
function alertConfirm(msg, okFunction, cancelFunction) {
    /*var btnAry=new Array();
    btnAry[0]= "确定";
    */

    if (!$.isFunction(okFunction)) {
        okFunction = function () {
        };
    }
    if (!$.isFunction(cancelFunction)) {
        cancelFunction = function () {
        };
    }

    //询问框
    layer.confirm(msg, {
        btn: ['确定', '取消'] //按钮
    }, function () {
        !okFunction();
    }, function () {
        !cancelFunction();
    });

}

/**
 * tab页签之间传至：设置json字符串到indexMainInput
 * @param json 需要传递的值，已经转换成json格式的字符串
 */
function setPageValue(json) {
    $(top.document.getElementById("indexMainInput")).attr('value', json);
}

/**
 * 获取indexMainInput里面设置的json字符串
 */
function getPageValue() {
    var datas = $(top.document.getElementById("mainForm")).serializeObject();
    var val = datas.indexMainInput;
    setPageValue("{}");//清空
    return JSON.parse(val);
}

/**
 * 设置弹窗按钮操作标记(是否点击"提交")
 * @param isSubmit 需要传递的值，(1)是否点击"提交"
 */
function setDiagIsSubmit(isSubmit) {
    $(top.document.getElementById("diagIsSubmit")).attr('value', isSubmit);
}

/**
 * 获取弹窗按钮操作标记
 */
function getDiagIsSubmit() {
    var isSubmit = $(top.document.getElementById("diagIsSubmit")).attr('value');
    //清空是否操作标记
    $(top.document.getElementById("diagIsSubmit")).attr('value', "");
    return isSubmit;
}

/**
 * 打开tab(mid)
 * @param id
 * @param fid
 * @param MENU_NAME
 * @param MENU_URL
 */
function openTab(id, fid, MENU_NAME, MENU_URL) {

    $("#" + fid).attr("class", "active open");
    $("#" + id).attr("class", "active");
    top.mainFrame.tabAddHandler(id, MENU_NAME, MENU_URL);
    if (MENU_URL != "druid/index.html") {
        $("#jzts").show();
    }
}

/**
 * 通过mid关闭tab
 * @param mid
 */
function closeTab(mid) {
    top.mainFrame.close(mid);
}

function refreshTab(mid, mtitle, murl) {
    top.mainFrame.refreshTab(mid, mtitle, murl);
}

/*************************数据字典查询start*******************************/
var SYS_DICT_FLAG = {
    //积分大类
    SCORE_BIG_TYPE_FLAG: "SCORE_BIG_TYPE_FLAG",
    //自我管理
    DAILY_TYPE_FLAG: "DAILY_TYPE_FLAG",
    TO_OBJECTTYPE_FLAG: "TO_OBJECTTYPE_FLAG",
    JOB_GRADE_FLAG: "JOB_GRADE_FLAG",
    USER_STATUS: "USER_STATUS",
    SEX_FLAG: "SEX_FLAG",
    TASK_STATUS_FLAG: "TASK_STATUS_FLAG",
    YES_NO_FLAG: "YES_NO_FLAG",
    RECEIVE_LEVEL_FLAG: "RECEIVE_LEVEL_FLAG",
    ISTEAM_FLAG: "ISTEAM_FLAG",
    PLAN_TIME_SORT: "PLAN_TIME_SORT", // 计划时间段
    WEEKLY_STATUS_FLAG: "WEEKLY_STATUS_FLAG" // 周报状态
}


//数据字典TypeId
var SYS_DICT_TYPE_ID = {
    /*****************任务状态********************/
    //待审核
    TASK_STATUS_FLAG_CHECK: -1,
    //竞标中
    TASK_STATUS_FLAG_BID: 0,
    //进行中
    TASK_STATUS_FLAG_GOING: 1,
    //已完成待评分
    TASK_STATUS_FLAG_WAITPOINT: 2,
    //已评分
    TASK_STATUS_FLAG_FINISH: 3,

    /*****************积分标准*******************/
    //在岗积分
    SCORE_STANDARDS_JOBING: 1,
    /*****************职等********************/
    /**
     1	总经理
     3	部门经理
     5	主管
     10	其他*/
    JOB_GRADE_GENERAL_MGR: 1,
    JOBGRADEFLAG_DEPARTMENT_MGR: 3,
    JOB_GRADE_SUPERVISOR: 5,
    JOBGRADEFLAG_OTHER: 10,

    /********************计划时间段********************/
    WEEK_1_UPPER: 1,		// 周一上午
    WEEK_1_LOWER: 2,		// 周一下午
    WEEK_2_UPPER: 3,		// 周二上午
    WEEK_2_LOWER: 4,		// 周二下午
    WEEK_3_UPPER: 5,		// 周三上午
    WEEK_3_LOWER: 6,		// 周三下午
    WEEK_4_UPPER: 7,		// 周四上午
    WEEK_4_LOWER: 8,		// 周四下午
    WEEK_5_UPPER: 9,		// 周五上午
    WEEK_5_LOWER: 10,		// 周五下午

    /********************周报状态********************/
    WEEKLY_STATUS_REPULSE: -1,		// 打回
    WEEKLY_STATUS_PUBLISHED: 1,		// 已发布
    WEEKLY_STATUS_UNPUBLISHED: 0		// 未发布

};

/*************************数据字典查询end*******************************/

//操作类别
var ACTION = {
    ACTION: "ACTION",
    ADD: "ADD",
    EDIT: "EDIT",
    DELETE: "DELETE",//彻底删除
    VIEW: "VIEW"//查询
};

//通过DefineName,查询对应类型数据字典
function getBaseTypeDicByDefineName(DefineName, callback) {
    $.getJSON(sysBasePath + "basetypedic/findDDLDatasByDefineName", {
            "ACTION": "GETSYSDICTBYFLAG",
            "DefineName": DefineName
        },
        function (data) {
            var json = [{id: "", text: ""}];
            if (isEmptyObject(data.rows)) {

            } else if (jQuery.isArray(data.rows)) {//从DB查询时
                json = data.rows;
            } else {//从缓存读取时
                if (data.rows) {
                    json = eval('(' + data.rows + ')');
                }
            }
            !callback(json);
        });
}

//通过value获取对应JSON组的text
function getNameByValue(data, value) {
    return getNameByDataValue(data, value);
}

//通过value获取对应JSON组的text
function getNameByDataValue(data, value) {
    if (!data || !jQuery.isArray(data)) {
        return "";
    }
    var text;
    $.each(data, function (i, row) {
        if (row.id == value) {
            text = row.text;
            return false;
        }
    });
    return text;
}

/**
 * Ajax请求数据
 * @param url
 * @param params
 * @param callback
 */
function getJson(url, params, callback) {
    $.getJSON(sysBasePath + url, params,
        function (data) {
            !callback(data.rows);
        });
}

/**
 * Excel导入DB(并导出错误数据)公共方法
 * @param comParams
 */
function import_excel_common(comParams) {

    var dialogBtnId = "btnImportXls"
    var dialogId = "dialog_import";
    if (comParams && comParams.baseUrl) {
        comParams.pageUrl = comParams.baseUrl + comParams.pageUrl;
        comParams.importUrl = comParams.baseUrl + comParams.importUrl;
    }
    //打开导入Excel弹框
    var params = {
        dialogId: dialogId,
        width: 400,
        height: 300,
        pageUrl: comParams.pageUrl,
        title: commonLanObj.Import,
        btnId: dialogBtnId,
        btnText: commonLanObj.Submit,
        handlerFunction: function () {

            var formData = $('#import_form').serializeObject();
            if ($.string.isNullOrWhiteSpace(formData.FILE_NAME)) {//验证是否上传文件
                alertWarn(commonLanObj.Import + " " + commonLanObj.CommonNotNull);
                return false;
            }
            $('#' + dialogBtnId).linkbutton('disable');

            submitJson(comParams.importUrl, {"FILE_NAME": formData.FILE_NAME},
                function (data) {

                    if (data.result == RESULT_SUCESS) {
                        if (data.rows.RESULT == "E" && data.rows.FILE_NAME) {//存在错误数据Excel时，发起导出Excel请求
                            var paramsDownExcel = {
                                url: comParams.baseUrl + "downExcel.do?FILE_NAME=" + data.rows.FILE_NAME
                            };
                            //openExcel(paramsDownExcel);
                            window.open(comParams.baseUrl + "downExcel.do?FILE_NAME=" + data.rows.FILE_NAME);
                        }
                        $("#" + dialogId).dialog('destroy');
                        if ($.isFunction(comParams.loadData)) {//刷新列表
                            !comParams.loadData();
                        }
                    } else {
                        $('#' + dialogBtnId).linkbutton('enable');
                    }
                    alertWarn(data.resultInfo);

                });
        },
        onLoadFunction: null,
        onResetFunction: null,
        toolbar: false
    };
    createDialog(params);
}

/**
 * 为bootstrap-table插件添加操作栏里面的编辑按钮
 */
function createTableEditHtml() {
    var html = '<a class="edit" href="javascript:void(0)" title="编辑">' +
        '<span class="blue"><i class="icon-edit icon-size"></i>编辑</span>' +
        '</a>  ';
    return html;
}

/**
 * 为bootstrap-table插件添加操作栏里面的删除按钮
 */
function createTableDelHtml() {
    var html = '<a class="remove" href="javascript:void(0)" title="删除">' +
        '<span class="red"><i class="icon-trash icon-size"></i>删除</span>' +
        '</a>';
    return html;
}

/**
 * 为bootstrap-table插件添加操作栏里面的查看按钮
 */
function createTableCheckHtml() {
    var html = '<a class="check" href="javascript:void(0)" title="查看">' +
        '<span class="DarkBlue"><i class="icon-zoom-in icon-size"></i>查看</span>' +
        '</a>  ';
    return html;
}


/**
 * 为bootstrap-table插件添加操作栏里面的上移按钮
 */
function createTableUpHtml(text) {
    var html = '<a class="itemUp" href="javascript:void(0)" title="' + text + '">' +
        '<span class="orange"><i class="icon-arrow-up icon-size"></i></span>' +
        '</a>  ';
    return html;
}

/**
 * 为bootstrap-table插件添加操作栏里面的下移按钮
 */
function createTableDownHtml(text) {
    var html = '<a class="itemDown" href="javascript:void(0)" title="' + text + '">' +
        '<span class="orange"><i class="icon-arrow-down icon-size"></i></span>' +
        '</a>  ';
    return html;
}

/**
 * 为bootstrap-table插件添加操作栏里面的新增按钮
 */
function createTableAddHtml(text) {
    var html = '<a class="plus" href="javascript:void(0)" title="' + text + '">' +
        '<span class="blue"><i class="icon-plus icon-size"></i></span>' +
        '</a>  ';
    return html;
}

/**
 * 为bootstrap-table插件添加操作栏里面的饼状图按钮
 */
function createTablePieHtml() {
    var html = '<a class="pie" href="javascript:void(0)" title="饼状图">' +
        '<span class="blue"><i class="icon-bar-chart icon-size"></i></span>' +
        '</a>  ';
    return html;
}

/**
 * 公共弹窗选择-光交箱
 * @param params
 * @param diag 初始化弹窗
 * @param callbackFunction 自定义弹窗提交按钮回调
 * @param isSingle 1单选/2多选
 */
function diagSelectBox(params) {
    var diag = params.diag;
    var callbackFunction = params.callbackFunction;

    if (!$.isFunction(callbackFunction)) {
        callbackFunction = function () { //默认关闭事件

            var boxInfoId = $(diag.innerFrame.contentWindow.document.getElementById('boxInfoId')).val();
            var boxNo = $(diag.innerFrame.contentWindow.document.getElementById('boxNo')).val();
            if (boxInfoId) {
                $("#boxNo").val(boxNo);
                $("#boxInfoId").val(boxInfoId);
            }

            diag.close();
        };
    }

    var isSingle = 1;
    if (params.isSingle) {
        isSingle = params.isSingle;
    }

    var boxInfoIds;
    if (params.boxInfoIds) {
        boxInfoIds = params.boxInfoIds;
    }

    var boxIds;
    if (params.boxIds) {
        boxIds = params.boxIds;
    }

    var url = "";
    if (boxInfoIds != null && boxInfoIds != undefined) {
        url = sysBasePath + 'boxinfo/goSelectBox.do?isSingle=' + isSingle + '&boxInfoId=' + boxInfoIds;
    } else if (boxIds != null && boxIds != undefined) {
        url = sysBasePath + 'boxinfo/goSelectBox.do?isSingle=' + isSingle + '&boxIds=' + boxIds;
    } else {
        url = sysBasePath + 'boxinfo/goSelectBox.do?isSingle=' + isSingle;
    }

    top.jzts();
    diag.Drag = true;
    diag.Title = "选择光交箱";
    diag.URL = url;/*sysBasePath + 'boxinfo/goSelectBox.do?isSingle='+isSingle;*/
    diag.Width = 700;
    diag.Height = 600;
    diag.CancelEvent = callbackFunction;
    diag.show();
}

/**
 * 公共弹窗选择-告警等级(光交箱状态等级)
 * @param params
 * @param diag 初始化弹窗
 * @param callbackFunction 自定义弹窗提交按钮回调
 * @param isSingle 1单选/2多选
 */
function diagSelectStateValue(params) {
    var diag = params.diag;
    var callbackFunction = params.callbackFunction;

    if (!$.isFunction(callbackFunction)) {
        callbackFunction = function () { //默认关闭事件

            var stateValueId = $(diag.innerFrame.contentWindow.document.getElementById('stateValueId')).val();
            var stateValue = $(diag.innerFrame.contentWindow.document.getElementById('stateValue')).val();
            if (stateValueId) {
                $("#stateValue").val(stateValue);
                $("#stateValueId").val(stateValueId);
            }

            diag.close();
        };
    }
    var isSingle = 1;
    if (params.isSingle) {
        isSingle = params.isSingle;
    }

    var stateValueIds;
    if (params.stateValueIds) {
        stateValueIds = params.stateValueIds;
    }

    var url = "";
    if (stateValueIds != null && stateValueIds != undefined) {
        url = sysBasePath + 'statevalue/goSelectStateValue?isSingle=' + isSingle + '&stateValueIds=' + stateValueIds;
    } else {
        url = sysBasePath + 'statevalue/goSelectStateValue?isSingle=' + isSingle;
    }

    top.jzts();
    diag.Drag = true;
    diag.Title = "选择光交箱等级";
    diag.URL = url;/*sysBasePath + 'statevalue/goSelectStateValue.do?isSingle='+isSingle;*/
    diag.Width = 500;
    diag.Height = 600;
    diag.CancelEvent = callbackFunction;
    diag.show();
}

/**
 * 公共弹窗选择-系统用户
 * @param params
 * @param diag 初始化弹窗
 * @param callbackFunction 自定义弹窗提交按钮回调
 * @param isSingle 1单选/2多选
 */
function diagSelectUser(params) {
    var diag = params.diag;
    var callbackFunction = params.callbackFunction;

    if (!$.isFunction(callbackFunction)) {
        callbackFunction = function () { //默认关闭事件

            var USER_ID = $(diag.innerFrame.contentWindow.document.getElementById('USER_ID')).val();
            var NAME = $(diag.innerFrame.contentWindow.document.getElementById('NAME')).val();
            if (USER_ID) {
                $("#NAME").val(NAME);
                $("#USER_ID").val(USER_ID);
            }

            diag.close();
        };
    }
    var isSingle = 1;
    if (params.isSingle) {
        isSingle = params.isSingle;
    }
    var selectDepartmentId = -1;
    if (params.selectDepartmentId) {
        selectDepartmentId = params.selectDepartmentId;
    }
    var receiverUserIds;
    if (params.receiverUserIds) {
        receiverUserIds = params.receiverUserIds;
    }

    var url = "";
    if (receiverUserIds != null && receiverUserIds != undefined) {
        url = sysBasePath + 'user/goSelectUser.do?isSingle=' + isSingle + '&selectDepartmentId=' + selectDepartmentId + '&receiverUserIds=' + receiverUserIds;
    } else {
        url = sysBasePath + 'user/goSelectUser.do?isSingle=' + isSingle + '&selectDepartmentId=' + selectDepartmentId;
    }

    top.jzts();
    diag.Drag = true;
    diag.Title = "选择系统用户";
    diag.URL = url;/*sysBasePath + 'user/goSelectUser.do?isSingle='+isSingle +'&selectDepartmentId='+selectDepartmentId;*/
    diag.Width = 650;
    diag.Height = 600;
    diag.CancelEvent = callbackFunction;
    diag.show();
}

/**
 * 公共弹窗选择-钥匙
 * @param params
 * @param diag 初始化弹窗
 * @param callbackFunction 自定义弹窗提交按钮回调
 * @param isSingle 1单选/2多选
 */
function diagSelectKey(params) {
    var diag = params.diag;
    var callbackFunction = params.callbackFunction;

    if (!$.isFunction(callbackFunction)) {
        callbackFunction = function () { //默认关闭事件

            var keyInfoId = $(diag.innerFrame.contentWindow.document.getElementById('keyInfoId')).val();
            if (keyInfoId) {

                $("#keyInfoId").val(keyInfoId);
            }

            diag.close();
        };
    }
    var isSingle = 1;
    if (params.isSingle) {
        isSingle = params.isSingle;
    }

    top.jzts();
    diag.Drag = true;
    diag.Title = "选择钥匙";
    diag.URL = sysBasePath + 'keyinfo/goSelectKey.do?isSingle=' + isSingle;
    diag.Width = 650;
    diag.Height = 600;
    diag.CancelEvent = callbackFunction;
    diag.show();
}