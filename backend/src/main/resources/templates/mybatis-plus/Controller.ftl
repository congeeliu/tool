package com.usts.backend.controller;

import com.alibaba.fastjson.JSONObject;
import com.usts.backend.pojo.Student;
import com.usts.backend.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class StudentController {
@Autowired
private StudentService studentService;

@PostMapping("/student/add/")
private Map<String, String> add(Student student) {
return studentService.add(student);
}

@PostMapping("/student/remove/")
private Map<String, String> remove(Integer id) {
return studentService.remove(id);
}

@PostMapping("/student/update/")
private Map<String, String> update(Student student) {
return studentService.update(student);
}

@GetMapping("/student/get/")
private JSONObject getList(Integer page, Integer size, Student student) {
return studentService.getList(page, size, student);
}

}
