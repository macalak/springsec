package ite.librarymaster.application.configuration;

import ite.librarymaster.infrastructure.security.CustomAccessDecisionVoter;
import org.keycloak.adapters.springboot.KeycloakSpringBootConfigResolver;
import org.keycloak.adapters.springsecurity.KeycloakConfiguration;
import org.keycloak.adapters.springsecurity.authentication.KeycloakAuthenticationProvider;
import org.keycloak.adapters.springsecurity.config.KeycloakWebSecurityConfigurerAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.vote.AuthenticatedVoter;
import org.springframework.security.access.vote.RoleVoter;
import org.springframework.security.access.vote.UnanimousBased;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.authority.mapping.SimpleAuthorityMapper;
import org.springframework.security.data.repository.query.SecurityEvaluationContextExtension;
import org.springframework.security.web.access.expression.WebExpressionVoter;
import org.springframework.security.web.authentication.session.NullAuthenticatedSessionStrategy;
import org.springframework.security.web.authentication.session.SessionAuthenticationStrategy;

import java.util.Arrays;
import java.util.List;

@KeycloakConfiguration
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class KeycloakConfig extends KeycloakWebSecurityConfigurerAdapter {

	static Logger LOG = LoggerFactory.getLogger(KeycloakConfig.class);

	@Autowired
	private CustomAccessDecisionVoter accessDecisionVoter;

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		try {
			KeycloakAuthenticationProvider keycloakAuthenticationProvider = keycloakAuthenticationProvider();
			keycloakAuthenticationProvider.setGrantedAuthoritiesMapper(new SimpleAuthorityMapper());
			auth.authenticationProvider(keycloakAuthenticationProvider);
		} catch (Exception ex) {
			LOG.error(ex.getMessage());
		}
	}

	@Bean
	public KeycloakSpringBootConfigResolver KeycloakConfigResolver() {
		return new KeycloakSpringBootConfigResolver();
	}

	@Bean
	@Override
	protected SessionAuthenticationStrategy sessionAuthenticationStrategy() {
		return new NullAuthenticatedSessionStrategy();
	}

	@Bean
	public AccessDecisionManager accessDecisionManager() {
		List<AccessDecisionVoter<? extends Object>> decisionVoters
				= Arrays.asList(
				new WebExpressionVoter(),
				new RoleVoter(),
				new AuthenticatedVoter(),
				accessDecisionVoter);
		return new UnanimousBased(decisionVoters);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		super.configure(http);
		http.authorizeRequests().antMatchers("/h2-console/**").permitAll()
				                .anyRequest().authenticated()
				                .accessDecisionManager(accessDecisionManager())
								.and().csrf().disable();
	}
}
