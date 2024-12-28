package com.dzqc.cloud.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @GetMapping("/test/db")
    public Object testDb() {
        String sql = "SELECT * FROM departmentinfo LIMIT 1";
        List<Map<String, Object>> result = jdbcTemplate.queryForList(sql);
        return result;
    }
}
