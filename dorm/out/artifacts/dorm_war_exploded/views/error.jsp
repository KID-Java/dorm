<%--
  Created by IntelliJ IDEA.
  User: LinGuoLai
  Date: 2018/6/7
  Time: 18:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"  isErrorPage="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Error</title>
</head>
<body>

<div align="center">


    <div align="center" style="width: 400px;border-style: groove;">

        <h3 style="color: red">
            <c:choose>
                <c:when test="${msg == null}">
                    发生未知错误！！！
                </c:when>
                <c:otherwise>
                    ${msg}
                </c:otherwise>
            </c:choose>
        </h3>

    </div>
</div>
</body>
</html>
