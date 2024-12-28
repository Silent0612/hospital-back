package com.dzqc.cloud.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.dzqc.cloud.entity.Appointment;

@Mapper
public interface AppointmentMapper {
    void insert(Appointment appointment);
    
    List<Appointment> selectByUserId(Integer userId);
    
    List<Appointment> selectByDoctorId(Integer doctorId);
    
    Appointment selectByPrimaryKey(Integer id);
    
    int updateByPrimaryKeySelective(Appointment appointment);
} 