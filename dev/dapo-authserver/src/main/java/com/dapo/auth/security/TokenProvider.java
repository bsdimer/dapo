package com.dapo.auth.security;

import com.dapo.auth.common.config.AppProperties;
import com.dapo.auth.common.oauth2.TokenClaims;
import com.dapo.auth.common.oauth2.UserPrincipal;
import com.dapo.auth.model.UserEntity;
import com.dapo.auth.repository.UserRepository;
import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class TokenProvider {

    private static final Logger logger = LoggerFactory.getLogger(TokenProvider.class);

    private AppProperties appProperties;
    private UserRepository userRepository;

    public TokenProvider(AppProperties appProperties, UserRepository userRepository) {
        this.appProperties = appProperties;
        this.userRepository = userRepository;
    }

    public String createToken(Authentication authentication) {
        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
        Optional<UserEntity> userEntityOptional = userRepository.findByEmail(userPrincipal.getEmail());

        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + appProperties.getAuth().getTokenExpirationMsec());

        Map<String, Object> claims = new HashMap<>();
        claims.put(TokenClaims.sub, userPrincipal.getId());
        claims.put(TokenClaims.email, userPrincipal.getEmail());
        claims.put(TokenClaims.preferred_username, userPrincipal.getUsername());
        userEntityOptional.ifPresent(userEntity -> {
            claims.put(TokenClaims.name, userEntity.getName());
            claims.put(TokenClaims.email_verified, userEntity.getEmailVerified());
            claims.put(TokenClaims.phone_number, userEntity.getPhone());
            claims.put(TokenClaims.picture, userEntity.getImageUrl());
        });
        return Jwts.builder()
                .setSubject(Long.toString(userPrincipal.getId()))
                .setIssuedAt(new Date())
                .setClaims(claims)
                .setExpiration(expiryDate)
                .signWith(SignatureAlgorithm.HS512, appProperties.getAuth().getTokenSecret())
                .compact();
    }

    public Long getUserIdFromToken(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(appProperties.getAuth().getTokenSecret())
                .parseClaimsJws(token)
                .getBody();

        return Long.parseLong(claims.getSubject());
    }

    public boolean validateToken(String authToken) {
        try {
            Jwts.parser().setSigningKey(appProperties.getAuth().getTokenSecret()).parseClaimsJws(authToken);
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
