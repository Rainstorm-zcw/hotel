<%--
  Created by IntelliJ IDEA.
  User: zcw
  Date: 2021/4/27
  Time: 22:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@page isELIgnored="false" %>
<html>
<head>

    <meta http-equiv="Content-Type" content="text/html;charset=utf-8"/>
    <title>Title</title>
    <link rel="stylesheet" href="/static/EasyUI/bootstrap/easyui.css" type="text/css"></link>
    <link rel="stylesheet" href="/static/EasyUI/icon.css" type="text/css"></link>
    <script type="text/javascript" src="/static/EasyUI/jquery-1.10.2.min.js"></script>
    <script type="text/javascript" src="/static/EasyUI/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="/static/EasyUI/easyui-lang-zh_CN.js"></script>
    <script type="text/javascript" src="/static/js/upload.js"></script>

</head>
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
                <span>酒店管理</span>
            </div>
            <div style="width: 120px; height: 30px;float:left;text-align:right;font-size:14px; padding-top:1px;">
                <a style="color: blue" onclick="hotelList()">后台管理</a>
            </div>
        </div>
        <c:if test="${userInfo.userType == 1}">
            <button style="width: 320px; height: 30px; color: white; background-color: #2a83ff; border-radius: 7px;"
                    id="addHotel" onclick="showAddHotel()">新增
            </button>
        </c:if>
        <c:forEach items="${hotelInfoList}" var="hotelInfo">
            <div style="width:100%;height: 150px;border:1px solid white; /*position: relative;*/">
                <div style="width: 220px;height: 120px;  float: right;">
                    <span style="font-size: 15px;font-weight: bold;">${hotelInfo.hotelname}</span>
                    <br/>
                    <c:choose>
                        <c:when test="${hotelInfo.hoteltype == 2}">
                            <span style="color:gray;font-size: 14px;">类型：豪华高端</span>
                        </c:when>
                        <c:when test="${hotelInfo.hoteltype == 3}">
                            <span style="color:gray;font-size: 14px;">类型：精品酒店</span>
                        </c:when>
                        <c:otherwise>
                            <span style="color:gray;font-size: 14px;">类型：经济连锁</span>
                        </c:otherwise>
                    </c:choose>
                    <br/>
                    <span style="color:gray;font-size: 14px;">地址：${fn:substring(hotelInfo.hoteladdress,0,8) }..</span>
                    <br/>
                    <span style="color: blue;font-size: 14px;">评价：4.5分</span>
                    <br/>
                    <span style="color: red;font-size: 15px;">电话：${hotelInfo.hoteltel }</span>
                    <div style="float: right;margin-right: 30px;">
                        <button>编辑</button>
                        <button onclick="delHotel(${hotelInfo.id})">删除</button>
                    </div>
                </div>
                <div style="width: 100px;height: 120px;">
                    <img src="${hotelInfo.imageUrl}" width="100%" height="150px" onclick="showHotelDetail(${hotelInfo.id})"/>
                </div>
            </div>
        </c:forEach>
    </div>
    <div id="showAddHotel"
         style="width: 265px; height: 400px; position: fixed; border:1px solid blue; border-radius: 7px; margin: -450px 30px 0px 30px; background-color: white; display: none">
        <form name="form" id="form" action="/upload/handleFileUpload" method="post" enctype="multipart/form-data">
            <input type="file" name="fileName" id="filename" accept="image/png, image/jpeg, image/jpg"
                   multiple="multiple" onchange="checkImage(this)">
            <input type="button" id="submitBtn" onclick="checkSubmit()" value="上传"/>
        </form>
        <div id="onLoadImage">

        </div>
        <input type="hidden" id="images" name="images"/>
        <div></div>
        <table>
            <tr>
                <td style="width: 70px;">类型</td>

                <td>
                    <select id="hotelType" name="hoteltype">
                        <option value="4">经济连锁</option>
                        <option value="2">豪华高端</option>
                        <option value="3">精品酒店</option>
                    </select>
                </td>
                <%--<td><input id="hotelType" name="hoteltype"/></td>--%>
            </tr>
            <tr>
                <td>标间</td>
                <td><input id="num1" name="num1"/></td>
            </tr>
            <tr>
                <td>双人间</td>
                <td><input id="num2" name="num2"/></td>
            </tr>
            <tr>
                <td>单人间</td>
                <td><input id="num3" name="num3"/></td>
            </tr>
            <tr>
                <td>名称</td>
                <td><input id="hotelName" name="hotelname" placeholder="请输入酒店名称"/></td>
            </tr>
            <tr>
                <td>评分</td>
                <td>4.5</td>
            </tr>
            <tr>
                <td>地址</td>
                <td><input id="hotelAddress" name="hoteladdress" placeholder="请输入地址"/></td>
            </tr>
            <tr>
                <td>电话</td>
                <td><input id="hotelTel" name="hoteltel" placeholder="请输入电话"/></td>
            </tr>
            <tr>
                <td>描述</td>
                <td><input id="content" name="content" placeholder="请输入描述"/></td>
            </tr>
        </table>
        <button onclick="addHotel()">确定</button>
        <button onclick="addhotelCancel()">取消</button>
    </div>
    <div id="messageModel"
         style="width: 272px; height: 120px; position: fixed; border:1px solid grey; border-radius: 7px; margin: -450px 30px 0px 30px; background-color: white; display: none">
        <span>提示</span>
        <br/>
        <span id="message"></span>
        <br/>
        <button id="messageNo">否</button>
        <button id="messageYes">是</button>
    </div>
</div>
<script>
    function showHotelDetail(id){
        console.log("这里是id"+id);
        $.get('/hotelServer/showHotelInfoDetail',{"id":id});
        window.location = "/hotelServer/showHotelInfoDetail?id="+id;

    }

    /**
     * 打开显示添加酒店框
     */
    function showAddHotel() {
        var display = document.getElementById("showAddHotel").style.display;
        if ("none" === display) {
            document.getElementById("showAddHotel").style.display = "block";
        } else {
            $("#showAddHotel").find("input").val("");
            document.getElementById("showAddHotel").style.display = "none";
        }
    }

    /**
     * 保存酒店信息
     */
    function addHotel() {
        var url = "/hotelServer/saveHotelInfo";
        var data = {
            "hoteltype": $("#hotelType").val(),
            "num1": $("#num1").val(),
            "num2": $("#num2").val(),
            "num3": $("#num3").val(),
            "hotelname": $("#hotelName").val(),
            "hoteladdress": $("#hotelAddress").val(),
            "hoteltel": $("#hotelTel").val(),
            "content": $("#content").val(),
            "images": $("#images").val()
        };
        console.log(data);
        if(null == data.hotelname || ""==data.hotelname){
            alert("酒店名称必填");
        }
        $.post(url, data, function (data) {
            if (data.status > 0) {
                alert(data.message);
                location.reload();
            }
        });
    }

    /**
     * 添加酒店取消
     */
    function addhotelCancel() {
        document.getElementById("showAddHotel").style.display = "none";
    }

    /**
     * 显示删除酒店提示信息
     * @param id
     */
    function delHotel(id) {
        $("#message").text("是否确认删除？");
        document.getElementById("messageModel").style.display = "block";
        $("#messageNo").click(function () {
            document.getElementById("messageModel").style.display = "none";
        });
        $("#messageYes").click(function () {
            console.log("开始提交");
            $.post("/hotelServer/delHotelInfo", {'id': id}, function (data) {
                console.log(data);
                if (data.status > 0) {
                    location.reload();
                }
            })
        });
    }

    function hotelList(){
        window.location = "/hotelServer/hotelList";
    }
</script>
</body>
</html>
