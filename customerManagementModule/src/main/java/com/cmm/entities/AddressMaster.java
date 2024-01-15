package com.cmm.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AddressMaster {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long addressId;
    private String street1;
    private String street2;
    private String city;
    private String district;
    private String state;
    private String zipcode;
    private String typeOfAddress;

    public static void main(String[] args) {

    }
}
