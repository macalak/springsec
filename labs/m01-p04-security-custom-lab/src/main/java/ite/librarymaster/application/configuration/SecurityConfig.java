package ite.librarymaster.application.configuration;

import ite.librarymaster.infrastructure.JWTAuthenticationFilter;
import ite.librarymaster.infrastructure.JWTAuthorizationFilter;
import ite.librarymaster.infrastructure.security.MyAuthenticationProvider;
import ite.librarymaster.infrastructure.security.MyAuthenticationWithPinProvider;
import ite.librarymaster.infrastructure.security.MyCustomAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static ite.librarymaster.infrastructure.SecurityConstants.SIGN_UP_URL;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private MyAuthenticationProvider myAuthenticationProvider;
    @Autowired
    private MyAuthenticationWithPinProvider myAuthenticationWithPinProvider;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().authorizeRequests()
                .antMatchers(HttpMethod.POST, SIGN_UP_URL).permitAll()
                .antMatchers("/h2-console/**").permitAll()
                .antMatchers("/login").permitAll()
                .anyRequest().authenticated()
                .and()
// TODO 17: Configure custom Authentication filter
//                .addFilterAt(new MyCustomAuthenticationFilter(authenticationManager()), UsernamePasswordAuthenticationFilter.class)
                .formLogin();
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        // TODO 6: configure your custom UserDetailsService

        // TODO 11: Configure custom AuthenticationProvider (the UserDetailsService config is not required, so remove it )

        //TODO: 15: Configure Custom  user/password with pin Authentication provider

    }
}
