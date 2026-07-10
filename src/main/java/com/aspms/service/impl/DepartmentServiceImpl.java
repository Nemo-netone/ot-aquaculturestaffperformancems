package com.aspms.service.impl;

import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.aspms.service.DepartmentService;
import com.aspms.mapper.DepartmentMapper;
import com.aspms.entity.Department;

/**
 * 养殖部门信息 业务逻辑实现类
 */
@Service
@Transactional
public class DepartmentServiceImpl implements DepartmentService {

    @Resource
    public DepartmentMapper departmentMapper;

    /**
     * 添加养殖部门信息
     * @param department
     * @return
     */
    @Override
    public boolean doAddDepartmentInfo(Department department) {
        //检查养殖部门信息是否存在
        if(departmentMapper.doGetDepartmentInfo(department)==null) {
            //开始添加养殖部门信息
            return departmentMapper.doAddDepartmentInfo(department)!=0;
        }
        return false;
    }

    /**
     * 修改养殖部门信息
     * @param department
     * @return
     */
    @Override
    public boolean doModifyDepartmentInfo(Department department) {
        return departmentMapper.doModifyDepartmentInfo(department)!=0;
    }

    /**
     * 删除养殖部门信息
     * @param department
     * @return
     */
    @Override
    public boolean doDeleteDepartmentInfo(Department department) {
        return departmentMapper.doDeleteDepartmentInfo(department)!=0;
    }

    /**
     * 获取养殖部门信息
     * @param department
     * @return
     */
    @Override
    public Department doGetDepartmentInfo(Department department) {
        return departmentMapper.doGetDepartmentInfo(department);
    }

    /**
     * 获取养殖部门列表
     * @return
     */
    @Override
    public List<Department> doGetDepartmentInfoList() {
        return departmentMapper.doGetDepartmentInfoList();
    }

    /**
     * 查询养殖部门列表
     * @param department
     * @return
     */
    @Override
    public List<Department> doQueryDepartmentInfoList(Department department) {
        return departmentMapper.doQueryDepartmentInfoList(department);
    }

}
