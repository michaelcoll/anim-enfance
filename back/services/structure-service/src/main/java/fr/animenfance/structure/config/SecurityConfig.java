package fr.animenfance.structure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.context.WebSessionServerSecurityContextRepository;

@Configuration
public class SecurityConfig {

  @Bean
  public SecurityWebFilterChain springWebFilterChain(ServerHttpSecurity http) {

    return http
            .csrf().disable()
            .httpBasic().securityContextRepository(new WebSessionServerSecurityContextRepository())
          .and()
            .authorizeExchange()
            .pathMatchers("/structure/**").permitAll()
            .pathMatchers(HttpMethod.OPTIONS, "/partner/**").permitAll()
            .anyExchange().permitAll()
          .and()
            .build();
  }
}
