package com.aspms.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Mapper;
import com.aspms.entity.Department;

/**
 * 养殖部门信息 Mapper接口
 */
@Mapper
public interface DepartmentMapper {

    /**
     * 添加养殖部门信息
     * @param department
     * @return
     */
    int doAddDepartmentInfo(Department department);

    /**
     * 修改养殖部门信息
     * @param department
     * @return
     */
    int doModifyDepartmentInfo(Department department);

    /**
     * 删除养殖部门信息
     * @param department
     * @return
     */
    int doDeleteDepartmentInfo(Department department);

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
