package com.example.beans;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.persistence.ConstructorResult;
import javax.persistence.Entity;
import javax.persistence.SqlResultSetMapping;

/**
 * DTO для отчёта по сделкам. Конструктор для мапинга из NativeQuery
 */
public class DealsData {


    public String buyer_name;
    public String buyer_surname;
    public String buyer_acronym;
    public String buyer_phone;

    public Integer price_max;


    public String seller_name;
    public String seller_surname;
    public String seller_acronym;
    public String seller_phone;

    public Integer price;


    public String district_name;

    public Integer profit;

    public DealsData(String buyer_name, String buyer_surname, String buyer_acronym, String buyer_phone, Integer price_max, String seller_name, String seller_surname, String seller_acronym, String seller_phone, Integer price, String district_name, Integer profit) {
        this.buyer_name = buyer_name;
        this.buyer_surname = buyer_surname;
        this.buyer_acronym = buyer_acronym;
        this.buyer_phone = buyer_phone;
        this.price_max = price_max;
        this.seller_name = seller_name;
        this.seller_surname = seller_surname;
        this.seller_acronym = seller_acronym;
        this.seller_phone = seller_phone;
        this.price = price;
        this.district_name = district_name;
        this.profit = profit;
    }
}

