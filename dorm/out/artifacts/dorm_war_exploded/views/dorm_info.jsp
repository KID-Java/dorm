<%--
  Created by IntelliJ IDEA.
  User: LinGuoLai
  Date: 2018/6/8
  Time: 14:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>宿舍信息管理</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/statics/css/table.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/statics/css/main.css">
</head>
<body>
<div style="width: 100%" class="prerequisite">
    <div class="title">
        <span>宿舍信息管理</span>
    </div>
    <table border="2px">
        <thead>
        <tr>
            <th>宿舍编号</th>
            <th>所属公寓</th>
            <th>舍长</th>
            <th>宿舍电话</th>
            <th>宿舍人数</th>
            <th>宿舍设备</th>
            <th>卫生状况</th>
            <th>宿舍结构</th>
            <th>详细信息</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="dormitory" items="${pageBean.list}">
            <tr>
                <td>${dormitory.id}</td>
                <td>${dormitory.apartment}</td>
                <td>${dormitory.headmaster.name}</td>
                <td>${dormitory.phone}</td>
                <td>${dormitory.personNum}</td>
                <td>${dormitory.equipment}</td>
                <td>${dormitory.environment}</td>
                <td>${dormitory.frame}</td>
                <td>
                    <a href="${pageContext.request.contextPath}/dorm?m=detailedInfo&id=${dormitory.id}">详细信息</a>

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
            <a href="${pageBean.url}&currentPageNum=1">首页</a>&nbsp;
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


</div>

</body>
</html>
