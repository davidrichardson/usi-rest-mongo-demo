package uk.ac.ebi.usi.repo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import uk.ac.ebi.usi.model.Domain;
import uk.ac.ebi.usi.model.Submission;

import java.util.List;

/**
 * Created by davidr on 20/01/2017.
 */
@RestResource(exported = false)
public interface SubmissionRepository extends MongoRepository<Submission,String> {


    @Override
    @PreAuthorize("hasRole('#domain')")
    @RestResource(exported = true)
    Submission insert(Submission entity);

    @Override
    @PreAuthorize("hasRole('#domain')")
    @RestResource(exported = true)
    Submission save(Submission entity);

    @Override
    @PostAuthorize("hasRole('#domain')")
    @RestResource(exported = true)
    Submission findOne(String s);


    @Override
    @PreAuthorize("hasRole('#domain')")
    @RestResource(exported = true)
    void delete(Submission entity);

    @Query("{'domain.id': ?0 }")
    Page<Submission> findByDomain(String domain, Pageable pageable);
}
