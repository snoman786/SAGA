package com.sayyed.orderservice.controller;

import com.sayyed.orderservice.entity.*;
import com.sayyed.orderservice.service.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    OrderService orderService;

    // Actually it should be post but fine for now .
    @GetMapping("create/product/{productName}/quantity/{quantity}")
    public ResponseEntity<Order> createOrder(@PathVariable String productName, @PathVariable Long quantity) {
        Order createOrderRequest = new Order(productName, quantity);
        createOrderRequest.setStatus("NEW");
        Order response = orderService.createOrder(createOrderRequest);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
