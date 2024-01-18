package com.cmm.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ServiceMaster {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long serviceId;

    private String serviceName;
    private String description;
    private String serviceRate;
}
