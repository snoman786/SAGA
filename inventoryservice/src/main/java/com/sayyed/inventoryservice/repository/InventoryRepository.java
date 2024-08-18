package com.sayyed.inventoryservice.repository;

import com.sayyed.inventoryservice.entity.*;
import org.springframework.data.jpa.repository.*;

public interface InventoryRepository extends JpaRepository<Inventory,Long> {


    @Query(value = "select count(1) from inventory where product_name = :productName and quantity > :quantity",nativeQuery = true)
    public int validate(String productName,Long quantity);

}
