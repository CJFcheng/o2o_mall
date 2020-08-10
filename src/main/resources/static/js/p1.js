
var eleFile = document.querySelector('#file');
// 压缩图片需要的一些元素和对象
var reader = new FileReader();

var imga=document.getElementById('img1');

var  img = new Image();

// base64地址图片加载完毕后
img.onload = function() {
    var originWidth = this.width, //image resize   压缩后的宽
        originHeight=this.height,
        maxWidth = 40, maxHeight = 40,
        quality = 0.1, //image quality  压缩质量
        canvas = document.createElement('canvas'),
        drawer = canvas.getContext("2d");
    canvas.width = maxWidth;
    canvas.height = originHeight/originWidth*maxWidth;
    drawer.drawImage(img, 0, 0, canvas.width, canvas.height);
    //上传到七牛云base64
    var base64 = canvas.toDataURL("image/jpeg", quality); // 这里就拿到了压缩后的base64图片
    //console.log(base64);
    //blob对象
    canvas.toBlob(function(blob){
        //console.log(blob)
    }, "image/jpeg", quality);
    var file = dataURLtoFile(base64, 'name');
    console.log(file);
    imga.src=base64;

};
//base64转file
//filename图片的名字dataurl是base64地址
function dataURLtoFile(dataurl, filename) {
    var arr = dataurl.split(','), mime = arr[0].match(/:(.*?);/)[1],
        bstr = atob(arr[1]), n = bstr.length, u8arr = new Uint8Array(n);
    while(n--){
        u8arr[n] = bstr.charCodeAt(n);
    }
    return new File([u8arr], filename, {type:mime});
}

// 文件base64化，以便获知图片原始尺寸
reader.onload = function() {
    //reader.result就是base64
    img.src = reader.result;
};
//addEventListener监听change事件
eleFile.addEventListener('change', function () {
    file = this.files[0];
    // 选择的文件是图片
    if (file.type.indexOf("image") == 0) {
        //console.log(file);
        reader.readAsDataURL(file); //以数据url的方式读取文件内容

    }
});