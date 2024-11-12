package com.junlinc.rest;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;

public class MongoTemplateTest extends RestApplicationTests{
    @Autowired
    private MongoTemplate mongoTemplate;
    @Test
    public void testCreateCollection() {
        mongoTemplate.createCollection("collection2");
    }
}
