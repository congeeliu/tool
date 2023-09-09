package com.usts.backend.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.usts.backend.pojo.GenerateMessage;
import com.usts.backend.pojo.Property;
import com.usts.backend.service.GenerateService;
import freemarker.template.Configuration;
import freemarker.template.Template;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class GenerateServiceImpl implements GenerateService {

    private static final String TEMPLATE_PATH = "src/main/resources/templates/freemarker/";
    private static final String OUT_PATH = "src/main/java/com/usts/backend/test/";

    @Override
    public void generateAll(GenerateMessage generateMessage) {
//        List<String> attributes = JSONArray.parseArray(attributeStr, String.class);
//        List<String> types = JSONArray.parseArray(typeStr, String.class);

//        String packageName = "com.usts.backend.test";
//        String className = "Student";
//        List<Property> properties = new ArrayList<>();
//        properties.add(new Property("id", "Integer"));
//        properties.add(new Property("name", "String"));
//        properties.add(new Property("age", "Integer"));

        String packageName = generateMessage.getPackageName();
        String className = generateMessage.getClassName();
        List<Property> properties = generateMessage.getProperties();

        Map<String, Object> dataMap = new HashMap<String, Object>();
        dataMap.put("packageName", packageName);
        dataMap.put("className", className);
        dataMap.put("properties", properties);
        dataMap.put("firstPropertyType", properties.get(0).getType());
        dataMap.put("firstPropertyName", properties.get(0).getName());

        String[] templateNameList = {"Model.ftl", "Mapper.ftl", "Service.ftl", "ServiceImpl.ftl", "Controller.ftl", "Mybatis.ftl"};
        String[] outNameList = {"pojo/" + className + ".java", "mapper/" + className + "Mapper.java", "service/" + className + "Service.java", "service/impl/" + className + "ServiceImpl.java", "controller/" + className + "Controller.java", "mapper/" + className + "Mapper.xml"};

        for (int i = 0; i < templateNameList.length; i ++) {
            generate(templateNameList[i], outNameList[i], dataMap);
        }
    }

    private void generate(String templateName, String outName, Map<String, Object> dataMap) {
        Configuration configuration = new Configuration(Configuration.VERSION_2_3_0);
        Writer out = null;
        try {
            // step2 获取模版路径
            configuration.setDirectoryForTemplateLoading(new File(TEMPLATE_PATH));
            // step3 创建数据模型
//            Map<String, Object> dataMap = new HashMap<String, Object>();
//            dataMap.put("packageName", "com.usts.backend.test");
            // step4 加载模版文件
            Template template = configuration.getTemplate(templateName);
            // step5 生成数据
            File outFile = new File(OUT_PATH  + outName);
            out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outFile)));
            // step6 输出文件
            template.process(dataMap, out);
            System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^" + outName + " 文件创建成功 !");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (out != null) {
                    out.flush();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
