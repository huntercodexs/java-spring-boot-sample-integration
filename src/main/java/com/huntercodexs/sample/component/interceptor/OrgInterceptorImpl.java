package com.huntercodexs.sample.component.interceptor;

import com.huntercodexs.integration.core.interfaces.ClientInterceptorIntegration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Component
public class OrgInterceptorImpl implements ClientInterceptorIntegration {

    @Autowired
    private OrganizationalService organizationalService;

    @Override
    public boolean checkSupport(Object value) {
        return value.toString().equals("organizational");
    }

    @Override
    public String getClientToken() {
        return organizationalService.getClientToken().orElse("");
    }

    @Service
    public static class OrganizationalService {
        public Optional<String> getClientToken() {
            System.out.println("calling getClientToken from OrganizationService");
            return Optional.of("Bearer OrganizationTokenFake");
        }
    }

}
