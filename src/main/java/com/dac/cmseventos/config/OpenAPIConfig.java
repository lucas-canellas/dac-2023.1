package com.dac.cmseventos.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;


@Configuration
public class OpenAPIConfig {

  @Value("${cms-dac.openapi.dev-url}")
  private String devUrl;

  @Value("${cms-dac.openapi.prod-url}")
  private String prodUrl;

  @Bean
  public OpenAPI myOpenAPI() {
    Server devServer = new Server();
    devServer.setUrl(devUrl);
    devServer.setDescription("Server URL in Development environment");

    Server prodServer = new Server();
    prodServer.setUrl(prodUrl);
    prodServer.setDescription("Server URL in Production environment");

    Contact contact = new Contact();
    contact.setName("cms-dac-github");
    contact.setUrl("https://github.com/lucas-canellas/dac-2023.2");

    License mitLicense = new License().name("MIT License").url("https://choosealicense.com/licenses/mit/");

    Info info = new Info()
        .title("API CMS Eventos")
        .version("1.0")
        .contact(contact)
        .description("<b>Alunos:</b> Lucas David Canellas, Matheus Vieira da Silva, Leonardo Tramont Lutz Moreira e Fabio Lucas Maciel da Silva")
        .license(mitLicense);

    return new OpenAPI().info(info).servers(List.of(devServer, prodServer));
  }
}
