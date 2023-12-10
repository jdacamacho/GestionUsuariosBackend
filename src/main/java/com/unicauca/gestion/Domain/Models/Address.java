package com.unicauca.gestion.Domain.Models;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Address {
    private long idAddress;
    private String residentalAddress;
    private String city;
    private String country;

    public Address(){

    }
}
