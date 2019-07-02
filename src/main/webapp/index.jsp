<%--
  Created by IntelliJ IDEA.
  User: qi
  Date: 2019/6/28
  Time: 23:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>EmployeeInformation</title>
    <title>员工信息操作</title>
    <!-- Bootstrap -->
    <link href="bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <!-- jquery是Js框架 -->
    <script src="bootstrap/js/jquery-1.11.1.js"></script>
    <!-- 加载 Bootstrap 的所有 JavaScript 插件。你也可以根据需要只加载单个插件。 -->
    <script src="bootstrap/js/bootstrap.min.js"></script>
    <style type="text/css">
        .main{border:solid 1px #666;width:100%;height:800px;}
        #content_frame{border:solid 1px red;width:100%;height:800px;}
    </style>
    <script type="text/javascript">
        function empInfo() {
            $("#content_frame").attr("src","empControl");
        }

        function home() {
            $("#content_frame").attr("src","home.jsp");
        }

    </script>
</head>
<body>
<!--外层容器-->
<div class="main">
    <%--<ul class="nav nav-tabs">
        <li role="presentation" class="active" ><a href="javascript:void(0)" onclick="home()" >Home</a></li>
        <li role="presentation"><a href="javascript:void(0)" onclick="empInfo()">员工信息管理</a></li>
        <li role="presentation"><a href="#">Messages</a></li>
    </ul>--%>
    <nav class="navbar navbar-inverse navbar-fixed-top">
        <div class="container">
            <div class="navbar-header">
            </div>
            <div id="navbar" class="navbar-collapse collapse">
                <ul class="nav navbar-nav">
                    <li ><a href="javascript:void(0)" onclick="home()">HOME</a> </li>
                    <li ><a href="javascript:void(0)" onclick="empInfo()">员工信息</a> </li>
                    <li><a href="#">部门信息管理</a></li>
                </ul>
            </div><!--/.nav-collapse -->
        </div>
    </nav>
    <!--嵌入当前页面中的一个window窗体-->
    <iframe id="content_frame" src="empControl?action=select" style="padding-top:40px">
</iframe>
</div>
    </div>
</body>
</html>
