<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@page isELIgnored="false" %>
<html>
<head>

    <meta http-equiv="Content-Type" content="text/html;charset=utf-8"/>
    <title>Title</title>
    <link rel="stylesheet" href="/static/EasyUI/bootstrap/easyui.css" type="text/css"/>
    <link rel="stylesheet" href="/static/EasyUI/icon.css" type="text/css"/>
    <script type="text/javascript" src="/static/js/jquery-1.5.1.min.js"></script>
</head>
<style>
    .h {
        overflow-y: scroll;
        padding-right: 10px;
        height: 600px;
    }

</style>
<body class="h" style="position: absolute; border:1px red solid; width: 320px; height: 500px;  left:35%;top:25%;">

<style>
    .h {
        overflow-y: scroll;
        padding-right: 10px;
        height: 600px;
    }

</style>
<body class="h" style="position: absolute; border:1px red solid; width: 320px; height: 500px;  left:35%;top:25%;">
<div style="width: 320px; height: 100%;overflow: hidden;">
    <div style="width: 350px; height: 100%; overflow-y:scroll; overflow-x:hidden; /*padding-right: 17px; box-sizing: content-box;*/">
        <div style="width:350px; height: 30px;">
            <div style="width: 195px; height: 30px; text-align: right; float: left;font-weight:bold;">
                <span>酒店列表</span>
            </div>
            <div style="width: 120px; height: 30px;float:left;text-align:right;font-size:14px; padding-top:1px;">
                <a style="color: blue" onclick="indexAjax.hotelList()">预定列表</a>
            </div>
        </div>

        <input type="text" id="searchHotel" onchange="indexAjax.initGrid()" placeholder="搜索酒店名称/地址"/>
        <button onclick="indexAjax.initGrid()">搜索</button>
        <div style="width: 100%; height: 25px; display: block;margin: 5px 1px 10px 1px;" id="clickTest">
            <div style="width: 80px;height: 100%;text-align: center;float: left;font-size: 13px;">
                <span id="menu0">全部</span>
            </div>
            <c:forEach items="${hotelTypes}" var="type">
                <div style="width: 80px;height: 100%;text-align: center;float: left;font-size: 13px;">
                    <span id="menu${type.id}">${type.typename}</span>
                </div>
            </c:forEach>
        </div>
        <div id="hotelInfoShow" style="width:100%;height: 100%;">

        </div>
        <input type="hidden" id="hotelType"/>
        <div id="messageModel"
             style="width: 272px; height: 120px; position: fixed; border:1px solid blue; border-radius: 7px; margin: -450px 30px 0px 30px; background-color: white; display: none">
            <span>提示</span>
            <br/>
            <span id="message"></span>
            <br/>
            <button id="messageNo">否</button>
            <button id="messageYes">是</button>
        </div>
    </div>
    <script type="text/javascript">

        $(function () {
            indexAjax.init();
        });

        var indexAjax = {
            init: function () {
                indexAjax.initGrid();
                indexAjax.clickTest();
                indexAjax.showDetail();
            },
            initGrid: function () {
                var str = "";
                var keyWord = $("#searchHotel").val();
                var hotelType = $("#hotelType").val();
                var dataParam = {
                    "hotelname": keyWord,
                    "hoteladdress": keyWord,
                    "hoteltype": hotelType
                };
                $.ajax({
                    url: "/hotelServer/queryAllHotelInfo",
                    type: "POST",
                    dataType: "JSON",
                    data: JSON.stringify(dataParam),
                    contentType: "application/json",
                    success: function (data) {

                        $.each(data.obj, function (index, item) {
                            str += "<div style=\"width:100%;height: 150px;border:1px solid white; /*position: relative;*/\">";
                            str += '<div style="width: 220px;height: 120px;  float: right;">';
                            str += '<span style="font-size: 15px;font-weight: bold;">' + item.hotelname + '</span><br/>';
                            if (item.hoteltype == 2) {
                                str += ' <span style="color:gray;font-size: 14px;">类型：豪华高端</span><br/>';
                            } else if (item.hoteltype == 3) {
                                str += '<span style="color:gray;font-size: 14px;">类型：精品酒店</span><br/>';
                            } else {
                                str += '<span style="color:gray;font-size: 14px;">类型：经济连锁</span></br>';
                            }
                            str += '<span style="color:gray;font-size: 14px;">地址：' + item.hoteladdress + '..</span></br>';
                            str += '<span style="color: blue;font-size: 14px;">评价：4.5分</span></br>';
                            str += '<span style="color: red;font-size: 15px;">电话：' + item.hoteltel + '</span>';
                            str += '</div>';
                            str += '<div style="width: 100px;height: 120px;">';
                            str += '<img id=img'+item.id+'  src="' + item.imageUrl + '" width="100%" height="150px" />';
                            str += "</div>";
                            str += "</div>";
                        });

                        $("#hotelInfoShow").html(str);
                    },
                    error: function () {

                    }
                });
            },
            clickTest: function () {
                $("#clickTest").click(function (e) {
                    var menuId = e.target.id;
                    var id = menuId.substring(4, 5);
                    for (var i = 0; i < 10; i++) {
                        if (id == i) {
                            $("#" + menuId).css("color", "blue");
                            $("#" + menuId).parent().css("border-bottom", "2px solid blue");
                            if (id != 0) {
                                $("#hotelType").val(i);
                            } else {
                                $("#hotelType").val(null);
                            }
                            indexAjax.initGrid();
                        } else {
                            $("#menu" + i).css("color", "black");
                            $("#menu" + i).parent().css("border-bottom", "0px");
                        }
                    }
                });
            },
            showDetail: function () {
                $("#hotelInfoShow").click(function(e){
                    var targetId = e.target.id;
                    var img = targetId.substring(0,3);
                    var id = targetId.substring(3,10);
                    if(img == "img"){
                        console.log(id);
                        window.location = "/hotelServer/showHotelInfoDetail?id=" + id;
                    }
                });
                /*console.log("这里是id" + id);
                $.get('/hotelServer/showHotelInfoDetail', {"id": id});
                window.location = "/hotelServer/showHotelInfoDetail?id=" + id;*/
            },
            hotelList: function () {
                window.location = "/hotelServer/hotelList";
            }
        }
    </script>
</body>
</html>
