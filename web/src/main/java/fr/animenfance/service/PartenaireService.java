package fr.animenfance.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import fr.animenfance.bean.Partenaire;
import fr.animenfance.dao.PartenaireDao;
import fr.animenfance.exception.PartenaireNotFoundException;

@Service
public class PartenaireService {

  private final PartenaireDao dao;

  @Autowired
  public PartenaireService(PartenaireDao dao) {
    this.dao = dao;
  }

  public Partenaire getById(Integer id) throws PartenaireNotFoundException {
    Partenaire partenaire = dao.getById(id);

    if (partenaire == null) throw new PartenaireNotFoundException();

    return partenaire;
  }

  public List<Partenaire> list() {
    return dao.list();
  }

  public Integer deleteById(Integer id) {
    return dao.deleteById(id);
  }

  public Integer create(Partenaire partenaire) {
    return dao.create(partenaire);
  }
}
