package com.qty.control;

import com.qty.dao.EmpDao;
import com.qty.daoImpl.EmpDaoImpl;
import com.qty.entity.EmpEntity;
import com.qty.utils.PageUtils;

import java.io.IOException;
import java.util.List;
import javax.servlet.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class EmpControl extends HttpServlet {
    //处理Post请求方式
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request,response);
    }
    //处理get请求方式
    protected void doGet(HttpServletRequest req, HttpServletResponse response) throws ServletException, IOException {
        String action=req.getParameter("action");
        if(action.equals("select")){
            String pageNow=req.getParameter("pageNow");
            String ename=req.getParameter("ename");
            String job=req.getParameter("job");
            String minHiredate=req.getParameter("minHiredate");
            String maxHiredate=req.getParameter("maxHiredate");
            int startIndex=0;
            int endIndex=0;
            if(pageNow==null||pageNow.trim().equals("")){
                pageNow="1";
            }
            //查询
            EmpDao dao=new EmpDaoImpl();
            EmpEntity entity=new EmpEntity();
            if(ename!=null&&!ename.trim().equals("")){
                entity.setEname(new String(ename.getBytes("iso-8859-1"),"utf-8"));
                System.out.println(entity.getEname()+"=======");
            }
            if(job!=null&&!job.trim().equals("")){
                entity.setJob(job);
            }
            if(minHiredate!=null&&!minHiredate.trim().equals("")){
                entity.setMinHireDate(minHiredate);
            }
            if(maxHiredate!=null&&!maxHiredate.trim().equals("")){
                entity.setMaxHireDate(maxHiredate);
            }
            PageUtils utils=new PageUtils();
            utils.setPageNow(Integer.parseInt(pageNow));     //封装当前页码，计算偏移量
            int total= dao.selectEmpCount(entity);
            utils.setTotalItem(total);//封装总记录数，计算出总页数
            List<EmpEntity> list=dao.selectEmpPage(utils,entity);
            for(int i=0;i<list.size();i++){
                EmpEntity e=list.get(i);
                System.out.println(e.getDname()+":"+e.getEmpno()+":"+e.getEname());
            }
            System.out.println("总记录数："+utils.getTotalItem()+">>总页数："+utils.getTotalPage());
            ///计算页码其实位置 （页面显示的）
            ///页码最大页码结束位置（页面显示的)
            if(Integer.parseInt(pageNow)<=11&&Integer.parseInt(pageNow)>=1){
                startIndex=1;
                if(utils.getTotalPage()<11){
                    endIndex=utils.getTotalPage();
                }else{
                    endIndex=11;
                }
            }
            if(Integer.parseInt(pageNow)>11&&Integer.parseInt(pageNow)<=utils.getTotalPage()){
                //当前页码后面不够取5个数，则参照最大页码前退10个数作为起始页码
                if(Integer.parseInt(pageNow)+5>=utils.getTotalPage()){   //不够获取某个页码，后面五个页码数字  s
                    startIndex=utils.getTotalPage()-10;//如果某页码后面不够取，则从最大页码前推 11个数
                    endIndex=utils.getTotalPage();
                }
                else{//如果当前页码后够取5个数，则前后各推五个数作为起始截止位置
                    startIndex=utils.getPageNow()-5;
                    endIndex=utils.getPageNow()+5;

                }
            }
            System.out.println(startIndex+":"+endIndex)  ;
            //查询工作种类
            List<String> jobRows=dao.selectJobs();

            //响应客户端
            //(1)客户端跳转-serlvet跳转servlet
            //(2)服务器端跳转跳转页面 页面可以接受request中的数据

            //将页码、总记录数，总页数，传到前台页码
            req.setAttribute("rows",list);
            req.setAttribute("pageNow",utils.getPageNow());
            req.setAttribute("total",utils.getTotalItem());
            req.setAttribute("totalPage",utils.getTotalPage());
            req.setAttribute("startIndex",startIndex);
            req.setAttribute("endIndex",endIndex);
            req.setAttribute("jobRows",jobRows);
            req.setAttribute("ename",ename);
            req.setAttribute("job",job);
            req.setAttribute("minHiredate",minHiredate);
            req.setAttribute("maxHiredate",maxHiredate);
            req.getRequestDispatcher("/empManager.jsp").forward(req,response); //服务器端跳转

        }
        else if(action.equals("delete")){
            String empno=req.getParameter("empno");
            EmpDao dao=new EmpDaoImpl();
            int i= dao.deleteEmp(Integer.parseInt(empno));
            if(i==1){
                //servlet跳转serlet 客户端跳转
                response.sendRedirect("empControl?action=select&pageNow=1");

            }else{
                req.setAttribute("errorInfo","delete Error");
                req.getRequestDispatcher("/error.jsp").forward(req,response); //服务器端跳转
            }


        }
        else if(action.equals("loadAddEmppage")){
            EmpDao dao=new EmpDaoImpl();
            //查询工作种类
            List<String> jobRows=dao.selectJobs();
            req.setAttribute("jobRows",jobRows);
            //跳转新增页面
            req.getRequestDispatcher("/addEmp.jsp").forward(req,response);
        }

    }
}

