package com.usts.backend.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.usts.backend.service.GenerateService;
import org.springframework.stereotype.Service;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

@Service
public class GenerateServiceImpl implements GenerateService {
    @Override
    public void generate(String packageName, String entityName, String attributeStr, String typeStr) {
        List<String> attributes = JSONArray.parseArray(attributeStr, String.class);
        List<String> types = JSONArray.parseArray(typeStr, String.class);

        String entityPath = "D:\\test\\pojo\\" + entityName + ".java";
        String mapperPath = "D:\\test\\mapper\\" + entityName + "Mapper.java";
        String servicePath = "D:\\test\\service\\" + entityName + "Service.java";
        String serviceImplPath = "D:\\test\\service\\impl\\" + entityName + "ServiceImpl.java";
        String controllerPath = "D:\\test\\controller\\" + entityName + "Controller.java";
        String viewPath = "D:\\test\\view\\" + entityName + "View.vue";

        String entityContent = getEntityContent(packageName, entityName, attributes, types);
        String mapperContent = getMapperContent(packageName, entityName);
        String serviceContent = getServiceContent(packageName, entityName);
        String serviceImplContent = getServiceImplContent(packageName, entityName, attributes.get(0));
        String controllerContent = getControllerContent(packageName, entityName);
        String viewContent = getViewContent(entityName, attributes);

        write(entityPath, entityContent);
        write(mapperPath, mapperContent);
        write(servicePath, serviceContent);
        write(serviceImplPath, serviceImplContent);
        write(controllerPath, controllerContent);
        write(viewPath, viewContent);
    }

    private String getEntityContent(String packageName, String entityName, List<String> attributes, List<String> types) {
        String res = "package " + packageName + ".pojo;\n" +
                "\n" +
                "import com.baomidou.mybatisplus.annotation.IdType;\n" +
                "import com.baomidou.mybatisplus.annotation.TableId;\n" +
                "import lombok.AllArgsConstructor;\n" +
                "import lombok.Data;\n" +
                "import lombok.NoArgsConstructor;\n" +
                "\n" +
                "@Data\n" +
                "@NoArgsConstructor\n" +
                "@AllArgsConstructor\n" +
                "public class " + entityName + " {\n" +
                "    @TableId(type = IdType.AUTO)\n" +
                "    private Integer id;\n";
        for (int i = 0; i < attributes.size(); i ++) {
            String t = "    private " + types.get(i) + " " + attributes.get(i) + ";\n";
            res += t;
        }
        res += "}\n";
        return res;
    }

    private String getMapperContent(String packageName, String entityName) {
        String res = "package " + packageName + ".mapper;\n" +
                "\n" +
                "import com.baomidou.mybatisplus.core.mapper.BaseMapper;\n" +
                "import com.usts.backend.pojo."+ entityName +";\n" +
                "import org.apache.ibatis.annotations.Mapper;\n" +
                "\n" +
                "@Mapper\n" +
                "public interface " + entityName + "Mapper extends BaseMapper<" + entityName + "> {\n" +
                "}\n";
        return res;
    }

    private String getServiceContent(String packageName, String entityName) {
        String lowercase = entityName.toLowerCase();
        String combine = entityName + " " + lowercase;
        String res = "package " + packageName + ".service;\n" +
                "\n" +
                "import com.alibaba.fastjson.JSONObject;\n" +
                "import com.usts.backend.pojo." + entityName + ";\n" +
                "\n" +
                "import java.util.Map;\n" +
                "\n" +
                "public interface " + entityName + "Service {\n" +
                "    Map<String, String> add(" + combine + ");\n" +
                "\n" +
                "    Map<String, String> remove(Integer id);\n" +
                "\n" +
                "    Map<String, String> update(" + combine + ");\n" +
                "\n" +
                "    JSONObject getList(Integer page, Integer size, " + combine + ");\n" +
                "\n" +
                "}\n";
        return res;
    }

    private String getServiceImplContent(String packageName, String entityName, String attribute) {
        String lowercase = entityName.toLowerCase();
        String t = "" + attribute.charAt(0);
        String attributeUpper = t.toUpperCase() + attribute.substring(1);
        String combine = entityName + " " + lowercase;
        String res = String.format("package %s.service.impl;\n" +
                "\n" +
                "import com.alibaba.fastjson.JSONObject;\n" +
                "import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;\n" +
                "import com.baomidou.mybatisplus.core.metadata.IPage;\n" +
                "import com.baomidou.mybatisplus.extension.plugins.pagination.Page;\n" +
                "import com.usts.backend.mapper.%sMapper;\n" +
                "import com.usts.backend.pojo.%s;\n" +
                "import com.usts.backend.service.%sService;\n" +
                "import org.springframework.beans.factory.annotation.Autowired;\n" +
                "import org.springframework.stereotype.Service;\n" +
                "\n" +
                "import java.util.HashMap;\n" +
                "import java.util.List;\n" +
                "import java.util.Map;\n" +
                "\n" +
                "@Service\n" +
                "public class %sServiceImpl implements %sService {\n" +
                "    @Autowired\n" +
                "    private %sMapper %sMapper;\n" +
                "\n" +
                "    @Override\n" +
                "    public Map<String, String> add(%s) {\n" +
                "        %sMapper.insert(%s);\n" +
                "        Map<String, String> resp = new HashMap<>();\n" +
                "        resp.put(\"errorMessage\", \"success\");\n" +
                "        return resp;\n" +
                "    }\n" +
                "\n" +
                "    @Override\n" +
                "    public Map<String, String> remove(Integer id) {\n" +
                "        %sMapper.deleteById(id);\n" +
                "        Map<String, String> resp = new HashMap<>();\n" +
                "        resp.put(\"errorMessage\", \"success\");\n" +
                "        return resp;\n" +
                "    }\n" +
                "\n" +
                "    @Override\n" +
                "    public Map<String, String> update(%s) {\n" +
                "        %sMapper.updateById(%s);\n" +
                "        Map<String, String> resp = new HashMap<>();\n" +
                "        resp.put(\"errorMessage\", \"success\");\n" +
                "        return resp;\n" +
                "    }\n" +
                "\n" +
                "    @Override\n" +
                "    public JSONObject getList(Integer page, Integer size, %s) {\n" +
                "        IPage<%s> %sIPage = new Page<>(page, size);\n" +
                "        QueryWrapper<%s> queryWrapper = new QueryWrapper<>();\n" +
                "        queryWrapper.like(!\"\".equals(%s.get%s()), \"%s\", %s.get%s());\n" +
                "        List<%s> %ss = %sMapper.selectPage(%sIPage, queryWrapper).getRecords();\n" +
                "        JSONObject resp = new JSONObject();\n" +
                "        resp.put(\"%ss\", %ss);\n" +
                "        resp.put(\"recordsCount\", %sMapper.selectCount(queryWrapper));\n" +
                "        return resp;\n" +
                "    }\n" +
                "\n" +
                "}\n", packageName, entityName, entityName, entityName, entityName, entityName, entityName, lowercase, combine, lowercase, lowercase, lowercase, combine, lowercase, lowercase, combine, entityName, lowercase, entityName, lowercase, attributeUpper, attribute, lowercase, attributeUpper, entityName, lowercase, lowercase, lowercase, lowercase, lowercase, lowercase);
        return res;
    }

    private String getControllerContent(String packageName, String entityName) {
        String lowercase = entityName.toLowerCase();
        String combine = entityName + " " + lowercase;
        String res = String.format("package %s.controller;\n" +
                "\n" +
                "import com.alibaba.fastjson.JSONObject;\n" +
                "import com.usts.backend.pojo.%s;\n" +
                "import com.usts.backend.service.%sService;\n" +
                "import org.springframework.beans.factory.annotation.Autowired;\n" +
                "import org.springframework.web.bind.annotation.GetMapping;\n" +
                "import org.springframework.web.bind.annotation.PostMapping;\n" +
                "import org.springframework.web.bind.annotation.RestController;\n" +
                "\n" +
                "import java.util.Map;\n" +
                "\n" +
                "@RestController\n" +
                "public class %sController {\n" +
                "    @Autowired\n" +
                "    private %sService %sService;\n" +
                "\n" +
                "    @PostMapping(\"/%s/add/\")\n" +
                "    private Map<String, String> add(%s) {\n" +
                "        return %sService.add(%s);\n" +
                "    }\n" +
                "\n" +
                "    @PostMapping(\"/%s/remove/\")\n" +
                "    private Map<String, String> remove(Integer id) {\n" +
                "        return %sService.remove(id);\n" +
                "    }\n" +
                "\n" +
                "    @PostMapping(\"/%s/update/\")\n" +
                "    private Map<String, String> update(%s) {\n" +
                "        return %sService.update(%s);\n" +
                "    }\n" +
                "\n" +
                "    @GetMapping(\"/%s/get/\")\n" +
                "    private JSONObject getList(Integer page, Integer size, %s) {\n" +
                "        return %sService.getList(page, size, %s);\n" +
                "    }\n" +
                "\n" +
                "}\n", packageName, entityName, entityName, entityName, entityName, lowercase, lowercase, combine, lowercase, lowercase, lowercase, lowercase, lowercase, combine, lowercase, lowercase, lowercase, combine, lowercase, lowercase);
        return res;
    }

    private String getViewContent(String entityName, List<String> attributes) {
        String lowercase = entityName.toLowerCase();
        String res = String.format("<template>\n" +
                "  <CardBase>\n" +
                "    <form>\n" +
                "      <div class=\"row mb-3\">\n" +
                "        <label for=\"find\" class=\"col-1 col-form-label\">查询</label>\n" +
                "        <div class=\"col-3\">\n" +
                "          <input v-model=\"findContent.%s\" type=\"text\" class=\"form-control\" id=\"find\">\n" +
                "        </div>\n" +
                "        <div class=\"col-3\">\n" +
                "          <button @click=\"pullPage(1)\" type=\"button\" class=\"btn btn-primary m-1\">查询</button>\n" +
                "          <button type=\"button\" class=\"btn btn-primary m-1\" data-bs-toggle=\"modal\" data-bs-target=\"#add-modal\">新增</button>\n" +
                "          <!-- add-modal -->\n" +
                "          <div class=\"modal fade\" id=\"add-modal\" tabindex=\"-1\">\n" +
                "            <div class=\"modal-dialog modal-lg\">\n" +
                "              <div class=\"modal-content\">\n" +
                "                <div class=\"modal-header\">\n" +
                "                  <h1 class=\"modal-title fs-5\">增加</h1>\n" +
                "                  <button type=\"button\" class=\"btn-close\" data-bs-dismiss=\"modal\" aria-label=\"Close\"></button>\n" +
                "                </div>\n" +
                "                <div class=\"modal-body\">\n" +
                "                  <form>\n" +
                "                    <div class=\"row mb-3\">\n", attributes.get(0));
        for (String attribute: attributes) {
            res += String.format("                      <label for=\"%s\" class=\"col-2 col-form-label\">%s:</label>\n" +
                    "                      <div class=\"col-10\">\n" +
                    "                        <input v-model=\"%sAdd.%s\" type=\"text\" class=\"form-control\" id=\"%s\">\n" +
                    "                      </div>\n", attribute, attribute, lowercase, attribute, attribute);
        }
        res += "                    </div>\n" +
                "                  </form>\n" +
                "                </div>\n" +
                "                <div class=\"modal-footer\">\n" +
                "                  <button @click=\"add\" type=\"button\" class=\"btn btn-primary\">确认</button>\n" +
                "                  <button type=\"button\" class=\"btn btn-secondary\" data-bs-dismiss=\"modal\">关闭</button>\n" +
                "                </div>\n" +
                "              </div>\n" +
                "            </div>\n" +
                "          </div>\n" +
                "\n" +
                "        </div>\n" +
                "      </div>\n" +
                "    </form>\n" +
                "\n" +
                "    <table class=\"table\">\n" +
                "      <thead>\n" +
                "        <tr>\n" +
                "          <th>id</th>\n";
        for (String attribute: attributes) {
            res += String.format("          <th>%s</th>\n", attribute);
        }
        res += String.format("          <th>option</th>\n" +
                "        </tr>\n" +
                "      </thead>\n" +
                "      <tbody>\n" +
                "        <tr v-for=\"%s in %ss\" :key=\"%s.id\">\n" +
                "          <td>{{ %s.id }}</td>\n", lowercase, lowercase, lowercase, lowercase);
        for (String attribute: attributes) {
            res += String.format("          <td>{{ %s.%s }}</td>\n", lowercase, attribute);
        }
        res += String.format("          <td>\n" +
                "            <button @click=\"remove(%s.id)\" type=\"button\" class=\"btn btn-primary btn-sm me-2\">删除</button>\n" +
                "            <button type=\"button\" class=\"btn btn-primary btn-sm\" data-bs-toggle=\"modal\"\n" +
                "              :data-bs-target=\"'#update-modal-' + %s.id\">修改</button>\n" +
                "            <!-- update-modal -->\n" +
                "            <div class=\"modal fade\" :id=\"'update-modal-' + %s.id\" tabindex=\"-1\">\n" +
                "              <div class=\"modal-dialog modal-lg\">\n" +
                "                <div class=\"modal-content\">\n" +
                "                  <div class=\"modal-header\">\n" +
                "                    <h1 class=\"modal-title fs-5\">修改</h1>\n" +
                "                    <button type=\"button\" class=\"btn-close\" data-bs-dismiss=\"modal\" aria-label=\"Close\"></button>\n" +
                "                  </div>\n" +
                "                  <div class=\"modal-body\">\n" +
                "                    <form>\n" +
                "                      <div class=\"row mb-3\">\n", lowercase, lowercase, lowercase);
        for (String attribute: attributes) {
            res += String.format("                        <label for=\"%s\" class=\"col-2 col-form-label\">%s:</label>\n" +
                    "                        <div class=\"col-10\">\n" +
                    "                          <input v-model=\"%s.%s\" type=\"text\" class=\"form-control\" id=\"%s\">\n" +
                    "                        </div>\n", attribute, attribute, lowercase, attribute, attribute);
        }
        res += String.format("                      </div>\n" +
                "                    </form>\n" +
                "                  </div>\n" +
                "                  <div class=\"modal-footer\">\n" +
                "                    <button @click=\"update(%s)\" type=\"button\" class=\"btn btn-primary\">确认</button>\n" +
                "                    <button type=\"button\" class=\"btn btn-secondary\" data-bs-dismiss=\"modal\">关闭</button>\n" +
                "                  </div>\n" +
                "                </div>\n" +
                "              </div>\n" +
                "            </div>\n" +
                "\n" +
                "          </td>\n" +
                "        </tr>\n" +
                "      </tbody>\n" +
                "    </table>\n" +
                "\n" +
                "    <nav aria-label=\"Page navigation example\">\n" +
                "      <ul class=\"pagination justify-content-end\">\n" +
                "        <li class=\"page-item\" @click=\"clickPage(-2)\">\n" +
                "          <a class=\"page-link\" href=\"#\">前一页</a>\n" +
                "        </li>\n" +
                "        <li :class=\"'page-item ' + page.isActive\" v-for=\"page in pages\" :key=\"page.number\"\n" +
                "          @click=\"clickPage(page.number)\">\n" +
                "          <a class=\"page-link\" href=\"#\">{{ page.number }}</a>\n" +
                "        </li>\n" +
                "        <li class=\"page-item\" @click=\"clickPage(-1)\">\n" +
                "          <a class=\"page-link\" href=\"#\">后一页</a>\n" +
                "        </li>\n" +
                "      </ul>\n" +
                "    </nav>\n" +
                "  </CardBase>\n" +
                "</template>\n" +
                "\n" +
                "<script>\n" +
                "import CardBase from '../components/CardBase.vue';\n" +
                "import $ from 'jquery';\n" +
                "import { useStore } from 'vuex';\n" +
                "import { ref, reactive } from 'vue';\n" +
                "import { Modal } from 'bootstrap/dist/js/bootstrap';\n" +
                "\n" +
                "export default {\n" +
                "  name: '%sView',\n" +
                "  components: {\n" +
                "    CardBase,\n" +
                "  },\n" +
                "  setup() {\n" +
                "    const store = useStore();\n" +
                "    let %ss = ref([]);\n" +
                "    let %sAdd = reactive({\n", lowercase, entityName, lowercase, lowercase);
        for (String attribute: attributes) {
            res += String.format("      %s: '',\n", attribute);
        }
        res += String.format("    });\n" +
                "    let pages = ref([]);\n" +
                "    let recordsCount = 0;\n" +
                "    let pageSize = 10;\n" +
                "    let currentPage = 1;\n" +
                "    let findContent = reactive({\n" +
                "      %s: '',\n" +
                "    })\n" +
                "\n" +
                "    const clickPage = page => {\n" +
                "      if (page === -2) page = currentPage - 1;\n" +
                "      else if (page === -1) page = currentPage + 1;\n" +
                "      let maxPage = parseInt(Math.ceil(recordsCount / pageSize));\n" +
                "      if (page >= 1 && page <= maxPage) {\n" +
                "        pullPage(page);\n" +
                "      }\n" +
                "    };\n" +
                "\n" +
                "    const updatePage = () => {\n" +
                "      let maxPage = parseInt(Math.ceil(recordsCount / pageSize));\n" +
                "      let newPages = [];\n" +
                "      for (let i = currentPage - 2; i <= currentPage + 2; i++) {\n" +
                "        if (i >= 1 && i <= maxPage) {\n" +
                "          newPages.push({\n" +
                "            number: i,\n" +
                "            isActive: i === currentPage ? 'active' : ''\n" +
                "          });\n" +
                "        }\n" +
                "      }\n" +
                "      pages.value = newPages;\n" +
                "    };\n" +
                "\n" +
                "    const pullPage = (page) => {\n" +
                "      currentPage = page\n" +
                "      $.ajax({\n" +
                "        url: 'http://127.0.0.1:3000/%s/get/',\n" +
                "        type: 'get',\n" +
                "        data: {\n" +
                "          page: page,\n" +
                "          size: pageSize,\n" +
                "          ...findContent,\n" +
                "        },\n" +
                "        headers: {\n" +
                "          Authorization: 'Bearer ' + store.state.user.token\n" +
                "        },\n" +
                "        success(resp) {\n" +
                "          %ss.value = resp.%ss;\n" +
                "          recordsCount = resp.recordsCount;\n" +
                "          updatePage();\n" +
                "        }\n" +
                "      });\n" +
                "    };\n" +
                "\n" +
                "    pullPage(currentPage);\n" +
                "\n" +
                "    const add = () => {\n" +
                "      $.ajax({\n" +
                "        url: 'http://127.0.0.1:3000/%s/add/',\n" +
                "        type: 'post',\n" +
                "        data: {\n", attributes.get(0), lowercase, lowercase, lowercase, lowercase);
        for (String attribute: attributes) {
            res += String.format("          %s: %sAdd.%s,\n", attribute, lowercase, attribute);
        }
        res += String.format("        },\n" +
                "        headers: {\n" +
                "          Authorization: 'Bearer ' + store.state.user.token\n" +
                "        },\n" +
                "        success(resp) {\n" +
                "          if (resp.errorMessage === 'success') {\n" +
                "            Modal.getInstance(\"#add-modal\").hide();\n" +
                "            pullPage(currentPage);\n" +
                "          }\n" +
                "        }\n" +
                "      });\n" +
                "    };\n" +
                "\n" +
                "    const remove = (id) => {\n" +
                "      $.ajax({\n" +
                "        url: 'http://127.0.0.1:3000/%s/remove/',\n" +
                "        type: 'post',\n" +
                "        data: {\n" +
                "          id: id\n" +
                "        },\n" +
                "        headers: {\n" +
                "          Authorization: 'Bearer ' + store.state.user.token\n" +
                "        },\n" +
                "        success(resp) {\n" +
                "          if (resp.errorMessage === 'success') {\n" +
                "            pullPage(currentPage);\n" +
                "          }\n" +
                "        }\n" +
                "      });\n" +
                "    };\n" +
                "\n" +
                "    const update = (%s) => {\n" +
                "      $.ajax({\n" +
                "        url: 'http://127.0.0.1:3000/%s/update/',\n" +
                "        type: 'post',\n" +
                "        data: {\n" +
                "          id: %s.id,\n", lowercase, lowercase, lowercase, lowercase);
        for (String attribute: attributes) {
            res += String.format("          %s: %s.%s,\n", attribute, lowercase, attribute);
        }
        res += String.format("        },\n" +
                "        headers: {\n" +
                "          Authorization: 'Bearer ' + store.state.user.token\n" +
                "        },\n" +
                "        success(resp) {\n" +
                "          if (resp.errorMessage === 'success') {\n" +
                "            Modal.getInstance('#update-modal-' + %s.id).hide();\n" +
                "            pullPage(currentPage);\n" +
                "          }\n" +
                "        }\n" +
                "      });\n" +
                "    };\n" +
                "\n" +
                "    return {\n" +
                "      %ss,\n" +
                "      %sAdd,\n" +
                "      pages,\n" +
                "      findContent,\n" +
                "      clickPage,\n" +
                "      add,\n" +
                "      remove,\n" +
                "      update,\n" +
                "      pullPage,\n" +
                "    }\n" +
                "  }\n" +
                "}\n" +
                "</script>\n" +
                "\n" +
                "<style scoped>\n" +
                "input {\n" +
                "  margin-bottom: 10px;\n" +
                "}\n" +
                "</style>\n", lowercase, lowercase, lowercase);
        return res;
    }

    private void write(String path, String content) {
        try {
            BufferedWriter out = new BufferedWriter(new FileWriter(path));
            out.write(content);
            out.flush();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
