<%--
  Created by IntelliJ IDEA.
  User: zcw
  Date: 2021/4/28
  Time: 12:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>登录</title>
</head>
<body style="position: absolute; border:1px red solid; width: 300px; height: 500px;  left:35%;top:25%;">
    <div style="width: 100%; height: 30px; text-align: center">
        <h3>账号登录</h3>
    </div>
    <div style="width: 100%;height: 100%;">
        <form action="/userInfo/userLogin" method="post">

            <table style="width: 100%;height: 100px;margin-top: 100px;">
                <tr>
                    <td style="width: 60px; height: 30px;">用户名</td><td><input id="userName" type="text" name="userName" placeholder="请输入用户名"/></td>
                </tr>
                <tr>
                    <td style="width: 60px; height: 30px;">密码</td><td><input id="password" type="password" name="password" placeholder="请输入密码"/><span>⊙</span></td>
                </tr>
                <%--<tr>
                    <td><input type="text" placeholder="请输入验证码"/></td>
                </tr>--%>
            </table>
            <div style="width: 100%; height: 30px;">
                <input style="color:white;background-color: #ffd23b; width:100%; height: 100%" type="submit" value="登录" />
            </div>
        </form>
    </div>
</body>
</html>
