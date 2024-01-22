package com.cmm.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class ServiceRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long serviceRequestId;

    private String requestTitle;

    @ManyToOne
    @JoinColumn(name = "vehicle_id")
    private VehicleMaster vehicleMaster;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "service_request_id")
    private List<ServiceMaster> serviceMasters = new ArrayList<>();

    @Temporal(TemporalType.TIMESTAMP)
    private Timestamp serviceRequestDate;

    private String serviceStatus;

    private boolean isPickupAndDropRequested;


    @Temporal(TemporalType.TIMESTAMP)
    private Timestamp prickupDateAndTime;

    @ElementCollection
    @CollectionTable(name = "service_request_img_paths", joinColumns = @JoinColumn(name = "service_request_id"))
    @Column(name = "img_path")
    private List<String> serviceRequestImgPaths;

    @Temporal(TemporalType.TIMESTAMP)
    private Timestamp deliveryDate;

    private String serviceDescription;

    private long transactionId;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private UserMaster userMaster;

}
