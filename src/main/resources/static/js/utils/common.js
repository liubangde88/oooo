﻿
/**
 * json填充表单
 * @param formId
 * @param json
 */
$.fn.loadData = function (json) {
    var $form = this;//form等容器

    var jsonObj = json;
    if (typeof json === 'string') {
        jsonObj = $.parseJSON(json);
    }

    for (var key in jsonObj) {  //遍历json字符串
        var objtype = jsonObjType(jsonObj[key]); // 获取值类型

        if (objtype === "array") { //如果是数组，一般都是数据库中多对多关系

            var obj1 = jsonObj[key];
            for (var arraykey in obj1) {
                //alert(arraykey + jsonObj[arraykey]);
                var arrayobj = obj1[arraykey];
                for (var smallkey in arrayobj) {
//	          setCkb(key, arrayobj[smallkey]); // setCkb函数未定义，暂时注释掉
                    break;
                }
            }
        } else if (objtype === "object") { //如果是对象，啥都不错，大多数情况下，会有 xxxId 这样的字段作为外键表的id

        } else if (objtype === "string") { //如果是字符串
            var str = jsonObj[key];
            //如果是日期的特殊处理
            /*var date = new Date(str);
            if (date.getDay()) {
              $("[name=" + key + "]", $form).val(date.format("yyyy-MM-dd"));
              continue;
            }*/

            var tagobjs = $("[name=" + key + "]", $form);
            if ($(tagobjs[0]).attr("type") == "radio") {//如果是radio控件
                $.each(tagobjs, function (keyobj, value) {
                    if ($(value).attr("val") == jsonObj[key]) {
                        value.checked = true;
                    }
                });
                continue;
            }

            if (tagobjs[0] && tagobjs[0].nodeName && tagobjs[0].nodeName.toLowerCase() == "select") {//如果是select,使用select2控件用法
                tagobjs.val(jsonObj[key]).trigger('change');
                continue;
            }

            $("[name=" + key + "]", $form).val(jsonObj[key]);

        } else {
            var tagobjs = $("[name=" + key + "]", $form);
            if (tagobjs[0] && tagobjs[0].nodeName && tagobjs[0].nodeName.toLowerCase() == "select") {//如果是select,使用select2控件用法
                tagobjs.val(jsonObj[key]).trigger('change');
                continue;
            }
            //其他的直接赋值
            $("[name=" + key + "]", $form).val(jsonObj[key]);
        }

    }
}

/**
 * 判断json对象类型
 * @param obj
 */
function jsonObjType(obj) {
    if (typeof obj === "object") {
        var teststr = JSON.stringify(obj);
        if (teststr[0] == '{' && teststr[teststr.length - 1] == '}') return "class";
        if (teststr[0] == '[' && teststr[teststr.length - 1] == ']') return "array";
    }
    return typeof obj;
}

﻿/**
 * 根据数据字典自动生成Radio 数据项类型{[parmName:id,parmValue:desc]}
 *
 * @param resource
 *            数据来源URL 或者是 JSON格式的数据 JsonData
 * @param parentId
 *            单选框要放置的上层节点的ID
 * @param name
 *            单选框的Name属性名字
 * @param defaultValue
 *            默认值
 * @param lineLength
 *            换行长度(默认为0,不换行)
 * @param keyPropertyName
 *            返回的数据对象的key属性名,默认为parmName
 * @param valuePropertyName
 *            返回的数据对象的value属性名,默认为parmValue
 *
 * 自动生成的ID规则:name+"_"+key
 */
function createRadioHtml(params) {

    if (!params) {
        return;
    }
    if (!params.resource) {
        params.resource = [];
    }
    if (!params.parentId) {
        params.parentId = "";
    }
    if (!params.name) {
        params.name = "";
    }
    if (!params.defaultValue) {
        params.defaultValue = "";
    }
    if (!params.lineLength) {
        params.lineLength = 0;
    }

    var jsonData = null;

    if (!params.keyPropertyName) {
        params.keyPropertyName = "parmName";
    }
    if (!params.valuePropertyName) {
        params.valuePropertyName = "parmValue";
    }
    // 判断如果是字符串就执行URL,否则就是JsonData
    if ($.util.isString(params.resource)) {
        $.getJSON(params.resource, function (data) {
            jsonData = data;
            return processRadionHtml(params.resource, params.parentId, params.name, params.defaultValue, params.lineLength, params.keyPropertyName,
                params.valuePropertyName, jsonData);
        });
    } else {
        jsonData = params.resource;
        return processRadionHtml(params.resource, params.parentId, params.name, params.defaultValue, params.lineLength, params.keyPropertyName,
            params.valuePropertyName, jsonData);
    }
}

/**
 * 最终生成Radio HTML代码
 * @param resource
 * @param parentId
 * @param name
 * @param defaultValue
 * @param lineLength
 * @param keyPropertyName
 * @param valuePropertyName
 * @param jsonData
 * @returns
 */
function processRadionHtml(resource, parentId, name, defaultValue, lineLength, keyPropertyName, valuePropertyName,
                           jsonData) {
    var html = '';
    $.each(jsonData, function (i, json) {
        // if (!$.string.isNullOrEmpty(eval("json." + keyPropertyName))) {
        if ((lineLength) && (!isNaN(lineLength)) && (lineLength > 0) && (i > 0)) {
            if ((i % lineLength) == 0) {
                html += "<br>";
            }
        }
        html += "<label for=\"" + name + "_" + eval("json." + keyPropertyName) + "\"><input type=\"radio\" id=\""
            + name + "_" + eval("json." + keyPropertyName) + "\" name=\"" + name + "\" value=\""
            + eval("json." + keyPropertyName) + "\" ";
        if ((defaultValue) && (eval("json." + keyPropertyName) == defaultValue)) {
            html += " checked=\"true\" ";
        }
        html += "></input>" + eval("json." + valuePropertyName) + "</label>";
        // }
    });
    if (!$.string.isNullOrEmpty(parentId)) {
        $("#" + parentId).append(html);
    }
    return html;
}

/**
 * 根据数据字典自动生成CheckBox 数据项类型{[parmName:id,parmValue:desc]}
 *
 * @param resource
 *            数据来源URL 或者是 JSON格式的数据 JsonData
 * @param parentId
 *            复选框要放置的上层节点的ID
 * @param name
 *            复选框的Name属性名字
 * @param defaultValue
 *            默认选择的值(多个值以,隔开)
 * @param lineLength
 *            换行长度(默认为0,不换行)
 * @param keyPropertyName
 *            返回的数据对象的key属性名,默认为parmName
 * @param valuePropertyName
 *            返回的数据对象的value属性名,默认为parmValue 自动生成的ID规则:name+"_"+key
 */
function createCheckBoxHtml(params) {

    if (!params) {
        return;
    }
    if (!params.resource) {
        params.resource = [];
    }
    if (!params.parentId) {
        params.parentId = "";
    }
    if (!params.name) {
        params.name = "";
    }
    if (!params.defaultValue) {
        params.defaultValue = "";
    }
    if (!params.lineLength) {
        params.lineLength = 0;
    }

    var jsonData = null;
    if (!params.keyPropertyName) {
        params.keyPropertyName = "parmName";
    }
    if (!params.valuePropertyName) {
        params.valuePropertyName = "parmValue";
    }
    // 判断如果是字符串就执行URL,否则就是JsonData
    if ($.util.isString(params.resource)) {
        $.getJSON(params.resource, function (data) {
            jsonData = data;
            return processCheckBoxHtml(params.resource, params.parentId, params.name, params.defaultValue, params.lineLength, params.keyPropertyName,
                params.valuePropertyName, jsonData);
        });
    } else {
        jsonData = params.resource;
        return processCheckBoxHtml(params.resource, params.parentId, params.name, params.defaultValue, params.lineLength, params.keyPropertyName,
            params.valuePropertyName, jsonData);
    }
}

/**
 * 最终生成CheckBox HTML代码
 * @param resource
 * @param parentId
 * @param name
 * @param defaultValue
 * @param lineLength
 * @param keyPropertyName
 * @param valuePropertyName
 * @param jsonData
 * @returns {String}
 */
function processCheckBoxHtml(resource, parentId, name, defaultValue, lineLength, keyPropertyName, valuePropertyName,
                             jsonData) {
    var html = '';
    $.each(jsonData, function (i, json) {
        // if (!$.string.isNullOrEmpty(eval("json." + keyPropertyName))) {
        if ((lineLength) && (!isNaN(lineLength)) && (lineLength > 0) && (i > 0)) {
            if ((i % lineLength) == 0) {
                html += "<br>";
            }
        }
        html += "<label for=\"" + name + "_" + eval("json." + keyPropertyName) + "\"><input type=\"checkbox\" id=\""
            + name + "_" + eval("json." + keyPropertyName) + "\" name=\"" + name + "\" value=\""
            + eval("json." + keyPropertyName) + "\" ";
        if (defaultValue) {
            var defaultValues = defaultValue.split(",");
            for (var i = 0; i < defaultValues.length; i++) {
                if (defaultValues[i] == eval("json." + keyPropertyName)) {
                    html += " checked=\"true\" ";
                }
            }
        }
        html += "></input>" + eval("json." + valuePropertyName) + "</label>";
        // }
    });
    if (!$.string.isNullOrEmpty(parentId)) {
        $("#" + parentId).append(html);
    }
    return html;
}

function cleardata(form) {
    //排除掉对应控件后清空
    $('#' + form + ' input').filter(":text").val('');
    $('#' + form + ' input[type=number]').val('');
    $('#' + form + ' textarea').val('');
    $('#' + form + ' select').val('--选择--');
    $('#' + form + ' input').filter(":radio").removeAttr("checked");

    //下拉树
    $('#' + form + ' .easyui-combotree').next().find(".combo-value").val("");
    //$("#"+form+ "div[class='tree-node tree-node-selected']").removeClass("tree-node-selected");
    //$("div[class='tree-node tree-node-selected'] span.tree-title").text("");

}

//重置页面
function localReload() {
    window.location.reload();
}

//打开Prompt弹窗
function openPrompt() {
    var operateMsg = "操作中,请稍后...";
    var operateMsg = (commonLanObj && commonLanObj.IN_PROGRESS) ? commonLanObj.IN_PROGRESS : operateMsg;
    $.Prompt(operateMsg);
}

//弹窗Prompt关闭
function closePrompt() {
    $.Prompt({close: true});
}

/******************************easyui前端控件公共方法end******************************/

/************************ ajax 请求数据公共方法 ***************************/
(function ($) {
    $.getJSON = function (url, data, callback) {
        var localData = getLocalData(url, data);
        if (localData) {
            if (typeof (callback) == 'undefined') {
                data(localData);
            } else {
                callback(localData);
            }
            return;
        }
        return $.get(url, data, callback, "json");
    };
    $.documentComplete = function (callback) {
        //		var complete;
        //		complete = setInterval(function(){
        //			if (document.readyState == 'complete') {
        //	            try {
        //	            	callback();
        //	                clearInterval(complete);//执行成功，清除监听
        //	            } catch (err) {
        //	                return true;
        //	            }
        //	        }
        //		}, 300);
        setTimeout(function () {
            callback();
        }, 200);
    };
    getLocalData = function (url, data) {
        var lan;
        var parm;
        var localData = null;
        if (typeof (data) == 'function') {
            var parser = purl(url);
        } else {
            lan = data.language;
            parm = data.paramType;
        }
        if (typeof (lan) != 'undefined' && typeof (parm) != 'undefined') {
            try {
                localData = eval(lan + "_" + parm);
            } catch (err) {
                localData = null;
            }
        }
        return localData;
    };
})(jQuery);

/**
 * ajax post 提交公共方法
 *
 * @param url
 *            请求数据来源URL
 * @param params
 *            json格式的参数
 * @param callback
 *            成功后的回调函数
 */
function submitJson(url, params, callback) {
    $.ajax({
        url: url,
        type: 'post',
        data: params,
        traditional: true,
        dataType: 'json',
        success: callback,
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            if (textStatus == "timeout") {
                var msg = "请求超时，请刷新当前页重试！";
                if (commonLanObj && commonLanObj.RequestTimeout) {
                    alertWarn(commonLanObj.RequestTimeout);
                }
            } else {
                alertWarn(textStatus + "：" + errorThrown);
//            	bootbox.alert(textStatus + "：" + errorThrown, function(){});
            }
        }
    });
}

/************************ ajax 请求数据公共方法 end***************************/

//合并两个json到第一个json
function mergeJson(json1, json2) {
    return eval('(' + (JSON.stringify(json1) + JSON.stringify(json2)).replace(/}{/, ',') + ')');
}

//网页域名
function getHost() {
    var host = window.location.host;
    var url = "http://" + host + "/";
    return url;
}

//上传文件存放位置
function getFilesUrl() {
    var url = getHost() + "files/";
    return url;
}

//生成随机数函数
//1、获取0-100的随机数——getRandom(100);
//2、获取0-999的随机数——getRandom(999);
function getRandom(n) {
    return Math.floor(Math.random() * n + 1)
}

//获取两个日期相隔天数
function getDateDiff(startDate, endDate) {
    var startTime = new Date(Date.parse(startDate.replace(/-/g, "/"))).getTime();
    var endTime = new Date(Date.parse(endDate.replace(/-/g, "/"))).getTime();
    var dates = Math.abs((startTime - endTime)) / (1000 * 60 * 60 * 24);
    return dates;
}

//日期加上天数后的新日期.
function addDays(date, days) {
    var nd = new Date(date);
    nd = nd.valueOf();
    nd = nd + days * 24 * 60 * 60 * 1000;
    nd = new Date(nd);
    //alert(nd.getFullYear() + "年" + (nd.getMonth() + 1) + "月" + nd.getDate() + "日");
    var y = nd.getFullYear();
    var m = nd.getMonth() + 1;
    var d = nd.getDate();
    if (m <= 9) m = "0" + m;
    if (d <= 9) d = "0" + d;
    var cdate = y + "-" + m + "-" + d;
    return cdate;
}

//获取页面get传值
function getvl(name) {
    return getUrlvl(location.href, name);
}

function getUrlvl(url, name) {
    var reg = new RegExp("(^|\\?|&)" + name + "=([^&]*)(\\s|&|$)", "i");
    if (reg.test(url))
        return decodeURI(RegExp.$2.replace(/\+/g, " "));
    return "";
}

/********************************************validate start***********************************************/
//验证文本框输入是否为空
function valTextIsNull(ctrId) {

    if (null == $("#" + ctrId).val() || $("#" + ctrId).val().length <= 0) {
        var text = $("#" + ctrId).parent().prev().text();
        text = text.replace(':', '');
        text = text.replace('：', '');
        alert(text + "不能为空");

        return false;
    }
    return true;
}

function isNotBlank(str) {
    if (null == str || "" == str || undefined == str) {
        return false;
    } else {
        return true;
    }
}

function ismail(mail) {
    return (new RegExp(/^(?:[a-zA-Z0-9]+[_\-\+\.]?)*[a-zA-Z0-9]+@(?:([a-zA-Z0-9]+[_\-]?)*[a-zA-Z0-9]+\.)+([a-zA-Z]{2,})+$/).test(mail));
}

//判断对象是否为空
function isEmptyObject(obj) {
    if (!obj) {
        return true;
    }
    for (var name in obj) {
        return false;
    }
    return true;
}

/********************************************validate end***********************************************/

//页面所以控件只读设置
function viewModel(form) {
    var formCtl = "";
    if (form) {
        formCtl = "#" + form + " ";
    }

    $(formCtl + "input:radio").attr("disabled", "disabled");
    $(formCtl + "input:text").attr("disabled", "disabled");
    $(formCtl + "input:checkbox").attr("disabled", "disabled");
    $(formCtl + "textarea").attr("disabled", "disabled");
    $(formCtl + "select").attr("disabled", "disabled");
    $(formCtl + ":file").attr("disabled", "disabled");
    /*$(formCtl+"a").attr("href", "#");
    $(formCtl+"a").removeAttr("onclick");
    
    $(":button").attr("disabled", "disabled");*/

}

/**
 * 封装全部替换
 * 用法：str.replaceAll(f,e)
 */
String.prototype.replaceAll = function (f, e) {//把f替换成e
    var reg = new RegExp(f, "g"); //创建正则RegExp对象
    return this.replace(reg, e);
}

//打印特定区域
function printArea(id_str) {
    var el = document.getElementById(id_str);
    var iframe = document.createElement('IFRAME');
    var doc = null;
    iframe.setAttribute('style', 'position:absolute;width:0px;height:0px;left:-500px;top:-500px;');
    document.body.appendChild(iframe);
    doc = iframe.contentWindow.document;
    // 引入打印的专有CSS样式，根据实际修改
    //doc.write('<LINK rel="stylesheet" type="text/css" href="css/print.css">');
    doc.write('<div>' + el.innerHTML + '</div>');
    doc.close();
    iframe.contentWindow.focus();
    iframe.contentWindow.print();
    if (navigator.userAgent.indexOf("MSIE") > 0) {
        document.body.removeChild(iframe);
    }
}