package com.example.mission.global.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration class for Swagger/OpenAPI.  This bean customizes the
 * auto‑generated API documentation with a title, description and version as
 * described in the workbook.  It also defines a basic JWT security scheme,
 * which is a common pattern when securing APIs with tokens.  For now the
 * security scheme is optional; it demonstrates how to add headers to all
 * requests in the UI.  See the chapter on Spring Security for details on
 * implementing authentication.
 */
@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI missionOpenAPI() {
        Info info = new Info()
                .title("Mission API")
                .description("APIs for mission service built using domain architecture")
                .version("0.0.1")
                .contact(new Contact().name("Mission Project").email("support@example.com"));

        // Define a JWT bearer authentication scheme.  Clients can set the
        // Authorization header to test secured endpoints.  If you do not
        // implement JWT yet, the scheme is unused but demonstrates how to
        // configure it for later.
        String schemeName = "JWT TOKEN";
        SecurityRequirement securityRequirement = new SecurityRequirement().addList(schemeName);
        Components components = new Components()
                .addSecuritySchemes(schemeName, new SecurityScheme()
                        .name(schemeName)
                        .type(SecurityScheme.Type.HTTP)
                        .scheme("Bearer")
                        .bearerFormat("JWT"));

        return new OpenAPI()
                .info(info)
                .addServersItem(new Server().url("/"))
                .components(components)
                .addSecurityItem(securityRequirement);
    }
}