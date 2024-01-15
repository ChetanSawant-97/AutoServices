package com.cmm.repositories;

import com.cmm.entities.UserMaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMasterRepo extends JpaRepository<UserMaster, Long> {
    public UserMaster findByEmail(String username);
}
