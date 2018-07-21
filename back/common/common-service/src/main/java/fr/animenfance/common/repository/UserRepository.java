package fr.animenfance.common.repository;

import fr.animenfance.bean.user.User;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;

public interface UserRepository extends ReactiveMongoRepository<User, Integer> {

  Mono<User> findByUsername(String username);
}
