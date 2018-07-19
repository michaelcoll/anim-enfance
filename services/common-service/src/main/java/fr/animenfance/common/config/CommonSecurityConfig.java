package fr.animenfance.common.config;

import fr.animenfance.common.repository.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.server.authorization.AuthorizationContext;
import reactor.core.publisher.Mono;

@Configuration
public class CommonSecurityConfig {

  @Bean
  public PasswordEncoder passwordEncoder() {
    return PasswordEncoderFactories.createDelegatingPasswordEncoder();
  }

  public static Mono<AuthorizationDecision> currentUserMatchesPath(Mono<Authentication> authentication, AuthorizationContext context) {
    return authentication
      .map(a -> context.getVariables().get("user").equals(a.getName()))
      .map(AuthorizationDecision::new);
  }

  @Bean
  public ReactiveUserDetailsService userDetailsService(UserRepository users) {
    return (username) -> users.findByUsername(username)
      .map(u -> User.withUsername(u.getUsername())
        .password(u.getPassword())
        .authorities(u.getRoles().toArray(new String[0]))
        .accountExpired(!u.isActive())
        .credentialsExpired(!u.isActive())
        .disabled(!u.isActive())
        .accountLocked(!u.isActive())
        .build()
      );
  }
}
