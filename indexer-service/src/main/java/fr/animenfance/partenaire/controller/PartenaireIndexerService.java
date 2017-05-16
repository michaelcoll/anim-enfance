package fr.animenfance.partenaire.controller;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import fr.animenfance.bean.Partenaire;

public interface PartenaireIndexerService {

  @RequestMapping(value = "/partenaires/search",
    method = GET,
    produces = APPLICATION_JSON_VALUE)
  ResponseEntity<List<Partenaire>> searchPartenaire(
    @RequestParam(name = "search") final String search,
    @RequestParam(name = "hitCount") final Integer hitCount);

  @RequestMapping(value = "/partenaires/rebuild-index",
    method = GET)
  void rebuildIndex();

}
