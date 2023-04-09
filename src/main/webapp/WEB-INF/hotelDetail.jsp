<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>酒店详情</title>
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

<div style="width: 350px; height: 100%; /*padding-right: 17px; box-sizing: content-box;*/">
    <div style="width:350px; height: 30px;">
        <div style="width: 195px; height: 30px; text-align: right; float: left;font-weight:bold;">
            <span>酒店详情</span>
        </div>
    </div>
    <div id="showDetail" style="width:350px; height: 140px;">
        <input id="hotelInfoImgs" value="${hotelInfoImgStr}" type="hidden"/>
        <c:if test="${hotelInfoImgs!=null}">
            <%--<c:forEach items="${hotelInfoImgs}" var="image">--%>
            <img id="imgUrl" onclick='checkImg()' style="width: 320px;height: 138px; position: absolute;"
                 src="${hotelInfoImgs[0].imgUrl}"/>
            <%--</c:forEach>--%>
        </c:if>

    </div>
    <div id="hiddenDetail" style="width:350px; height: 140px; display: none">
        <input id="hotelInfoImgs" value="${hotelInfoImgStr}" type="hidden"/>
        <c:if test="${hotelInfoImgs!=null}">
            <c:forEach items="${hotelInfoImgs}" var="image">
                <img style="width: 320px;height: 138px; position: absolute;" src="${image.imgUrl}"/>
            </c:forEach>
        </c:if>

    </div>
    <div style="width:325px; height: 150px;">
        <div style="font-weight: bold; margin-top:7px;">
            ${hotelInfo.hotelname}</div>
        <div style="font-size: 12px;color: grey; margin-top:7px;">类型：
            <c:choose>
                <c:when test="${hotelInfo.hoteltype == 2}">
                    豪华高端
                </c:when>
                <c:when test="${hotelInfo.hoteltype == 3}">
                    精品酒店
                </c:when>
                <c:otherwise>
                    经济连锁
                </c:otherwise>
            </c:choose>
        </div>
        <div style="font-size: 12px;color: grey; margin-top:7px;">地址：${hotelInfo.hoteladdress}</div>
        <div style="font-size: 12px;color: grey; margin-top:7px;">电话：${hotelInfo.hoteltel}</div>
        <div style="font-size: 12px;color: grey; margin-top:7px;">
            今日房间：标间/${hotelInfo.num1}&nbsp;双人间/${hotelInfo.num2}&nbsp;单人间/${hotelInfo.num3}</div>
        <div style="color: blue; margin-top:7px;">评分：4.5分</div>
        <div style="font-size: 12px;color: grey; margin-top:7px;">描述 ${hotelInfo.content}</div>
        <c:if test="${userInfo.userType == 2}">
            <div style="width: 100%;height: 40px;">
                <button style="color: white; background-color: #ffd310; width: 80px;height: 30px;display: block; border-radius: 4px; float: right" onclick="recerveHotel()">
                    预订
                </button>
            </div>
        </c:if>
    </div>
    <input type="hidden" id="hotelId" value="${hotelInfo.id}"/>
    <div id="recerveHotel"
         style="width: 265px; height: 240px; position: fixed; border:1px solid gray; border-radius: 7px; margin: -220px 12px 0px 30px; background-color: white; display: none">
        <input type="hidden" id="images" name="images"/>
        <div id="message" style="width: 100%; height: 30px; display: none; text-align: center; margin-top:5px; border-top: 1px solid gray; border-bottom: 1px solid gray;"></div>
        <table>
            <tr>
                <td style="width: 70px;">类型</td>
                <td>
                    <select id="roomType" name="roomType">
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
                <td><input id="roomNum" name="roomNum"/></td>
            </tr>
        </table>
        <button onclick="recerveOk()">确定</button>
        <button onclick="recerveCancel()">取消</button>
    </div>
    <div id="messageModel"
         style="width: 272px; height: 120px; position: fixed; border:1px solid grey; border-radius: 7px; margin: -200px 30px 0px 30px; background-color: white; display: none">
        <span>提示</span>
        <br/>
        <span id="errorMessage"></span>
        <br/>
        <button id="messageNo" onclick="hiddenMessage()">否</button>
        <button id="messageYes" onclick="hiddenMessage()">是</button>
    </div>
</div>
<script>
    function checkImg() {
        var img = $("#imgUrl").attr("src");
        console.log(img);
        var img1 = img;
        var imgs = $("#hiddenDetail").find("img");
        var num = 0;
        for (var i = 0; i <= imgs.length - 1; ++i) {
            if (img1 === imgs[i].src) {
                num = i + 1;
            }
        }
        console.log(num);
        if (num === 0) {
            $("#imgUrl").attr("src", imgs[1].src);
        } else {
            if (num === imgs.length) {
                $("#imgUrl").attr("src", imgs[0].src);
                console.log("等於" + imgs[0].src);
            } else if (num > imgs.length) {
                $("#imgUrl").attr("src", imgs[0].src);
                console.log("大於" + imgs[0].src);
            } else {
                $("#imgUrl").attr("src", imgs[num].src);
                console.log("小於" + imgs[num].src);
            }
        }
        console.log("結束");
    }

    function recerveHotel(){
        document.getElementById("recerveHotel").style.display = "block";
        document.getElementById("message").style.display = "none";
    }

    function recerveCancel(){
        document.getElementById("recerveHotel").style.display = "none";
        $("#recerveHotel").find("input").val("");
        document.getElementById("message").style.display = "none";
    }

    function recerveOk(){
        var url = "/hotelServer/saveHotelOrder";
        var data = {
            "roomType": $("#roomType").val(),
            "userName": $("#userName").val(),
            "userMobile": $("#userMobile").val(),
            "createTime": $("#createTime").val(),
            "endTime": $("#endTime").val(),
            "roomNum": $("#roomNum").val(),
            "hotelId": $("#hotelId").val()
        };
        if(null == data.userName || ""==data.userName){
            alert("用户名必填");
        }
        if(null == data.userMobile || ""==data.userMobile){
            alert("手机号必填");
        }
        $.post(url, data, function (data) {
            console.log(data);
            if (data.status > 0) {
                alert(data.message);
                location.reload();
            }else if(data.status < 0){
                document.getElementById("message").style.display = "block";
                $("#message").html(data.message);
            }else{
                document.getElementById("messageModel").style.display = "block";
                $("#errorMessage").text(data.message);
                recerveCancel();
            }
        });
    }

    function hiddenMessage(){
        document.getElementById("messageModel").style.display = "none";
        $("#errorMessage").text("");
    }
</script>
</body>
</html>
