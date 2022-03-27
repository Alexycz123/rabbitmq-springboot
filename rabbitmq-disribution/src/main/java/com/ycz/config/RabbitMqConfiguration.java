package com.ycz.config;/*
 @author ycz
 @date 2022-03-06-0:15
*/

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.RabbitListenerContainerFactory;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class RabbitMqConfiguration {

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



    @Bean
    public DirectExchange exchange(){
        return new DirectExchange("order_direct_exchange",true,false);
    }

    @Bean
    public Queue queue(){
        Map<String,Object> args=new HashMap<>();
        args.put("x-dead-letter-exchange" , "dead_direct_exchange" );
        args.put("x-dead-letter-routing-key" , "dead");
        //死信队列
        return new Queue("ttl.order.direct.queue",true,false,false,args);
    }

    @Bean
    public Binding binding(){
        return BindingBuilder.bind(queue()).to(exchange()).with("order");
    }


    @Bean
    public MessageConverter messageConverter(){
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public SimpleRabbitListenerContainerFactory rabbitListenerContainerFactory(ConnectionFactory connectionFactory) {
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        factory.setAcknowledgeMode(AcknowledgeMode.MANUAL);
        factory.setConnectionFactory(connectionFactory);
        factory.setMessageConverter(new Jackson2JsonMessageConverter());
        return factory;
    }

}
