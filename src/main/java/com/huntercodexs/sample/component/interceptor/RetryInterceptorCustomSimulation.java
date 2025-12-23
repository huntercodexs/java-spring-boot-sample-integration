package com.huntercodexs.sample.component.interceptor;

import com.huntercodexs.integration.core.interfaces.RetryInterceptorIntegration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import static com.huntercodexs.integration.core.constants.CoreIntegrationConstants.CORE_RETRYER_HANDLER_EXCEPTION_CUSTOM;

@Component
public class RetryInterceptorCustomSimulation implements RetryInterceptorIntegration {

    private static final Logger log = LoggerFactory.getLogger(RetryInterceptorCustomSimulation.class);

    @Override
    public boolean supports(Object value) {
        return value.toString().equals(CORE_RETRYER_HANDLER_EXCEPTION_CUSTOM);
    }

    @Override
    public void execute() {
        log.info("This is a CUSTOM retry interceptor integration");
    }
}
