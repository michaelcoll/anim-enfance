package fr.animenfance.user.service;

import fr.animenfance.bean.user.User;
import fr.animenfance.common.service.AbstractCRUDService;
import fr.animenfance.user.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import reactor.core.scheduler.Scheduler;

@Service
public class UserService extends AbstractCRUDService<User, Integer> {

  @Autowired
  public UserService(UserDao dao, @Qualifier("jdbcScheduler") Scheduler scheduler) {
    super(dao, scheduler);
  }
}
