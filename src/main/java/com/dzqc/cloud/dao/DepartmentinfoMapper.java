package com.dzqc.cloud.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.dzqc.cloud.entity.Departmentinfo;

@Mapper
public interface DepartmentinfoMapper {
    
    /**
     * 获取所有科室列表
     */
    List<Departmentinfo> selectAll();
    
    /**
     * 根据主键获取科室信息
     */
    Departmentinfo selectByPrimaryKey(Integer id);
}