package com.huntercodexs.sample.controlller;

import com.huntercodexs.sample.api.InvalidApiClientSimulation;
import com.huntercodexs.sample.api.UserApiClientSimulation;
import com.huntercodexs.sample.dto.UserRequestSimulation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserControllerSimulation {

    private final UserApiClientSimulation userApiClientMock;
    private final InvalidApiClientSimulation invalidApiClientMock;

    @GetMapping("/test/success")
    public void success() {
        System.out.println(">>> success start");

        UserRequestSimulation userRequestSimulation = new UserRequestSimulation();
        userRequestSimulation.setName("Username Test");
        userRequestSimulation.setEmail("username@email.com");

        System.out.println(">>> Starting integration request");
        System.out.println(userRequestSimulation);

        Void result = userApiClientMock.create(userRequestSimulation);

        System.out.println(">>> The result is: "+result);
        System.out.println(">>> finished");
    }

    @GetMapping("/test/exception")
    public void exception() {
        System.out.println(">>> exception start");

        UserRequestSimulation userRequestSimulation = new UserRequestSimulation();
        userRequestSimulation.setName("Username Test");
        userRequestSimulation.setEmail(null);

        System.out.println(">>> Starting integration request");
        System.out.println(userRequestSimulation);

        Void result = userApiClientMock.create(userRequestSimulation);

        System.out.println(">>> The result is: "+result);
        System.out.println(">>> finished");
    }

    @GetMapping("/test/invalid-api")
    public void invalidApi() {
        System.out.println(">>> invalid-api start");

        UserRequestSimulation userRequestSimulation = new UserRequestSimulation();
        userRequestSimulation.setName("Username Test");
        userRequestSimulation.setEmail("username@email.com");

        System.out.println(">>> Starting integration request");
        System.out.println(userRequestSimulation);

        Void result = invalidApiClientMock.create(userRequestSimulation);

        System.out.println(">>> The result is: "+result);
        System.out.println(">>> finished");
    }

    @PostMapping("/create")
    public void create(@Valid @RequestBody UserRequestSimulation request) {
        System.out.println(">>> create start");
        System.out.println(request);

        if (request.getEmail() == null) {
            System.out.println(">>> Email is null, throwing exception");
            throw new RuntimeException("Email is mandatory");
        }

        System.out.println(">>> finished");
    }

}
