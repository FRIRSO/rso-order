package si.fri.rso.projekt.orders.services.beans;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;

@RequestScoped
public class OrdersBean {

    private Client httpClient;

    //@Inject
    //private AppProperties appProperties;

    //@Inject
    //private OrdersBean buyersBean;

    //@Inject
    //@DiscoverService("rso-orderes")
    //private Optional<String> baseUrl;

    @PostConstruct
    private void init() {
        httpClient = ClientBuilder.newClient();
    }
}
