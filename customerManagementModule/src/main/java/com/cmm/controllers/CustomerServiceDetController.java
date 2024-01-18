package com.cmm.controllers;

import com.cmm.dto.CustomerServiceDetDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/customerServiceDet")
public class CustomerServiceDetController {
    public ResponseEntity<List<CustomerServiceDetDto>> getCustomerServiceDetails(){
        return ResponseEntity.ok().body(new ArrayList<>());
    }
}
