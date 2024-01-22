package com.cmm.dto.servicesdto.serviceResponseChild;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ServiceMaster{
    private Long serviceId;
    private String serviceName;
}