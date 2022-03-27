package com.ycz.mapper;/*
 @author ycz
 @date 2022-03-04-14:11
*/

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ycz.entity.Order;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OrderMapper extends BaseMapper<Order> {


}
