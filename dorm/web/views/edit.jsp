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
    <title>修改学生信息</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/statics/css/main.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/statics/css/table.css">
</head>
<body>
    <div align="center">
        <form action="${pageContext.request.contextPath}/stu?m=editStu" method="post">
            <div style="width: 100%" class="prerequisite">
                <div class="title">
                    <span>学生信息修改</span>
                </div>
                <h3 class="msg">${msg}</h3>

                <input type="hidden" name="id" value="${student.id}">
                <input type="hidden" name="currentPage" value="${currentPage}">

                <table align="center" style="width: 25%;height: 40%;">
                    <tr>
                        <td align="right">姓名:</td>
                        <td ><input type="text" name="name" value="${student.name}"> </td>
                    </tr>
                    <tr>
                        <td align="right">年龄:</td>
                        <td ><input type="text" name="age" value="${student.age}"> </td>
                    </tr>
                    <tr>
                        <td align="right">性别:</td>
                        <td ><input type="text" name="sex" value="${student.sex}"> </td>
                    </tr>
                    <tr>
                        <td align="right">电话:</td>
                        <td ><input type="text" name="telphone" value="${student.telphone}"> </td>
                    </tr>
                    <tr>
                        <td align="right">专业:</td>
                        <td ><input type="text" name="major" value="${student.major}"> </td>
                    </tr>
                    <tr>
                        <td align="right">院系:</td>
                        <td ><input type="text" name="department" value="${student.department}"> </td>
                    </tr>
                </table>
                <br/>
                <div align="center">
                    <input class="button" type="submit" value="修改">&nbsp;
                    <input class="button" type="reset" value="重置">
                </div>
            </div>

        </form>
    </div>


</body>
</html>