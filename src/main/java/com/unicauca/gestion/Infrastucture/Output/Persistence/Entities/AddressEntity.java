package com.unicauca.gestion.Infrastucture.Output.Persistence.Entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;

@Entity
@Table(name = "addresses")
@Data
@AllArgsConstructor
public class AddressEntity {
    
    @Id
    private long idUser;

    @OneToOne
    @MapsId
    @JoinColumn(name = "idUser")
    private StudentEntity objStudent;

    private String residentalAddress;
    private String city;
    private String country;

    public AddressEntity(){

    }
}
