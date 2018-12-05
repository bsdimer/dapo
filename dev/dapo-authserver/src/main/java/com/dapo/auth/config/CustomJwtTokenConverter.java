package com.dapo.auth.config;

import com.dapo.common.authentication.StandardUser;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

/**
 * Created by dimer on 18.03.17.
 */
public class CustomJwtTokenConverter extends JwtAccessTokenConverter {


    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken accessToken,
                                     OAuth2Authentication authentication) {
        StandardUser user = (StandardUser) authentication.getPrincipal();

        ((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(user.getDetails());

        return super.enhance(accessToken, authentication);
    }
}
