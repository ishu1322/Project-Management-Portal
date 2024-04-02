package com.cts.project.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;


@Configuration
public class OpenApiConfig {
	@Bean
    public OpenAPI usersMicroserviceOpenAPI() {
        return new OpenAPI()
                .info(new Info().title("Project Management Api")
                                 .description("Thses are the API's used in this application")
                                 .version("1.0")
                                 .contact(new Contact().name("Ishu")
                                		 .email("ishu@gmail.com")
                                		 .url("xyz.com")));
                         
    }

}
