<%--
  Created by IntelliJ IDEA.
  User: LinGuoLai
  Date: 2018/6/8
  Time: 9:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>宿舍评分信息修改</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/statics/css/main.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/statics/css/table.css">
</head>
<body>
    <div align="center">
        <form action="${pageContext.request.contextPath}/dorm?m=editScore" method="post">
            <div style="width: 100%" class="prerequisite">
                <div class="title">
                    <span>宿舍评分信息修改</span>
                </div>
                <h3 class="msg">${msg}</h3>

                <input type="hidden" name="id" value="${dormitory.id}">
                <input type="hidden" name="currentPage" value="${currentPage}">

                <table align="center" style="width: 25%;height: 40%;">
                    <tr>
                        <td align="right">宿舍编号:</td>
                        <td >${dormitory.id}</td>
                    </tr>
                    <tr>
                        <td align="right">所在公寓:</td>
                        <td >${dormitory.apartment} </td>
                    </tr>
                    <tr>
                        <td align="right">舍长:</td>
                        <td >${dormitory.headmaster.name}</td>
                    </tr>
                    <tr>
                        <td align="right">宿舍电话:</td>
                        <td >${dormitory.phone} </td>
                    </tr>
                    <tr>
                        <td align="right">宿舍人数:</td>
                        <td>${dormitory.personNum}</td>
                    </tr>
                    <tr>
                        <td align="right">本周评分:</td>
                        <td ><input type="text" name="score" value="${dormitory.score}"> </td>
                    </tr>
                </table>
                <br/>
                <div align="center">
                    <input class="button" type="submit" value="修改">&nbsp;
                </div>
            </div>

        </form>
    </div>


</body>
</html>