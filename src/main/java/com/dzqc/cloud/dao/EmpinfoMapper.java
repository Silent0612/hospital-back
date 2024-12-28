package com.dzqc.cloud.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.dzqc.cloud.entity.Empinfo;

public interface EmpinfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Empinfo record);

    int insertSelective(Empinfo record);

    Empinfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Empinfo record);

    int updateByPrimaryKey(Empinfo record);
    /**
     * 根据部门ID查询医生列表
     */
    List<Empinfo> selectByDid(Integer departmentid);

    Empinfo selectByPhoneAndPassword(@Param("phone") String phone, @Param("password") String password);
}