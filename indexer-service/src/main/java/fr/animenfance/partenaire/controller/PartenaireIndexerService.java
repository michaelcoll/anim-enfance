package fr.animenfance.partenaire.controller;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import fr.animenfance.bean.Partenaire;

public interface PartenaireIndexerService {

  @GetMapping(value = "/partenaires/search", produces = APPLICATION_JSON_VALUE)
  ResponseEntity<List<Partenaire>> searchPartenaire(
    @RequestParam(name = "search") final String search,
    @RequestParam(name = "hitCount") final Integer hitCount);

  @GetMapping(value = "/partenaires/rebuild-index")
  void rebuildIndex();

}
