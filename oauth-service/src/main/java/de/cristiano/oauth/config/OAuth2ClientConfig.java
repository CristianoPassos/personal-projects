package de.cristiano.oauth.config;
import de.cristiano.oauth.domain.OAuthClientDetails;
import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Getter
@Configuration
@ConfigurationProperties(prefix = "oauth")
public class OAuth2ClientConfig {
    private List<OAuthClientDetails> clients = new ArrayList<>();

}
