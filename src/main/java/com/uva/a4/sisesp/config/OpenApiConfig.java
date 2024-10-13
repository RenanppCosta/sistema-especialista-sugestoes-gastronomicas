package com.uva.a4.sisesp.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Sistema Especialista API")
                        .version("1.0.0")
                        .description("Descrição da minha API que vai interagir com o PROLOG"));
    }
    
    
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
