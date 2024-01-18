package com.cmm.repositories;

import com.cmm.entities.ServiceMaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServiceMasterRepo extends JpaRepository<ServiceMaster, Long> {

}
