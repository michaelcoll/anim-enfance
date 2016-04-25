package fr.animenfance.controller;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.Callable;

import org.apache.lucene.queryparser.classic.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import fr.animenfance.bean.Partenaire;
import fr.animenfance.service.PartenaireIndexerService;

@RestController
public class PartenaireIndexerController {

  private static final Logger LOGGER = LoggerFactory.getLogger(PartenaireIndexerController.class);

  @Autowired
  private PartenaireIndexerService service;

  @RequestMapping(value = "/search/partenaires",
    method = GET,
    produces = APPLICATION_JSON_VALUE)
  public Callable<ResponseEntity<List<Partenaire>>> searchPartenaire(
    @RequestParam final String search,
    @RequestParam(defaultValue = "20") final Integer hitCount) {
    return () -> {
      try {
        return ResponseEntity.ok(service.searchPartenaire(search, hitCount));
      } catch (ParseException e) {
        LOGGER.error(e.getLocalizedMessage(), e);
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
      } catch (IOException e) {
        LOGGER.error(e.getLocalizedMessage(), e);
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
      }
    };
  }

  @RequestMapping(value = "/search/partenaires/rebuild-index",
    method = GET)
  public void rebuildIndex() {
    service.scheduleIndexRebuild();
  }


}
