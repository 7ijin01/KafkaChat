package org.example.kafkachat.user.repository;


import org.example.kafkachat.user.entity.UserEntity;
import org.example.kafkachat.user.repository.custom.CustromUserRepository;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository<UserEntity,String>, CustromUserRepository
{
}
