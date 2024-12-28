package com.dzqc.cloud.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dzqc.cloud.common.ResultObject;
import com.dzqc.cloud.entity.Appointment;
import com.dzqc.cloud.entity.Empinfo;
import com.dzqc.cloud.service.AppointmentService;
import com.dzqc.cloud.service.DepartmentService;
import com.dzqc.cloud.service.EmpinfoService;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class AppointmentController {
    
    @Autowired
    private AppointmentService appointmentService;

    @Autowired
    private EmpinfoService empinfoService;

    @Autowired
    private DepartmentService departmentService;

    /**
     * 创建预约
     */
    @PostMapping("/appointments")
    public ResultObject createAppointment(@RequestBody Appointment appointment) {
        try {
            // 设置创建时间
            appointment.setCreateTime(new Date());
            // 设置状态为待就诊
            appointment.setStatus(0);
            
            // 验证必要参数
            if (appointment.getUserId() == null) {
                return ResultObject.error("用户ID不能为空");
            }
            if (appointment.getDoctorId() == null) {
                return ResultObject.error("医生ID不能为空");
            }
            if (appointment.getUserName() == null || appointment.getUserName().trim().isEmpty()) {
                return ResultObject.error("患者姓名不能为空");
            }
            if (appointment.getUserGender() == null) {
                return ResultObject.error("患者性别不能为空");
            }
            if (appointment.getUserAge() == null) {
                return ResultObject.error("患者年龄不能为空");
            }
            
            // 获取医生信息
            Empinfo doctor = empinfoService.findById(appointment.getDoctorId());
            if (doctor != null) {
                appointment.setDoctorName(doctor.getUsername());
                appointment.setDepartmentName(departmentService.findById(doctor.getDepId()).getDepartmentname());
            }
            
            appointmentService.createAppointment(appointment);
            return ResultObject.success("预约成功");
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObject.error("预约失败：" + e.getMessage());
        }
    }

    /**
     * 获取用户的预约列表
     */
    @GetMapping("/appointments/user/{userId}")
    public ResultObject getUserAppointments(@PathVariable Integer userId) {
        try {
            List<Appointment> appointments = appointmentService.getAppointmentsByUserId(userId);
            return new ResultObject(200, "获取预约列表成功", appointments);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObject.error("获取预约列表失败：" + e.getMessage());
        }
    }

    /**
     * 获取医生的预约列表
     */
    @GetMapping("/appointments/doctor/{doctorId}")
    public ResultObject getDoctorAppointments(@PathVariable Integer doctorId) {
        try {
            return ResultObject.success(appointmentService.getAppointmentsByDoctorId(doctorId));
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObject.error("获取预约列表失败：" + e.getMessage());
        }
    }

    /**
     * 取消预约
     */
    @PutMapping("/appointments/{id}/cancel")
    public ResultObject cancelAppointment(@PathVariable Integer id) {
        try {
            Appointment appointment = appointmentService.getAppointmentById(id);
            if (appointment == null) {
                return ResultObject.error("预约不存在");
            }
            
            if (appointment.getStatus() != 0) {
                return ResultObject.error("该预约无法取消");
            }
            
            // 设置状态为已取消
            appointment.setStatus(2);
            appointmentService.updateAppointment(appointment);
            
            return ResultObject.success("取消预约成功");
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObject.error("取消预约失败：" + e.getMessage());
        }
    }

    /**
     * 获取预约详情
     */
    @GetMapping("/appointments/{id}")
    public ResultObject getAppointmentDetail(@PathVariable Integer id) {
        try {
            Appointment appointment = appointmentService.getAppointmentById(id);
            if (appointment == null) {
                return ResultObject.error("预约不存在");
            }
            return ResultObject.success(appointment);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObject.error("获取预约详情失败：" + e.getMessage());
        }
    }

    /**
     * 更新预约信息
     */
    @PutMapping("/appointments/{id}")
    public ResultObject updateAppointment(@PathVariable Integer id, @RequestBody Appointment appointment) {
        try {
            Appointment existingAppointment = appointmentService.getAppointmentById(id);
            if (existingAppointment == null) {
                return ResultObject.error("预约不存在");
            }
            
            // 更新诊断信息
            existingAppointment.setDiagnoseResult(appointment.getDiagnoseResult());
            existingAppointment.setPrescription(appointment.getPrescription());
            existingAppointment.setStatus(appointment.getStatus());
            
            appointmentService.updateAppointment(existingAppointment);
            return ResultObject.success("更新成功");
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObject.error("更新预约信息失败：" + e.getMessage());
        }
    }
}
