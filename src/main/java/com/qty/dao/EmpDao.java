package com.qty.dao;

import com.qty.entity.EmpEntity;
import java.util.List;
import com.qty.utils.PageUtils;

public interface EmpDao {
    public int saveEmp(EmpEntity entity);
    public int deleteEmp(int empno);
    public int updateEmp(EmpEntity entity);
    public List<EmpEntity> selectALL();
    public EmpEntity selectOne(int empno);
    public int selectEmpCount(EmpEntity entity);
    public List<EmpEntity> selectEmpPage(PageUtils pageUtils,EmpEntity entity);
    public List<String> selectJobs();
}
