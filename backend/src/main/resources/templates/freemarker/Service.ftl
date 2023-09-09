package ${packageName}.service;

import com.alibaba.fastjson.JSONObject;
import ${packageName}.pojo.${className};

public interface ${className}Service {
    JSONObject add(${className} ${className?uncap_first});

    JSONObject remove(${firstPropertyType} ${firstPropertyName});

    JSONObject update(${className} ${className?uncap_first});

    JSONObject getList(int page, int size);

    JSONObject find(${firstPropertyType} ${firstPropertyName}, int page, int size);

}
