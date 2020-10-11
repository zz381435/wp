//调用window对象的setInterval方法设置每隔1000s自动执行依次changeLeaveTime函数
window.setInterval(changeLeaveTime, 500);

//自定义函数changeLeaveTime
function changeLeaveTime() {
    /*获取id=leaveTime的标签元素对象，访问其innerText属性，将值转换为整型*/
    var time = parseInt(document.getElementById("mainTime").innerText);
    time = time - 1;
    if (time == 0) {
        window.location.href = "login.html";
    }else {
        //获取获取id=leaveTime的标签元素对象，设置innerText属性
        document.getElementById("mainTime").innerText = time;
    }
}