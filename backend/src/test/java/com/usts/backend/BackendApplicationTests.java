package com.usts.backend;

import com.usts.backend.service.impl.utils.Attribute;
import freemarker.template.Configuration;
import freemarker.template.Template;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest
class BackendApplicationTests {

    private static final String TEMPLATE_PATH = "src/main/resources/templates/";
    private static final String CLASS_PATH = "src/main/java/com/usts/backend/test/";

    @Test
    void contextLoads() {
        Configuration configuration = new Configuration(Configuration.VERSION_2_3_0);
        Writer out = null;
        try {
            // step2 获取模版路径
            configuration.setDirectoryForTemplateLoading(new File(TEMPLATE_PATH));
            // step3 创建数据模型
            Map<String, Object> dataMap = new HashMap<String, Object>();
            dataMap.put("classPath", "com.usts.backend.test");
            dataMap.put("className", "User");

            List<Attribute> attributes = new ArrayList<>();
            attributes.add(new Attribute("username", "String"));
            attributes.add(new Attribute("password", "String"));
            dataMap.put("attributes", attributes);
//            dataMap.put("username", "username");
//            dataMap.put("password","password");
            // step4 加载模版文件
            Template template = configuration.getTemplate("test.ftl");
            // step5 生成数据
            File docFile = new File(CLASS_PATH  + "User.java");
            out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(docFile)));
            // step6 输出文件
            template.process(dataMap, out);
            System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^User.java 文件创建成功 !");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != out) {
                    out.flush();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
