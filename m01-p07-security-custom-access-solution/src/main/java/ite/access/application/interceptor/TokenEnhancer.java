package ite.access.application.interceptor;

import ite.access.KeycloakAuthenticationTokenExtension;
import org.keycloak.adapters.springsecurity.token.KeycloakAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Service
public class TokenEnhancer implements HandlerInterceptor {


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        if (SecurityContextHolder.getContext().getAuthentication() instanceof KeycloakAuthenticationToken) {
            KeycloakAuthenticationToken securityContext = (KeycloakAuthenticationToken) SecurityContextHolder
                    .getContext().getAuthentication();
            KeycloakAuthenticationTokenExtension enhancedToken = new KeycloakAuthenticationTokenExtension(securityContext.getAccount(),
                    securityContext.isInteractive(), securityContext.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(enhancedToken);
        }
        return true;
    }
}
