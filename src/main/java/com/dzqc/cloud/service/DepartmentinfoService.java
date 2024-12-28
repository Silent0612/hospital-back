package com.dzqc.cloud.service;

import java.util.List;

import com.dzqc.cloud.entity.Departmentinfo;

public interface DepartmentinfoService {
    
    /**
     * 获取所有科室列表
     */
    List<Departmentinfo> selectAll();
    
    /**
     * 根据ID获取科室信息
     */
    Departmentinfo selectById(Integer id);
    
    Departmentinfo findById(Integer id);
}
