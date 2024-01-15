package com.cmm.services.impl;

import com.cmm.dto.UserProfileDetails;
import com.cmm.entities.AddressMaster;
import com.cmm.entities.UserMaster;
import com.cmm.repositories.UserMasterRepo;
import com.cmm.services.UserService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class UserServicesImpl implements UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserServicesImpl.class);



    @Autowired
    private UserMasterRepo userMasterRepo;

    public UserDetailsService userDetailsService(){
        return new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
                return userMasterRepo.findByEmail(username);
            }
        };
    }

    public UserProfileDetails getUserMasterDetails(String username){
        UserProfileDetails userProfileDetails = UserProfileDetails.builder().build();
        try {
            UserMaster userMaster = userMasterRepo.findByEmail(username);
            userProfileDetails = UserProfileDetails.builder()
                    .firstName(userMaster.getFirstName())
                    .lastName(userMaster.getLastName())
                    .addressList(userMaster.getAddressMaster())
                    .userName(userMaster.getEmail())
                    .gender(userMaster.getGender()).build();

        }catch (Exception e){
            e.printStackTrace();
        }
        return userProfileDetails;
    }

    public String updateUserMasterDetails(UserProfileDetails userProfileDetails){
        try {
            UserMaster userMaster = userMasterRepo.findByEmail(userProfileDetails.getUserName());

            List<AddressMaster> filteredList = userProfileDetails.getAddressList().stream()
                    .filter(address -> !address.getStreet1().isBlank() && !address.getStreet2().isBlank())
                    .collect(Collectors.toList());

            userMaster.setFirstName(userProfileDetails.getFirstName());
            userMaster.setLastName(userProfileDetails.getLastName());
            userMaster.setGender(userProfileDetails.getGender());
            userMaster.setEmail(userProfileDetails.getNewUserName());

            // Create a new ArrayList from the filtered list
            userMaster.setAddressMaster(new ArrayList<>(filteredList));

            userMasterRepo.saveAndFlush(userMaster);

        } catch (Exception e) {
            logger.error("Error updating user profile details", e);
            // Handle the exception appropriately, e.g., return an error message
            return "Error updating user profile details";
        }
        return "Success";
    }

}
