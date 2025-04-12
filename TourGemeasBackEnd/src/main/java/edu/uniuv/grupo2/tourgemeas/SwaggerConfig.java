package edu.uniuv.grupo2.tourgemeas;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class SwaggerConfig {
	@Bean
	public OpenAPI openApi() {
        return new OpenAPI()
				.info(new Info()
						.title("Tour Cidades Gêmeas API")
						.version("1.0")
						.description("Documentação da API do sistema Tour Cidades Gêmeas"));
    }
}
