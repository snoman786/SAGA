package com.sayyed.orderservice.message;

import com.sayyed.orderservice.entity.*;
import com.sayyed.orderservice.service.*;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

import static com.sayyed.orderservice.Constant.*;
import static com.sayyed.orderservice.Constant.NO_INVENTORY_ORDER_QUEUE;

@Component
public class MessageConsumer {

    @Autowired
    OrderService orderService;

    @RabbitListener(queues = NO_INVENTORY_ORDER_QUEUE)
    public void receiveNoInventoryMessage(Order order)
    {
        // Handle the received message here
        System.out.println("Received No Inventory Message for order : " + order.toString());
        // Update Order with proper status .
        orderService.updateOrderStatus(order,"NO_INVENTORY");

    }

    @RabbitListener(queues = AVAILABLE_INVENTORY_ORDER_QUEUE)
    public void receiveAvailableInventoryMessage(Order order)
    {
        // Handle the received message here
        System.out.println("Received Available Inventory Message for order : " + order.toString());
        // Update Order with proper status .
        orderService.updateOrderStatus(order,"SUCCESS");

    }
}
