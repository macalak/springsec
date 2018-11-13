package ite.librarymaster.application.api;

import ite.librarymaster.domain.model.ApplicationUser;
import ite.librarymaster.infrastructure.ApplicationUserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {
    private static final Logger LOG = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private ApplicationUserRepository applicationUserRepository;

    @PostMapping("/sign-up")
    public void signUp(@RequestBody ApplicationUser user) {
        LOG.info("Singin-up user:{}", user);
        applicationUserRepository.save(user);
    }
}
