package com.usts.backend.service;

import com.usts.backend.pojo.GenerateMessage;
import com.usts.backend.pojo.Property;

import java.util.List;

public interface GenerateService {
    void generateAll(GenerateMessage generateMessage);
}
