package com.cmm.services.impl;

import com.cmm.entities.ServiceMaster;
import com.cmm.repositories.ServiceMasterRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ServicesManager {

    @Autowired
    private ServiceMasterRepo serviceMasterRepo;

    public List<ServiceMaster> deleteService(Long serviceMasterId){
        Optional<ServiceMaster> serviceMaster = serviceMasterRepo.findById(serviceMasterId);
        serviceMaster.ifPresent(master -> serviceMasterRepo.delete(master));
        return getServicesList();
    }

    public List<ServiceMaster> getServicesList(){
        List<ServiceMaster> serviceMastersList = serviceMasterRepo.findAll();
        return serviceMastersList.size()>0? serviceMastersList : new ArrayList<>();
    }

    public List<ServiceMaster> saveOrUpdateService(ServiceMaster serviceMaster) {
        List<ServiceMaster> serviceMastersList = new ArrayList<>();
        try{
            serviceMasterRepo.saveAndFlush(serviceMaster);
            serviceMastersList = getServicesList();
        }catch (Exception e){
            e.printStackTrace();
        }

        return serviceMastersList;
    }
}
