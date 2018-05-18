package fr.animenfance.user.controller;

import fr.animenfance.bean.user.User;
import fr.animenfance.user.exception.UserNotFoundException;
import fr.animenfance.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/user")
public class UserController {

  private final UserService service;

  @Autowired
  public UserController(UserService service) {
    this.service = service;
  }

  @GetMapping("/{id}")
  public Mono<User> getById(final @PathVariable Integer id) {
    return service.getById(id)
      .switchIfEmpty(Mono.error(new UserNotFoundException()));
  }

  @GetMapping
  public Flux<User> list() {
    return service.list();
  }

  @PutMapping
  public Mono<Integer> create(@RequestBody final User user) {
    return service.create(user);
  }

  @DeleteMapping("/{id}")
  public Mono<Integer> deleteById(final @PathVariable Integer id) {
    return service.deleteById(id);
  }

  @ExceptionHandler
  public ResponseEntity<String> handle(@SuppressWarnings("unused") UserNotFoundException ex) {
    return ResponseEntity.notFound().build();
  }
}
