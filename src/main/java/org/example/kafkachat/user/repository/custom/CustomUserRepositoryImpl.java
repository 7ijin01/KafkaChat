package org.example.kafkachat.user.repository.custom;

import lombok.RequiredArgsConstructor;
import org.example.kafkachat.user.entity.UserEntity;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class CustomUserRepositoryImpl implements CustromUserRepository
{
    private final MongoTemplate mongoTemplate;

    @Override
    public UserEntity findByUsername(String username) {
        Query query= new Query();
        query.addCriteria(Criteria.where("username").is(username));
        return mongoTemplate.findOne(query,UserEntity.class);
    }
}
