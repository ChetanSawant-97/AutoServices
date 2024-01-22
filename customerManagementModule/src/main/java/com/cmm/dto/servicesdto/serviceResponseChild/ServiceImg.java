package com.cmm.dto.servicesdto.serviceResponseChild;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ServiceImg {
    private String base64Data;
}
