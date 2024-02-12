package com.dongrami.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Collections;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI openAPI() {
        SecurityScheme securityScheme = new SecurityScheme()
                .type(SecurityScheme.Type.APIKEY).scheme("Authorization")
                .in(SecurityScheme.In.HEADER).name("Authorization");

        SecurityRequirement securityRequirement = new SecurityRequirement().addList("api_key");

        return new OpenAPI()
                .components(new Components().addSecuritySchemes("api_key", securityScheme))
                .security(Collections.singletonList(securityRequirement))
                .info(getDefaultInfo());
    }

    private Info getDefaultInfo() {
        return new Info()
                .title("Dogrami Internal API")
                .description("internal API for dogrami service")
                .version("1.0.0");
    }

}
