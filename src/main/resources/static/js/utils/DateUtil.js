/**
 * DateUtil.js 日期工具包 时间:2015-8-11 11:35:32 注：1天=86400000毫秒
 */
var DateUtil = function () {

    this.WeekDay; // 星期几
    this.WeekDayStr;
    this.Day; // 当天
    this.Year; // 当年
    this.Month; // 当月
    this.Hours; // 当前小时
    this.Minutes;
    this.Seconds;
    this.Time; // 当前事件
    var Nowdate = new Date();
    this.WeekDay = Nowdate.getDay();
    this.Month = Nowdate.getMonth();
    this.Day = Nowdate.getDate();
    this.Year = Nowdate.getFullYear();
    this.WeekDayStr = '星期' + '日一二三四五六'.charAt(this.WeekDay)
    this.Hours = Nowdate.getHours();
    this.Minutes = Nowdate.getMinutes();
    this.Seconds = Nowdate.getSeconds();
    this.Time = this.Year + "-" + (this.Month + 1) + "-" + this.Day + " "
        + this.Hours + ":" + this.Minutes + ":" + this.Seconds;

    // 今天
    this.showCurrentDay = function () {
        return Nowdate;
    };
    // 本周第一天
    this.showWeekFirstDay = function () {
        var WeekFirstDay = new Date(Nowdate - (this.WeekDay - 1) * 86400000);
        return WeekFirstDay;
    };
    // 本周最后一天
    this.showWeekLastDay = function () {
        var WeekFirstDay = this.showWeekFirstDay();
        var WeekLastDay = new Date((WeekFirstDay / 1000 + 6 * 86400) * 1000);
        return WeekLastDay;
    };
    // 本月第一天
    this.showMonthFirstDay = function () {
        var MonthFirstDay = new Date(this.Year, this.Month, 1);
        return MonthFirstDay;
    };
    // 本月最后一天
    this.showMonthLastDay = function () {
        var MonthNextFirstDay = new Date(this.Year, this.Month + 1, 1);
        var MonthLastDay = new Date(MonthNextFirstDay - 86400000);
        return MonthLastDay;
    };

    // 当年第一天
    this.showYearFirstDay = function () {
        var YearFirstDay = new Date(this.Year, 0, 1);
        return YearFirstDay;
    };
    // 当年最后一天
    this.showYearLastDay = function () {
        var YearNextFirstDay = new Date(this.Year + 1, 0, 1);
        var YearLastDay = new Date(YearNextFirstDay - 86400000);
        return YearLastDay;
    };

    // 上年第一天
    this.showYearPreviousFirstDay = function () {
        var YearPreviousFirstDay = new Date(this.Year - 1, 0, 1);
        return YearPreviousFirstDay;
    };
    // 上年最后一天
    this.showYearPreviousLastDay = function () {
        var YearFirstDay = this.showYearFirstDay();
        var YearPreviousLastDay = new Date(YearFirstDay - 86400000);
        return YearPreviousLastDay;
    };

    // 下年第一天
    this.showYearNextFirstDay = function () {
        var YearNextFirstDay = new Date(this.Year + 1, 0, 1);
        return YearNextFirstDay;
    };
    // 下年最后一天
    this.showYearNextLastDay = function () {
        var step = new Date(this.Year + 2, 0, 1);
        var YearNextLastDay = new Date(step - 86400000);
        return YearNextLastDay;
    };

    // 上月第一天
    this.showPreviousFirstDay = function () {
        var MonthFirstDay = this.showMonthFirstDay()
        return new Date(MonthFirstDay.getFullYear(), MonthFirstDay.getMonth()
            - 1, 1)
    };
    // 上月最后一天
    this.showPreviousLastDay = function () {
        var MonthFirstDay = this.showMonthFirstDay();
        return new Date(MonthFirstDay - 86400000);
    };
    // 上周第一天
    this.showPreviousFirstWeekDay = function () {
        var WeekFirstDay = this.showWeekFirstDay()
        return new Date(WeekFirstDay - 86400000 * 7)
    };
    // 上周最后一天
    this.showPreviousLastWeekDay = function () {
        var WeekFirstDay = this.showWeekFirstDay()
        return new Date(WeekFirstDay - 86400000)
    };
    // 上一天
    this.showPreviousDay = function () {
        var MonthFirstDay = new Date();
        return new Date(MonthFirstDay - 86400000);
    };
    // 下一天
    this.showNextDay = function () {
        var MonthFirstDay = new Date();
        return new Date((MonthFirstDay / 1000 + 86400) * 1000);
    };
    // 下周第一天
    this.showNextFirstWeekDay = function () {
        var MonthFirstDay = this.showWeekLastDay()
        return new Date((MonthFirstDay / 1000 + 86400) * 1000)
    };
    // 下周最后一天
    this.showNextLastWeekDay = function () {
        var MonthFirstDay = this.showWeekLastDay()
        return new Date((MonthFirstDay / 1000 + 7 * 86400) * 1000)
    };
    // 下月第一天
    this.showNextFirstDay = function () {
        var MonthFirstDay = this.showMonthFirstDay()
        return new Date(MonthFirstDay.getFullYear(), MonthFirstDay.getMonth()
            + 1, 1)
    };
    // 下月最后一天
    this.showNextLastDay = function () {
        var MonthFirstDay = this.showMonthFirstDay()
        return new Date(new Date(MonthFirstDay.getFullYear(), MonthFirstDay
                    .getMonth()
                + 2, 1)
            - 86400000)
    };

    // 返回json
    this.toObject = function (startTime, endTime) {
        var obj = {
            start: startTime.getFullYear() + "-" + (startTime.getMonth() + 1)
                + "-" + startTime.getDate(),
            end: endTime.getFullYear() + "-" + (endTime.getMonth() + 1) + "-"
                + endTime.getDate()
        };
        return obj;
    }
    //};


    // 上一年 {start:2010-01-01 00:00:00,end:2010-12-31 23:59:59}
    this.PreviousYear = function () {
        return this.toObject(this.showYearPreviousFirstDay(), this
            .showYearPreviousLastDay());
    };
    // 本年 {start:2011-01-01 00:00:00,end:2011-12-31 23:59:59}
    this.CurrentYear = function () {
        return this.toObject(this.showYearFirstDay(), this.showYearLastDay());
    };
    // 下一年 {start:2012-01-01 00:00:00,end:2012-12-31 23:59:59}
    this.NextYear = function () {
        return this.toObject(this.showYearNextFirstDay(), this
            .showYearNextLastDay());
    };
    // 上一月 {start:2011-01-01 00:00:00,end:2011-01-31 23:59:59}
    this.PreviousMonth = function () {
        return this.toObject(this.showPreviousFirstDay(), this
            .showPreviousLastDay());
    };
    // 本月 {start:2011-02-01 00:00:00,end:2011-02-28 23:59:59}
    this.CurrentMonth = function () {
        return this.toObject(this.showMonthFirstDay(), this.showMonthLastDay());
    };
    // 下一月 {start:2011-03-01 00:00:00,end:2011-03-31 23:59:59}
    this.NextMonth = function () {
        return this.toObject(this.showNextFirstDay(), this.showNextLastDay());
    };
    // 上一周
    this.PreviousWeekDay = function () {
        return this.toObject(this.showPreviousFirstWeekDay(), this
            .showPreviousLastWeekDay());
    };
    // 本周
    this.CurrentWeekDay = function () {
        return this.toObject(this.showWeekFirstDay(), this.showWeekLastDay());
    };
    // 下一周
    this.NextWeekDay = function () {
        return this.toObject(this.showNextFirstWeekDay(), this
            .showNextLastWeekDay());
    };
    // 上一天
    this.PreviousDay = function () {
        return this.toObject(this.showPreviousDay(), this.showPreviousDay());
    };
    // 今天
    this.CurrentDay = function () {
        return this.toObject(this.showCurrentDay(), this.showCurrentDay());
    };
    // 下一天
    this.NextDay = function () {
        return this.toObject(this.showNextDay(), this.showNextDay());
    };


    /**
     * 字符串转换为日期对象
     * @param date Date 格式为yyyy-MM-dd HH:mm:ss，必须按年月日时分秒的顺序，中间分隔符不限制
     */
    this.strToDate = function (dateStr) {
        var data = dateStr;
        var reCat = /(\d{1,4})/gm;
        var t = data.match(reCat);
        t[1] = t[1] - 1;
        eval('var d = new Date(' + t.join(',') + ');');
        return d;
    }

    /**
     * 时间相隔天数差
     * @param begintime
     * @param endtime
     * @returns
     */
    this.daysDiff = function (begintime, endtime) {
        var begintime_ms = Date.parse(new Date(begintime.replace(/-/g, "/"))); //begintime 为开始时间
        var endtime_ms = Date.parse(new Date(endtime.replace(/-/g, "/")));   // endtime 为结束时间

        var date3 = endtime_ms - begintime_ms;  //时间差的毫秒数

        //计算出相差天数
        var days = Math.floor(date3 / (24 * 3600 * 1000));
        return days;

    };

    /**
     * 时间相隔分钟差
     * @param begintime
     * @param endtime
     * @returns
     */
    this.minutesDiff = function (begintime, endtime) {
        var begintime_ms = Date.parse(new Date(begintime.replace(/-/g, "/"))); //begintime 为开始时间
        var endtime_ms = Date.parse(new Date(endtime.replace(/-/g, "/")));   // endtime 为结束时间

        var date3 = endtime_ms - begintime_ms;  //时间差的毫秒数

        //计算出相差分钟
        var minutes = Math.floor(date3 / (60 * 1000));
        return minutes;

    };

    /**
     * 时间相隔月数差
     * @param begintime
     * @param endtime
     * @returns
     */
    this.monthsDiff = function (begintime, endtime) {
        var begintime_ms = Date.parse(new Date(begintime.replace(/-/g, "/"))); //begintime 为开始时间
        var endtime_ms = Date.parse(new Date(endtime.replace(/-/g, "/")));   // endtime 为结束时间

        var date3 = endtime_ms - begintime_ms;  //时间差的毫秒数

        //计算出相差月数
        var months = Math.floor(date3 / (24 * 3600 * 1000 * 30));
        return months;

    };

    /**
     * 日期的加减天数
     */
    this.addDays = function (date, days) {
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
    };

    return this;
}();


//日期格式化
/*使用方法 
var now = new Date();
var nowStr = now.format("yyyy-MM-dd hh:mm:ss");
使用方法2: 
var testDate = new Date();
var testStr = testDate.format("YYYY年MM月dd日hh小时mm分ss秒"); */
Date.prototype.format = function (format) {
    var o = {
        "M+": this.getMonth() + 1, //month 
        "d+": this.getDate(), //day 
        "h+": this.getHours(), //hour 
        "m+": this.getMinutes(), //minute 
        "s+": this.getSeconds(), //second 
        "q+": Math.floor((this.getMonth() + 3) / 3), //quarter 
        "S": this.getMilliseconds() //millisecond 
    }

    if (/(y+)/.test(format)) {
        format = format.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
    }

    for (var k in o) {
        if (new RegExp("(" + k + ")").test(format)) {
            format = format.replace(RegExp.$1, RegExp.$1.length == 1 ? o[k] : ("00" + o[k]).substr(("" + o[k]).length));
        }
    }
    return format;
} 