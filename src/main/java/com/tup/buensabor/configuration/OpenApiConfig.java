package com.tup.buensabor.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI buenSaborBackendOpenAPI() {
        return new OpenAPI()
                .info(new Info().title("API - El Buen Sabor")
                        .description("API que provee de todas las funcionalidades necesarias para el Backend de El Buen Sabor")
                        .version("1.0"));
    }
}