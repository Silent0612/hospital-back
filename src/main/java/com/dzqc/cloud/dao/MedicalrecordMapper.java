package com.dzqc.cloud.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.dzqc.cloud.entity.Medicalrecord;

public interface MedicalrecordMapper {
    
    // 查询所有病历记录
    List<Medicalrecord> selectAll();
    
    // 根据主键删除病历记录
    int deleteByPrimaryKey(@Param("id") Integer id);
    
    // 根据主键更新病历记录
    int updateByPrimaryKey(Medicalrecord record);
    
    // 根据主键查询病历记录
    Medicalrecord selectByPrimaryKey(@Param("id") Integer id);
    
    // 根据用户ID查询病历记录
    List<Medicalrecord> selectByUserId(@Param("userId") Integer userId);
    
    // 分页查询病历记录
    List<Medicalrecord> selectByPage(@Param("doctorid") Integer doctorid, 
                                    @Param("offset") Integer offset, 
                                    @Param("limit") Integer limit);
    
    // 查询总记录数
    Integer selectTotal(@Param("doctorid") Integer doctorid);
    
    // 插入病历记录
    int insert(Medicalrecord record);
    
    // 更新病历记录
    int update(Medicalrecord record);
    
    // 删除病历记录
    int delete(@Param("id") Integer id);
    
    // 根据用户ID分页查询病历记录
    List<Medicalrecord> selectByUserIdPage(@Param("userId") Integer userId,
                                          @Param("offset") Integer offset,
                                          @Param("limit") Integer limit);
}