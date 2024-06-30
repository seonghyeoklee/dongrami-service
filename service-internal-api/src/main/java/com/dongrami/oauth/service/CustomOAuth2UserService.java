package com.dongrami.oauth.service;

import com.dongrami.common.key.KeyGenerator;
import com.dongrami.oauth.exception.OAuthProviderMissMatchException;
import com.dongrami.oauth.info.OAuth2UserInfo;
import com.dongrami.oauth.info.OAuth2UserInfoFactory;
import com.dongrami.oauth.info.UserPrincipal;
import com.dongrami.user.domain.ProviderType;
import com.dongrami.user.domain.RoleType;
import com.dongrami.user.domain.UserEntity;
import com.dongrami.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomOAuth2UserService extends DefaultOAuth2UserService {
    private final KeyGenerator inviteCodeGenerator;
    private final UserRepository userRepository;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User user = super.loadUser(userRequest);

        try {
            return this.process(userRequest, user);
        } catch (AuthenticationException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new InternalAuthenticationServiceException(ex.getMessage(), ex.getCause());
        }
    }

    private OAuth2User process(OAuth2UserRequest userRequest, OAuth2User user) {
        ProviderType providerType = ProviderType.valueOf(userRequest.getClientRegistration().getRegistrationId().toUpperCase());

        OAuth2UserInfo userInfo = OAuth2UserInfoFactory.getOAuth2UserInfo(providerType, user.getAttributes());
        UserEntity savedUser = userRepository.findByUserUniqueId(userInfo.getId());

        if (savedUser != null) {
            if (providerType != savedUser.getProviderType()) {
                throw new OAuthProviderMissMatchException(
                        "Looks like you're signed up with " + providerType +
                        " account. Please use your " + savedUser.getProviderType() + " account to login."
                );
            }
            updateUser(savedUser, userInfo);
        } else {
            String inviteCode = inviteCodeGenerator.generateKey();
            savedUser = createUser(userInfo, providerType, inviteCode);
        }

        return UserPrincipal.create(savedUser, user.getAttributes());
    }

    private UserEntity createUser(OAuth2UserInfo userInfo, ProviderType providerType, String inviteCode) {
        UserEntity userEntity = UserEntity.createUser(
                userInfo.getId(),
                userInfo.getName(),
                userInfo.getEmail(),
                true,
                userInfo.getImageUrl(),
                providerType,
                RoleType.USER,
                inviteCode
        );

        return userRepository.saveAndFlush(userEntity);
    }

    private UserEntity updateUser(UserEntity user, OAuth2UserInfo userInfo) {
        if (userInfo.getName() != null && !user.getUsername().equals(userInfo.getName())) {
            user.setUsername(userInfo.getName());
        }

        if (userInfo.getImageUrl() != null && !user.getProfileInfo().getProfileImageUrl().equals(userInfo.getImageUrl())) {
            user.getProfileInfo().setProfileImageUrl(userInfo.getImageUrl());
        }

        return user;
    }
}
