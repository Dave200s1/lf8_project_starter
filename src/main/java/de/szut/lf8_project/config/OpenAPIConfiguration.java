package de.szut.lf8_project.config;


import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.ServletContext;

@Configuration
public class OpenAPIConfiguration {

    private ServletContext context;

    public OpenAPIConfiguration(ServletContext context) {
        this.context = context;
    }


    @Bean
    public OpenAPI springShopOpenAPI(
            //  @Value("${info.app.version}") String appVersion,
    ) {
        final String securitySchemeName = "bearerAuth";

        return new OpenAPI()
                .addServersItem(new Server().url(this.context.getContextPath()))
                .info(new Info()
                        .title("Project Management Micro-Service")
                        .description("\nProject Management Service API manages the projects of HighTec Gmbh including employees.\n" +
                                "\nIt offers the possibility to create, read, update and delete projects.\n")

                        .version("0.1"))
                .addSecurityItem(new SecurityRequirement().addList(securitySchemeName))
                .components(
                        new Components()
                                .addSecuritySchemes(securitySchemeName,
                                        new SecurityScheme()
                                                .name(securitySchemeName)
                                                .type(SecurityScheme.Type.HTTP)
                                                .scheme("bearer")
                                                .bearerFormat("JWT")
                                )
                );
    }


}