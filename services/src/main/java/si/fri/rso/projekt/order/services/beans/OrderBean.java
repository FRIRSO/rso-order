package si.fri.rso.projekt.order.services.beans;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kumuluz.ee.discovery.annotations.DiscoverService;
import si.fri.rso.projekt.order.services.configuration.AppProperties;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.ProcessingException;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
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
    @DiscoverService("rso-order")
    private Optional<String> baseurl;

    @PostConstruct
    private void init() {
        httpClient = ClientBuilder.newClient();
        objectMapper = new ObjectMapper();
    }

    public String getMessage() {
        return "Hello from module ORDER!";
    }

    public String readConfig() {
        if(appProperties.isExternalServicesEnabled())
            return "ext service enabled!";
        return "ext service disabled";
    }

    public void setConfig(boolean config) {
        appProperties.setExternalServicesEnabled(config);
    }

    public String getMessageDiscovery(){
        if(baseurl.isPresent()) {
            try {
                return httpClient
                        .target(baseurl.get() + "/v1/drugaTest/url2")
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
}
