package com.sba.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/jdbc")
public class JdbcController {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @RequestMapping("/app_d")
    public String app_d () {
        System.out.println("app_d");
        return "app_d";
    }

    @GetMapping("/findUserList")
    public List<Map<String, Object>> findUserList() {
        System.out.println("findUserList");
        List<Map<String, Object>> userListMap = new ArrayList<>();
        try {
            String sql = "select userid, username, orgid, sex, idcard from sys_users where userid like '%000%' and effectflag = 'E' order by userid ";
            userListMap = jdbcTemplate.queryForList(sql);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return userListMap;
    }
}
