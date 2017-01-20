package uk.ac.ebi.usi.repo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RestResource;
import uk.ac.ebi.usi.model.Domain;


@RestResource
public interface DomainRepository extends MongoRepository<Domain,String> {


}
