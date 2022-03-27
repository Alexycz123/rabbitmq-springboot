package com.ycz.service.fanout;/*
 @author ycz
 @date 2022-03-03-9:46
*/


import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@RabbitListener(queues = {"duanxin.fanout.queue"})
@Service
public class DuanxinService {


    @RabbitHandler
    public void receiveMessage(String message){
        System.out.println("短信收到了消息"+message);
    }


}
