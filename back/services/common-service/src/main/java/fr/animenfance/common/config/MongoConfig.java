package fr.animenfance.common.config;

import fr.animenfance.common.bean.Username;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;
import org.springframework.security.core.context.ReactiveSecurityContextHolder;
import org.springframework.security.core.context.SecurityContext;
import reactor.core.publisher.Mono;

@Configuration
@EnableMongoAuditing
@EnableReactiveMongoRepositories("fr.animenfance")
public class MongoConfig {

  @Bean
  public AuditorAware<Username> auditor() {
    return () -> ReactiveSecurityContextHolder.getContext()
      .map(SecurityContext::getAuthentication)
      .log()
      .filter(a -> a != null && a.isAuthenticated())
//            .map(Authentication::getPrincipal)
//            .cast(UserDetails.class)
      .map(auth -> new Username(auth.getName()))
      .switchIfEmpty(Mono.empty())
      .blockOptional();
  }
}
