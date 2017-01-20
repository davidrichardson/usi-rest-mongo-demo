package uk.ac.ebi.usi.repo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RestResource;
import uk.ac.ebi.usi.model.Domain;
import uk.ac.ebi.usi.model.Submission;

/**
 * Created by davidr on 20/01/2017.
 */
public interface SubmissionRepository extends MongoRepository<Submission,String> {


    @Query("{'domain.id': ?0 }")
    Page<Submission> findByDomain(String domain, Pageable pageable);
}
