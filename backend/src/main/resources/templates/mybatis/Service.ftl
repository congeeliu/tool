package com.usts.backend.service;

import com.alibaba.fastjson.JSONObject;
import com.usts.backend.pojo.Student;

import java.util.Map;

public interface StudentService {
Map<String, String> add(Student student);

Map<String, String> remove(Integer id);

Map<String, String> update(Student student);

JSONObject getList(Integer page, Integer size, Student student);

}
