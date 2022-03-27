package com.ycz.service;/*
 @author ycz
 @date 2022-03-03-9:22
*/

import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class OrderService {


    @Autowired
    private RabbitTemplate rabbitTemplate;


    public void makeOrder(String userid,String productid,int num){

        String orderId = UUID.randomUUID().toString();

        //      交换机，路由key/queue队列名称，消息内容
        String  exchangeName = "fanout_order_exchange";
        String  routingKey = "";
        rabbitTemplate.convertAndSend(exchangeName,routingKey,orderId);
    }


    public void makeOrder2(String userid,String productid,int num){

        String orderId = UUID.randomUUID().toString();

        //      交换机，路由key/queue队列名称，消息内容
        String  exchangeName = "direct_order_exchange";
        rabbitTemplate.convertAndSend(exchangeName,"sms",orderId);
        rabbitTemplate.convertAndSend(exchangeName,"weixin",orderId);
    }

    public void makeOrder3(String userid,String productid,int num){

        String orderId = UUID.randomUUID().toString();

        //      交换机，路由key/queue队列名称，消息内容
        String  exchangeName = "ttl_order_exchange";
        rabbitTemplate.convertAndSend(exchangeName,"ttl",orderId);
    }

    public void makeOrderTTlMessage(String userid,String productid,int num){

        String orderId = UUID.randomUUID().toString();

        //      交换机，路由key/queue队列名称，消息内容
        String  exchangeName = "ttl_order_exchange";
        String routkey = "ttlmessage";

        MessagePostProcessor messagePostProcessor=new MessagePostProcessor() {
            @Override
            public Message postProcessMessage(Message message) throws AmqpException {

                message.getMessageProperties().setExpiration("500");
                message.getMessageProperties().setContentEncoding("UTF-8");
                return message;
            }
        };
        rabbitTemplate.convertAndSend(exchangeName,routkey,orderId,messagePostProcessor);
    }

}
