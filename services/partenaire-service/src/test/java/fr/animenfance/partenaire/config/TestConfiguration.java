package fr.animenfance.partenaire.config;


import com.zaxxer.hikari.HikariDataSource;
import org.flywaydb.core.Flyway;
import org.flywaydb.test.FlywayHelperFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static fr.animenfance.partenaire.config.DatasourceConfig.buildDataSource;

@Configuration
public class TestConfiguration {

  private static final String DRIVER_CLASSNAME = "org.h2.Driver";
  private static final String JDBC_URL = "jdbc:h2:mem:default";

  @Bean(destroyMethod = "close")
  public HikariDataSource testDataSource() {
    int poolSize = 5;
    return buildDataSource(poolSize, DRIVER_CLASSNAME, JDBC_URL, null, null);
  }

  @Bean
  public FlywayHelperFactory flywayHelperFactory() {
    return new FlywayHelperFactory();
  }

  @Bean
  public Flyway flyway(FlywayHelperFactory flywayHelperFactory,
                       @Qualifier("testDataSource") HikariDataSource dataSource) {
    Flyway flyway = flywayHelperFactory.createFlyway();
    flyway.setDataSource(dataSource);

    return flyway;
  }

  @Bean
  public SqlSessionFactoryBean sqlSessionFactoryBean(@Qualifier("testDataSource") HikariDataSource dataSource) {
    SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
    factoryBean.setDataSource(dataSource);
    return factoryBean;
  }

  @Bean
  public MapperScannerConfigurer mapperScannerConfigurer() {
    MapperScannerConfigurer configurer = new MapperScannerConfigurer();
    configurer.setBasePackage("fr.animenfance.partenaire.dao");
    configurer.setSqlSessionFactoryBeanName("sqlSessionFactoryBean");
    return configurer;
  }
}
