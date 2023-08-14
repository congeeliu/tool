package com.usts.backend.service.impl;

import com.usts.backend.service.GenerateCodeService;
import freemarker.template.Configuration;
import freemarker.template.Template;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class GenerateCodeServiceImpl implements GenerateCodeService {
    private static final String TEMPLATE_PATH = "src/main/resources/templates/";
    private static final String CLASS_PATH = "src/main/java/com/usts/backend/test/";

    @Override
    public void generate() {
//        // step1 创建freeMarker配置实例
//        Configuration configuration = new Configuration(Configuration.VERSION_2_3_0);
//        Writer out = null;
//        try {
//            // step2 获取模版路径
//            configuration.setDirectoryForTemplateLoading(new File(TEMPLATE_PATH));
//            // step3 创建数据模型
//            Map<String, Object> dataMap = new HashMap<String, Object>();
//            dataMap.put("classPath", "com.freemark.hello");
//            dataMap.put("className", "User");
//            dataMap.put("Id", "Id");
//            dataMap.put("userName", "userName");
//            dataMap.put("password","password");
//            // step4 加载模版文件
//            Template template = configuration.getTemplate("test.ftl");
//            // step5 生成数据
//            File docFile = new File(CLASS_PATH + "\\" + "User.java");
//            out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(docFile)));
//            // step6 输出文件
//            template.process(dataMap, out);
//            System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^User.java 文件创建成功 !");
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            try {
//                if (null != out) {
//                    out.flush();
//                }
//            } catch (Exception e2) {
//                e2.printStackTrace();
//            }
//        }
    }


//    Configuration cfg = null;
//    {
//        cfg = new Configuration(Configuration.VERSION_2_3_0);
//        cfg.setTemplateLoader(new ClassTemplateLoader(GenerateCodeService.class, "/templates"));
//        cfg.setDefaultEncoding("UTF-8");
//    }

//    public void generateCode(List<TableClass> tableClassList, String realPath) {
//        try {
//            Template modelTemplate = cfg.getTemplate("Model.ftl.ftl");
//            Template mapperJavaTemplate = cfg.getTemplate("Mapper.java.ftl");
//            Template mapperXmlTemplate = cfg.getTemplate("Mapper.xml.ftl");
//            Template serviceTemplate = cfg.getTemplate("Service.java.ftl");
//            Template controllerTemplate = cfg.getTemplate("Controller.java.ftl");
//            Connection connection = DBUtils.getConnection();
//            DatabaseMetaData metaData = connection.getMetaData();
//            for (TableClass tableClass : tableClassList) {
//                ResultSet columns = metaData.getColumns(connection.getCatalog(), null, tableClass.getTableName(), null);
//                ResultSet primaryKeys = metaData.getPrimaryKeys(connection.getCatalog(), null, tableClass.getTableName());
//                List<ColumnClass> columnClassList = new ArrayList<>();
//                while (columns.next()) {
//                    String column_name = columns.getString("COLUMN_NAME");
//                    String type_name = columns.getString("TYPE_NAME");
//                    String remarks = columns.getString("REMARKS");
//                    ColumnClass columnClass = new ColumnClass();
//                    columnClass.setRemark(remarks);
//                    columnClass.setColumnName(column_name);
//                    columnClass.setType(type_name);
//                    columnClass.setPropertyName(CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, column_name));
//                    primaryKeys.first();
//                    while (primaryKeys.next()) {
//                        String pkName = primaryKeys.getString("COLUMN_NAME");
//                        if (column_name.equals(pkName)) {
//                            columnClass.setPrimary(true);
//                        }
//                    }
//                    columnClassList.add(columnClass);
//                }
//                tableClass.setColumns(columnClassList);
//                String path = realPath + "/" + tableClass.getPackageName().replace(".", "/");
//                generate(modelTemplate, tableClass, path + "/model/");
//                generate(mapperJavaTemplate, tableClass, path + "/mapper/");
//                generate(mapperXmlTemplate, tableClass, path + "/mapper/");
//                generate(serviceTemplate, tableClass, path + "/service/");
//                generate(controllerTemplate, tableClass, path + "/controller/");
//            }
//            return RespBean.ok("代码已生成", realPath);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return RespBean.error("代码生成失败");
//    }
//
//    private void generate(Template template, TableClass tableClass, String path) throws IOException, TemplateException {
//        File folder = new File(path);
//        if (!folder.exists()) {
//            folder.mkdirs();
//        }
//        String fileName = path + "/" + tableClass.getModelName() + template.getName().replace(".ftl", "").replace("Model", "");
//        FileOutputStream fos = new FileOutputStream(fileName);
//        OutputStreamWriter out = new OutputStreamWriter(fos);
//        template.process(tableClass,out);
//        fos.close();
//        out.close();
//    }
}
