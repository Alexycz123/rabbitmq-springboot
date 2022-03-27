package com.ycz.service;/*
 @author ycz
 @date 2022-03-04-14:11
*/

import com.baomidou.mybatisplus.extension.service.IService;
import com.ycz.entity.Order;
import com.ycz.mapper.OrderMapper;

public interface OrderService extends IService<Order>

{
    void saveAndPost();


    void saveOrderAndMessage();


}
