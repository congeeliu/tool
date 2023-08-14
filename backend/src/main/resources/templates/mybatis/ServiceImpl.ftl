package com.usts.backend.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.usts.backend.mapper.StudentMapper;
import com.usts.backend.pojo.Student;
import com.usts.backend.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class StudentServiceImpl implements StudentService {
@Autowired
private StudentMapper studentMapper;

@Override
public Map<String, String> add(Student student) {
studentMapper.insert(student);
Map<String, String> resp = new HashMap<>();
resp.put("errorMessage", "success");
return resp;
}

@Override
public Map<String, String> remove(Integer id) {
studentMapper.deleteById(id);
Map<String, String> resp = new HashMap<>();
resp.put("errorMessage", "success");
return resp;
}

@Override
public Map<String, String> update(Student student) {
studentMapper.updateById(student);
Map<String, String> resp = new HashMap<>();
resp.put("errorMessage", "success");
return resp;
}

@Override
public JSONObject getList(Integer page, Integer size, Student student) {
IPage<Student> studentIPage = new Page<>(page, size);
    QueryWrapper<Student> queryWrapper = new QueryWrapper<>();
        queryWrapper.like(!"".equals(student.getName()), "name", student.getName());
        List<Student> students = studentMapper.selectPage(studentIPage, queryWrapper).getRecords();
            JSONObject resp = new JSONObject();
            resp.put("students", students);
            resp.put("recordsCount", studentMapper.selectCount(queryWrapper));
            return resp;
            }

            }
