package fr.animenfance.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@Configuration
public class DatasourceConfig extends HikariConfig {

  @Value("${spring.datasource.driverclassname}")
  private String driverClassName;

  @Value("${spring.datasource.url}")
  private String jdbcUrl;

  @Value("${spring.datasource.username}")
  private String userName;

  @Value("${spring.datasource.password}")
  private String password;

  @Value("${spring.datasource.maximum-pool-size}")
  private int poolSize;

  @Bean(destroyMethod = "close")
  @ConditionalOnMissingBean
  public HikariDataSource primaryDataSource() {
    return buildDataSource(poolSize, driverClassName, jdbcUrl, userName, password);
  }

  public static HikariDataSource buildDataSource(
    int poolSize, String driverClassName, String jdbcUrl, String userName, String password) {
    final HikariDataSource ds = new HikariDataSource();
    ds.setMaximumPoolSize(poolSize);
    ds.setDriverClassName(driverClassName);
    ds.setJdbcUrl(jdbcUrl);
    ds.setUsername(userName);
    ds.setPassword(password);
    return ds;
  }
}
