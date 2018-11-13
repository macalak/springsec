package ite.librarymaster.infrastructure.security;

import ite.access.KeycloakAuthenticationTokenExtension;
import ite.access.SecurityContextWrapper;
import ite.access.application.service.AccessRuleService;
import ite.access.application.service.AccessRuleServiceImpl;
import org.keycloak.KeycloakSecurityContext;
import org.keycloak.adapters.springsecurity.token.KeycloakAuthenticationToken;
import org.keycloak.representations.AccessToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.FilterInvocation;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;

import static ite.access.SecurityContextWrapper.PROPERTY_PROJECTS_NAME;

/**
 * ACCESS_GRANTED – the voter gives an affirmative answer
 * ACCESS_DENIED – the voter gives a negative answer
 * ACCESS_ABSTAIN – the voter abstains from voting
 */
@Component
public class CustomAccessDecisionVoter implements AccessDecisionVoter<FilterInvocation>  {
    private final Logger LOG = LoggerFactory.getLogger(CustomAccessDecisionVoter.class);

    private static final String CLAIM_CUSTOMER_ID_NAME = "customerId";

    @Autowired
    AccessRuleService accessRuleService;

    @Override
    public boolean supports(ConfigAttribute attribute) {
        return true;
    }

    @Override
    public int vote(Authentication authentication, FilterInvocation fi,
                    Collection<ConfigAttribute> attributes) {


        LOG.info("URI: {}",fi.getRequestUrl());
        String httpMethod = fi.getHttpRequest().getMethod();
        String httpQuery = fi.getHttpRequest().getQueryString();
        String httpRequestPath = fi.getRequestUrl();
        LOG.info("HTTP Method: {}", httpMethod);
        LOG.info("HTTP Query: {}", httpQuery);
        LOG.info("HTTP Request Path {}", httpRequestPath);

        authentication.getAuthorities().stream().forEach(c -> LOG.info("Authority: {}", ((GrantedAuthority) c).getAuthority()));
        if (SecurityContextHolder.getContext().getAuthentication() instanceof KeycloakAuthenticationToken) {
            KeycloakAuthenticationToken securityContext = (KeycloakAuthenticationToken) SecurityContextHolder
                    .getContext().getAuthentication();
            KeycloakSecurityContext keycloakSecurityContext = securityContext.getAccount().getKeycloakSecurityContext();
            AccessToken token = keycloakSecurityContext.getToken();
            String customerId = (String) token.getOtherClaims().get(CLAIM_CUSTOMER_ID_NAME);
            LOG.info("CLAIM {} = {}", CLAIM_CUSTOMER_ID_NAME, customerId);
            List<String> projects = accessRuleService.isAccessGranted(httpRequestPath, httpQuery, httpMethod, null);
            if(projects == null || projects.isEmpty()){
                return ACCESS_DENIED;
            } else{
                SecurityContextWrapper contextWrapper = new SecurityContextWrapper(SecurityContextHolder.getContext());
                contextWrapper.getProperties().put(PROPERTY_PROJECTS_NAME, projects);
                SecurityContextHolder.setContext(contextWrapper);
            }
        }
        return ACCESS_GRANTED;
    }

    @Override
    public boolean supports(Class clazz) {
        return true;
    }
}
