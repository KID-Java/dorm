function changeFrame(url) {
    var iframe = document.getElementById("iframe");
    iframe.src = url;
}

function select() {
    var url = document.getElementById("prerequisite").value;
    var keyword = document.getElementById("keyword").value;
    changeFrame(url + keyword)
}

function getTime() {
    var myDate = new Date().Format("yyyy-MM-dd EEE HH:mm:ss");
    var timer = document.getElementById("timer");
    timer.innerText = myDate;

}

window.onload = function () {
    getTime();
    setInterval("getTime();", 1000);
};

Date.prototype.Format = function(fmt) {
    var o = {
        "M+": this.getMonth() + 1, //月份
        "d+": this.getDate(), //日
        "h+": this.getHours() % 12 == 0 ? 12 : this.getHours() % 12, //小时
        "H+": this.getHours(), //小时
        "m+": this.getMinutes(), //分
        "s+": this.getSeconds(), //秒
        "S": this.getMilliseconds() //毫秒
    };
    //周
    var week = {
        "0": "日",
        "1": "一",
        "2": "二",
        "3": "三",
        "4": "四",
        "5": "五",
        "6": "六"
    };
    //季度
    var quarter = {
        "1": "一",
        "2": "二",
        "3": "三",
        "4": "四"
    };
    if (/(y+)/.test(fmt)) {
        fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
    }
    if (/(E+)/.test(fmt)) {
        fmt = fmt.replace(RegExp.$1, ((RegExp.$1.length > 1) ? (RegExp.$1.length > 2 ? "星期" : "周") : "") + week[this.getDay() + ""]);
    }
    if (/(q+)/.test(fmt)) {
        fmt = fmt.replace(RegExp.$1, ((RegExp.$1.length > 1) ? "第" : "") + quarter[Math.floor((this.getMonth() + 3) / 3) + ""] + "季度");
    }
    for (var j in o) {
        if (new RegExp("(" + j + ")").test(fmt)) {
            fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[j]) : (("00" + o[j]).substr(("" + o[j]).length)));
        }
    }
    return fmt;
}
