package com.cmm.dto;

import com.cmm.entities.AddressMaster;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserProfileDetails {
    private String firstName;
    private String lastName;
    private String userName;
    private String newUserName;
    private String password;
    private String gender;
    private List<AddressMaster> addressList;
}
