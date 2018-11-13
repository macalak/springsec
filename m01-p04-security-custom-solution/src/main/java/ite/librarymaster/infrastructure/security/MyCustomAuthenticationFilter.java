package ite.librarymaster.infrastructure.security;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// This is plugged into Authentication filter proxy chain (Servlet stuff)
public class MyCustomAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    static final String PIN_PARAMETER_NAME = "pin";

    private AuthenticationManager authenticationManager;

    public MyCustomAuthenticationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        String password = super.obtainPassword(request);
        String username = super.obtainUsername(request);

        String pin = request.getParameter(PIN_PARAMETER_NAME);
        MyAuthenticationWithPinToken myAuthenticationToken = new MyAuthenticationWithPinToken(username, password, pin);
        super.setDetails(request, myAuthenticationToken);
        // Authenticate token by Authentication Provider on behalf of Authentication Manager
        return authenticationManager.authenticate(myAuthenticationToken);
    }
}
