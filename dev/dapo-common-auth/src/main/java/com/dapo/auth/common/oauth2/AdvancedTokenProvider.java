package com.dapo.auth.common.oauth2;

import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by dimomass on 23.02.19.
 */
public class AdvancedTokenProvider {

    private String tokenSecret;

    private static final Logger logger = LoggerFactory.getLogger(AdvancedTokenProvider.class);

    public AdvancedTokenProvider(String tokenSecret) {
        this.tokenSecret = tokenSecret;
    }

    public Long getUserIdFromToken(String token) {
        return Long.parseLong(getUserPrincipalClaimsFromToken(token).getSubject());
    }

    public Claims getUserPrincipalClaimsFromToken(String token){
       return Jwts.parser()
                .setSigningKey(tokenSecret)
                .parseClaimsJws(token)
                .getBody();
    }

    public boolean validateToken(String authToken) {
        try {
            Jwts.parser().setSigningKey(tokenSecret).parseClaimsJws(authToken);
            return true;
        } catch (SignatureException ex) {
            logger.error("Invalid JWT signature");
        } catch (MalformedJwtException ex) {
            logger.error("Invalid JWT token");
        } catch (ExpiredJwtException ex) {
            logger.error("Expired JWT token");
        } catch (UnsupportedJwtException ex) {
            logger.error("Unsupported JWT token");
        } catch (IllegalArgumentException ex) {
            logger.error("JWT claims string is empty.");
        }
        return false;
    }
}
