package com.ycz.service.direct;/*
 @author ycz
 @date 2022-03-03-9:45
*/


import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@RabbitListener(queues = {"sms.direct.queue"})
@Service
public class SmsDirectService {

    @RabbitHandler
    public void receiveMessage(String message){
        System.out.println("sms收到了消息"+message);
    }
}
