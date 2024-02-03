package com.dongrami.config.security;

import com.dongrami.config.properties.AppProperties;
import com.dongrami.domain.RoleType;
import com.dongrami.oauth.exception.RestAuthenticationEntryPoint;
import com.dongrami.oauth.filter.TokenAuthenticationFilter;
import com.dongrami.oauth.handler.OAuth2AuthenticationFailureHandler;
import com.dongrami.oauth.handler.OAuth2AuthenticationSuccessHandler;
import com.dongrami.oauth.handler.TokenAccessDeniedHandler;
import com.dongrami.oauth.repository.OAuth2AuthorizationRequestBasedOnCookieRepository;
import com.dongrami.oauth.service.CustomOAuth2UserService;
import com.dongrami.oauth.service.CustomUserDetailsService;
import com.dongrami.oauth.token.AuthTokenProvider;
import com.dongrami.repository.UserRefreshTokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsUtils;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

    private final AppProperties appProperties;
    private final AuthTokenProvider tokenProvider;
    private final CustomUserDetailsService userDetailsService;
    private final CustomOAuth2UserService oAuth2UserService;
    private final TokenAccessDeniedHandler tokenAccessDeniedHandler;
    private final UserRefreshTokenRepository userRefreshTokenRepository;

    private static final String[] PERMIT_URL = {
            "/health/port",
            "/actuator/**",
            "/api/v1/auth/login"
    };

    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .cors()
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .csrf().disable()
                .formLogin().disable()
                .httpBasic().disable()
                .exceptionHandling()
                .authenticationEntryPoint(new RestAuthenticationEntryPoint())
                .accessDeniedHandler(tokenAccessDeniedHandler)
                .and()
                .authorizeRequests()
                .antMatchers(PERMIT_URL).permitAll()
                .requestMatchers(CorsUtils::isPreFlightRequest).permitAll()
                .antMatchers("/api/**").hasAnyAuthority(RoleType.USER.getCode())
                .antMatchers("/api/**/admin/**").hasAnyAuthority(RoleType.ADMIN.getCode())
                .anyRequest().authenticated()
                .and()
                .oauth2Login()
                .authorizationEndpoint()
                .baseUri("/oauth2/authorization")
                .authorizationRequestRepository(oAuth2AuthorizationRequestBasedOnCookieRepository())
                .and()
                .redirectionEndpoint()
                .baseUri("/*/oauth2/code/*")
                .and()
                .userInfoEndpoint()
                .userService(oAuth2UserService)
                .and()
                .successHandler(oAuth2AuthenticationSuccessHandler())
                .failureHandler(oAuth2AuthenticationFailureHandler());

        http.addFilterBefore(tokenAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    /**
     * 쿠키 기반 인가 Repository (인가 응답을 연계 하고 검증)
     *
     * @return OAuth2AuthorizationRequestBasedOnCookieRepository
     */
    @Bean
    public OAuth2AuthorizationRequestBasedOnCookieRepository oAuth2AuthorizationRequestBasedOnCookieRepository() {
        return new OAuth2AuthorizationRequestBasedOnCookieRepository();
    }

    /**
     *  Oauth 인증 성공 핸들러
     *
     * @return OAuth2AuthenticationSuccessHandler
     */
    @Bean
    public OAuth2AuthenticationSuccessHandler oAuth2AuthenticationSuccessHandler() {
        return new OAuth2AuthenticationSuccessHandler(
                tokenProvider,
                appProperties,
                userRefreshTokenRepository,
                oAuth2AuthorizationRequestBasedOnCookieRepository()
        );
    }

    /**
     * Oauth 인증 실패 핸들러
     *
     * @return OAuth2AuthenticationFailureHandler
     */
    @Bean
    public OAuth2AuthenticationFailureHandler oAuth2AuthenticationFailureHandler() {
        return new OAuth2AuthenticationFailureHandler(oAuth2AuthorizationRequestBasedOnCookieRepository());
    }

    /**
     * 토큰 필터 설정
     *
     * @return TokenAuthenticationFilter
     */
    @Bean
    public TokenAuthenticationFilter tokenAuthenticationFilter() {
        return new TokenAuthenticationFilter(tokenProvider);
    }
}
