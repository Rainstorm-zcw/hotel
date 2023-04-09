
var curFiles = [];//文件数组，用来保存上传的文件信息

//检查上传的图片
function checkImage(obj) {
    var files = obj.files;
    console.log(files.length);
    if(files){

        if(files.length <= 3) {//把一次上传图片数限制在3张
            for (var i = 0; i < files.length; i++) {
                var item = files.item(i);
                var size = item.size;
                if (size / 1000 < 100) { //简易大小限制100K
                    curFiles.push(item);
                }
                else {
                    alert("第" + (i + 1) + "张图片过大");
                }
            }
        }
        else{
            $("#filename").val("");
            alert("一次最多上传3张图片");
        }
    }
    else {
        $("#filename").val("");
        alert("请选择上传文件");
    }

    //去除文件名相同的情况（上传列表中多次出现同一个文件）
    for (var i = 0; i < curFiles.length - 1; i++) {
        for (var j = 1; j < curFiles.length; j++) {
            if (i != j) {
                if (curFiles[i].name == curFiles[j].name) {
                    curFiles.splice(j, 1)
                }
            }
        }
    }

    //判断上传图片大小(100KB)
    for(var i = 0; i < curFiles.length; i++){
        var size = curFiles[i].size;
        if(size/1000>100){
            curFiles.splice(i, 1);
        }

    }

    console.log(curFiles);

    onLoadImage();
}

//预览图片
function onLoadImage() {
    $("#onLoadImage").html("");
    for(var i = 0; i < curFiles.length; i++){
        (function(i){
            var file = curFiles[i];
            var reader = new FileReader();
            reader.readAsDataURL(file);
            reader.onload = function(){
                $('#onLoadImage').append("<span><span style='display: none'>"+file.name+"</span><img src='"+reader.result+"' id='"+i+"' onclick='del(this.id)' style='width: 60px;height: 60px;'/></span>");
            }
        })(i)
    }
}

//删除功能
function del(id) {
    var name = $("#"+id).prev().text();
    console.log(name);
    curFiles = curFiles.filter(function(file) {
        return file.name !== name;
    });
    console.log(curFiles);
    onLoadImage();
}

//上传功能的实现
function checkSubmit() {
    if(curFiles.length>0){
        var formdata =  new FormData($('#form')[0]);
        for (var i = 0; i<curFiles.length; i++) {
            formdata.append('uploadFiles', curFiles[i]);
        }
        console.log(formdata);
        $.ajax({
            url: '/upload/handleFileUpload',
            type: 'post',
            data: formdata,
            processData: false,
            contentType: false,
            success: function(data) {
                console.log(data.length+"个上传结果");
                var images = [];
                for(var k in data){
                    var num=Number(k)+1;
                    console.log("第"+num+"张上传结果："+data[k].result_msg);
                    if(data[k].hasOwnProperty("relativePath"))
                        console.log("第"+num+"张相对路径："+data[k].relativePath);
                    images[k] = data[k].relativePath;
                }
                console.log(images);
                $("#images").val(images);
            },
            error: function(err) {
                alert("上传失败");
            }
        });
    }
    else{
        alert("请选择文件后上传");
    }
}