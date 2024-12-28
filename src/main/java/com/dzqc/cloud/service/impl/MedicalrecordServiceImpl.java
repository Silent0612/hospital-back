package com.dzqc.cloud.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dzqc.cloud.dao.MedicalrecordMapper;
import com.dzqc.cloud.entity.Medicalrecord;
import com.dzqc.cloud.service.MedicalrecordService;

@Service
public class MedicalrecordServiceImpl implements MedicalrecordService {

    @Autowired
    private MedicalrecordMapper medicalrecordMapper;

    @Override
    public List<Medicalrecord> selectAll() {
        return medicalrecordMapper.selectAll();
    }

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return medicalrecordMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKey(Medicalrecord record) {
        return medicalrecordMapper.updateByPrimaryKey(record);
    }

    @Override
    public Medicalrecord selectByPrimaryKey(Integer id) {
        return medicalrecordMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Medicalrecord> selectByUserId(Integer userId) {
        return medicalrecordMapper.selectByUserId(userId);
    }

    @Override
    public List<Medicalrecord> selectByPage(Integer doctorid, Integer offset, Integer limit) {
        return medicalrecordMapper.selectByPage(doctorid, offset, limit);
    }

    @Override
    public Integer selectTotal(Integer doctorid) {
        return medicalrecordMapper.selectTotal(doctorid);
    }

    @Override
    public int insert(Medicalrecord record) {
        return medicalrecordMapper.insert(record);
    }

    @Override
    public int update(Medicalrecord record) {
        return medicalrecordMapper.update(record);
    }

    @Override
    public int delete(Integer id) {
        return medicalrecordMapper.delete(id);
    }

    @Override
    public List<Medicalrecord> selectByUserId(Integer userId, Integer offset, Integer limit) {
        return medicalrecordMapper.selectByUserIdPage(userId, offset, limit);
    }
}
