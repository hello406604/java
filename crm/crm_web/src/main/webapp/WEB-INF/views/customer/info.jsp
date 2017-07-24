<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>凯盛软件CRM-客户详情</title>
    <%@ include file="../base/base-css.jsp"%>
    <style>
        .td_title {
            font-weight: bold;
        }
        .star {
            font-size: 20px;
            color: #ff7400;
        }
    </style>
    <link rel="stylesheet" href="/static/plugins/select2/select2.min.css">
</head>
<body class="hold-transition skin-blue sidebar-mini">
<!-- Site wrapper -->
<div class="wrapper">
    <jsp:include page="../base/base-side.jsp">
        <jsp:param name="active" value="customer_my"/>
    </jsp:include>
    <!-- 右侧内容部分 -->
    <div class="content-wrapper">

        <!-- Main content -->
        <section class="content">
            <div class="box">
                <div class="box-header with-border">
                    <h3 class="box-title">客户资料</h3>
                    <div class="box-tools">
                        <a href="/customer/my" class="btn btn-primary btn-sm"><i class="fa fa-arrow-left"></i> 返回列表</a>
                        <a href="/customer/my/edit/${customer.id}" class="btn bg-purple btn-sm"><i class="fa fa-pencil"></i> 编辑</a>
                        <c:choose>
                            <c:when test="${not empty public1}">
                                <button id="transferBtn" rel="${customer.id}" class="btn bg-orange btn-sm"><i class="fa fa-exchange"></i> 分配任务</button>
                                <button id="shareBtn" rel="${customer.id}" class="btn bg-maroon btn-sm"><i class="fa fa-recycle"></i> 抢占客户</button>
                            </c:when>
                            <c:otherwise>
                                <button id="transferBtn" rel="${customer.id}" class="btn bg-orange btn-sm"><i class="fa fa-exchange"></i> 转交他人</button>
                                <button id="shareBtn" rel="${customer.id}" class="btn bg-maroon btn-sm"><i class="fa fa-recycle"></i> 放入公海</button>
                            </c:otherwise>
                        </c:choose>
                        <button id="delBtn" rel="${customer.id}" class="btn btn-danger btn-sm"><i class="fa fa-trash-o"></i> 删除</button>
                    </div>
                </div>
                <div class="box-body no-padding">
                    <table class="table">
                        <tr>
                            <td class="td_title">姓名</td>
                            <td>${customer.custName}</td>
                            <td class="td_title">职位</td>
                            <td>${customer.jobTitle}</td>
                            <td class="td_title">联系电话</td>
                            <td>${customer.mobile}</td>
                        </tr>
                        <tr>
                            <td class="td_title">所属行业</td>
                            <td>${customer.trade}</td>
                            <td class="td_title">客户来源</td>
                            <td>${customer.source}</td>
                            <td class="td_title">级别</td>
                            <td class="star">${customer.level}</td>
                        </tr>
                        <c:if test="${not empty customer.address}">
                            <tr>
                                <td class="td_title">地址</td>
                                <td colspan="5">${customer.address}</td>
                            </tr>
                        </c:if>
                        <c:if test="${not empty customer.mark}">
                            <tr>
                                <td class="td_title">备注</td>
                                <td colspan="5">${customer.mark}</td>
                            </tr>
                        </c:if>
                    </table>
                </div>
                <div class="box-footer">

                    <span style="...">
                        <c:if test="${not empty customer.reminder}">
                            <i class="fa fa-exchange"></i> ${customer.reminder}
                        </c:if>
                    </span>

                    <span style="color: #ccc" class="pull-right">
                        创建日期：<fmt:formatDate value="${customer.createTime}" pattern="yyyy-MM-dd HH:mm"/> &nbsp;&nbsp;&nbsp;&nbsp;
                        <c:if test="${not empty customer.updateTime}">
                            最后修改日期：<fmt:formatDate value="${customer.updateTime}" pattern="yyyy-MM-dd HH:mm"/>
                        </c:if>
                    </span>
                </div>
            </div>

            <div class="row">
                <div class="col-md-8">
                    <div class="box">
                        <div class="box-header with-border">
                            <h3 class="box-title">跟进记录</h3>
                        </div>
                        <div class="box-body">

                        </div>
                    </div>
                </div>
                <div class="col-md-4">
                    <div class="box">
                        <div class="box-header with-border">
                            <h3 class="box-title">日程安排</h3>
                        </div>
                        <div class="box-body">

                        </div>
                    </div>
                    <div class="box">
                        <div class="box-header with-border">
                            <h3 class="box-title">相关资料</h3>
                        </div>
                        <div class="box-body">

                        </div>
                    </div>
                </div>
            </div>
            <div class="modal fade" id="accountModal">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span></button>
                            <h4 class="modal-title">选择转入员工</h4>
                        </div>
                        <div class="modal-body">
                            <select id="accountId" class="form-control" style="width: 100%">
                                <option value=""></option>
                                <c:forEach items="${accList}" var="account">
                                    <option value="${account.id}">${account.userName} ( ${account.mobile} )</option>
                                </c:forEach>
                            </select>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                            <button type="button" class="btn btn-primary" id="tranBtnOk">转交</button>
                        </div>
                    </div><!-- /.modal-content -->
                </div><!-- /.modal-dialog -->
            </div><!-- /.modal -->

        </section>
        <!-- /.content -->
    </div>
    <!-- /.content-wrapper -->

    <%@ include file="../base/base-footer.jsp"%>

</div>
<!-- ./wrapper -->

<%@include file="../base/base-js.jsp"%>
<script src="/static/plugins/layer/layer.js"></script>
<script src="/static/plugins/select2/select2.full.min.js"></script>
<script>

    $(function () {

        $("#accountId").select2();

//        获取客户ID  如果要获取字符串类型的值 需要加""
        var id = ${customer.id};
//        删除客户
        $("#delBtn").click(function () {
            var id = $(this).attr("rel");
            layer.confirm("删除客户会自动删除相关数据，确定吗?",function(){
                window.location.href = "/customer/my/del/"+id;
            });
        });

        var public1 = ${public1}

//        将客户放入公海或者枪战客户
        $("#shareBtn").click(function () {
            if(!public1){
                window.location.href = "/customer/public/share/" + id;
            }else {
                layer.confirm("确定要将 [" + ${customer.custName} + "] 放入公海么?",function () {
                    window.location.href = "/customer/my/share/" + id;
                });
            }
        });

        // 将客户转交给他人
        $("#transferBtn").click(function () {
            $("#accountModal").modal({
                show:true,
                backdrop:"static",
            });
//                window.location.href = "/customer/my/transfer/" + id;
        });
        $("#tranBtnOk").click(function () {
            var accountId = $("#accountId").val();
            if (accountId) {
                window.location.href = "/customer/my/"+id+"/transfer/" + accountId;
            } else {
                layer.msg("请输入要转交的员工");
            }
        })
    })
</script>

</body>
</html>
