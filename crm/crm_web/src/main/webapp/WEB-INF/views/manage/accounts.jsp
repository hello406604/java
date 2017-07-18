<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>凯盛软件CRM-首页</title>
    <%@ include file="../base/base-css.jsp"%>
    <link rel="stylesheet" href="/static/plugins/tree/css/metroStyle/metroStyle.css">
</head>
<body class="hold-transition skin-blue sidebar-mini">
<!-- Site wrapper -->
<div class="wrapper">
    <%@include file="../base/base-side.jsp"%>
    <!-- 右侧内容部分 -->
    <div class="content-wrapper">

        <!-- Main content -->
        <section class="content">

            <div class="row">
                <div class="col-md-2">
                    <div class="box">
                        <div class="box-body">
                            <button class="btn btn-default" id="addDeptBtn">添加部门</button>
                            <ul id="ztree" class="ztree"></ul>
                        </div>
                    </div>
                </div>
                <div class="col-md-10">
                    <!-- Default box -->
                    <div class="box">
                        <div class="box-header with-border">
                            <h3 class="box-title">员工管理</h3>
                            <div class="box-tools pull-right">
                                <button type="button" class="btn btn-box-tool" id="addAccountBtn">
                                    <i class="fa fa-plus"></i> 添加员工</button>
                            </div>
                        </div>
                        <div class="box-body">
                            <table class="table">
                                <thead>
                                <tr>
                                    <th>姓名</th>
                                    <th>部门</th>
                                    <th>手机</th>
                                    <th>#</th>
                                </tr>
                                </thead>
                            </table>
                        </div>
                    </div>
                    <!-- /.box -->
                </div>
            </div>

        </section>
        <!-- /.content -->
    </div>

    <!-- Modal -->
    <div class="modal fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" id="addAccountModal">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title" id="myModalLabel">添加用户</h4>
                </div>
                <div class="modal-body">
                    <form id="addAccountForm">
                        <div class="form-group">
                            <label> 姓名</label>
                            <input type="text" name="userName" class="form-control">
                        </div>
                        <div class="form-group">
                            <label> 密码(默认值为123123)</label>
                            <input type="password" name="password" class="form-control" value="123123">
                        </div>
                        <div class="form-group">
                            <label> 电话</label>
                            <input type="text" name="mobile" class="form-control">
                        </div>
                        <div class="form-group">
                            <label>部门</label>
                            <div id="deptArea">
                            </div>
                        </div>
                    </form>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                    <button type="button" class="btn btn-primary" id="saveAccountBtn">保存</button>
                </div>
            </div>
        </div>
    </div>
    <!-- /.content-wrapper -->

    <%@ include file="../base/base-footer.jsp"%>


</div>
<!-- ./wrapper -->

<%@include file="../base/base-js.jsp"%>
<script src="/static/plugins/tree/js/jquery.ztree.all.min.js"></script>
<script src="/static/plugins/layer/layer.js"></script>
<script src="/static/plugins/validate/jquery.validate.js"></script>
</body>
</html>


<script>
    $(function () {
        var setting = {
            data: {
                simpleData:{
                    enable : true
                }
            },
            async: {
                enable : true,
                url:"/manage/account/depts.json"
            },
            callback : function(event,treeId,treeNode,clickFlag) {
                layer.alert(treeNode.id + treeNode.name + treeNode.pId);
            }
        }
        var tree = $.fn.zTree.init($("#ztree"), setting);

        //添加部门
        $("#addDeptBtn").click(function () {
            layer.prompt("请输入要添加的部门名称",function (text,index) {
                layer.close(index);
                $.post("/manage/account/dept/new",{"deptName":text,"pId":1}).done(function (data) {
                    alert(data.state);
                    if (data.state == "success") {
                        layer.msg("添加成功");
                        tree.reAsyncChildNodes(null,"refresh");
                    } else {
                        layer.msg(data.message);
                    }
                }).error(function () {
                    layer.alert("服务器异常");
                });
            });
        });

        $("#addAccountBtn").click(function() {
            $("#deptArea").html("");
            $.post("/manage/account/depts.json").done(function(data) {
                if (data.length == 0 || data.length == 1) {
                    layer.msg("请先创建部门");
                    return;
                }
                for (var i = 0 ; i < data.length ; i++) {
                    var obj = data[i];
                    if (obj.pId != 0){
                        var html = '<label class="checkbox-inline"><input type="checkbox" name="deptId" value="'+ obj.id +'" >'+ obj.name +'</label>'
                        $(html).appendTo($("#deptArea"));
                    }
                }
                $("#addAccountModal").modal({
                    show:true,
                    backdrop:'static'
                });
            }).error(function () {
                layer.msg("服务器异常")
            });
        });

        $("#saveAccountBtn").click(function () {
            $("#addAccountForm").submit();
        });

        $("#addAccountForm").validate({
            errorClass : 'text-danger',
            errorElement : 'span',
            rules : {
                userName: {
                    required : true
                },
                password : {
                    required : true
                },
                mobile : {
                    required : true
                },
                deptId : {
                    required : true
                }
            },
            messages : {
                userName: {
                    required : "请输入账号"
                },
                password : {
                    required : "请输入密码"
                },
                mobile : {
                    required : "请输入手机号码"
                },
                deptId : {
                    required : "请选择所属部门"
                }
            },
            submitHandler : function () {
                $.post("/manage/account/new",$("#addAccountForm").serialize()).done (function (data) {
                    if(data.state == "success") {
                        layer.msg("添加成功");
                        $("#addAccountForm")[0].reset();
                        $("#addAccountModal").modal('hide');
                    } else {
                        layer.msg(data.message);
                    }
                }).error(function () {
                    layer.msg("服务器异常")
                })
            }
        });

    });
</script>
