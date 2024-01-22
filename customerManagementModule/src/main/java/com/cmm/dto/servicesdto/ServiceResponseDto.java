package com.cmm.dto.servicesdto;

import com.cmm.entities.ServiceMaster;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ServiceResponseDto {
    private String serviceRequestTitle;
    private List<ServiceMaster> servicesList;
    private List<ServiceImg> serviceImgs;
    private String serviceStatus;
    private String serviceDescription;
    private String serviceRequestDateAndTime;




}
