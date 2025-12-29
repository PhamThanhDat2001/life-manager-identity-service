package com.life_manager.identity_service.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class OpenApiConfig {
    @Bean
    public OpenAPI customOpenAPI(
            @Value("${open.api.title}") String title,
            @Value("${open.api.version}") String version,
            @Value("${open.api.title}") String title,
            @Value("${open.api.title}") String title
    ) {
        return new OpenAPI()
                .info(new Info().title(title)
                .description("Open API Documentation")
                .version(version).description("This is the Open API Documentation")
                .license(new License().name("API Licence").url("http://springdoc.org")))
                .servers(List.of(new Server().url("http://localhost:8080").description("Local")));
    }
}
