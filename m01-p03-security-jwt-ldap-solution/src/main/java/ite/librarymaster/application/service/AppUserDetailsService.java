package ite.librarymaster.application.service;

import ite.librarymaster.domain.model.ApplicationUser;
import ite.librarymaster.infrastructure.ApplicationUserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import static java.util.Collections.emptyList;

@Service
public class AppUserDetailsService implements UserDetailsService {
    private static final Logger LOG = LoggerFactory.getLogger(AppUserDetailsService.class);

    @Autowired
    private ApplicationUserRepository applicationUserRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        LOG.info("Loading user name:{} details...", username);
        ApplicationUser applicationUser = applicationUserRepository.findByUsername(username);
        if (applicationUser == null) {
            LOG.error("Loading user name:{} details FAILED!", username);
            throw new UsernameNotFoundException(username);
        }else{
            LOG.info("ApplicationUser found:{} ",applicationUser);
        }
        return new User(applicationUser.getUsername(), applicationUser.getPassword(), emptyList());
    }
}
