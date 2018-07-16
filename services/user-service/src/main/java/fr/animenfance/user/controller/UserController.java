package fr.animenfance.user.controller;

import fr.animenfance.bean.user.User;
import fr.animenfance.user.exception.UserNotFoundException;
import fr.animenfance.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@CrossOrigin("*")
@RestController
@RequestMapping("/user")
public class UserController {

  private final UserRepository repository;

  @Autowired
  public UserController(UserRepository repository) {
    this.repository = repository;
  }

  @GetMapping("/{id}")
  public Mono<User> getById(final @PathVariable Integer id) {
    return repository.findById(id)
      .switchIfEmpty(Mono.error(new UserNotFoundException()));
  }

  @GetMapping
  public Flux<User> list() {
    return repository.findAll();
  }

  @PutMapping
  public Mono<User> create(@RequestBody final User user) {
    return repository.save(user);
  }

  @DeleteMapping("/{id}")
  public Mono<Void> deleteById(final @PathVariable Integer id) {
    return repository.deleteById(id);
  }

  @ExceptionHandler
  public ResponseEntity<String> handle(@SuppressWarnings("unused") UserNotFoundException ex) {
    return ResponseEntity.notFound().build();
  }
}
