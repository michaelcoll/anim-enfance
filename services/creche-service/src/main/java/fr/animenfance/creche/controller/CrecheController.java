package fr.animenfance.creche.controller;

import fr.animenfance.bean.creche.Creche;
import fr.animenfance.creche.exception.CrecheNotFoundException;
import fr.animenfance.creche.service.CrecheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/partenaire")
public class CrecheController {

  private final CrecheService service;

  @Autowired
  public CrecheController(CrecheService service) {
    this.service = service;
  }

  @GetMapping("/{id}")
  public Mono<Creche> getById(final @PathVariable Integer id) {
    return service.getById(id)
      .switchIfEmpty(Mono.error(new CrecheNotFoundException()));
  }

  @GetMapping
  public Flux<Creche> list() {
    return service.list();
  }

  @PutMapping
  public Mono<Integer> create(@RequestBody final Creche creche) {
    return service.create(creche);
  }

  @DeleteMapping("/{id}")
  public Mono<Integer> deleteById(final @PathVariable Integer id) {
    return service.deleteById(id);
  }

  @ExceptionHandler
  public ResponseEntity<String> handle(@SuppressWarnings("unused") CrecheNotFoundException ex) {
    return ResponseEntity.notFound().build();
  }
}
