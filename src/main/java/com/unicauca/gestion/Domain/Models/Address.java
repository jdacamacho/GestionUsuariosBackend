package com.unicauca.gestion.Domain.Models;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Address {
    private long idUser;
    private Student objStudent;
    private String residentalAddress;
    private String city;
    private String country;

    public Address(){

    }
}
