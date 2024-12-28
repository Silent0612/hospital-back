package com.dzqc.cloud.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dzqc.cloud.dao.DepartmentinfoMapper;
import com.dzqc.cloud.entity.Departmentinfo;
import com.dzqc.cloud.service.DepartmentinfoService;

@Service
public class DepartmentinfoServiceImpl implements DepartmentinfoService {

    @Autowired
    private DepartmentinfoMapper departmentinfoMapper;

    @Override
    public List<Departmentinfo> selectAll() {
        return departmentinfoMapper.selectAll();
    }

    @Override
    public Departmentinfo selectById(Integer id) {
        return departmentinfoMapper.selectByPrimaryKey(id);
    }

    @Override
    public Departmentinfo findById(Integer id) {
        return departmentinfoMapper.selectByPrimaryKey(id);
    }
}
