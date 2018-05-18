package fr.animenfance.user.config;

import fr.animenfance.common.config.TestConfiguration;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(TestConfiguration.class)
public class TestUserConfiguration {

  @Bean
  public MapperScannerConfigurer mapperScannerConfigurer() {
    MapperScannerConfigurer configurer = new MapperScannerConfigurer();
    configurer.setBasePackage("fr.animenfance.user.dao");
    configurer.setSqlSessionFactoryBeanName("sqlSessionFactoryBean");
    return configurer;
  }
}
