package com.dzqc.cloud.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dzqc.cloud.dao.DepartmentinfoMapper;
import com.dzqc.cloud.entity.Departmentinfo;
import com.dzqc.cloud.service.DepartmentService;

@Service
public class DepartmentServiceImpl implements DepartmentService {
    
    @Autowired
    private DepartmentinfoMapper departmentinfoMapper;

    @Override
    public Departmentinfo findById(Integer id) {
        return departmentinfoMapper.selectByPrimaryKey(id);
    }
} 