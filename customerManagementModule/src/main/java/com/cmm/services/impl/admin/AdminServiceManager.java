package com.cmm.services.impl.admin;

import com.cmm.dto.servicesdto.ServiceDetailsForAdmin;
import com.cmm.repositories.CustomerServiceDetRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AdminServiceManager {
    @Autowired
    private CustomerServiceDetRepo customerServiceDetRepo;


    public List<ServiceDetailsForAdmin> serviceDetailsForAdminList(String fromDate, String toDate, String status, String customerName ){


        return new ArrayList<>();
    }
}
