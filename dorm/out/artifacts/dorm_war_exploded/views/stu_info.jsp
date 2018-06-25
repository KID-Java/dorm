<%--
  Created by IntelliJ IDEA.
  User: LinGuoLai
  Date: 2018/6/4
  Time: 20:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>学生信息</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/statics/css/table.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/statics/css/main.css">
</head>
<body>
<div style="width: 100%" class="prerequisite">
    <div class="title">
        <span>学生信息管理</span>
    </div>
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
                <th>操作</th>
            </tr>
        </thead>
        <tbody>
        <c:forEach var="student" items="${pageBean.list}">
            <tr>
                <td>${student.id}</td>
                <td>${student.name}</td>
                <td>${student.age}</td>
                <td>${student.sex}</td>
                <td>${student.telphone}</td>
                <td>${student.major}</td>
                <td>${student.department}</td>
                <td>
                    <a href="${pageContext.request.contextPath}/stu?m=toEdit&stuId=${student.id}&currentPage=${pageBean.currentPage}">修改</a>
                    &nbsp;
                    <a href="${pageContext.request.contextPath}/stu?m=deleteStu&stuId=${student.id}&currentPage=${pageBean.currentPage}">删除</a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    </div>

    <hr/>
    <%--显示分页--%>
    <div style="text-align: center">
        共${pageBean.totalRecordNumber}条记录&nbsp;  ${pageBean.pageNumber}条/每页&nbsp;  第${pageBean.currentPage}页/共${pageBean.toatlPageNumber}页&nbsp;
        <c:choose>
            <c:when test="${pageBean.currentPage == 1}">
                首页 &nbsp;上一页
            </c:when>
            <c:otherwise>
                <a href="${pageBean.url}&currentPageNum=${1 }">首页</a>&nbsp;
                <a href="${pageBean.url}&currentPageNum=${pageBean.currentPage -1 }">上一页</a>
            </c:otherwise>
        </c:choose>
        &nbsp;
        <%--设置起始页码--%>
        <c:choose>
            <c:when test="${pageBean.currentPage - 5 <= 0 }">
                <c:set var="start" value="1"/>
            </c:when>
            <c:otherwise>
                <c:set var="start" value="${pageBean.currentPage - 5}"/>
            </c:otherwise>
        </c:choose>

        <%--设置结束页码--%>
        <c:choose>
            <c:when test="${start + 9 > pageBean.toatlPageNumber}">
                <c:set var="last" value="${pageBean.toatlPageNumber}"/>
            </c:when>
            <c:otherwise>
                <c:set var="last" value="${start + 9}"/>
            </c:otherwise>
        </c:choose>
        <%--循环打印页码--%>
        <c:forEach var="currentPageNum" begin="${start}" end="${last}">
            <c:choose>
                <c:when test="${currentPageNum == pageBean.currentPage}">
                    [${currentPageNum}]&nbsp;
                </c:when>
                <c:otherwise>
                    <a href="${pageBean.url}&currentPageNum=${currentPageNum}">[${currentPageNum}]</a>&nbsp;
                </c:otherwise>
            </c:choose>

        </c:forEach>
        &nbsp;
        <c:choose>
            <c:when test="${pageBean.currentPage == pageBean.toatlPageNumber}">
                下一页 &nbsp;尾页
            </c:when>
            <c:otherwise>
                <a href="${pageBean.url}&currentPageNum=${pageBean.currentPage + 1 }">下一页</a>&nbsp;
                <a href="${pageBean.url}&currentPageNum=${pageBean.toatlPageNumber }">尾页</a>
            </c:otherwise>
        </c:choose>


        <h3 class="msg">${msg}</h3>

    </div>




</body>
</html>
