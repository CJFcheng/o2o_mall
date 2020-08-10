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


