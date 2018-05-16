package fr.animenfance.partenaire.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@MapperScan("fr.animenfance.partenaire.dao")
@Configuration
public class DatasourceConfig extends HikariConfig {

  @Value("${spring.datasource.hikari.driverclassname}")
  private String driverClassName;

  @Value("${spring.datasource.hikari.jdbc-url}")
  private String jdbcUrl;

  @Value("${spring.datasource.hikari.username}")
  private String userName;

  @Value("${spring.datasource.hikari.password}")
  private String password;

  @Value("${spring.datasource.hikari.maximum-pool-size}")
  private int poolSize;

  @Bean(destroyMethod = "close")
  @ConditionalOnMissingBean(type="javax.sql.DataSource")
  public HikariDataSource primaryDataSource() {
    return buildDataSource(poolSize, driverClassName, jdbcUrl, userName, password);
  }

  static HikariDataSource buildDataSource(
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
