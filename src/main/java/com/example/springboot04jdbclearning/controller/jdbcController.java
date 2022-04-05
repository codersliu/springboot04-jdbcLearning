package com.example.springboot04jdbclearning.controller;

import com.sun.org.glassfish.gmbal.ParameterNames;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class jdbcController {
    @Autowired
    JdbcTemplate jdbcTemplate;

    //查询所有user信息
    @GetMapping("/jdbcSelect")
    public List<Map<String, Object>> jdbcSelect(){
        String sql = "select * from user";
        List<Map<String, Object>> maps = jdbcTemplate.queryForList(sql);
        return maps;

    }

    //插入新user信息
    @GetMapping("/jdbcInsert")
    public String jdbcInsert(){
        String sql = "insert into company.user(id, username, password) values (6, 'junjw', '4545456')";
        jdbcTemplate.update(sql);
        return "message==>insert ok!";
    }

    //根据传入id修改user信息
    @GetMapping("/jdbcUpdate/{id}")
    public String jdbcUpdate(@PathVariable("id") int id){
        String sql = "update company.user set username=?, password=? where id="+id;
        Object[] newUser = new Object[2];
        newUser[0] = "yaoww";
        newUser[1] = "112233";
        jdbcTemplate.update(sql, newUser);
        return "message==>update ok!";
    }

    //根据传入id删除user信息
    @GetMapping("jdbcDelete/{id}")
    public String jdbcDelete(@PathVariable("id") int id){
        String sql = "delete from company.user where id=?";
        jdbcTemplate.update(sql, id);
        return "message==>delete ok!";
    }
}
