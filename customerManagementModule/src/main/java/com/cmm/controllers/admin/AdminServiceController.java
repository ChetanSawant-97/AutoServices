package com.cmm.controllers.admin;

import com.cmm.dto.servicesdto.ServiceDetailsForAdmin;
import com.cmm.services.impl.admin.AdminServiceManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/adminService")
public class AdminServiceController {

    @Autowired
    private AdminServiceManager adminServiceManager;

    @GetMapping("/getServices")
    public ResponseEntity<List<ServiceDetailsForAdmin>> getGetAllServiceList(@RequestParam String fromDate,
                                                                             @RequestParam String toDate,
                                                                             @RequestParam String status,
                                                                             @RequestParam String customerName) {
        return ResponseEntity.ok().body(adminServiceManager.serviceDetailsForAdminList(fromDate,toDate,status, customerName));
    }

}
