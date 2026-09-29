package com.gabriel.system_user.security.swagger;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class swagger {

    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("System_user")
                        .description("Sistema de cadastro de apenas usuário básico")
                        .version("v1.1.0")
                        .contact(new Contact()
                                .name("Gabriel Lucas")
                                .email("gabriel.lv.lucas@gmail.com")));
    }
}
