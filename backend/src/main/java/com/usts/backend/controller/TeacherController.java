package com.usts.backend.controller;

import com.alibaba.fastjson.JSONObject;
import com.usts.backend.pojo.Teacher;
import com.usts.backend.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class TeacherController {
    @Autowired
    private TeacherService teacherService;

    @PostMapping("/teacher/add/")
    private Map<String, String> add(Teacher teacher) {
        return teacherService.add(teacher);
    }

    @PostMapping("/teacher/remove/")
    private Map<String, String> remove(Integer id) {
        return teacherService.remove(id);
    }

    @PostMapping("/teacher/update/")
    private Map<String, String> update(Teacher teacher) {
        return teacherService.update(teacher);
    }

    @GetMapping("/teacher/get/")
    private JSONObject getList(Integer page, Integer size, Teacher teacher) {
        return teacherService.getList(page, size, teacher);
    }

}
