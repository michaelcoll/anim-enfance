package fr.animenfance.partenaire.service;

import fr.animenfance.bean.partenaire.Partenaire;
import fr.animenfance.partenaire.dao.PartenaireDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Scheduler;

@Service
public class PartenaireService {

  private final PartenaireDao dao;
  private final Scheduler scheduler;

  @Autowired
  public PartenaireService(PartenaireDao dao, @Qualifier("jdbcScheduler") Scheduler scheduler) {
    this.dao = dao;
    this.scheduler = scheduler;
  }

  public Mono<Partenaire> getById(Integer id) {
    return Mono.fromCallable(() -> dao.getById(id)).publishOn(scheduler);
  }

  public Flux<Partenaire> list() {
    return Mono.fromCallable(dao::list).publishOn(scheduler).flatMapMany(Flux::fromIterable);
  }

  public Mono<Integer> create(Partenaire partenaire) {
    return Mono.fromCallable(() -> dao.create(partenaire)).publishOn(scheduler);
  }

  public Mono<Integer> deleteById(Integer id) {
    return Mono.fromCallable(() -> dao.deleteById(id)).publishOn(scheduler);
  }
}
