package com.ycz.config;/*
 @author ycz
 @date 2022-03-03-11:14
*/

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class DirectTTLConfiguration {


    @Bean
    public DirectExchange directExchangettl(){
        return new DirectExchange("ttl_order_exchange",true,false);
    }

    @Bean
    public Queue ttlQueue(){
        Map<String,Object> args=new HashMap<>();
        args.put("x-message-ttl",5000);
        return new Queue("ttl.direct.quere",true,false,false,args);
    }

    @Bean
    public Binding ttlBinding(){
        return  BindingBuilder.bind(ttlQueue()).to(directExchangettl()).with("ttl");
    }
}
