<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link rel="stylesheet" href="/static/css/bootstrap.min.css">
    <link rel="stylesheet" href="/static/css/font-awesome.min.css">
    <title>movie 首页</title>
</head>
<body>
    <div class="container">
        <div class="panel panel-info">
            <div class="panel-heading">搜索</div>
            <div class="panel-body">
                <form class="form-inline" method="get">
                    <input type="text" class="form-control" name="title" placeholder="电影名称" value="${title}">
                    <input type="text" class="form-control" name="daoyan" placeholder="导演" value="${daoyan}">
                    <input type="text" class="form-control" name="min" placeholder="最小分值" value="${min}">
                    <input type="text" class="form-control" name="max" placeholder="最大分值" value="${max}">
                    <button class="btn btn-default"><i class="fa fa-search"></i> 搜</button>
                </form>
            </div>
        </div>
        <a href="/movie/new" class="btn btn-info">
            添加数据
        </a>
        <table class="table">
            <c:if test="${not empty message}">
                <div class="alert alert-success">
                    ${message}
                </div>
            </c:if>
            <thead>
                <tr>
                    <th>电影名称</th>
                    <th>导演</th>
                    <th>上映时间</th>
                    <th>发行时间</th>
                    <th>评分</th>
                    <th>操作</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${movieList.list}" var="movie">
                    <tr>
                        <td>${movie.title}</td>
                        <td>${movie.daoyan}</td>
                        <td>${movie.sendtime}</td>
                        <td>${movie.releaseyear}</td>
                        <td>${movie.rate}</td>
                        <td><a href="javascript:;" rel="${movie.id}" class="del"><i class="fa fa-trash"></i> </a>&nbsp;
                            <a href="javascript:;" rel="${movie.id}" class="update"> <i class="fa fa-pencil"></i></a>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
        <ul id="pagination-demo" class="pagination-sm"></ul>
    </div>

    <script src="/static/js/jquery.js"></script>
    <script src="/static/layer/layer.js"></script>
    <script src="/static/js/jquery.twbsPagination.min.js"></script>
    <script>
        $(function(){
            //分页
            $("#pagination-demo").twbsPagination({
                totalPages:${movieList.pages},
                visiblePages:5,
                first:"首页",
                last:"末页",
                prev:"上一页",
                next:"下一页",
                href:"?pageNo={{number}}"
            });

            $(".del").click(function(){
                var id = $(this).attr("rel");
                layer.confirm("确定要删除么？",function () {
                    window.location.href="/movie/del/"+id;
                })
            });

            $(".update").click(function () {

            });
        });
    </script>
</body>
</html>