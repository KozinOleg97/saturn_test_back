package com.example.services;

import com.example.beans.DistrictData;
import com.example.entity.Buyer;
import com.example.entity.District;
import io.quarkus.panache.common.Sort;
import org.jboss.logging.Logger;

import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;

/**
 * Endpoints для районов
 */
@Path("api/v1/districts")
@Consumes("application/json")
@Produces("application/json")
public class Districts {
    private static final Logger LOGGER = Logger.getLogger(Districts.class.getName());


    @POST
    @Path("")
    @Transactional
    public Response addNew(DistrictData districtData) {

        District district = new District();

        update(districtData, district);

        district.persistAndFlush();

        return Response.ok(district.id).build();
    }

    @PUT
    @Path("/{id}")
    @Transactional
    public Response updateDistrict(DistrictData districtData, @PathParam("id") long id) {

        District district = District.findById(id);
        if (district == null) {
            return Response.status(500).build();
        }

        update(districtData, district);

        district.persistAndFlush();

        return Response.ok().build();
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public Response deleteDistrict(@PathParam("id") long id){
        District district = District.findById(id);
        if (district == null) {
            return Response.status(500).build();
        }

        district.delete();
        return Response.ok().build();
    }


    @GET
    @Path("/{id}")
    public Response getDistrict(@PathParam("id") long id){

        District district = District.findById(id);

        return Response.ok(district).build();
    }

    @GET
    @Path("")
    public Response getDistrictList(){
        return Response.ok(District.listAll(Sort.ascending("name"))).build();
    }

    private void update(DistrictData districtData, District district) {
        district.name = districtData.name;
        district.gps_latitude = districtData.gps_latitude;
        district.gps_longitude = districtData.gps_longitude;
    }

}


