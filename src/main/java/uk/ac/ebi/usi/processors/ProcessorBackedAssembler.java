package uk.ac.ebi.usi.processors;

import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceAssembler;
import org.springframework.hateoas.ResourceProcessor;


public class ProcessorBackedAssembler<T> implements ResourceAssembler<T, Resource<T>> {

    public ProcessorBackedAssembler(ResourceProcessor<Resource<T>> resourceProcessor) {
        this.resourceProcessor = resourceProcessor;
    }

    private ResourceProcessor<Resource<T>> resourceProcessor;

    @Override
    public Resource<T> toResource(T entity) {
        Resource<T> resource = new Resource<T>(entity);

        return resourceProcessor.process(resource);
    }
}
