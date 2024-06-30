package com.dongrami.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .components(getComponents())
                .addSecurityItem(getSecurityRequirement())
                .info(getInfo());
    }
    private Info getInfo() {
        return new Info()
                .title("Dogrami Internal API")
                .description("internal API for dogrami service")
                .version("1.0.0");
    }

    private Components getComponents() {
        return new Components()
                .addSecuritySchemes("Json Web Token",
                        new SecurityScheme()
                                .type(SecurityScheme.Type.HTTP)
                                .scheme("bearer")
                                .bearerFormat("JWT")
                );
    }

    private SecurityRequirement getSecurityRequirement() {
        return new SecurityRequirement()
                .addList("Json Web Token");
    }
}
