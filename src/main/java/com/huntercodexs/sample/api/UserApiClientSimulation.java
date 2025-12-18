package com.huntercodexs.sample.api;

import com.huntercodexs.integration.core.config.ClientConfigIntegration;
import com.huntercodexs.sample.dto.UserRequestSimulation;
import jakarta.validation.Valid;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name="user", url="http://localhost:8080/api/users", configuration = ClientConfigIntegration.class)
public interface UserApiClientSimulation {

    @RequestMapping(method = RequestMethod.POST, value = "/create")
    Void create(@Valid @RequestBody UserRequestSimulation userRequestSimulation);

}
