package ite.librarymaster.infrastructure.security;

import ite.librarymaster.domain.model.ApplicationUser;
import ite.librarymaster.infrastructure.ApplicationUserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.stereotype.Component;

/**
 * Custom AuthenticationProvider
 */
@Component
// TODO 9: Implement the correct interface
public class MyAuthenticationProvider  {
    private static final Logger LOG = LoggerFactory.getLogger(MyAuthenticationProvider.class);

    // TODO 9: Autowire the ApplicationUserRepository

    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        LOG.info("Going to Authenticate {}", authentication);
        UsernamePasswordAuthenticationToken token = (UsernamePasswordAuthenticationToken) authentication;
        // TODO 10: Use ApplicationUserRepository to get user by Principal name (name is provided by token)
        ApplicationUser appUser = null;
        if (!PasswordEncoderFactories.createDelegatingPasswordEncoder().matches(token.getCredentials().toString(),appUser.getPassword())
                || appUser == null){
            LOG.error("The credentials are invalid!");
            throw new BadCredentialsException("The credentials are invalid!");
        }
        LOG.info("Successfully Authenticated");
        return new UsernamePasswordAuthenticationToken(appUser, appUser.getPassword(), appUser.getAuthorities());
    }

    // MyAuthenticationProvider is able to authenticate UsernamePasswordAuthenticationToken
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.equals(authentication);
    }
}
