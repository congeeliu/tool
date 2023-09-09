package com.usts.backend.controller;

import com.alibaba.fastjson.JSONObject;
import com.usts.backend.pojo.GenerateMessage;
import com.usts.backend.pojo.Property;
import com.usts.backend.service.GenerateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class GenerateController {
    @Autowired
    private GenerateService generateService;

    @PostMapping("/generate/")
    private void generate(@RequestBody GenerateMessage generateMessage) {
//        System.out.println(properties);
        generateService.generateAll(generateMessage);
}

//    @PostMapping("/generate/test/")
//    private void generateTest(@RequestBody GenerateMessage generateMessage) {
//        System.out.println(generateMessage.getPackageName() + " " + generateMessage.getClassName());
//        System.out.println(generateMessage.getProperties().get(0).getName());
//    }
}
