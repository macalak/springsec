package ite.librarymaster.application.service;

import ite.librarymaster.domain.model.ApplicationUser;
import ite.librarymaster.infrastructure.ApplicationUserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import static java.util.Collections.emptyList;

@Service
// TODO 2: Implement a correct interface
public class AppUserDetailsService implements UserDetailsService {
    private static final Logger LOG = LoggerFactory.getLogger(AppUserDetailsService.class);

    //TODO 3: Autowire the ApplicationUserRepository

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        LOG.info("Loading user name:{} details...", username);
        // TODO 5: Use repository to get user details

        return null;
    }
}
