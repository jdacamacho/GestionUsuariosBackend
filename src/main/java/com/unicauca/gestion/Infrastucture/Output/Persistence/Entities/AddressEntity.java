package com.unicauca.gestion.Infrastucture.Output.Persistence.Entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

@Entity(name = "addresses")
@Data
@AllArgsConstructor
public class AddressEntity {
    
    @Id
    private long idUser;

    @OneToOne
    @MapsId
    @JoinColumn(name = "idUser")
    private StudentEntity objStudent;

    @NotNull(message = "residental address can't be null")
    private String residentalAddress;

    @NotNull(message = "city address can't be null")
    private String city;

    @NotNull(message = "country address can't be null")
    private String country;

    public AddressEntity(){

    }
}
