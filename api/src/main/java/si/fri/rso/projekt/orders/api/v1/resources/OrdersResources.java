package si.fri.rso.projekt.orders.api.v1.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/buyer")
public class OrdersResources {

    //@Inject
    //private OrdersBean buyersBean;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public Response getOrders() {
        return Response.ok("Dela").build();
    }
}
