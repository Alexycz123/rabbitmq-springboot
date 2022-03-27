package com.ycz.service.fanout;/*
 @author ycz
 @date 2022-03-03-9:45
*/

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@RabbitListener(queues = {"weixin.fanout.queue"})
@Service
public class WeixinService {


    @RabbitHandler
    public void receiveMessage(String message){
        System.out.println("weixin收到了消息::::::"+message);
    }
}
