package fr.animenfance.user.repository;

import fr.animenfance.bean.user.User;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface UserRepository extends ReactiveMongoRepository<User, Integer> {

}
