package com.dapo.auth.authentication;

import com.dapo.auth.service.UserService;
import com.dapo.common.authentication.StandardUser;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * Created by dimer on 17.10.16.
 */
public class BasicAuthenticationProvider implements AuthenticationProvider {

    private UserService userService;

    public BasicAuthenticationProvider(UserService userService) {
        this.userService = userService;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        try {
            StandardUser user = (StandardUser) userService.authenticate(authentication);

            if (user == null || ! user.isEnabled()) {
                authentication.setAuthenticated(false);
                return authentication;
            }

            Authentication result = new UsernamePasswordAuthenticationToken(user, user.getPassword(), user.getAuthorities());

            SecurityContextHolder.getContext().setAuthentication(result);
            return result;
        } catch (Exception e) {
            throw new UsernameNotFoundException(e.getMessage(), e);
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return true;
    }
}
