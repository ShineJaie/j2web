<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!-- Sidebar -->
<div id="jsidebar-wrapper">
    <nav class="navbar navbar-inverse navbar-fixed-top" id="sidebar-wrapper" role="navigation">
        <ul class="nav sidebar-nav">
            <li class="sidebar-brand">
                <a class="jside-menu" href="#">
                    <i class="fa fa-fire"></i>&nbsp; 技术研究
                </a>
            </li>
            <sec:authorize access="hasRole('ROLE_ADMIN')">
                <li>
                    <a class="jside-menu" href="#">
                        <i class="fa fa-users"></i>&nbsp;
                        用户列表</a>
                </li>
            </sec:authorize>
            <li>
                <a page_select="page_home" class="jside-menu" href="#">
                    <i class="fa fa-newspaper-o"></i>&nbsp; 新增资讯</a>
            </li>
            <li>
                <a class="jside-menu" href="#">文章</a>
            </li>
            <li>
                <a class="jside-menu" href="#">讨论</a>
            </li>
            <li class="dropdown jsidebar-dropdown">
                <a href="#" class="dropdown-toggle">Demo <span class="caret"></span></a>
                <ul class="dropdown-menu" role="menu">
                    <li class="dropdown-header">技术研究</li>
                    <li><a class="jside-menu">Jstree</a></li>
                    <li><a class="jside-menu" href="#">JqueryFileUpload</a></li>
                    <li><a class="jside-menu" href="#">等待</a></li>
                    <li><a class="jside-menu" href="#">等待</a></li>
                    <li><a class="jside-menu" href="#">等待</a></li>
                    <li class="dropdown jsidebar-dropdown">
                        <a href="#" class="dropdown-toggle">三级菜单 <span class="caret"></span></a>
                        <ul class="dropdown-menu" role="menu">
                            <li class="dropdown-header">三级菜单</li>
                            <li><a class="jside-menu" href="#">你好</a></li>
                            <li><a class="jside-menu" href="#">我是</a></li>
                            <li><a class="jside-menu" href="#">吴</a></li>
                            <li><a class="jside-menu" href="#">仙</a></li>
                            <li><a class="jside-menu" href="#">杰</a></li>
                        </ul>
                    </li>
                </ul>
            </li>

            <li class="dropdown jsidebar-dropdown">
                <a href="#" class="dropdown-toggle">测试 <span class="caret"></span></a>
                <ul class="dropdown-menu" role="menu">
                    <li class="dropdown-header">测试研究</li>
                    <li><a class="jside-menu" href="#">稍等</a></li>
                    <li><a class="jside-menu" href="#">稍等</a></li>
                    <li><a class="jside-menu" href="#">等待</a></li>
                    <li><a class="jside-menu" href="#">等待</a></li>
                    <li><a class="jside-menu" href="#">等待</a></li>
                </ul>
            </li>

            <li>
                <a class="jside-menu" href="#">联系</a>
            </li>
            <li>
                <a class="jside-menu" href="#">关注</a>
            </li>
        </ul>
    </nav>

    <button type="button" class="hamburger is-closed">
        <span class="hamb-top"></span>
        <span class="hamb-middle"></span>
        <span class="hamb-bottom"></span>
    </button>
</div>
<!-- /#sidebar-wrapper -->

<!-- Topbar -->
<div id="jtopbar-wrapper">
    <nav class="navbar navbar-default navbar-inverse" id="topbar-wrapper" role="navigation">
        <div class="container-fluid">
            <!-- Brand and toggle get grouped for better mobile display -->
            <div class="navbar-header">
                <button type="button" class="navbar-toggle collapsed" data-toggle="collapse"
                        data-target="#bs-topbar-navbar-collapse">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a data-pjax class="navbar-brand jtop-menu"
                   href="<c:url value='/home'/>">
                    <i class="fa fa-rocket"></i>&nbsp; Shine Jaie</a>
            </div>

            <!-- Collect the nav links, forms, and other content for toggling -->
            <div class="collapse navbar-collapse" id="bs-topbar-navbar-collapse">
                <ul class="nav navbar-nav">
                    <li>
                        <a data-pjax page_select="page_home" class="jtop-menu"
                           href="<c:url value='/home'/>">
                            <i class="fa fa-home"></i>&nbsp; 首页</a>
                    </li>
                    <li><a class="jtop-menu" href="#">研究</a></li>
                    <li class="dropdown jtopbar-dropdown">
                        <a href="#" class="dropdown-toggle">你懂得
                            <span class="caret"></span></a>
                        <ul class="dropdown-menu" role="menu">
                            <li><a class="jtop-menu" href="#">Java</a></li>
                            <li><a class="jtop-menu" href="#">JavaScript</a></li>
                            <li><a class="jtop-menu" href="#">Bootstrap</a></li>
                            <li class="divider"></li>
                            <li><a class="jtop-menu" href="#">MySQL</a></li>
                            <li class="divider"></li>
                            <li><a class="jtop-menu" href="#">Redis</a></li>
                        </ul>
                    </li>
                </ul>

                <div class="navbar-form navbar-left">
                    <div class="form-group">
                        <div class="input-group">
                            <input type="text" class="form-control" placeholder="全局搜索">

                            <div class="input-group-btn">
                                <button class="btn btn-block"><i class="fa fa-search"></i></button>
                            </div>
                        </div>
                    </div>
                </div>


                <div class="nav navbar-nav navbar-right">

                    <sec:authorize access="hasAnyRole('ROLE_USER', 'ROLE_ADMIN')">

                        <c:url value="/j_spring_security_logout" var="logoutUrl"/>

                        <form class="navbar-right" action="${logoutUrl}" method="post">
                            <p class="navbar-text"><i class="fa fa-user"></i>&nbsp; 用户名:
                                <a class="navbar-link pa-userinfo" href="#"
                                   style="padding-left: 5px;">
                                        ${pageContext.request.userPrincipal.name}</a>
                            </p>

                            <p class="navbar-text" style="color: #d2d2d2; padding-left: 5px;">|</p>

                            <a class="btn btn-link" type="submit" style="padding-top: 15px;"
                               href="${logoutUrl}"><i class="fa fa-leaf"></i>&nbsp; 安全退出</a>

                            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                        </form>

                        <p class="navbar-text navbar-right" style="color: #d2d2d2; padding-left: 5px;">|</p>

                        <a data-pjax page_select="page_register" class="btn btn-link" style="padding-top: 15px"
                           href="<c:url value='/register'/>">
                            <i class="fa fa-registered"></i>&nbsp; 用户注册
                        </a>
                    </sec:authorize>

                </div>
                <!-- /.navbar-collapse -->
            </div>
            <!-- /.container-fluid -->
        </div>
    </nav>
</div>
<!-- /#topbar-wrapper -->