package br.edu.iff.ccc.bsi.SistemaHospitalar.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
            .info(new Info().title("Sistema Hospitalar API")
            .description("API para gerenciamento de usuários, médicos, consultas e mais.")
            .version("1.0"));
    }
}
