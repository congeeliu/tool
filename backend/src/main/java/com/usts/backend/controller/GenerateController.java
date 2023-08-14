package com.usts.backend.controller;

import com.usts.backend.service.GenerateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GenerateController {
    @Autowired
    private GenerateService generateService;

    @PostMapping("/generate/")
    private void generate(String packageName, String entityName, String attribute, String type) {
        generateService.generate(packageName, entityName, attribute, type);
    }
}
