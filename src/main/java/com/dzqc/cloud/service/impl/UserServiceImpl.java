package com.dzqc.cloud.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dzqc.cloud.dao.UserinfoMapper;
import com.dzqc.cloud.entity.Userinfo;
import com.dzqc.cloud.service.UserService;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserinfoMapper userinfoMapper;

    @Override
    public int insertUser(Userinfo userinfo) {
        return userinfoMapper.insertSelective(userinfo);
    }

    @Override
    public Userinfo selectByPhone(String phone) {
        return userinfoMapper.selectByPhone(phone);
    }

    @Override
    public List<Userinfo> selectAll() {
        return userinfoMapper.selectAll();
    }

    @Override
    public List<Userinfo> selectByDepartment(Integer departmentId) {
        return userinfoMapper.selectByDepartment(departmentId);
    }
}
