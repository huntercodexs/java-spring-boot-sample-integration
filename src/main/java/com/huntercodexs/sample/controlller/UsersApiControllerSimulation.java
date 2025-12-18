package com.huntercodexs.sample.controlller;

import com.huntercodexs.api.users.api.UsersApi;
import com.huntercodexs.api.users.model.*;
import com.huntercodexs.integration.ratelimit.annotation.RateLimit;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/simulation")
public class UsersApiControllerSimulation implements UsersApi {

    @Override
    public ResponseEntity<CreateUserResponse> createNewUser(CreateUserRequest createUserRequest) {
        System.out.println("Creating new user: " + createUserRequest);
        return null;
    }

    @Override
    @RateLimit(limit = 2, duration = 20, unit = TimeUnit.SECONDS)
    public ResponseEntity<Void> deleteUserById(String userId) {
        System.out.println("Deleting user with ID: " + userId);
        return null;
    }

    @Override
    public ResponseEntity<UsersResponsePagination> getAllUsers(Integer limit, Integer offset, String name) {
        return null;
    }

    @Override
    @RateLimit(limit = 5, duration = 20, unit = TimeUnit.SECONDS)
    public ResponseEntity<UserResponse> getUserById(String userId) {
        System.out.println("Fetching user with ID: " + userId);
        return null;
    }

    @Override
    public ResponseEntity<Void> patchUserById(String userId, UserUpdateRequest userUpdateRequest) {
        return null;
    }
}
