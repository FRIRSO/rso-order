package si.fri.rso.projekt.order.services.beans;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kumuluz.ee.discovery.annotations.DiscoverService;
import si.fri.rso.projekt.order.services.configuration.AppProperties;
import si.fri.rso.projekt.orders.models.MongoOrder;
import si.fri.rso.projekt.orders.models.Order;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.ProcessingException;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@ApplicationScoped
public class OrderBean {

    private Logger log = Logger.getLogger(OrderBean.class.getName());

    private Client httpClient;

    private ObjectMapper objectMapper;

    @Inject
    private AppProperties appProperties;

    @Inject
    @DiscoverService("rso-buyer")
    private Optional<String> url;

    @PostConstruct
    private void init() {
        httpClient = ClientBuilder.newClient();
        objectMapper = new ObjectMapper();
    }

    public String readConfig() {
        if (appProperties.isExternalServicesEnabled())
            return "ext service enabled!";
        return "ext service disabled";
    }


    public void setConfig(boolean config) {
        appProperties.setExternalServicesEnabled(config);
    }


    public String getMessageDiscovery(){
        if(url.isPresent()) {
            try {
                return httpClient
                        .target(url.get() + "/v1/buyers")
                        .request()
                        .get(String.class);
            }
            catch (WebApplicationException | ProcessingException e) {
                //throw new InternalServerErrorException(e.getMessage());
                return e.getMessage();
            }
        }

        return "baseUrl is not present!";
    }

    public List<Order> getOrders() {
        MongoOrder mb = new MongoOrder();

        return mb.getAllOrders();
    }

    public Order getOrder(Integer orderID) {
        MongoOrder mb = new MongoOrder();

        Order order = mb.getOrder(orderID);

        if(orderID == null) {
            return null;
        }

        return order;
    }
}
