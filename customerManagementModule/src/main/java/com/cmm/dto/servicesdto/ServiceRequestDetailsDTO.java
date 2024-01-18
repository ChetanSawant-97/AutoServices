package com.cmm.dto.servicesdto;

import com.cmm.entities.ServiceMaster;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ServiceRequestDetailsDTO {

    private List<ServiceMaster> servicesList;
    private List<byte[]> serviceRequestImgPaths;
    private String serviceDescription;

}
