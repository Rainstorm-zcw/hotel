<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html;charset=utf-8"/>
    <title>多图片上传</title>
</head>
<body>
<form name="form" id="form" action="/upload/handleFileUpload" method="post" enctype="multipart/form-data">
    <input type="file" name="fileName" id="filename" accept="image/png, image/jpeg, image/jpg" multiple="multiple" onchange="checkImage(this)">
    <input type="button" id="submitBtn" onclick="checkSubmit()" value="上传"/>
</form>
<div id="onLoadImage">

</div>
</body>
<script src="/static/js/jquery-2.1.4.min.js"></script>
<script type="text/javascript">

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
                    $('#onLoadImage').append("<span><span style='display: none'>"+file.name+"</span><img src='"+reader.result+"' id='"+i+"' onclick='del(this.id)' style='width: 80px;height: 80px;'/></span>");
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
                url: 'handleFileUpload',
                type: 'post',
                data: formdata,
                processData: false,
                contentType: false,
                success: function(data) {
                    alert(data.length+"个上传结果");
                    for(var k in data){
                        var num=Number(k)+1;
                        alert("第"+num+"张上传结果："+data[k].result_msg);
                        if(data[k].hasOwnProperty("relativePath"))
                            alert("第"+num+"张相对路径："+data[k].relativePath);
                    }
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

</script>
</html>

