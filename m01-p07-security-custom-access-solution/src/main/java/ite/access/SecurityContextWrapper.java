package ite.access;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;

import java.util.HashMap;
import java.util.Map;

public class SecurityContextWrapper implements SecurityContext {
    public static final String PROPERTY_PROJECTS_NAME = "projects";

    private SecurityContext securityContext;
    private Map<String, Object> properties = new HashMap<>();

    public SecurityContextWrapper(SecurityContext securityContext) {
        this.securityContext = securityContext;
    }

    @Override
    public Authentication getAuthentication() {
        return securityContext.getAuthentication();
    }

    @Override
    public void setAuthentication(Authentication authentication) {
        securityContext.setAuthentication(authentication);
    }

    public Map<String, Object> getProperties() {
        return properties;
    }

    public String getProject(){
        return "testproject";
    }

}
