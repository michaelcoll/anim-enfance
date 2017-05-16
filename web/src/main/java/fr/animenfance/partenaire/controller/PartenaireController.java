package fr.animenfance.partenaire.controller;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import java.util.List;
import java.util.concurrent.Callable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import fr.animenfance.bean.Partenaire;
import fr.animenfance.exception.PartenaireNotFoundException;
import fr.animenfance.partenaire.service.PartenaireService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class PartenaireController {

  @Autowired private PartenaireService service;

  @SuppressWarnings("SpringJavaAutowiringInspection")
  @Autowired private PartenaireIndexerService indexer;

  @GetMapping(value = "/partenaires/{id}",
    produces = APPLICATION_JSON_VALUE)
  public Callable<ResponseEntity<Partenaire>> getById(final @PathVariable Integer id) {
    return () -> {
      try {
        return ResponseEntity.ok(service.getById(id));
      } catch (PartenaireNotFoundException e) {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
      }
    };
  }

  @GetMapping(value = "/partenaires", produces = APPLICATION_JSON_VALUE)
  public Callable<ResponseEntity<List<Partenaire>>> list() {
    return () -> ResponseEntity.ok(service.list());
  }

  @PostMapping(value = "/partenaires", produces = APPLICATION_JSON_VALUE)
  public Callable<ResponseEntity<Integer>> create(@RequestBody final Partenaire partenaire) {
    return () -> ResponseEntity.ok(service.create(partenaire));
  }

  @DeleteMapping(value = "/partenaires/{id}", produces = APPLICATION_JSON_VALUE)
  public Callable<ResponseEntity<Integer>> deleteById(final @PathVariable Integer id) {
    return () -> ResponseEntity.ok(service.deleteById(id));
  }

  @GetMapping(value = "/partenaires/search",
    produces = APPLICATION_JSON_VALUE)
  public Callable<ResponseEntity<List<Partenaire>>> searchPartenaire(
    @RequestParam String search, @RequestParam(defaultValue = "20") Integer hitCount) {
    return () -> indexer.searchPartenaire(search, hitCount);
  }

  @GetMapping(value = "/partenaires/rebuild-index")
  public Callable<ResponseEntity<String>> rebuildIndex() {
    return () -> {
      indexer.rebuildIndex();
      return ResponseEntity.ok("Done.");
    };
  }
}
