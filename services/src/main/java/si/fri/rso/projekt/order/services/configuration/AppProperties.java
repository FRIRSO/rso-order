package si.fri.rso.projekt.order.services.configuration;

import com.kumuluz.ee.configuration.cdi.ConfigBundle;
import com.kumuluz.ee.configuration.cdi.ConfigValue;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped

//dev->1.0->config->app-properties->external-services-> enabled : true / false
@ConfigBundle("app-properties")
public class AppProperties {
    @ConfigValue(value = "external-services.enabled", watch = true)
    private boolean externalServicesEnabled;


    public boolean isExternalServicesEnabled() {
        return externalServicesEnabled;
    }

    public void setExternalServicesEnabled(boolean externalServicesEnabled) {
        this.externalServicesEnabled = externalServicesEnabled;
    }
}
