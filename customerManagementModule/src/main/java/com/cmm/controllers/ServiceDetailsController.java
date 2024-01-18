package com.cmm.controllers;

import com.cmm.entities.ServiceMaster;
import com.cmm.entities.ServiceRequest;
import com.cmm.services.impl.ServicesManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/service")
public class ServiceDetailsController {
    @Autowired
    private ServicesManager servicesManager;
    @PostMapping("requestService")
    public ResponseEntity<String> requestService(@RequestBody ServiceRequest serviceRequest){
        return ResponseEntity.ok().body("Requested");
    }


    @PostMapping("createService")
    public ResponseEntity<List<ServiceMaster>> createNewService(@RequestBody ServiceMaster serviceMasterDto){
        List<ServiceMaster> serviceMasterList = servicesManager.saveOrUpdateService(serviceMasterDto);
        return ResponseEntity.ok().body(serviceMasterList);
    }

    @GetMapping("serviceMaster")
    public ResponseEntity<List<ServiceMaster>> getServiceMasterList(){
        List<ServiceMaster> serviceMasterList =servicesManager.getServicesList();
        return ResponseEntity.ok().body(serviceMasterList);
    }

    @DeleteMapping("deleteService")
    public ResponseEntity<List<ServiceMaster>> deleteService(@RequestParam Long serviceMasterId){
        return ResponseEntity.ok().body(servicesManager.deleteService(serviceMasterId));
    }

}
