package com.usts.backend.service.impl;

import com.alibaba.fastjson.JSONObject;
//import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
//import com.baomidou.mybatisplus.core.metadata.IPage;
//import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
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
    public JSONObject add(Student student) {
//        studentMapper.insert(student);
        studentMapper.add(student);
        JSONObject resp = new JSONObject();
        resp.put("errorMessage", "success");
        return resp;
    }

    @Override
    public JSONObject remove(Integer id) {
//        studentMapper.deleteById(id);
        studentMapper.remove(id);
        JSONObject resp = new JSONObject();
        resp.put("errorMessage", "success");
        return resp;
    }

    @Override
    public JSONObject update(Student student) {
//        studentMapper.updateById(student);
        studentMapper.update(student);
        JSONObject resp = new JSONObject();
        resp.put("errorMessage", "success");
        return resp;
    }

    @Override
    public JSONObject getList(int page, int size) {
//        IPage<Student> studentIPage = new Page<>(page, size);
//        QueryWrapper<Student> queryWrapper = new QueryWrapper<>();
//        queryWrapper.like(!"".equals(student.getName()), "name", student.getName());
//        List<Student> students = studentMapper.selectPage(studentIPage, queryWrapper).getRecords();
//        JSONObject resp = new JSONObject();
//        resp.put("students", students);
//        resp.put("recordsCount", studentMapper.selectCount(queryWrapper));
//        return resp;

        int curIndex = (page - 1) * size;
        List<Student> students = studentMapper.getList(curIndex, size);
        JSONObject resp = new JSONObject();
        resp.put("students", students);
        resp.put("recordsCount", studentMapper.getCount());
        return resp;
    }

    @Override
    public JSONObject find(int page, int size, String name) {
        int curIndex = (page - 1) * size;
        List<Student> students = studentMapper.find(curIndex, size, name);
        int cnt = studentMapper.getCountOfFind(name);
        JSONObject resp = new JSONObject();
        resp.put("students", students);
        resp.put("recordsCount", cnt);
        return resp;
    }

}
