package com.example.entity;

import com.example.beans.DealsData;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.persistence.*;



@Entity
@JsonIdentityInfo(
        generator = ObjectIdGenerators.IntSequenceGenerator.class, property = "@id"

)
public class Seller extends PanacheEntity {
    public String name;
    public String surname;
    public String acronym;
    public String phone;

    public Integer floors_number;
    public Integer apartment_floor;
    public Integer apartment_area;
    public Integer price;


    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn
    public District district;
}
