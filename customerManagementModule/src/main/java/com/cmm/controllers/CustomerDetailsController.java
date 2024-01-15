package com.cmm.controllers;

import com.cmm.dto.UserProfileDetails;
import com.cmm.entities.UserMaster;
import com.cmm.services.impl.UserServicesImpl;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/user")
@RequiredArgsConstructor
public class CustomerDetailsController {
    @Autowired
    private UserServicesImpl userServices;

    @GetMapping("/profile")
    public ResponseEntity<UserProfileDetails> getProfileDetails(@RequestParam String username){
        return ResponseEntity.ok(userServices.getUserMasterDetails(username));
    }

    @PostMapping("/updateProfile")
    public ResponseEntity<String> updateProfileDetails(@RequestBody UserProfileDetails userProfileDetails){
        ResponseEntity<String> responseEntity;
        String resp = userServices.updateUserMasterDetails(userProfileDetails);
        if(StringUtils.isNotBlank(resp)){
            responseEntity =ResponseEntity.ok().build();
        }else {
            responseEntity =ResponseEntity.badRequest().build();
        }
        return responseEntity;
    }
}
