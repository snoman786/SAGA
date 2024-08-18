package com.sayyed.inventoryservice.message;

import com.sayyed.inventoryservice.entity.*;
import com.sayyed.inventoryservice.service.*;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

import static com.sayyed.inventoryservice.Constant.CREATE_ORDER_QUEUE;

@Component
public class MessageConsumer {

    @Autowired
    InventoryService inventoryService;

    @Autowired
    MessageProducer messageProducer;

    @RabbitListener(queues = CREATE_ORDER_QUEUE)
    public void receivecreateOrderMessage(Order order)
    {
        // Handle the received message here
        System.out.println("Received Create Order message: " + order.toString());
        boolean doesProductExists =  inventoryService.validateInventory(order.getProductName(),order.getQuantity());
         // if false compensate order service else move a head and do next operation .
        if(!doesProductExists) {
            // Inventory is not available so we need to call compensating transaction.
            messageProducer.sendNoInventoryMsg(order);
        }else{
            System.out.println("Since Inventory exists so order can be done ");
            messageProducer.sendAvailableInventoryMsg(order);

        }
    }
}
