package com.contas.contas.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.models.media.StringSchema;
import org.springdoc.core.utils.SpringDocUtils;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(info = @Info(title = "${swagger.title}", version = "${swagger.version}",
        description = "${swagger.description}",
        contact = @Contact(name = "${swagger.contact.name}", email = "${swagger.contact.email}")),
        security = {@SecurityRequirement(name = "bearerToken")}
)
@SecurityScheme(name = "bearerToken", type = SecuritySchemeType.HTTP,  scheme = "bearer", bearerFormat = "JWT")
public class SwaggerConfig {

    static {
        SpringDocUtils.getConfig().replaceWithSchema(Object.class,new StringSchema());
    }



}
