package com.ycz.rabbit;/*
 @author ycz
 @date 2022-03-06-1:14
*/

import com.alibaba.fastjson.JSONObject;
import com.rabbitmq.client.Channel;
import com.ycz.eneity.Disribution;
import com.ycz.eneity.Order;
import com.ycz.service.DisributionService;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Date;


@Component
public class MessageController {

    @Autowired
    private DisributionService disributionService;

    @RabbitListener(queues = {"ttl.order.direct.queue"})
    @RabbitHandler
    public void getOrder(
            String msg
            , Channel channel,
            @Header(AmqpHeaders.DELIVERY_TAG) long tag
            ) throws IOException {
        try {
            Order order = JSONObject.parseObject(msg, Order.class);
            System.out.println("order接收成功：：：：" + order);
            Integer orderId = order.getId();
            //之后保存运单
            Disribution disribution=new Disribution();
            disribution.setOrderId(orderId);
            disribution.setCreateTime(new Date());
            disribution.setRiderId(2);
            disributionService.save(disribution);

            System.out.println( 1 / 0);
            channel.basicAck(tag,false);

        }catch (Exception e){
            System.out.println("人工干预");

            //消息退回,不重发
            channel.basicNack(tag , false , false);
        }



    }


}
