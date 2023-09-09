package ${packageName}.mapper;

import ${packageName}.pojo.${className};
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface ${className}Mapper {
    void add(${className} ${className?uncap_first});

    void remove(${firstPropertyType} ${firstPropertyName});

    void update(${className} ${className?uncap_first});

    List<${className}> getList(@Param("currIndex")int currIndex, @Param("pageSize")int pageSize);

    List<${className}> find(@Param("${firstPropertyName}")${firstPropertyType} ${firstPropertyName}, @Param("currIndex")int currIndex, @Param("pageSize")int pageSize);

    int getCount();

    int getCountOfFind(${firstPropertyType} ${firstPropertyName});

}
