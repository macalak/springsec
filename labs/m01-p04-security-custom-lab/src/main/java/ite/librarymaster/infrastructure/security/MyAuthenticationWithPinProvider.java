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
public class MyAuthenticationWithPinProvider implements AuthenticationProvider {
    private static final Logger LOG = LoggerFactory.getLogger(MyAuthenticationWithPinProvider.class);
    private static final String VALID_PIN = "1234";

    @Autowired
    private ApplicationUserRepository userRepository;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        LOG.info("Going to Authenticate with pin {}", authentication);
        // TODO 14: Implementation
        LOG.info("Successfully Authenticated");
        return authentication;
    }

    // MyAuthenticationProvider is able to authenticate UsernamePasswordAuthenticationToken
    @Override
    public boolean supports(Class<?> authentication) {
        return MyAuthenticationWithPinToken.class.equals(authentication);
    }
}
