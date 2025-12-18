package com.huntercodexs.sample.config;

import com.huntercodexs.integration.handler.exception.RateLimitExceededException;
import com.huntercodexs.integration.ratelimit.action.RateLimitServiceBusAction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

import static com.huntercodexs.integration.ratelimit.constants.RateLimitServiceBusIntegrationConstants.RATE_LIMIT_SERVICE_BUS_MSG_EXCEEDED;

@Component
public class RateLimitActionMessageImpl implements RateLimitServiceBusAction {

    private static final Logger log = LoggerFactory.getLogger(RateLimitActionMessageImpl.class);

    @Override
    public boolean supports(Object value) {
        return value.toString().equals("__MESSAGE__");
    }

    @Override
    public void execute(Object[] args, String keyName, int limit, int duration, TimeUnit timeUnit) {
        System.out.println("Custom RateLimitActionMessageImpl executed for key: " + keyName);
        System.out.println("Limit: " + limit + ", Duration: " + duration + " " + timeUnit);

        for (Object arg : args) {
            System.out.println("Argument: " + arg);
        }

        throw new RateLimitExceededException(String.format(
                "My "+RATE_LIMIT_SERVICE_BUS_MSG_EXCEEDED, limit, keyName, duration, timeUnit.toString().toLowerCase()));
    }
}
