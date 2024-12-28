package com.dzqc.cloud.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dzqc.cloud.entity.Empinfo;
import com.dzqc.cloud.service.EmpinfoService;

@RestController
@CrossOrigin(origins = {"http://localhost:5000", "http://localhost:5001"}, allowCredentials = "true")
@RequestMapping("/api/empinfo")
public class EmpinfoController {
    @Autowired
    private EmpinfoService empinfoService;

    @PostMapping("/login")
    public Map<String, Object> login(@RequestParam String phone, @RequestParam String password) {
        Map<String, Object> result = new HashMap<>();
        try {
            Empinfo empinfo = empinfoService.login(phone, password);
            if (empinfo != null) {
                result.put("code", 200);
                result.put("message", "登录成功");
                result.put("data", empinfo);
            } else {
                result.put("code", 500);
                result.put("message", "用户名或密码错误");
            }
        } catch (Exception e) {
            result.put("code", 500);
            result.put("message", "登录失败：" + e.getMessage());
        }
        return result;
    }

    @GetMapping("/findByDid")
    public Map<String, Object> findByDid(@RequestParam(value = "departmentid", required = true) Integer departmentid) {
        Map<String, Object> result = new HashMap<>();
        try {
            if (departmentid == null) {
                result.put("code", 400);
                result.put("message", "科室ID不能为空");
                return result;
            }
            System.out.println("正在查询科室ID: " + departmentid + " 的医生列表");
            List<Empinfo> doctors = empinfoService.findByDid(departmentid);
            System.out.println("查询到 " + (doctors != null ? doctors.size() : 0) + " 个医生");
            result.put("code", 200);
            result.put("message", "获取医生列表成功");
            result.put("data", doctors);
        } catch (Exception e) {
            e.printStackTrace();
            result.put("code", 500);
            result.put("message", "获取医生列表失败：" + e.getMessage());
        }
        return result;
    }

    @GetMapping("/findById")
    public Map<String, Object> findById(@RequestParam Integer id) {
        Map<String, Object> result = new HashMap<>();
        try {
            Empinfo doctor = empinfoService.findById(id);
            if (doctor != null) {
                result.put("code", 200);
                result.put("message", "获取医生信息成功");
                result.put("data", doctor);
            } else {
                result.put("code", 404);
                result.put("message", "未找到医生信息");
            }
        } catch (Exception e) {
            result.put("code", 500);
            result.put("message", "获取医生信息失败：" + e.getMessage());
        }
        return result;
    }
}
