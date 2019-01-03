package si.fri.rso.projekt.order.api.v1.configuration;

import com.kumuluz.ee.configuration.cdi.ConfigBundle;
import com.kumuluz.ee.configuration.cdi.ConfigValue;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped

//dev->1.0->config->rest-properties->healthy-> enabled : true / false
@ConfigBundle("rest-properties")
public class RestProperties {
    @ConfigValue(value = "healthy.enabled", watch = true)
    private boolean healthy;

    public boolean isHealthy() {
        return healthy;
    }

    public void setHealthy(boolean healthy) {
        this.healthy = healthy;
    }
}
