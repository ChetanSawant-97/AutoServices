package com.cmm.dto.servicesdto;

import com.cmm.dto.servicesdto.serviceResponseChild.ServiceMaster;

import java.util.List;

public class ServiceDetailsForAdmin {


    private String customerName;
    private String requestedDate;

    private String status;
    private String expectedDelivery;

    private List<ServiceMaster> servicesList;

}
