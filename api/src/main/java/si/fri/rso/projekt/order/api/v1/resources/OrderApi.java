package si.fri.rso.projekt.order.api.v1.resources;

import com.kumuluz.ee.logs.cdi.Log;
import com.kumuluz.ee.logs.cdi.LogParams;
import org.eclipse.microprofile.metrics.Counter;
import org.eclipse.microprofile.metrics.annotation.Metered;
import org.eclipse.microprofile.metrics.annotation.Metric;
import org.eclipse.microprofile.metrics.annotation.Timed;
import si.fri.rso.projekt.order.api.v1.configuration.RestProperties;
import si.fri.rso.projekt.order.services.beans.OrderBean;
import si.fri.rso.projekt.order.models.Order;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@RequestScoped
@Log
@Path("orders")
public class OrderApi {

    @Inject
    private OrderBean orderBean;

    @Inject
    private RestProperties restProperties;

    //@Inject
    //@Metric(name = "order_counter")
    //private Counter orderCounter;

    //@GET
    //@Path("url")
    //public Response test() {
    //    return Response.status(Response.Status.OK).entity(orderBean.getMessageDiscovery()).build();
    //
    //}

    @GET
    @Path("url2")
    public Response test2() {
        return Response.status(Response.Status.OK).entity(orderBean.getMessageDiscovery2()).build();

    }

    @GET
    @Path("service")
    @Produces(MediaType.APPLICATION_JSON)
    @Metered(name = "service_meter")
    public Response service() {
        return Response.status(Response.Status.OK).entity(orderBean.readConfig()).build();
    }

    @GET
    @Path("healthy")
    public Response serviceHealthy() {
        return Response.status(Response.Status.OK).entity(restProperties.isHealthy()).build();
    }

    @GET
    @Path("healthyFalse")
    public Response serviceHealthyFalse() {
        restProperties.setHealthy(false);
        return Response.status(Response.Status.OK).entity("OK").build();
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


    @GET
    @Timed(name = "get_orders_timer")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getOrders() {
        return Response.ok(orderBean.getOrders()).build();
    }

    @GET
    @Path("/{orderID}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getOrdersbyID(@PathParam("orderID") Integer orderID) {
        Order order = orderBean.getOrder(orderID);

        if(order != null) {
            return Response.ok(order).build();
        }
        else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
}
