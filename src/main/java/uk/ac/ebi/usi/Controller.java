package uk.ac.ebi.usi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.*;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uk.ac.ebi.usi.model.Sample;
import uk.ac.ebi.usi.model.Submission;
import uk.ac.ebi.usi.processors.ProcessorBackedAssembler;
import uk.ac.ebi.usi.repo.SampleRepository;
import uk.ac.ebi.usi.repo.SubmissionRepository;

/**
 * Created by davidr on 20/01/2017.
 */
@RestController
public class Controller {

    @Autowired
    SubmissionRepository submissionRepository;

    @Autowired
    SampleRepository sampleRepository;

    @Autowired PagedResourcesAssembler pagedResourcesAssembler;
    @Autowired
    ResourceProcessor<Resource<Submission>> submissionProcessor;

    @Autowired
    ResourceProcessor<Resource<Sample>> sampleProcessor;


    @RequestMapping("/domain/{domainId}/submissions")
    public PagedResources<Resource<Submission>> getDomainSubmissions(@PathVariable String domainId, Pageable pageable){
        Page<Submission> page = submissionRepository.findByDomain(domainId,pageable);

        return pagedResourcesAssembler.toResource(page, new ProcessorBackedAssembler<Submission>(submissionProcessor));

    }

    @RequestMapping("/submission/{submissionId}/samples")
    public PagedResources<Resource<Sample>> getSubmissionSamples(@PathVariable String submissionId, Pageable pageable){
        Page<Sample> page = sampleRepository.findBySubmission(submissionId,pageable);

        return pagedResourcesAssembler.toResource(page, new ProcessorBackedAssembler<Sample>(sampleProcessor));

    }
}
