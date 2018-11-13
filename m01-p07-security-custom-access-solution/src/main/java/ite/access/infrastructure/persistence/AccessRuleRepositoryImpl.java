package ite.access.infrastructure.persistence;

import ite.access.domain.model.AccessRule;
import ite.access.domain.model.AccessRuleRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Arrays;
import java.util.List;

@Repository
public class AccessRuleRepositoryImpl implements AccessRuleRepository {

    private static AccessRule libraryRule;

    static{
        libraryRule = new AccessRule();
        libraryRule.setAllowedMethod(Arrays.asList(RequestMethod.GET));
        libraryRule.setCustomerId("12345");
        libraryRule.setId("1");
        libraryRule.setPath("/library/books");
        libraryRule.setProjectId("testproject");
        libraryRule.setQuery("*");
        libraryRule.setService("library");
    }

    @Override
    public List<AccessRule> findByCustomerId(String customerId) {
        return Arrays.asList(libraryRule);
    }
}
