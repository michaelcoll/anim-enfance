package fr.animenfance.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.animenfance.bean.Partenaire;
import fr.animenfance.dao.PartenaireDao;
import fr.animenfance.exception.PartenaireNotFoundException;

@Service
public class PartenaireService {

  @Autowired
  private PartenaireDao dao;

  public Partenaire getById(Long id) throws PartenaireNotFoundException {
    Partenaire partenaire = dao.getById(id);

    if(partenaire == null) throw new PartenaireNotFoundException();

    return partenaire;
  }

  public List<Partenaire> list() {
    return dao.list();
  }
}
