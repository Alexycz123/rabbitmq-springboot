package com.ycz.service.direct;/*
 @author ycz
 @date 2022-03-03-9:46
*/


import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@RabbitListener(queues = {"duanxin.direct.queue"})
@Service
public class DuanxinDirectService {


    @RabbitHandler
    public void receiveMessage(String message){
        System.out.println("短信收到了消息"+message);
    }


}
