package com.cmm.services.impl;

import com.cmm.dto.UserProfileDetails;
import com.cmm.dto.servicesdto.ServiceImg;
import com.cmm.dto.servicesdto.ServiceRequestDetailsDTO;
import com.cmm.dto.servicesdto.ServiceResponseDto;
import com.cmm.entities.ServiceMaster;
import com.cmm.entities.ServiceRequest;
import com.cmm.entities.UserMaster;
import com.cmm.repositories.CustomerServiceDetRepo;
import com.cmm.repositories.ServiceMasterRepo;
import com.cmm.repositories.UserMasterRepo;
import com.cmm.repositories.UserRoleRepo;
import com.cmm.utilities.CommonUtils;
import com.cmm.utilities.Constants;
import jakarta.transaction.Transactional;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Service
public class CustomerServiceDetails {

    @Autowired
    private ServiceMasterRepo serviceMasterRepo;
    @Autowired
    private CustomerServiceDetRepo customerServiceDetRepo;
    @Autowired
    private UserRoleRepo userRoleRepo;
    @Autowired
    private UserMasterRepo userMasterRepo;

    public List<ServiceResponseDto> getRequestedServices(){
        UserMaster userMaster =(UserMaster) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        List<ServiceRequest> customerServiceDetList = customerServiceDetList = customerServiceDetRepo.findByUserMasterId(userMaster.getCustomerId());

        return customerServiceDetList.stream()
                .map(CustomerServiceDetails::mapToServiceRespDto)
                .collect(Collectors.toList());
    }

    @Transactional(rollbackOn = Exception.class)
    public List<ServiceResponseDto> requestService(ServiceRequestDetailsDTO serviceRequestDetailsDTO){

        UserMaster userMaster =(UserMaster) SecurityContextHolder.getContext().getAuthentication().getPrincipal();


        long currentTimeMillis = System.currentTimeMillis();
        Timestamp currentTimestamp = new Timestamp(currentTimeMillis);
        List<String> imgPaths = saveImgAndGetFilesPathList(serviceRequestDetailsDTO.getServiceDescImages());

        List<ServiceMaster> serviceRequested =  serviceRequestDetailsDTO.getSelectedServices()
                                                .stream()
                                                .map(serviceDto ->
                                                        serviceMasterRepo.findById(serviceDto.getServiceId())
                                                                .orElseGet(ServiceMaster::new)
                                                )
                                                .toList();

        ServiceRequest serviceRequest = ServiceRequest.builder()
                .userMaster(userMaster)
                .requestTitle(serviceRequestDetailsDTO.getServiceRequestTitle())
                .serviceMasters(serviceRequested)
                .serviceStatus(Constants.STATUS_REQUESTED)
                .serviceRequestDate(currentTimestamp)
                .serviceRequestImgPaths(imgPaths)
                .isPickupAndDropRequested(StringUtils.equals(serviceRequestDetailsDTO.getIsPickupAndDropRequested(), "true"))
                .serviceDescription(serviceRequestDetailsDTO.getServiceDescription())
                .prickupDateAndTime(serviceRequestDetailsDTO.getPickupDateAndTime().isPresent()?serviceRequestDetailsDTO.getPickupDateAndTime().get():null).build();

        customerServiceDetRepo.saveAndFlush(serviceRequest);

        return getRequestedServices();
    }

    public static ServiceResponseDto mapToServiceRespDto(ServiceRequest serviceRequest){

        List<ServiceImg> serviceImg = new ArrayList<>();
        List<ServiceMaster> serviceMastersList = new ArrayList<>();

        try{

            serviceImg = serviceRequest.getServiceRequestImgPaths().isEmpty() ?
                            new ArrayList<>() : serviceRequest.getServiceRequestImgPaths()
                                                .stream()
                                                .map(serviceImgPath ->{
                                                    return ServiceImg.builder()
                                                            .base64String(getImageBase64String(serviceImgPath)).build();
                                                }).toList();

            serviceMastersList = serviceRequest.getServiceMasters()
                    .stream()
                    .map(serviceMaster -> {
                        return ServiceMaster.builder()
                                .serviceName(serviceMaster.getServiceName())
                                .serviceId(serviceMaster.getServiceId()).build();
                    }).toList();

        }catch (Exception e){
            e.printStackTrace();
        }
        return ServiceResponseDto.builder()
                .serviceRequestTitle(serviceRequest.getRequestTitle())
                .servicesList(serviceMastersList)
                .serviceDescription(serviceRequest.getServiceDescription())
                .serviceStatus(serviceRequest.getServiceStatus())
                .serviceImgs(serviceImg)
                .serviceRequestDateAndTime( CommonUtils.getResponseDateAndTime(serviceRequest.getServiceRequestDate()))
                .build();
    }

    public static List<String> saveImgAndGetFilesPathList(List<ServiceImg> serviceImg){
        List<String> serviceImgPaths = new ArrayList<>();

        UserDetails userDetails =(UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        String userName = userDetails.getUsername();
        String currentDate = LocalDate.now().toString();

        String baseDirPath = "";
        String os = System.getProperty("os.name").toLowerCase();

        if (os.contains("win")) {
            baseDirPath = "D:\\usr\\local\\serviceImg\\";
        } else if (os.contains("nix") || os.contains("nux") || os.contains("mac")) {
            baseDirPath = "/usr/local/serviceImg";
        } else {
            baseDirPath = "/usr/local/serviceImg";
        }

        String finalBaseDirPath = baseDirPath;
        AtomicInteger count = new AtomicInteger();

        if(serviceImg!=null){
            serviceImgPaths = serviceImg.stream().
                    map(imageBase-> {
                        String filePath = finalBaseDirPath+ userName + File.separator + currentDate +File.separator +"ServiceImage_0"+ count+".jpg";
                        count.getAndIncrement();
                        return CommonUtils.saveBase64Image(imageBase.getBase64String(),filePath);
                    })
                    .collect(Collectors.toList());
        }
        return serviceImgPaths;
    }

    public static String getImageBase64String(String imagePath) {
        try {
            Path path = Path.of(imagePath);
            byte[] imageBytes = Files.readAllBytes(path);
            return Base64.getEncoder().encodeToString(imageBytes);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
