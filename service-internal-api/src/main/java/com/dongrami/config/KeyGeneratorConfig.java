package com.dongrami.config;

import com.dongrami.common.key.InviteCodeGenerator;
import com.dongrami.common.key.KeyGenerator;
import com.dongrami.common.key.NanoIdKeyGenerator;
import com.dongrami.common.key.UUIDKeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KeyGeneratorConfig {

    @Bean
    public KeyGenerator uuidKeyGenerator() {
        return new UUIDKeyGenerator();
    }

    @Bean
    public KeyGenerator nanoIdKeyGenerator() {
        return new NanoIdKeyGenerator();
    }

    @Bean
    public KeyGenerator inviteCodeGenerator() {
        return new InviteCodeGenerator();
    }
}