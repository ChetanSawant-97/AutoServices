package com.cmm.controllers;

import com.cmm.dto.servicesdto.ServiceRequestDetailsDTO;
import com.cmm.dto.servicesdto.ServiceResponseDto;
import com.cmm.services.impl.CustomerServiceDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("api/v1/customerServiceDet")
public class CustomerServiceDetController {
    @Autowired
    private CustomerServiceDetails customerServiceDetails;
    @GetMapping("getServices")
    public ResponseEntity<List<ServiceResponseDto>> getCustomerServiceDetails(){
        return ResponseEntity.ok().body(customerServiceDetails.getRequestedServices());
    }

    @PostMapping("requestService")
    public ResponseEntity<List<ServiceResponseDto>> requestService(@RequestBody ServiceRequestDetailsDTO customerServiceDetDto){
        return ResponseEntity.ok().body(customerServiceDetails.requestService(customerServiceDetDto));
    }


}
