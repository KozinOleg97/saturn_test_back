package com.example.entity;

import com.example.beans.DealsData;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.persistence.*;

/**
 * Анотация для мапинга в DealsData, не относиться к entity продавца
 */
@SqlResultSetMapping(name = "DealsMap", classes = {
        @ConstructorResult(targetClass = DealsData.class,
                columns = {
                        @ColumnResult(name = "buyer_name", type = String.class),
                        @ColumnResult(name = "buyer_surname", type = String.class),
                        @ColumnResult(name = "buyer_acronym", type = String.class),
                        @ColumnResult(name = "buyer_phone", type = String.class),
                        @ColumnResult(name = "price_max", type =Integer.class ),

                        @ColumnResult(name = "seller_name", type = String.class),
                        @ColumnResult(name = "seller_surname", type = String.class),
                        @ColumnResult(name = "seller_acronym", type = String.class),
                        @ColumnResult(name = "seller_phone", type = String.class),
                        @ColumnResult(name = "price", type = Integer.class),

                        @ColumnResult(name = "district_name", type = String.class),
                        @ColumnResult(name = "profit", type = Integer.class),
        })
})
@Entity
@JsonIdentityInfo(
        generator = ObjectIdGenerators.IntSequenceGenerator.class, property = "@id"

)
public class Buyer extends PanacheEntity {
    public String name;
    public String surname;
    public String acronym;
    public String phone;


    public Integer apartment_area_min;
    public Integer apartment_area_max;
    public Integer price_max = 0;
    public Boolean exclude_last_first_floor = false;


    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn
    public District district;
}
