package br.edu.unisinos.instituicaoabc.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.Serializable;

@Log4j2
@Configuration
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
public class SwaggerConfig implements Serializable {

    private static final long serialVersionUID = 1L;

    @Bean
    public OpenAPI customOpenAPI(@Value("${info.app.description}") String appDescription, @Value("${info.app.version}") String appVersion) {
        return new OpenAPI()
                .info(new Info()
                        .title("Instituição de Ensino ABC - Unisinos - Grupo 6")
                        .version(appVersion)
                        .description(appDescription)
                        .termsOfService("http://swagger.io/terms/"));
    }
}
