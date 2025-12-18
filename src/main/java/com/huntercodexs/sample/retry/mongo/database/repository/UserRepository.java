package com.huntercodexs.sample.retry.mongo.database.repository;

import com.huntercodexs.sample.retry.mongo.database.entity.UserEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository<UserEntity, String> {
}
