package com.dzqc.cloud.service;

import com.dzqc.cloud.entity.Departmentinfo;

public interface DepartmentService {
    
    /**
     * 根据ID查找科室信息
     */
    Departmentinfo findById(Integer id);
    
} 