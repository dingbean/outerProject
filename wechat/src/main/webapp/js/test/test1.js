/*
function readFile(obj){
    var file = obj.files[0];
    //判断类型是不是图片
    if(!/image\/\w+/.test(file.type)){
        alert("请确保文件为图像类型");
        return false;
    }
    var imageUrl = getObjectURL(file);
    convertImgToBase64(imageUrl, function(base64Img){
        // base64Img为转好的base64,放在img src直接前台展示(<img src="data:image/jpg;base64,/9j/4QMZRXh...." />)
        //alert(base64Img);
        // base64转图片不需要base64的前缀data:image/jpg;base64
        var base64Str = base64Img.split(",")[1];
        alert(base64Str);
        console.log(base64Str);
    });
    var reader = new FileReader();
    reader.readAsDataURL(file);
    reader.onload = function(e){
        alert(this.result); //就是base64
        console.log(this.result);
    }
}
 */

function convertImgToBase64(url, callback, outputFormat){
    var canvas = document.createElement('CANVAS');
    var ctx = canvas.getContext('2d');
    var img = new Image;
    img.crossOrigin = 'Anonymous';
    img.onload = function(){
        var width = img.width;
        var height = img.height;
        // 按比例压缩4倍
        var rate = (width<height ? width/height : height/width)/4;
        canvas.width = width*rate;
        canvas.height = height*rate;
        ctx.drawImage(img,0,0,width,height,0,0,width*rate,height*rate);
        var dataURL = canvas.toDataURL(outputFormat || 'image/png');
        callback.call(this, dataURL);
        canvas = null;
    };
    img.src = url;
}

function getObjectURL(file) {
    var url = null ;
    if (window.createObjectURL!=undefined) {  // basic
        url = window.createObjectURL(file) ;
    } else if (window.URL!=undefined) {       // mozilla(firefox)
        url = window.URL.createObjectURL(file) ;
    } else if (window.webkitURL!=undefined) { // web_kit or chrome
        url = window.webkitURL.createObjectURL(file) ;
    }
    return url ;
}