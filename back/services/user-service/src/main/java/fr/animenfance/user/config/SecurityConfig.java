package fr.animenfance.user.config;

import fr.animenfance.common.config.CommonSecurityConfig;
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
            .pathMatchers(HttpMethod.PUT, "/users/**").authenticated()
            .pathMatchers(HttpMethod.DELETE, "/users/**").authenticated()
            .pathMatchers(HttpMethod.GET, "/auth/**").authenticated()
            .pathMatchers("/users/{user}/**").access(CommonSecurityConfig::currentUserMatchesPath)
            .anyExchange().permitAll()
          .and()
            .build();
  }
}
