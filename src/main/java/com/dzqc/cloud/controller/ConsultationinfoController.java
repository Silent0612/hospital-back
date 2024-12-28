package com.dzqc.cloud.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dzqc.cloud.common.Message;
import com.dzqc.cloud.common.ResultObject;
import com.dzqc.cloud.entity.Medicalrecord;
import com.dzqc.cloud.service.MedicalrecordService;

@RestController
@CrossOrigin
@RequestMapping("/api/consultation")
public class ConsultationinfoController {

    private static final Logger logger = LoggerFactory.getLogger(ConsultationinfoController.class);

    @Autowired
    private MedicalrecordService medicalrecordService;

    /**
     * 查询所有就诊记录
     */
    @RequestMapping("/list")
    public ResultObject list() {
        try {
            List<Medicalrecord> list = medicalrecordService.selectAll();
            return ResultObject.success(list);
        } catch (Exception e) {
            return ResultObject.error(Message.SERVER_ERROR);
        }
    }

    /**
     * 添加就诊记录
     */
    @RequestMapping("/add")
    public ResultObject add(@RequestBody Medicalrecord medicalrecord) {
        try {
            medicalrecordService.insert(medicalrecord);
            return ResultObject.success("添加成功");
        } catch (Exception e) {
            logger.error("添加就诊记录失败", e);
            return ResultObject.error(Message.SERVER_ERROR);
        }
    }

    /**
     * 删除就诊记录
     */
    @RequestMapping("/delete")
    public ResultObject delete(Integer id) {
        try {
            medicalrecordService.deleteByPrimaryKey(id);
            return ResultObject.success("删除成功");
        } catch (Exception e) {
            return ResultObject.error(Message.SERVER_ERROR);
        }
    }

    /**
     * 修改就诊记录
     */
    @RequestMapping("/update")
    public ResultObject update(@RequestBody Medicalrecord medicalrecord) {
        try {
            medicalrecordService.updateByPrimaryKey(medicalrecord);
            return ResultObject.success("修改成功");
        } catch (Exception e) {
            return ResultObject.error(Message.SERVER_ERROR);
        }
    }

    /**
     * 根据ID查询就诊记录
     */
    @RequestMapping("/findById")
    public ResultObject findById(Integer id) {
        try {
            Medicalrecord medicalrecord = medicalrecordService.selectByPrimaryKey(id);
            return ResultObject.success(medicalrecord);
        } catch (Exception e) {
            logger.error("查询就诊记录失败", e);
            return ResultObject.error(Message.SERVER_ERROR);
        }
    }

    /**
     * 根据用户ID查询就诊记录
     */
    @RequestMapping("/findByUserId")
    public ResultObject findByUserId(Integer userId) {
        try {
            List<Medicalrecord> list = medicalrecordService.selectByUserId(userId);
            return ResultObject.success(list);
        } catch (Exception e) {
            return ResultObject.error(Message.SERVER_ERROR);
        }
    }
}
