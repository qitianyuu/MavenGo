package com.qty.daoImpl;

import com.qty.dao.EmpDao;
import com.qty.database.DBConnection;
import com.qty.entity.EmpEntity;
import com.qty.utils.PageUtils;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmpDaoImpl implements EmpDao {
    private Connection connection;
    private PreparedStatement pst;
    private ResultSet res;

    @Override
    public int saveEmp(EmpEntity entity) {
        DBConnection db = new DBConnection();
        this.connection = db.getConnection();

        String sql = "insert into emp (empno,ename,job,sal,hiredate,dname)" + "values(empno,?,?,?,?,?)";
        try {
            pst = this.connection.prepareStatement(sql);
            pst.setString(1, entity.getEname());
            pst.setString(2, entity.getJob());
            pst.setBigDecimal(3, entity.getSal());
            pst.setDate(4, entity.getHiredate());
            pst.setString(5, entity.getDname());
            int i = pst.executeUpdate();
            return i;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (connection != null && !connection.isClosed()) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return 0;
    }

    @Override
    public int deleteEmp(int empno) {
        DBConnection db = new DBConnection();
        this.connection = db.getConnection();
        String sql = "delete from emp where empno = ?";
        try {
            pst = this.connection.prepareStatement(sql);
            pst.setInt(1, empno);
            int i = pst.executeUpdate();
            return i;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (connection != null && !connection.isClosed()) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return 0;
    }

    @Override
    public int updateEmp(EmpEntity entity) {
        DBConnection db = new DBConnection();
        this.connection = db.getConnection();
        String sql = "update emp set ename=?,job=?,dname=?sal=?hiredate=? where empno=?";
        try {
            pst = this.connection.prepareStatement(sql);
            pst.setString(1, entity.getEname());
            pst.setString(2, entity.getJob());
            pst.setString(3, entity.getDname());
            pst.setBigDecimal(4, entity.getSal());
            pst.setDate(5, entity.getHiredate());
            pst.setInt(6, entity.getEmpno());
            int i = pst.executeUpdate();
            return i;

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (connection != null && !connection.isClosed()) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return 0;
    }

    @Override
    public List<EmpEntity> selectALL() {
        List<EmpEntity> list = new ArrayList<EmpEntity>();
        try {
            DBConnection db = new DBConnection();
            this.connection = db.getConnection();
            String sql = "select empno,ename,job,sal,hiredate,dname from emp";
            pst = this.connection.prepareStatement(sql);
            res = pst.executeQuery();
            while (res.next()) {
                EmpEntity e = new EmpEntity();
                e.setEmpno(res.getInt(1));
                e.setEname(res.getString(2));
                e.setJob(res.getString(3));
                e.setSal(res.getBigDecimal(4));
                e.setHiredate(res.getDate(5));
                e.setDname(res.getString(6));
                list.add(e);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (connection != null && !connection.isClosed()) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return list;
    }

    @Override
    public EmpEntity selectOne(int empno) {
        DBConnection db = new DBConnection();
        this.connection = db.getConnection();
        EmpEntity e = null;
        String sql = "select empno,ename,job,sal,hiredate,dname from emp where empno = ?";
        try {
            pst = this.connection.prepareStatement(sql);
            pst.setInt(1, empno);
            res = pst.executeQuery();
            while (res.next()) {
                e = new EmpEntity();
                e.setEmpno(res.getInt(1));
                e.setEname(res.getString(2));
                e.setJob(res.getString(3));
                e.setSal(res.getBigDecimal(4));
                e.setHiredate(res.getDate(5));
                e.setDname(res.getString(6));
            }
        } catch (SQLException s) {
            s.printStackTrace();
        } finally {
            try {
                if (connection != null && !connection.isClosed()) {
                    connection.close();
                }
            } catch (SQLException s) {
                s.printStackTrace();
            }
        }
        return e;
    }

    @Override
    public int selectEmpCount(EmpEntity entity) {
        //查询记录数，包含带条件查询
        int total = 0;
        try {
            DBConnection db = new DBConnection();
            this.connection = db.getConnection();
            StringBuilder sql = new StringBuilder("select count(empno) from emp where 1=1 ");
            if(entity != null){
                if(entity.getEname()!=null&&!entity.getEname().trim().equals("")){
                    sql.append("and ename like '%"+entity.getEname()+"%' ");
                }
                if(entity.getJob()!=null&&!entity.getJob().trim().equals("")){
                    sql.append("and job = '"+entity.getJob()+"' ");
                }
                if(entity.getMaxHireDate()!=null&&!entity.getMaxHireDate().equals("")){
                    sql.append("and hiredate <= '"+entity.getMaxHireDate()+"' ");
                }
                if(entity.getMinHireDate()!=null&&!entity.getMinHireDate().equals("")){
                    sql.append("and hiredate >= '"+entity.getMinHireDate()+"' ");
                }
            }
            System.out.println(sql.toString());
            pst = this.connection.prepareStatement(sql.toString());
            res = pst.executeQuery();

            while(res.next()){
                total = res.getInt(1);//???????????????
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                if(connection!=null&&!connection.isClosed()){
                    connection.close();
                }
            } catch (SQLException s) {
                s.printStackTrace();
            }
        }
        return total;
    }

    @Override
    public List<EmpEntity> selectEmpPage(PageUtils pageUtils, EmpEntity entity) {
        List<EmpEntity> list = new ArrayList<EmpEntity>();
        try {
            DBConnection db = new DBConnection();
            this.connection = db.getConnection();
            StringBuilder sql = new StringBuilder("select empno,ename,job,sal,hiredate,dname  from emp   where 1=1 ");
            if(entity != null){
                if(entity.getEname()!=null&&!entity.getEname().trim().equals("")){
                    sql.append(" and ename like '%"+entity.getEname()+"%' ");
                }
                if(entity.getJob()!=null&&!entity.getJob().trim().equals("")){
                    sql.append(" and job = '"+entity.getJob()+"' ");
                }
                if(entity.getMaxHireDate()!=null&&!entity.getMaxHireDate().equals("")){
                    sql.append(" and hiredate <= '"+entity.getMaxHireDate()+"' ");
                }
                if(entity.getMinHireDate()!=null&&!entity.getMinHireDate().equals("")){
                    sql.append(" and hiredate >= '"+entity.getMinHireDate()+"' ");
                }
            }
            sql.append(" limit ?,? ");
            pst= this.connection.prepareStatement(sql.toString());
            System.out.println(sql.toString());
            pst.setInt(1,pageUtils.getOffset());
            pst.setInt(2,pageUtils.getPageSize());
            res = pst.executeQuery();
            while (res.next()){
                EmpEntity e = new EmpEntity();
                e.setEmpno(res.getInt(1));//1表示第一列，即select后面的列下标，从1开始
                e.setEname(res.getString(2));
                e.setJob(res.getString(3));
                e.setSal(res.getBigDecimal(4)) ;
                e.setHiredate(res.getDate(5));
                e.setDname(res.getString(6));
                list.add(e);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                if(connection!=null&&!connection.isClosed()){
                    connection.close();
                }
            }catch (SQLException e){
                e.printStackTrace();
            }
        }
        return list;
    }

    @Override
    public List<String> selectJobs() {
        List<String> list = new ArrayList<String>();
        try {
            DBConnection db = new DBConnection();
            this.connection = db.getConnection();
            String sql = "select DISTINCT job from emp  ";
            pst = this.connection.prepareStatement(sql);
            res = pst.executeQuery();
            while (res.next()){
                list.add(res.getString(1));
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally{
            try {
                if(connection!=null&&!connection.isClosed()){
                    connection.close();
                }
            } catch (SQLException s) {
                s.printStackTrace();
            }
        }
        return list;
    }
}

