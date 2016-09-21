package fr.animenfance.configuration;

import static springfox.documentation.builders.PathSelectors.regex;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.google.common.base.Predicate;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
@Profile("dev")
public class SwaggerConfig {

  @Bean
  public Docket partenaireApi() {
    return new Docket(DocumentationType.SWAGGER_2)
      .groupName("partenaires-api")
      .apiInfo(apiInfo())
      .select()
      .paths(partenairePath())
      .build()
      .enableUrlTemplating(true);
  }

  private Predicate<String> partenairePath() {
    return regex("/partenaires.*");
  }

  private ApiInfo apiInfo() {
    return new ApiInfoBuilder()
      .title("Anim-Enfance API")
      .termsOfServiceUrl("http://springfox.io")
      .license("The MIT License (MIT)")
      .licenseUrl("https://github.com/michaelcoll/anim-enfance-back/blob/master/LICENSE")
      .version("2.0")
      .build();
  }

}
