package com.dongrami.config;

import com.dongrami.common.key.KeyGenerator;
import com.dongrami.common.key.NanoIdKeyGenerator;
import com.dongrami.common.key.UUIDKeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class KeyGeneratorConfig {

    @Bean
    public KeyGenerator uuidKeyGenerator() {
        return new UUIDKeyGenerator();
    }

    @Bean
    @Primary
    public KeyGenerator nanoIdKeyGenerator() {
        return new NanoIdKeyGenerator();
    }

}