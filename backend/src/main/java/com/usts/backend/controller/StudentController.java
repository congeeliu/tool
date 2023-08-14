package com.usts.backend.controller;

import com.alibaba.fastjson.JSONObject;
import com.usts.backend.pojo.Student;
import com.usts.backend.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

//import java.util.Map;

@RestController
public class StudentController {
    @Autowired
    private StudentService studentService;

    @PostMapping("/student/add/")
    private JSONObject add(Student student) {
        return studentService.add(student);
    }

    @PostMapping("/student/remove/")
    private JSONObject remove(Integer id) {
        return studentService.remove(id);
    }

    @PostMapping("/student/update/")
    private JSONObject update(Student student) {
        return studentService.update(student);
    }

    @GetMapping("/student/get/")
    private JSONObject getList(int page, int size, String name) {
//        return studentService.getList(page, size);
        return studentService.find(page, size, name);
    }

}
