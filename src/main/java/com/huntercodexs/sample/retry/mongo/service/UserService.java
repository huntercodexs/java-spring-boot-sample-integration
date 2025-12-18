package com.huntercodexs.sample.retry.mongo.service;

import com.huntercodexs.integration.mongo.retry.MongoRetry;
import com.huntercodexs.sample.retry.mongo.database.entity.UserEntity;
import com.huntercodexs.sample.retry.mongo.database.repository.UserRepository;
import com.huntercodexs.sample.retry.mongo.dto.UserRequestDto;
import com.huntercodexs.sample.retry.mongo.dto.UserResponseDto;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private static final Logger log = LoggerFactory.getLogger(UserService.class);

    private final UserRepository userRepository;
    private final MongoRetry mongoRetry;

    public UserResponseDto add(UserRequestDto userDto) {

        UserEntity userEntity = new UserEntity();
        userEntity.setName(userDto.getName());
        userEntity.setEmail(userDto.getEmail());

        UserEntity result = mongoRetry.add(userRepository, userEntity);

        return UserResponseDto.builder().id(result.getId()).build();
    }

    public List<UserResponseDto> findAll() {

        Iterable<UserEntity> result = mongoRetry.findAll(userRepository);

        List<UserResponseDto> response = new ArrayList<>();

        result.forEach(entity -> {
            UserResponseDto userResponseDto = new UserResponseDto();
            userResponseDto.setId(entity.getId());
            userResponseDto.setName(entity.getName());
            userResponseDto.setEmail(entity.getEmail());
            response.add(userResponseDto);
        });

        return response;
    }

    public UserResponseDto findById(String id) {

        UserEntity result = mongoRetry.findById(userRepository, id);

        UserResponseDto userResponseDto = new UserResponseDto();
        userResponseDto.setId(result.getId());
        userResponseDto.setName(result.getName());
        userResponseDto.setEmail(result.getEmail());

        return userResponseDto;
    }

    public boolean updateById(UserRequestDto request, String id) {

        UserEntity userEntity = new UserEntity();
        userEntity.setName(request.getName());
        userEntity.setEmail(request.getEmail());

        return mongoRetry.updateById(userRepository, id, userEntity);
    }

    public boolean deleteById(String id) {
        return mongoRetry.deleteById(userRepository, id);
    }

}
