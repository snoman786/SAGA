package com.sayyed.orderservice.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.support.converter.*;
import org.springframework.context.annotation.*;

import static com.sayyed.orderservice.Constant.*;

// TODO : This kind of configuration classes are common to all services so a common project needs to get created and added as dependency here .
@Configuration
public class RabbitMQConfig {

    @Bean
    public Exchange purchaseExchange() {
        return ExchangeBuilder.directExchange(PURCHASE_EXCHANGE).build();
    }


    @Bean public Queue createOrderQueue()
    {
        return new Queue(CREATE_ORDER_QUEUE, false);
    }

    @Bean
    public Queue noInventoryQueue(){
        return new Queue(NO_INVENTORY_ORDER_QUEUE,false);
    }

    @Bean
    public MessageConverter converter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public Queue availableInventoryQueue(){
        return new Queue(AVAILABLE_INVENTORY_ORDER_QUEUE,false);
    }

    @Bean
    public Binding createOrderBinding()
    {
        return BindingBuilder.bind(createOrderQueue())
                .to(purchaseExchange())
                .with(CREATE_ORDER_ROUTING_KEY)
                .noargs();
    }

    @Bean
    public Binding noInventoryBinding()
    {
        return BindingBuilder.bind(noInventoryQueue())
                .to(purchaseExchange())
                .with(NO_INVENTORY_ROUTING_KEY)
                .noargs();
    }

    @Bean
    public Binding availableInventoryBinding()
    {
        return BindingBuilder.bind(availableInventoryQueue())
                .to(purchaseExchange())
                .with(AVAILABLE_INVENTORY_ROUTING_KEY)
                .noargs();
    }
}
