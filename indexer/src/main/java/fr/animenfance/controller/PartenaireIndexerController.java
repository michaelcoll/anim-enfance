package fr.animenfance.controller;

import java.io.IOException;
import java.util.List;

import org.apache.lucene.queryparser.classic.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import fr.animenfance.bean.Partenaire;
import fr.animenfance.service.PartenaireIndexerService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class PartenaireIndexerController implements PartenaireIndexerInterface {

  @Autowired
  private PartenaireIndexerService service;

  @Override
  public ResponseEntity<List<Partenaire>> searchPartenaire(final String search, final Integer hitCount) {
    int lHitCount;
    if (hitCount == null) {
      lHitCount = 20;
    } else {
      lHitCount = hitCount;
    }

    try {
      return ResponseEntity.ok(service.searchPartenaire(search, lHitCount));
    } catch (ParseException e) {
      log.error(e.getLocalizedMessage(), e);
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    } catch (IOException e) {
      log.error(e.getLocalizedMessage(), e);
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @Override
  public void rebuildIndex() {
    service.scheduleIndexRebuild();
  }

}
