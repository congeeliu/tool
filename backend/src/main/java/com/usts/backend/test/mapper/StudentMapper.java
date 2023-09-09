package com.usts.backend.test.mapper;

import com.usts.backend.test.pojo.Student;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface StudentMapper {
    void add(Student student);

    void remove(Integer id);

    void update(Student student);

    List<Student> getList(@Param("currIndex")int currIndex, @Param("pageSize")int pageSize);

    List<Student> find(@Param("id")Integer id, @Param("currIndex")int currIndex, @Param("pageSize")int pageSize);

    int getCount();

    int getCountOfFind(Integer id);

}
