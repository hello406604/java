<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!-- 顶部导航栏部分 -->
<header class="main-header">
    <!-- Logo -->
    <a href="/home" class="logo">
        <!-- mini logo for sidebar mini 50x50 pixels -->
        <span class="logo-mini"><b>CRM</b></span>
        <!-- logo for regular state and mobile devices -->
        <span class="logo-lg"><b>凯盛</b>CRM</span>
    </a>
    <!-- Header Navbar: style can be found in header.less -->
    <nav class="navbar navbar-static-top">
        <!-- Sidebar toggle button-->
        <a href="#" class="sidebar-toggle" data-toggle="offcanvas" role="button">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
        </a>

        <div class="navbar-custom-menu">
            <ul class="nav navbar-nav">
                <!-- User Account: style can be found in dropdown.less -->
                <li class="dropdown user user-menu">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                        <img src="/static/dist/img/user2-160x160.jpg" class="user-image" alt="User Image">
                        <span class="hidden-xs">${sessionScope.curr_user.userName}</span>
                    </a>
                    <ul class="dropdown-menu">
                        <!-- User image -->
                        <li class="user-header">
                            <img src="/static/dist/img/user2-160x160.jpg" class="img-circle" alt="User Image">

                            <p>
                                ${sessionScope.curr_user.userName}
                                <small>
                                    <c:forEach items="${sessionScope.curr_user.deptList}" var="dept">
                                        ${dept.deptName}&nbsp;&nbsp;
                                    </c:forEach>
                                </small>
                            </p>
                        </li>
                        <!-- Menu Footer-->
                        <li class="user-footer">
                            <div class="pull-left">
                                <a href="/manage/account/profile" class="btn btn-default btn-flat">个人设置</a>
                            </div>
                            <div class="pull-right">
                                <a href="/logout" class="btn btn-default btn-flat">安全退出</a>
                            </div>
                        </li>
                    </ul>
                </li>
            </ul>
        </div>
    </nav>
</header>

<!-- =============================================== -->

<!-- 左侧菜单栏 -->
<aside class="main-sidebar">
    <!-- sidebar: style can be found in sidebar.less -->
    <section class="sidebar">

        <!-- 搜索表单，不需要删除即可 -->
        <!--<form action="#" method="get" class="sidebar-form">
          <div class="input-group">
            <input type="text" name="q" class="form-control" placeholder="Search...">
                <span class="input-group-btn">
                  <button type="submit" name="search" id="search-btn" class="btn btn-flat"><i class="fa fa-search"></i>
                  </button>
                </span>
          </div>
        </form>-->
        <!-- /.search form -->
        <!-- 菜单 -->
        <ul class="sidebar-menu">
            <li class="header">系统功能</li>
            <!-- 客户管理 -->
            <li class="treeview ${param.active.startsWith('customer/') ? 'active' : ''}">
                <a href="#">
                    <i class="fa fa-address-book-o"></i> <span>客户管理</span>
                    <span class="pull-right-container">
              <i class="fa fa-angle-left pull-right"></i>
            </span>
                </a>
                <ul class="treeview-menu">
                    <li class="${param.active == 'customer/my' ? 'active' : ''}"><a href="/customer/my"><i class="fa fa-circle-o"></i> 我的客户</a></li>
                    <li class="${param.active == 'customer/public' ? 'active' : ''}"><a href="/customer/public"><i class="fa fa-circle-o"></i> 公海客户</a></li>
                </ul>
            </li>
            <!-- 工作记录 -->
            <li class="treeview ${param.active.startsWith('saleChance/') ? 'active' : ''}">
                <a href="#">
                    <i class="fa fa-bars"></i> <span>销售机会</span>
                    <span class="pull-right-container">
              <i class="fa fa-angle-left pull-right"></i>
            </span>
                </a>
                <ul class="treeview-menu">
                    <li class="${param.active == 'saleChance/my' ? 'active' : ''}"><a href="/saleChance/my"><i class="fa fa-circle-o"></i> 我的销售机会</a></li>
                    <li><a href="/saleChance/public"><i class="fa fa-circle-o"></i> 公共销售机会</a></li>
                </ul>
            </li>
            <!-- 待办事项 -->
            <li class="treeview">
                <a href="#">
                    <i class="fa fa-calendar ${param.active.startsWith('task/') ? 'active' : ''}"></i> <span>待办事项</span>
                    <span class="pull-right-container">
              <i class="fa fa-angle-left pull-right"></i>
            </span>
                </a>
                <ul class="treeview-menu">
                    <li class="${param.active == 'task' ? 'active' : ''}"><a href="/task"><i class="fa fa-circle-o"></i> 待办列表</a></li>
                    <li><a href=""><i class="fa fa-circle-o"></i> 逾期事项</a></li>
                </ul>
            </li>
            <!-- 统计报表 -->
            <li class="${param.active.startsWith('charts') ? 'active' : ''}  treeview">
                <a href="#">
                    <i class="fa fa-pie-chart"></i> <span>统计报表</span>
                    <span class="pull-right-container">
              <i class="fa fa-angle-left pull-right"></i>
            </span>
                </a>
                <ul class="treeview-menu">
                    <li class="${param.active == 'charts/static' ? 'active' : ''}"><a href="/charts/static"><i class="fa fa-circle-o"></i> 静态展示</a></li>
                    <li><a href="/charts/dynamic"><i class="fa fa-circle-o"></i> 动态展示</a></li>
                </ul>
            </li>


            <li class="${param.active == 'disk' ? 'active' : ''}"><a href="/disk"><i class="fa fa-share-alt"></i> <span>公司网盘</span></a></li>
            <li class="header">系统管理</li>
            <!-- 部门员工管理 -->
            <li class="${param.active == 'account' ? 'active' : ''}"><a href="/manage/account"><i class="fa fa-users"></i> <span>员工管理</span></a></li>
            <!--<li><a href="#"><i class="fa fa-circle-o text-yellow"></i> <span>Warning</span></a></li>
            <li><a href="#"><i class="fa fa-circle-o text-aqua"></i> <span>Information</span></a></li>-->
        </ul>
    </section>
    <!-- /.sidebar -->
</aside>

<!-- =============================================== -->