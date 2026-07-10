package com.aspms.service;

import java.util.List;
import com.aspms.entity.Department;

/**
 * 养殖部门信息 业务逻辑接口
 */
public interface DepartmentService {

    /**
     * 添加养殖部门信息
     * @param department
     * @return
     */
    boolean doAddDepartmentInfo(Department department);

    /**
     * 修改养殖部门信息
     * @param department
     * @return
     */
    boolean doModifyDepartmentInfo(Department department);

    /**
     * 删除养殖部门信息
     * @param department
     * @return
     */
    boolean doDeleteDepartmentInfo(Department department);

    /**
     * 获取养殖部门信息
     * @param department
     * @return
     */
    Department doGetDepartmentInfo(Department department);

    /**
     * 获取养殖部门列表
     * @return
     */
    List<Department> doGetDepartmentInfoList();

    /**
     * 查询养殖部门列表
     * @param department
     * @return
     */
    List<Department> doQueryDepartmentInfoList(Department department);

}
