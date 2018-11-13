package ite.access.application.service;

import ite.access.domain.model.AccessRuleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Component
public class AccessRuleServiceImpl implements AccessRuleService {
    @Autowired
    private AccessRuleRepository accessRuleRepository;

    @Override
    public List<String> isAccessGranted(String path, String query, String method, Map<String, String> requestHeaders) {
        // TODO: Use repository to get available access rules
        return Arrays.asList("testproject");
    }
}
