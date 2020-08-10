////上传图片显示
function imgshow(obj, n) {
    var file = obj.files[0];

    console.log(obj);
    console.log(file);
    console.log("file.size = " + file.size); //file.size 单位为byte

    var reader = new FileReader();

    //读取文件过程方法
    reader.onloadstart = function (e) {
        console.log("开始读取....");
    }
    reader.onprogress = function (e) {
        console.log("正在读取中....");
    }
    reader.onabort = function (e) {
        console.log("中断读取....");
    }
    reader.onerror = function (e) {
        console.log("读取异常....");
    }
    reader.onload = function (e) {
        console.log("成功读取....");
        if (n == 1) {
            var img = document.getElementById("img1");
            img.src = e.target.result;
            //或者 img.src = this.result;  //e.target == this
        }
        if (n == 2) {
            var img = document.getElementById("img2");
            img.src = e.target.result;
            //或者 img.src = this.result;  //e.target == this
        }
        if (n == 3) {
            var img = document.getElementById("img3");
            img.src = e.target.result;
            //或者 img.src = this.result;  //e.target == this
        }
        if (n == 4) {
            var img = document.getElementById("img4");
            img.src = e.target.result;
            //或者 img.src = this.result;  //e.target == this
        }
        if (n == 5) {
            var img = document.getElementById("img5");
            img.src = e.target.result;
            //或者 img.src = this.result;  //e.target == this
        }
        if (n == 6) {
            var img = document.getElementById("img6");
            img.src = e.target.result;
            //或者 img.src = this.result;  //e.target == this
        }


    }
    reader.readAsDataURL(file)
}


















/////按钮确定
var deletesh = function (id) {


    if (window.confirm('是否确定删除，确认后将不可取消')) {
        var ule = "#" + id;
        var td = new XMLHttpRequest();
        td.open("GET", ule, true);
        td.send();
        //location.reload([false]); 
        setTimeout("location.reload()", 100);

    }

}
/////列表显示
var show1 = function () {


    var class_1 = document.getElementById("林铭煜").className;

    if (class_1 == "show") {
        $("#林铭煜").addClass("hidden");
        $("#林铭煜").removeClass("show");

    } else {
        $("#林铭煜").addClass("show");
        $("#林铭煜").removeClass("hidden");


    }


}
