package com.dongrami.user.application;

import com.dongrami.user.domain.UserEntity;
import com.dongrami.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public UserEntity getUser(String userUniqueId) {
        return userRepository.findByUserUniqueId(userUniqueId);
    }
}
