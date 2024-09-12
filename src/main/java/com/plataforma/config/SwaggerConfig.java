package com.plataforma.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.http.HttpHeaders;

@OpenAPIDefinition(
        info = @Info(
                title = "API E'commerce",
                description = "API's proyecto winners",
                termsOfService = "www.winners.com",
                version = "1.0.0",
                contact = @Contact(
                        name = "Luis ",
                        url = "www.winners.com",
                        email = "choqued63@gmail.com"
                ),
                license = @License(
                        name = "Software usado bajo licencia xD",
                        url = "www.winners.com/license"
                )
        ),
        servers = {
                @Server(
                        description = "DEV SERVER",
                        url = "http://localhost:8080"
                ),
                @Server(
                        description = "PROD SERVER",
                        url = "http://www.winner.com:8080"
                )
        },
        security = @SecurityRequirement(
                name = "Security token"
        )
)
@SecurityScheme(
        name = "Security token",
        description = "Access token for my API",
        type = SecuritySchemeType.HTTP,
        paramName = HttpHeaders.AUTHORIZATION,
        in = SecuritySchemeIn.HEADER,
        scheme = "bearer",
        bearerFormat = "JWT"
)
public class SwaggerConfig {
}
