<%--
  Created by IntelliJ IDEA.
  User: zcw
  Date: 2021/7/17
  Time: 20:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script type="text/javascript" src="/static/js/jquery-1.5.1.min.js"></script>
</head>
<body>
    <input id="click" onclick="A()" type="button"/> 点这个按钮试试
</body>
<script type="text/javascript">
    function A(){
        var dataParam={
            "A":"1",
            "B":"2"
        };
        $.ajax({
            url: "/test/printInfo",
            type: "POST",
            dataType: "application/json",
            data: JSON.stringify(dataParam),
            contentType: "application/json",
            success: function (data) {

            }
        });
        $.post("/test/printInfo",dataParam,function(data){

        });
    }
</script>
</html>
