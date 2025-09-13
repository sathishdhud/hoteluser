package com.hotel.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.Contact;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI hotelManagementOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Hotel Management System API")
                        .description("Comprehensive API for managing hotel operations including reservations, check-ins, billing, and more")
                        .version("1.0.0")
                        .contact(new Contact()
                                .name("Hotel Management Team")
                                .email("support@hotelmanagement.com")));
    }
}
