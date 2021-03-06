package ite.access;

import org.keycloak.adapters.spi.KeycloakAccount;
import org.keycloak.adapters.springsecurity.token.KeycloakAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class KeycloakAuthenticationTokenExtension extends KeycloakAuthenticationToken {
    private String project="testproject";

    public KeycloakAuthenticationTokenExtension(KeycloakAccount account, boolean interactive) {
        super(account, interactive);
    }

    public KeycloakAuthenticationTokenExtension(KeycloakAccount account, boolean interactive, Collection<? extends GrantedAuthority> authorities) {
        super(account, interactive, authorities);
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }
}
