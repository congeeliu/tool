package com.usts.backend.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.usts.backend.mapper.TeacherMapper;
import com.usts.backend.pojo.Teacher;
import com.usts.backend.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TeacherServiceImpl implements TeacherService {
    @Autowired
    private TeacherMapper teacherMapper;

    @Override
    public Map<String, String> add(Teacher teacher) {
        teacherMapper.insert(teacher);
        Map<String, String> resp = new HashMap<>();
        resp.put("errorMessage", "success");
        return resp;
    }

    @Override
    public Map<String, String> remove(Integer id) {
        teacherMapper.deleteById(id);
        Map<String, String> resp = new HashMap<>();
        resp.put("errorMessage", "success");
        return resp;
    }

    @Override
    public Map<String, String> update(Teacher teacher) {
        teacherMapper.updateById(teacher);
        Map<String, String> resp = new HashMap<>();
        resp.put("errorMessage", "success");
        return resp;
    }

    @Override
    public JSONObject getList(Integer page, Integer size, Teacher teacher) {
        IPage<Teacher> teacherIPage = new Page<>(page, size);
        QueryWrapper<Teacher> queryWrapper = new QueryWrapper<>();
        queryWrapper.like(!"".equals(teacher.getUsername()), "username", teacher.getUsername());
        List<Teacher> teachers = teacherMapper.selectPage(teacherIPage, queryWrapper).getRecords();
        JSONObject resp = new JSONObject();
        resp.put("teachers", teachers);
        resp.put("recordsCount", teacherMapper.selectCount(queryWrapper));
        return resp;
    }

}
