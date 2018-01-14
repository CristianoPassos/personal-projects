package de.cristiano.oauth.domain;
import lombok.Data;

import java.util.List;

@Data
public class OAuthClientDetails {
    private String clientId;

    private String clientSecret;

    private String scope;

    private List<String> authorizedGrantTypes;

    private String authorities;

    private Integer accessTokenValidity;

    private Integer refreshTokenValidity;

}
