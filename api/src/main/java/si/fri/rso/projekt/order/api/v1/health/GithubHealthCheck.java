package si.fri.rso.projekt.order.api.v1.health;

import org.eclipse.microprofile.health.Health;
import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;

import javax.enterprise.context.ApplicationScoped;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.logging.Logger;

@Health
@ApplicationScoped
public class GithubHealthCheck implements HealthCheck {

    private static final String url = "https://github.com/kumuluz/kumuluzee";

    private static final Logger LOG = Logger.getLogger(GithubHealthCheck.class.getSimpleName());

    @Override
    public HealthCheckResponse call() {
        try {

            HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
            connection.setRequestMethod("HEAD");

            if (connection.getResponseCode() == 200) {
                return HealthCheckResponse.named(GithubHealthCheck.class.getSimpleName()).up().build();
            }
        } catch (Exception exception) {
            LOG.severe(exception.getMessage());
        }
        return HealthCheckResponse.named(GithubHealthCheck.class.getSimpleName()).down().build();
    }
}
