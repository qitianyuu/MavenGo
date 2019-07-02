<%--
  Created by IntelliJ IDEA.
  User: qi
  Date: 2019/6/28
  Time: 23:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>员工信息管理</title>

    <!-- Bootstrap -->
    <link href="bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <link href="bootstrap/css/bashboard.css" rel="stylesheet">
    <!-- jquery是Js框架 -->
    <script src="bootstrap/js/jquery-1.11.1.js"></script>
    <!-- 加载 Bootstrap 的所有 JavaScript 插件。你也可以根据需要只加载单个插件。 -->
    <script src="bootstrap/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="js/My97DatePicker/WdatePicker.js"> </script>
    <script type="text/javascript">
        function loadFirstPage(){

            $("#searchEmpForm").attr("action","empControl?action=select&pageNow=1").submit();//按照id找Form，修改action属性，提交表单
        }
        function lastPage(pageTotal){
            $("#searchEmpForm").attr("action","empControl?action=select&pageNow="+pageTotal).submit();
        }

        //光标放到导航上触发事件
        function toggleNav(obj){
            var ulNav=obj.parentNode.children[1];
            if(ulNav){
                if(ulNav.style.display=="none"){
                    ulNav.style.display="block";
                }else{
                    ulNav.style.display="none";
                }
            }
        }

        function pageOver(obj){
            obj.parentNode.className="active";
        }

        function pageOut(obj){
            obj.parentNode.className="disable";
        }

        function showPage(obj){
            //修改选项卡标题
            document.getElementById("tabTitle").innerHTML=obj.innerHTML
            //修改iframe的url
            var url= obj.getAttribute("url");
            document.getElementById("mainFrame").src=url;
        }
        //点击某一个页码
        function loadPage(pageNow){
            $("#searchEmpForm").attr("action","empControl?action=select&pageNow="+pageNow).submit();
        }
        //点击查询
        function searchEmp(){
            $("#searchEmpForm").attr("action","empControl?action=select&pageNow=1&"+new Date()).submit();
        }

    </script>
</head>
<body>
<div >
    <form action="" method="post" id="searchEmpForm" class="form-inline">
        <div class="form-group">
            <label for="ename">姓名</label>
            <input type="text" name="ename"  value="${ename}" class="form-control" id="ename" placeholder="Jane Doe">
        </div>
        <div class="form-group">
            <label for="job">工作种类</label>
            <select name="job" id="job" class="form-control">
                <option value="">--请选择--</option>
                <c:forEach  items="${jobRows}" var="j">
                    <c:if test="${job==j}">
                        <option value="${j}" selected>${j}</option>
                    </c:if>
                    <c:if test="${job!=j}">
                        <option value="${j}" >${j}</option>
                    </c:if>

                </c:forEach>
            </select>
        </div>
        <div class="form-group">
            <label for="job">工作种类</label>
            <input type="text" readonly="" value="${minHiredate}" name="minHiredate"  class="form-control"
                   id="minHiredate"  onFocus="WdatePicker()">
            <input type="text" readonly="" value="${maxHiredate}" name="maxHiredate"  class="form-control"
                   id="maxHiredate"  onFocus="WdatePicker()">

        </div>
        <button onclick="searchEmp()" class="btn btn-primary">查询</button>
        <a href="empControl?action=loadAddEmppage"   class="btn btn-primary">新增</a>
    </form>
    <table class="table table-striped table-hover" >

        <tbody>
        <tr>
            <th>序号</th>
            <th>员工编号</th>
            <th>姓名</th>
            <th>工作类型</th>
            <th>工资</th>
            <th>入职日期</th>
            <th>部门名称</th>
            <th>操作</th>
        </tr>
        <c:forEach items="${rows}" var="e"  varStatus="r">
            <tr >
                <td >${r.count}</td>  <!--总共遍历到几条数据，count函数-->
                <td >${e.empno}</td>
                <td >${e.ename}</td>
                <td >${e.job}</td>
                <td >${e.sal}</td>
                <td >${e.hiredate}</td>
                <td >${e.dname}</td>
                <td>
                    <a href="empControl?action=delete&empno=${e.empno}" class="btn btn-primary">删除</a>
                    <%--empControl?action=delete&empno=${e.empno}--%>
                </td>
            </tr>
        </c:forEach>


        </tbody>

    </table>
    <nav aria-label="...">
        <ul class="pagination">
            <li ><a href="javascript:void(0)" onclick="loadFirstPage()" aria-label="Previous"><span aria-hidden="true" >首页</span></a></li>
            <%--${startIndex}:${endIndex}--%>
           <c:forEach   begin="${startIndex}" end="${endIndex}" step="1" var="rowNum">
                <c:if test="${pageNow==rowNum}">
                    <li  class="active"><a href="javascript:void(0)" onclick="loadPage('${rowNum}')" >${rowNum}<span class="sr-only">(current)</span></a></li>
                </c:if>
                <c:if test="${pageNow!=rowNum}">
                    <li  ><a href="javascript:void(0)" onclick="loadPage('${rowNum}')" >${rowNum}<span class="sr-only">(current)</span></a></li>
                </c:if>
            </c:forEach>
            <li ><a href="javascript:void(0)" onclick="lastPage('${totalPage}')" aria-label="Previous"><span aria-hidden="true">尾页</span></a></li>
            <li >  <span style="display:block;font-size:16px; padding-top:2px">第${pageNow}页</span>   </li>
            <li >  <span style="display:block;font-size:16px; padding-top:2px">总页数：${totalPage}  </span> </li>
            <li >  <span style="display:block;font-size:16px; padding-top:2px">共${total}条</span>   </li>
        </ul>
    </nav>
</div>



</body>
</html>
