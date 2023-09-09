package com.usts.backend.test.controller;

import com.alibaba.fastjson.JSONObject;
import com.usts.backend.test.pojo.Student;
import com.usts.backend.test.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

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
        private JSONObject getList(int page, int size) {
        return studentService.getList(page, size);
    }

    @GetMapping("/student/find/")
    private JSONObject find(Integer id, int page, int size) {
        return studentService.find(id, page, size);
    }

}
