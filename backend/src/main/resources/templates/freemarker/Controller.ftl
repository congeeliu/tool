package ${packageName}.controller;

import com.alibaba.fastjson.JSONObject;
import ${packageName}.pojo.${className};
import ${packageName}.service.${className}Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ${className}Controller {
    @Autowired
    private ${className}Service ${className?uncap_first}Service;

    @PostMapping("/${className?uncap_first}/add/")
    private JSONObject add(${className} ${className?uncap_first}) {
        return ${className?uncap_first}Service.add(${className?uncap_first});
    }

    @PostMapping("/${className?uncap_first}/remove/")
    private JSONObject remove(${firstPropertyType} ${firstPropertyName}) {
        return ${className?uncap_first}Service.remove(${firstPropertyName});
    }

    @PostMapping("/${className?uncap_first}/update/")
    private JSONObject update(${className} ${className?uncap_first}) {
        return ${className?uncap_first}Service.update(${className?uncap_first});
    }

    @GetMapping("/${className?uncap_first}/get/")
        private JSONObject getList(int page, int size) {
        return ${className?uncap_first}Service.getList(page, size);
    }

    @GetMapping("/${className?uncap_first}/find/")
    private JSONObject find(${firstPropertyType} ${firstPropertyName}, int page, int size) {
        return ${className?uncap_first}Service.find(${firstPropertyName}, page, size);
    }

}
