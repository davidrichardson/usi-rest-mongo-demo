package uk.ac.ebi.usi.processors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.rest.webmvc.support.RepositoryEntityLinks;
import org.springframework.hateoas.EntityLinks;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceAssembler;
import org.springframework.hateoas.ResourceProcessor;
import uk.ac.ebi.usi.Controller;
import uk.ac.ebi.usi.model.Domain;
import uk.ac.ebi.usi.model.Sample;
import uk.ac.ebi.usi.model.Submission;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;


/**
 * Created by davidr on 20/01/2017.
 */
@Configuration
public class ResourceProcessors {

    @Autowired
    RepositoryEntityLinks repositoryEntityLinks;
    @Autowired
    EntityLinks entityLinks;



    @Bean
    public ResourceProcessor<Resource<Domain>> domainProcessor() {

        return new ResourceProcessor<Resource<Domain>>() {

            @Override
            public Resource<Domain> process(Resource<Domain> resource) {

                resource.add(
                        linkTo(
                                methodOn(Controller.class).getDomainSubmissions(
                                        resource.getContent().getId(),
                                        new PageRequest(0, 1)))
                                .withRel("submissions")
                );

                return resource;
            }
        };
    }

    @Bean
    public ResourceProcessor<Resource<Submission>> submissionProcessor() {

        return new ResourceProcessor<Resource<Submission>>() {

            @Override
            public Resource<Submission> process(Resource<Submission> resource) {

                resource.add(
                        linkTo(
                                methodOn(Controller.class).getSubmissionSamples(
                                        resource.getContent().getId(),
                                        new PageRequest(0, 1)))
                                .withRel("samples")
                );



                return resource;
            }
        };
    }

    @Bean
    public ResourceProcessor<Resource<Sample>> sampleProcessor() {

        return new ResourceProcessor<Resource<Sample>>() {

            @Override
            public Resource<Sample> process(Resource<Sample> resource) {

                return resource;
            }
        };
    }
}
