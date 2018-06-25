<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: LinGuoLai
  Date: 2018/6/4
  Time: 16:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>宿舍信息管理系统</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/statics/css/main.css">
    <script type="text/javascript" src="${pageContext.request.contextPath}/statics/js/main.js"></script>
  </head>
  <body>

  <div class="main">

    <div class="title">
      <h2>宿舍信息管理系统</h2>
    </div>

    <div class="welcome">

      <div style="float: left">
        <p>
          <span class="timer" id="timer">1997年03月17日 星期一</span> 欢迎登陆宿舍信息管理系统！
        </p>
      </div>
      <div style="padding: 12px;float: right;">
          <button class="button" onclick="changeFrame('${pageContext.request.contextPath}/views/home.jsp')">首页</button>
          <button class="button" onclick="changeFrame('${pageContext.request.contextPath}/stu?m=stuInfo')">学生信息管理</button>
          <button class="button" onclick="changeFrame('${pageContext.request.contextPath}/dorm?m=dormInfo')">宿舍查询</button>
          <button class="button" onclick="changeFrame('${pageContext.request.contextPath}/views/visit.jsp')">访客登记</button>
          <button class="button" onclick="changeFrame('${pageContext.request.contextPath}/dorm?m=dormInfo&isScore=true')">宿舍评分</button>
      </div>

    </div>

    <hr>

    <div style="padding: 5px;">

      <div class="prerequisite">
        <div class="title">
          <span>综合查询区域</span>
        </div>

        <div align="center" style="padding-top: 20px;">
          <table>
            <tr>
              <td>关键字:</td>
              <td><input type="text" id="keyword"></td>
            </tr>
            <tr>
              <td>选条件:</td>
              <td >
                <select style="width: 100%" id="prerequisite">
                  <option value="${pageContext.request.contextPath}/stu?m=stuInfo&name=">姓名</option>
                  <option value="${pageContext.request.contextPath}/stu?m=stuInfo&major=">专业</option>
                  <option value="${pageContext.request.contextPath}/stu?m=stuInfo&telphone=">电话</option>
                  <option value="${pageContext.request.contextPath}/dorm?m=dormInfo&phone=">宿舍电话</option>
                  <option value="${pageContext.request.contextPath}/dorm?m=dormInfo&id=">宿舍编号</option>
                </select>
              </td>
            </tr>
          </table>

          <div align="center"  style="padding-top: 10px"><button  class="button" onclick="select()">查询</button></div>

        </div>

      </div>

      <div class="frame">
        <iframe id="iframe" name="iframe" src="${pageContext.request.contextPath}/views/home.jsp" width="100%" height="100%" style="border: 0;">

        </iframe>
      </div>
      <div class="foot">
        <marquee >
          <c:choose>
            <c:when test="${lastVisitInfo == null}">
              暂无最新留言！
            </c:when>
            <c:otherwise>
              ${lastVisitInfo.name}(${lastVisitInfo.address})在${lastVisitInfo.date}的时候留言：${lastVisitInfo.content}
            </c:otherwise>
          </c:choose>
        </marquee>
      </div>
    </div>

  </div>
  </body>
</html>
