<%@ page contentType="text/html;charset=UTF-8"  language="java" %>
<%@include file="common/tag.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title>秒杀列表页</title>
    <%@include file="common/head.jsp" %>
</head>
<body>
<%--页面显示部分--%>
<div class="container">
    <div class="panel panel-default">
        <div class="panel-heading text-center">
            <h2> 秒殺列表 </h2>
        </div>
        <div class="panel-body">
            <table class="table table-hover">
                <thead>
                <tr>
                    <th>名称</th>
                    <th>库存</th>
                    <th>开始时间</th>
                    <th>结束时间</th>
                    <th>创建时间</th>
                    <th>详情页</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="sk" items="${list}">
                    <tr>
                    <td>${sk.name}</td>
                    <td>${sk.number}</td>
                   <td>
                       <fmt:formatDate value="${sk.startTime}" pattern="yyyy-MM-dd hh:mm:ss"></fmt:formatDate>
                   </td>
                    <td>
                        <fmt:formatDate value="${sk.endTime}" pattern="yyyy-MM-dd hh:mm:ss"></fmt:formatDate>
                    </td>
                    <td>
                        <fmt:formatDate value="${sk.createTime}" pattern="yyyy-MM-dd hh:mm:ss"></fmt:formatDate>
                    </td>
                    <td>
                        <a class="btn btn-info" href="/seckill/${sk.seckillId}/detail" target="_blank"></a>
                    </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>

        </div>
    </div>
</div>


<!-- jQuery (Bootstrap 的所有 JavaScript 插件都依赖 jQuery，所以必须放在前边) -->
<script src="https://cdn.bootcss.com/jquery/1.12.4/jquery.min.js"></script>
<!-- 加载 Bootstrap 的所有 JavaScript 插件。你也可以根据需要只加载单个插件。 -->
<script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</body>
</html>