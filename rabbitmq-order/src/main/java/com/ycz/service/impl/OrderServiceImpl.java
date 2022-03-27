package com.ycz.service.impl;/*
 @author ycz
 @date 2022-03-04-14:12
*/

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.rabbitmq.tools.json.JSONUtil;
import com.sun.org.apache.regexp.internal.RE;
import com.ycz.entity.Message;
import com.ycz.entity.Order;
import com.ycz.mapper.MessageMapper;
import com.ycz.mapper.OrderMapper;
import com.ycz.service.OrderService;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import java.util.Date;

@Service
public class OrderServiceImpl extends
        ServiceImpl<OrderMapper, Order> implements OrderService {
    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private MessageMapper messageMapper;

    @Autowired
    private RabbitTemplate rabbitTemplate;



    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveAndPost() {
        Order order=new Order();
        order.setNum(110);
        order.setConsumerId(2);
        order.setProductId(3);
        order.setTotalPrice(150.0);
        order.setCreateTime(new Date());
        this.save(order);
        ResponseEntity<Order> orderResponseEntity = restTemplate.postForEntity("http://localhost:8080/dis/getOrder", order, Order.class);
        System.out.println(orderResponseEntity);
    }

    @PostConstruct
    public void regCallback(){
        rabbitTemplate.setConfirmCallback(new RabbitTemplate.ConfirmCallback() {
            @Override
            public void confirm(CorrelationData correlationData, boolean ack, String cause) {
                System.out.println("correlationData:::" + correlationData);

                System.out.println("cause:::::"  +  cause);

                System.out.println("ack::::" + ack);

                String orderId = correlationData.getId();

                if (!ack){
                    System.out.println("Mq队列应答失败：orderId " + orderId);
                    return;
                }
                try {
                    //这就是应答成功了，更新状态
                    Message message=new Message();
                    message.setStatus(1);
                    UpdateWrapper<Message> updateWrapper=new UpdateWrapper<>();
                    updateWrapper.eq("order_id" , Integer.parseInt(orderId));
                    int update = messageMapper.update(message  , updateWrapper );
                    if (update==1){
                        System.out.println("消息状态修改成功");
                    }
                }catch (Exception e){
                    System.out.println("本地消息修改失败，出现异常" + e.getMessage());
                }
            }
        });
    }

    @Override
    public void saveOrderAndMessage() {

        Order order=new Order();
        order.setNum(110);
        order.setConsumerId(2);
        order.setProductId(3);
        order.setTotalPrice(150.0);
        order.setCreateTime(new Date());

        boolean save = this.save(order);
        if (save) {
            Message message=new Message();
            message.setOrderId(order.getId());
            message.setCreateTime(new Date());
            message.setStatus(0);
            messageMapper.insert(message);
        }



        //保存之后，发消息到rabbitmq
        String exchangeName = "order_direct_exchange";
        String routKey = "order";
        rabbitTemplate.convertAndSend(exchangeName , routKey, JSONObject.toJSONString(order),
                new CorrelationData(order.getId().toString()) );
        //发消息 ，， 等之后确认发消息是否成功，如果成功将修改message中的status属性




    }
}
