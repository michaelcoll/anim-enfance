package fr.animenfance.creche.service;

import fr.animenfance.bean.creche.Creche;
import fr.animenfance.common.service.AbstractCRUDService;
import fr.animenfance.creche.dao.CrecheDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import reactor.core.scheduler.Scheduler;

@Service
public class CrecheService extends AbstractCRUDService<Creche, Integer> {

  @Autowired
  public CrecheService(CrecheDao dao, @Qualifier("jdbcScheduler") Scheduler scheduler) {
    super(dao, scheduler);
  }
}
