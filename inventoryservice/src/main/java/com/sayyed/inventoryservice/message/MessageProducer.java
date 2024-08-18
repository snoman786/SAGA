package com.sayyed.inventoryservice.message;

import com.sayyed.inventoryservice.entity.*;
import org.springframework.amqp.rabbit.core.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

import static com.sayyed.inventoryservice.Constant.NO_INVENTORY_ROUTING_KEY;
import static com.sayyed.inventoryservice.Constant.PURCHASE_EXCHANGE;
import static com.sayyed.inventoryservice.Constant.*;

@Component
public class MessageProducer {

    @Autowired
    RabbitTemplate rabbitTemplate;

    public void sendNoInventoryMsg(Order order){
        rabbitTemplate.convertAndSend(PURCHASE_EXCHANGE, NO_INVENTORY_ROUTING_KEY, order);

    }

    public  void sendAvailableInventoryMsg(Order order){
        rabbitTemplate.convertAndSend(PURCHASE_EXCHANGE, AVAILABLE_INVENTORY_ROUTING_KEY, order);
    }
}
