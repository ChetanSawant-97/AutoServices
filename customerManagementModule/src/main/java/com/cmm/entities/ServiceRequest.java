package com.cmm.entities;

import jakarta.persistence.*;

import java.sql.Timestamp;
import java.util.List;

public class ServiceRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long serviceRequestId;

    @ManyToOne
    @JoinColumn(name = "vehicle_id")
    private VehicleMaster vehicleMaster;

    @OneToMany

    private List<ServiceMaster> servicesList;

    private Timestamp serviceRequestDate;

    private String serviceStatus;

    private String deliveryDate;

    private String serviceDescription;

    private long transactionId;

}
