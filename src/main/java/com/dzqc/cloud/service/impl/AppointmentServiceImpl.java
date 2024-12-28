package com.dzqc.cloud.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dzqc.cloud.dao.AppointmentMapper;
import com.dzqc.cloud.entity.Appointment;
import com.dzqc.cloud.service.AppointmentService;

@Service
public class AppointmentServiceImpl implements AppointmentService {

    @Autowired
    private AppointmentMapper appointmentMapper;

    @Override
    public void createAppointment(Appointment appointment) {
        appointmentMapper.insert(appointment);
    }

    @Override
    public List<Appointment> getAppointmentsByUserId(Integer userId) {
        return appointmentMapper.selectByUserId(userId);
    }

    @Override
    public List<Appointment> getAppointmentsByDoctorId(Integer doctorId) {
        return appointmentMapper.selectByDoctorId(doctorId);
    }

    @Override
    public Appointment getAppointmentById(Integer id) {
        return appointmentMapper.selectByPrimaryKey(id);
    }

    @Override
    public void updateAppointment(Appointment appointment) {
        appointmentMapper.updateByPrimaryKeySelective(appointment);
    }
} 