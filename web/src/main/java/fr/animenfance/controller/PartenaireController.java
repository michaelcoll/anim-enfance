package fr.animenfance.controller;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.DELETE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.util.List;
import java.util.concurrent.Callable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import fr.animenfance.bean.Partenaire;
import fr.animenfance.exception.PartenaireNotFoundException;
import fr.animenfance.service.PartenaireService;

@RestController
public class PartenaireController {

  private final PartenaireService service;
  private final PartenaireIndexerInterface indexer;

  @Autowired
  public PartenaireController(PartenaireService service, PartenaireIndexerInterface indexer) {
    this.service = service;
    this.indexer = indexer;
  }

  @RequestMapping(value = "/partenaires/{id}",
    method = GET,
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

  @RequestMapping(value = "/partenaires", method = GET, produces = APPLICATION_JSON_VALUE)
  public Callable<ResponseEntity<List<Partenaire>>> list() {
    return () -> ResponseEntity.ok(service.list());
  }

  @RequestMapping(value = "/partenaires", method = POST, produces = APPLICATION_JSON_VALUE)
  public Callable<ResponseEntity<Integer>> create(@RequestBody final Partenaire partenaire) {
    return () -> ResponseEntity.ok(service.create(partenaire));
  }

  @RequestMapping(value = "/partenaires/{id}", method = DELETE, produces = APPLICATION_JSON_VALUE)
  public Callable<ResponseEntity<Integer>> deleteById(final @PathVariable Integer id) {
    return () -> ResponseEntity.ok(service.deleteById(id));
  }

  @RequestMapping(value = "/partenaires/search",
    method = GET,
    produces = APPLICATION_JSON_VALUE)
  public Callable<ResponseEntity<List<Partenaire>>> searchPartenaire(
    @RequestParam String search, @RequestParam(defaultValue = "20") Integer hitCount) {
    return () -> indexer.searchPartenaire(search, hitCount);
  }
}
