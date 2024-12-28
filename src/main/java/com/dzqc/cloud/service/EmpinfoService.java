package com.dzqc.cloud.service;

import java.util.List;

import com.dzqc.cloud.entity.Empinfo;

public interface EmpinfoService {
    /**
     * 根据科室id 查询对应的医生信息
     * @param did
     * @return
     */
    public List<Empinfo> findByDid(Integer did);

    /**
     * 根据ID查询医生信息
     * @param id
     * @return
     */
    public Empinfo findById(Integer id);

    /**
     * 新增科室医生
     */
    void insertEmp(Empinfo empinfo);

    Empinfo login(String phone, String password);
}
