package com.usts.backend.test.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.usts.backend.test.mapper.StudentMapper;
import com.usts.backend.test.pojo.Student;
import com.usts.backend.test.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    private StudentMapper studentMapper;

    @Override
    public JSONObject add(Student student) {
        studentMapper.add(student);
        JSONObject resp = new JSONObject();
        resp.put("errorMessage", "success");
        return resp;
    }

    @Override
    public JSONObject remove(Integer id) {
        studentMapper.remove(id);
        JSONObject resp = new JSONObject();
        resp.put("errorMessage", "success");
        return resp;
    }

    @Override
    public JSONObject update(Student student) {
        studentMapper.update(student);
        JSONObject resp = new JSONObject();
        resp.put("errorMessage", "success");
        return resp;
    }

    @Override
    public JSONObject getList(int page, int size) {
        int curIndex = (page - 1) * size;
        List<Student> students = studentMapper.getList(curIndex, size);
        JSONObject resp = new JSONObject();
        resp.put("students", students);
        resp.put("recordsCount", studentMapper.getCount());
        return resp;
    }

    @Override
    public JSONObject find(Integer id, int page, int size) {
        int curIndex = (page - 1) * size;
        List<Student> students = studentMapper.find(id, curIndex, size);
        int cnt = studentMapper.getCountOfFind(id);
        JSONObject resp = new JSONObject();
        resp.put("students", students);
        resp.put("recordsCount", cnt);
        return resp;
    }

}
