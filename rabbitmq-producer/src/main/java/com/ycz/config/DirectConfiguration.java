package com.ycz.config;/*
 @author ycz
 @date 2022-03-03-10:04
*/

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DirectConfiguration {


    @Bean
    public DirectExchange directExchange(){
        return new DirectExchange("direct_order_exchange",true,false);
    }

    @Bean
    public Queue smsDirectQueue(){
        return new Queue("sms.direct.queue",true);
    }
    @Bean
    public Queue weixinDirectQueue(){
        return new Queue("weixin.direct.queue",true);
    }
    @Bean
    public Queue duanxinDirectQueue(){
        return new Queue("duanxin.direct.queue",true);
    }


    //完成绑定
    @Bean
    public Binding smsDirectBingding(){
        return BindingBuilder.bind(smsDirectQueue()).to(directExchange()).with("sms");
    }
    @Bean
    public Binding weixinDirectBingding(){
        return BindingBuilder.bind(weixinDirectQueue()).to(directExchange()).with("weixin");
    }
    @Bean
    public Binding duanxinDirectBingding(){
        return BindingBuilder.bind(duanxinDirectQueue()).to(directExchange()).with("duanxin");
    }



}
