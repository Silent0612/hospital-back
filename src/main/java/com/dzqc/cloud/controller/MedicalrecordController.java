package com.dzqc.cloud.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dzqc.cloud.common.ResultObject;
import com.dzqc.cloud.entity.Medicalrecord;
import com.dzqc.cloud.service.MedicalrecordService;

@RestController
@RequestMapping("/api/medicalrecord")
public class MedicalrecordController {
    
    @Autowired
    private MedicalrecordService medicalrecordService;

    @GetMapping("/list")
    public ResultObject list(@RequestParam Integer doctorid,
                           @RequestParam(defaultValue = "0") Integer page,
                           @RequestParam(defaultValue = "10") Integer size) {
        Integer offset = page * size;
        List<Medicalrecord> records = medicalrecordService.selectByPage(doctorid, offset, size);
        Integer total = medicalrecordService.selectTotal(doctorid);
        
        Map<String, Object> data = new HashMap<>();
        data.put("records", records);
        data.put("total", total);
        
        return new ResultObject(200, "获取病历记录成功", data);
    }

    @PostMapping("/add")
    public ResultObject add(@RequestBody Medicalrecord record) {
        int result = medicalrecordService.insert(record);
        if (result > 0) {
            return new ResultObject(200, "添加病历记录成功", null);
        }
        return new ResultObject(500, "添加病历记录失败", null);
    }

    @PutMapping("/update")
    public ResultObject update(@RequestBody Medicalrecord record) {
        int result = medicalrecordService.update(record);
        if (result > 0) {
            return new ResultObject(200, "更新病历记录成功", null);
        }
        return new ResultObject(500, "更新病历记录失败", null);
    }

    @DeleteMapping("/delete/{id}")
    public ResultObject delete(@PathVariable Integer id) {
        int result = medicalrecordService.delete(id);
        if (result > 0) {
            return new ResultObject(200, "删除病历记录成功", null);
        }
        return new ResultObject(500, "删除病历记录失败", null);
    }
}
