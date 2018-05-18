package fr.animenfance.partenaire.config;

import fr.animenfance.common.config.TestConfiguration;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(TestConfiguration.class)
public class TestPartenaireConfiguration {

  @Bean
  public MapperScannerConfigurer mapperScannerConfigurer() {
    MapperScannerConfigurer configurer = new MapperScannerConfigurer();
    configurer.setBasePackage("fr.animenfance.partenaire.dao");
    configurer.setSqlSessionFactoryBeanName("sqlSessionFactoryBean");
    return configurer;
  }
}
