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
    <title>访客登记</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/statics/css/main.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/statics/css/table.css">
</head>
<body>
    <div align="center">
        <form action="${pageContext.request.contextPath}/visit?m=addVisitInfo" method="post">
            <div style="width: 100%" class="prerequisite">
                <div class="title">
                    <span>访客登记</span>
                    <div style="float: right;margin-right: 20px"><a href="${pageContext.request.contextPath}/visit?m=visitInfo">来访记录</a></div>
                </div>

                <div style="padding-top: 20px">

                    <table>
                        <tr>
                            <td>
                                您是否是本校工作人员:
                            </td>
                            <td>
                                <input type="radio" name="status" value="1" checked="checked">是<input type="radio" name="status" value="2">否
                            </td>
                        </tr>
                        <tr>
                            <td>
                                您来自什么地方:
                            </td>
                            <td>
                                <select name="address">
                                    <option value="">-请选择您所在的城市-</option>
                                    <option value="湛江">湛江</option>
                                    <option value="茂名">茂名</option>
                                    <option value="广州">广州</option>
                                    <option value="北京">北京</option>
                                    <option value="深圳">深圳</option>
                                    <option value="上海">上海</option>
                                    <option value="珠海">珠海</option>
                                    <option value="东莞">东莞</option>
                                </select>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                请留下您的姓名:
                            </td>
                            <td>
                                <input type="text" name="name">
                            </td>
                        </tr>
                        <tr>
                            <td>
                                请留言（包括您的建议）:
                            </td>
                            <td>
                                <textarea name="content" rows="5" cols="30"></textarea>
                            </td>
                        </tr>
                    </table>

                </div>
                <hr/>
                <input class="button" type="submit" value="提交">
                <div align="center">
                    <h3 class="msg">${msg}</h3>
                </div>
            </div>
        </form>

    </div>

</body>
</html>