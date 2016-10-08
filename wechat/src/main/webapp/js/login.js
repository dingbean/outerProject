var register = angular.module("login", []);


register.controller('loginCtrl', function($scope,$rootScope,$http) {
    $scope.clickForm = function() {
        var data = {userName:$scope.user.username,password:$scope.user.password};
        $http.post("auth/login", data)
            .success(function(){
                console.info("Saved.");
            })
            .error(function(){
                console.error("Failed to save.");
            });
    };
    $scope.readFile = function(obj){debugger;
        var file = obj.files[0];
        //判断类型是不是图片
        if(!/image\/\w+/.test(file.type)){
            alert("请确保文件为图像类型");
            return false;
        }

        lrz(file,{width:500,quality:1})
            .then(function (rst) {
                var base64Str = rst.base64.split(",")[1];
                $rootScope.pic = base64Str;
            })
            .catch(function (err) {
                // 处理失败会执行
            })
            .always(function () {
                // 不管是成功失败，都会执行
            });
        /*var imageUrl = getObjectURL(file);
        convertImgToBase64(imageUrl, function(base64Img){
            // base64Img为转好的base64,放在img src直接前台展示(<img src="data:image/jpg;base64,/9j/4QMZRXh...." />)
            //alert(base64Img);
            // base64转图片不需要base64的前缀data:image/jpg;base64
            var base64Str = base64Img.split(",")[1];
            $rootScope.pic = base64Str;
            //alert(base64Str);
            //console.log(base64Str);
        });*/
    }
});