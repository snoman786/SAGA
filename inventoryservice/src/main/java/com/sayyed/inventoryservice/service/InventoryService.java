package com.sayyed.inventoryservice.service;

import com.sayyed.inventoryservice.repository.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

@Service
public class InventoryService {

    @Autowired
    InventoryRepository inventoryRepository;

    public boolean validateInventory(String prodName,Long quantity){

        int count = inventoryRepository.validate(prodName,quantity);

        System.out.println("Count is : " +count);

        return count > 0 ? true : false ;
    }
}
