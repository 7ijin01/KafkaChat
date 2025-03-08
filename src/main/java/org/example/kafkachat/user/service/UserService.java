package org.example.kafkachat.user.service;

import lombok.RequiredArgsConstructor;
import org.example.kafkachat.user.dto.UserRequestDto;
import org.example.kafkachat.user.entity.UserEntity;
import org.example.kafkachat.user.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService
{
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public void userRegister(UserRequestDto.RegisterRequest registerRequest)
    {
        UserEntity user = userRepository.findByUserId(registerRequest.getUserId());
        if(user==null)
        {
            UserEntity userEntity =UserEntity.builder()
                    .userId(registerRequest.getUserId())
                    .userName(registerRequest.getUsername())
                    .password(bCryptPasswordEncoder.encode(registerRequest.getPassword()))
                    .build();
            userRepository.save(userEntity);
        }

    }

}
