package com.dzqc.cloud.service;

import java.util.List;

import com.dzqc.cloud.entity.Appointment;

public interface AppointmentService {
    /**
     * 创建预约
     */
    void createAppointment(Appointment appointment);

    /**
     * 获取用户的预约列表
     */
    List<Appointment> getAppointmentsByUserId(Integer userId);

    /**
     * 获取医生的预约列表
     */
    List<Appointment> getAppointmentsByDoctorId(Integer doctorId);

    /**
     * 获取单个预约
     */
    Appointment getAppointmentById(Integer id);

    /**
     * 更新预约
     */
    void updateAppointment(Appointment appointment);
} 