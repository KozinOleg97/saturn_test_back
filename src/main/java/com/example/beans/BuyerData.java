package com.example.beans;

/**
 * DTO для покупателей
 */
public class BuyerData {

    public String name;
    public String surname;
    public String acronym;
    public String phone;

    public Integer apartment_area_min;
    public Integer apartment_area_max;
    public Integer price_max = 0;
    public Boolean exclude_last_first_floor;
    public Long district_id;


}
