package fr.animenfance.structure.repository;

import fr.animenfance.bean.structure.Structure;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface StructureRepository extends ReactiveCrudRepository<Structure, Integer> {

}
