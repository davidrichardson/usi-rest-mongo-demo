package uk.ac.ebi.usi.repo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.query.Param;
import uk.ac.ebi.usi.model.Sample;
import uk.ac.ebi.usi.model.Submission;

/**
 * Created by davidr on 20/01/2017.
 */
public interface SampleRepository extends MongoRepository<Sample,String>{

    @Query("{'submission.id': ?0 }")
    Page<Sample> findBySubmission( String submission, Pageable pageable);

}
