package com.junlinc.rest.repository;
import com.junlinc.rest.domain.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {
}
