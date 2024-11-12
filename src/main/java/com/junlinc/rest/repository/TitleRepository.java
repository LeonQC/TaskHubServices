package com.junlinc.rest.repository;
import java.util.Optional;
import com.junlinc.rest.domain.Title;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface TitleRepository extends MongoRepository<Title, String> {
    Title findByTitle(String title);

}
