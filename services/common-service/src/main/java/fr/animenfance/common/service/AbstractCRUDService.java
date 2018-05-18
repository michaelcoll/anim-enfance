package fr.animenfance.common.service;

import fr.animenfance.common.dao.CRUDDao;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Scheduler;

public abstract class AbstractCRUDService<T, I> {

  private final CRUDDao<T, I> dao;
  private final Scheduler scheduler;

  public AbstractCRUDService(CRUDDao<T, I> dao, Scheduler scheduler) {
    this.dao = dao;
    this.scheduler = scheduler;
  }

  public Mono<T> getById(I id) {
    return Mono.fromCallable(() -> dao.getById(id)).publishOn(scheduler);
  }

  public Flux<T> list() {
    return Mono.fromCallable(dao::list).publishOn(scheduler).flatMapMany(Flux::fromIterable);
  }

  public Mono<Integer> create(T item) {
    return Mono.fromCallable(() -> dao.create(item)).publishOn(scheduler);
  }

  public Mono<Integer> deleteById(I id) {
    return Mono.fromCallable(() -> dao.deleteById(id)).publishOn(scheduler);
  }
}
