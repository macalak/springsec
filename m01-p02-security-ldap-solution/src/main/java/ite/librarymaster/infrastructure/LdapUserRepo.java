package ite.librarymaster.infrastructure;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ldap.NamingException;
import org.springframework.ldap.core.AttributesMapper;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.stereotype.Component;

import javax.naming.directory.Attributes;
import java.util.List;

import static org.springframework.ldap.query.LdapQueryBuilder.query;

@Component
public class LdapUserRepo {
    @Autowired
    private LdapTemplate ldapTemplate;

    public List<String> getAllUserNames() {
        return ldapTemplate.search(
                query().where("objectclass").is("person"),
                new AttributesMapper<String>() {
                    public String mapFromAttributes(Attributes attrs)
                            throws NamingException, javax.naming.NamingException {
                        return attrs.get("cn").get().toString();
                    }
                });
    }
}
