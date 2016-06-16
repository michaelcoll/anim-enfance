package fr.animenfance.controller;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;

import java.util.List;
import java.util.concurrent.Callable;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import fr.animenfance.bean.Partenaire;

@FeignClient("indexer")
public interface PartenaireIndexerInterface {

  @RequestMapping(value = "/partenaires/search",
    method = GET,
    produces = APPLICATION_JSON_VALUE)
  Callable<ResponseEntity<List<Partenaire>>> searchPartenaire(
    @RequestParam final String search,
    @RequestParam(defaultValue = "20") final Integer hitCount);

  @RequestMapping(value = "/partenaires/rebuild-index",
    method = GET)
  void rebuildIndex();

}
