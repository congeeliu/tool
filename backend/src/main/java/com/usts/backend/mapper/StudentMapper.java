package com.usts.backend.mapper;

//import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.usts.backend.pojo.Student;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

//@Mapper
//public interface StudentMapper extends BaseMapper<Student> {
//}

@Mapper
@Repository
public interface StudentMapper {
    void add(Student student);

    void remove(Integer id);

    void update(Student student);

    List<Student> getList(@Param("currIndex")int currIndex, @Param("pageSize")int pageSize);

    List<Student> find(@Param("currIndex")int currIndex, @Param("pageSize")int pageSize, String name);

    int getCount();

    int getCountOfFind(String name);

}
