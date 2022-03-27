package com.ycz.mapper;/*
 @author ycz
 @date 2022-03-06-0:09
*/

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ycz.entity.Message;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MessageMapper extends BaseMapper<Message> {
}
