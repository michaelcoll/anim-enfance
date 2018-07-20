package fr.animenfance.user;

import fr.animenfance.bean.user.User;
import fr.animenfance.common.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Slf4j
@Component
class DataInitializer {

  private final UserRepository users;
  private final PasswordEncoder passwordEncoder;

  public DataInitializer(UserRepository users, PasswordEncoder passwordEncoder) {
    this.users = users;
    this.passwordEncoder = passwordEncoder;
  }

  @EventListener(value = ContextRefreshedEvent.class)
  public void init() {
    initUsers();
  }

  private void initUsers() {
    log.info("start users initialization  ...");
    this.users
      .deleteAll()
      .thenMany(
        Flux
          .just("user", "admin")
          .flatMap(
            username -> {
              List<String> roles = "user".equals(username)
                ? Collections.singletonList("ROLE_USER")
                : Arrays.asList("ROLE_USER", "ROLE_ADMIN");

              User user = User.builder()
                .roles(roles)
                .username(username)
                .password(passwordEncoder.encode("password"))
                .email(username + "@example.com")
                .build();
              return this.users.save(user);
            }
          )
      )
      .log()
      .subscribe(
        null,
        null,
        () -> log.info("done users initialization...")
      );
  }
}
