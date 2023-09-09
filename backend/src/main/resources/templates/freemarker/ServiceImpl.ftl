package ${packageName}.service.impl;

import com.alibaba.fastjson.JSONObject;
import ${packageName}.mapper.${className}Mapper;
import ${packageName}.pojo.${className};
import ${packageName}.service.${className}Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ${className}ServiceImpl implements ${className}Service {
    @Autowired
    private ${className}Mapper ${className?uncap_first}Mapper;

    @Override
    public JSONObject add(${className} ${className?uncap_first}) {
        ${className?uncap_first}Mapper.add(${className?uncap_first});
        JSONObject resp = new JSONObject();
        resp.put("errorMessage", "success");
        return resp;
    }

    @Override
    public JSONObject remove(${firstPropertyType} ${firstPropertyName}) {
        ${className?uncap_first}Mapper.remove(id);
        JSONObject resp = new JSONObject();
        resp.put("errorMessage", "success");
        return resp;
    }

    @Override
    public JSONObject update(${className} ${className?uncap_first}) {
        ${className?uncap_first}Mapper.update(${className?uncap_first});
        JSONObject resp = new JSONObject();
        resp.put("errorMessage", "success");
        return resp;
    }

    @Override
    public JSONObject getList(int page, int size) {
        int curIndex = (page - 1) * size;
        List<${className}> ${className?uncap_first}s = ${className?uncap_first}Mapper.getList(curIndex, size);
        JSONObject resp = new JSONObject();
        resp.put("${className?uncap_first}s", ${className?uncap_first}s);
        resp.put("recordsCount", ${className?uncap_first}Mapper.getCount());
        return resp;
    }

    @Override
    public JSONObject find(${firstPropertyType} ${firstPropertyName}, int page, int size) {
        int curIndex = (page - 1) * size;
        List<${className}> ${className?uncap_first}s = ${className?uncap_first}Mapper.find(${firstPropertyName}, curIndex, size);
        int cnt = ${className?uncap_first}Mapper.getCountOfFind(${firstPropertyName});
        JSONObject resp = new JSONObject();
        resp.put("${className?uncap_first}s", ${className?uncap_first}s);
        resp.put("recordsCount", cnt);
        return resp;
    }

}
