<%--
  Created by IntelliJ IDEA.
  User: LinGuoLai
  Date: 2018/6/9
  Time: 20:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>宿舍详细信息</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/statics/css/table.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/statics/css/main.css">
</head>
<body>
<div align="center">
    <div style="width: 100%" class="prerequisite">
        <div class="title">
            <span>宿舍详细信息</span>
        </div>

        <input type="hidden" name="id" value="${dormitory.id}">
        <input type="hidden" name="currentPage" value="${currentPage}">

        <table class="dataintable" style="height: 30%;">
            <tr>
                <td align="right">宿舍编号:</td>
                <td ><input type="text" name="name" value="${dormitory.id}"> </td>
                <td align="right">所属公寓:</td>
                <td ><input type="text" name="name" value="${dormitory.apartment}"> </td>
            </tr>
            <tr>
                <td align="right">舍长姓名:</td>
                <td ><input type="text" name="age" value="${dormitory.phone}"> </td>
                <td align="right">宿舍电话:</td>
                <td ><input type="text" name="age" value="${dormitory.headmaster.name}"> </td>
            </tr>
            <tr>
                <td align="right">宿舍人数:</td>
                <td ><input type="text" name="sex" value="${dormitory.personNum}"> </td>
                <td align="right">宿舍设备:</td>
                <td ><input type="text" name="age" value="${dormitory.equipment}"> </td>
            </tr>
            <tr>
                <td align="right">卫生状况:</td>
                <td ><input type="text" name="telphone" value="${dormitory.environment}"> </td>
                <td align="right">宿舍结构:</td>
                <td ><input type="text" name="age" value="${dormitory.frame}"> </td>
            </tr>
        </table>

        <hr/>
        宿舍成员信息
        <hr/>

        <table border="2px">
            <thead>
            <tr>
                <th>学号</th>
                <th>姓名</th>
                <th>年龄</th>
                <th>性别</th>
                <th>电话</th>
                <th>专业</th>
                <th>院系</th>
                <th>描述</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="student" items="${dormitory.students}">
                <tr>
                    <td>${student.id}</td>
                    <td>${student.name}</td>
                    <td>${student.age}</td>
                    <td>${student.sex}</td>
                    <td>${student.telphone}</td>
                    <td>${student.major}</td>
                    <td>${student.department}</td>
                    <td>
                        <c:choose>
                            <c:when test="${dormitory.headmaster.id == student.id}">
                                <p style="color: red;">舍长</p>
                            </c:when>
                            <c:otherwise>
                                &nbsp;
                            </c:otherwise>
                        </c:choose>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>


</body>
</html>