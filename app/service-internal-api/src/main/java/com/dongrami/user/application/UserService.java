package com.dongrami.user.application;

import com.dongrami.domain.UserEntity;
import com.dongrami.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public UserEntity getUser(String userId) {
        return userRepository.findByUserId(userId);
    }
}
