<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link rel="stylesheet" href="/static/css/bootstrap.min.css">
    <link rel="stylesheet" href="/static/css/font-awesome.min.css">
    <title>movie save</title>
</head>
<body>
    <div class="container">
        <div class="col-md-6 col-md-offset-3">
            <form method="post">
                <div class="form-group">
                    <label>电影名称</label>
                    <input type="text" name="title" class="form-control">
                </div>
                <div class="form-group">
                    <label>导演</label>
                    <input type="text" name="daoyan" class="form-control">
                </div>
                <div class="form-group">
                    <label>发布时间</label>
                    <input type="text" name="sendtime" class="form-control">
                </div>
                <div class="form-group">
                    <label>发行年</label>
                    <input type="text" name="releaseyear" class="form-control">
                </div>
                <div class="form-group">
                    <label>评分</label>
                    <input type="text" name="rate" class="form-control">
                </div>
                <button>保存</button>
            </form>
        </div>
    </div>
</body>
</html>