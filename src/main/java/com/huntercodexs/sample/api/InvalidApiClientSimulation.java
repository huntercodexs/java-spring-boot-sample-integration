package com.huntercodexs.sample.api;

import com.huntercodexs.integration.core.config.ClientConfigIntegration;
import com.huntercodexs.sample.dto.UserRequestSimulation;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import jakarta.validation.Valid;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name="invalid-api", url="http://localhost:8085/api/users", configuration = ClientConfigIntegration.class)
public interface InvalidApiClientSimulation {

    @CircuitBreaker(name = "invalidApiClient")
    @RequestMapping(method = RequestMethod.POST, value = "/create")
    Void create(@Valid @RequestBody UserRequestSimulation userRequestSimulation);

}
