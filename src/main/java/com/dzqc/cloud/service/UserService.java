package com.dzqc.cloud.service;

import java.util.List;

import com.dzqc.cloud.entity.Userinfo;

public interface UserService {
    /**
     * 插入用户信息
     * @param userinfo
     * @return 受影响的行数
     */
    int insertUser(Userinfo userinfo);

    /**
     * 根据手机号查询用户信息
     * @param phone
     * @return
     */
    Userinfo selectByPhone(String phone);

    /**
     * 查询所有用户信息
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
