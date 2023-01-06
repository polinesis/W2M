package com.formacionbdi.microservicios.app.heroes.configuration;

import static springfox.documentation.builders.PathSelectors.regex;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * The type Swagger config.
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {
  /**
   * Product api docket.
   *
   * @return the docket
   */
  @Bean
  public Docket productApi() {
    return new Docket(DocumentationType.SWAGGER_2).select()
        .apis(RequestHandlerSelectors.basePackage("com.formacionbdi.microservicios.app.heroes"))
        .paths(regex("/.*")).build().apiInfo(metaData());
  }

  private ApiInfo metaData() {
    return new ApiInfo("microservicios-heroes",
        "Aplicacion destinada a brindar funcionalidad relativa a la administracion de super heroes ",
        "", "Terms of service",
        new Contact("Mauro Quaranta - Administracion super heroes", "http://www.superHeroes.com.ar/",
            "quaranta713@gmail.com"),
        "Apache License Version 2.0", "https://www.apache.org/licenses/LICENSE-2.0");
  }

}
