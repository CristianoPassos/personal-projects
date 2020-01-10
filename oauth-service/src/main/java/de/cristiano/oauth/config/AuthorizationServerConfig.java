package de.cristiano.oauth.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.builders.InMemoryClientDetailsServiceBuilder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;

import java.util.List;

@Configuration
@RequiredArgsConstructor
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

    private final AuthenticationManager authenticationManager;

    private final OAuth2ClientConfig clientConfig;

    private final TokenStore tokenStore;

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) {
        endpoints
                .authenticationManager(authenticationManager)
                .tokenStore(tokenStore)
        ;
    }

    @Override
    public void configure(final ClientDetailsServiceConfigurer clients) throws Exception {
        final InMemoryClientDetailsServiceBuilder builder = clients.inMemory();

        clientConfig.getClients().forEach(client -> {
            final List<String> authorizedGrantTypes = client.getAuthorizedGrantTypes();
            builder.withClient(client.getClientId())
                    .authorizedGrantTypes(authorizedGrantTypes.toArray(new String[authorizedGrantTypes.size()]))
                    .scopes(client.getScope())
                    .accessTokenValiditySeconds(client.getAccessTokenValidity())
                    .refreshTokenValiditySeconds(client.getRefreshTokenValidity())
                    .secret(client.getClientSecret())
            ;
        });

    }

}
