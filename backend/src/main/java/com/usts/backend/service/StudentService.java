package com.usts.backend.service;

import com.alibaba.fastjson.JSONObject;
import com.usts.backend.pojo.Student;

//import java.util.Map;

public interface StudentService {
    JSONObject add(Student student);

    JSONObject remove(Integer id);

    JSONObject update(Student student);

    JSONObject getList(int page, int size);

    JSONObject find(int page, int size, String name);

}
