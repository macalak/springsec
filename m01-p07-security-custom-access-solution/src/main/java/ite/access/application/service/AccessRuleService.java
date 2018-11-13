package ite.access.application.service;

import java.util.List;
import java.util.Map;

public interface AccessRuleService {
    public List<String> isAccessGranted(String path, String query, String method, Map<String, String> requestHeaders);
}
