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
                .addFilterAt(new MyCustomAuthenticationFilter(authenticationManager()), UsernamePasswordAuthenticationFilter.class)
                .formLogin();
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        //TODO: Not required if the MyAuthenticationProvider is used
        //auth.userDetailsService(userDetailsService).passwordEncoder(PasswordEncoderFactories.createDelegatingPasswordEncoder());
        //TODO: Custom user/password provider
        // auth.authenticationProvider(myAuthenticationProvider);
        //TODO: Custom  user/password with pin provider
        auth.authenticationProvider(myAuthenticationWithPinProvider);

    }
}
