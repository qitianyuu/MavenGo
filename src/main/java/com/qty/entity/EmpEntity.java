package com.qty.entity;

import java.math.BigDecimal;
import java.sql.Date;

public class EmpEntity {
    private int empno;
    private String ename;
    private String job;
    private Date hiredate;
    private BigDecimal sal;
    private String dname;
    private String maxHireDate;
    private String minHireDate;

    public String getMaxHireDate() {
        return maxHireDate;
    }

    public void setMaxHireDate(String maxHireDate) {
        this.maxHireDate = maxHireDate;
    }

    public String getMinHireDate() {
        return minHireDate;
    }

    public void setMinHireDate(String minHireDate) {
        this.minHireDate = minHireDate;
    }

    public int getEmpno() {
        return empno;
    }

    public void setEmpno(int empno) {
        this.empno = empno;
    }

    public String getEname() {
        return ename;
    }

    public void setEname(String ename) {
        this.ename = ename;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public Date getHiredate() {
        return hiredate;
    }

    public void setHiredate(Date hiredate) {
        this.hiredate = hiredate;
    }

    public BigDecimal getSal() {
        return sal;
    }

    public void setSal(BigDecimal sal) {
        this.sal = sal;
    }

    public String getDname() {
        return dname;
    }

    public void setDname(String dname) {
        this.dname = dname;
    }
}
