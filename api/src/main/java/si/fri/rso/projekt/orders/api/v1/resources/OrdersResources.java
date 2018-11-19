package si.fri.rso.projekt.orders.api.v1.resources;


import si.fri.rso.projekt.orders.services.beans.OrdersBean;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@RequestScoped
@Path("/buyer")
public class OrdersResources {

    @Inject
    private OrdersBean buyersBean;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public Response getOrders() {
        return Response.ok(buyersBean.getOrders()).build();
    }
}
