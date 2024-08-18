package com.sayyed.orderservice.message;

import com.sayyed.orderservice.entity.*;
import org.springframework.amqp.rabbit.core.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

import static com.sayyed.orderservice.Constant.CREATE_ORDER_ROUTING_KEY;
import static com.sayyed.orderservice.Constant.PURCHASE_EXCHANGE;

@Component
public class MessageProducer {

    @Autowired
    RabbitTemplate rabbitTemplate;

    public void sendCreateOrderMsg(Order order){
        rabbitTemplate.convertAndSend(PURCHASE_EXCHANGE, CREATE_ORDER_ROUTING_KEY, order);

    }
}
