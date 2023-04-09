<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>预订列表</title>
    <script type="text/javascript" src="/static/EasyUI/jquery-1.10.2.min.js"></script>
</head>
<style>
    .h {
        overflow-y: scroll;
        padding-right: 10px;
        height: 600px;
    }

</style>
<body class="h" style="position: absolute; border:1px red solid; width: 320px; height: 500px;  left:35%;top:25%;">

<div style="width: 320px; height: 100%; overflow: hidden; /*padding-right: 17px; box-sizing: content-box;*/">
    <div style="width: 350px; height: 100%; overflow-y:scroll; overflow-x:hidden; /*padding-right: 17px; box-sizing: content-box;*/">
        <div style="width:350px; height: 30px;">
            <div style="width: 100%; height: 30px; text-align: center; float: left;font-weight:bold;">
                <c:if test="${userInfo.userType == 1}">
                    <span>管理酒店预订列表</span>
                </c:if>
                <c:if test="${userInfo.userType == 2}">
                    <span>预订列表</span>
                </c:if>
            </div>
        </div>
        <c:forEach items="${hotelOrderList}" var="hotelOrder">
            <div style="width:313px;height: 250px;border:1px solid darkgray; border-radius: 3px;margin-top:5px;margin-left: 5px; /*position: relative;*/">
                <div style="font-size: 12px;color: grey; margin-top:7px;">
                    酒店名称：${hotelInfoMap.get(hotelOrder.hotelId).hotelname}</div>
                <div style="font-size: 12px;color: grey; margin-top:7px;">类型：
                    <c:choose>
                        <c:when test="${hotelInfoMap.get(hotelOrder.hotelId).hoteltype == 2}">
                            豪华高端
                        </c:when>
                        <c:when test="${hotelInfoMap.get(hotelOrder.hotelId).hoteltype == 3}">
                            精品酒店
                        </c:when>
                        <c:otherwise>
                            经济连锁
                        </c:otherwise>
                    </c:choose>
                </div>
                <div style="font-size: 12px;color: grey; margin-top:7px;">房间号：${hotelOrder.orderNo}</div>
                <div style="font-size: 12px;color: grey; margin-top:7px;">
                    地址：${hotelInfoMap.get(hotelOrder.hotelId).hoteladdress}</div>
                <div style="font-size: 12px;color: grey; margin-top:7px;">姓名：${hotelOrder.userName}</div>
                <div style="font-size: 12px;color: grey; margin-top:7px;">电话：${hotelOrder.userMobile}</div>
                <div style="font-size: 12px;color: grey; margin-top:7px;">开始时间：${hotelOrder.createTime}</div>
                <div style="font-size: 12px;color: grey; margin-top:7px;">结束时间：${hotelOrder.endTime}</div>
                <div style="font-size: 12px;color: grey; margin-top:7px;">房间数：${hotelOrder.roomNum}</div>
                <div style="width: 100%; height: 15px; float: right; display: block; margin-right: 20px; text-align: right;">
                    <%--<c:if test="${userInfo.userType == 2}">--%>
                        <button onclick="editHotelOrder(${hotelOrder.id})">更改</button>
                    <%--</c:if>--%>
                </div>
            </div>
        </c:forEach>

        <div id="recerveHotel"
             style="width: 265px; height: 240px; position: fixed; border:1px solid gray; border-radius: 7px; margin: -370px 12px 0px 30px; background-color: white; display: none">
            <input type="hidden" id="images" name="images"/>
            <div id="message"
                 style="width: 100%; height: 30px; display: none; text-align: center; margin-top:5px; border-top: 1px solid gray; border-bottom: 1px solid gray;"></div>
            <table>
                <tr>
                    <td style="width: 70px;">类型</td>
                    <td>
                        <select id="roomType" name="roomType" disabled>
                            <option value="1">标间</option>
                            <option value="2">双人间</option>
                            <option value="3">单人间</option>
                        </select>
                    </td>
                    <%--<td><input id="hotelType" name="hoteltype"/></td>--%>
                </tr>
                <tr>
                    <td>用户名</td>
                    <td><input id="userName" name="userName" placeholder="请输入用户名"/></td>
                </tr>
                <tr>
                    <td>电话</td>
                    <td><input id="userMobile" name="userMobile" placeholder="请输入电话"/></td>
                </tr>
                <tr>
                    <td>开始时间</td>
                    <td><input id="createTime" name="createTime" placeholder="请输入开始时间" value="2021-05-05"/></td>
                </tr>
                <tr>
                    <td>结束时间</td>
                    <td><input id="endTime" name="endTime" placeholder="请输入结束时间" value="2021-05-06"/></td>
                </tr>
                <tr>
                    <td>房间间数</td>
                    <td><input id="roomNum" name="roomNum" disabled/></td>
                </tr>
                <input id="id" type="hidden"/>
            </table>
            <button onclick="recerveOk()">确定</button>
            <button onclick="recerveCancel()">取消</button>
        </div>
    </div>

    <input type="hidden" id="hotelId" value="${hotelInfo.id}"/>

</div>
<script>
    function editHotelOrder(id) {
        console.log(id);
        var url = "/hotelServer/getHotelOrder?id=" + id;
        $.get(url, function (data) {
            console.log(data);
            $("#userName").val(data.hotelOrder.userName);
            $("#userMobile").val(data.hotelOrder.userMobile);
            $("#createTime").val(data.hotelOrder.createTime);
            $("#endTime").val(data.hotelOrder.endTime);
            $("#roomNum").val(data.hotelOrder.roomNum);
            $("#hotelId").val(data.hotelOrder.hotelId);
            $("#roomType").val(data.hotelOrder.roomType);
            $("#id").val(data.hotelOrder.id);
            document.getElementById("recerveHotel").style.display = "block";
        });
    }

    function recerveOk() {
        var url = "/hotelServer/editHotelOrder";
        var data = {
            "userName": $("#userName").val(),
            "userMobile": $("#userMobile").val(),
            "createTime": $("#createTime").val(),
            "endTime": $("#endTime").val(),
            "id": $("#id").val()
        };
        $.post(url, data, function (data) {
            console.log(data);
            alert(data.message);
            location.reload();
        });
    }

    function recerveCancel() {
        document.getElementById("recerveHotel").style.display = "none";
        $("#recerveHotel").find("input").val("");
        document.getElementById("message").style.display = "none";
    }
</script>
</body>
</html>
