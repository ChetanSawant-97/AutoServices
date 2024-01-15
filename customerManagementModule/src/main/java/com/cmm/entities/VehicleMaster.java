package com.cmm.entities;

import jakarta.persistence.*;

@Entity
public class VehicleMaster {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long vehicleId;
    @ManyToOne
    @JoinColumn(name = "userMaster_id")
    private UserMaster userMaster;

    private String make;
    private String model;

    private String makeYear;
    @Column(unique = true)
    private String vehicleIdentificationNumber;

}
