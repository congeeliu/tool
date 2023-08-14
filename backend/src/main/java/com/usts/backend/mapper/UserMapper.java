package com.usts.backend.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.usts.backend.pojo.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<User> {
}
