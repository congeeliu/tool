package com.usts.backend.test.service;

import com.alibaba.fastjson.JSONObject;
import com.usts.backend.test.pojo.Student;

public interface StudentService {
    JSONObject add(Student student);

    JSONObject remove(Integer id);

    JSONObject update(Student student);

    JSONObject getList(int page, int size);

    JSONObject find(Integer id, int page, int size);

}
