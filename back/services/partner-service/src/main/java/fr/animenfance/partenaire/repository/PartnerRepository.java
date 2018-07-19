package fr.animenfance.partenaire.repository;

import fr.animenfance.bean.partenaire.Partner;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface PartnerRepository extends ReactiveCrudRepository<Partner, String> {

}
