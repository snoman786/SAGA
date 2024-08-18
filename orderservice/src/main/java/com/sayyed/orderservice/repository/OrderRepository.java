package com.sayyed.orderservice.repository;

import com.sayyed.orderservice.entity.*;
import org.springframework.data.jpa.repository.*;

public interface OrderRepository extends JpaRepository<Order,Long> {



}
