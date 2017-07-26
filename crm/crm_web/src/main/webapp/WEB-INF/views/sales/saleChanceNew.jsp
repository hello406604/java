<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>凯盛软件CRM-首页</title>
    <%@ include file="../base/base-css.jsp"%>
    <link rel="stylesheet" href="/static/plugins/select2/select2.min.css">
</head>
<body class="hold-transition skin-blue sidebar-mini">
<!-- Site wrapper -->
<div class="wrapper">
    <jsp:include page="../base/base-side.jsp">
        <jsp:param name="active" value="home"/>
    </jsp:include>
    <!-- 右侧内容部分 -->

            <div class="content-wrapper">

                <!-- Main content -->
                <section class="content">

                    <!-- Default box -->
                    <div class="box">
                        <div class="box-header with-border">
                            <h3 class="box-title">我的销售机会</h3>

                            <div class="box-tools pull-right">
                                <a href="/saleChance/my" type="button" class="btn btn-primary">
                                    返回列表
                                </a>
                            </div>
                        </div>
                        <div class="box-body">
                            <form method="post" id="salesForm" action="/saleChance/my/new">
                                <div class="form-group">
                                    <label>机会名称</label>
                                    <input type="text" name="salesName" id="salesName" class="form-control">
                                </div>
                                <div class="form-group">
                                    <label>关联客户</label>
                                    <select id="customerId" name="customerId" class="form-control">
                                        <option value="">请选择客户</option>
                                        <c:forEach items="${customerList}" var="customer">
                                        <option value="${customer.id}">${customer.custName}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                                <div class="form-group">
                                    <label>机会价值</label>
                                    <input type="text" name="salesValue" id="salesValue" class="form-control">
                                </div>
                                <div class="form-group">
                                    <label>当前进度</label>
                                    <select name="currentProgress" id="currentProgress" class="form-control">
                                        <option value="">请选择进度</option>
                                        <c:forEach items="${currentProgress}" var="progress">
                                            <option value="${progress}">${progress}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                                <div class="form-group">
                                    <label>详细内容</label>
                                    <textarea name="content" id="content" class="form-control"></textarea>
                                </div>
                            </form>
                        </div>
                        <!-- /.box-body -->
                        <div class="box-footer">
                            <button id="saveBtn" class="btn btn-primary">保存</button>
                        </div>
                    </div>
                    <!-- /.box -->

                </section>
                <!-- /.content -->
            </div>

    <%@ include file="../base/base-footer.jsp"%>

</div>
<!-- ./wrapper -->

<%@include file="../base/base-js.jsp"%>
<script src="/static/plugins/validate/jquery.validate.js"></script>
<script src="/static/plugins/select2/select2.min.js"></script>
<script>

    $(function () {
        $("#client").select2();

        $("#saveBtn").click(function () {
            $("#salesForm").submit();
        });

        $("#salesForm").validate({
            errorClass:'text-danger',
            errorElement:'span',
            rules:{
                salesName:{
                    required : true
                },
                customerId : {
                    required : true
                },
                salesValue : {
                    required : true,
                    number : true
                },
                currentProgress : {
                    required : true
                }
            },
            messages:{
                salesName:{
                    required : "请输入机会名称"
                },
                customerId : {
                    required : "请选择客户"
                },
                salesValue : {
                    required : "请输入机会价值",
                    number : "请输入正确的金额"
                },
                currentProgress : {
                    required : "请选择当前进度"
                }
            }
        });

    });

</script>
</body>
</html>
