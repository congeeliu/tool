package com.usts.backend.service;

import com.alibaba.fastjson.JSONObject;
import com.usts.backend.pojo.Teacher;

import java.util.Map;

public interface TeacherService {
    Map<String, String> add(Teacher teacher);

    Map<String, String> remove(Integer id);

    Map<String, String> update(Teacher teacher);

    JSONObject getList(Integer page, Integer size, Teacher teacher);

}
