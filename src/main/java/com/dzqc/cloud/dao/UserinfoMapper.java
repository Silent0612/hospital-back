package com.dzqc.cloud.dao;

import java.util.List;

import com.dzqc.cloud.entity.Userinfo;

public interface UserinfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Userinfo record);

    int insertSelective(Userinfo record);

    Userinfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Userinfo record);

    int updateByPrimaryKey(Userinfo record);

    /**
     * 根据手机号查询用户
     * @param phone
     * @return
     */
    Userinfo selectByPhone(String phone);

    /**
     * 查询所有用户
     * @return
     */
    List<Userinfo> selectAll();

    /**
     * 根据科室ID查询医生列表
     * @param departmentId 科室ID
     * @return 医生列表
     */
    List<Userinfo> selectByDepartment(Integer departmentId);
}