<%--
  Created by IntelliJ IDEA.
  User: LinGuoLai
  Date: 2018/6/10
  Time: 12:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>来访登记记录</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/statics/css/table.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/statics/css/main.css">
</head>
<body>
<div align="center">
    <div style="width: 100%" class="prerequisite">
        <div class="title">
            <span>来访登记记录</span>
        </div>

        <c:forEach var="visitInfo" items="${pageBean.list}">
            <table border="2px">
                <thead>
                <tr>
                    <th colspan="3" style="text-align: left;padding-left: 10px">${visitInfo.name}(${visitInfo.address})</th>
                </tr>
                </thead>
                <tbody>
                    <tr>
                        <td>来访时间:<fmt:formatDate value="${visitInfo.date}" pattern="yyyy年MM月dd日 HH时mm分ss秒"/> </td>
                        <td>是否本校工作人员：<span style="color: red">${visitInfo.status == 1 ? '是' : '否'}</span></td>
                        <td>来访者地址：${visitInfo.address}</td>
                    </tr>
                    <tr>
                        <td colspan="3">${visitInfo.content}</td>
                    </tr>
                </tbody>
            </table>
            <hr/>
        </c:forEach>
    </div>

    <%--显示分页--%>
    <div style="text-align: center">
        共${pageBean.totalRecordNumber}条记录&nbsp;  ${pageBean.pageNumber}条/每页&nbsp;  第${pageBean.currentPage}页/共${pageBean.toatlPageNumber}页&nbsp;
        <c:choose>
            <c:when test="${pageBean.currentPage == 1}">
                首页 &nbsp;上一页
            </c:when>
            <c:otherwise>
                <a href="${pageContext.request.contextPath}/visit?m=visitInfo&currentPageNum=${1 }">首页</a>&nbsp;
                <a href="${pageContext.request.contextPath}/visit?m=visitInfo&currentPageNum=${pageBean.currentPage -1 }">上一页</a>
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
                    <a href="${pageContext.request.contextPath}/visit?m=visitInfo&currentPageNum=${currentPageNum}">[${currentPageNum}]</a>&nbsp;
                </c:otherwise>
            </c:choose>

        </c:forEach>
        &nbsp;
        <c:choose>
            <c:when test="${pageBean.currentPage == pageBean.toatlPageNumber}">
                下一页 &nbsp;尾页
            </c:when>
            <c:otherwise>
                <a href="${pageContext.request.contextPath}/visit?m=visitInfo&currentPageNum=${pageBean.currentPage + 1 }">下一页</a>&nbsp;
                <a href="${pageContext.request.contextPath}/visit?m=visitInfo&currentPageNum=${pageBean.toatlPageNumber }">尾页</a>
            </c:otherwise>
        </c:choose>
</div>

</body>
</html>
