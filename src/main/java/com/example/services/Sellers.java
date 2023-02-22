package com.example.services;

import com.example.beans.SellerData;
import com.example.entity.District;
import com.example.entity.Seller;
import org.jboss.logging.Logger;

import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;


/**
 * Endpoints для продавцов
 */
@Path("api/v1/sellers")
@Consumes("application/json")
@Produces("application/json")
public class Sellers {

    private static final Logger LOGGER = Logger.getLogger(Sellers.class.getName());

    @POST
    @Path("")
    @Transactional
    public Response addSeller(SellerData sellerData) {

        Seller seller = new Seller();

        update(sellerData, seller);

        seller.persistAndFlush();

        return Response.ok(seller.id).build();
    }


    @PUT
    @Path("/{id}")
    @Transactional
    public Response updateSeller(SellerData sellerData, @PathParam("id") long id) {

        Seller seller = Seller.findById(id);
        if (seller == null) {
            return Response.status(500).build();
        }

        update(sellerData, seller);

        seller.persistAndFlush();

        return Response.ok().build();
    }


    @DELETE
    @Path("/{id}")
    @Transactional
    public Response deleteSeller(@PathParam("id") long id){
        Seller seller = Seller.findById(id);
        if (seller == null) {
            return Response.status(500).build();
        }

        seller.delete();
        return Response.ok().build();
    }

    @GET
    @Path("/{id}")
    public Response getSeller(@PathParam("id") long id){

        Seller seller = Seller.findById(id);

        SellerData sellerData = new SellerData();

        filSellerData(sellerData, seller);

        return Response.ok(sellerData).build();
    }



    @GET
    @Path("")
    public Response getSellerList() {

        return Response.ok(Seller.listAll()).build();
    }


    private void filSellerData(SellerData sellerData, Seller seller) {
        sellerData.name = seller.name;
        sellerData.surname = seller.surname;
        sellerData.acronym = seller.acronym;
        sellerData.phone = seller.phone;
        sellerData.apartment_area = seller.apartment_area;
        sellerData.apartment_floor = seller.apartment_floor;
        sellerData.price = seller.price;
        sellerData.floors_number = seller.floors_number;

        sellerData.district_id = seller.district.id;
    }


    private void update(SellerData sellerData, Seller seller) {
        seller.name = sellerData.name;
        seller.surname = sellerData.surname;
        seller.acronym = sellerData.acronym;
        seller.phone = sellerData.phone;
        seller.apartment_area = sellerData.apartment_area;
        seller.apartment_floor = sellerData.apartment_floor;
        seller.price = sellerData.price;
        seller.floors_number = sellerData.floors_number;

        seller.district = District.findById(sellerData.district_id);
    }
}
