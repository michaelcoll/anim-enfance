package fr.animenfance.structure.controller;

import fr.animenfance.bean.structure.Structure;
import fr.animenfance.structure.exception.StructureNotFoundException;
import fr.animenfance.structure.repository.StructureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@CrossOrigin("*")
@RestController
@RequestMapping("/structure")
public class StructureController {

  private final StructureRepository repository;

  @Autowired
  public StructureController(StructureRepository repository) {
    this.repository = repository;
  }

  @GetMapping("/{id}")
  public Mono<Structure> getById(final @PathVariable Integer id) {
    return repository.findById(id)
      .switchIfEmpty(Mono.error(new StructureNotFoundException()));
  }

  @GetMapping
  public Flux<Structure> list() {
    return repository.findAll();
  }

  @PutMapping
  public Mono<Structure> save(@RequestBody final Structure creche) {
    return repository.save(creche);
  }

  @DeleteMapping("/{id}")
  public Mono<Void> deleteById(final @PathVariable Integer id) {
    return repository.deleteById(id);
  }

  @ExceptionHandler
  public ResponseEntity<String> handle(@SuppressWarnings("unused") StructureNotFoundException ex) {
    return ResponseEntity.notFound().build();
  }
}
