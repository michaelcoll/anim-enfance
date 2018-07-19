package fr.animenfance.partenaire.controller;

import fr.animenfance.bean.partenaire.Partner;
import fr.animenfance.partenaire.exception.PartnerNotFoundException;
import fr.animenfance.partenaire.repository.PartnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static org.springframework.http.HttpStatus.NO_CONTENT;

@RestController
@RequestMapping("/partner")
public class PartenaireController {

  private final PartnerRepository repository;

  @Autowired
  public PartenaireController(PartnerRepository repository) {
    this.repository = repository;
  }

  @GetMapping("/{id}")
  public Mono<Partner> getById(final @PathVariable String id) {
    return repository.findById(id)
      .switchIfEmpty(Mono.error(new PartnerNotFoundException()));
  }

  @GetMapping
  public Flux<Partner> list() {
    return repository.findAll();
  }

  @PutMapping
  public Mono<Partner> create(@RequestBody final Partner partner) {
    return repository.save(partner);
  }

  @DeleteMapping("/{id}")
  @ResponseStatus(NO_CONTENT)
  public Mono<Void> deleteById(final @PathVariable String id) {
    return repository.deleteById(id);
  }

  @ExceptionHandler
  public ResponseEntity<String> handle(@SuppressWarnings("unused") PartnerNotFoundException ex) {
    return ResponseEntity.notFound().build();
  }
}
