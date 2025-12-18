package com.huntercodexs.sample.component.interceptor;

import com.huntercodexs.integration.core.interfaces.ClientInterceptorIntegration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Component
public class UserInterceptorImpl implements ClientInterceptorIntegration {

    @Autowired
    private UserManagerService userManagerService;

    @Override
    public boolean checkSupport(Object value) {
        return value.toString().equals("user");
    }

    @Override
    public String getClientToken() {
        return userManagerService.getClientToken().orElse("");
    }

    @Service
    public static class UserManagerService {
        public Optional<String> getClientToken() {
            System.out.println("calling getClientToken from UserManagerService");
            return Optional.of("Bearer UserManagerTokenFake");
        }
    }
}
