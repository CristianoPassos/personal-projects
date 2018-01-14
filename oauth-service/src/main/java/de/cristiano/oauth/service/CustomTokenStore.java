package de.cristiano.oauth.service;

import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2RefreshToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

import javax.sql.DataSource;
import java.util.Collection;

public class CustomTokenStore implements TokenStore {

    private final RedisTokenStore cacheAccessTokenStore;

    private final JdbcTokenStore jdbcRefreshTokenStore;

    public CustomTokenStore(final DataSource dataSource, final RedisConnectionFactory connectionFactory) {
        this.cacheAccessTokenStore = new RedisTokenStore(connectionFactory);
        this.jdbcRefreshTokenStore = new JdbcTokenStore(dataSource);
    }

    @Override
    public Collection<OAuth2AccessToken> findTokensByClientId(final String clientId) {
        return cacheAccessTokenStore.findTokensByClientId(clientId);
    }

    @Override
    public Collection<OAuth2AccessToken> findTokensByClientIdAndUserName(final String clientId, final String userName) {
        return cacheAccessTokenStore.findTokensByClientIdAndUserName(clientId, userName);
    }

    @Override
    public OAuth2AccessToken getAccessToken(final OAuth2Authentication authentication) {
        return cacheAccessTokenStore.getAccessToken(authentication);
    }

    @Override
    public OAuth2AccessToken readAccessToken(String tokenValue) {
        return cacheAccessTokenStore.readAccessToken(tokenValue);
    }

    @Override
    public OAuth2Authentication readAuthentication(final OAuth2AccessToken token) {
        return jdbcRefreshTokenStore.readAuthentication(token);
    }

    @Override
    public OAuth2Authentication readAuthentication(final String token) {
        return jdbcRefreshTokenStore.readAuthentication(token);
    }

    @Override
    public OAuth2Authentication readAuthenticationForRefreshToken(final OAuth2RefreshToken token) {
        return jdbcRefreshTokenStore.readAuthenticationForRefreshToken(token);
    }

    @Override
    public OAuth2RefreshToken readRefreshToken(final String tokenValue) {
        return jdbcRefreshTokenStore.readRefreshToken(tokenValue);
    }

    @Override
    public void removeAccessToken(OAuth2AccessToken token) {
        cacheAccessTokenStore.removeAccessToken(token);
    }

    @Override
    public void removeAccessTokenUsingRefreshToken(final OAuth2RefreshToken refreshToken) {
        cacheAccessTokenStore.removeAccessTokenUsingRefreshToken(refreshToken);
    }

    @Override
    public void removeRefreshToken(final OAuth2RefreshToken token) {
        jdbcRefreshTokenStore.removeRefreshToken(token);
    }

    @Override
    public void storeAccessToken(OAuth2AccessToken token, OAuth2Authentication authentication) {
        cacheAccessTokenStore.storeAccessToken(token, authentication);
    }

    @Override
    public void storeRefreshToken(final OAuth2RefreshToken refreshToken, final OAuth2Authentication authentication) {
        jdbcRefreshTokenStore.storeRefreshToken(refreshToken, authentication);
    }

}
