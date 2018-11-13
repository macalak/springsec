package ite.librarymaster.infrastructure;

import ite.librarymaster.domain.model.ApplicationUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ldap.NamingException;
import org.springframework.ldap.core.AttributesMapper;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.stereotype.Component;

import javax.naming.directory.Attributes;
import java.util.List;

import static org.springframework.ldap.query.LdapQueryBuilder.query;

@Component
public class ApplicationUserRepository {
    final private static Logger LOG = LoggerFactory.getLogger(ApplicationUserRepository.class);
    @Autowired
    private LdapTemplate ldapTemplate;

    public List<String> getAllUserNames() {
        LOG.info("Retrieving all users...");
        return ldapTemplate.search(
                query().where("objectclass").is("person"),
                new AttributesMapper<String>() {
                    public String mapFromAttributes(Attributes attrs)
                            throws NamingException, javax.naming.NamingException {
                        return attrs.get("cn").get().toString();
                    }
                });
    }

    public ApplicationUser findByUsername(String username){
        LOG.info("Searching user details for username:{} ...", username);
        return ldapTemplate.findOne(query().where("uid").is(username), ApplicationUser.class);
    }

    public void save(ApplicationUser applicationUser){
        ldapTemplate.create(applicationUser);
    }
}
