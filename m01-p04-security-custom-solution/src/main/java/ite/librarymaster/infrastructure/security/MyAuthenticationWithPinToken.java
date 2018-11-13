package ite.librarymaster.infrastructure.security;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class MyAuthenticationWithPinToken extends UsernamePasswordAuthenticationToken {

    private String pin;

    // This is used for example by login filter
    public MyAuthenticationWithPinToken(Object principal, Object credentials, String pin) {
        super(principal, credentials);
        this.pin=pin;
    }

    // This is used by AuthenticationProvider or AuthenticationManager
    public MyAuthenticationWithPinToken(Object principal, Object credentials, Collection<? extends GrantedAuthority> authorities, String pin) {
        super(principal, credentials, authorities);
        this.pin=pin;
    }

    public String getPin() {
        return pin;
    }
}
