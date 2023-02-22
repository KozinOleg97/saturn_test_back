package com.example.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import io.quarkus.hibernate.orm.panache.PanacheEntity;
import io.smallrye.common.constraint.NotNull;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;

@Entity
@JsonIdentityInfo(
        generator = ObjectIdGenerators.IntSequenceGenerator.class, property = "@id"

)
public class District extends PanacheEntity {
    @NotNull
    public String name;
    public Float gps_latitude;
    public Float gps_longitude;

    @OneToOne(mappedBy = "district", fetch = FetchType.LAZY)
    @JsonIgnore
    public Buyer buyer;

    @OneToOne(mappedBy = "district",fetch = FetchType.LAZY)
    @JsonIgnore
    public Seller seller;

}
