package com.playtech.listo.utils;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;

@OpenAPIDefinition(
        info = @Info(
                description = "OpenApi Documentation for Spring Security",
                title = "OpenApi specs",
                version = "1.0",
                license = @License(
                        name = "License Name",
                        url = "www.<zz>.com"
                ),
                termsOfService = "Terminos"
        ),
        servers = {
                @Server(
                        description = "Local ENV",
                        url = "http://localhost:8092"
                ),
                @Server(
                        description = "Prod ENV",
                        url = "URL"
                )
        },
        security = {
                @SecurityRequirement(
                        name = "bearerAuth"
                )
        }
)
@SecurityScheme(
        name = "bearerAuth",
        description = "JWT Auth Desc",
        scheme = "bearer",
        type = SecuritySchemeType.HTTP,
        bearerFormat = "JWT",
        in = SecuritySchemeIn.HEADER
)

public class ApiConfig {

}
