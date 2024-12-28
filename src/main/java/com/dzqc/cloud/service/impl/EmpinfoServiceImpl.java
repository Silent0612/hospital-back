package com.dzqc.cloud.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dzqc.cloud.dao.EmpinfoMapper;
import com.dzqc.cloud.entity.Empinfo;
import com.dzqc.cloud.service.EmpinfoService;

@Service
public class EmpinfoServiceImpl implements EmpinfoService {
    @Autowired
    private EmpinfoMapper empinfoMapper;

    @Override
    public List<Empinfo> findByDid(Integer did) {
        return empinfoMapper.selectByDid(did);
    }

    @Override
    public Empinfo findById(Integer id) {
        return empinfoMapper.selectByPrimaryKey(id);
    }

    @Override
    public void insertEmp(Empinfo empinfo) {
        empinfoMapper.insert(empinfo);
    }

    @Override
    public Empinfo login(String phone, String password) {
        return empinfoMapper.selectByPhoneAndPassword(phone, password);
    }
}
