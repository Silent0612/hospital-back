package com.dzqc.cloud.service;

import java.util.List;

import com.dzqc.cloud.entity.Medicalrecord;

public interface MedicalrecordService {
    
    // 分页查询所有病历记录
    List<Medicalrecord> selectAll();
    
    // 根据主键删除病历记录
    int deleteByPrimaryKey(Integer id);
    
    // 根据主键更新病历记录
    int updateByPrimaryKey(Medicalrecord record);
    
    // 根据主键查询病历记录
    Medicalrecord selectByPrimaryKey(Integer id);
    
    // 根据用户ID查询病历记录
    List<Medicalrecord> selectByUserId(Integer userId);
    
    // 分页查询病历记录
    List<Medicalrecord> selectByPage(Integer doctorid, Integer offset, Integer limit);
    
    // 查询总记录数
    Integer selectTotal(Integer doctorid);
    
    // 插入病历记录
    int insert(Medicalrecord record);
    
    // 更新病历记录
    int update(Medicalrecord record);
    
    // 删除病历记录
    int delete(Integer id);
    
    // 根据用户ID分页查询病历记录
    List<Medicalrecord> selectByUserId(Integer userId, Integer offset, Integer limit);
}
