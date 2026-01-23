package com.life_manager.identity_service.core.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {
    @Bean
    public OpenAPI customOpenAPI(
            @Value("${open.api.title}") String title,
            @Value("${open.api.description}") String description,
            @Value("${open.api.version}") String version
    ) {
        return new OpenAPI()
                .info(new Info()
                        .title(title)
                        .version(version)
                        .description(description));
    }

    @Bean
    public GroupedOpenApi customGroupedOpenAPI() {
        return GroupedOpenApi.builder().group("identity-service")
                .pathsToMatch("/v1/**").build();
    }
}
