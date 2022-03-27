package com.ycz.service.fanout;/*
 @author ycz
 @date 2022-03-03-9:45
*/


import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@RabbitListener(queues = {"sms.fanout.queue"})
@Service
public class SmsService {

    @RabbitHandler
    public void receiveMessage(String message){
        System.out.println("sms收到了消息"+message);
    }
}
