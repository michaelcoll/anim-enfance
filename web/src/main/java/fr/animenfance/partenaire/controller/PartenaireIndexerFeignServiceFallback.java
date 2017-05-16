package fr.animenfance.partenaire.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import fr.animenfance.bean.Partenaire;

@Component
public class PartenaireIndexerFeignServiceFallback implements PartenaireIndexerFeignService {

  @Override
  public ResponseEntity<List<Partenaire>> searchPartenaire(String search, Integer hitCount) {
    return ResponseEntity.status(HttpStatus.GATEWAY_TIMEOUT).body(new ArrayList<>());
  }

  @Override
  public void rebuildIndex() {

  }
}
