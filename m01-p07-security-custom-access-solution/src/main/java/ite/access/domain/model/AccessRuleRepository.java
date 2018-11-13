package ite.access.domain.model;

import java.util.List;

public interface AccessRuleRepository {
    List<AccessRule> findByCustomerId(String customerId);
}
