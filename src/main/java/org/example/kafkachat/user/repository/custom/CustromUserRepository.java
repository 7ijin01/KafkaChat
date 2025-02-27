package org.example.kafkachat.user.repository.custom;

import org.example.kafkachat.user.entity.UserEntity;

public interface CustromUserRepository
{
    UserEntity findByUsername(String username);
}
