package org.example.kafkachat.jwt.security;


import lombok.RequiredArgsConstructor;
import org.example.kafkachat.user.entity.UserEntity;
import org.example.kafkachat.user.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService
{
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
        UserEntity userEntity =userRepository.findByUserId(userId);
        if(userEntity == null)
        {
            return null;
        }
        return new CustomUserDetails(userEntity);
    }
}
