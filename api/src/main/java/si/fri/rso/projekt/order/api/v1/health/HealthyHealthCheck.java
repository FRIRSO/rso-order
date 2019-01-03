package si.fri.rso.projekt.order.api.v1.health;

import org.eclipse.microprofile.health.Health;
import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;
import si.fri.rso.projekt.order.api.v1.configuration.RestProperties;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@Health
@ApplicationScoped

public class HealthyHealthCheck implements HealthCheck {

    @Inject
    RestProperties restProperties;

    @Override
    public HealthCheckResponse call() {
        if (restProperties.isHealthy()) {
            return HealthCheckResponse.named(HealthyHealthCheck.class.getSimpleName()).up().build();
        }
        return HealthCheckResponse.named(HealthyHealthCheck.class.getSimpleName()).down().build();
    }
}