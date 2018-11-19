package si.fri.rso.projekt.order.api.v1.resources;

import si.fri.rso.projekt.order.services.beans.OrderBean;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;

@RequestScoped
@Path("order")
public class OrderApi {

    @Inject
    private OrderBean orderBean;

    @GET
    @Produces("text/plain")
    public String hello() {
        return "Hello from kumuluze. its working!";
    }

    @GET
    @Path("discovery")
    public Response disc() {

        String returnMsg = orderBean.getMessage();
        return Response.status(Response.Status.OK).entity(returnMsg).build();
    }

    @GET
    @Path("url")
    public Response test() {
        String response = orderBean.getMessageDiscovery();
        return Response.status(Response.Status.OK).entity(response).build();

    }

    @GET
    @Path("service")
    public Response service() {
        return Response.status(Response.Status.OK).entity(orderBean.readConfig()).build();
    }

    @GET
    @Path("disable")
    public Response test4() {
        orderBean.setConfig(false);
        String response = "OK";
        return Response.status(Response.Status.OK).entity(response).build();
    }

    @GET
    @Path("enable")
    public Response test5() {
        orderBean.setConfig(true);
        String response = "OK";
        return Response.status(Response.Status.OK).entity(response).build();
    }
}
