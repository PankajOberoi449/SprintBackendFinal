package com.sprint.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sprint.dto.ShipmentDto;
import com.sprint.services.IShipmentService;




 
@RestController
@RequestMapping("/api/v1/shipments")
@CrossOrigin(origins = "http://localhost:3000")
public class ShipmentController {
 
    @Autowired
    private IShipmentService shipmentService;
 
    @GetMapping
    public ResponseEntity<List<ShipmentDto>> getAllShipments() {

        List<ShipmentDto> shipments = shipmentService.getAllShipments();

        if (shipments.isEmpty()) {

        	System.out.println("chal rha hai");
            return ResponseEntity.noContent().build();

        }

        return ResponseEntity.ok(shipments);

    }

}

 