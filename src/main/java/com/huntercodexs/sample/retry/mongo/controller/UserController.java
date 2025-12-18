package com.huntercodexs.sample.retry.mongo.controller;

import com.huntercodexs.sample.retry.mongo.dto.UserRequestDto;
import com.huntercodexs.sample.retry.mongo.dto.UserResponseDto;
import com.huntercodexs.sample.retry.mongo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/mongo-retry/users")
public class UserController {

    private final UserService userService;

    @PostMapping(consumes = "application/json", produces = "application/json")
    public ResponseEntity<UserResponseDto> add(@RequestBody UserRequestDto request) {
        UserResponseDto response = userService.add(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping(produces = "application/json")
    public ResponseEntity<List<UserResponseDto>> findAll() {
        List<UserResponseDto> response = userService.findAll();
        return ResponseEntity.ok(response);
    }

    @GetMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<UserResponseDto> findById(@PathVariable String id) {
        UserResponseDto response = userService.findById(id);
        return ResponseEntity.ok(response);
    }

    @PutMapping(value = "/{id}", consumes = "application/json")
    public ResponseEntity<Void> updateById(@RequestBody UserRequestDto request, @PathVariable String id) {
        boolean updated = userService.updateById(request, id);
        if (updated) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable String id) {
        boolean deleted = userService.deleteById(id);
        if (deleted) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
}
