package fr.animenfance.partenaire.controller;

import fr.animenfance.bean.partenaire.Partenaire;
import fr.animenfance.partenaire.exception.PartenaireNotFoundException;
import fr.animenfance.partenaire.service.PartenaireService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/partenaire")
public class PartenaireController {

  private final PartenaireService service;

  @Autowired
  public PartenaireController(PartenaireService service) {
    this.service = service;
  }

  @GetMapping("/{id}")
  public Mono<Partenaire> getById(final @PathVariable Integer id) {
    return service.getById(id)
      .switchIfEmpty(Mono.error(new PartenaireNotFoundException()));
  }

  @GetMapping
  public Flux<Partenaire> list() {
    return service.list();
  }

  @PutMapping
  public Mono<Integer> create(@RequestBody final Partenaire partenaire) {
    return service.create(partenaire);
  }

  @DeleteMapping("/{id}")
  public Mono<Integer> deleteById(final @PathVariable Integer id) {
    return service.deleteById(id);
  }

  @ExceptionHandler
  public ResponseEntity<String> handle(@SuppressWarnings("unused") PartenaireNotFoundException ex) {
    return ResponseEntity.notFound().build();
  }
}
