package com.sayyed.orderservice.service;

import com.sayyed.orderservice.entity.*;
import com.sayyed.orderservice.message.*;
import com.sayyed.orderservice.repository.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

import java.util.*;

@Service
public class OrderService {

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    MessageProducer messageProducer;

    public Order createOrder(Order request){
        Order order =  orderRepository.save(request);
        messageProducer.sendCreateOrderMsg(order);
        return order;
    }

    public void updateOrderStatus(Order order,String status){
      Optional<Order> orderOptional =  orderRepository.findById(order.getId());
      orderOptional.ifPresent(value -> {
          value.setStatus(status);
          orderRepository.save(value);
      });

    }
}
