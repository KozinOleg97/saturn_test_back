package com.example.services;

import com.example.beans.DealsData;


import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import java.util.List;


/**
 * Endpoints для отчёта
 */
@Path("api/v1/deals")
@Consumes("application/json")
@Produces("application/json")
public class DealsSearch {

    @Inject
    EntityManager em;

    @GET
    @Path("")
    public Response getDeals() {

        String sql = """
                select b.name                  as buyer_name,
                                     b.surname               as buyer_surname,
                                     b.acronym               as buyer_acronym,
                                     b.phone                 as buyer_phone,
                                     b.price_max             as price_max,
                              
                                     s.name                  as seller_name,
                                     s.surname               as seller_surname,
                                     s.acronym               as seller_acronym,
                                     s.phone                 as seller_phone,
                                     s.price                 as price,
                              
                                     d.name                  as district_name,
                                     (b.price_max - s.price) as profit
                              
                              from seller s
                                       left join district d
                                                 on s.district_id = d.id
                                       left outer join buyer b
                                                       on s.district_id = b.district_id
                              where b.price_max >= s.price
                                and b.apartment_area_min <= s.apartment_area
                                and b.apartment_area_max >= s.apartment_area
                                and (
                                      (b.exclude_last_first_floor = false)
                                      or (
                                              (s.apartment_floor <> 1
                                                  and b.exclude_last_first_floor = true)
                                              and
                                              (s.apartment_floor <> s.floors_number
                                                  and b.exclude_last_first_floor = true)
                                          ))
                """;


        Query query = em.createNativeQuery(sql, "DealsMap");


        List<DealsData> dealsDataList = query.getResultList();


        return Response.ok(dealsDataList).build();
    }



}
