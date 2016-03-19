package fr.animenfance.controller;

import java.util.List;
import java.util.concurrent.Callable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.animenfance.bean.Partenaire;
import fr.animenfance.exception.PartenaireNotFoundException;
import fr.animenfance.service.PartenaireService;

@RestController
@RequestMapping("/partenaires")
public class PartenaireController {

  private static final Logger LOGGER = LoggerFactory.getLogger(PartenaireController.class);

  @Autowired
  private PartenaireService service;

  @RequestMapping("/{id}")
  public Partenaire getById(@PathVariable Long id) throws PartenaireNotFoundException {
    return service.getById(id);
  }

  @RequestMapping
  public Callable<List<Partenaire>> list() {
    return service::list;
  }
}
