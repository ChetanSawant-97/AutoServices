package com.cmm.repositories;

import com.cmm.entities.ServiceRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerServiceDetRepo extends JpaRepository<ServiceRequest, Long> {

    @Query("SELECT e FROM ServiceRequest e WHERE e.userMaster.customerId = :userId")
    List<ServiceRequest> findByUserMasterId(@Param("userId") Long userId);
}
