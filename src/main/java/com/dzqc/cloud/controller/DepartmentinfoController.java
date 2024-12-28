package com.dzqc.cloud.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dzqc.cloud.entity.Departmentinfo;
import com.dzqc.cloud.service.DepartmentinfoService;

@RestController
@CrossOrigin(origins = {"http://localhost:5000", "http://localhost:5001"}, allowCredentials = "true")
@RequestMapping("/api/departmentinfo")
public class DepartmentinfoController {
    
    @Autowired
    private DepartmentinfoService departmentinfoService;
    
    @GetMapping("/list")
    public Map<String, Object> list() {
        Map<String, Object> result = new HashMap<>();
        try {
            List<Departmentinfo> departments = departmentinfoService.selectAll();
            result.put("code", 200);
            result.put("message", "获取科室列表成功");
            result.put("data", departments);
        } catch (Exception e) {
            result.put("code", 500);
            result.put("message", "获取科室列表失败：" + e.getMessage());
        }
        return result;
    }
    
    @GetMapping("/findById")
    public Map<String, Object> findById(@RequestParam(value = "id", required = true) Integer id) {
        Map<String, Object> result = new HashMap<>();
        try {
            if (id == null) {
                result.put("code", 400);
                result.put("message", "科室ID不能为空");
                return result;
            }
            System.out.println("正在查询科室ID: " + id);
            Departmentinfo department = departmentinfoService.findById(id);
            if (department != null) {
                System.out.println("找到科室: " + department.getDepartmentname());
                result.put("code", 200);
                result.put("message", "获取科室信息成功");
                result.put("data", department);
            } else {
                result.put("code", 404);
                result.put("message", "未找到科室信息");
            }
        } catch (Exception e) {
            e.printStackTrace();
            result.put("code", 500);
            result.put("message", "获取科室信息失败：" + e.getMessage());
        }
        return result;
    }
}