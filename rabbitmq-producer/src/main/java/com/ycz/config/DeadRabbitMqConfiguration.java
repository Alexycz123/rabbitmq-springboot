package com.ycz.config;/*
 @author ycz
 @date 2022-03-04-10:18
*/

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DeadRabbitMqConfiguration {

    @Bean
    public DirectExchange directDeadExchange(){
        return new DirectExchange("dead_direct_exchange",true,false);
    }

    @Bean
    public Queue deadQueue(){
        return new Queue("dead.direct.queue",true);
    }

    @Bean
    public Binding deadBind(){
        return BindingBuilder.bind(deadQueue()).to(directDeadExchange()).with("dead");
    }



}
