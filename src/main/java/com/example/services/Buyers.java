package com.example.services;


import com.example.beans.BuyerData;
import com.example.beans.SellerData;
import com.example.entity.*;
import org.jboss.logging.Logger;

import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;

/**
 * Endpoints для покупателей
 */
@Path("api/v1/buyers")
@Consumes("application/json")
@Produces("application/json")
public class Buyers {

    private static final Logger LOGGER = Logger.getLogger(Buyers.class.getName());

    @POST
    @Path("")
    @Transactional
    public Response addBuyer(BuyerData buyerData) {

        Buyer buyer = new Buyer();

        update(buyerData, buyer);

        buyer.persistAndFlush();

        return Response.ok(buyer.id).build();
    }


    @PUT
    @Path("/{id}")
    @Transactional
    public Response updateBuyer(BuyerData buyerData, @PathParam("id") Long id) {

        Buyer buyer = Buyer.findById(id);
        if (buyer == null) {
            return Response.status(500).build();
        }

        update(buyerData, buyer);

        buyer.persistAndFlush();

        return Response.ok().build();
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public Response deleteBuyer(@PathParam("id") long id){
        Buyer buyer = Buyer.findById(id);
        if (buyer == null) {
            return Response.status(500).build();
        }

        buyer.delete();
        return Response.ok().build();
    }


    @GET
    @Path("/{id}")
    public Response getBuyer(@PathParam("id") long id){

        Buyer buyer = Buyer.findById(id);

        BuyerData buyerData = new BuyerData();

        filBuyerData(buyerData, buyer);

        return Response.ok(buyerData).build();
    }

    @GET
    @Path("")
    public Response getBuyerList() {

        return Response.ok(Buyer.listAll()).build();
    }


    private void filBuyerData(BuyerData buyerData, Buyer buyer) {
        buyerData.name = buyer.name;
        buyerData.surname = buyer.surname;
        buyerData.acronym = buyer.acronym;
        buyerData.phone = buyer.phone;
        buyerData.exclude_last_first_floor = buyer.exclude_last_first_floor;
        buyerData.apartment_area_max = buyer.apartment_area_max;
        buyerData.apartment_area_min = buyer.apartment_area_min;
        buyerData.price_max = buyer.price_max;

        buyerData.district_id = buyer.district.id;
    }

    private void update(BuyerData buyerData, Buyer buyer) {
        buyer.name = buyerData.name;
        buyer.surname = buyerData.surname;
        buyer.acronym = buyerData.acronym;
        buyer.phone = buyerData.phone;
        buyer.exclude_last_first_floor = buyerData.exclude_last_first_floor;
        buyer.apartment_area_max = buyerData.apartment_area_max;
        buyer.apartment_area_min = buyerData.apartment_area_min;
        buyer.price_max = buyerData.price_max;

        buyer.district = District.findById(buyerData.district_id);
    }

}
